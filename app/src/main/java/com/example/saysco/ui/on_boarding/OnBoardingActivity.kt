package com.example.saysco.ui.on_boarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.saysco.databinding.ActivityOnboardingBinding
import com.example.saysco.databinding.ActivitySplashBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.login.LoginActivity
import com.example.saysco.ui.login.LoginViewModel
import com.example.saysco.ui.main.MainActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.welcomeBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}