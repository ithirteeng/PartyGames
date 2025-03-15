package com.ith.partygames.common.server

import fi.iki.elonen.NanoHTTPD
import fi.iki.elonen.NanoHTTPD.IHTTPSession
import fi.iki.elonen.NanoHTTPD.Response
import fi.iki.elonen.NanoHTTPD.newFixedLengthResponse

abstract class GameServerRouter {

    private val handlers: MutableList<(session: IHTTPSession) -> Response?> = mutableListOf()

    fun serve(session: IHTTPSession): Response? {
        handleSession(session)
        return handlers.getResponses(session)
    }

    abstract fun handleSession(session: IHTTPSession)

    protected fun addSessionHandlers(vararg handlers: (session: IHTTPSession) -> Response?) {
        this.handlers.addAll(handlers)
    }
}

internal fun List<(session: IHTTPSession) -> Response?>.getResponses(session: IHTTPSession): Response? {
    for (handler in this) {
        val response = handler(session)
        if (response != null) return response
    }
    return null
}

internal fun List<GameServerRouter>.getResponse(session: IHTTPSession): Response {
    for (router in this) {
        val response = router.serve(session)
        if (response != null) return response
    }
    return newFixedLengthResponse(
        Response.Status.NOT_FOUND,
        NanoHTTPD.MIME_PLAINTEXT,
        "routers are not found"
    )
}
