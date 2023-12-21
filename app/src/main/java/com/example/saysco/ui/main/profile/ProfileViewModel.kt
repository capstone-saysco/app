package com.example.saysco.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.saysco.data.repository.AuthRepository
import com.example.saysco.data.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository, private val authRepository: AuthRepository) : ViewModel() {

    fun getUser() = userRepository.getSession().asLiveData()

    fun logout(){
        viewModelScope.launch {
            userRepository.logout()
//            authRepository.logout(userRepository.getToken())
        }
    }
}