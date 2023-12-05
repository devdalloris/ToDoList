package com.example.todolist

import java.text.SimpleDateFormat
import java.util.Date

class TimeUtility {
    companion object{
        @Suppress("DEPRECATION")
        fun timeStamp():String{
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val date = Date()
            val current = formatter.format(date)
            return current
        }
    }
}