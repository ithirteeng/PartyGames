package com.ith.partygames.screens.common_connection.host.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ith.partygames.common.architecture.ComplexViewModel
import com.ith.partygames.screens.common_connection.host.domain.HostRepository
import com.ith.partygames.screens.common_connection.host.navigation.HostRoute
import com.ustadmobile.meshrabiya.vnet.AndroidVirtualNode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class HostViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val androidVirtualNode: AndroidVirtualNode,
    private val repository: HostRepository,
) : ComplexViewModel<HostState, HostEvent, HostEffect>() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.localNodeState.collect { nodeState ->
                updateState { oldState -> oldState.copy(localNodeState = nodeState) }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.clientNodes.collect { clientNodes ->
                updateState { oldState -> oldState.copy(clientNodes = clientNodes) }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.init()
        }
    }

    override fun initState(): HostState = HostState()

    override fun processEvent(event: HostEvent) {
        super.processEvent(event)
        when (event) {
            is HostEvent.Init -> init()
            is HostEvent.StartGameHostEvent -> { /*todo: implement*/ }
            is HostEvent.StartHotspotHostEvent -> startHotspot()
            is HostEvent.StopHotspotEvent -> stopHotspot()
        }
    }

    private fun init() {
        repository.startServer()

        val arguments = savedStateHandle.toRoute<HostRoute>()
        updateState { oldState -> oldState.copy(gameType = arguments.gameType) }
        androidVirtualNode.setGameType(arguments.gameType)
    }

    private fun startHotspot() = viewModelScope.launch {
        repository.enableHotspot()
            .onSuccess {
                updateState { oldState ->
                    oldState.copy(hotspotState = HotspotState.HotspotActivated)
                }
            }
            .onFailure { showError(it.message) }
    }

    private fun stopHotspot() {
        viewModelScope.launch {
            repository.disableHotspot()
                .onSuccess {
                    updateState { oldState ->
                        oldState.copy(hotspotState = HotspotState.Init())
                    }
                }
                .onFailure { showError(it.message) }
        }
    }

    private fun showError(message: String?) {
        updateState { oldState ->
            oldState.copy(hotspotState = HotspotState.Init(message))
        }
        sendEffect(HostEffect.ShowErrorMessage(message))
    }
}
