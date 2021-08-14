package com.blackbox.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackbox.watchlist.responses.VerifyUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VerifyUserViewModel :ViewModel() {

    private val _liveDataVerifyResponse by lazy { MutableLiveData<VerifyUserResponse>() }
    val liveDataVerifyResponse : LiveData<VerifyUserResponse> = _liveDataVerifyResponse

    fun verifyUser(name: String){
        viewModelScope.launch {
            with(Dispatchers.IO){
                val response = RetrofitHelper.retrofit.create(ApiService::class.java).checkVerifiedStatus(name)
                if (response.isSuccessful){
                    _liveDataVerifyResponse.postValue(response.body())
                }

            }
        }
    }

}