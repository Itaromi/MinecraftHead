package com.example.minecrafthead.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.minecrafthead.data.repository.FavoritesRepository
import com.example.minecrafthead.data.repository.PlayerRepository
import com.example.minecrafthead.utils.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val favoritesRepository = FavoritesRepository(application)
    private val playerRepository = PlayerRepository()

    private val _avatar = MutableStateFlow<Resource<android.graphics.Bitmap>?>(null)
    val avatar: StateFlow<Resource<android.graphics.Bitmap>?> = _avatar

    val favorites: StateFlow<Set<String>> = favoritesRepository.favorites.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptySet()
    )

    val maxFavorites = 5

    fun loadAvatar(username: String) {
        viewModelScope.launch {
            _avatar.value = Resource.Loading()
            _avatar.value = playerRepository.fetchAvatar(username)
        }
    }

    fun canAddFavorite(): Boolean {
        return favorites.value.size < maxFavorites
    }

    fun addFavorite(username: String): Boolean {
        return if (canAddFavorite()) {
            viewModelScope.launch {
                favoritesRepository.addFavorite(username)
            }
            true
        } else {
            false
        }
    }

    fun removeFavorite(username: String) {
        viewModelScope.launch {
            favoritesRepository.removeFavorite(username)
        }
    }
}
