package com.example.zavrsni.db

import androidx.room.*
import com.example.zavrsni.models.Task
import java.time.LocalDate

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task:Task)

    @Query("SELECT * FROM tasks ORDER BY date DESC")
    suspend fun getAllTasks() : MutableList<Task>

    @Query("DELETE FROM tasks WHERE date <:currentDate")
    suspend fun clearOldTasks(currentDate: LocalDate)

    @Delete
    suspend fun deleteTask(task:Task)
}