package com.ustadmobile.meshrabiya.vnet.wifi.state

import com.ustadmobile.meshrabiya.vnet.wifi.HotspotStatus
import com.ustadmobile.meshrabiya.vnet.wifi.WifiConnectConfig

data class WifiDirectState(
    val hotspotStatus: HotspotStatus = HotspotStatus.STOPPED,
    val error: Int = 0,
    val config: WifiConnectConfig? = null,
) {
}
