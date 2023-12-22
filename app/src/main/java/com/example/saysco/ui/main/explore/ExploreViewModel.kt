package com.example.saysco.ui.main.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saysco.data.repository.AuthRepository
import com.example.saysco.data.repository.EssayRepository
import com.example.saysco.data.repository.StudentAnswerRepository
import com.example.saysco.data.repository.UserRepository

class ExploreViewModel(
    private val essayRepository: EssayRepository,
    private val studentAnswerRepository: StudentAnswerRepository,
    private val userRepository: UserRepository
    ) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}