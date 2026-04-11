package com.example.frenchproject.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val FrenchColorScheme = lightColorScheme(
    primary = FrenchBlue,
    onPrimary = Color.White,
    secondary = FrenchRed,
    onSecondary = Color.White,
    tertiary = FrenchGold,
    onTertiary = FrenchNavy,
    background = FrenchLight,
    onBackground = FrenchNavy,
    surface = Color.White,
    onSurface = FrenchNavy,
)

@Composable
fun FrenchProjectTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = FrenchColorScheme,
        typography = Typography,
        content = content
    )
}
