package com.example.saysco.di

import android.content.Context
import com.example.saysco.data.database.ScoringRoomDatabase
import com.example.saysco.data.preference.UserPreference
import com.example.saysco.data.preference.dataStore
import com.example.saysco.data.remote.retrofit.ApiConfig
import com.example.saysco.data.repository.AuthRepository
import com.example.saysco.data.repository.EssayRepository
import com.example.saysco.data.repository.StudentAnswerRepository
import com.example.saysco.data.repository.UserRepository
import com.example.saysco.utils.AppExecutors
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideEssayRepository(context: Context): EssayRepository {
        val database = ScoringRoomDatabase.getInstance(context)
        val dao = database.essayDao()
        val appExecutors = AppExecutors()
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val token = user.token
        val apiService = ApiConfig.getApiService(token)
        return EssayRepository(context,dao,appExecutors,apiService)
    }

    fun provideStudentAnswerRepository(context: Context): StudentAnswerRepository {
        val database = ScoringRoomDatabase.getInstance(context)
        val dao = database.studentAnswerDao()
        val appExecutors = AppExecutors()
        return StudentAnswerRepository(context,dao,appExecutors)
    }
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
    fun provideRepository(context: Context): AuthRepository {
        val apiService = ApiConfig.getApiService()
        return AuthRepository.getInstance(apiService)
    }
}