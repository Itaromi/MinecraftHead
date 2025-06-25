package com.example.minecrafthead.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "favorites")

class FavoritesRepository(private val context: Context) {

    companion object {
        private val FAVORITES_KEY = stringSetPreferencesKey("favorites")
    }

    val favorites: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[FAVORITES_KEY] ?: emptySet()
        }

    suspend fun addFavorite(pseudo: String) {
        context.dataStore.edit { preferences ->
            val current = preferences[FAVORITES_KEY] ?: emptySet()
            preferences[FAVORITES_KEY] = current + pseudo
        }
    }

    suspend fun removeFavorite(pseudo: String) {
        context.dataStore.edit { preferences ->
            val current = preferences[FAVORITES_KEY] ?: emptySet()
            preferences[FAVORITES_KEY] = current - pseudo
        }
    }
}
