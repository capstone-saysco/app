package com.example.saysco.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "KeyAnswer")
@Parcelize
data class KeyAnswer (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "idQuestion")
    var idQuestion: String? = null,

    @ColumnInfo(name = "keyword")
    var keyword: String? = null

) : Parcelable