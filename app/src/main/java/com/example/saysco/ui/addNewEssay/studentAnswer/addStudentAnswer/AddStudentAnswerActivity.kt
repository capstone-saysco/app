package com.example.saysco.ui.addNewEssay.studentAnswer.addStudentAnswer

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.saysco.R
import com.example.saysco.data.model.Essay
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.databinding.ActivityAddStudentAnswerBinding
import com.example.saysco.databinding.ActivityListStudentAnswerBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.addNewEssay.studentAnswer.listStudentAnswer.ListStudentAnswerActivity
import com.example.saysco.ui.addNewEssay.studentAnswer.listStudentAnswer.ListStudentAnswerViewModel

class AddStudentAnswerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentAnswerBinding

    private val viewModel by viewModels<AddStudentAnswerViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add Student Answer"

        val essay = intent.getParcelableExtra<Essay>("userEssay")
        val idEssay = intent.getStringExtra("essayId").toString()

        if (essay != null){
            binding.saveButton.setOnClickListener {
                val studentName: String = binding.edStudentName.text.toString()
                val studentNumber: String = binding.edStudentNumber.text.toString()
                val answer: String = binding.edStudentAnswer.text.toString()

                val essayId = try {idEssay.toInt()}catch (e:Exception){0}
                val numberStudent = try {studentNumber.toInt()}catch (e:Exception){0}

                val newStudentAnswer = StudentAnswer(idEssay = essayId, studentName = studentName, studentNumber = numberStudent, answer = answer)

                insertStudentAnswer(newStudentAnswer,essay)
                Log.d(TAG, newStudentAnswer.toString())
            }
        }
    }

    private fun insertStudentAnswer(studentAnswer: StudentAnswer, essay: Essay){
        viewModel.insertStudentAnswer(studentAnswer)
        Toast.makeText(this, getString(R.string.notif_saved), Toast.LENGTH_LONG).show()
        val intent = Intent(this, ListStudentAnswerActivity::class.java)
        intent.putExtra("userEssay", essay)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}