package com.example.geek_central.model

import androidx.room.Embedded
import androidx.room.Relation

class WorkGeekAnimeWithPopularAndHosted(
    @Embedded val workGeekAnime: WorkGeekAnime,
    @Relation(
        parentColumn = "id_work_geek_anime",
        entityColumn = "workgeek_popular_id"
    ) val popular: Popular,
    @Relation(
        parentColumn = "id_work_geek_anime",
        entityColumn = "workgeek_hosted_id"
    ) val hosted: Hosted
) : Comparable<WorkGeekAnimeWithPopularAndHosted>  {
    override fun compareTo(other: WorkGeekAnimeWithPopularAndHosted): Int = if(popular.grade > other.popular.grade) 1 else 0
}