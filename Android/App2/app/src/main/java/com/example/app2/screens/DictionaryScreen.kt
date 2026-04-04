package com.example.app2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.app2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    onBack: () -> Unit,
    onCommonVerbs: () -> Unit,
    onTimeExpressions: () -> Unit,
    onNumbers: () -> Unit,
    onColors: () -> Unit,
    onAdjectives: () -> Unit,
    onAdverbs: () -> Unit,
    onCommonWords: () -> Unit,
    onMovement: () -> Unit,
    onSomeNoneAll: () -> Unit,
    onConfusingVerbs: () -> Unit,
    onTechWords: () -> Unit,
    onGreetings: () -> Unit,
    onMath: () -> Unit,
    onConnectors: () -> Unit,
    onVacation: () -> Unit,
    onWorkplaces: () -> Unit
) {
    val buttons = listOf(
        "Common Verbs" to onCommonVerbs,
        "Time Expressions" to onTimeExpressions,
        "Numbers" to onNumbers,
        "Colors" to onColors,
        "Adjectives" to onAdjectives,
        "Adverbs" to onAdverbs,
        "Common Words" to onCommonWords,
        "Movement" to onMovement,
        "Some, None, All" to onSomeNoneAll,
        "Slightly Confusing Verbs" to onConfusingVerbs,
        "Tech Vocabulary" to onTechWords,
        "Greetings" to onGreetings,
        "Math" to onMath,
        "Yet, Already & Others" to onConnectors,
        "Vacation" to onVacation,
        "Workplaces & Jobs" to onWorkplaces
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.portugal_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                ButtonGrid(buttons)
            }
        }
    }
}

@Composable
private fun ButtonGrid(buttons: List<Pair<String, () -> Unit>>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        buttons.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { (label, onClick) ->
                    Button(
                        onClick = onClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = label,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
                if (row.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
