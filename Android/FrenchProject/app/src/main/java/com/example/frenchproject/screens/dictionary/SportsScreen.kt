package com.example.frenchproject.screens.dictionary

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class SportItem(val french: String, val english: String)
private data class SportSection(val title: String, val items: List<SportItem>)

private val sportSections = listOf(
    SportSection(
        "Sports", listOf(
            SportItem("le football", "soccer"),
            SportItem("le basket", "basketball"),
            SportItem("le tennis", "tennis"),
            SportItem("le rugby", "rugby / football"),
            SportItem("la boxe", "boxing"),
            SportItem("la lutte", "wrestling"),
            SportItem("le golf", "golf"),
            SportItem("la natation", "swimming"),
            SportItem("le surf", "surfing"),
            SportItem("le ski", "skiing"),
            SportItem("la gymnastique", "gymnastics"),
            SportItem("l'haltérophilie (f)", "weight lifting"),
            SportItem("la course", "running"),
            SportItem("le marathon", "marathon"),
            SportItem("le saut à la perche", "pole vaulting"),
            SportItem("le curling", "curling"),
            SportItem("l'athlétisme (m)", "athletics")
        )
    ),
    SportSection(
        "Players & Roles", listOf(
            SportItem("le joueur / la joueuse", "player"),
            SportItem("l'arbitre (m/f)", "referee"),
            SportItem("l'entraîneur / l'entraîneuse", "coach"),
            SportItem("l'athlète (m/f)", "athlete"),
            SportItem("l'équipe (f)", "team")
        )
    ),
    SportSection(
        "Venues & Equipment", listOf(
            SportItem("le terrain", "field / court"),
            SportItem("le court", "tennis court"),
            SportItem("le ballon", "ball"),
            SportItem("le filet", "net"),
            SportItem("le but", "goal / goalpost")
        )
    ),
    SportSection(
        "Game Concepts", listOf(
            SportItem("le match", "match"),
            SportItem("le tournoi", "tournament"),
            SportItem("le score", "score"),
            SportItem("gagner", "to win"),
            SportItem("perdre", "to lose"),
            SportItem("faire match nul", "to tie / draw"),
            SportItem("la pénalité", "penalty"),
            SportItem("le hors-jeu", "offside"),
            SportItem("le temps mort", "time out"),
            SportItem("les règles (f pl)", "rules")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sports", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FrenchBlue)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            sportSections.forEach { section ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = FrenchBlue,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.35f))
                    Spacer(modifier = Modifier.height(4.dp))
                }
                items(section.items.size) { i ->
                    SportItemCard(section.items[i])
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun SportItemCard(item: SportItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 9.dp)
        ) {
            Text(
                text = item.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                fontSize = 14.sp,
                modifier = Modifier.width(180.dp)
            )
            Text(
                text = item.english,
                style = MaterialTheme.typography.bodyMedium,
                color = FrenchNavy
            )
        }
    }
}
