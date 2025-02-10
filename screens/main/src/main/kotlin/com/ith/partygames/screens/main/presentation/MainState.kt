package com.ith.partygames.screens.main.presentation

import com.ith.partygames.common.architecture.BaseState
import com.ith.partygames.common.games.GameType

data class MainState(
    val games: List<GameType> = GameType.entries
): BaseState