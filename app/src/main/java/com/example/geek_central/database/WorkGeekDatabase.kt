package com.example.geek_central.database

import android.content.Context
import androidx.annotation.MainThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.geek_central.model.*


@Database(entities = [WorkGeekManga::class, Author::class, Popular::class, Hosted::class], version = 1, exportSchema = false)
abstract class WorkGeekDatabase : RoomDatabase(){

    abstract fun workGeekDao() : WorkGeekMangaDao
    abstract fun popularDao() : PopularDao
    abstract fun hostedDao() : HostedDao

    companion object {
        private var INSTANCE: WorkGeekDatabase? = null

        @MainThread
        fun getDatabase(context: Context): WorkGeekDatabase {

            val tempInstace = INSTANCE

            if(tempInstace != null) return tempInstace

            synchronized(this){

                val instance = Room.databaseBuilder(context, WorkGeekDatabase::class.java, "workgeek_database").build()
                INSTANCE = instance

                return instance
            }

        }
    }
}