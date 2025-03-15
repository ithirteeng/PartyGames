package com.ith.partygames.screens.common_connection.host.data.model

import com.ustadmobile.meshrabiya.vnet.VirtualNode

data class ClientNodeState(
    val isReady: Boolean = false,
    val nodeAddress: Int,
    val info: VirtualNode.LastOriginatorMessage,
)
