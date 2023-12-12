package com.example.saysco.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.saysco.data.model.KeyAnswer

@Dao
interface KeyAnswerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(keyAnswer: KeyAnswer)

    @Update
    fun update(keyAnswer: KeyAnswer)

    @Delete
    fun delete(keyAnswer: KeyAnswer)

    @Query("DELETE FROM KeyAnswer WHERE idQuestion = :idQuestion")
    fun deleteAllKeyAnswers(idQuestion: String)

    @Query("SELECT * from keyAnswer WHERE idQuestion = :idQuestion ORDER BY id ASC")
    fun getAllKeyAnswers(idQuestion: String): LiveData<List<KeyAnswer>>

    @Query("SELECT * FROM KeyAnswer WHERE idQuestion = :idQuestion ORDER BY id DESC LIMIT 1")
    fun getKeyAnswer(idQuestion: String): LiveData<KeyAnswer>
}