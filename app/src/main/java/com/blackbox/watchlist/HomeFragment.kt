package com.blackbox.watchlist

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home_screen.*

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeVieModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
        val pref = activity?.getPreferences(Context.MODE_PRIVATE)
        val name = pref?.getString("userName", "")
        userName?.text = name
        changeUser?.setOnClickListener {
            pref?.edit()?.clear()?.apply()
            findNavController().navigate(R.id.action_home_to_verify)
        }

    }

    private fun setObservers() {
        viewModel.liveDataSearchResponse.observe(viewLifecycleOwner, Observer {
            val bundle = Bundle()
            bundle.putParcelable("search data", it)
            val inputMethodManager =
                context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
            findNavController().navigate(R.id.action_home_to_searchlist, bundle)
        })
    }


    private fun setListeners() {
        myWatchList?.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_watchlist)
        }
        searchBtn?.setOnClickListener {
            if (repoNameEt?.text.isNullOrEmpty()) {
                Toast.makeText(context, "Enter search keyword", Toast.LENGTH_SHORT).show()
            } else {
                repoNameEt?.text?.toString()?.let {
                    viewModel.searchRepo(repoNameEt?.text.toString())
                }
            }
        }

    }
}