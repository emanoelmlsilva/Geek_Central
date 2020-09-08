package com.example.geek_central.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.geek_central.model.Popular

@Dao
interface PopularDao {

    @Insert
    fun insert(popular : Popular)

    @Update
    fun update(popular: Popular)
}