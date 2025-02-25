package com.ith.partygames.screens.common_connection.host.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ith.partygames.common.architecture.ComplexViewModel
import com.ith.partygames.screens.common_connection.host.navigation.HostRoute
import com.ustadmobile.meshrabiya.vnet.AndroidVirtualNode
import com.ustadmobile.meshrabiya.vnet.wifi.ConnectBand
import com.ustadmobile.meshrabiya.vnet.wifi.HotspotType
import com.ustadmobile.meshrabiya.vnet.wifi.WifiDirectError
import kotlinx.coroutines.launch

internal class HostViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val androidVirtualNode: AndroidVirtualNode,
) : ComplexViewModel<HostState, HostEvent, HostEffect>() {

    init {
        viewModelScope.launch {
            androidVirtualNode.state.collect { nodeState ->
                updateState { oldState ->
                    oldState.copy(
                        localNodeState = oldState.localNodeState.copy(
                            localAddress = nodeState.address,
                            deviceName = nodeState.deviceName,
                            wifiState = nodeState.wifiState,
                            bluetoothState = nodeState.bluetoothState,
                            connectUri = nodeState.connectUri,
                        )
                    )
                }
            }
        }
    }

    override fun initState(): HostState = HostState()

    override fun processEvent(event: HostEvent) {
        when (event) {
            HostEvent.StartHotspotHostEvent -> processStartHotspot()
            HostEvent.StartGameHostEvent -> {}
            HostEvent.StopHotspotEvent -> processStopHotspot()
            HostEvent.Init -> init()
        }
    }

    private fun init() {
        val arguments = savedStateHandle.toRoute<HostRoute>()
        updateState { oldState -> oldState.copy(gameType = arguments.gameType) }
    }

    private fun processStartHotspot() = viewModelScope.launch {
        val response = androidVirtualNode.setWifiHotspotEnabled(
            enabled = true,
            preferredBand = ConnectBand.BAND_2GHZ,
            hotspotType = HotspotType.AUTO
        )
        if (response != null && response.errorCode != 0) {
            val error = WifiDirectError.errorString(response.errorCode)
            updateState { oldState ->
                oldState.copy(hotspotState = HotspotState.Init(error))
            }
            sendEffect(HostEffect.ShowErrorMessage(error))
        } else {
            updateState { oldState ->
                oldState.copy(hotspotState = HotspotState.HotspotActivated)
            }
        }
    }

    private fun processStopHotspot() {
        viewModelScope.launch {
            androidVirtualNode.setWifiHotspotEnabled(enabled = false)
            updateState { oldState ->
                oldState.copy(hotspotState = HotspotState.Init())
            }
        }
    }
}
