package com.example.geek_central.utils

import com.example.geek_central.model.BaseWorkGeek

open class FilterSearch(var list: List<BaseWorkGeek>, val text: String) {

    fun searchText(): List<BaseWorkGeek> {

        val newList: ArrayList<BaseWorkGeek> = ArrayList()

        for (item: BaseWorkGeek in list) {

            if (item.title!!.toLowerCase().contains(text.toLowerCase())) {
                newList.add(item)
            }
        }

        return newList
    }

}