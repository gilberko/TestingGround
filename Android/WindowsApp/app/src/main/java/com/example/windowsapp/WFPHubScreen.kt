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
fun WFPHubScreen(navController: NavController) {
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
            text = "WFP",
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

        HackerButton("OVERVIEW") { navController.navigate("kernel_wfp_overview") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("SUBLAYERS") { navController.navigate("kernel_wfp_sublayers") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("CLASSIFY AND NOTIFY") { navController.navigate("kernel_wfp_classify_notify") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("FLOWS") { navController.navigate("kernel_wfp_flows") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("ALE LAYERS") { navController.navigate("kernel_wfp_ale_layers") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("RAW SOCKETS") { navController.navigate("kernel_wfp_raw_sockets") }

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
    }
}
