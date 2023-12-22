package com.example.saysco.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.saysco.data.database.EssayDao
import com.example.saysco.data.model.Essay
import com.example.saysco.data.database.ScoringRoomDatabase
import com.example.saysco.data.remote.response.AllEssayResponse
import com.example.saysco.data.remote.retrofit.ApiConfig
import com.example.saysco.data.remote.retrofit.ApiService
import com.example.saysco.utils.AppExecutors


class EssayRepository(
    context: Context,
    private var mEssaysDao: EssayDao,
    private var executorService: AppExecutors,
    private val apiService: ApiService
) {

    init {
        val db = ScoringRoomDatabase.getInstance(context)
        mEssaysDao = db.essayDao()
    }

    suspend fun getAllEssays(userId: String): LiveData<List<Essay>> = mEssaysDao.getAllEssays(userId)

    fun getLatestEssay(userId: String): LiveData<Essay> = mEssaysDao.getLatestEssay(userId)

    fun insert(essay: Essay) {
        executorService.diskIO.execute { mEssaysDao.insert(essay) }
    }

    fun delete(essay: Essay) {
        executorService.diskIO.execute { mEssaysDao.delete(essay) }
    }
    fun deleteAllEssays(userId: String) {
        executorService.diskIO.execute { mEssaysDao.deleteAllEssays(userId) }
    }

    fun update(essay: Essay) {
        executorService.diskIO.execute { mEssaysDao.update(essay) }
    }

    suspend fun getAllEssay(token: String) : AllEssayResponse {
        val apiService = ApiConfig.getApiService(token)
        return apiService.getAllEssays()
    }

}