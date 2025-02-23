package com.ith.partygames.common.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object VirtualNodeDatastore {

    private const val DATASTORE_NAME = "party_games_datastore"

    val Context.virtualNodeSettingsDataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)
}
