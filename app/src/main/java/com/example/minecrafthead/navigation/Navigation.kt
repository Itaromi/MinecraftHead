package com.example.minecrafthead.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minecrafthead.ui.favorites.FavoritesScreen
import com.example.minecrafthead.ui.home.HomeScreen
import com.example.minecrafthead.ui.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


// Déclaration des routes
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorites : Screen("favorites")
}

// Navigation principale
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: HomeViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(viewModel = viewModel, navController = navController)
        }
    }
}

