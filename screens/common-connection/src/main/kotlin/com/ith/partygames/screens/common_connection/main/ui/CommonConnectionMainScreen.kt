package com.ith.partygames.screens.common_connection.main.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ith.partygames.common.games.GameType
import com.ith.partygames.common.ui.components.LoadingIndicator
import com.ith.partygames.screens.common_connection.R
import com.ith.partygames.screens.common_connection.main.presentation.MainEvent
import com.ith.partygames.screens.common_connection.main.presentation.MainState
import com.ith.partygames.screens.common_connection.main.presentation.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CommonConnectionMainScreen(
    viewModel: MainViewModel = koinViewModel(),
    onBecomeHostButtonClick: (gameType: GameType) -> Unit,
    onBecomeClientButtonClick: (gameType: GameType) -> Unit,
) {
    when (val state = viewModel.state.collectAsState().value) {
        is MainState.Loading -> {
            LaunchedEffect(null) { viewModel.processEvent(MainEvent.Init) }
            LoadingIndicator()
        }

        is MainState.Content -> {
            Content(
                state = state,
                onBecomeHostButtonClick = { onBecomeHostButtonClick(state.gameType) },
                pnBecomeClientButtonClick = { onBecomeClientButtonClick(state.gameType) }
            )
        }
    }
}

@Composable
private fun Content(
    state: MainState.Content,
    onBecomeHostButtonClick: () -> Unit,
    pnBecomeClientButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = state.gameType.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier,
            onClick = onBecomeHostButtonClick
        ) {
            Text(text = stringResource(R.string.create_host))
        }

        Button(
            modifier = Modifier,
            onClick = pnBecomeClientButtonClick
        ) {
            Text(text = stringResource(R.string.connect_to_host))
        }
    }
}
