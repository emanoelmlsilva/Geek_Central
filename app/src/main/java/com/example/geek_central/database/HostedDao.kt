package com.example.geek_central.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.geek_central.model.Host

@Dao
interface HostedDao {

    @Transaction
    @Query("SELECT * FROM hosts")
    fun getAll() : LiveData<List<Host>>

    @Insert
    fun insert(host: Host)

    @Update
    fun update(host: Host)
}