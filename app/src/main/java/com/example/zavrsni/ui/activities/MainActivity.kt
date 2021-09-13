package com.example.zavrsni.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.zavrsni.R
import com.example.zavrsni.ui.fragments.HomeFragment
import com.example.zavrsni.ui.fragments.TasksFragment
import com.example.zavrsni.util.DateHelper
import com.example.zavrsni.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private val taskViewModel: TaskViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val tasksFragment = TasksFragment()

        if (savedInstanceState == null) {
            setCurrentFragment(homeFragment)
            showDailyTasksNotification()
            clearOldTasks()
        }

        bottom_navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    setCurrentFragment(homeFragment)
                    true
                }
                R.id.nav_tasks -> {
                    setCurrentFragment(tasksFragment)
                    true
                }
                else -> false
            }
        }
        bottom_navigation.setOnItemReselectedListener { }

    }

    private fun showDailyTasksNotification() {
        CoroutineScope(Main).launch {
            val sum = taskViewModel.getTasksSumForCurrentDay()

            Toast.makeText(
                this@MainActivity,
                getString(
                    R.string.important_tasks_message,
                    sum.toString()
                ), Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun clearOldTasks() {
        val calendar = GregorianCalendar()
        taskViewModel.deleteOldTasks(DateHelper.getStartTimeOfDate(calendar.timeInMillis))
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

}
