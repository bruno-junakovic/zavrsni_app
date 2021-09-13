package com.example.zavrsni.util

import android.content.Context
import android.text.format.DateFormat
import java.util.*


object DateHelper {

    fun getStartTimeOfDate(date: Long): Long {
        val calendar = GregorianCalendar()

        calendar.timeInMillis = date
        calendar.set(GregorianCalendar.HOUR_OF_DAY, 0)
        calendar.set(GregorianCalendar.MINUTE, 0)
        calendar.set(GregorianCalendar.SECOND, 0)
        calendar.set(GregorianCalendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }


    fun getEndTimeOfDate(date: Long): Long {
        val calendar = GregorianCalendar()

        calendar.timeInMillis = date
        calendar.set(GregorianCalendar.HOUR_OF_DAY, 23)
        calendar.set(GregorianCalendar.MINUTE, 59)
        calendar.set(GregorianCalendar.SECOND, 59)
        calendar.set(
            GregorianCalendar.MILLISECOND,
            59
        )
        return calendar.timeInMillis
    }

    fun getLocalityFormattedDate(dateInMillis: Long, context: Context): String {
        val dateFormat = DateFormat.getDateFormat(context)
        return dateFormat.format(dateInMillis)
    }
}