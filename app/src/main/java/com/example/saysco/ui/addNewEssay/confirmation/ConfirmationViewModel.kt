package com.example.saysco.ui.addNewEssay.confirmation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.saysco.data.Result
import com.example.saysco.data.model.Essay
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.data.model.User
import com.example.saysco.data.remote.response.InputAnswerResponse
import com.example.saysco.data.remote.response.InputEssayResponse
import com.example.saysco.data.remote.response.UserResponse
import com.example.saysco.data.remote.retrofit.ApiConfig
import com.example.saysco.data.repository.EssayRepository
import com.example.saysco.data.repository.StudentAnswerRepository
import com.example.saysco.data.repository.UserRepository

class ConfirmationViewModel (
    private val essayRepository: EssayRepository,
    private val studentAnswerRepository: StudentAnswerRepository,
    private val userRepository: UserRepository
) : ViewModel(){

    fun getLatestEssay(idUser: String) = essayRepository.getLatestEssay(idUser)

    fun getAllStudentAnswer(idEssay: String) : LiveData<List<StudentAnswer>> = studentAnswerRepository.getAllStudentAnswers(idEssay)

    fun getSession(): LiveData<User> {
        return userRepository.getSession().asLiveData()
    }

    fun deleteEssay(essay: Essay) {
        essayRepository.delete(essay)
    }

    fun deleteAllStudentAnswers(idEssay: String) = studentAnswerRepository.deleteAllStudentAnswers(idEssay)


    fun sendEssay (
        token: String,
        userId:String,
        question:String,
        keyAnswer:String
    ): LiveData<Result<InputEssayResponse>> = liveData {
        emit(Result.Loading)
        try {
            val apiService = ApiConfig.getApiService(token)
            val response = apiService.addEssay(userId = userId, question = question, keyAnswer = keyAnswer)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
    fun sendStudentAnswer (
        token: String,
        essayId:String,
        studentName:String,
        studentNumber:String,
        studentAnswer:String,
        score:String
    ): LiveData<Result<InputAnswerResponse>> = liveData {
        emit(Result.Loading)
        try {
            val apiService = ApiConfig.getApiService(token)
            val response = apiService.addAnswer(
                essayId,
                studentName,
                studentNumber,
                studentAnswer,
                score
            )
            // Log response here
            Log.d("API_RESPONSE_STUDENT_ANSWER", response.toString())
            emit(Result.Success(response))
        } catch (e: Exception) {
            // Log exception here
            Log.e("API_EXCEPTION_STUDENT_ANSWER", e.message, e)
            emit(Result.Error(e.message.toString()))
        }
    }
}