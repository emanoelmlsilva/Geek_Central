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

    fun getAllWorkGeeksMangas(): LiveData<List<WorkGeekMangaWithPopularAndHosted>> =
        workRespository.getAllWorkGeeksMangas()

    fun getAllWorkGeeksAnimes(): LiveData<List<WorkGeekAnimeWithPopularAndHosted>> =
        workRespository.getAllWorkGeeksAnimes()

    fun insert(popular: Popular, hosted: Hosted, workGeekManga: WorkGeekManga) : Boolean {

        var isInsert : Boolean = false

        viewModelScope.launch(Dispatchers.IO) {

           isInsert = workRespository.insert(popular, hosted, workGeekManga)

        }

        return isInsert;
    }

    fun insert(popular: Popular, hosted: Hosted, workGeekAnime: WorkGeekAnime) : Boolean{

        var isInsert : Boolean = false

        viewModelScope.launch(Dispatchers.IO) {

            isInsert = workRespository.insert(popular, hosted, workGeekAnime)

        }

        return isInsert;

    }

    fun delete(type: String, id: Long) {
        viewModelScope.launch { workRespository.delete(id, type) }
    }

}