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

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("SYSTEM SERVICES", fontSize = 12.sp) { navController.navigate("advanced_system_services") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("RPC AND WMI", fontSize = 12.sp) { navController.navigate("advanced_rpc_wmi") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("PE FILE STRUCTURE\nAND LOADING", fontSize = 12.sp) { navController.navigate("advanced_pe_file") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("HOW A DEBUGGER WORKS", fontSize = 12.sp) { navController.navigate("how_a_debugger_works") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("ACCESS TOKENS AND IMPERSONATION", fontSize = 11.sp) { navController.navigate("advanced_access_tokens") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("MEMORY AND PAGING", fontSize = 12.sp) { navController.navigate("advanced_memory_paging") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("DLLs", fontSize = 12.sp) { navController.navigate("advanced_dlls") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("OPPORTUNISTIC LOCKS", fontSize = 12.sp) { navController.navigate("advanced_oplocks") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("REGISTRY", fontSize = 12.sp) { navController.navigate("advanced_registry") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("PAGING I/O", fontSize = 12.sp) { navController.navigate("advanced_paging_io") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("LSASS", fontSize = 12.sp) { navController.navigate("advanced_lsass") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("WERFAULT", fontSize = 12.sp) { navController.navigate("advanced_werfault") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("STARTUP", fontSize = 12.sp) { navController.navigate("advanced_startup") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("ELAM", fontSize = 12.sp) { navController.navigate("advanced_elam") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("PROTECTED AND PPL", fontSize = 12.sp) { navController.navigate("advanced_protected_ppl") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("DACL AND SDDL", fontSize = 12.sp) { navController.navigate("advanced_dacl_sddl") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.weight(1f)) { HackerButton("WAITING THREAD\nBEHIND THE SCENES", fontSize = 11.sp) { navController.navigate("advanced_waiting_thread") } }
            Box(modifier = Modifier.weight(1f)) { HackerButton("BEWARE THE\nREGISTRY", fontSize = 11.sp) { navController.navigate("advanced_beware_registry") } }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HackerButton("CERTIFICATE STORE", fontSize = 12.sp) { navController.navigate("advanced_certificate_store") }

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("< BACK") { navController.popBackStack() }
    }
}
