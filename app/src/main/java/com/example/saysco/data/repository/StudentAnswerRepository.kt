package com.example.saysco.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.saysco.data.database.StudentAnswerDao
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.data.database.ScoringRoomDatabase
import com.example.saysco.utils.AppExecutors


class StudentAnswerRepository(
    context: Context,
    private var mStudentAnswersDao: StudentAnswerDao,
    private var executorService: AppExecutors
) {

    init {
        val db = ScoringRoomDatabase.getInstance(context)
        mStudentAnswersDao = db.studentAnswerDao()
    }

    fun getAllStudentAnswers(idQuestion: String): LiveData<List<StudentAnswer>> = mStudentAnswersDao.getAllStudentAnswers(idQuestion)


    fun getStudentAnswer(idQuestion: String): LiveData<StudentAnswer> = mStudentAnswersDao.getStudentAnswer(idQuestion)

    fun insert(studentAnswer: StudentAnswer) {
        executorService.diskIO.execute { mStudentAnswersDao.insert(studentAnswer) }
    }

    fun delete(studentAnswer: StudentAnswer) {
        executorService.diskIO.execute { mStudentAnswersDao.delete(studentAnswer) }
    }
    fun deleteAllStudentAnswers(idQuestion: String) {
        executorService.diskIO.execute { mStudentAnswersDao.deleteAllStudentAnswers(idQuestion) }
    }

    fun update(studentAnswer: StudentAnswer) {
        executorService.diskIO.execute { mStudentAnswersDao.update(studentAnswer) }
    }

}