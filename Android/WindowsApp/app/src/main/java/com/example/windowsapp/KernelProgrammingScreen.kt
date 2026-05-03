package com.example.windowsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.windowsapp.ui.theme.HackerGreen

@Composable
fun KernelProgrammingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "KERNEL PROGRAMMING",
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "─".repeat(28),
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        HackerButton("INTERNAL DATA STRUCTURES") { navController.navigate("kernel_data_structures") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("IOCTL") { navController.navigate("kernel_ioctl") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("NT... VS ZW...") { navController.navigate("kernel_nt_zw") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("KERNEL DEBUGGING") { navController.navigate("kernel_debugging") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("SIMPLE DRIVER") { navController.navigate("kernel_simple_driver") }

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
