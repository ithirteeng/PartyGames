package com.ith.partygames.common.data.server

import java.net.InetAddress

class UrlBuilder {
    private var protocol: String = "http"
    private var host: String = ""
    private var port: Int? = null
    private val pathSegments = mutableListOf<String>()
    private val queryParams = mutableMapOf<String, String>()

    fun protocol(protocol: String) = apply { this.protocol = protocol }

    fun host(host: InetAddress) = apply { this.host = host.hostAddress ?: "localhost" }

    fun port(port: Int) = apply { this.port = port }

    fun path(segment: String) = apply { pathSegments.add(segment) }

    fun pathParam(pathParam: String) = apply { pathSegments.add("{$pathParam}") }

    fun queryParam(key: String, value: String) = apply { queryParams[key] = value }

    fun build(): String {
        require(host.isNotEmpty()) { "Host must not be empty" }
        require(port != null) { "Port must not be empty" }

        val portPart = port?.let { ":$it" }

        val pathPart = pathSegments.joinToString("/", prefix = "/")

        val queryPart = if (queryParams.isNotEmpty()) {
            queryParams.entries.joinToString("&", prefix = "?") { "${it.key}=${it.value}" }
        } else ""

        return "$protocol://$host$portPart$pathPart$queryPart"
    }
}

fun url(init: UrlBuilder.() -> Unit): String {
    return UrlBuilder().apply(init).build()
}
