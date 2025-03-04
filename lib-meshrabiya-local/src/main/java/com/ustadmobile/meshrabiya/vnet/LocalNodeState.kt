package com.ustadmobile.meshrabiya.vnet

import com.ith.partygames.common.games.GameType
import com.ustadmobile.meshrabiya.vnet.bluetooth.MeshrabiyaBluetoothState
import com.ustadmobile.meshrabiya.vnet.wifi.state.MeshrabiyaWifiState

data class LocalNodeState(
    val address: Int = 0,
    val deviceName: String? = null,
    val wifiState: MeshrabiyaWifiState = MeshrabiyaWifiState(),
    val bluetoothState: MeshrabiyaBluetoothState = MeshrabiyaBluetoothState(deviceName = ""),
    val connectUri: String? = null,
    val originatorMessages: Map<Int, VirtualNode.LastOriginatorMessage> = emptyMap(),
    val gameType: GameType = GameType.UNKNOWN,
)
