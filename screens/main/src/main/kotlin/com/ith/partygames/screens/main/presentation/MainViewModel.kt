package com.ith.partygames.screens.main.presentation

import androidx.lifecycle.SavedStateHandle
import com.ith.partygames.common.architecture.BaseViewModel

class MainViewModel(
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<MainState, MainEvent>() {

    override fun initState(): MainState = MainState()

    override fun processEvent(event: MainEvent) {}
}
