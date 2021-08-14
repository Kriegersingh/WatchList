package com.blackbox.watchlist

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


object RetrofitHelper {
    val retrofit: Retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor())
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
        return@lazy Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(
                Json(JsonConfiguration.Stable.copy(ignoreUnknownKeys = true)).asConverterFactory("application/json".toMediaType())
            )
            .client(httpClient.build())
            .build()
    }
}