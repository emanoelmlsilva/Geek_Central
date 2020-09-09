package com.example.geek_central.model

class BaseWorkGeek {
    var workGeekId: Long? = null
    lateinit var title: String
    var currentGeek: Int = 0
    var season: String? = null
    var totalGeek: Int = 0
    lateinit var popular: Popular
    lateinit var host: Host
    var workGeekManga: WorkGeekManga? = null
    var workGeekAnimne: WorkGeekAnime? = null
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

}
