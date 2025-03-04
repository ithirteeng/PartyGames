package com.ith.partygames.screens.common_connection.client.ui

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ith.partygames.screens.common_connection.R
import com.ith.partygames.screens.common_connection.client.presentation.ClientEffect
import com.ith.partygames.screens.common_connection.client.presentation.ClientEvent
import com.ith.partygames.screens.common_connection.client.presentation.ClientState
import com.ith.partygames.screens.common_connection.client.presentation.ClientViewModel
import com.ith.partygames.screens.common_connection.client.presentation.NodeState
import com.ith.partygames.screens.common_connection.common.ui.ErrorTextBox
import com.ith.partygames.screens.common_connection.common.ui.InfoText
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CommonConnectionClientScreen(
    viewModel: ClientViewModel = koinViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(null) {
        viewModel.processEvent(ClientEvent.Init)
        observeEffects(context, viewModel)
    }

    val state = viewModel.state.collectAsStateWithLifecycle().value
    Content(
        state = state,
        processEvent = viewModel::processEvent
    )
}

@Composable
private fun Content(
    state: ClientState,
    processEvent: (ClientEvent) -> Unit,
) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp)
    ) {
        item {
            if (state.gameType != null) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.gameType.name
                )
            }
        }
        item {
            when(state.nodeState) {
                is NodeState.Init -> InitContent(
                    state = state.nodeState,
                    processEvent = processEvent
                )
                is NodeState.ConnectedToHotspot -> ConnectedToHotspotContent(
                    state = state,
                    processEvent = processEvent
                )
            }
        }
    }
}

@Composable
private fun InitContent(
    state: NodeState.Init,
    processEvent: (ClientEvent) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        QrCodeScanner(processEvent = processEvent)
        if (state.error != null) {
            ErrorTextBox(state.error)
        }
    }
}

@Composable
private fun ConnectedToHotspotContent(
    state: ClientState,
    processEvent: (ClientEvent) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        InfoText(state.localNodeState.wifiState)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { processEvent(ClientEvent.DisconnectFromHotspot) }
        ) {
            Text(text = stringResource(R.string.disconnect_from_hotspot))
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { processEvent(ClientEvent.SendReadyToPlayEvent) }
        ) {
            Text(text = stringResource(R.string.ready_to_play))
        }
    }
}

@Composable
private fun QrCodeScanner(
    processEvent: (ClientEvent) -> Unit
) {
    val qrCodeScannerLauncher = rememberLauncherForActivityResult(
        contract = ScanContract()
    ) { result ->
        val link = result.contents
        processEvent(ClientEvent.ConnectToHotspotWithLink(link = link))
    }
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            qrCodeScannerLauncher.launch(
                ScanOptions().apply {
                    setOrientationLocked(false)
                    setPrompt("Bullshit, big stinky bullshit")
                }
            )
        }
    ) {
        Text(stringResource(R.string.scan_qr_code))
    }
}

private suspend fun observeEffects(
    context: Context,
    viewModel: ClientViewModel
) {
    viewModel.effectsFlow.collect { effect ->
        when (effect) {
            is ClientEffect.ShowErrorMessage -> Toast.makeText(
                context, effect.message, Toast.LENGTH_SHORT
            ).show()
        }
    }
}
