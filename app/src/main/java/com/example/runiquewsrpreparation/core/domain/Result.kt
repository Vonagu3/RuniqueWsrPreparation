package com.example.runiquewsrpreparation.core.domain

import com.example.runiquewsrpreparation.core.domain.Error as ErrorMarker

sealed interface Result<out D, out E: ErrorMarker> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: ErrorMarker>(val error: E): Result<Nothing, E>
}