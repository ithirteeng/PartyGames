package com.ith.partygames.screens.common_connection.host.domain

import com.ith.partygames.common.mesh_utils.LocalNodeState
import com.ith.partygames.screens.common_connection.host.data.model.ClientNodeState
import kotlinx.coroutines.flow.StateFlow

internal interface HostRepository {

    val localNodeState: StateFlow<LocalNodeState>
    val clientNodes: StateFlow<List<ClientNodeState>>

    suspend fun init()

    suspend fun enableHotspot(): Result<Unit>

    suspend fun disableHotspot(): Result<Unit>

    fun setNodeIsReady(nodeAddress: Int)
}
