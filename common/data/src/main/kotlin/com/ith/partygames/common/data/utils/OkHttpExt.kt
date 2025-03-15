package com.ith.partygames.common.data.utils

import com.ith.partygames.common.data.model.NetworkException
import okhttp3.OkHttpClient
import okhttp3.Request

fun OkHttpClient.makeGetRequest(
    url: String,
): Result<String?> {
    val request = Request.Builder()
        .url(url)
        .build()

    val response = this.newCall(request).execute()
    return if (response.isSuccessful) {
        Result.success(response.body?.string())
    } else {
        Result.failure(
            NetworkException(
                errorCode = response.code,
                errorMessage = response.body?.string() ?: response.message
            )
        )
    }
}
