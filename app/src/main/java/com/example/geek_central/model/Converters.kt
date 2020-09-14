package com.example.geek_central.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun fromString(value: String?): List<String?>? {
        val listType = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson<List<String?>>(value, listType)
    }

    @TypeConverter
    fun fromArrayLisr(list: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}