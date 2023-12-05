package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("task_table")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var txtTaskTitle: String,
    var txtTask: String,
    var date: String
        )