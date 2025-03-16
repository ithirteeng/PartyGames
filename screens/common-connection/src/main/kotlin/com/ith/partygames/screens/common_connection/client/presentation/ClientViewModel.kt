package com.ith.partygames.screens.common_connection.client.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ith.partygames.common.architecture.ComplexViewModel
import com.ith.partygames.screens.common_connection.client.domain.ClientRepository
import com.ith.partygames.screens.common_connection.host.navigation.HostRoute
import com.ustadmobile.meshrabiya.ext.asInetAddress
import com.ustadmobile.meshrabiya.vnet.AndroidVirtualNode
import com.ustadmobile.meshrabiya.vnet.MeshrabiyaConnectLink
import com.ustadmobile.meshrabiya.vnet.wifi.WifiConnectConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

internal class ClientViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val androidVirtualNode: AndroidVirtualNode,
    private val repository: ClientRepository,
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
            is ClientEvent.DisconnectFromHotspot -> {/*TODO()*/ }

            is ClientEvent.SendReadyToPlayEvent -> viewModelScope.launch(Dispatchers.IO) {
                val wifiStationState =
                    state.value.localNodeState.wifiState?.wifiStationState ?: return@launch
                val toAddress = wifiStationState.config?.nodeVirtualAddr ?: return@launch
                val fromAddress = androidVirtualNode.address

                repository.sendReadyToPlayEvent(
                    toAddress = toAddress.asInetAddress(),
                    fromAddress = fromAddress
                ).onSuccess { message ->
                    sendEffect(ClientEffect.ShowErrorMessage(message))
                }.onFailure { error ->
                    sendEffect(ClientEffect.ShowErrorMessage(error.message))
                }

            }
        }
    }

    private fun init() {
        val arguments = savedStateHandle.toRoute<HostRoute>()
        updateState { oldState -> oldState.copy(gameType = arguments.gameType) }
        androidVirtualNode.setGameType(arguments.gameType)
    }

    private fun connectToHotspotWithLink(event: ClientEvent.ConnectToHotspotWithLink) =
        viewModelScope.launch {
            try {
                if (event.link != null) {
                    val connectLink = MeshrabiyaConnectLink.parseUri(uri = event.link)
                    val hotspotConfig = connectLink.hotspotConfig
                    if (hotspotConfig != null) {
                        Timber.d("config: $hotspotConfig")
                        tryConnectToHost(hotspotConfig)
                    } else {
                        showError("Hotspot config is null!")
                        return@launch
                    }
                } else {
                    showError("Connect link is null!")
                    return@launch
                }
            } catch (e: Exception) {
                Timber.e(e, "Caught exception while connecting")
                showError("got error: " + e.message.toString())
            }
        }

    private suspend fun tryConnectToHost(wifiConnectConfig: WifiConnectConfig) {
        if (wifiConnectConfig.gameType != state.value.gameType) {
            showError("Wrong Game!")
        } else {
            androidVirtualNode.connectAsStation(wifiConnectConfig)
            updateState { oldState -> oldState.copy(nodeState = NodeState.ConnectedToHotspot) }
        }
    }

    private fun disconnectFromHotspot() = viewModelScope.launch(Dispatchers.Main) {
        androidVirtualNode.disconnectWifiStation()
    }

    private fun showError(message: String) {
        sendEffect(ClientEffect.ShowErrorMessage(message))
        Timber.e(message)
    }
}
