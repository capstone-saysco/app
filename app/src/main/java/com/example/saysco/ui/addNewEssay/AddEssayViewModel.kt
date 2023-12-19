package com.example.saysco.ui.addNewEssay

import androidx.lifecycle.ViewModel
import com.example.saysco.data.model.Essay
import com.example.saysco.data.repository.EssayRepository
import com.example.saysco.data.repository.StudentAnswerRepository

class AddEssayViewModel (
    private val essayRepository: EssayRepository,
    private val studentAnswerRepository: StudentAnswerRepository
) : ViewModel(){

    fun getLatestEssay(idUser: String) = essayRepository.getLatestEssay(idUser)

    fun insertEssay(essay: Essay) {
        essayRepository.insert(essay)
    }

    fun updateEssay(essay: Essay) {
        essayRepository.update(essay)
    }

    fun deleteEssay(essay: Essay) {
        essayRepository.delete(essay)
    }

    fun deleteAllStudentAnswers(idEssay: String) = studentAnswerRepository.deleteAllStudentAnswers(idEssay)

}