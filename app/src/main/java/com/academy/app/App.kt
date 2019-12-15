package com.academy.app

import android.app.Application
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs

class App : Application() {

    companion object {
        const val PREFS_NAME = "SHARED_PREFS"
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(PREFS_NAME)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}