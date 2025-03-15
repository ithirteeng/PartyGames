package com.ith.partygames.common.data.provider

import com.ustadmobile.meshrabiya.vnet.AndroidVirtualNode
import okhttp3.OkHttpClient
import java.time.Duration

object OkHttpProvider {

    fun createOkHttpClient(
        androidVirtualNode: AndroidVirtualNode,
    ): OkHttpClient = OkHttpClient.Builder()
        .socketFactory(androidVirtualNode.socketFactory)
        .connectTimeout(Duration.ofSeconds(30))
        .readTimeout(Duration.ofSeconds(30))
        .writeTimeout(Duration.ofSeconds(30))
        .build()
}
