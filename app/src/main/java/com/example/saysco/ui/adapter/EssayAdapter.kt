package com.example.saysco.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.saysco.data.model.Essay
import com.example.saysco.data.remote.response.EssayItem
import com.example.saysco.databinding.ItemEssayBinding
import com.example.saysco.databinding.ItemStudentAnswerBinding

class EssayAdapter : ListAdapter<EssayItem, EssayAdapter.ListViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: EssayAdapter.ListViewHolder, position: Int) {
        val essays = getItem(position)
        if (essays != null) {
            holder.bind(essays)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EssayAdapter.ListViewHolder {
        val binding = ItemEssayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    inner class ListViewHolder(private val binding: ItemEssayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(essay: EssayItem) {
            with(binding){
                tvEssayQuestion.text = essay.question
                tvEssayKeyAnswer.text = essay.keyAnswer
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EssayItem>() {
            override fun areItemsTheSame(oldItem: EssayItem, newItem: EssayItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: EssayItem, newItem: EssayItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}