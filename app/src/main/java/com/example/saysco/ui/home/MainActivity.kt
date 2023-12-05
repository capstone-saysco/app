package com.example.saysco.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.saysco.databinding.ActivityMainBinding
import com.example.saysco.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        Thread.sleep(3000)
        installSplashScreen()

        binding.welcomeBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

//        var splashScreen = installSplashScreen()
//        splashScreen.apply {
//            setOnExitAnimationListener {splashScreenView ->
//                ObjectAnimator.ofFloat(
//                    splashScreenView.view,
//                    View.TRANSLATION_Y,
//                    0f, -splashScreenView.view.height.toFloat()
//                ).apply {
//                    interpolator = DecelerateInterpolator()
//                    duration = 500L
//                    doOnEnd { splashScreenView.remove() }
//                    start()
//                }
//            }
//        }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}