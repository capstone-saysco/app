package com.example.saysco.ui.on_boarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.saysco.databinding.ActivityOnboardingBinding
import com.example.saysco.ui.login.LoginActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityOnboardingBinding.inflate(layoutInflater)

        Thread.sleep(3000)
        installSplashScreen()

        supportActionBar?.hide()

        binding.welcomeBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}