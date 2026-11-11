package com.example.app2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun TutorialScreen(
    onBack: () -> Unit,
    onVerbsAndTenses: () -> Unit,
    onPrepositions: () -> Unit,
    onPronunciation: () -> Unit,
    onMorePrepositions: () -> Unit,
    onParaPorA: () -> Unit,
    onCliticPronouns: () -> Unit,
    onComparisons: () -> Unit,
    onQuestions: () -> Unit,
    onDemonstratives: () -> Unit,
    onNegation: () -> Unit,
    onSomeNoneAll: () -> Unit,
    onConnectors: () -> Unit,
    onAdjectivesAdverbs: () -> Unit
) {
    val buttons = listOf(
        "Verbs And Tenses" to onVerbsAndTenses,
        "Prepositions" to onPrepositions,
        "Pronunciation" to onPronunciation,
        "More About Prepositions" to onMorePrepositions,
        "Para vs Por vs A" to onParaPorA,
        "Object Pronouns (Clitics)" to onCliticPronouns,
        "Comparisons" to onComparisons,
        "Questions" to onQuestions,
        "This, That, Here & There" to onDemonstratives,
        "Negation" to onNegation,
        "Some, None, All" to onSomeNoneAll,
        "Yet, Already & Others" to onConnectors,
        "Adjectives and Adverbs" to onAdjectivesAdverbs
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
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(20.dp))
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // A lone button in the last row spans the full width.
                row.forEach { (label, onClick) ->
                    Button(
                        onClick = onClick,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .heightIn(min = 52.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = label,
                            textAlign = TextAlign.Center,
                            maxLines = 3,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}
