package com.example.saysco.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.saysco.data.database.KeyAnswerDao
import com.example.saysco.data.model.KeyAnswer
import com.example.saysco.data.database.ScoringRoomDatabase
import com.example.saysco.utils.AppExecutors


class KeyAnswerRepository(
    context: Context,
    private var mKeyAnswersDao: KeyAnswerDao,
    private var executorService: AppExecutors
) {

    init {
        val db = ScoringRoomDatabase.getInstance(context)
        mKeyAnswersDao = db.keyAnswerDao()
//        executorService = Executors.newSingleThreadExecutor()

    }

    fun getAllKeyAnswers(idQuestion: String): LiveData<List<KeyAnswer>> = mKeyAnswersDao.getAllKeyAnswers(idQuestion)

    fun getKeyAnswer(idQuestion: String): LiveData<KeyAnswer> = mKeyAnswersDao.getKeyAnswer(idQuestion)

    fun insert(keyAnswer: KeyAnswer) {
        executorService.diskIO.execute { mKeyAnswersDao.insert(keyAnswer) }
    }

    fun delete(keyAnswer: KeyAnswer) {
        executorService.diskIO.execute { mKeyAnswersDao.delete(keyAnswer) }
    }
    fun deleteAllKeyAnswers(idQuestion: String) {
        executorService.diskIO.execute { mKeyAnswersDao.deleteAllKeyAnswers(idQuestion) }
    }

    fun update(keyAnswer: KeyAnswer) {
        executorService.diskIO.execute { mKeyAnswersDao.update(keyAnswer) }
    }

}