package com.example.geek_central.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.geek_central.model.Hosted
import com.example.geek_central.model.Popular

@Dao
interface HostedDao {

    @Transaction
    @Query("SELECT * FROM hosteres")
    fun getAll() : LiveData<List<Hosted>>

    @Insert
    fun insert(hosted: Hosted)
}