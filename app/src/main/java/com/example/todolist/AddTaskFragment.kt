package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.todolist.databinding.FragmentAddTaskBinding


class AddTaskFragment : Fragment(R.layout.fragment_add_task) {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddTaskBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}