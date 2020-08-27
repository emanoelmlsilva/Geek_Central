package com.example.geek_central.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.geek_central.model.WorkGeek

@Dao
interface WorkGeekDao {

    @Query("SELECT * FROM work_geek_table")
    fun getAll() : List<WorkGeek>

    @Query("SELECT * FROM work_geek_table WHERE title LIKE :title LIMIT 1")
    fun finByTitle(title : String): WorkGeek

    @Insert
    fun insertAll(vararg workGeeks: WorkGeek)

    @Delete
    fun delete(workGeek: WorkGeek)
}