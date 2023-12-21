package com.example.saysco.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.saysco.data.repository.EssayRepository
import com.example.saysco.data.repository.AuthRepository
import com.example.saysco.data.repository.StudentAnswerRepository
import com.example.saysco.data.repository.UserRepository
import com.example.saysco.di.Injection
import com.example.saysco.ui.addNewEssay.AddEssayViewModel
import com.example.saysco.ui.addNewEssay.confirmation.ConfirmationViewModel
import com.example.saysco.ui.addNewEssay.studentAnswer.addStudentAnswer.AddStudentAnswerViewModel
import com.example.saysco.ui.addNewEssay.studentAnswer.editStudentAnswer.EditStudentAnswerViewModel
import com.example.saysco.ui.addNewEssay.studentAnswer.listStudentAnswer.ListStudentAnswerViewModel
import com.example.saysco.ui.login.LoginViewModel
import com.example.saysco.ui.main.explore.ExploreViewModel
import com.example.saysco.ui.main.home.HomeViewModel
import com.example.saysco.ui.main.profile.ProfileViewModel
import com.example.saysco.ui.register.RegisterViewModel
import com.example.saysco.ui.splashscreen.SplashViewModel

class ViewModelFactory private constructor(
    private val userRepository: UserRepository,
    private val essayRepository: EssayRepository,
    private val studentAnswerRepository: StudentAnswerRepository,
    private val authRepository: AuthRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEssayViewModel::class.java)) {
            return AddEssayViewModel(essayRepository, studentAnswerRepository,) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(userRepository) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(authRepository) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(authRepository, userRepository) as T
        } else if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(userRepository) as T
        } else if (modelClass.isAssignableFrom(ListStudentAnswerViewModel::class.java)) {
            return ListStudentAnswerViewModel(essayRepository,studentAnswerRepository) as T
        } else if (modelClass.isAssignableFrom(AddStudentAnswerViewModel::class.java)) {
            return AddStudentAnswerViewModel(studentAnswerRepository) as T
        } else if (modelClass.isAssignableFrom(EditStudentAnswerViewModel::class.java)) {
            return EditStudentAnswerViewModel(studentAnswerRepository) as T
        } else if (modelClass.isAssignableFrom(ConfirmationViewModel::class.java)) {
            return ConfirmationViewModel(essayRepository,studentAnswerRepository, userRepository) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(userRepository, authRepository) as T
        } else if (modelClass.isAssignableFrom(ExploreViewModel::class.java)) {
            return ExploreViewModel(essayRepository, studentAnswerRepository, userRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideUserRepository(context),
                    Injection.provideEssayRepository(context),
                    Injection.provideStudentAnswerRepository(context),
                    Injection.provideRepository(context)
                )
            }.also { instance = it }
    }
}
