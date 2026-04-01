package com.example.linuxapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val LinuxColorScheme = darkColorScheme(
    primary = TerminalGreen,
    secondary = TerminalGreenDark,
    tertiary = TerminalGrey,
    background = TerminalBlack,
    surface = TerminalGrey,
    onPrimary = TerminalBlack,
    onSecondary = TerminalBlack,
    onBackground = TerminalWhite,
    onSurface = TerminalWhite,
)

@Composable
fun LinuxAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LinuxColorScheme,
        typography = Typography,
        content = content
    )
}
