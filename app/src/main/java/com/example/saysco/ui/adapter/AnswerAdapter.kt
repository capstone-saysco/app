package com.example.saysco.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.data.remote.response.AnswerItem
import com.example.saysco.databinding.ItemAnswerBinding
import com.example.saysco.databinding.ItemStudentAnswerBinding
import com.example.saysco.databinding.ItemStudentAnswerEditBinding

class AnswerAdapter : ListAdapter<AnswerItem, AnswerAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val studentAnswer = getItem(position)
        if (studentAnswer != null) {
            holder.bind(studentAnswer)
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(studentAnswer) }
        }
    }

    inner class ViewHolder(private val binding: ItemAnswerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(studentAnswer: AnswerItem) {
            binding.tvItemStudentName.text = studentAnswer.studentName
            binding.tvItemStudentNumber.text = studentAnswer.studentNumber.toString()
            binding.tvItemAnswerStudent.text = studentAnswer.answer
            binding.tvScore.text = studentAnswer.score.toString()
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(studentAnswer: AnswerItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AnswerItem>() {
            override fun areItemsTheSame(oldItem: AnswerItem, newItem: AnswerItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AnswerItem, newItem: AnswerItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}