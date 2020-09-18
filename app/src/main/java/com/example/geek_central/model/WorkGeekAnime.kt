package com.example.geek_central.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "work_geeks_animes")
class WorkGeekAnime(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id_work_geek_anime") override var workGeekId: Long? = null,
    @ColumnInfo(name = "title")
    override var title: String = "",
    @ColumnInfo(name = "author")
    override var author: String? = null,
    @ColumnInfo(name = "current_geek")
    override var currentGeek: Double = 0.0,
    @ColumnInfo(name = "season")
    override var season: String? = null,
    @ColumnInfo(name = "total_geek")
    override var totalGeek: Double = 0.0,
    @ColumnInfo(name = "categories")
    override var categories: List<String> = ArrayList()
) : WorkGeek {
}