package com.example.rowapp

import android.app.Application
import android.content.Context

class App:Application() {

    companion object{
        lateinit var context: App private set
    }

    override fun onCreate(){
        super.onCreate()
        context = this
    }
}