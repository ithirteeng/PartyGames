package com.ith.partygames.screens.main.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.ith.partygames.common.games.GameType
import com.ith.partygames.screens.main.presentation.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel(),
    navigateToCommonConnectionScreen: (GameType) -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    LazyColumn {
        items(state.games) { gameType ->
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navigateToCommonConnectionScreen(gameType) }
            ) {
                Text(text = gameType.name)
            }
        }
    }
}