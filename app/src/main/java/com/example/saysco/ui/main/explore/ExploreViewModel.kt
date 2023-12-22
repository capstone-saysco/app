package com.example.saysco.ui.main.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.saysco.data.remote.response.AllEssayResponse
import com.example.saysco.data.repository.EssayRepository
import com.example.saysco.data.Result
import com.example.saysco.data.model.User
import com.example.saysco.data.repository.UserRepository
import kotlinx.coroutines.launch

class ExploreViewModel(private val essayRepository: EssayRepository, private val userRepository: UserRepository) : ViewModel() {

    private val _essays = MutableLiveData<Result<AllEssayResponse>>()
    val essays: LiveData<Result<AllEssayResponse>> get() = _essays

    fun loadEssays(token:String) {
        viewModelScope.launch {
            _essays.value = Result.Loading
            try {
                val response = essayRepository.getAllEssay(token)
                _essays.value = Result.Success(response)
            } catch (e: Exception) {
                _essays.value = Result.Error(e.message.toString())
            }
        }
    }
    fun getSession(): LiveData<User> {
        return userRepository.getSession().asLiveData()
    }
}