package com.ith.partygames.screens.common_connection.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ith.partygames.common.ui.theme.PartyGamesTheme

@Composable
fun ErrorTextBox(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = PartyGamesTheme.materialColors.errorContainer,
                shape = RoundedCornerShape(32.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = message,
            color = PartyGamesTheme.materialColors.error
        )
    }
}
