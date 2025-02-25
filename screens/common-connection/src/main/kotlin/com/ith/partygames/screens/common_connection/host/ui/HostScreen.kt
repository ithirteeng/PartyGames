package com.ith.partygames.screens.common_connection.host.ui

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.zxing.BarcodeFormat
import com.ith.partygames.common.ui.theme.PartyGamesTheme
import com.ith.partygames.screens.common_connection.R
import com.ith.partygames.screens.common_connection.common.ui.InfoText
import com.ith.partygames.screens.common_connection.host.presentation.HostEffect
import com.ith.partygames.screens.common_connection.host.presentation.HostEvent
import com.ith.partygames.screens.common_connection.host.presentation.HostState
import com.ith.partygames.screens.common_connection.host.presentation.HostViewModel
import com.ith.partygames.screens.common_connection.host.presentation.HotspotState
import com.journeyapps.barcodescanner.BarcodeEncoder
import org.koin.androidx.compose.koinViewModel
import kotlin.math.min

@Composable
internal fun HostScreen(
    viewModel: HostViewModel = koinViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(null) {
        viewModel.processEvent(HostEvent.Init)
        observeEffects(context, viewModel)
    }

    val state = viewModel.state.collectAsStateWithLifecycle().value
    Content(
        processEvent = viewModel::processEvent,
        state = state
    )
}

@Composable
private fun Content(
    processEvent: (event: HostEvent) -> Unit,
    state: HostState
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
            when (state.hotspotState) {
                is HotspotState.Init -> InitContent(processEvent, state.hotspotState)
                is HotspotState.HotspotActivated -> HotspotActivatedContent(processEvent, state)
            }
        }
    }
}

@Composable
private fun InitContent(
    processEvent: (event: HostEvent) -> Unit,
    state: HotspotState.Init
) {
    Column {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { processEvent(HostEvent.StartHotspotHostEvent) }
        ) {
            Text(text = stringResource(R.string.start_hotspot))
        }
        if (state.error != null) {
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
                    text = state.error,
                    color = PartyGamesTheme.materialColors.error
                )
            }
        }
    }
}

@Composable
private fun HotspotActivatedContent(
    processEvent: (event: HostEvent) -> Unit,
    state: HostState
) {
    val barcodeEncoder = remember {
        BarcodeEncoder()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        InfoText(state.localNodeState)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { processEvent(HostEvent.StopHotspotEvent) }
        ) {
            Text(text = stringResource(R.string.stop_hotspot))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { processEvent(HostEvent.StartGameHostEvent) }
        ) {
            Text(text = stringResource(R.string.start_game))
        }

        if (state.localNodeState.connectUri != null && state.localNodeState.incomingConnectionsEnabled) {
            val config = LocalConfiguration.current
            val screenWidth = config.screenWidthDp
            val screenWidthPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                screenWidth.toFloat(),
                Resources.getSystem().displayMetrics,
            )
            val width = min(screenWidthPx.toInt(), 900)

            val qrCodeBitmap = remember(state.localNodeState.connectUri) {
                barcodeEncoder.encodeBitmap(
                    state.localNodeState.connectUri, BarcodeFormat.QR_CODE, width, width
                ).asImageBitmap()
            }

            Image(
                bitmap = qrCodeBitmap,
                contentDescription = null
            )
        }
    }
}

private suspend fun observeEffects(
    context: Context,
    viewModel: HostViewModel
) {
    viewModel.effectsFlow.collect { effect ->
        when (effect) {
            is HostEffect.ShowErrorMessage -> Toast.makeText(
                context, effect.message, Toast.LENGTH_SHORT
            ).show()
        }
    }
}
