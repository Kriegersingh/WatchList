package com.blackbox.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackbox.watchlist.responses.SearchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WatchListViewModel:ViewModel(){
    private val _liveDataWatchList by lazy { MutableLiveData<List<SearchData>>() }
    val liveDataWatchList :LiveData<List<SearchData>> = _liveDataWatchList


    fun getWatchList(name:String){
        viewModelScope.launch {
            with(Dispatchers.IO){
                val response = RetrofitHelper.retrofit.create(ApiService::class.java).getWatchList(name)
                if (response.isSuccessful){
                    response.body()?.let {
                        _liveDataWatchList.postValue(it)
                    }
                }
            }
        }
    }
}