package com.example.zavrsni.models

import java.time.LocalDate

data class DatabaseTask(val title : String,
                        val description : String,
                        val date: String,
                        val priority : Int,
                        var notification : Boolean) {
}