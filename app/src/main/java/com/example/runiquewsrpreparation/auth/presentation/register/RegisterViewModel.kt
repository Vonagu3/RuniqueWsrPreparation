package com.example.runiquewsrpreparation.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runiquewsrpreparation.auth.domain.AuthRepository
import com.example.runiquewsrpreparation.auth.domain.UserDataValidator
import com.example.runiquewsrpreparation.core.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    private val _eventFlow = MutableSharedFlow<RegisterEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        snapshotFlow { state.password.text }
            .onEach { password ->
                val passwordState = userDataValidator.validatePassword(password.toString())
                state = state.copy(
                    passwordValidationState = passwordState,
                    canRegister = state.isEmailValid && passwordState.isValidPassword
                )
            }.launchIn(viewModelScope)

        snapshotFlow { state.email.text }
            .onEach { email ->
                val isValidEmail = userDataValidator.isEmailValid(email.toString())
                state = state.copy(
                    isEmailValid = isValidEmail,
                    canRegister = isValidEmail && state.passwordValidationState.isValidPassword
                )
            }.launchIn(viewModelScope)
    }

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }

            else -> Unit
        }
    }

    private fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = repository.register(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString().trim()
            )
            state = state.copy(isRegistering = false)

            when (result) {
                is Result.Error -> {
                    _eventFlow.emit(RegisterEvent.Error(result.error.message))
                }

                is Result.Success -> {
                    _eventFlow.emit(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }
}