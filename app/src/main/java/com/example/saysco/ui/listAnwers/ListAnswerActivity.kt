package com.example.saysco.ui.listAnwers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saysco.R
import com.example.saysco.data.Result
import com.example.saysco.data.model.Essay
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.data.remote.response.AnswerItem
import com.example.saysco.data.remote.response.EssayItem
import com.example.saysco.databinding.ActivityListAnswerBinding
import com.example.saysco.databinding.ActivityListStudentAnswerBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.adapter.EssayAdapter
import com.example.saysco.ui.adapter.StudentAnswerAdapter
import com.example.saysco.ui.addNewEssay.AddEssayActivity
import com.example.saysco.ui.addNewEssay.studentAnswer.listStudentAnswer.ListStudentAnswerViewModel
import com.example.saysco.ui.login.LoginActivity
import com.google.android.material.snackbar.Snackbar

class ListAnswerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListAnswerBinding
    private val viewModel by viewModels<ListAnswerViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Student Answer"

        val essay = intent.getParcelableExtra<EssayItem>("userEssay")

        if (essay != null) {
            setupUI(essay)
        } else {
            // Handle the case where essay is null
            showAlert("Invalid essay data")
            finish()
        }
    }

    private fun setupUI(essay: EssayItem) {
        binding.tvQuestion.text = essay.question
        binding.tvAnswer.text = essay.keyAnswer

        viewModel.getSession().observe(this) { user ->
            if (user.token != null) {
                val essayId = essay.id
                setupRecyclerView()

                viewModel.answers.observe(this) { result ->
                    when (result) {
                        is Result.Loading -> showLoading(true)
                        is Result.Success -> {
                            showLoading(false)
                            val answers = result.data.data.map { it.toStudentAnswer() }
                            updateRecyclerView(answers)
                        }
                        is Result.Error -> {
                            showLoading(false)
                            showAlert("Error loading answers")
                        }
                    }
                }

                viewModel.loadAnswers(user.token, essayId.toString())

            } else {
                val intent = Intent(this@ListAnswerActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun updateRecyclerView(answers: List<StudentAnswer>) {
        val adapter = StudentAnswerAdapter()
        adapter.submitList(answers)
        binding.rcStudentAnswer.adapter = adapter
    }

    private fun setupRecyclerView() {
        binding.rcStudentAnswer.layoutManager = LinearLayoutManager(this)
        binding.rcStudentAnswer.setHasFixedSize(true)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showAlert(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
    private fun AnswerItem.toStudentAnswer(): StudentAnswer {
        return StudentAnswer(
            studentName = this.studentName,
            score = this.score,
            idEssay = this.essayId,
            studentNumber = this.studentNumber,
            answer = this.answer,
            id = this.id
        )
    }
}
