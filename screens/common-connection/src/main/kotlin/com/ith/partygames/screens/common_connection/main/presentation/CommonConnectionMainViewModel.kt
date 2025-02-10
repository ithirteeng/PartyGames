package com.ith.partygames.screens.common_connection.main.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.ith.partygames.common.architecture.BaseViewModel
import com.ith.partygames.screens.common_connection.main.navigation.CommonConnectionMainRoute

class CommonConnectionMainViewModel(
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<CommonConnectionMainState, CommonConnectionMainEvent>() {

    override fun initState(): CommonConnectionMainState = CommonConnectionMainState.Loading

    override fun processEvent(event: CommonConnectionMainEvent) {
        when (event) {
            is CommonConnectionMainEvent.Init -> init()
        }
    }

    private fun init() {
        val arguments = savedStateHandle.toRoute<CommonConnectionMainRoute>()
        updateState {
            CommonConnectionMainState.Content(gameType = arguments.gameType)
        }
    }
}