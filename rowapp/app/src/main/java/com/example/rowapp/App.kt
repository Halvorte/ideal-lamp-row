package com.example.rowapp

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this

    }

    companion object{
        lateinit var context: App private set
    }

    /*
    init {
        context = this
    }

     */




}