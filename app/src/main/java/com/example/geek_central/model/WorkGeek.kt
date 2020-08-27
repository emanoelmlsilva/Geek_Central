package com.example.geek_central.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "work_geek_table")
class WorkGeek(

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "current_geek")
    var currentGeek: Int? = null,
    @ColumnInfo(name = "season")
    var season: Int? = null,
    @ColumnInfo(name = "total_geek")
    var totalGeek: Int? = null,
    @ColumnInfo(name = "categories")
    var categories: MutableList<String>? = null,
    @ColumnInfo(name = "author")
    var author: Author? = null,
    @ColumnInfo(name = "popular")
    var popular: Popular? = null,
    @ColumnInfo(name = "hosted")
    var hosted: Hosted? = null
) {

}
