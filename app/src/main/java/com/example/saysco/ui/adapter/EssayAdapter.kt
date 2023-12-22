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

class EssayAdapter : ListAdapter<EssayItem, EssayAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEssayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val essay = getItem(position)
        if (essay != null) {
            holder.bind(essay)
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(essay) }
        }
    }

    inner class ViewHolder(private val binding: ItemEssayBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(essay: EssayItem) {
            with(binding){
                tvEssayQuestion.text = essay.question
                tvEssayKeyAnswer.text = essay.keyAnswer
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(essay: EssayItem)
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