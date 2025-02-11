package com.ith.partygames.screens.common_connection.main.presentation

import com.ith.partygames.common.architecture.BaseState
import com.ith.partygames.common.games.GameType

sealed interface CommonConnectionMainState : BaseState {

    data object Loading : CommonConnectionMainState

    data class Content(
        val gameType: GameType
    ) : CommonConnectionMainState
}
