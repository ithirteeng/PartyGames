package com.ith.partygames.screens.common_connection.client.presentation

import com.ith.partygames.common.architecture.BaseState
import com.ith.partygames.common.games.GameType
import com.ith.partygames.common.mesh_utils.LocalNodeState


internal data class ClientState(
    val gameType: GameType? = null,
    val localNodeState: LocalNodeState = LocalNodeState(),
    val nodeState: NodeState = NodeState.Init(),
) : BaseState

internal sealed interface NodeState {

    data class Init(
        val error: String? = null,
    ): NodeState

    data object ConnectedToHotspot: NodeState
}
