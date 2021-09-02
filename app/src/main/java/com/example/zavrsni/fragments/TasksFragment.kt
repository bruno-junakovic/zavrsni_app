package com.example.zavrsni.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.zavrsni.DayAdapter
import com.example.zavrsni.R
import com.example.zavrsni.application.zavrsni
import com.example.zavrsni.db.TaskDatabase
import com.example.zavrsni.models.Day
import com.example.zavrsni.models.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TasksFragment : BaseFragment() {

    lateinit var rvDays : RecyclerView
    lateinit var dayAdapter: DayAdapter
    //lateinit var daysAdapter : DaysListAdapter
    var daysList : MutableList<Day> = mutableListOf<Day>()
    var taskList : MutableList<Task> = mutableListOf<Task>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_tasks, container, false)

        getDays()
        getTasks()

        rvDays = view.findViewById(R.id.rvDays)
        dayAdapter = DayAdapter(taskList)
        rvDays.adapter = dayAdapter
        rvDays.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        //getTasks()

    }

    private fun getTasks() {
        launch {
            context?.let {
                taskList = TaskDatabase(it).getTaskDAO().getAllTasks()
            }
        }
    }

    private fun deleteTask(task : Task){
        GlobalScope.launch {
            TaskDatabase(zavrsni.instance.applicationContext).getTaskDAO().deleteTask(task)
        }
    }

    private fun getDays(){
        launch {
            context?.let {
                daysList = TaskDatabase(it).getDayDAO().getAllDays()
            }
        }
    }

    private fun setUpDays(){
        launch {
            context?.let {
                daysList.forEach {
                    it.taskList = TaskDatabase(zavrsni.instance.applicationContext).getDayDAO().getDayTasks(it.date)
                }
            }
        }
    }


}