package com.example.zavrsni.data.db.storage

import com.example.zavrsni.data.db.dao.TaskDAO
import com.example.zavrsni.data.models.persistance.Task

class TaskStorage(
    private val taskDAO: TaskDAO
) {

    fun getTasksLiveData() = taskDAO.getAllTasksLiveData()

    suspend fun getAllTasksAsynchronous(): MutableList<Task> {
        return taskDAO.getAllTasksAsynchronous()
    }

    suspend fun insertTask(task: Task) {
        taskDAO.insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDAO.deleteTask(task)
    }

    suspend fun deleteOldTasks(currentTime: Long) {
        taskDAO.clearOldTasks(currentTime)
    }

}