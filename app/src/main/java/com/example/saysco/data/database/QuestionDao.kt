package com.example.saysco.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.saysco.data.model.Question

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(question: Question)

    @Update
    fun update(question: Question)

    @Delete
    fun delete(question: Question)

    @Query("DELETE FROM Question WHERE idScoring = :idScoring")
    fun deleteAllQuestions(idScoring: String)

    @Query("SELECT * from question WHERE idScoring = :idScoring ORDER BY id ASC")
    fun getAllQuestions(idScoring: String): LiveData<List<Question>>

    @Query("SELECT * FROM Question WHERE idScoring = :idScoring ORDER BY id DESC LIMIT 1")
    fun getLatestQuestion(idScoring: String): LiveData<Question>
}