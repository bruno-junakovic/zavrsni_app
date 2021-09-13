package com.example.zavrsni.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zavrsni.data.db.dao.TaskDAO
import com.example.zavrsni.data.models.persistance.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)


abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTaskDAO(): TaskDAO
}