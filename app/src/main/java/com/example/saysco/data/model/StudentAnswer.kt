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

    @ColumnInfo(name = "idQuestion")
    var idQuestion: String? = null,

    @ColumnInfo(name = "idStudent")
    var idStudent: String? = null,

    @ColumnInfo(name = "studentName")
    var studentName: String? = null,

    @ColumnInfo(name = "studentAnswer")
    var studentAnswer: String? = null

) : Parcelable