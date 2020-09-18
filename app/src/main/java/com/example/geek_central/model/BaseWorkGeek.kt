package com.example.geek_central.model

import android.os.Parcel
import android.os.Parcelable

class BaseWorkGeek() : Parcelable{
    var workGeekId: Long? = null
    lateinit var title: String
    var author: String? = null
    var currentGeek: Double = 0.0
    var season: String? = null
    var totalGeek: Double = 0.0
    var categories: List<String> = ArrayList()
    lateinit var popular: Popular
    lateinit var host: Host
    var workGeekManga: WorkGeekManga? = null
    var workGeekAnimne: WorkGeekAnime? = null

    constructor(parcel: Parcel) : this() {
        workGeekId = parcel.readValue(Long::class.java.classLoader) as? Long
        title = parcel.readString().toString()
        author = parcel.readString().toString()
        currentGeek = parcel.readDouble()
        season = parcel.readString()
        totalGeek = parcel.readDouble()
        parcel.readStringList(categories)
    }

    fun copyFromWorkGeekManga(){
        workGeekManga?.workGeekId = workGeekId
        workGeekManga?.title = title
        workGeekManga?.author = author
        workGeekManga?.currentGeek = currentGeek
        workGeekManga?.totalGeek = totalGeek
        workGeekManga?.categories = categories
    }

    fun copyFromWorkGeekAnime(){
        workGeekAnimne?.workGeekId = workGeekId
        workGeekAnimne?.title = title
        workGeekAnimne?.author = author
        workGeekAnimne?.season = season
        workGeekAnimne?.currentGeek = currentGeek
        workGeekAnimne?.totalGeek = totalGeek
        workGeekAnimne?.categories = categories
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(workGeekId)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeDouble(currentGeek)
        parcel.writeString(season)
        parcel.writeDouble(totalGeek)
        parcel.writeStringList(categories)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "BaseWorkGeek(workGeekId=$workGeekId, title='$title', author=$author, currentGeek=$currentGeek, season=$season, totalGeek=$totalGeek, categories=$categories, popular=$popular, host=$host, workGeekManga=$workGeekManga, workGeekAnimne=$workGeekAnimne)"
    }

    companion object CREATOR : Parcelable.Creator<BaseWorkGeek> {
        override fun createFromParcel(parcel: Parcel): BaseWorkGeek {
            return BaseWorkGeek(parcel)
        }

        override fun newArray(size: Int): Array<BaseWorkGeek?> {
            return arrayOfNulls(size)
        }
    }


}
