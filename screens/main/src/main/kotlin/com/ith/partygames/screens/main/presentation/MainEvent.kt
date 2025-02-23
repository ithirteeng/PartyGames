package com.ith.partygames.screens.main.presentation

import com.ith.partygames.common.architecture.BaseEvent

sealed interface MainEvent : BaseEvent {

    data class BluetoothPermissionGranted(val isGranted: Boolean): MainEvent
    data class WifiPermissionGranted(val isGranted: Boolean): MainEvent
}
