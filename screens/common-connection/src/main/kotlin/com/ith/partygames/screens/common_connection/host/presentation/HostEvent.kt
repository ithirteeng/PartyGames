package com.ith.partygames.screens.common_connection.host.presentation

import com.ith.partygames.common.architecture.BaseEvent

internal sealed interface HostEvent : BaseEvent {

    data object Init : HostEvent

    data object StartHotspotHostEvent : HostEvent

    data object StartGameHostEvent : HostEvent

    data object StopHotspotEvent : HostEvent
}
