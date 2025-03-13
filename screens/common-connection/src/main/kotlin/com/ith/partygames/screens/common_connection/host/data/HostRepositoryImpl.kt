package com.ith.partygames.screens.common_connection.host.data

import com.ith.partygames.common.server.NodeServer
import com.ith.partygames.screens.common_connection.host.domain.HostRepository

internal class HostRepositoryImpl(
    private val server: NodeServer,
): HostRepository {

    override fun startServer() {
        server.start()
    }
}
