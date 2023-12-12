package com.example.saysco.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.saysco.data.model.KeyAnswer
import com.example.saysco.data.model.Question
import com.example.saysco.data.model.Scoring
import com.example.saysco.data.model.StudentAnswer

@Database(entities = [Scoring::class, Question::class, KeyAnswer::class, StudentAnswer::class], version = 2)
abstract class ScoringRoomDatabase : RoomDatabase() {

    abstract fun scoringDao(): ScoringDao
    abstract fun questionDao(): QuestionDao
    abstract fun keyAnswerDao(): KeyAnswerDao
    abstract fun studentAnswerDao(): StudentAnswerDao

    companion object {
        @Volatile
        private var INSTANCE: ScoringRoomDatabase? = null

        fun getInstance(context: Context): ScoringRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ScoringRoomDatabase::class.java, "SaySco.db"
                ).build()
            }
    }
}