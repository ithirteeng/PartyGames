package com.ith.partygames.common.data.provider

import android.content.Context
import com.ith.partygames.common.data.storage.VirtualNodeDatastore.virtualNodeSettingsDataStore
import com.ustadmobile.meshrabiya.vnet.AndroidVirtualNode

object LocalNodeProvider {

    fun createLocalNode(
        context: Context,
    ): AndroidVirtualNode = AndroidVirtualNode(
        appContext = context,
        dataStore = context.virtualNodeSettingsDataStore,
        deviceName = "TEST_DEVICE_NAME"
    )
}
