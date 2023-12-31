package com.example.saysco.data.repository

import com.example.saysco.data.model.User
import com.example.saysco.data.preference.UserPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class UserRepository private constructor(
    private val userPreference: UserPreference
) {

    private var token = ""

    fun getToken(): String {
        getSession()
        return token
    }

    suspend fun saveSession(user: User) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<User> {
        return userPreference.getSession().onEach {
            token = it.token
        }
    }

    suspend fun logout() {
        userPreference.logout()
    }


    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference)
            }.also { instance = it }
    }
}