package com.gka.myflickclient.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gka.myflickclient.R
import kotlinx.android.synthetic.main.item_search_history.view.*

class HistoryAdapter(var data: ArrayList<String>, private var mainPresenter: MainPresenter) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_search_history, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = data[position]

        holder.itemView.historyWord.text = word
        holder.itemView.setOnClickListener { mainPresenter.historyItemClicked(word) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateItems(data: ArrayList<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}