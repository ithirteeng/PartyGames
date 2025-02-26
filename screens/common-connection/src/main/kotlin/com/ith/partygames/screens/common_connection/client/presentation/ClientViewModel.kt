package com.ith.partygames.screens.common_connection.client.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ith.partygames.common.architecture.ComplexViewModel
import com.ith.partygames.screens.common_connection.host.navigation.HostRoute
import com.ustadmobile.meshrabiya.vnet.AndroidVirtualNode
import com.ustadmobile.meshrabiya.vnet.MeshrabiyaConnectLink
import kotlinx.coroutines.launch
import timber.log.Timber

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
        super.processEvent(event)
        when (event) {
            is ClientEvent.Init -> init()
            is ClientEvent.ConnectToHotspotWithLink -> connectToHotspotWithLink(event)
            is ClientEvent.DisconnectFromHotspot -> TODO()
            is ClientEvent.SendReadyToPlayEvent -> TODO()
        }
    }

    private fun init() {
        val arguments = savedStateHandle.toRoute<HostRoute>()
        updateState { oldState -> oldState.copy(gameType = arguments.gameType) }
    }

    private fun connectToHotspotWithLink(event: ClientEvent.ConnectToHotspotWithLink) =
        viewModelScope.launch {
            try {
                if (event.link != null) {
                    val connectLink = MeshrabiyaConnectLink.parseUri(uri = event.link)
                    val hotspotConfig = connectLink.hotspotConfig
                    if (hotspotConfig != null) {
                        androidVirtualNode.connectAsStation(hotspotConfig)
                        updateState { oldState -> oldState.copy(nodeState = NodeState.ConnectedToHotspot) }
                    } else {
                        showError("Hotspot config is null!")
                    }
                } else {
                    showError("Connect link is null!")
                }
            } catch (e: Exception) {
                Timber.e(e, "Caught exception while connecting")
                showError("got error: " + e.message.toString())
            }
        }

    private fun showError(message: String) {
        sendEffect(ClientEffect.ShowErrorMessage(message))
        Timber.e(message)
    }
}
