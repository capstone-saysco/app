package com.example.saysco.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.saysco.data.model.Question
import com.example.saysco.data.repository.KeyAnswerRepository
import com.example.saysco.databinding.ItemQuestionBinding

class KeywordAdapter : ListAdapter<Question, KeywordAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var keyAnswerRepository: KeyAnswerRepository

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = getItem(position)
        if (question != null) {
            holder.bind(question)
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(question) }
        }
    }

    inner class ViewHolder(private val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question) {
            binding.tvItemQuestion.text = question.question
            val idQuestion = question.id.toString()
            keyAnswerRepository.getAllKeyAnswers(idQuestion).observe(binding.root.context as LifecycleOwner){ itemQuestion ->
                if (!itemQuestion.isNullOrEmpty()) {
                    val keyword = itemQuestion[0].keyword
                    binding.tvItemKeyword.text = keyword
                } else {
                    binding.tvItemKeyword.text = "No keyword"
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(question: Question)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Question>() {
            override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
                return oldItem == newItem
            }
        }
    }
}