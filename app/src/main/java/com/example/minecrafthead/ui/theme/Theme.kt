package com.example.minecrafthead.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = PurplePrimary,
    secondary = PurpleGrey,
    background = BackgroundLight,
    onPrimary = Color.White,
    onBackground = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = PurplePrimary,
    secondary = PurpleGrey,
    background = BackgroundDark,
    onPrimary = Color.White,
    onBackground = Color.White
)

@Composable
fun MinecraftHeadTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
