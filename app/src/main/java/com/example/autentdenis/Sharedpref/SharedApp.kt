package com.example.autentdenis.Sharedpref

import android.app.Application

class SharedApp  : Application() {
    companion object {
        lateinit var prefs: Prefes
    }
    override fun onCreate() {
        super.onCreate()
        prefs = Prefes(applicationContext)
    }
}