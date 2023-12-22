package com.example.saysco.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.saysco.data.database.StudentAnswerDao
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.data.database.ScoringRoomDatabase
import com.example.saysco.data.remote.response.AllAnswersResponse
import com.example.saysco.data.remote.response.AllEssayResponse
import com.example.saysco.data.remote.retrofit.ApiConfig
import com.example.saysco.data.remote.retrofit.ApiService
import com.example.saysco.utils.AppExecutors


class StudentAnswerRepository(
    context: Context,
    private var mStudentAnswersDao: StudentAnswerDao,
    private var executorService: AppExecutors,
) {

    init {
        val db = ScoringRoomDatabase.getInstance(context)
        mStudentAnswersDao = db.studentAnswerDao()
    }

    fun getAllStudentAnswers(idEssay: String): LiveData<List<StudentAnswer>> = mStudentAnswersDao.getAllStudentAnswers(idEssay)


    fun getStudentAnswer(idEssay: String): LiveData<StudentAnswer> = mStudentAnswersDao.getStudentAnswer(idEssay)

    fun insert(studentAnswer: StudentAnswer) {
        executorService.diskIO.execute { mStudentAnswersDao.insert(studentAnswer) }
    }

    fun delete(studentAnswer: StudentAnswer) {
        executorService.diskIO.execute { mStudentAnswersDao.delete(studentAnswer) }
    }
    fun deleteAllStudentAnswers(idEssay: String) {
        executorService.diskIO.execute { mStudentAnswersDao.deleteAllStudentAnswers(idEssay) }
    }

    fun update(studentAnswer: StudentAnswer) {
        executorService.diskIO.execute { mStudentAnswersDao.update(studentAnswer) }
    }

    suspend fun getAllAnswers(token:String, answerId: String): AllAnswersResponse {
        val apiService = ApiConfig.getApiService(token)
        return apiService.getAnswer(answerId)
    }

}