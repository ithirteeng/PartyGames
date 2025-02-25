package com.ith.partygames.screens.common_connection.common.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ith.partygames.common.mesh_utils.LocalNodeUiState

@Composable
internal fun InfoText(state: LocalNodeUiState) {
    Text(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        text = "SSID: ${state.wifiState?.connectConfig?.ssid} (${state.wifiState?.connectConfig?.band})\n" +
                "Passphrase: ${state.wifiState?.connectConfig?.passphrase}\n" +
                "LinkLocal: ${state.wifiState?.connectConfig?.linkLocalAddr}\n" +
                "MAC Address: ${state.wifiState?.connectConfig?.bssid/*: state.wifiState?.connectConfig?.linkLocalToMacAddress*/}\n" +
                "Port: ${state.wifiState?.connectConfig?.port}\n"
    )
}
