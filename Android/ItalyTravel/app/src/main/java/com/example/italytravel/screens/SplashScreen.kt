package com.example.italytravel.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.italytravel.R
import kotlinx.coroutines.delay

/** Shown for 3 seconds when the app starts, then goes to the Main screen. */
@Composable
fun SplashScreen(onDone: () -> Unit) {
    Image(
        painter = painterResource(R.drawable.app_splash),
        contentDescription = "Italy Family Adventure",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    LaunchedEffect(Unit) {
        delay(3000)
        onDone()
    }
}
