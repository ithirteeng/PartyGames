package com.ith.partygames.common.data.utils

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

    fun path(vararg segments: String) = apply { pathSegments.addAll(segments) }

    fun pathParam(pathParam: String) = apply { pathSegments.add("{$pathParam}") }

    fun queryParam(key: String, value: String?) = apply { queryParams[key] = value.toString() }

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

fun buildUrl(init: UrlBuilder.() -> Unit): String {
    return UrlBuilder().apply(init).build()
}

fun urlPath(vararg segments: String): String {
    return segments.toList().joinToString("/", prefix = "/")
}
