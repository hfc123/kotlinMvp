package com.example.danie.test

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class App : Application() {

    companion object {
        private val TAG="App"
        var context:Context by Delegates.notNull()
         private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}