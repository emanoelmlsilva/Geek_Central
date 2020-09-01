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

    fun insert(popular: Popular, hosted: Hosted, workGeek: WorkGeekManga) : Boolean{

        if(workGeekDao.findIdMangaByTitle(workGeek.title) != 0L){
            return false;
        }else {

            workGeekDao.insertManga(workGeek)

            val idWorkGeek = workGeekDao.findIdMangaByTitle(workGeek.title)

            popular.workGeekOwnerId = idWorkGeek

            hosted.workGeekOwnerId = idWorkGeek

            popularDao.insert(popular)

            hostedDao.insert(hosted)

            return true;
        }

    }

    fun insert(popular: Popular, hosted: Hosted, workGeekAnime: WorkGeekAnime) : Boolean{

        if(workGeekDao.findIdAnimeByTitle(workGeekAnime.title) != 0L){
            return false;
        }else {
            workGeekDao.insertAnime(workGeekAnime)

            val idWorkGeek  = workGeekDao.findIdAnimeByTitle(workGeekAnime.title)

            popular.workGeekOwnerId = idWorkGeek

            hosted.workGeekOwnerId = idWorkGeek

            popularDao.insert(popular)

            hostedDao.insert(hosted)

            return true;
        }


    }
}