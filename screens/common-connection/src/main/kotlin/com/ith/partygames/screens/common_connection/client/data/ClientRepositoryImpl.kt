package com.ith.partygames.screens.common_connection.client.data

import com.ith.partygames.common.data.utils.buildUrl
import com.ith.partygames.common.data.utils.makeGetRequest
import com.ith.partygames.common.server.NodeServer
import com.ith.partygames.screens.common_connection.client.domain.ClientRepository
import com.ith.partygames.screens.common_connection.common.data.CLIENT
import com.ith.partygames.screens.common_connection.common.data.FROM_ADDRESS_PARAM
import com.ith.partygames.screens.common_connection.common.data.READY_TO_PLAY
import com.ustadmobile.meshrabiya.ext.requireAddressAsInt
import okhttp3.OkHttpClient
import java.net.InetAddress

internal class ClientRepositoryImpl(
    private val okHttpClient: OkHttpClient,
) : ClientRepository {

    override suspend fun sendReadyToPlayEvent(
        toAddress: InetAddress,
        fromAddress: InetAddress,
    ): Result<String?> = okHttpClient.makeGetRequest(
        url = buildUrl {
            host(toAddress)
            port(NodeServer.HOST_PORT)
            path(CLIENT, READY_TO_PLAY)
            queryParam(FROM_ADDRESS_PARAM, fromAddress.requireAddressAsInt().toString())
        }
    )
}
