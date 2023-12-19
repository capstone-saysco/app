package com.example.saysco.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.saysco.data.model.Essay

@Dao
interface EssayDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(essay: Essay)

    @Update
    fun update(essay: Essay)

    @Delete
    fun delete(essay: Essay)

    @Query("DELETE FROM Essay WHERE userId = :userId")
    fun deleteAllEssays(userId: String)

    @Query("SELECT * from Essay WHERE userId = :userId ORDER BY id ASC")
    fun getAllEssays(userId: String): LiveData<List<Essay>>

    @Query("SELECT * FROM Essay WHERE userId = :idScoring ORDER BY id DESC LIMIT 1")
    fun getLatestEssay(idScoring: String): LiveData<Essay>
}