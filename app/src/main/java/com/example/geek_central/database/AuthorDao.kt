package com.example.geek_central.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.geek_central.model.Author

@Dao
interface AuthorDao {

    @Transaction
    @Query("SELECT * FROM authores")
    fun getAll(): LiveData<List<Author>>

    @Insert
    fun insert(list : List<Author>)
}