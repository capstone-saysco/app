package com.example.saysco.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Question")
@Parcelize
data class Question (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "idScoring")
    var idScoring: String? = null,

    @ColumnInfo(name = "question")
    var question: String? = null

) : Parcelable