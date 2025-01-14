package com.example.runiquewsrpreparation.auth.data

import android.util.Patterns
import com.example.runiquewsrpreparation.auth.domain.PatternValidator

object EmailPatternValidator : PatternValidator {
    override fun matches(value: String) = Patterns.EMAIL_ADDRESS.matcher(value).matches()
}