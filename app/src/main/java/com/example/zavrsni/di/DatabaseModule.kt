package com.example.zavrsni.di

import androidx.room.Room
import com.example.zavrsni.config.DATABASE_NAME
import com.example.zavrsni.data.db.TaskDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), TaskDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .addMigrations()
            .build()
    }

    single { get<TaskDatabase>().getTaskDAO() }
}