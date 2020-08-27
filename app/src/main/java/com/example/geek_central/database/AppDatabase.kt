package com.example.geek_central.database

import android.content.Context
import androidx.annotation.MainThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.geek_central.model.WorkGeek


@Database(entities = arrayOf(WorkGeek::class), version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun workGeekDao() : WorkGeekDao

    companion object{
        private lateinit var instance: AppDatabase

        @MainThread
        fun get(context: Context? = null): AppDatabase {
            instance = if(::instance.isInitialized) instance else Room.databaseBuilder<AppDatabase>(
                context!!.applicationContext,
                AppDatabase::class.java, "word_database"
            )
                .build()
            return instance
        }
    }
}