package com.example.zavrsni.models


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tasks")
data class Task (val title : String,
                 val description : String,
                 val date: LocalDate,
                 val priority : Int,
                 var notification : Boolean){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}