package com.example.runiquewsrpreparation.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import com.example.runiquewsrpreparation.auth.domain.PasswordValiationState

data class RegisterState(
    val email: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValiationState = PasswordValiationState(),
    val canRegister: Boolean = false
)
