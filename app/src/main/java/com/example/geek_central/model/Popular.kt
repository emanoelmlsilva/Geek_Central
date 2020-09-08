package com.example.geek_central.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "populares")
data class Popular(
        @PrimaryKey(autoGenerate = true)
        @NotNull
        @ColumnInfo(name = "id_popular")
        val popularIid: Long? = null,

        @ColumnInfo(name = "workgeek_popular_id") var workGeekOwnerId: Long? =  null,

        @ColumnInfo(name = "grade")
        var grade: Double = 0.0,
        @ColumnInfo(name = "favorite")
        var favorite: Boolean = false) {
}
