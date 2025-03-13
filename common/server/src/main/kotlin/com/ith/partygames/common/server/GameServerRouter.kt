package com.ith.partygames.common.server

import fi.iki.elonen.NanoHTTPD.IHTTPSession
import fi.iki.elonen.NanoHTTPD.Response

interface GameServerRouter {

    fun serve(session: IHTTPSession): Response?
}

fun List<GameServerRouter>.getResponse(session: IHTTPSession): Response {
    lateinit var response: Response
    for (router in this) {
        router.serve(session)?.also {
            response = it
            return response
        }
    }
    return response
}
