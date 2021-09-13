package com.example.zavrsni.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.zavrsni.data.models.persistance.Task


@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM tasks ORDER BY date ASC")
    suspend fun getAllTasksAsynchronous(): MutableList<Task>

    @Query("SELECT * FROM tasks ORDER BY date ASC")
    fun getAllTasksLiveData(): LiveData<List<Task>>

    @Query("DELETE FROM tasks WHERE date <:currentTime")
    suspend fun clearOldTasks(currentTime: Long)

    @Delete
    suspend fun deleteTask(task: Task)
}