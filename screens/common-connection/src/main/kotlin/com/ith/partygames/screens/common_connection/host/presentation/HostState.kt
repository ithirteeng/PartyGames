package com.ith.partygames.screens.common_connection.host.presentation

import com.ith.partygames.common.architecture.BaseState
import com.ith.partygames.common.games.GameType
import com.ith.partygames.common.mesh_utils.LocalNodeUiState


internal data class CommonConnectionHostState(
    val gameType: GameType? = null,
    val localNodeState: LocalNodeUiState = LocalNodeUiState(),
    val hostState: HostState = HostState.Init(),
) : BaseState

internal sealed interface HostState {

    data class Init(
        val error: String? = null,
    ): HostState

    data object HotspotActivated: HostState
}
