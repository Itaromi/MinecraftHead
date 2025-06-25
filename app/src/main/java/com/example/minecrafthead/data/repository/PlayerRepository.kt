package com.example.minecrafthead.data.repository

import android.graphics.BitmapFactory
import com.example.minecrafthead.data.api.RetrofitInstance
import com.example.minecrafthead.utils.Resource

class PlayerRepository {
    suspend fun fetchAvatar(username: String): Resource<android.graphics.Bitmap> {
        return try {
            val response = RetrofitInstance.api.getAvatar(username)
            if (response.isSuccessful && response.body() != null) {
                val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
                Resource.Success(bitmap)
            } else {
                Resource.Error("Erreur : ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Exception : ${e.message}")
        }
    }
}
