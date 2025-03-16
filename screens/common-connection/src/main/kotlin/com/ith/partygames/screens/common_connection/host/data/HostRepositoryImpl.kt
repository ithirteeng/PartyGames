package com.ith.partygames.screens.common_connection.host.data

import com.ith.partygames.common.mesh_utils.LocalNodeException
import com.ith.partygames.common.mesh_utils.LocalNodeState
import com.ith.partygames.screens.common_connection.host.data.model.ClientNodeState
import com.ith.partygames.screens.common_connection.host.domain.HostRepository
import com.ustadmobile.meshrabiya.vnet.AndroidVirtualNode
import com.ustadmobile.meshrabiya.vnet.wifi.ConnectBand
import com.ustadmobile.meshrabiya.vnet.wifi.HotspotType
import com.ustadmobile.meshrabiya.vnet.wifi.WifiDirectError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class HostRepositoryImpl(
    private val androidVirtualNode: AndroidVirtualNode,
) : HostRepository {

    private val _localNodeState = MutableStateFlow(LocalNodeState())
    override val localNodeState = _localNodeState.asStateFlow()

    private val _clientNodes = MutableStateFlow<List<ClientNodeState>>(emptyList())
    override val clientNodes = _clientNodes.asStateFlow()

    override suspend fun init() {
        collectAndroidVirtualNodeState()
    }

    override suspend fun enableHotspot(): Result<Unit> {
        val response = androidVirtualNode.setWifiHotspotEnabled(
            enabled = true,
            preferredBand = ConnectBand.BAND_5GHZ,
            hotspotType = HotspotType.AUTO
        )
        return if (response != null && response.errorCode != 0) {
            Result.failure(LocalNodeException(WifiDirectError.errorString(response.errorCode)))
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun disableHotspot(): Result<Unit> {
        val response = androidVirtualNode.setWifiHotspotEnabled(enabled = false)
        return if (response != null && response.errorCode != 0) {
            Result.failure(LocalNodeException(WifiDirectError.errorString(response.errorCode)))
        } else {
            Result.success(Unit)
        }
    }

    override fun setNodeIsReady(nodeAddress: Int) {
        _clientNodes.update { nodes ->
            nodes.map {
                if (it.nodeAddress == nodeAddress) {
                    it.copy(isReady = true)
                } else {
                    it
                }
            }
        }
    }

    private suspend fun collectAndroidVirtualNodeState() {
        androidVirtualNode.state.collect { nodeState ->
            _clientNodes.update { oldNodes ->
                val intersectIds = nodeState.originatorMessages.keys.intersect(oldNodes.map { it.nodeAddress })
                val newNodes = oldNodes.toMutableList()
                newNodes.removeIf { it.nodeAddress !in intersectIds }

                nodeState.originatorMessages.map { pair ->
                    newNodes.find { it.nodeAddress == pair.key } ?: ClientNodeState(
                        nodeAddress = pair.key,
                        info = pair.value
                    )
                }
                newNodes
            }
            _localNodeState.update { oldState ->
                oldState.copy(
                    localAddress = nodeState.address,
                    deviceName = nodeState.deviceName,
                    wifiState = nodeState.wifiState,
                    bluetoothState = nodeState.bluetoothState,
                    connectUri = nodeState.connectUri,
                    nodes = nodeState.originatorMessages
                )
            }
        }
    }
}
