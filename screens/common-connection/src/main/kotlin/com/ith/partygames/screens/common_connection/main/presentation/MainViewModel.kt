package com.ith.partygames.screens.common_connection.main.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.ith.partygames.common.architecture.BaseViewModel
import com.ith.partygames.screens.common_connection.main.navigation.CommonConnectionMainRoute

internal class MainViewModel(
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<MainState, MainEvent>() {

    override fun initState(): MainState = MainState.Loading

    override fun processEvent(event: MainEvent) {
        when (event) {
            is MainEvent.Init -> init()
        }
    }

    private fun init() {
        val arguments = savedStateHandle.toRoute<CommonConnectionMainRoute>()
        updateState {
            MainState.Content(gameType = arguments.gameType)
        }
    }
}
