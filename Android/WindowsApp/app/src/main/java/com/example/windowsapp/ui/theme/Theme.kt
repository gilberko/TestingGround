package com.example.windowsapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val HackerColorScheme = darkColorScheme(
    primary = HackerGreen,
    onPrimary = Color.Black,
    background = Color.Black,
    onBackground = HackerGreen,
    surface = Color.Black,
    onSurface = HackerGreen,
    secondary = HackerGreenDim,
    onSecondary = Color.Black,
    tertiary = HackerRed,
    onTertiary = Color.Black,
)

@Composable
fun WindowsAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = HackerColorScheme,
        typography = Typography,
        content = content
    )
}
