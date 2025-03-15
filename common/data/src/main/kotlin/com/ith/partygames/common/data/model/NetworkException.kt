package com.ith.partygames.common.data.model

class NetworkException(
    val errorCode: Int,
    val errorMessage: String,
) : Exception() {
    override val message: String = "CODE: $errorCode, MESSAGE: $errorMessage"
}
