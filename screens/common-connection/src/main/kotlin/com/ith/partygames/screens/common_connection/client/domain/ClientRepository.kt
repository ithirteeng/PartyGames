package com.ith.partygames.screens.common_connection.client.domain

import java.net.InetAddress

internal interface ClientRepository {

    suspend fun sendReadyToPlayEvent(
        toAddress: InetAddress,
        fromAddress: InetAddress,
    ): Result<String?>
}
