package com.example.todolist.domain


fun interface OnItemClickListener {
    fun onItemClicked(position: Int)
}