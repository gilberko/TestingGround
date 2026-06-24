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
fun TutorialScreen(
    onBack: () -> Unit,
    onPrepositions: () -> Unit,
    onConjugationBasic: () -> Unit,
    onConjugationAdvanced: () -> Unit,
    onPronunciation: () -> Unit,
    onReflexiveVerbs: () -> Unit,
    onMorePrepositions: () -> Unit,
    onCliticPronouns: () -> Unit,
    onIrregularVerbs: () -> Unit,
    onUsefulVerbs: () -> Unit,
    onTenses: () -> Unit,
    onComparisons: () -> Unit,
    onQuestions: () -> Unit,
    onDemonstratives: () -> Unit,
    onClarifications: () -> Unit,
    onNegation: () -> Unit,
    onSomeNoneAll: () -> Unit,
    onConnectors: () -> Unit,
    onAuthorsThoughts: () -> Unit,
    onAdjectivesAdverbs: () -> Unit,
    onMoreTenses: () -> Unit
) {
    val buttons = listOf(
        "Prepositions" to onPrepositions,
        "Conjugation — Basic" to onConjugationBasic,
        "Conjugation — Advanced" to onConjugationAdvanced,
        "Pronunciation" to onPronunciation,
        "Reflexive Verbs" to onReflexiveVerbs,
        "More About Prepositions" to onMorePrepositions,
        "Object Pronouns (Clitics)" to onCliticPronouns,
        "Irregular Verbs" to onIrregularVerbs,
        "Useful Verbs" to onUsefulVerbs,
        "Tenses" to onTenses,
        "Comparisons" to onComparisons,
        "Questions" to onQuestions,
        "This, That, Here & There" to onDemonstratives,
        "Clarifications & Misc" to onClarifications,
        "Negation" to onNegation,
        "Some, None, All" to onSomeNoneAll,
        "Yet, Already & Others" to onConnectors,
        "Author's Personal Thoughts" to onAuthorsThoughts,
        "Adjectives and Adverbs" to onAdjectivesAdverbs,
        "More Tenses" to onMoreTenses
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
