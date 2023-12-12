package com.example.saysco.ui.newscoring.question.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saysco.data.model.Question
import com.example.saysco.data.repository.QuestionRepository

class QuestionViewModel (private val questionRepository: QuestionRepository) : ViewModel(){

    fun getQuestion(idScoring: String) = questionRepository.getAllQuestions(idScoring)

    fun getLatestQuestion(idScoring: String) = questionRepository.getLatestQuestion(idScoring)

    fun insertQuestion(question: Question) {
        questionRepository.insert(question)
    }

    fun updateQuestion(question: Question) {
        questionRepository.update(question)
    }

    fun deleteQuestion(question: Question) {
        questionRepository.delete(question)
    }
}