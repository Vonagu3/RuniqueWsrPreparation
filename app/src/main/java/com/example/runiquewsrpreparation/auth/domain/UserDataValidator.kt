package com.example.runiquewsrpreparation.auth.domain

import androidx.compose.foundation.text.input.TextFieldState

class UserDataValidator {

    fun validatePassword(password: String): PasswordValiationState {
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasDigit = password.any { it.isDigit() }
        val hasLowerCaseCharacter = password.any { it.isLowerCase() }
        val hasUpperCaseCharacter = password.any { it.isUpperCase() }

        return PasswordValiationState(
            hasMinLength = hasMinLength,
            hasNumber = hasDigit,
            hasLowerCaseCharacter = hasLowerCaseCharacter,
            hasUpperCaseCharacter = hasUpperCaseCharacter
        )
    }

    fun isEmailValid(email: String): Boolean {

    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 9
    }
}