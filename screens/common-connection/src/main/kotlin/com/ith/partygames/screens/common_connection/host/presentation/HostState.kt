package com.ith.partygames.screens.common_connection.host.presentation

import com.ith.partygames.common.architecture.BaseState
import com.ith.partygames.common.games.GameType
import com.ith.partygames.common.mesh_utils.LocalNodeState
import com.ith.partygames.screens.common_connection.host.data.model.ClientNodeState


internal data class HostState(
    val gameType: GameType? = null,
    val localNodeState: LocalNodeState = LocalNodeState(),
    val clientNodes: List<ClientNodeState> = emptyList(),
    val hotspotState: HotspotState = HotspotState.Init(),
) : BaseState

internal sealed interface HotspotState {

    data class Init(
        val error: String? = null,
    ): HotspotState

    data object HotspotActivated: HotspotState
}
