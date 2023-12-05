package com.example.todolist.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.domain.OnItemClickListener
import com.example.todolist.R
import com.example.todolist.TaskRvAdapter
import com.example.todolist.domain.TimeUtility
import com.example.todolist.data.TaskDatabase
import com.example.todolist.data.TaskEntity
import com.example.todolist.databinding.FragmentListTaskBinding
import com.example.todolist.databinding.UpdateCustomDialogBinding

class ListTaskFragment : Fragment(R.layout.fragment_list_task) {
    private var _binding: FragmentListTaskBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListTaskBinding.bind(view)
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_listTaskFragment_to_addTaskFragment)
        }
        val database = TaskDatabase.getDatabaseInstance(requireContext())
        val list = database.takDao().getAllUsers().toMutableList()
        val adapter = TaskRvAdapter(list)
        binding.recyclerview.adapter = adapter

        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0,
            UCharacter.IndicPositionalCategory.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                database.takDao().deleteTask(list[viewHolder.adapterPosition])
                list.removeAt(viewHolder.adapterPosition)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                Toast.makeText(requireContext(), "Task deleted!", Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)
        adapter.setOnItemCLickedListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int) {
                val builder = AlertDialog.Builder(requireContext())
                val dialogBinding = UpdateCustomDialogBinding.inflate(layoutInflater)
                builder.setView(dialogBinding.root)
                dialogBinding.updateTaskTitle.setText(list[position].txtTaskTitle)
                dialogBinding.updateTask.setText(list[position].txtTask)
                val dialog = builder.create()
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialogBinding.btnOk.setOnClickListener {
                    database.takDao()
                        .updateTask(
                            TaskEntity(
                                position,
                                dialogBinding.updateTaskTitle.text.toString(),
                                dialogBinding.updateTask.text.toString(),
                                TimeUtility.timeStamp()
                            )
                        )
                    list[position] = TaskEntity(
                        position,
                        dialogBinding.updateTaskTitle.text.toString(),
                        dialogBinding.updateTask.text.toString(),
                        TimeUtility.timeStamp()
                    )
                    adapter.notifyItemChanged(position)
                    dialog.dismiss()
                }
                dialogBinding.btnCancel.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()

            }
        })

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}