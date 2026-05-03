package com.example.windowsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.windowsapp.ui.theme.HackerGreen
import com.example.windowsapp.ui.theme.HackerGreenDim

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        color = HackerGreen,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun BodyText(text: String) {
    Text(
        text = text,
        color = HackerGreen,
        fontFamily = FontFamily.Monospace,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CodeBlock(code: String) {
    Text(
        text = code,
        color = Color(0xFFFFFFFF),
        fontFamily = FontFamily.Monospace,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0A0A0A))
            .border(1.dp, HackerGreenDim)
            .padding(8.dp)
    )
}
