package com.example.zavrsni

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zavrsni.application.zavrsni
import com.example.zavrsni.db.TaskDatabase
import com.example.zavrsni.models.Task
import kotlinx.android.synthetic.main.task_template_green.view.*
import kotlinx.android.synthetic.main.task_template_red.view.*
import kotlinx.android.synthetic.main.task_template_red.view.ivDeleteRed
import kotlinx.android.synthetic.main.task_template_yellow.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DayAdapter(tasks : MutableList<Task> ) : RecyclerView.Adapter<DayAdapter.TaskViewHolder>() {

    private var taskList = tasks
    private final val TYPE_GREEN = 2
    private final val TYPE_YELLOW = 1
    private final val TYPE_RED = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return when (viewType) {
            TYPE_GREEN -> {
                TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_template_green, parent, false))
            }
            TYPE_YELLOW -> {
                TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_template_yellow, parent, false))
            }
            else -> {
                TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_template_red, parent, false))
            }
        }
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun getItemViewType(position: Int): Int {
        var task : Task = taskList[position]
        return when (task.priority) {
            0 -> {
                TYPE_RED
            }
            1 -> {
                TYPE_YELLOW
            }
            else -> {
                TYPE_GREEN
            }
        }
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun onBind(task : Task){

            when (task.priority) {
                1 -> {
                    itemView.tvTaskTitleRed.text = task.title
                    itemView.ivDeleteRed.setOnClickListener {
                        GlobalScope.launch {
                            TaskDatabase(zavrsni.instance.applicationContext).getTaskDAO().deleteTask(task)
                        }
                    }
                }
                2 -> {
                    itemView.tvTaskTitleYellow.text = task.title
                    itemView.ivDeleteYellow.setOnClickListener {
                        GlobalScope.launch {
                            TaskDatabase(zavrsni.instance.applicationContext).getTaskDAO().deleteTask(task)
                        }
                    }
                }
                else -> {
                    itemView.tvTaskTitleGreen.text = task.title
                    itemView.ivDeleteGreen.setOnClickListener {
                        GlobalScope.launch {
                            TaskDatabase(zavrsni.instance.applicationContext).getTaskDAO().deleteTask(task)
                        }
                    }
                }
            }

        }
    }
}