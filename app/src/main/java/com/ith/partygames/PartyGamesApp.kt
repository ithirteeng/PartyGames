package com.ith.partygames

import android.app.Application
import com.ith.partygames.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PartyGamesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PartyGamesApp)
            androidLogger(Level.ERROR)
            modules(koinModules)
        }
    }
}
