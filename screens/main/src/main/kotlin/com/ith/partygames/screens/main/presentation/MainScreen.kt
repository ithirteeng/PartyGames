package com.ith.partygames.screens.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.ith.partygames.common.ui.components.LoadingIndicator
import com.ith.partygames.common.ui.theme.PartyGamesTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel(),
) {

    val state = viewModel.state.collectAsState().value
    when (state) {
        is MainState.Loading -> LoadingIndicator(color = PartyGamesTheme.colors.lime)
        is MainState.Content -> {}
    }
}