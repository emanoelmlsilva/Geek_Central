package com.example.geek_central.utils

import com.example.geek_central.model.WorkGeek

open class FilterSearch(var list: ArrayList<WorkGeek>, val text : String) {

    fun searchText() : ArrayList<WorkGeek>{

        val newList : ArrayList<WorkGeek> = ArrayList()

        for(item : WorkGeek in list){

            if(item.title!!.toLowerCase().contains(text.toLowerCase())) {
                newList.add(item)
            }
        }

        return newList
    }

}