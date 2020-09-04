package com.example.geek_central.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "work_geeks_animes")
class WorkGeekAnime(@PrimaryKey(autoGenerate = true)
                    @NotNull
                    @ColumnInfo(name = "id_work_geek_anime") override var workGeekId : Long? =  null,
                    @ColumnInfo(name = "title")
                    override var title: String = "",
                    @ColumnInfo(name = "current_geek")
                    override var currentGeek: Int = 0,

                    @ColumnInfo(name = "season")
                    override var season: Int? = null,
                    @ColumnInfo(name = "total_geek")
                    override var totalGeek: Int = 0) : WorkGeek {
}