package com.ith.partygames.common.architecture

abstract class ComplexViewModel<State : BaseState, Event : BaseEvent, Effect : BaseEffect> : BaseViewModel<State, Event>() {

    abstract fun processEffect(effect: Effect)
}
