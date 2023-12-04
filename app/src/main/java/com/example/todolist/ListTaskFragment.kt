package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.FragmentListTaskBinding

class ListTaskFragment : Fragment(R.layout.fragment_list_task) {
    private var _binding: FragmentListTaskBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListTaskBinding.bind(view)
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_listTaskFragment_to_addTaskFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}