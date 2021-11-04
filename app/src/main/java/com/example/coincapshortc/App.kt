package com.example.coincapshortc

import android.app.Application
//import com.jakewharton.threetenabp.AndroidThreeTen
import com.example.coincapshortc.di.ServiceLocator
import timber.log.Timber
import java.util.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        ServiceLocator.init(applicationContext)

        //AndroidThreeTen.init(applicationContext)
        Locale.setDefault(Locale("da", "DK"))
    }
}