package com.ith.partygames.screens.common_connection.main.presentation

import com.ith.partygames.common.architecture.BaseEvent

sealed interface MainEvent : BaseEvent {

    data object Init : MainEvent
}
