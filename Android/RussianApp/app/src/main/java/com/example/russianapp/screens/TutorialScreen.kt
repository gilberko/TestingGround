package com.example.russianapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.russianapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialScreen(
    onBack: () -> Unit,
    onAlphabet: () -> Unit,
    onGrammarCases: () -> Unit,
    onVerbConjugation: () -> Unit,
    onAdjectiveConjugation: () -> Unit,
    onNounDeclension: () -> Unit,
    onDateTime: () -> Unit,
    onPrepositions: () -> Unit,
    onNegation: () -> Unit,
    onConditionals: () -> Unit,
    onParticiples: () -> Unit,
    onTypesOfAny: () -> Unit,
    onMyself: () -> Unit,
    onAboutAPerson: () -> Unit,
    onComparisons: () -> Unit,
    onMisc: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.russian_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            }
        ) { innerPadding ->
            val buttons = listOf(
                "Alphabet"                  to onAlphabet,
                "Grammar - Cases"           to onGrammarCases,
                "Verb Conjugation"          to onVerbConjugation,
                "Adjective Conjugation"     to onAdjectiveConjugation,
                "Noun Declension"           to onNounDeclension,
                "Date & Time"               to onDateTime,
                "Prepositions"              to onPrepositions,
                "Negation"                  to onNegation,
                "Conditionals"              to onConditionals,
                "Participles"               to onParticiples,
                "Types Of Any"              to onTypesOfAny,
                "My, Myself, You, Yourself" to onMyself,
                "About A Person and About Actions" to onAboutAPerson,
                "Comparisons"                      to onComparisons,
                "Misc"                             to onMisc
            )
            val buttonPairs = buttons.chunked(2)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                buttonPairs.forEachIndexed { rowIndex, pair ->
                    if (rowIndex > 0) {
                        item { Spacer(modifier = Modifier.height(12.dp)) }
                    }
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            pair.forEach { (label, action) ->
                                OutlinedButton(
                                    onClick = action,
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        containerColor = Color.Black,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(label)
                                }
                            }
                            if (pair.size < 2) Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}
