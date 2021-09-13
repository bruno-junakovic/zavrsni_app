package com.example.zavrsni.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zavrsni.data.models.persistance.Task
import com.example.zavrsni.repositories.TaskRepository
import com.example.zavrsni.util.DateHelper
import kotlinx.coroutines.launch
import java.util.*

class TaskViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    val tasks: LiveData<List<Task>> = taskRepository.getTasksLiveData()

    fun insertTask(task: Task) {
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }

    fun deleteOldTasks(currentTime: Long) {
        viewModelScope.launch {
            taskRepository.clearOldTasks(currentTime)
        }
    }

    suspend fun getTasksSumForCurrentDay(): Int {
        val currentTime = GregorianCalendar(Locale.getDefault()).timeInMillis
        val startOfDay = DateHelper.getStartTimeOfDate(currentTime)
        val endOfDay = DateHelper.getEndTimeOfDate(currentTime)

        return taskRepository.getTasksAsynchronous()
            .filter { task ->
                task.date in startOfDay..endOfDay
                        && task.notification
            }.size

    }

}