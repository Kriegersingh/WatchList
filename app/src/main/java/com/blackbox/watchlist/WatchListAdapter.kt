package com.blackbox.watchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackbox.watchlist.responses.SearchData

class WatchListAdapter : RecyclerView.Adapter<WatchListViewHolder>() {
    private val list = ArrayList<SearchData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_watch_list,parent,false)
        return WatchListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addData(list: List<SearchData>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        holder.bindData(list[position])
    }
}