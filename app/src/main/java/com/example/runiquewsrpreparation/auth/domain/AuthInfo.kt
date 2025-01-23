package com.example.runiquewsrpreparation.auth.domain


data class AuthInfo(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
