package com.example.runiquewsrpreparation.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}