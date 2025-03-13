package com.ith.partygames.common.mesh_utils

import android.os.Build
import com.ustadmobile.meshrabiya.vnet.VirtualNode
import com.ustadmobile.meshrabiya.vnet.bluetooth.MeshrabiyaBluetoothState
import com.ustadmobile.meshrabiya.vnet.wifi.ConnectBand
import com.ustadmobile.meshrabiya.vnet.wifi.HotspotType
import com.ustadmobile.meshrabiya.vnet.wifi.state.MeshrabiyaWifiState

data class LocalNodeUiState(
    val localAddress: Int = 0,
    val deviceName: String? = null,
    val bandOptions: List<ConnectBand> = listOf(ConnectBand.BAND_2GHZ),
    val hotspotTypeOptions: List<HotspotType> = listOf(
        HotspotType.AUTO,
        HotspotType.WIFIDIRECT_GROUP, HotspotType.LOCALONLY_HOTSPOT
    ),
    val band: ConnectBand = bandOptions.first(),
    val hotspotTypeToCreate: HotspotType = hotspotTypeOptions.first(),
    val wifiState: MeshrabiyaWifiState? = null,
    val bluetoothState: MeshrabiyaBluetoothState? = null,
    val connectUri: String? = null,
    val nodes: Map<Int, VirtualNode.LastOriginatorMessage> = emptyMap(),
) {

    val incomingConnectionsEnabled: Boolean
        get() = wifiState?.connectConfig != null

    val connectBandVisible: Boolean
        get() = Build.VERSION.SDK_INT >= 29 && wifiState?.connectConfig == null
}
