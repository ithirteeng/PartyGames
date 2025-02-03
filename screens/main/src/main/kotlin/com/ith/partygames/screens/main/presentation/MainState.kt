package com.ith.partygames.screens.main.presentation

import com.ith.partygames.common.architecture.BaseState

sealed interface MainState: BaseState {

    data object Loading: MainState

    data class Content(
        val name: String
    ): MainState
}