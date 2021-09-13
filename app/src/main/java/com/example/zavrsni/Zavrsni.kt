package com.example.zavrsni

import android.app.Application
import com.example.zavrsni.di.databaseModule
import com.example.zavrsni.di.repositoryModule
import com.example.zavrsni.di.storageModule
import com.example.zavrsni.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Zavrsni : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@Zavrsni)
            modules(
                arrayListOf(
                    viewModelModule,
                    repositoryModule,
                    databaseModule,
                    storageModule
                )
            )
        }

    }
}