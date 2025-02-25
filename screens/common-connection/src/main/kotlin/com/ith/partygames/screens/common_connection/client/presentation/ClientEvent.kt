package com.ith.partygames.screens.common_connection.client.presentation

import com.ith.partygames.common.architecture.BaseEvent

internal sealed interface ClientEvent : BaseEvent {

    data object Init : ClientEvent

    data object ConnectToHotspot: ClientEvent

    data object SendReadyToPlayEvent: ClientEvent

    data object DisconnectFromHotspot: ClientEvent
}
