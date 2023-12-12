package com.example.saysco.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.saysco.data.model.Scoring
import com.example.saysco.data.database.ScoringDao
import com.example.saysco.data.database.ScoringRoomDatabase
import com.example.saysco.utils.AppExecutors


class ScoringRepository(
    context: Context,
    private var mScoringsDao: ScoringDao,
    private var executorService: AppExecutors
) {

    init {
        val db = ScoringRoomDatabase.getInstance(context)
        mScoringsDao = db.scoringDao()
//        executorService = Executors.newSingleThreadExecutor()

    }

    fun getAllScorings(idUser: String): LiveData<List<Scoring>> = mScoringsDao.getAllScorings(idUser)

    fun getLatestScoring(idUser: String): LiveData<Scoring> = mScoringsDao.getLatestScoring(idUser)

    fun insert(scoring: Scoring) {
        executorService.diskIO.execute { mScoringsDao.insert(scoring) }
    }

    fun delete(scoring: Scoring) {
        executorService.diskIO.execute { mScoringsDao.delete(scoring) }
    }
    fun deleteAllScorings(idUser: String) {
        executorService.diskIO.execute { mScoringsDao.deleteAllScorings(idUser) }
    }

    fun update(scoring: Scoring) {
        executorService.diskIO.execute { mScoringsDao.update(scoring) }
    }

}