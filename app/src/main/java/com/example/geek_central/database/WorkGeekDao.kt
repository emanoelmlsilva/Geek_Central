package com.example.geek_central.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.geek_central.model.WorkGeekAnime
import com.example.geek_central.model.WorkGeekAnimeWithPopularAndHosted
import com.example.geek_central.model.WorkGeekManga
import com.example.geek_central.model.WorkGeekMangaWithPopularAndHosted

@Dao
interface WorkGeekDao {

    //lista mangas
    @Transaction
    @Query("SELECT * FROM work_geeks_mangas ORDER BY title ASC")
    fun getWorkGeeksMangasOrderByTitle(): LiveData<List<WorkGeekMangaWithPopularAndHosted>>

    @Transaction
    @Query("SELECT DISTINCT work_geeks_mangas.* FROM work_geeks_mangas, populares, hosts WHERE (id_work_geek_manga = workgeek_popular_id AND id_work_geek_manga = workgeek_hosted_id) ORDER BY favorite DESC")
    fun getWorkGeeksMangasOrdeByFavorite(): LiveData<List<WorkGeekMangaWithPopularAndHosted>>

    @Transaction
    @Query("SELECT  DISTINCT work_geeks_mangas.* FROM work_geeks_mangas, populares, hosts WHERE (id_work_geek_manga = workgeek_popular_id AND id_work_geek_manga = workgeek_hosted_id) ORDER BY total_geek DESC")
    fun getWorkGeeksMangasOrdeByTotal(): LiveData<List<WorkGeekMangaWithPopularAndHosted>>

    @Transaction
    @Query("SELECT  DISTINCT work_geeks_mangas.* FROM work_geeks_mangas, populares, hosts WHERE (id_work_geek_manga = workgeek_popular_id AND id_work_geek_manga = workgeek_hosted_id) ORDER BY grade DESC")
    fun getWorkGeeksMangasOrdeByGrade(): LiveData<List<WorkGeekMangaWithPopularAndHosted>>

    //lista animes
    @Transaction
    @Query("SELECT * FROM work_geeks_animes ORDER BY title ASC")
    fun getWorkGeeksAnimes(): LiveData<List<WorkGeekAnimeWithPopularAndHosted>>

    @Transaction
    @Query("SELECT DISTINCT work_geeks_animes.* FROM work_geeks_animes, populares, hosts WHERE (id_work_geek_anime = workgeek_popular_id AND id_work_geek_anime = workgeek_hosted_id) ORDER BY favorite DESC")
    fun getWorkGeeksAnimesOrdeByFavorite(): LiveData<List<WorkGeekAnimeWithPopularAndHosted>>

    @Transaction
    @Query("SELECT DISTINCT work_geeks_animes.* FROM work_geeks_animes, populares, hosts WHERE (id_work_geek_anime = workgeek_popular_id AND id_work_geek_anime = workgeek_hosted_id) ORDER BY total_geek DESC")
    fun getWorkGeeksAnimesOrdeByTotal(): LiveData<List<WorkGeekAnimeWithPopularAndHosted>>

    @Transaction
    @Query("SELECT DISTINCT work_geeks_animes.* FROM work_geeks_animes, populares, hosts WHERE (id_work_geek_anime = workgeek_popular_id AND id_work_geek_anime = workgeek_hosted_id) ORDER BY grade DESC")
    fun getWorkGeeksAnimesOrdeByGrade(): LiveData<List<WorkGeekAnimeWithPopularAndHosted>>

    @Query("SELECT id_work_geek_manga FROM work_geeks_mangas WHERE title = :title LIMIT 1")
    fun findIdMangaByTitle(title: String): Long

    @Query("SELECT id_work_geek_anime FROM work_geeks_animes WHERE title = :title LIMIT 1")
    fun findIdAnimeByTitle(title: String): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertManga(workGeek: WorkGeekManga)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnime(workGeekAnime: WorkGeekAnime)

    @Update
    fun updateManga(workGeek: WorkGeekManga)

    @Update
    fun updateAnime(workGeek: WorkGeekAnime)

    @Query("DELETE FROM work_geeks_mangas WHERE id_work_geek_manga = :id")
    fun deleteManga(id: Long)

    @Query("DELETE FROM work_geeks_animes WHERE id_work_geek_anime = :id")
    fun deleteAnime(id: Long)
}
