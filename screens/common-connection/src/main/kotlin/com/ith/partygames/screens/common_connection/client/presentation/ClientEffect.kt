package com.ith.partygames.screens.common_connection.client.presentation

import com.ith.partygames.common.architecture.BaseEffect

internal sealed interface ClientEffect: BaseEffect {

    data class ShowErrorMessage(val message: String?): ClientEffect
}
