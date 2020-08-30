package com.example.geek_central.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.geek_central.database.WorkGeekDatabase
import com.example.geek_central.database.WorkGeekMangaRepository
import com.example.geek_central.enums.TypeWork
import com.example.geek_central.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkGeekViewModel(application: Application) : AndroidViewModel(application) {

    private var workRespositoryManga : WorkGeekMangaRepository

    init{

        val workGeekDao = WorkGeekDatabase.getDatabase(application).workGeekDao()
        val hostedDao = WorkGeekDatabase.getDatabase(application).hostedDao()
        val popularDao = WorkGeekDatabase.getDatabase(application).popularDao()

        workRespositoryManga = WorkGeekMangaRepository(workGeekDao, popularDao, hostedDao)

    }

    fun getAllWorkGeeks(type : String) : LiveData<List<WorkGeekMangaWithPopularAndHosted>>{
        return when(type){
            TypeWork.MANGA.toString() -> workRespositoryManga.getAllWorkGeeks()
            else -> workRespositoryManga.getAllWorkGeeks()
        }
    }

    fun insert(popular: Popular, hosted: Hosted, workGeekManga: WorkGeekManga){

        viewModelScope.launch(Dispatchers.IO){
            if(workGeekManga != null){
                workRespositoryManga.insert(popular, hosted, workGeekManga)
            }
        }

    }

    fun delete(type: String, id : Long){
        viewModelScope.launch {
            when(type){
                TypeWork.MANGA.toString() -> workRespositoryManga.delete(id)
            }
             }
    }

}