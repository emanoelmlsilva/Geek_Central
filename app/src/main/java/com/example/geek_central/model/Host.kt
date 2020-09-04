package com.example.geek_central.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "hosts")
class Host(
    @PrimaryKey(autoGenerate = true) @NotNull @ColumnInfo(name = "id_hosted") val hostedId: Long? = null,

    @ColumnInfo(name = "workgeek_hosted_id") var workGeekOwnerId: Long? =  null,

    @ColumnInfo(name = "site")
    var site: String = "",
    @ColumnInfo(name = "type")
    var type: String = ""
) {

}
