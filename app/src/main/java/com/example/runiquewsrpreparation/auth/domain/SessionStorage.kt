package com.example.runiquewsrpreparation.auth.domain

interface SessionStorage {
    suspend fun get(): AuthInfo?
    suspend fun set(info: AuthInfo?)
}