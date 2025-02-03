package com.ith.partygames.common.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}