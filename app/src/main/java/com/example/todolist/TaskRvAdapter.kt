package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TaskItemBinding


class TaskRvAdapter(private val list: List<TaskEntity>) :
    RecyclerView.Adapter<TaskRvAdapter.ViewHolder>() {

    private  var listener: OnItemClickListener?=null

    inner class ViewHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: TaskEntity) {
            binding.txtTaskTitle.text=note.txtNoteTitle
            binding.txtTask.text = note.txtNote
            binding.txtDate.text=note.date

            binding.root.setOnClickListener {
                listener?.onItemClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun setOnItemCLickedListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}