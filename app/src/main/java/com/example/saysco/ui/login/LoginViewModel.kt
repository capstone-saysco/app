package com.example.saysco.ui.login

import androidx.lifecycle.ViewModel
import com.example.saysco.data.repository.RemoteRepository

class LoginViewModel(private val repository: RemoteRepository) : ViewModel() {
    fun login(email: String, password: String) = repository.loginUser(email, password)
}