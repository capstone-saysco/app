package com.example.saysco.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.saysco.data.model.Essay
import com.example.saysco.data.model.StudentAnswer

@Database(entities = [Essay::class, StudentAnswer::class], version = 3)
abstract class ScoringRoomDatabase : RoomDatabase() {

    abstract fun essayDao(): EssayDao
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