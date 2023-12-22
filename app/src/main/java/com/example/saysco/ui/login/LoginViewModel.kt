package com.example.saysco.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.saysco.data.Result
import com.example.saysco.data.model.User
import com.example.saysco.data.remote.response.UserResponse
import com.example.saysco.data.remote.retrofit.ApiConfig
import com.example.saysco.data.repository.AuthRepository
import com.example.saysco.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository, private val userRepository: UserRepository) : ViewModel() {
    fun login(email: String, password: String) = repository.loginUser(email, password)

    fun getDataUser(token: String): LiveData<Result<UserResponse>> = liveData {
        emit(Result.Loading)
        try {
            val apiService = ApiConfig.getApiService(token)
            val response = apiService.getUser()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
    fun saveSession(user: User) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }
}