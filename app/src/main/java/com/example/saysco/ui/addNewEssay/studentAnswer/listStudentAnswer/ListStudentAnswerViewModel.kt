package com.example.saysco.ui.addNewEssay.studentAnswer.listStudentAnswer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.saysco.data.model.Essay
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.data.repository.EssayRepository
import com.example.saysco.data.repository.StudentAnswerRepository

class ListStudentAnswerViewModel(
    private val essayRepository: EssayRepository,
    private val studentAnswerRepository: StudentAnswerRepository
) : ViewModel() {

    fun getLatestEssay(idUser: String) = essayRepository.getLatestEssay(idUser)

    fun getAllStudentAnswer(idEssay: String) : LiveData<List<StudentAnswer>> = studentAnswerRepository.getAllStudentAnswers(idEssay)

    fun deleteStudentAnswer(studentAnswer: StudentAnswer) {
        studentAnswerRepository.delete(studentAnswer)
    }
}