package com.home.firebaseflowexampledemo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.home.firebaseflowexampledemo.databinding.ActivityMainRecyclerViewItemBinding
import com.home.firebaseflowexampledemo.model.MainPojo

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var list: MutableList<MainPojo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityMainRecyclerViewItemBinding
            .inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userBriefPojo = list[position]
        holder.bindData(userBriefPojo)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<MainPojo>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(binding: ActivityMainRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val nameTextView: TextView = binding.textViewName
        private val payTextView: TextView = binding.textViewPay
        private val addressTextView: TextView = binding.textViewAddress

        fun bindData(userBriefPojo: MainPojo?) {
            userBriefPojo?.apply {
                nameTextView.text = name
                payTextView.text = pay
                addressTextView.text = address
            }
        }
    }
}