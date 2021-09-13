package com.example.zavrsni.data.models.persistance


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.zavrsni.common.enums.TaskPriority
import com.example.zavrsni.config.TASK_TABLE_NAME

@Entity(tableName = TASK_TABLE_NAME)
data class Task(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val title: String,
        val description: String,
        val date: Long,
        val priority: TaskPriority,
        val notification: Boolean
)