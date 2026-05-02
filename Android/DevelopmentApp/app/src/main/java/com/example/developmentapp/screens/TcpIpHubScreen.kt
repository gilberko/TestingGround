package com.example.developmentapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TcpIpHubScreen(
    onBack:     () -> Unit,
    onEthernet: () -> Unit,
    onIp:       () -> Unit,
    onIcmp:     () -> Unit,
    onUdp:      () -> Unit,
    onTcp:      () -> Unit,
    onDns:      () -> Unit,
    onIpsec:    () -> Unit,
    onSslTls:   () -> Unit,
    onVpn:      () -> Unit,
    onProxy:    () -> Unit,
    onArp:      () -> Unit,
    onDhcp:     () -> Unit,
    onQuic:     () -> Unit,
    onSmtp:     () -> Unit,
    onHttp:     () -> Unit,
    onFtp:      () -> Unit,
    onTftp:     () -> Unit,
    onWifi:     () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "TCP/IP",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))
            TcpIpButtonRow("Ethernet",  onEthernet,
                           "IP",        onIp)
            Spacer(Modifier.height(12.dp))
            TcpIpButtonRow("ICMP",      onIcmp,
                           "UDP",       onUdp)
            Spacer(Modifier.height(12.dp))
            TcpIpButtonRow("TCP",       onTcp,
                           "DNS",       onDns)
            Spacer(Modifier.height(12.dp))
            TcpIpButtonRow("IPSec",     onIpsec,
                           "SSL/TLS",   onSslTls)
            Spacer(Modifier.height(12.dp))
            TcpIpButtonRow("VPN",       onVpn,
                           "Proxy",     onProxy)
            Spacer(Modifier.height(12.dp))
            TcpIpButtonRow("ARP",       onArp,
                           "DHCP",      onDhcp)
            Spacer(Modifier.height(12.dp))
            TcpIpButtonRow("QUIC",      onQuic,
                           "SMTP",      onSmtp)
            Spacer(Modifier.height(12.dp))
            TcpIpButtonRow("HTTP",      onHttp,
                           "FTP",       onFtp)
            Spacer(Modifier.height(12.dp))
            TcpIpButtonRow("TFTP",      onTftp,
                           "WiFi",      onWifi)
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TcpIpButtonRow(
    label1: String, onClick1: () -> Unit,
    label2: String, onClick2: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        TcpIpHubButton(label1, onClick1, Modifier.weight(1f))
        TcpIpHubButton(label2, onClick2, Modifier.weight(1f))
    }
}

@Composable
private fun TcpIpHubButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick  = onClick,
        modifier = modifier.height(52.dp),
        border = BorderStroke(1.dp, Color(0xFF00FF41)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Black,
            contentColor   = Color(0xFF00FF41)
        )
    ) {
        Text(
            text       = label,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Medium,
            color      = Color(0xFF00FF41),
            textAlign  = TextAlign.Center,
            fontSize   = 11.sp
        )
    }
}
