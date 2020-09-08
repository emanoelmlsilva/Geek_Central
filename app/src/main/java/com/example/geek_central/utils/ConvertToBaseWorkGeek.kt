package com.example.geek_central.utils

import androidx.annotation.MainThread
import com.example.geek_central.model.BaseWorkGeek
import com.example.geek_central.model.WorkGeekAnimeWithPopularAndHosted
import com.example.geek_central.model.WorkGeekMangaWithPopularAndHosted

class ConvertToBaseWorkGeek {

    companion object{
        private lateinit var instance: ConvertToBaseWorkGeek

        @MainThread
        fun get() : ConvertToBaseWorkGeek {
            instance = if(::instance.isInitialized) instance else ConvertToBaseWorkGeek()

            return instance
        }
    }

    fun workGeekMnagaFromBaseWorkGeek(list: List<WorkGeekMangaWithPopularAndHosted>): MutableList<BaseWorkGeek>{

        val listBaseWorkGeek: MutableList<BaseWorkGeek> = ArrayList()

        for( workgeek in list){

            val baseWorkGeek = BaseWorkGeek()
            baseWorkGeek.workGeekManga = workgeek.workGeek
            baseWorkGeek.workGeekId = workgeek.workGeek.workGeekId
            baseWorkGeek.title = workgeek.workGeek.title
            baseWorkGeek.currentGeek = workgeek.workGeek.currentGeek
            baseWorkGeek.totalGeek = workgeek.workGeek.totalGeek
            baseWorkGeek.season = workgeek.workGeek.season
            baseWorkGeek.popular = workgeek.popular
            baseWorkGeek.host = workgeek.host

            listBaseWorkGeek.add(baseWorkGeek)
        }
        return listBaseWorkGeek
    }

    fun workGeekAnimeFromBaseWorkGeek(list: List<WorkGeekAnimeWithPopularAndHosted>): MutableList<BaseWorkGeek>{

        val listBaseWorkGeek: MutableList<BaseWorkGeek> = ArrayList()

        for( workgeek in list){

            val baseWorkGeek = BaseWorkGeek()
            baseWorkGeek.workGeekAnimne = workgeek.workGeek
            baseWorkGeek.workGeekId = workgeek.workGeek.workGeekId
            baseWorkGeek.title = workgeek.workGeek.title
            baseWorkGeek.currentGeek = workgeek.workGeek.currentGeek
            baseWorkGeek.totalGeek = workgeek.workGeek.totalGeek
            baseWorkGeek.season = workgeek.workGeek.season
            baseWorkGeek.popular = workgeek.popular
            baseWorkGeek.host = workgeek.host

            listBaseWorkGeek.add(baseWorkGeek)
        }
        return listBaseWorkGeek
    }

}