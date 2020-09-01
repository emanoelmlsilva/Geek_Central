package com.example.geek_central.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.geek_central.model.*

@Dao
interface WorkGeekDao {

    @Transaction
    @Query("SELECT * FROM work_geeks_mangas")
    fun getWorkGeeksMangas() : LiveData<List<WorkGeekMangaWithPopularAndHosted>>

    @Transaction
    @Query("SELECT * FROM work_geeks_animes")
    fun getWorkGeeksAnimes() : LiveData<List<WorkGeekAnimeWithPopularAndHosted>>
    
    @Query("SELECT id_work_geek_manga FROM work_geeks_mangas WHERE title = :title LIMIT 1")
    fun findIdMangaByTitle(title : String) : Long

    @Query("SELECT id_work_geek_anime FROM work_geeks_animes WHERE title = :title LIMIT 1")
    fun findIdAnimeByTitle(title: String) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertManga(workGeek: WorkGeekManga)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnime(workGeekAnime: WorkGeekAnime)

    @Update
    fun updateManga(workGeek: WorkGeekManga)

    @Update
    fun updateAnime(workGeek: WorkGeekAnime)

    @Query("DELETE FROM work_geeks_mangas WHERE id_work_geek_manga = :id")
    fun deleteManga(id : Long)

    @Query("DELETE FROM work_geeks_animes WHERE id_work_geek_anime = :id")
    fun deleteAnime(id : Long)
}
