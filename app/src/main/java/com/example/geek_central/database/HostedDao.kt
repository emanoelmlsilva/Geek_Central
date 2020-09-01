package com.example.geek_central.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.geek_central.model.Hosted
import com.example.geek_central.model.Popular

@Dao
interface HostedDao {

    @Transaction
    @Query("SELECT * FROM hosteres")
    fun getAll() : LiveData<List<Hosted>>

    @Insert
    fun insert(hosted: Hosted)

    @Update
    fun update(hosted: Hosted)
}