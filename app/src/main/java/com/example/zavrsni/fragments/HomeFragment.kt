package com.example.zavrsni.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.zavrsni.R
import com.example.zavrsni.db.TaskDatabase
import com.example.zavrsni.models.Day
import com.example.zavrsni.models.Task
import kotlinx.android.synthetic.main.day_template.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class HomeFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var date : LocalDate = LocalDate.now()

        buAddTask.setOnClickListener {
            val localDate : LocalDate = LocalDate.parse(etDate.text.trim(), DateTimeFormatter.ofPattern("d/MM/yyyy"))
            val title : String = etTitle.text.toString().trim()
            val description : String = etDescription.text.toString().trim()

            var priority : Int = when {
                rbRed.isChecked -> {
                    0
                }
                rbYellow.isChecked -> {
                    1
                }
                else -> {
                    2
                }
            }

            var notification : Boolean = sbNotification.isChecked

            if(title.isEmpty()){
                etTitle.error = "title required"
                etTitle.requestFocus()
                return@setOnClickListener
            }

            if(etDate.text.isEmpty()){
                etDate.error = "date required"
                etDate.requestFocus()
                return@setOnClickListener
            }

            launch {
                val task = Task(title,description,localDate,priority,notification)
                context?.let {
                    TaskDatabase(it).getTaskDAO().addTask(task)
                    Toast.makeText(it, "Task added", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}