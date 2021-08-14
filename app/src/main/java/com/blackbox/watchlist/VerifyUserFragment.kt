package com.blackbox.watchlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_verify_username.*

class VerifyUserFragment : Fragment() {

    private val viewModel by viewModels<VerifyUserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verify_username, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkAndNavigate()
        initViews()
        setObserver()
    }

    private fun initViews() {
        btnVerify?.setOnClickListener {
            if (usernameEt?.text.isNullOrEmpty()) {
                Toast.makeText(context, "Enter User Name", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.verifyUser(usernameEt?.text.toString())
            }
        }
    }

    private fun setObserver(){
        viewModel.liveDataVerifyResponse.observe(viewLifecycleOwner , Observer {res->
            res.login?.let {
                with(activity?.getPreferences(Context.MODE_PRIVATE)?.edit()){
                    this?.putString("userName" ,it )
                    this?.apply()
                }
                checkAndNavigate()
            }
        })
    }


    private fun checkAndNavigate(){
        val userName = activity?.getPreferences(Context.MODE_PRIVATE)?.getString("userName","")

        if (!userName.isNullOrEmpty()){
            findNavController().navigate(R.id.action_verify_to_home)
        }
    }
}