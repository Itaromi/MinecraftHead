package com.example.minecrafthead

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.minecrafthead.navigation.AppNavigation
import com.example.minecrafthead.ui.theme.MinecraftHeadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MinecraftHeadTheme {
                AppNavigation()
            }
        }
    }
}
