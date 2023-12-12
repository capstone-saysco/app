package com.example.saysco.ui.newscoring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.saysco.R
import com.example.saysco.data.model.Question
import com.example.saysco.data.model.Scoring
import com.example.saysco.databinding.ActivityNewScoringBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.newscoring.question.list.QuestionActivity

class NewScoringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewScoringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewScoringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "New Scoring"

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this@NewScoringActivity)
        val viewModel: NewScoringViewModel by viewModels {
            factory
        }

        val idUser = intent.getStringExtra("idUser").toString()
        val fromPage = intent.getStringExtra("fromPage").toString()

        if (fromPage == "Home") {
            checkDraftScoring(idUser,viewModel)
        } else if (fromPage == "Listquestion") {
            viewModel.getLatestScoring(idUser).observe(this){ scoring->
                updateScoring(scoring, viewModel)
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

    private fun toQuestionPage(scoring: Scoring){
        val intent = Intent(this@NewScoringActivity, QuestionActivity::class.java)
        intent.putExtra("idScoring", scoring.id)
        startActivity(intent)
    }

    private fun checkDraftScoring(idUser: String, viewModel: NewScoringViewModel){
        viewModel.getLatestScoring(idUser).observe(this){scoring->
            if (scoring != null) {
                showDialog(scoring, idUser, viewModel)
            } else {
                with(binding){
                    saveButton.setOnClickListener {
                        val titleScoring: String = edNamescoring.text.toString()
                        val descriptionScoring: String = edDesccoring.text.toString()
                        val newScoring = Scoring(idUser = idUser ,titleScoring = titleScoring, descriptionScoring = descriptionScoring)
                        insertScoring(newScoring, viewModel)
                    }
                }
            }
        }
    }

    private fun showDialog(scoring: Scoring, idUser:String, viewModel: NewScoringViewModel) {
        AlertDialog.Builder(this).apply {
            setTitle("Scoring Confirmation")
            setMessage("There is a draft scoring that has not been continued, do you want to continue the previous draft?")
            setPositiveButton("Yes") { _, _ ->
                updateScoring(scoring, viewModel)
            }
            setNegativeButton("Delete") {_,_ ->
                deleteScoring(scoring, idUser ,viewModel)
            }
            create()
            show()
        }
    }

    private fun insertScoring(scoring: Scoring, viewModel: NewScoringViewModel){
        viewModel.insertScoring(scoring)
        Toast.makeText(this@NewScoringActivity, getString(R.string.notif_saved), Toast.LENGTH_LONG).show()
        toQuestionPage(scoring)
    }

    private fun updateScoring(scoring: Scoring, viewModel: NewScoringViewModel) {
        with(binding){
            val title = scoring.titleScoring
            val desc = scoring.descriptionScoring
            edNamescoring.setText(title)
            edDesccoring.setText(desc)
            saveButton.setText(getString(R.string.btn_update))
            saveButton.setOnClickListener {
                viewModel.updateScoring(scoring)
                toQuestionPage(scoring)
            }
        }
    }

    private fun deleteScoring(scoring: Scoring, idUser: String, viewModel: NewScoringViewModel) {
        val idScoring = scoring.id.toString()
        deleteQuestion(idScoring, viewModel)
        viewModel.deleteScoring(scoring)

        with(binding){
            saveButton.setOnClickListener {
                val titleScoring: String = edNamescoring.text.toString()
                val descriptionScoring: String = edDesccoring.text.toString()
                val newScoring = Scoring(idUser = idUser, titleScoring = titleScoring, descriptionScoring = descriptionScoring)
                insertScoring(newScoring, viewModel)
            }
        }
    }


    private fun deleteQuestion(idScoring: String, viewModel: NewScoringViewModel) {
        val questionsLiveData = viewModel.getQuestion(idScoring)
        questionsLiveData.observeForever { listQuestions ->
            if (listQuestions != null && listQuestions.isNotEmpty()) {
                deleteAnswer(listQuestions, viewModel)
                deleteKeyword(listQuestions,viewModel)
            }
            questionsLiveData.removeObserver {}
        }
        viewModel.deleteAllQuestions(idScoring)
    }

    private fun deleteAnswer(listQuestion: List<Question>, viewModel: NewScoringViewModel) {
        for (question in listQuestion) {
            val idQuestion = question.id.toString()
            viewModel.deleteAllStudentAnswers(idQuestion)
        }
    }

    private fun deleteKeyword(listQuestion: List<Question>, viewModel: NewScoringViewModel) {
        for (question in listQuestion) {
            val idQuestion = question.id.toString()
            viewModel.deleteAllKeyAnswer(idQuestion)
        }
    }

}