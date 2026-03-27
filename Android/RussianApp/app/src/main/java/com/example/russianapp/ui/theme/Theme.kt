package com.example.russianapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val RussianColorScheme = lightColorScheme(
    primary = RussianBlue,
    secondary = RussianRed,
    tertiary = RussianBlueLight,
    onPrimary = OnBlue,
    onSecondary = OnRed
)

@Composable
fun RussianAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = RussianColorScheme,
        typography = Typography,
        content = content
    )
}
