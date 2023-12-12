package com.example.saysco.ui.newscoring.question.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saysco.data.model.Question
import com.example.saysco.databinding.ActivityQuestionBinding
import com.example.saysco.ui.adapter.QuestionAdapter
import com.example.saysco.ui.newscoring.question.ViewModelFactory
import com.example.saysco.ui.newscoring.question.add.AddQuestionActivity

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Question"

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this@QuestionActivity)
        val viewModel: QuestionViewModel by viewModels {
            factory
        }
        val idScoring = intent.getStringExtra("idScoring").toString()


        binding.rcQuestion.layoutManager = LinearLayoutManager(this)

        binding.addQuestionButton.setOnClickListener {
            val intent = Intent(this@QuestionActivity, AddQuestionActivity::class.java)
            intent.putExtra("idScoring", idScoring)
            startActivity(intent)
        }

    }
    fun getData(idScoring:String, viewModel: QuestionViewModel){
        val adapter = QuestionAdapter()
        viewModel.getQuestion(idScoring).observe(this) { questions->
            binding.progressBar.visibility = View.INVISIBLE
            adapter.submitList(questions)
            adapter.setOnItemClickCallback(object : QuestionAdapter.OnItemClickCallback {
                override fun onItemClicked(question: Question) {
//                    val intentToAddAnswer = Intent(this@QuestionActivity, StudentAnswerActivity::class.java)
//                    intentToAddAnswer.putExtra("DATA", question)
//                    startActivity(intentToAddAnswer)
                }
            })
        }
    }
}