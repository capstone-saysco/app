package com.example.saysco.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.saysco.data.model.Scoring

@Dao
interface ScoringDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(scoring: Scoring)

    @Update
    fun update(scoring: Scoring)

    @Delete
    fun delete(scoring: Scoring)

    @Query("DELETE FROM Scoring WHERE idUser = :idUser")
    fun deleteAllScorings(idUser: String)

    @Query("SELECT * from scoring WHERE idUser = :idUser ORDER BY id ASC")
    fun getAllScorings(idUser: String): LiveData<List<Scoring>>

    @Query("SELECT * FROM Scoring WHERE idUser = :idUser ORDER BY id DESC LIMIT 1")
    fun getLatestScoring(idUser: String): LiveData<Scoring>
}