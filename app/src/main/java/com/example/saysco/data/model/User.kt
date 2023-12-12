package com.example.saysco.data.model

data class User(
    val idUser: String,
    val username: String,
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)