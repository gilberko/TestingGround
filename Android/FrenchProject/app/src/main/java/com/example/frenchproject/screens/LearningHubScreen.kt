package com.example.frenchproject.screens

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.frenchproject.R
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningHubScreen(
    onBack: () -> Unit,
    onSubjectPronouns: () -> Unit,
    onArticles: () -> Unit,
    onEtreAvoir: () -> Unit,
    onTenses: () -> Unit,
    onReflexiveVerbs: () -> Unit,
    onAskingQuestions: () -> Unit,
    onDemonstratives: () -> Unit,
    onNegation: () -> Unit,
    onPrepositions: () -> Unit,
    onPassive: () -> Unit,
    onConditionals: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("Learning The Language", color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = FrenchBlue)
                )
            }
        ) { innerPadding ->
            val buttons = listOf(
                "Subject Pronouns" to onSubjectPronouns,
                "Articles" to onArticles,
                "Être et Avoir" to onEtreAvoir,
                "Tenses" to onTenses,
                "Reflexive Verbs" to onReflexiveVerbs,
                "Asking Questions" to onAskingQuestions,
                "This and That,\nHere and There" to onDemonstratives,
                "Negation" to onNegation,
                "Prepositions" to onPrepositions,
                "Passive Voice" to onPassive,
                "Conditionals" to onConditionals
            )
            val pairs = buttons.chunked(2)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                pairs.forEachIndexed { index, pair ->
                    if (index > 0) Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        pair.forEach { (label, action) ->
                            OutlinedButton(
                                onClick = action,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(64.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = FrenchNavy,
                                    contentColor = Color.White
                                )
                            ) {
                                Text(label, textAlign = TextAlign.Center)
                            }
                        }
                        if (pair.size < 2) Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
