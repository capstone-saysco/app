package com.example.saysco.ui.listAnwers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.saysco.data.Result
import com.example.saysco.data.model.User
import com.example.saysco.data.remote.response.AllAnswersResponse
import com.example.saysco.data.remote.response.AllEssayResponse
import com.example.saysco.data.remote.response.UserResponse
import com.example.saysco.data.remote.retrofit.ApiConfig
import com.example.saysco.data.repository.AuthRepository
import com.example.saysco.data.repository.StudentAnswerRepository
import com.example.saysco.data.repository.UserRepository
import kotlinx.coroutines.launch

class ListAnswerViewModel(private val studentAnswerRepository: StudentAnswerRepository, private val userRepository: UserRepository) : ViewModel() {
    private val _answers = MutableLiveData<Result<AllAnswersResponse>>()
    val answers: LiveData<Result<AllAnswersResponse>> get() = _answers

    fun loadAnswers(token:String, essayId: String) {
        viewModelScope.launch {
            _answers.value = Result.Loading
            try {
                val response = studentAnswerRepository.getAllAnswers(token,essayId)
                _answers.value = Result.Success(response)
            } catch (e: Exception) {
                _answers.value = Result.Error(e.message.toString())
            }
        }
    }
    fun getSession(): LiveData<User> {
        return userRepository.getSession().asLiveData()
    }
}