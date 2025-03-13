package com.ith.partygames.common.games

import kotlinx.serialization.Serializable

@Serializable
enum class GameType(val value: Byte) {
    DECRYPTOR(1),
    CODE_NAMES(2),
    SPIN_THE_BOTTLE(3),
    UNKNOWN(4);

    companion object {
        fun fromByteValue(byte: Byte): GameType {
            return GameType.entries.first { it.value == byte }
        }
    }
}
