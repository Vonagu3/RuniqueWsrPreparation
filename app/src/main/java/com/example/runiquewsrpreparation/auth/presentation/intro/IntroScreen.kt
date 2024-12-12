package com.example.runiquewsrpreparation.auth.presentation.intro

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.runiquewsrpreparation.R
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
//    GradientBackground {
        Text(
            text = stringResource(id = R.string.welcome_to_runique),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp
        )
//    }
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