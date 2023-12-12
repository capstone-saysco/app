package com.example.saysco.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.saysco.data.database.QuestionDao
import com.example.saysco.data.model.Question
import com.example.saysco.data.database.ScoringRoomDatabase
import com.example.saysco.utils.AppExecutors


class QuestionRepository(
    context: Context,
    private var mQuestionsDao: QuestionDao,
    private var executorService: AppExecutors
) {

    init {
        val db = ScoringRoomDatabase.getInstance(context)
        mQuestionsDao = db.questionDao()
//        executorService = Executors.newSingleThreadExecutor()

    }

    fun getAllQuestions(idScoring: String): LiveData<List<Question>> = mQuestionsDao.getAllQuestions(idScoring)

    fun getLatestQuestion(idScoring: String): LiveData<Question> = mQuestionsDao.getLatestQuestion(idScoring)

    fun insert(question: Question) {
        executorService.diskIO.execute { mQuestionsDao.insert(question) }
    }

    fun delete(question: Question) {
        executorService.diskIO.execute { mQuestionsDao.delete(question) }
    }
    fun deleteAllQuestions(idScoring: String) {
        executorService.diskIO.execute { mQuestionsDao.deleteAllQuestions(idScoring) }
    }

    fun update(question: Question) {
        executorService.diskIO.execute { mQuestionsDao.update(question) }
    }

}