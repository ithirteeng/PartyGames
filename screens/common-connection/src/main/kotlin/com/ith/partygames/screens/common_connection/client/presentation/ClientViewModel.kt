package com.ith.partygames.screens.common_connection.client.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ith.partygames.common.architecture.ComplexViewModel
import com.ith.partygames.screens.common_connection.host.navigation.HostRoute
import com.ustadmobile.meshrabiya.vnet.AndroidVirtualNode
import kotlinx.coroutines.launch

internal class ClientViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val androidVirtualNode: AndroidVirtualNode,
) : ComplexViewModel<ClientState, ClientEvent, ClientEffect>() {

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

    override fun initState(): ClientState = ClientState()

    override fun processEvent(event: ClientEvent) {
        when (event) {
            is ClientEvent.Init -> init()
            is ClientEvent.ConnectToHotspot -> TODO()
            is ClientEvent.DisconnectFromHotspot -> TODO()
            is ClientEvent.SendReadyToPlayEvent -> TODO()
        }
    }

    private fun init() {
        val arguments = savedStateHandle.toRoute<HostRoute>()
        updateState { oldState -> oldState.copy(gameType = arguments.gameType) }
    }
}
