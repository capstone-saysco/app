package com.example.saysco.ui.addNewEssay.studentAnswer.editStudentAnswer

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.saysco.R
import com.example.saysco.data.model.Essay
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.databinding.ActivityAddStudentAnswerBinding
import com.example.saysco.databinding.ActivityEditStudentAnswerBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.addNewEssay.studentAnswer.editStudentAnswer.EditStudentAnswerViewModel
import com.example.saysco.ui.addNewEssay.studentAnswer.listStudentAnswer.ListStudentAnswerActivity

class EditStudentAnswerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentAnswerBinding

    private val viewModel by viewModels<EditStudentAnswerViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Edit Student Answer"

        val essay = intent.getParcelableExtra<Essay>("userEssay")
        val data = intent.getParcelableExtra<StudentAnswer>("studentAnswer")

        if (essay != null){
            if (data != null) {
                val idAnswer = data.idEssay.toString()
                val idEssay = data.idEssay.toString()
                val dataNumberStudent = data.studentNumber?.toString() ?: ""
                val convertNumberStudent = try { dataNumberStudent.toInt() } catch (e: Exception) { 0 }

                binding.edStudentName.setText(data.studentName ?: "")
                binding.edStudentNumber.setText(convertNumberStudent.toString())
                binding.edStudentAnswer.setText(data.answer ?: "")

                binding.saveButton.setOnClickListener {
                    val studentName: String = binding.edStudentName.text.toString()
                    val studentNumber: String = binding.edStudentNumber.text.toString()
                    val answer: String = binding.edStudentAnswer.text.toString()

                    val essayId = try { idEssay.toInt() } catch (e: Exception) { 0 }
                    val answerId = try { idAnswer.toInt() } catch (e: Exception) { 0 }
                    val numberStudent = try { studentNumber.toInt() } catch (e: Exception) { 0 }

                    data.studentName = studentName
                    data.studentNumber = numberStudent
                    data.answer = answer

                    updateStudentAnswer(data, essay)
                    Log.d(ContentValues.TAG, data.toString())
                }
            }
        }
    }
    private fun updateStudentAnswer(studentAnswer: StudentAnswer, essay: Essay){
        viewModel.updateStudentAnswer(studentAnswer)
        Toast.makeText(this, getString(R.string.notif_saved), Toast.LENGTH_LONG).show()
        val intent = Intent(this, ListStudentAnswerActivity::class.java)
        intent.putExtra("userEssay", essay)
        startActivity(intent)
        finish()
    }
}