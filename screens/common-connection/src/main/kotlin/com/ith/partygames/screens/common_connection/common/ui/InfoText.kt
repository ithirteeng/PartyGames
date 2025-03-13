package com.ith.partygames.screens.common_connection.common.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ith.partygames.common.mesh_utils.LocalNodeUiState
import com.ith.partygames.common.ui.components.LoadingIndicator
import com.ustadmobile.meshrabiya.ext.addressToDotNotation
import com.ustadmobile.meshrabiya.vnet.wifi.state.MeshrabiyaWifiState
import com.ustadmobile.meshrabiya.vnet.wifi.state.WifiStationState

@Composable
internal fun InfoText(state: LocalNodeUiState) {
    Text(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        text = "SSID: ${state.wifiState?.connectConfig?.ssid} (${state.wifiState?.connectConfig?.band})\n" +
                "Passphrase: ${state.wifiState?.connectConfig?.passphrase}\n" +
                "LinkLocal: ${state.wifiState?.connectConfig?.linkLocalAddr}\n" +
                "MAC Address: ${state.wifiState?.connectConfig?.bssid/*: state.wifiState?.connectConfig?.linkLocalToMacAddress*/}\n" +
                "Port: ${state.wifiState?.connectConfig?.port}\n" +
                "IP: ${state.localAddress.addressToDotNotation()}\n"
    )
}

@Composable
internal fun InfoText(state: MeshrabiyaWifiState?) {
    if (state != null) {
        when (state.wifiStationState.status) {
            WifiStationState.Status.CONNECTING -> LoadingIndicator()
            else -> Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = "Station SSID: ${state.wifiStationState.config?.ssid}\n" +
                        "Station IP: ${state.wifiStationState.config?.nodeVirtualAddr?.addressToDotNotation()}\n" +
                        "Status: ${state.wifiStationState.status}\n" +
                        "Passphrase: ${state.wifiStationState.config?.passphrase}\n" +
                        "Port: ${state.wifiStationState.config?.port}"
            )
        }
    }
}
