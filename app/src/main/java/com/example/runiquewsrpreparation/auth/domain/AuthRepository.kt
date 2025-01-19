package com.example.runiquewsrpreparation.auth.domain

import com.example.runiquewsrpreparation.core.domain.DataError
import com.example.runiquewsrpreparation.core.domain.EmptyResult
import com.example.runiquewsrpreparation.core.domain.Result

interface AuthRepository {
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
}