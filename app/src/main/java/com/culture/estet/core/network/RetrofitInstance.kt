package com.culture.estet.core.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val logger by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    private val retrofit = Retrofit.Builder()
        .baseUrl("BASE_URL")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
        .build()


    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}