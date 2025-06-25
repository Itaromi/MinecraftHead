package com.example.minecrafthead.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

object RetrofitInstance {
    private val client = OkHttpClient.Builder().build()

    val api: McHeadsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://mc-heads.net/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(McHeadsApi::class.java)
    }
}
