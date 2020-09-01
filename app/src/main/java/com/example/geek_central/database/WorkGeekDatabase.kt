package com.example.geek_central.database

import android.content.Context
import androidx.annotation.MainThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.geek_central.model.*


@Database(entities = [WorkGeekManga::class,WorkGeekAnime::class, Author::class, Popular::class, Host::class], version = 1, exportSchema = false)
abstract class WorkGeekDatabase : RoomDatabase(){

    abstract fun workGeekDao() : WorkGeekDao
    abstract fun popularDao() : PopularDao
    abstract fun hostedDao() : HostDao

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