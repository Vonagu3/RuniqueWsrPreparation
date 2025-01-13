package com.example.runiquewsrpreparation.core.presentation.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.runiquewsrpreparation.ui.theme.CheckIcon
import com.example.runiquewsrpreparation.ui.theme.EmailIcon
import com.example.runiquewsrpreparation.ui.theme.RuniqueWsrPreparationTheme


@Composable
fun RuniqueTextField(
    state: TextFieldState,
    hint: String,
    modifier: Modifier = Modifier,
    startIcon: ImageVector? = null,
    firstEndIcon: ImageVector? = null,
    secondEndIcon: ImageVector? = null,
    title: String? = null,
    additionalInfo: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    getTextObfuscationMode: (Boolean) -> TextObfuscationMode = { TextObfuscationMode.Visible },
    onClick: (Boolean) -> Boolean
) {
    Column(
        modifier = modifier
    ) {
        RoniqueTextInfo(title, additionalInfo)
        Spacer(modifier = Modifier.height(4.dp))
        RoniqueSecureTextField(
            state = state,startIcon = startIcon,
            firstEndIcon = firstEndIcon,
            secondEndIcon = secondEndIcon,
            hint = hint,
            keyboardType = keyboardType,
            getTextObfuscationMode = getTextObfuscationMode,
            onClick = onClick,
        )
    }
}


@Composable
private fun RoniqueTextInfo(
    title: String?,
    additionalInfo: String?,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        title?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        additionalInfo?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
        }
    }
}


@Composable
private fun RoniqueSecureTextField(
    state: TextFieldState,
    startIcon: ImageVector? = null,
    firstEndIcon: ImageVector? = null,
    secondEndIcon: ImageVector? = null,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    getTextObfuscationMode: (Boolean) -> TextObfuscationMode = { TextObfuscationMode.Visible },
    onClick: (Boolean) -> Boolean
) {
    var isFocused by remember { mutableStateOf(false) }
    var changeEndIconCondition by remember { mutableStateOf(true) }

    val click = { changeEndIconCondition = onClick(changeEndIconCondition) }

    BasicSecureTextField(
        state = state,
        textObfuscationMode = getTextObfuscationMode(changeEndIconCondition),
        textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isFocused) {
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
                } else {
                    MaterialTheme.colorScheme.surface
                }
            )
            .border(
                width = 1.dp,
                color = if (isFocused)
                    MaterialTheme.colorScheme.primary
                else
                    Color.Transparent
            )
            .padding(12.dp)
            .onFocusChanged {
                isFocused = it.isFocused
            },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
        decorator = { innerBox ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                startIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Box(modifier = Modifier.weight(1f)) {
                    if (state.text.isEmpty() && !isFocused) {
                        Text(
                            text = hint,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    innerBox()
                }
                firstEndIcon?.let {
                    Spacer(modifier = Modifier.width(16.dp))
                    if (secondEndIcon !== null) {
                        IconButton(
                            onClick = click,
                            content = {
                                Icon(
                                    imageVector = if (changeEndIconCondition) firstEndIcon else secondEndIcon,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                        )
                    } else {
                        IconButton(
                            onClick = click,
                            content = {
                                Icon(
                                    imageVector = firstEndIcon,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                        )
                    }
                }
            }
        }
    )
}


@Preview
@Composable
private fun RuniqueTextFieldPreview() {
    RuniqueWsrPreparationTheme {
        RuniqueTextField(
            state = rememberTextFieldState(),
            startIcon = EmailIcon,
            firstEndIcon = CheckIcon,
            hint = "example@test.com",
            title = "Email",
            additionalInfo = "Must be a valid email",
            onClick = { true },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
