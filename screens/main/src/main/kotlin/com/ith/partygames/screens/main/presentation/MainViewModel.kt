package com.ith.partygames.screens.main.presentation

import androidx.lifecycle.SavedStateHandle
import com.ith.partygames.common.architecture.BaseViewModel

class MainViewModel(
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<MainState, MainEvent>() {

    override fun initState(): MainState = MainState()

    override fun processEvent(event: MainEvent) {
        when (event) {
            is MainEvent.BluetoothPermissionGranted -> processBluetoothPermissionGranted(event)
            is MainEvent.WifiPermissionGranted -> processWifiPermissionGranted(event)
        }
    }

    private fun processBluetoothPermissionGranted(event: MainEvent.BluetoothPermissionGranted) {
        updateState { oldState ->
            oldState.copy(isBluetoothPermissionGranted = event.isGranted)
        }
    }

    private fun processWifiPermissionGranted(event: MainEvent.WifiPermissionGranted) {
        updateState { oldState ->
            oldState.copy(isWifiPermissionGranted = event.isGranted)
        }
    }
}
