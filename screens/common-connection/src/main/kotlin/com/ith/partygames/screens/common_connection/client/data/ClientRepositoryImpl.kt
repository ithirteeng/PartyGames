package com.ith.partygames.screens.common_connection.client.data

import com.ith.partygames.common.server.NodeServer
import com.ith.partygames.screens.common_connection.client.domain.ClientRepository

internal class ClientRepositoryImpl(
    private val server: NodeServer,
): ClientRepository {

    override fun startServer() {
        server.start()
    }
}
