package com.example.runiquewsrpreparation.auth.domain

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfo(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
