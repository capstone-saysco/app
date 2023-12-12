package com.example.saysco.ui.newscoring.question.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.saysco.R
import com.example.saysco.databinding.ActivityAddQuestionBinding
import com.example.saysco.databinding.ActivityQuestionBinding

class AddQuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}