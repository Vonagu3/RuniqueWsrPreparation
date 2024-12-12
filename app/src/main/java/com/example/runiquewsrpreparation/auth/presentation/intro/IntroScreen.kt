package com.example.runiquewsrpreparation.auth.presentation.intro

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.runiquewsrpreparation.ui.theme.RuniqueWsrPreparationTheme

@Composable
fun IntroScreenRoot(
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit
) {

    IntroScreen(
        onAction = { action ->
            when(action) {
                IntroAction.OnSignInClick -> onSignInClick()
                IntroAction.OnSignUpClick -> onSignUpClick()
            }
        }
    )
}

@Composable
fun IntroScreen(
    onAction: (IntroAction) -> Unit
) {
    onAction(IntroAction.OnSignInClick)
}

@Preview
@Composable
private fun IntroScreenPrev() {
    RuniqueWsrPreparationTheme {
        IntroScreen(
            onAction = {}
        )
    }
}