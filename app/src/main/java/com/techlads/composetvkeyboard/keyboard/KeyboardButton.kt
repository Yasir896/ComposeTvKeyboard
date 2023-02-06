package com.techlads.composetvkeyboard.keyboard

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techlads.composetvkeyboard.domain.model.Digit
import com.techlads.composetvkeyboard.domain.model.Key
import com.techlads.composetvkeyboard.domain.model.UtilityKey
import com.techlads.composetvkeyboard.theme.md_theme_dark_onPrimary

@Composable
fun KeyboardButton(key: Key, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val focusRequester = remember { FocusRequester() }

    Button(
        onClick = {
            focusRequester.requestFocus()
            onClick()
        },
        interactionSource = interactionSource,
        contentPadding = PaddingValues(0.dp),
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFocused) md_theme_dark_onPrimary else MaterialTheme.colorScheme.primaryContainer,
            contentColor = if (isFocused) MaterialTheme.colorScheme.primaryContainer else md_theme_dark_onPrimary
        ),
        modifier = Modifier
            .aspectRatio((key.span.toFloat() / 1F))
            .padding(4.dp)
            .focusRequester(focusRequester)
    ) {
        when (key) {
            is UtilityKey -> {
                Icon(
                    key.icon,
                    contentDescription = key.text,
                    modifier = Modifier.size(16.dp)
                )
            }
            else -> {
                Text(text = key.text)
            }
        }
    }
}

@Preview
@Composable
fun KeyboardButtonPreview() {
    KeyboardButton(Digit("0", "0")) {}
}