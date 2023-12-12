package com.example.saysco.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Scoring")
@Parcelize
data class Scoring (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "idUser")
    var idUser: String? = null,

    @ColumnInfo(name = "titleScoring")
    var titleScoring: String? = null,

    @ColumnInfo(name = "descriptionScoring")
    var descriptionScoring: String? = null

) : Parcelable