package com.example.geek_central.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.geek_central.database.WorkGeekDatabase
import com.example.geek_central.database.WorkGeekRepository
import com.example.geek_central.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkGeekViewModel(application: Application) : AndroidViewModel(application) {

    private var workRespository: WorkGeekRepository

    init {

        val workGeekDao = WorkGeekDatabase.getDatabase(application).workGeekDao()
        val hostedDao = WorkGeekDatabase.getDatabase(application).hostedDao()
        val popularDao = WorkGeekDatabase.getDatabase(application).popularDao()

        workRespository = WorkGeekRepository(workGeekDao, popularDao, hostedDao)

    }

    fun findByTitleManta(title : String) : Boolean{
        return workRespository.findByTitleManta(title)
    }

    fun findByTitleAnime(title : String) : Boolean{
        return workRespository.findByTitleAnime(title)
    }

    fun getAllWorkGeeksMangas(): LiveData<List<WorkGeekMangaWithPopularAndHosted>> =
        workRespository.getAllWorkGeeksMangas()

    fun getAllWorkGeeksAnimes(): LiveData<List<WorkGeekAnimeWithPopularAndHosted>> =
        workRespository.getAllWorkGeeksAnimes()

    fun insert(popular: Popular, host: Host, workGeekManga: WorkGeekManga) {

        viewModelScope.launch(Dispatchers.IO) {

            workRespository.insert(popular, host, workGeekManga)

        }

    }

    fun insert(popular: Popular, host: Host, workGeekAnime: WorkGeekAnime) {

        viewModelScope.launch(Dispatchers.IO) {

            workRespository.insert(popular, host, workGeekAnime)

        }

    }

    fun update(workGeekManga: WorkGeekMangaWithPopularAndHosted){
        viewModelScope.launch(Dispatchers.IO) {
            workRespository.updateManga(workGeekManga)
        }
    }

    fun update(workGeekAnime: WorkGeekAnimeWithPopularAndHosted){
        viewModelScope.launch(Dispatchers.IO) {
            workRespository.updateAnime(workGeekAnime)
        }
    }

    fun delete(type: String, id: Long) {
        viewModelScope.launch { workRespository.delete(id, type) }
    }

}