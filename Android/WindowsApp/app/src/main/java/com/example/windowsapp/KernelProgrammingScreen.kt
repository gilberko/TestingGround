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

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("INTERNAL DATA STRUCTURES", fontSize = 13.sp) { navController.navigate("kernel_data_structures") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("IOCTL", fontSize = 13.sp) { navController.navigate("kernel_ioctl") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("NT... VS ZW...", fontSize = 13.sp) { navController.navigate("kernel_nt_zw") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("KERNEL DEBUGGING", fontSize = 13.sp) { navController.navigate("kernel_debugging") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("SIMPLE DRIVER", fontSize = 13.sp) { navController.navigate("kernel_simple_driver") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("PLUG AND PLAY", fontSize = 13.sp) { navController.navigate("kernel_pnp") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("REGISTERING FOR CALLBACKS", fontSize = 13.sp) { navController.navigate("kernel_callbacks") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("IRQL", fontSize = 13.sp) { navController.navigate("irql") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("FILTER DRIVERS", fontSize = 13.sp) { navController.navigate("kernel_filter_drivers") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("SYMBOLIC LINKS", fontSize = 13.sp) { navController.navigate("kernel_symbolic_links") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("THREADS", fontSize = 13.sp) { navController.navigate("kernel_threads") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("SYNCHRONIZATION", fontSize = 13.sp) { navController.navigate("kernel_synchronization") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("INTERRUPT HANDLING", fontSize = 13.sp) { navController.navigate("kernel_interrupt_handling") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("WFP", fontSize = 13.sp) { navController.navigate("kernel_wfp") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("FILESYSTEM MINIFILTERS", fontSize = 12.sp) { navController.navigate("kernel_fs_minifilters") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("APC", fontSize = 12.sp) { navController.navigate("kernel_apc") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("MEMORY ALLOCATION", fontSize = 12.sp) { navController.navigate("kernel_memory_allocation") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("BUGCHECKS AND DRIVER VERIFIER", fontSize = 12.sp) { navController.navigate("kernel_bugchecks") } }
        }

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
