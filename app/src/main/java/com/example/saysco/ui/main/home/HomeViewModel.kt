package com.example.saysco.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saysco.data.repository.ScoringRepository

class HomeViewModel(private val scoringRepository: ScoringRepository) : ViewModel() {
    fun getScoring(idUser: String) = scoringRepository.getAllScorings(idUser)

    fun deleteAllScorings(idUser: String) = scoringRepository.deleteAllScorings(idUser)

}