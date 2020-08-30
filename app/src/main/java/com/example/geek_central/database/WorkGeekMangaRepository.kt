package com.example.geek_central.database

import androidx.lifecycle.LiveData
import com.example.geek_central.model.*

class WorkGeekMangaRepository(private val workGeekMangaDao: WorkGeekMangaDao, private val popularDao: PopularDao, private val hostedDao : HostedDao) {

    fun getAllWorkGeeks() : LiveData<List<WorkGeekMangaWithPopularAndHosted>> = workGeekMangaDao.getWorkGeeksMangas()

    fun delete(id: Long){
        workGeekMangaDao.deleteManga(id)
    }

    fun insert(popular: Popular, hosted: Hosted, workGeek: WorkGeekManga){

        workGeekMangaDao.insert(workGeek)

        val idWorkGeek  = workGeekMangaDao.findIdByTitle(workGeek.title)

        popular.workGeekOwnerId = idWorkGeek

        popularDao.insert(popular)

        hostedDao.insert(hosted)

    }
}