package com.example.runiquewsrpreparation.auth.data

import com.example.runiquewsrpreparation.auth.domain.AuthInfo

fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerialazable {
    return AuthInfoSerialazable(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}

fun AuthInfoSerialazable.toAuthInfo(): AuthInfo {
    return AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}