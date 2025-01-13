package com.example.runiquewsrpreparation.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runiquewsrpreparation.auth.domain.PasswordValiationState
import com.example.runiquewsrpreparation.auth.domain.UserDataValidator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RegisterViewModel constructor(
    private val userDataValidator: UserDataValidator
): ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

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
        when(action) {
            RegisterAction.OnRegisterClick -> TODO()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }
            else -> Unit
        }
    }

}