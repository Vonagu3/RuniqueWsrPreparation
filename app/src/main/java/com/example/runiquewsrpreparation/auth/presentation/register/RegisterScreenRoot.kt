package com.example.runiquewsrpreparation.auth.presentation.register

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.runiquewsrpreparation.R
import com.example.runiquewsrpreparation.auth.domain.UserDataValidator
import com.example.runiquewsrpreparation.core.presentation.designsystem.component.GradientBackground
import com.example.runiquewsrpreparation.core.presentation.designsystem.component.RuniqueTextField
import com.example.runiquewsrpreparation.ui.theme.CheckIcon
import com.example.runiquewsrpreparation.ui.theme.CrossIcon
import com.example.runiquewsrpreparation.ui.theme.EmailIcon
import com.example.runiquewsrpreparation.ui.theme.EyeClosedIcon
import com.example.runiquewsrpreparation.ui.theme.EyeOpenedIcon
import com.example.runiquewsrpreparation.ui.theme.LockIcon

@Composable
fun RegisterScreenRoot(
    onSignInClick: () -> Unit,
    onSuccessfulRegisteration: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    RegisterScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                RegisterAction.OnLoginClick -> onSignInClick()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    GradientBackground {
        Text(text = "Welcome to " + stringResource(R.string.runique))
        Spacer(Modifier.height(16.dp))

        RuniqueTextField(
            state.email,
            hint = "Введите Email",
            startIcon = EmailIcon,
            icon = if (state.isEmailValid) CheckIcon else CrossIcon,
            title = "Email",
            additionalInfo = "Email must be correct",
        )
        RuniqueTextField(
            state.password,
            hint = "Введите пароль",
            startIcon = LockIcon,
            icon = if (state.isPasswordVisible) EyeClosedIcon else EyeOpenedIcon,
            title = "Password",
            keyboardType = KeyboardType.Password,
            visibleTextObfuscationMode = state.isPasswordVisible,
            onClick = { onAction(RegisterAction.OnTogglePasswordVisibilityClick) }
        )
        Spacer(Modifier.height(16.dp))

        PasswordValidator(
            state.passwordValidationState.hasMinLength,
            "Длина пароля должна быть больше ${UserDataValidator.MIN_PASSWORD_LENGTH}"
        )
        PasswordValidator(
            state.passwordValidationState.hasUpperCaseCharacter,
            "Пароль должен содержать хотя бы 1 заглавную букву"
        )
        PasswordValidator(
            state.passwordValidationState.hasLowerCaseCharacter,
            "Пароль должен содержать хотя бы 1 маленькую букву"
        )
        PasswordValidator(
            state.passwordValidationState.hasNumber,
            "Пароль должен содержать хотя бы 1 цифру"
        )

        Button(
            onClick = { onAction(RegisterAction.OnRegisterClick) },
            enabled = state.canRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зарегистрироваться")
        }
    }
}


@Composable
private fun PasswordValidator(
    isValid: Boolean,
    text: String,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = if (isValid) CheckIcon else CrossIcon,
            contentDescription = if (isValid) "Valid" else "Not Valid"
        )
        Spacer(Modifier.width(10.dp))
        Text(text)
    }
}


@Preview
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(
        state = RegisterState(),
        onAction = {}
    )
}