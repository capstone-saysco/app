package com.example.saysco.ui.register

import androidx.lifecycle.ViewModel
import com.example.saysco.data.repository.RemoteRepository

class RegisterViewModel(private val repository: RemoteRepository) : ViewModel() {
    fun register(name: String, email: String, password: String) =
        repository.registerUser(name, email, password)
}