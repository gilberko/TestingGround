package com.example.windowsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun UserModeProgrammingScreen(navController: NavController) {
    HubBackground {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "USER MODE PROGRAMMING",
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

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("WRITING A DLL", fontSize = 12.sp) { navController.navigate("user_writing_dll") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("COM", fontSize = 12.sp) { navController.navigate("user_com") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("COMMUNICATING WITH DEVICE DRIVERS", fontSize = 11.sp) { navController.navigate("user_communicating_drivers") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("IPC", fontSize = 12.sp) { navController.navigate("user_ipc") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("THREADING AND SYNCHRONIZATION", fontSize = 11.sp) { navController.navigate("user_threading_sync") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("WINSOCK", fontSize = 12.sp) { navController.navigate("user_winsock") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("WRITING A SERVICE", fontSize = 12.sp) { navController.navigate("user_writing_service") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("IO COMPLETION PORT\nAND ASYNC OPS", fontSize = 11.sp) { navController.navigate("user_io_completion_port") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("UI PROGRAMMING", fontSize = 12.sp) { navController.navigate("user_ui_programming") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("MEMORY ALLOCATION", fontSize = 12.sp) { navController.navigate("user_memory_allocation") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("MFC", fontSize = 12.sp) { navController.navigate("user_mfc") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("ATL", fontSize = 12.sp) { navController.navigate("user_atl") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HackerButton("CALLING CONVENTIONS", fontSize = 12.sp) { navController.navigate("user_calling_conventions") }

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
    }
}
