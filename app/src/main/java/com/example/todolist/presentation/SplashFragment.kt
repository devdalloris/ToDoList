package com.example.todolist.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashBinding.bind(view)
        Handler(Looper.getMainLooper()).postDelayed(
            { findNavController().navigate(R.id.action_splashFragment_to_listTaskFragment) },
            4000
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}