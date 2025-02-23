package com.ith.partygames.screens.common_connection.main.presentation

import com.ith.partygames.common.architecture.BaseState
import com.ith.partygames.common.games.GameType

internal sealed interface MainState : BaseState {

    data object Loading : MainState

    data class Content(
        val gameType: GameType
    ) : MainState
}
