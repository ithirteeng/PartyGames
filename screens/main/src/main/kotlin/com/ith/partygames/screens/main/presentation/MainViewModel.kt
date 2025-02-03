package com.ith.partygames.screens.main.presentation

import com.ith.partygames.common.architecture.BaseViewModel

class MainViewModel: BaseViewModel<MainState, MainEvent>() {

    override fun initState(): MainState = MainState.Loading

    override fun processEvent(event: MainEvent) {
        //todo add events
    }
}