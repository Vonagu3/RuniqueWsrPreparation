package com.example.runiquewsrpreparation.auth.data.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String
)
