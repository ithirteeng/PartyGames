package com.ith.partygames.common.server

import fi.iki.elonen.NanoHTTPD
import timber.log.Timber

class NodeServer(
    port: Int,
    private val routers: List<GameServerRouter>,
): NanoHTTPD(port) {

    override fun start() {
        super.start()
        Timber.d("start: ${this.listeningPort}")
    }

    override fun serve(session: IHTTPSession): Response {
        return routers.getResponse(session)
    }

    companion object {
        const val TYPE_HOST = "TYPE_HOST"
        const val TYPE_CLIENT = "TYPE_CLIENT"
        const val HOST_PORT = 4232
        const val CLIENT_PORT = 4322
    }
}

