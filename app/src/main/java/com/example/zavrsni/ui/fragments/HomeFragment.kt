package com.example.zavrsni.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.os.bundleOf
import com.example.zavrsni.R
import com.example.zavrsni.common.base.BaseFragment
import com.example.zavrsni.common.enums.TaskPriority
import com.example.zavrsni.data.models.persistance.Task
import com.example.zavrsni.ui.dialogs.DATE_PICKER_DATE
import com.example.zavrsni.ui.dialogs.DATE_PICKER_ID
import com.example.zavrsni.ui.dialogs.DATE_PICKER_TITLE
import com.example.zavrsni.ui.dialogs.DatePickerFragment
import com.example.zavrsni.util.DateHelper.getLocalityFormattedDate
import com.example.zavrsni.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import java.util.*


private const val DIALOG_TASK_DATE = 1

private const val CURRENT_DATE = "CurrentDate"

class HomeFragment : BaseFragment(), DatePickerDialog.OnDateSetListener {

    private val taskViewModel: TaskViewModel by inject()

    private var currentDate = GregorianCalendar(Locale.getDefault()).timeInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            currentDate = savedInstanceState.getLong(CURRENT_DATE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etDate.setText(getLocalityFormattedDate(currentDate, requireContext()))

        btnAddTask.setOnClickListener {
            if (validateTaskInput()) {
                val priority = checkTaskPriority()
                val notification = sbNotification.isChecked
                val task = Task(
                    title = etTitle.text.toString().trim(),
                    description = etDescription.text.toString().trim(),
                    date = currentDate,
                    priority = priority,
                    notification = notification
                )
                taskViewModel.insertTask(task)
            }
        }

        etDate.setOnClickListener {
            showDatePicker(getString(R.string.pick_task_date), DIALOG_TASK_DATE)
        }

    }

    private fun showDatePicker(title: String, dialogId: Int) {

        val datePickerFragment = DatePickerFragment()

        val date = Date()

        currentDate.let {
            date.time = it
        }

        val arguments = bundleOf(
            DATE_PICKER_ID to dialogId,
            DATE_PICKER_TITLE to title,
            DATE_PICKER_DATE to date
        )

        datePickerFragment.arguments = arguments
        datePickerFragment.show(childFragmentManager, "datePicker")
    }

    private fun validateTaskInput(): Boolean {

        var isValid = true

        etTitle.apply {
            if (text.isEmpty()) {
                error = getString(R.string.title_required)
                requestFocus()
                isValid = false
            }
        }

        etDate.apply {
            if (text.isEmpty()) {
                error = getString(R.string.date_required)
                requestFocus()
                isValid = false
            }
        }

        etDescription.apply {
            if (text.isEmpty()) {
                error = getString(R.string.description_required)
                requestFocus()
                isValid = false
            }
        }

        return isValid

    }


    private fun checkTaskPriority(): TaskPriority {
        return when {
            rbRed.isChecked -> {
                TaskPriority.HIGH
            }
            rbYellow.isChecked -> {
                TaskPriority.MEDIUM
            }
            else -> {
                TaskPriority.LOW
            }
        }
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        when (view?.tag as Int) {
            DIALOG_TASK_DATE -> {
                val calendar = GregorianCalendar()
                calendar.set(year, month, dayOfMonth, 0, 0, 0)
                currentDate = calendar.timeInMillis
                currentDate.let { etDate.setText(getLocalityFormattedDate(it, requireContext())) }
            }

            else -> throw IllegalArgumentException("Invalid mode when receiving DatePickerDialog result")
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentDate.let { outState.putLong(CURRENT_DATE, it) }
    }
}