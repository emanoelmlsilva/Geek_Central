package com.example.geek_central.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.geek_central.model.*

@Dao
interface WorkGeekDao {

    @Transaction
    @Query("SELECT * FROM work_geeks_mangas ORDER BY title ASC")
    fun getWorkGeeksMangasOrderByTitle() : LiveData<List<WorkGeekMangaWithPopularAndHosted>>

    @Transaction
    @Query("SELECT * FROM work_geeks_mangas INNER JOIN populares ON populares.workgeek_popular_id = work_geeks_mangas.id_work_geek_manga INNER JOIN hosts ON hosts.workgeek_hosted_id = work_geeks_mangas.id_work_geek_manga ORDER BY favorite DESC")
    fun getWorkGeeksMangasOrdeByFavorite() : LiveData<List<WorkGeekMangaWithPopularAndHosted>>

    @Transaction
    @Query("SELECT * FROM work_geeks_mangas INNER JOIN populares ON populares.workgeek_popular_id = work_geeks_mangas.id_work_geek_manga INNER JOIN hosts ON hosts.workgeek_hosted_id = work_geeks_mangas.id_work_geek_manga ORDER BY total_geek ASC")
    fun getWorkGeeksMangasOrdeByTotal() : LiveData<List<WorkGeekMangaWithPopularAndHosted>>

    @Transaction
    @Query("SELECT * FROM work_geeks_mangas INNER JOIN populares ON populares.workgeek_popular_id = work_geeks_mangas.id_work_geek_manga INNER JOIN hosts ON hosts.workgeek_hosted_id = work_geeks_mangas.id_work_geek_manga ORDER BY grade DESC")
    fun getWorkGeeksMangasOrdeByGrade() : LiveData<List<WorkGeekMangaWithPopularAndHosted>>

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
