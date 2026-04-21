package com.example.czechapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationsScreen(
    onBack: () -> Unit,
    onDirections: () -> Unit,
    onRestaurant: () -> Unit,
    onBeach: () -> Unit,
    onTechMeeting: () -> Unit,
    onJob: () -> Unit,
    onAirport: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conversation Examples") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Button(onClick = onDirections, modifier = Modifier.fillMaxWidth()) { Text("Asking For Directions") } }
            item { Button(onClick = onRestaurant, modifier = Modifier.fillMaxWidth()) { Text("At The Restaurant") } }
            item { Button(onClick = onBeach, modifier = Modifier.fillMaxWidth()) { Text("At The Beach") } }
            item { Button(onClick = onTechMeeting, modifier = Modifier.fillMaxWidth()) { Text("Tech Meeting") } }
            item { Button(onClick = onJob, modifier = Modifier.fillMaxWidth()) { Text("Applying For A Job") } }
            item { Button(onClick = onAirport, modifier = Modifier.fillMaxWidth()) { Text("At The Airport") } }
        }
    }
}
