package com.example.geek_central.model

import android.os.Parcel
import android.os.Parcelable

class BaseWorkGeek() : Parcelable{
    var workGeekId: Long? = null
    lateinit var title: String
    var currentGeek: Int = 0
    var season: String? = null
    var totalGeek: Int = 0
    lateinit var popular: Popular
    lateinit var host: Host
    var workGeekManga: WorkGeekManga? = null
    var workGeekAnimne: WorkGeekAnime? = null

    constructor(parcel: Parcel) : this() {
        workGeekId = parcel.readValue(Long::class.java.classLoader) as? Long
        title = parcel.readString().toString()
        currentGeek = parcel.readInt()
        season = parcel.readString()
        totalGeek = parcel.readInt()
    }

    override fun toString(): String {
        return "BaseWorkGeek(workGeekId=$workGeekId, title='$title', currentGeek=$currentGeek, season=$season, totalGeek=$totalGeek, popular=$popular, host=$host, workGeekManga=$workGeekManga, workGeekAnimne=$workGeekAnimne)"
    }

    fun copyFromWorkGeekManga(){
        workGeekManga?.workGeekId = workGeekId
        workGeekManga?.title = title
        workGeekManga?.currentGeek = currentGeek
        workGeekManga?.totalGeek = totalGeek
    }

    fun copyFromWorkGeekAnime(){
        workGeekAnimne?.workGeekId = workGeekId
        workGeekAnimne?.title = title
        workGeekAnimne?.season = season
        workGeekAnimne?.currentGeek = currentGeek
        workGeekAnimne?.totalGeek = totalGeek
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(workGeekId)
        parcel.writeString(title)
        parcel.writeInt(currentGeek)
        parcel.writeString(season)
        parcel.writeInt(totalGeek)
    }

    override fun describeContents(): Int {
        return 0
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
