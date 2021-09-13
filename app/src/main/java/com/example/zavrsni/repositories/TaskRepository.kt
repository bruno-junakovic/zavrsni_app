package com.example.zavrsni.repositories

import com.example.zavrsni.data.db.storage.TaskStorage
import com.example.zavrsni.data.models.persistance.Task

class TaskRepository(
    private val taskStorage: TaskStorage
) {

    fun getTasksLiveData() = taskStorage.getTasksLiveData()

    suspend fun insertTask(task: Task) {
        taskStorage.insertTask(task)
    }

    suspend fun getTasksAsynchronous(): MutableList<Task> {
        return taskStorage.getAllTasksAsynchronous()
    }

    suspend fun deleteTask(task: Task) {
        taskStorage.deleteTask(task)
    }

    suspend fun clearOldTasks(currentTime: Long) {
        taskStorage.deleteOldTasks(currentTime)
    }

}