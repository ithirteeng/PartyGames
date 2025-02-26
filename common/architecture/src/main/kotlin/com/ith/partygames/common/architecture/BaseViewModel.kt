package com.ith.partygames.common.architecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

abstract class BaseViewModel<State : BaseState, Event : BaseEvent> : ViewModel() {

    private val _state: MutableStateFlow<State> by lazy { MutableStateFlow(initState()) }
    val state: StateFlow<State> = _state

    protected abstract fun initState(): State

    open fun processEvent(event: Event) {
        Timber
            .tag(getTagForLogging())
            .d("process event: $event")
    }

    protected fun updateState(execute: (oldState: State) -> State) {
        _state.update { execute(it) }
    }

    private fun getTagForLogging(): String {
        val parts = this.javaClass.name.split(".").toMutableList()
        parts.remove("presentation")
        parts.remove("screens")
        parts.remove("com")
        parts.remove("ith")
        parts.remove("partygames")
        return parts.joinToString(".")
    }
}
