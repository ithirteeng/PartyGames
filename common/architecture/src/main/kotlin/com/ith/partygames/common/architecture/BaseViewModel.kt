package com.ith.partygames.common.architecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State : BaseState, Event : BaseEvent> : ViewModel() {

    private val _state: MutableStateFlow<State> by lazy { MutableStateFlow(initState()) }
    val state: StateFlow<State> = _state

    protected abstract fun initState(): State

    abstract fun processEvent(event: Event)

    protected fun updateState(execute: (oldState: State) -> State) {
        _state.update { execute(it) }
    }
}
