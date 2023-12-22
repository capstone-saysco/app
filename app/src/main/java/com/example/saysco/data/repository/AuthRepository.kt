package com.example.saysco.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.saysco.data.Result
import com.example.saysco.data.remote.response.LoginResponse
import com.example.saysco.data.remote.response.RegisterResponse
import com.example.saysco.data.remote.retrofit.ApiConfig
import com.example.saysco.data.remote.retrofit.ApiService
import com.example.saysco.databinding.FragmentExploreBinding

class AuthRepository private constructor(
    private val apiService: ApiService
) {

    fun registerUser(
        userName: String,
        userEmail: String,
        userPassword: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(userName, userEmail, userPassword)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
    fun loginUser(
        userEmail: String,
        userPassword: String
    ): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(userEmail, userPassword)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun logout(token: String) {
        val apiServiceWithToken = ApiConfig.getApiService(token)
        apiServiceWithToken.logout()
    }

    companion object {
        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(
            apiService: ApiService
        ): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(apiService)
            }.also { instance = it }
    }
}