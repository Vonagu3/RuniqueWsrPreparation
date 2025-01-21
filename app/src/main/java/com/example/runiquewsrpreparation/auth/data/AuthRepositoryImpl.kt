package com.example.runiquewsrpreparation.auth.data

import com.example.runiquewsrpreparation.auth.data.models.LoginRequest
import com.example.runiquewsrpreparation.auth.data.models.LoginResponse
import com.example.runiquewsrpreparation.auth.data.models.RegisterRequest
import com.example.runiquewsrpreparation.auth.domain.AuthInfo
import com.example.runiquewsrpreparation.auth.domain.AuthRepository
import com.example.runiquewsrpreparation.auth.domain.SessionStorage
import com.example.runiquewsrpreparation.core.data.network.post
import com.example.runiquewsrpreparation.core.domain.DataError
import com.example.runiquewsrpreparation.core.domain.EmptyResult
import com.example.runiquewsrpreparation.core.domain.Result
import com.example.runiquewsrpreparation.core.domain.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
) : AuthRepository {
    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "register",
            body = RegisterRequest(
                email, password
            )
        )
    }

    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "login",
            body = LoginRequest(email, password)
        )
        if (result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }
        return result.asEmptyDataResult()
    }
}