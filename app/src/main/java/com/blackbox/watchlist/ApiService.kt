package com.blackbox.watchlist

import com.blackbox.watchlist.responses.SearchData
import com.blackbox.watchlist.responses.SearchResponse
import com.blackbox.watchlist.responses.VerifyUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/users/{username}")
    suspend fun checkVerifiedStatus(@Path("username") userName : String) : Response<VerifyUserResponse>

    @GET("/users/{username}/subscriptions")
    suspend fun getWatchList(@Path("username") userName : String) : Response<List<SearchData>>

    @GET("/search/repositories")
    suspend fun getRepoList(@Query("q") key : String) : Response<SearchResponse>
}