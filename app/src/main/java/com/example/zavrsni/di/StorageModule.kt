package com.example.zavrsni.di

import com.example.zavrsni.data.db.storage.TaskStorage
import org.koin.dsl.module

val storageModule = module {
    single { TaskStorage(get()) }
}