package com.ith.partygames.screens.common_connection.main.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.ith.partygames.screens.common_connection.main.presentation.CommonConnectionMainEvent
import com.ith.partygames.screens.common_connection.main.presentation.CommonConnectionMainState
import com.ith.partygames.screens.common_connection.main.presentation.CommonConnectionMainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CommonConnectionMainScreen(
    viewModel: CommonConnectionMainViewModel = koinViewModel(),
) {
    when (val state = viewModel.state.collectAsState().value) {
        is CommonConnectionMainState.Loading -> viewModel.processEvent(CommonConnectionMainEvent.Init)
        is CommonConnectionMainState.Content -> {
            Text(text = state.gameType.name)
        }
    }
}