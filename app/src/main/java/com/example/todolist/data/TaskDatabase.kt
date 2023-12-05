package com.example.todolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun takDao(): TaskDao

    companion object {
        private var instance: TaskDatabase? = null

        fun getDatabaseInstance(context: Context): TaskDatabase {
            return instance ?: Room.databaseBuilder(
                context,
                TaskDatabase::class.java,
                "tak_database"
            ).allowMainThreadQueries().build()
        }
    }
}