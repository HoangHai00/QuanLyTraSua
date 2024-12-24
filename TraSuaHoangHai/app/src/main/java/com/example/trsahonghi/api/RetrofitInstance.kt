package com.example.trsahonghi.api

import com.example.trsahonghi.ui.myapplication.MyApplication
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://appbanhang.somee.com/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val tokenInterceptor = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder().apply {
            val token = TokenManager.getToken(MyApplication.context)
            token?.let {
                addHeader("Authorization", "Bearer $it")
            }
        }
        chain.proceed(requestBuilder.build())
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}


