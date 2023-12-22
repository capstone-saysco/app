package com.example.saysco.ui.addNewEssay.studentAnswer.listStudentAnswer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saysco.R
import com.example.saysco.data.model.Essay
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.databinding.ActivityListStudentAnswerBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.adapter.StudentAnswerEditAdapter
import com.example.saysco.ui.addNewEssay.confirmation.ConfirmationActivity
import com.example.saysco.ui.addNewEssay.studentAnswer.addStudentAnswer.AddStudentAnswerActivity
import com.example.saysco.ui.addNewEssay.studentAnswer.editStudentAnswer.EditStudentAnswerActivity
import com.example.saysco.ui.main.MainActivity

class ListStudentAnswerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListStudentAnswerBinding

    private val viewModel by viewModels<ListStudentAnswerViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListStudentAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "List Student Answer"

        val essay = intent.getParcelableExtra<Essay>("userEssay")

        if (essay != null) {
            binding.rcStudentAnswer.layoutManager = LinearLayoutManager(this)
            binding.rcStudentAnswer.setHasFixedSize(true)

            essay.id = 1

            val userId = essay.userId.toString()
            val essayId = essay.id.toString()

            getStudentAnswer(essayId, essay)

            binding.addStudentAnswerButton.setOnClickListener {
                val intent = Intent(this, AddStudentAnswerActivity::class.java)
                intent.putExtra("essayId", essayId)
                intent.putExtra("userEssay", essay)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun getStudentAnswer(essayId: String, essay: Essay) {
        val adapter = StudentAnswerEditAdapter()
        viewModel.getAllStudentAnswer(essayId).observe(this@ListStudentAnswerActivity) { studentAnswer ->
            if (studentAnswer != null && studentAnswer.isNotEmpty()) {
                binding.progressBar.visibility = View.INVISIBLE
                adapter.submitList(studentAnswer)
                binding.rcStudentAnswer.adapter = adapter
                adapter.setOnItemClickCallback(object : StudentAnswerEditAdapter.OnItemClickCallback {
                    override fun onItemClicked(studentAnswer: StudentAnswer) {
                        val intentToEdit = Intent(this@ListStudentAnswerActivity, EditStudentAnswerActivity::class.java)
                        intentToEdit.putExtra("studentAnswer", studentAnswer)
                        intentToEdit.putExtra("userEssay", essay)
                        startActivity(intentToEdit)
                    }

                    override fun onEditButtonClicked(studentAnswer: StudentAnswer) {
                        val intentToEdit = Intent(this@ListStudentAnswerActivity, EditStudentAnswerActivity::class.java)
                        intentToEdit.putExtra("studentAnswer", studentAnswer)
                        intentToEdit.putExtra("userEssay", essay)
                        startActivity(intentToEdit)
                        finish()
                    }

                    override fun onDeleteButtonClicked(studentAnswer: StudentAnswer) {
                        viewModel.deleteStudentAnswer(studentAnswer)
                    }
                })
                binding.saveButton.setOnClickListener {
                    val intentToEdit = Intent(this@ListStudentAnswerActivity, ConfirmationActivity::class.java)
                    intentToEdit.putExtra("userEssay", essay)
                    startActivity(intentToEdit)
                }
            } else {
                binding.progressBar.visibility = View.INVISIBLE
                binding.tvNoData.visibility = View.VISIBLE
                binding.saveButton.isEnabled = false
                binding.saveButton.text = getString(R.string.add_student_answer_first)
                binding.saveButton.setTextColor(getColor(R.color.gray_light))
                binding.saveButton.setBackgroundColor(getColor(R.color.gray))
            }
        }
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