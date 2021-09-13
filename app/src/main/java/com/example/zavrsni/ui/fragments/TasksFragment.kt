package com.example.zavrsni.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zavrsni.R
import com.example.zavrsni.common.base.BaseFragment
import com.example.zavrsni.data.models.persistance.Task
import com.example.zavrsni.ui.adapters.TaskAdapter
import com.example.zavrsni.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.fragment_tasks.*
import org.koin.android.ext.android.inject

class TasksFragment : BaseFragment(), TaskAdapter.OnTaskClickListener {

    private val tasksAdapter = TaskAdapter(emptyList(), this)
    private val taskViewModel: TaskViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        taskViewModel.tasks.observe(this, { tasks ->
            tasksAdapter.loadNewTasks(tasks)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvTasks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvTasks.adapter = tasksAdapter
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDeleteClick(task: Task) {
        taskViewModel.deleteTask(task)
    }

    override fun onInfoClick(task: Task) {
        showDescriptionDialog(requireContext(), task.title, task.description)
    }


    private fun showDescriptionDialog(
        context: Context,
        title: String,
        message: String,
        buttonText: Int = R.string.ok
    ) {

        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(buttonText) { _, _ -> }
            .setCancelable(true)
            .show()

    }

}