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
fun FileSystemMinifiltersScreen(navController: NavController) {
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
            text = "FILESYSTEM MINIFILTERS",
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

        HackerButton("HISTORY AND OVERVIEW") { navController.navigate("kernel_fsmf_history") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("ALTITUDE") { navController.navigate("kernel_fsmf_altitude") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("BASIC MINIFILTER") { navController.navigate("kernel_fsmf_basic") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("PRE AND POST OPERATION") { navController.navigate("kernel_fsmf_pre_post") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("WHAT IS THE RAW DISK?") { navController.navigate("kernel_fsmf_raw_disk") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("NAMED PIPES") { navController.navigate("kernel_fsmf_named_pipes") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("MUP FS") { navController.navigate("kernel_fsmf_mup") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("REPARSE POINT") { navController.navigate("kernel_fsmf_reparse_point") }

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
    }
}
