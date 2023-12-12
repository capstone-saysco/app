package com.example.saysco.ui.newscoring

import androidx.lifecycle.ViewModel
import com.example.saysco.data.model.KeyAnswer
import com.example.saysco.data.model.Question
import com.example.saysco.data.model.Scoring
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.data.repository.KeyAnswerRepository
import com.example.saysco.data.repository.QuestionRepository
import com.example.saysco.data.repository.ScoringRepository
import com.example.saysco.data.repository.StudentAnswerRepository

class NewScoringViewModel (
    private val scoringRepository: ScoringRepository,
    private val questionRepository: QuestionRepository,
    private val keyAnswerRepository: KeyAnswerRepository,
    private val studentAnswerRepository: StudentAnswerRepository
) : ViewModel(){

    fun getScoring(idUser: String) = scoringRepository.getAllScorings(idUser)

    fun getLatestScoring(idUser: String) = scoringRepository.getLatestScoring(idUser)

    fun insertScoring(scoring: Scoring) {
        scoringRepository.insert(scoring)
    }

    fun updateScoring(scoring: Scoring) {
        scoringRepository.update(scoring)
    }

    fun deleteScoring(scoring: Scoring) {
        scoringRepository.delete(scoring)
    }

    fun getQuestion(idScoring: String) = questionRepository.getAllQuestions(idScoring)

    fun deleteAllQuestions(idScoring: String) = questionRepository.deleteAllQuestions(idScoring)

    fun deleteAllKeyAnswer(idScoring: String) = keyAnswerRepository.deleteAllKeyAnswers(idScoring)

    fun deleteAllStudentAnswers(idQuestion: String) = studentAnswerRepository.deleteAllStudentAnswers(idQuestion)

}