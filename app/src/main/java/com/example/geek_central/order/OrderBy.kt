package com.example.geek_central.order

import androidx.annotation.MainThread
import com.example.geek_central.enums.TypeOrderBy
import com.example.geek_central.model.WorkGeek

class OrderBy {

    companion object{
        private lateinit var instance: OrderBy

        @MainThread
        fun get() : OrderBy{
            instance = if(::instance.isInitialized) instance else OrderBy()

            return instance
        }
    }

    fun ordeBy(typeOrder : String, list : List<WorkGeek>): List<WorkGeek> {

        if(list.isNullOrEmpty()) return list

        val comparatorOrderBy = when(typeOrder){
            TypeOrderBy.FAVORITE.toString() -> compareByDescending { it.popular?.favorite }
            TypeOrderBy.TITLE.toString() -> compareBy { it.title }
            TypeOrderBy.TOTAL.toString() -> compareByDescending { it.totalGeek }
            TypeOrderBy.GRADE.toString() -> compareByDescending { it.popular?.note }
            else -> compareByDescending<WorkGeek> { it.popular?.favorite }
        }

        return list.sortedWith(comparatorOrderBy)
    }

}