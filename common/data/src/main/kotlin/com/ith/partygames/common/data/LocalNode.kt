package com.ith.partygames.common.data

import android.content.Context
import com.ith.partygames.common.data.storage.VirtualNodeDatastore.virtualNodeSettingsDataStore
import com.ustadmobile.meshrabiya.vnet.AndroidVirtualNode

object LocalNode {

    fun createLocalNode(
        context: Context,
    ): AndroidVirtualNode = AndroidVirtualNode(
        appContext = context,
        dataStore = context.virtualNodeSettingsDataStore,
        deviceName = "JOPA"
    )
}
