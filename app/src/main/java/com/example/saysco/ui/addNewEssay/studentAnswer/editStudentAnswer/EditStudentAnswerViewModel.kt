package com.example.saysco.ui.addNewEssay.studentAnswer.editStudentAnswer

import androidx.lifecycle.ViewModel
import com.example.saysco.data.model.Essay
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.data.repository.StudentAnswerRepository

class EditStudentAnswerViewModel(
    private val studentAnswerRepository: StudentAnswerRepository
) : ViewModel() {

    fun getAllStudentAnswer(idEssay: String) = studentAnswerRepository.getAllStudentAnswers(idEssay)

    fun insertStudentAnswer(studentAnswer: StudentAnswer) {
        studentAnswerRepository.insert(studentAnswer)
    }

    fun updateStudentAnswer(studentAnswer: StudentAnswer) {
        studentAnswerRepository.update(studentAnswer)
    }

    fun deleteStudentAnswer(studentAnswer: StudentAnswer) {
        studentAnswerRepository.delete(studentAnswer)
    }
}