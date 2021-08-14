package com.blackbox.watchlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.blackbox.watchlist.responses.SearchData
import kotlinx.android.synthetic.main.item_watch_list.view.*

class SearchListViewholder(view :View):RecyclerView.ViewHolder(view) {

    fun bindData(data :SearchData){
        with(itemView){
            repoName?.text =data.name
            ownerName?.text = data.owner?.login
            starCount?.text = data.starGazersCount?.toString()
            watcherCount?.text = data.watchersCount?.toString()
        }
    }
}