package com.example.zavrsni.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zavrsni.R
import com.example.zavrsni.common.enums.TaskPriority
import com.example.zavrsni.common.extensions.getMyDrawable
import com.example.zavrsni.data.models.persistance.Task
import com.example.zavrsni.util.DateHelper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_task.view.*


class TaskAdapter(
    private var tasks: List<Task>,
    private val listener: OnTaskClickListener
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    interface OnTaskClickListener {
        fun onDeleteClick(task: Task)
        fun onInfoClick(task: Task)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(tasks[position], listener)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun loadNewTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }


    class TaskViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun onBind(task: Task, listener: OnTaskClickListener) {

            when (task.priority) {
                TaskPriority.LOW -> {
                    itemView.background = itemView.context.getMyDrawable(R.drawable.bg_green)

                }
                TaskPriority.MEDIUM -> {

                    itemView.background = itemView.context.getMyDrawable(R.drawable.bg_yellow)

                }

                TaskPriority.HIGH -> {
                    itemView.background = itemView.context.getMyDrawable(R.drawable.bg_red)
                }
            }

            itemView.tvTitle.text = task.title
            itemView.tvDescription.text = task.description
            itemView.tvDate.text = DateHelper.getLocalityFormattedDate(task.date, itemView.context)

            itemView.ivDelete.setOnClickListener {
                listener.onDeleteClick(task)
            }

            itemView.ivInfo.setOnClickListener {
                listener.onInfoClick(task)
            }

        }
    }
}