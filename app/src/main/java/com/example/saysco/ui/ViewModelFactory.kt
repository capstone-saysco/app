package com.example.saysco.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.saysco.data.repository.KeyAnswerRepository
import com.example.saysco.data.repository.QuestionRepository
import com.example.saysco.data.repository.RemoteRepository
import com.example.saysco.data.repository.ScoringRepository
import com.example.saysco.data.repository.StudentAnswerRepository
import com.example.saysco.di.Injection
import com.example.saysco.ui.login.LoginActivity
import com.example.saysco.ui.login.LoginViewModel
import com.example.saysco.ui.main.home.HomeViewModel
import com.example.saysco.ui.newscoring.NewScoringViewModel
import com.example.saysco.ui.register.RegisterViewModel

class ViewModelFactory private constructor(
    private val scoringRepository: ScoringRepository,
    private val questionRepository: QuestionRepository,
    private val keyAnswerRepository: KeyAnswerRepository,
    private val studentAnswerRepository: StudentAnswerRepository,
    private val remoteRepository: RemoteRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewScoringViewModel::class.java)) {
            return NewScoringViewModel(
                scoringRepository,
                questionRepository,
                keyAnswerRepository,
                studentAnswerRepository
            ) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(scoringRepository) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(remoteRepository) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(remoteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideScoringRepository(context),
                    Injection.provideQuestionRepository(context),
                    Injection.provideKeyAnswerRepository(context),
                    Injection.provideStudentAnswerRepository(context),
                    Injection.provideRepository(context)
                )
            }.also { instance = it }
    }
}
