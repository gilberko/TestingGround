package com.example.czechapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialScreen(
    onBack: () -> Unit,
    onLetters: () -> Unit,
    onGrammarCases: () -> Unit,
    onVerbConjugation: () -> Unit,
    onNounDeclension: () -> Unit,
    onAdjectiveAgreement: () -> Unit,
    onDateTime: () -> Unit,
    onPrepositions: () -> Unit,
    onNegation: () -> Unit,
    onConditionals: () -> Unit,
    onAspect: () -> Unit,
    onMyself: () -> Unit,
    onAboutAPerson: () -> Unit,
    onComparisons: () -> Unit,
    onMisc: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Learning The Language") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        val buttons = listOf(
            "Letters & Diacritics"      to onLetters,
            "Grammar - Cases"           to onGrammarCases,
            "Verb Conjugation"          to onVerbConjugation,
            "Noun Declension"           to onNounDeclension,
            "Adjective Agreement"       to onAdjectiveAgreement,
            "Date & Time"               to onDateTime,
            "Prepositions"              to onPrepositions,
            "Negation"                  to onNegation,
            "Conditionals"              to onConditionals,
            "Aspect"                    to onAspect,
            "My, Myself, You, Yourself" to onMyself,
            "About A Person & Actions"  to onAboutAPerson,
            "Comparisons"               to onComparisons,
            "Misc"                      to onMisc
        )
        val pairs = buttons.chunked(2)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            pairs.forEachIndexed { rowIndex, pair ->
                if (rowIndex > 0) item { Spacer(modifier = Modifier.height(12.dp)) }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        pair.forEach { (label, action) ->
                            OutlinedButton(
                                onClick = action,
                                modifier = Modifier.weight(1f)
                            ) { Text(label) }
                        }
                        if (pair.size < 2) Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
