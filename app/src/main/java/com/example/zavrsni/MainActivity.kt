package com.example.zavrsni

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Query
import com.example.zavrsni.application.zavrsni
import com.example.zavrsni.db.TaskDatabase
import com.example.zavrsni.fragments.HomeFragment
import com.example.zavrsni.fragments.TasksFragment
import com.example.zavrsni.models.Day
import com.example.zavrsni.models.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val tasksFragment = TasksFragment()

        setCurrentFragment(homeFragment)

        clearAllDays()
        setDays()

        bottom_navigation.setOnItemReselectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    setCurrentFragment(homeFragment)
                    Log.i(TAG, "Home selected")
                }
                R.id.nav_tasks -> {
                    setCurrentFragment(tasksFragment)
                    Log.i(TAG, "Tasks selected")
                }
            }
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_wrapper, fragment)
                commit()
            }

    private fun setDays(){
        var currDate : LocalDate = LocalDate.now()
        var target : LocalDate = currDate.plusDays(14L)

        GlobalScope.launch() {
            var date : LocalDate = currDate
            while (date!=target){
                TaskDatabase(zavrsni.instance.applicationContext).getDayDAO().addDay(Day(date))
                date = date.plusDays(1L)
            }
        }
        clearOldDays(currDate)
        clearOldTasks(currDate)

    }

    private fun clearOldDays(currentDate: LocalDate){
        GlobalScope.launch {
            TaskDatabase(zavrsni.instance.applicationContext).getDayDAO().clearOldDays(currentDate)
        }
    }

    private fun clearOldTasks(currentDate: LocalDate){
        GlobalScope.launch {
            TaskDatabase(zavrsni.instance.applicationContext).getTaskDAO().clearOldTasks(currentDate)
        }
    }

    private fun clearAllDays(){
        GlobalScope.launch {
            TaskDatabase(zavrsni.instance.applicationContext).getDayDAO().clearAllDays()
        }
    }



}
