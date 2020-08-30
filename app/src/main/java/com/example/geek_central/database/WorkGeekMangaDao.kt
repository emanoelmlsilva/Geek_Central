package com.example.geek_central.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.geek_central.model.*

@Dao
interface WorkGeekMangaDao {

    @Transaction
    @Query("SELECT * FROM work_geeks_mangas")
    fun getWorkGeeksMangas() : LiveData<List<WorkGeekMangaWithPopularAndHosted>>

    @Query("SELECT id_work_geek_manga FROM work_geeks_mangas WHERE title = :title LIMIT 1")
    fun findIdByTitle(title : String) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(workGeek: WorkGeekManga)

    @Query("DELETE FROM work_geeks_mangas WHERE id_work_geek_manga = :id")
    fun deleteManga(id : Long)
}