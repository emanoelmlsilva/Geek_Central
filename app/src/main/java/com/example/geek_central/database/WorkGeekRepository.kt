package com.example.geek_central.database

import androidx.lifecycle.LiveData
import com.example.geek_central.enums.TypeWork
import com.example.geek_central.model.*

class WorkGeekRepository(private val workGeekDao: WorkGeekDao, private val popularDao: PopularDao, private val hostedDao : HostedDao) {

    fun getAllWorkGeeksMangas() : LiveData<List<WorkGeekMangaWithPopularAndHosted>> = workGeekDao.getWorkGeeksMangas()

    fun getAllWorkGeeksAnimes() : LiveData<List<WorkGeekAnimeWithPopularAndHosted>> = workGeekDao.getWorkGeeksAnimes()

    fun delete(id: Long, type: String){

        when(type){
            TypeWork.MANGA.toString() -> workGeekDao.deleteManga(id)
            TypeWork.ANIME.toString() -> workGeekDao.deleteAnime(id)
        }

    }

    fun insert(popular: Popular, hosted: Hosted, workGeek: WorkGeekManga){

        workGeekDao.insertManga(workGeek)

        val idWorkGeek  = workGeekDao.findIdMangaByTitle(workGeek.title)

        popular.workGeekOwnerId = idWorkGeek

        hosted.workGeekOwnerId = idWorkGeek

        popularDao.insert(popular)

        hostedDao.insert(hosted)

    }

    fun insert(popular: Popular, hosted: Hosted, workGeekAnime: WorkGeekAnime){

        workGeekDao.insertAnime(workGeekAnime)

        val idWorkGeek  = workGeekDao.findIdAnimeByTitle(workGeekAnime.title)

        popular.workGeekOwnerId = idWorkGeek

        hosted.workGeekOwnerId = idWorkGeek

        popularDao.insert(popular)

        hostedDao.insert(hosted)

    }
}