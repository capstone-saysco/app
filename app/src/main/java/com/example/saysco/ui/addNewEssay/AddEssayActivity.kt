package com.example.saysco.ui.addNewEssay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.saysco.R
import com.example.saysco.data.model.Essay
import com.example.saysco.databinding.ActivityAddEssayBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.addNewEssay.studentAnswer.listStudentAnswer.ListStudentAnswerActivity

class AddEssayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEssayBinding
    private val viewModel by viewModels<AddEssayViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEssayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "New Essay"

        val idUser = intent.getStringExtra("idUser").toString()
        val fromPage = intent.getStringExtra("fromPage").toString()

        if (fromPage == "Home") {
            checkDraftEssay(idUser)
        } else if (fromPage == "ListAnswer") {
            viewModel.getLatestEssay(idUser).observe(this){ essay->
                updateEssay(essay)
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

    private fun toStudentAnswerPage(essay: Essay){
        val intent = Intent(this, ListStudentAnswerActivity::class.java)
        intent.putExtra("userEssay", essay)
        startActivity(intent)
    }

    private fun checkDraftEssay(idUser: String){
        viewModel.getLatestEssay(idUser).observe(this){essay->
            if (essay != null) {
                showDialog(essay, idUser)
            } else {
                with(binding){
                    saveButton.setOnClickListener {
                        val questionEssay: String = edQuestionessay.text.toString()
                        val answerKeyEssay: String = edEdAnswerkeyessay.text.toString()
                        try {
                            val userId = idUser.toInt()
                            val newEssay = Essay(userId = userId, question = questionEssay, keyAnswer = answerKeyEssay)
                            insertEssay(newEssay)
                        } catch (e: NumberFormatException){
                            Toast.makeText(this@AddEssayActivity, e.toString() , Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun showDialog(essay: Essay, idUser:String) {
        AlertDialog.Builder(this).apply {
            setTitle("Essay Confirmation")
            setMessage("There is a draft essay that has not been continued, do you want to continue the previous draft?")
            setPositiveButton("Yes") { _, _ ->
                updateEssay(essay)
            }
            setNegativeButton("Delete") {_,_ ->
                deleteEssay(essay, idUser)
            }
            create()
            show()
        }
    }

    private fun insertEssay(essay: Essay){
        essay.id = 1
        viewModel.insertEssay(essay)
        Toast.makeText(this, getString(R.string.notif_saved), Toast.LENGTH_LONG).show()
        toStudentAnswerPage(essay)
    }

    private fun updateEssay(essay: Essay) {
        with(binding){
            val question = essay.question
            val keyAnswer = essay.keyAnswer
            edQuestionessay.setText(question)
            edEdAnswerkeyessay.setText(keyAnswer)
            saveButton.setText(getString(R.string.btn_update))
            saveButton.setOnClickListener {
                viewModel.updateEssay(essay)
                toStudentAnswerPage(essay)
            }
        }
    }

    private fun deleteEssay(essay: Essay, idUser: String) {
        val idEssay = essay.id.toString()
        deleteAnswerKey(idEssay)
        viewModel.deleteEssay(essay)

        with(binding){
            edQuestionessay.text?.clear()
            edEdAnswerkeyessay.text?.clear()

            saveButton.setOnClickListener {
                val questionEssay: String = edQuestionessay.text.toString()
                val answerKeyEssay: String = edEdAnswerkeyessay.text.toString()
                val userId: Int = idUser.toInt()
                val newEssay = Essay(userId = userId, question = questionEssay, keyAnswer = answerKeyEssay)
                insertEssay(newEssay)
            }
        }
    }


    private fun deleteAnswerKey(idEssay: String) {
        viewModel.deleteAllStudentAnswers(idEssay)
    }
}