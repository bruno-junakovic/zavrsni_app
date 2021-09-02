package com.example.zavrsni.db

import androidx.room.*
import com.example.zavrsni.models.Day
import com.example.zavrsni.models.Task
import java.time.LocalDate


@Dao
interface DayDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDay(day : Day)

    @Query("SELECT * FROM days ORDER BY date DESC")
    suspend fun getAllDays() : MutableList<Day>

    @Query("SELECT * FROM tasks WHERE tasks.date= :date")
    suspend fun getDayTasks(date: LocalDate) : MutableList<Task>

    @Query("DELETE FROM days WHERE date <:currentDate")
    suspend fun clearOldDays(currentDate: LocalDate)

    @Query("DELETE FROM days")
    suspend fun clearAllDays()

    @Delete
    suspend fun deleteDay(day: Day)

}