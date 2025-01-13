package com.example.runiquewsrpreparation.auth.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.runiquewsrpreparation.core.presentation.designsystem.component.RuniqueTextField

@Composable
fun RegisterScreenRoot(
    viewModel: RegisterViewModel
) {
    RegisterScreen(
        viewModel.state,
        viewModel::onAction
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    RuniqueTextField(
        state = state.email,
        hint = "hjsdgfhjdsf",
        onClick = {it}
    )
}

@Preview
@Composable
private fun RegisterScreenPreview() {

}