package com.ith.partygames

import android.app.Application
import com.google.zxing.client.android.BuildConfig
import com.ith.partygames.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class PartyGamesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PartyGamesApp)
            androidLogger(Level.ERROR)
            modules(koinModules)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
