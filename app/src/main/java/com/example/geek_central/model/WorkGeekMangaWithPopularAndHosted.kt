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
    ) val hosted : Hosted
) {
}