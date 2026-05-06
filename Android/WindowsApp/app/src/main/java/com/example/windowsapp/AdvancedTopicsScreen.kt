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
fun AdvancedTopicsScreen(navController: NavController) {
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
            text = "ADVANCED TOPICS",
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

        HackerButton("SYSTEM SERVICES") { navController.navigate("advanced_system_services") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("RPC AND WMI") { navController.navigate("advanced_rpc_wmi") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("PE FILE STRUCTURE AND LOADING") { navController.navigate("advanced_pe_file") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("HOW A DEBUGGER WORKS") { navController.navigate("how_a_debugger_works") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("ACCESS TOKENS AND IMPERSONATION") { navController.navigate("advanced_access_tokens") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("MEMORY AND PAGING") { navController.navigate("advanced_memory_paging") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("DLLs") { navController.navigate("advanced_dlls") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("OPPORTUNISTIC LOCKS") { navController.navigate("advanced_oplocks") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("REGISTRY") { navController.navigate("advanced_registry") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("PAGING I/O") { navController.navigate("advanced_paging_io") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("LSASS") { navController.navigate("advanced_lsass") }
        Spacer(modifier = Modifier.height(16.dp))
        HackerButton("WERFAULT") { navController.navigate("advanced_werfault") }

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
