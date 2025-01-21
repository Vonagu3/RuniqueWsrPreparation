package com.example.runiquewsrpreparation.auth.presentation.register

sealed interface RegisterEvent {
    data object RegistrationSuccess: RegisterEvent
    data class Error(val message: String): RegisterEvent
}