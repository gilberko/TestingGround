package com.example.developmentapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DevColorScheme = darkColorScheme(
    primary      = TerminalGreen,
    secondary    = TerminalGreenDark,
    tertiary     = TerminalGrey,
    background   = TerminalBlack,
    surface      = TerminalGrey,
    onPrimary    = TerminalBlack,
    onSecondary  = TerminalBlack,
    onBackground = TerminalWhite,
    onSurface    = TerminalWhite,
)

@Composable
fun DevelopmentAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DevColorScheme,
        typography  = Typography,
        content     = content
    )
}
