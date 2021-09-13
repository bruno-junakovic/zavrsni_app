package com.example.zavrsni.data.db.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Converters {

    @TypeConverter
    fun fromLocalDate(localDate: LocalDate): String {

        if (localDate == null) {
            return ""
        }
        return localDate.toString()
    }

    @TypeConverter
    fun toLocalDate(string: String): LocalDate {

        return LocalDate.parse(string.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}