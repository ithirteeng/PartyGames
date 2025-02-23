package com.ith.partygames.common.architecture

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class ComplexViewModel<State : BaseState, Event : BaseEvent, Effect : BaseEffect> :
    BaseViewModel<State, Event>() {

    private val _effectsFlow = MutableSharedFlow<Effect>()
    val effectsFlow = _effectsFlow.asSharedFlow()

    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch {
            _effectsFlow.emit(effect)
        }
    }
}
