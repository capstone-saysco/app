package com.example.saysco.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "StudentAnswer")
@Parcelize
data class StudentAnswer (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "idEssay")
    var idEssay: Int = 0,

    @ColumnInfo(name = "studentName")
    var studentName: String? = null,

    @ColumnInfo(name = "studentNumber")
    var studentNumber: Int = 0,

    @ColumnInfo(name = "answer")
    var answer: String? = null,

    @ColumnInfo(name = "score")
    var score: Int = 0

) : Parcelable