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
        const val HOST_PORT = 41242
        const val CLIENT_PORT = 14322
    }
}

