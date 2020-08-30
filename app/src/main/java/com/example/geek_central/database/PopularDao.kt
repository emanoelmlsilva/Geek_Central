package com.example.geek_central.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.geek_central.model.Popular

@Dao
interface PopularDao {

    @Insert
    fun insert(popular : Popular)

//    @Query("SELECT * FROM populares WHERE id_popular = :id")
//    fun findById(id : Long)
}