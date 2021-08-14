package com.blackbox.watchlist

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.blackbox.watchlist.responses.SearchData
import kotlinx.android.synthetic.main.fragment_watchlist.*

class WatchListFragment : Fragment() {

    private val viewModel by viewModels<WatchListViewModel>()
    private val adapter by lazy { WatchListAdapter() }

    private var watchList : List<SearchData>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userName = activity?.getPreferences(Context.MODE_PRIVATE)?.getString("userName","")
        userName?.let {
            viewModel.getWatchList(it)
        }
        setObservers()
        addSearch()
    }
    private fun setObservers(){
        viewModel.liveDataWatchList.observe(viewLifecycleOwner , Observer {res->
            watchList =res
            watchListRecycler?.adapter = adapter
            adapter.addData(res)
        })
    }


    private fun addSearch(){
        etSearch?.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if (p0?.toString()?.isNotEmpty()==true){
                    watchList?.filter {
                            it.name?.contains(p0.toString()) == true
                    }
                    watchList?.let {
                        adapter.addData(it)
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
    }
}