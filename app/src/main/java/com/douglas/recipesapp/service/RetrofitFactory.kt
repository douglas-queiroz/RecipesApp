package com.douglas.recipesapp.service

import com.douglas.recipesapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

        private val httpClient = OkHttpClient
            .Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

        private val gson = GsonBuilder().create()

        fun create(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}