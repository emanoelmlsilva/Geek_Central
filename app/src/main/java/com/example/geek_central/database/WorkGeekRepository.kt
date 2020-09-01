package com.example.geek_central.database

import androidx.lifecycle.LiveData
import com.example.geek_central.enums.TypeWork
import com.example.geek_central.model.*

class WorkGeekRepository(private val workGeekDao: WorkGeekDao, private val popularDao: PopularDao, private val hostDao : HostDao) {

    fun getAllWorkGeeksMangas() : LiveData<List<WorkGeekMangaWithPopularAndHosted>> = workGeekDao.getWorkGeeksMangasOrderByTitle()

    fun getAllWorkGeeksMangasFavorites() : LiveData<List<WorkGeekMangaWithPopularAndHosted>> = workGeekDao.getWorkGeeksMangasOrdeByFavorite()

    fun getAllWorkGeeksMangasTotal() : LiveData<List<WorkGeekMangaWithPopularAndHosted>> = workGeekDao.getWorkGeeksMangasOrdeByTotal()

    fun getAllWorkGeeksMangasGrades() : LiveData<List<WorkGeekMangaWithPopularAndHosted>> = workGeekDao.getWorkGeeksMangasOrdeByGrade()

    fun getAllWorkGeeksAnimes() : LiveData<List<WorkGeekAnimeWithPopularAndHosted>> = workGeekDao.getWorkGeeksAnimes()

    fun getAllWorkGeeksAnimesFavorites() : LiveData<List<WorkGeekMangaWithPopularAndHosted>> = workGeekDao.getWorkGeeksAnimesOrdeByFavorite()

    fun getAllWorkGeeksAnimesTotal() : LiveData<List<WorkGeekMangaWithPopularAndHosted>> = workGeekDao.getWorkGeeksAnimesOrdeByTotal()

    fun getAllWorkGeeksAnimesGrades() : LiveData<List<WorkGeekMangaWithPopularAndHosted>> = workGeekDao.getWorkGeeksAnimesOrdeByGrade()

    fun delete(id: Long, type: String){

        when(type){
            TypeWork.MANGA.toString() -> workGeekDao.deleteManga(id)
            TypeWork.ANIME.toString() -> workGeekDao.deleteAnime(id)
        }

    }

    fun insert(popular: Popular, host: Host, workGeek: WorkGeekManga){

            workGeekDao.insertManga(workGeek)

            val idWorkGeek = workGeekDao.findIdMangaByTitle(workGeek.title)

            popular.workGeekOwnerId = idWorkGeek

            host.workGeekOwnerId = idWorkGeek

            popularDao.insert(popular)

            hostDao.insert(host)

    }

    fun insert(popular: Popular, host: Host, workGeekAnime: WorkGeekAnime){

        workGeekDao.insertAnime(workGeekAnime)

            val idWorkGeek  = workGeekDao.findIdAnimeByTitle(workGeekAnime.title)

            popular.workGeekOwnerId = idWorkGeek

            host.workGeekOwnerId = idWorkGeek

            popularDao.insert(popular)

            hostDao.insert(host)

    }

    fun findByTitleManta(title : String) : Boolean{
        return workGeekDao.findIdMangaByTitle(title) != 0L
    }

    fun findByTitleAnime(title : String) : Boolean{
        return workGeekDao.findIdAnimeByTitle(title) != 0L
    }

    fun updateManga(workGeek: WorkGeekMangaWithPopularAndHosted){
        hostDao.update(workGeek.host)
        popularDao.update(workGeek.popular)
        workGeekDao.updateManga(workGeek.workGeek)
    }

    fun updateAnime(workGeek: WorkGeekAnimeWithPopularAndHosted){
        hostDao.update(workGeek.host)
        popularDao.update(workGeek.popular)
        workGeekDao.updateAnime(workGeek.workGeek)
    }
}