package com.example.saysco.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.saysco.data.model.StudentAnswer

@Dao
interface StudentAnswerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(studentAnswer: StudentAnswer)

    @Update
    fun update(studentAnswer: StudentAnswer)

    @Delete
    fun delete(studentAnswer: StudentAnswer)

    @Query("DELETE FROM StudentAnswer WHERE idQuestion = :idQuestion")
    fun deleteAllStudentAnswers(idQuestion: String)

    @Query("SELECT * from studentAnswer WHERE idQuestion = :idQuestion ORDER BY id ASC")
    fun getAllStudentAnswers(idQuestion: String): LiveData<List<StudentAnswer>>

    @Query("SELECT * FROM StudentAnswer WHERE idQuestion = :idQuestion ORDER BY id DESC LIMIT 1")
    fun getStudentAnswer(idQuestion: String): LiveData<StudentAnswer>
}