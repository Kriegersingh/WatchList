package com.blackbox.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blackbox.watchlist.responses.SearchResponse
import kotlinx.android.synthetic.main.fragment_watchlist.*

class SearchListFragment : Fragment() {

    private val adapter by lazy { SearchListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etSearch?.visibility = View.GONE
        arguments?.getParcelable<SearchResponse>("search data")?.items?.let {
            watchListRecycler?.adapter = adapter
            adapter.addData(it)
        }
    }


}