package com.example.runiquewsrpreparation.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoSerialazable(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
