package com.example.czechapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val APP_VERSION = "1.0"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onOpenTutorial: () -> Unit,
    onOpenDictionary: () -> Unit,
    onOpenConfig: () -> Unit,
    onOpenVerbQuiz: () -> Unit,
    onOpenVocabEngToCzech: () -> Unit,
    onOpenVocabCzechToEng: () -> Unit,
    onOpenConversations: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                actions = {
                    IconButton(onClick = onOpenConfig) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "A Stranger In A Strange Land",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Czech Edition",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedButton(
                onClick = onOpenVerbQuiz,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Verb Quiz") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenVocabEngToCzech,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Vocabulary Quiz: Eng → Czech") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenVocabCzechToEng,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Vocabulary Quiz: Czech → Eng") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenTutorial,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Learning The Language") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenDictionary,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Simple Dictionary") }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(
                onClick = onOpenConversations,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Conversation Examples") }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "v$APP_VERSION",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            )
        }
    }
}
