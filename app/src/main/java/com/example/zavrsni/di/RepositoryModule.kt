package com.example.zavrsni.di

import com.example.zavrsni.repositories.TaskRepository
import org.koin.dsl.module


val repositoryModule = module {
    single { TaskRepository(get()) }
}
