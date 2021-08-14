package com.blackbox.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackbox.watchlist.responses.SearchData
import com.blackbox.watchlist.responses.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeVieModel:ViewModel() {
    private val _liveDataSearchResponse by lazy { MutableLiveData<SearchResponse>() }
    val liveDataSearchResponse :LiveData<SearchResponse> = _liveDataSearchResponse

    fun searchRepo(key:String){
        viewModelScope.launch {
            with(Dispatchers.IO){
                val response = RetrofitHelper.retrofit.create(ApiService::class.java).getRepoList(key)
                if (response.isSuccessful){
                    _liveDataSearchResponse.postValue(response.body())
                }
            }
        }
    }


}