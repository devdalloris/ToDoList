package com.example.todolist.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.domain.TimeUtility
import com.example.todolist.data.TaskDatabase
import com.example.todolist.data.TaskEntity
import com.example.todolist.databinding.FragmentAddTaskBinding
class AddTaskFragment : Fragment(R.layout.fragment_add_task) {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddTaskBinding.bind(view)
        binding.btnUndo.setOnClickListener {
            onNumberClicked(binding.btnUndo)
        }
        binding.btnRedo.setOnClickListener {
            onNumberClicked(binding.btnRedo)
        }

        val database = TaskDatabase.getDatabaseInstance(requireContext())
        binding.btnSave.setOnClickListener {
            if (binding.etTask.text.isNotEmpty()) {
                database.takDao().addTask(
                    TaskEntity(
                        0,
                        binding.etTaskTitle.text.toString(),
                        binding.etTask.text.toString(),
                        TimeUtility.timeStamp()
                    )
                )
                binding.etTaskTitle.text.clear()
                binding.etTask.text.clear()
                Toast.makeText(requireContext(), "Task is saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Task is empty", Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnBack.setOnClickListener {
           findNavController().navigate(R.id.action_addTaskFragment_to_listTaskFragment)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun onNumberClicked(view: View) {
        var valueBuilder = StringBuilder(binding.etTask.text.toString())
        var textBuilder = StringBuilder(binding.etTask.text.toString())
        when (view.id) {
            R.id.btn_undo -> {
                if (valueBuilder.isNotEmpty()) {
                    valueBuilder.deleteCharAt(valueBuilder.length - 1)

                }
            }

            R.id.btn_redo -> {
                valueBuilder.append("")
            }
        }
        binding.etTask.setText(valueBuilder.toString(), TextView.BufferType.EDITABLE)
    }
}