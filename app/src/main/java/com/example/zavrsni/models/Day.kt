package com.example.zavrsni.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "days")
data class Day (var date : LocalDate,
                @Ignore
                var taskList: MutableList<Task>?){
    constructor(date: LocalDate) : this(date,null)
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}