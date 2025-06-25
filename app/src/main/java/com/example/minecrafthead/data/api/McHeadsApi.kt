package com.example.minecrafthead.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface McHeadsApi {
    @GET("avatar/{username}/128")
    suspend fun getAvatar(@Path("username") username: String): Response<ResponseBody>
}
