package com.example.saysco.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.databinding.ItemStudentAnswerEditBinding

class StudentAnswerEditAdapter : ListAdapter<StudentAnswer, StudentAnswerEditAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStudentAnswerEditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val studentAnswer = getItem(position)
        if (studentAnswer != null) {
            holder.bind(studentAnswer)
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(studentAnswer) }
            holder.editButton.setOnClickListener { onItemClickCallback.onEditButtonClicked(studentAnswer) }
            holder.deleteButton.setOnClickListener { onItemClickCallback.onDeleteButtonClicked(studentAnswer) }
        }
    }

    inner class ViewHolder(private val binding: ItemStudentAnswerEditBinding) : RecyclerView.ViewHolder(binding.root) {
        val editButton = binding.editButton
        val deleteButton = binding.deleteButton
        fun bind(studentAnswer: StudentAnswer) {
            binding.tvItemStudentName.text = studentAnswer.studentName
            binding.tvItemStudentNumber.text = studentAnswer.studentNumber.toString()
            binding.tvItemAnswerStudent.text = studentAnswer.answer
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(studentAnswer: StudentAnswer)
        fun onEditButtonClicked(studentAnswer: StudentAnswer)
        fun onDeleteButtonClicked(studentAnswer: StudentAnswer)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StudentAnswer>() {
            override fun areItemsTheSame(oldItem: StudentAnswer, newItem: StudentAnswer): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: StudentAnswer, newItem: StudentAnswer): Boolean {
                return oldItem == newItem
            }
        }
    }
}