package com.example.saysco.ui.addNewEssay.confirmation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saysco.R
import com.example.saysco.data.Result
import com.example.saysco.data.model.Essay
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.databinding.ActivityConfirmationBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.adapter.StudentAnswerAdapter
import com.example.saysco.ui.adapter.StudentAnswerEditAdapter
import com.example.saysco.ui.addNewEssay.statusResult.StatusResultActivity
import com.example.saysco.ui.addNewEssay.studentAnswer.editStudentAnswer.EditStudentAnswerActivity
import com.example.saysco.ui.addNewEssay.studentAnswer.listStudentAnswer.ListStudentAnswerActivity
import com.google.android.material.snackbar.Snackbar

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding

    private val viewModel by viewModels<ConfirmationViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Confirmation"

        val essay = intent.getParcelableExtra<Essay>("userEssay")

        if (essay != null){
            val essayId = essay.id.toString()
            val question = essay.question.toString()
            val keyAnswer = essay.keyAnswer.toString()
            binding.tvQuestion.setText(question)
            binding.tvAnswerKey.setText(keyAnswer)

            binding.rcStudentAnswer.layoutManager = LinearLayoutManager(this)
            binding.rcStudentAnswer.setHasFixedSize(true)


            viewModel.getSession().observe(this){userSession->
                if (userSession != null){
                    val token = userSession.token.toString()
                    val userId = userSession.token.toString()
                    binding.saveButton.setOnClickListener {
                        sendEssay(token, essayId,userId, question, keyAnswer, essay)
                    }
                }
            }
            getStudentAnswer(essayId)
        }
    }
    private fun getStudentAnswer(essayId:String){
        val adapter = StudentAnswerAdapter()
        viewModel.getAllStudentAnswer(essayId).observe(this) { studentAnswer->
            if (studentAnswer != null){
                binding.progressBar.visibility = View.INVISIBLE
                adapter.submitList(studentAnswer)
                binding.rcStudentAnswer.adapter = adapter
                adapter.setOnItemClickCallback(object : StudentAnswerAdapter.OnItemClickCallback {
                    override fun onItemClicked(studentAnswer: StudentAnswer) {
//                        val intentToEdit = Intent(this@ConfirmationActivity, EditStudentAnswerActivity::class.java)
//                        intentToEdit.putExtra("Answer", studentAnswer)
//                        startActivity(intentToEdit)
                    }
                })
            }
        }
    }

    private fun sendEssay(token:String, essayIdlocal: String, userId:String, question:String, keyAnswer:String, essay: Essay){
        viewModel.sendEssay(token = token, userId = userId, question = question, keyAnswer = keyAnswer).observe(this){
            if (it != null) {
                when (it) {
                    Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Error -> {
                        showLoading(false)
                        showAlert(getString(R.string.login_error))
                    }

                    is Result.Success -> {
                        showLoading(false)
//                        showAlert(getString(R.string.login_success))
                        //Update ID Essay
                        val essayId = it.data.data.id.toString()
                        sendStudentAnswers(token,essayIdlocal,essayId, essay)
                    }

                }
            }
        }
    }

    private fun sendStudentAnswers(token: String, essayIdlocal: String, essayId: String, essay: Essay) {
        viewModel.getAllStudentAnswer(essayIdlocal).observe(this) { studentAnswer->
            if (studentAnswer != null) {
                var successfulResponses = 0
                for (student in studentAnswer) {
                    val name = student.studentName.toString()
                    val numberStudent = student.studentNumber.toString()
                    val answer = student.answer.toString()
                    val score = student.score.toString()
                    viewModel.sendStudentAnswer(token, essayId, name, numberStudent, answer, score).observe(this){
                        if (it != null) {
                            when (it) {
                                Result.Loading -> {
                                    showLoading(true)
                                }

                                is Result.Error -> {
                                    showLoading(false)
                                    showAlert(getString(R.string.login_error))
                                }

                                is Result.Success -> {
                                    successfulResponses++

                                    //Ambil ID Student Answer
                                    val studentAnswerId = it.data.data.id

                                    student.id = studentAnswerId
                                    //Predict Score Student Answer
                                    predictScore(token, student)

                                    if (successfulResponses == studentAnswer.size) {
                                        showLoading(false)
                                        deleteEssay(essay)
                                        showAlert(getString(R.string.notif_saved))
                                        val intent = Intent(this, StatusResultActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun predictScore(token: String, studentAnswer: StudentAnswer) {
        val answerId = studentAnswer.id.toString()
        val answer = studentAnswer.answer.toString()

        viewModel.predictStudentAnswer(token, answerId, answer).observe(this) {
            if (it != null) {
                when (it) {
                    Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Error -> {
                        showLoading(false)
                        showAlert(getString(R.string.login_error))
                    }

                    is Result.Success -> {
                        showLoading(false)
                        studentAnswer.score = it.data.data.scoreResult
                        updateStudentAnswer(token, studentAnswer)
                    }
                }
            }
        }
    }
    private fun updateStudentAnswer(token: String, studentAnswer: StudentAnswer){
        viewModel.updateStudentAnswer(token, studentAnswer).observe(this){
            if (it != null) {
                when (it) {
                    Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Error -> {
                        showLoading(false)
                        showAlert(getString(R.string.login_error))
                    }

                    is Result.Success -> {
                        showLoading(false)
                    }
                }
            }
        }
    }

    private fun deleteEssay(essay: Essay) {
        val idEssay = essay.id.toString()
        deleteAnswerKey(idEssay)
        viewModel.deleteEssay(essay)
    }


    private fun deleteAnswerKey(idEssay: String) {
        viewModel.deleteAllStudentAnswers(idEssay)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility =
            View.VISIBLE else binding.progressBar.visibility = View.GONE
    }

    private fun showAlert(string: String) {
        Snackbar.make(binding.root, string, Snackbar.LENGTH_LONG).show()
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