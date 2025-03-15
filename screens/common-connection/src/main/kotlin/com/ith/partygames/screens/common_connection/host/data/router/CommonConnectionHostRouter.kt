package com.ith.partygames.screens.common_connection.host.data.router

import com.ith.partygames.common.data.utils.urlPath
import com.ith.partygames.common.server.GameServerRouter
import com.ith.partygames.screens.common_connection.common.data.CLIENT
import com.ith.partygames.screens.common_connection.common.data.FROM_ADDRESS_PARAM
import com.ith.partygames.screens.common_connection.common.data.READY_TO_PLAY
import fi.iki.elonen.NanoHTTPD
import fi.iki.elonen.NanoHTTPD.IHTTPSession
import fi.iki.elonen.NanoHTTPD.MIME_PLAINTEXT
import fi.iki.elonen.NanoHTTPD.Response
import fi.iki.elonen.NanoHTTPD.newFixedLengthResponse

internal class CommonConnectionHostRouter : GameServerRouter() {

    override fun handleSession(session: IHTTPSession) {
        addSessionHandlers(
            ::handleIsReadyToPlaySession,
        )
    }

    private fun handleIsReadyToPlaySession(
        session: IHTTPSession
    ): Response? {
        val url = session.uri
        val method = session.method

        if (method == NanoHTTPD.Method.GET && url.startsWith(urlPath(CLIENT, READY_TO_PLAY))) {
            val params = session.parameters

            return if (params.containsKey(FROM_ADDRESS_PARAM)) {
                try {
                    val address = params[FROM_ADDRESS_PARAM]?.firstOrNull().toString().toInt()
//                    repositoryImpl.setNodeIsReady(address)
                    newFixedLengthResponse(
                        Response.Status.OK,
                        MIME_PLAINTEXT,
                        "Good"
                    )
                } catch (e: Exception) {
                    newFixedLengthResponse(
                        Response.Status.BAD_REQUEST,
                        MIME_PLAINTEXT,
                        "from address is incorrect: ${e.message}"
                    )
                }
            } else {
                newFixedLengthResponse(
                    Response.Status.NOT_FOUND,
                    MIME_PLAINTEXT,
                    "from address is empty"
                )
            }
        }
        return null
    }
}
