package com.example.geek_central.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "authores")
data class Author(@PrimaryKey(autoGenerate = true)
             @NotNull
             @ColumnInfo(name = "id")
                  var authorId : Long? =  null,
             @ColumnInfo(name = "name")
             var name : String? = null){

    override fun toString(): String {
        return "Author(authorId=$authorId, name=$name)"
    }
}
