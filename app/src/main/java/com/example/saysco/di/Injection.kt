package com.example.saysco.di

import android.content.Context
import com.example.saysco.data.database.ScoringRoomDatabase
import com.example.saysco.data.repository.KeyAnswerRepository
import com.example.saysco.data.repository.QuestionRepository
import com.example.saysco.data.repository.ScoringRepository
import com.example.saysco.data.repository.StudentAnswerRepository
import com.example.saysco.utils.AppExecutors

object Injection {
    fun provideScoringRepository(context: Context): ScoringRepository {
//        val apiService = ApiConfig.getApiService()
        val database = ScoringRoomDatabase.getInstance(context)
        val dao = database.scoringDao()
        val appExecutors = AppExecutors()
        return ScoringRepository(context,dao,appExecutors)
    }
    fun provideQuestionRepository(context: Context): QuestionRepository {
//        val apiService = ApiConfig.getApiService()
        val database = ScoringRoomDatabase.getInstance(context)
        val dao = database.questionDao()
        val appExecutors = AppExecutors()
        return QuestionRepository(context,dao,appExecutors)
    }
    fun provideKeyAnswerRepository(context: Context): KeyAnswerRepository {
//        val apiService = ApiConfig.getApiService()
        val database = ScoringRoomDatabase.getInstance(context)
        val dao = database.keyAnswerDao()
        val appExecutors = AppExecutors()
        return KeyAnswerRepository(context,dao,appExecutors)
    }
    fun provideStudentAnswerRepository(context: Context): StudentAnswerRepository {
//        val apiService = ApiConfig.getApiService()
        val database = ScoringRoomDatabase.getInstance(context)
        val dao = database.studentAnswerDao()
        val appExecutors = AppExecutors()
        return StudentAnswerRepository(context,dao,appExecutors)
    }
}