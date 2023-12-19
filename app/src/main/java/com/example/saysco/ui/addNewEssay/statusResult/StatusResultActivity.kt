package com.example.saysco.ui.addNewEssay.statusResult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.saysco.databinding.ActivityStatusResultBinding
import com.example.saysco.ui.main.MainActivity

class StatusResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatusResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStatusResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}