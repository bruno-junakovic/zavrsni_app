package com.example.zavrsni.application

import android.app.Application

class zavrsni : Application() {
    companion object{
        lateinit var instance : Application
    }

    init {
        instance = this
    }
}