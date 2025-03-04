package com.ith.partygames.common.games

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable()
enum class GameType(val value: Byte) {
    DECRYPTOR(1),
    CODE_NAMES(2),
    SPIN_THE_BOTTLE(3),
    UNKNOWN(4);

    companion object {
        fun fromByteValue(byte: Byte): GameType {
            return GameType.entries.first { it.value == byte }
        }
//        fun GameType.toNavigationGameType(): NavigationGameType {
//            return when (this) {
//                DECRYPTOR -> NavigationGameType.DECRYPTOR
//                CODE_NAMES -> NavigationGameType.DECRYPTOR
//                SPIN_THE_BOTTLE -> NavigationGameType.DECRYPTOR
//                UNKNOWN -> NavigationGameType.DECRYPTOR
//            }
//        }
    }
}

//@Serializable
//enum class NavigationGameType() {
//    DECRYPTOR,
//    CODE_NAMES,
//    SPIN_THE_BOTTLE,
//    UNKNOWN;
//
//    companion object {
//        fun NavigationGameType.toGameType(): GameType {
//            return when (this) {
//                DECRYPTOR -> GameType.DECRYPTOR
//                CODE_NAMES -> GameType.DECRYPTOR
//                SPIN_THE_BOTTLE -> GameType.DECRYPTOR
//                UNKNOWN -> GameType.DECRYPTOR
//            }
//        }
//    }
//}

object GameTypeSerializer : KSerializer<GameType> {
    override fun deserialize(decoder: Decoder): GameType {
        return GameType.fromByteValue(decoder.decodeByte())
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("GameType", PrimitiveKind.BYTE)

    override fun serialize(encoder: Encoder, value: GameType) {
        encoder.encodeByte(value.value)
    }
}
