package com.example.geek_central.model

import androidx.room.Embedded
import androidx.room.Relation

data class WorkGeekMangaWithPopularAndHosted
    (
    @Embedded val workGeek: WorkGeekManga,
    @Relation(
        parentColumn = "id_work_geek_manga",
        entityColumn = "workgeek_popular_id"
    ) val popular: Popular,
    @Relation(
        parentColumn = "id_work_geek_manga",
        entityColumn = "workgeek_hosted_id"
    ) val host : Host
) : Comparable<WorkGeekMangaWithPopularAndHosted>  {
    override fun compareTo(other: WorkGeekMangaWithPopularAndHosted): Int = if(popular.grade > other.popular.grade) 1 else 0
}