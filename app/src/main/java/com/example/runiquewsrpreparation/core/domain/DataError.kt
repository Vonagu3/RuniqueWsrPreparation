package com.example.runiquewsrpreparation.core.domain

sealed interface DataError: Error {

    enum class Network(val message: String): DataError {
        REQUEST_TIMEOUT("REQUEST_TIMEOUT"),
        UNAUTHORIZED("UNAUTHORIZED"),
        CONFLICT("CONFLICT"),
        TOO_MANY_REQUEST("TOO_MANY_REQUEST"),
        NO_INTERNET("NO_INTERNET"),
        PAYLOAD_TOO_LARGE("PAYLOAD_TOO_LARGE"),
        SERVER_ERROR("SERVER_ERROR"),
        SERIALIZATION("SERIALIZATION"),
        UNKNOWN("UNKNOWN")
    }

    enum class Local: DataError {
        DISK_FULL
    }
}