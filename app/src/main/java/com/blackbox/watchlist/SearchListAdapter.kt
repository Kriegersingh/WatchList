package com.blackbox.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackbox.watchlist.responses.SearchData

class SearchListAdapter:RecyclerView.Adapter<SearchListViewholder>() {
    private val list =ArrayList<SearchData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_list ,parent,false)
        return SearchListViewholder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addData(list: List<SearchData>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SearchListViewholder, position: Int) {
        holder.bindData(list[position])
    }
}