package com.example.runiquewsrpreparation.auth.domain


class UserDataValidator(
    private val patternValidator: PatternValidator
) {

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

    fun isEmailValid(email: String): Boolean = patternValidator.matches(email.trim())

    companion object {
        const val MIN_PASSWORD_LENGTH = 9
    }
}