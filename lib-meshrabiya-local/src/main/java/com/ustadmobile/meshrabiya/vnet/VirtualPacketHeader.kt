package com.ustadmobile.meshrabiya.vnet

import android.util.Log
import com.ustadmobile.meshrabiya.ext.getStringOrThrow
import com.ustadmobile.meshrabiya.ext.putStringFromBytes
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Virtual packet structure
 * Header:
 * @param toAddr - the Virtual Node address this packet wants to reach. 0 = send over local link only.
 * @param port the Virtual Port on the destination Vitual Noe that this packet wants to reach
 * @param fromAddr the Virtual Node that originally sent this packet
 * @param fromPort the virtual port that this packet was sent from
 * @param lastHopAddr the virtual node address of the most recent hop. E.g. where the packet is
 *        sent from node A to node B, then node B to node C, the lastHop would be A when the packet
 *        is sent from A to B, and then B when the packet is sent from B to C.
 * @param hopCount the total number of hops this packet has taken. Starts at 1 when first sent and
 *        is incremented on each hop.
 * @param maxHops the maximum number of hops that this packet should live for. If exceeded, packet is
 *        dropped
 * @param payloadSize the size of the payload data
 *
 * Packet size/structure:
 * device name (20 bytes) + string length 4 bytes
 * To address (32bit int) 4 bytes
 * to port (16bit short) 2 bytes
 * from address (32bit int) 4 bytes
 * from port (16bit short) 2 bytes
 * lastHopAddr (32 bit int) 4 bytes
 * hopCount (8bit byte) 1 byte
 * maxHops (8bit byte) 1 byte
 * payloadSize (16bit short) 2 bytes
 * payload (byte array where size = payloadSize)
 */

data class VirtualPacketHeader(
    val deviceName: String,
    val toAddr: Int,
    val toPort: Int,
    val fromAddr: Int,
    val fromPort: Int,
    val lastHopAddr: Int,
    val hopCount: Byte,
    val maxHops: Byte,
    val payloadSize: Int, //Max size should be in line with MTU e.g. 1500. Stored as short
) {

    init {
        if(payloadSize > MAX_PAYLOAD)
            throw IllegalArgumentException("Payload size must not be > $MAX_PAYLOAD")
    }

    private val deviceNameBytes: ByteArray? by lazy {
        var fullNameString = ""
        //20 is max length of device name
        for (i in 0 until 20 - deviceName.length) fullNameString += " "
        fullNameString = deviceName + fullNameString
        val bytes = fullNameString.encodeToByteArray()
        bytes
    }

    fun toBytes(
        byteArray: ByteArray,
        offset: Int
    ) {
        val buf = ByteBuffer.wrap(byteArray, offset, HEADER_SIZE).order(ByteOrder.BIG_ENDIAN)
        buf.putStringFromBytes(deviceNameBytes)
        buf.putInt(toAddr)
        buf.putShort(toPort.toShort())
        buf.putInt(fromAddr)
        buf.putShort(fromPort.toShort())
        buf.putInt(lastHopAddr)
        buf.put(hopCount)
        buf.put(maxHops)
        buf.putShort(payloadSize.toShort())
    }

    fun toBytes(): ByteArray {
        return ByteArray(HEADER_SIZE).also {
            toBytes(it, 0)
        }
    }

    companion object {

        @Suppress("LocalVariableName", "UsePropertyAccessSyntax")
        fun fromBytes(
            bytes: ByteArray,
            offset: Int = 0,
        ): VirtualPacketHeader {
            val buf = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN)
            buf.position(offset)
            val _deviceName = buf.getStringOrThrow()
            val _toAddr = buf.getInt()
            val _toPort = buf.getShort()
            val _fromAddr = buf.getInt()
            val _fromPort = buf.getShort()
            val _lastHopAddr = buf.getInt()
            val _hopCount = buf.get()
            val _maxHops = buf.get()
            val _payloadSize = buf.getShort()

            return VirtualPacketHeader(
                toAddr = _toAddr,
                toPort = _toPort.toInt(),
                fromAddr = _fromAddr,
                fromPort = _fromPort.toInt(),
                lastHopAddr = _lastHopAddr,
                hopCount = _hopCount,
                maxHops = _maxHops,
                payloadSize = _payloadSize.toInt(),
                deviceName = _deviceName
            )
        }

        //Size of all header fields in bytes (as above)
        const val HEADER_SIZE = 44

        const val MAX_PAYLOAD = 2000


    }
}