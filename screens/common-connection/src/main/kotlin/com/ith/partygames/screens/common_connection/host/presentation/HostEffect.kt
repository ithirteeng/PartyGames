package com.ith.partygames.screens.common_connection.host.presentation

import com.ith.partygames.common.architecture.BaseEffect

internal sealed interface HostEffect: BaseEffect {

    data class ShowErrorMessage(val message: String?): HostEffect
}
