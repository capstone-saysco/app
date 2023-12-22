package com.example.saysco.ui.register

import androidx.lifecycle.ViewModel
import com.example.saysco.data.repository.AuthRepository

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {
    fun register(name: String, email: String, password: String) =
        repository.registerUser(name, email, password)
}