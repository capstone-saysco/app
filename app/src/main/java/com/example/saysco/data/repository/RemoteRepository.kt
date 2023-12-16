package com.example.saysco.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.saysco.data.Result
import com.example.saysco.data.model.User
import com.example.saysco.data.remote.response.LoginResponse
import com.example.saysco.data.remote.response.RegisterResponse
import com.example.saysco.data.remote.retrofit.ApiService

class RemoteRepository private constructor(
    private val apiService: ApiService
) {
    fun registerUser(
        userName: String,
        userEmail: String,
        userPassword: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            Log.d("repo", "masuk try")
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
//            userPreference.storeSession(
//                User(
//                    response.loginResult.name,
//                    response.loginResult.token
//                )
//            )
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: RemoteRepository? = null
        fun getInstance(
            apiService: ApiService
        ): RemoteRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteRepository(apiService)
            }.also { instance = it }
    }
}