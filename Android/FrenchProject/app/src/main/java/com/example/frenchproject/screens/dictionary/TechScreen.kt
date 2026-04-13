package com.example.frenchproject.screens.dictionary

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class TechWord(val french: String, val english: String)
private data class TechSection(val title: String, val words: List<TechWord>)

private val techSections = listOf(
    TechSection(
        title = "Software & Development",
        words = listOf(
            TechWord("le code", "code"),
            TechWord("déboguer", "to debug"),
            TechWord("le design / la maquette", "a design"),
            TechWord("concevoir", "to design"),
            TechWord("implémenter", "to implement"),
            TechWord("le débogueur", "debugger"),
            TechWord("les tests (m pl)", "testing"),
            TechWord("le compilateur", "compiler"),
            TechWord("l'éditeur de liens (m)", "linker"),
            TechWord("les scripts (m pl)", "scripts"),
            TechWord("l'automatisation (f)", "automation"),
            TechWord("l'intelligence artificielle (f)", "artificial intelligence"),
            TechWord("le bug", "bug"),
            TechWord("la fuite (mémoire)", "leak (memory)"),
            TechWord("l'allocation (f)", "allocation"),
            TechWord("ça ne marche pas", "it doesn't work"),
            TechWord("ça marche", "it works"),
            TechWord("tout fonctionne", "everything works")
        )
    ),
    TechSection(
        title = "Project & Team",
        words = listOf(
            TechWord("la réunion", "meeting"),
            TechWord("la date limite", "deadline"),
            TechWord("l'estimation (f)", "estimate"),
            TechWord("être prêt(e)", "to be ready"),
            TechWord("ne pas être encore prêt(e)", "not ready yet"),
            TechWord("le projet", "project"),
            TechWord("simple", "simple"),
            TechWord("compliqué(e)", "complicated")
        )
    ),
    TechSection(
        title = "Roles",
        words = listOf(
            TechWord("le programmeur / la programmeuse", "programmer"),
            TechWord("le designer / la designeuse", "designer"),
            TechWord("le chef de produit", "product manager"),
            TechWord("le chef d'équipe", "team leader"),
            TechWord("le manager", "manager"),
            TechWord("l'ingénieur(e) logiciel(le)", "software engineer")
        )
    ),
    TechSection(
        title = "Hardware & Infrastructure",
        words = listOf(
            TechWord("l'ordinateur portable (m)", "laptop"),
            TechWord("l'ordinateur (m)", "computer"),
            TechWord("la machine virtuelle", "virtual machine"),
            TechWord("l'environnement (m)", "environment"),
            TechWord("le disque dur", "hard drive"),
            TechWord("le disque dur portable", "portable hard drive"),
            TechWord("la clé USB", "USB stick / disk on key"),
            TechWord("le port USB", "USB port"),
            TechWord("le port série", "serial port"),
            TechWord("le port", "port"),
            TechWord("le réseau", "network"),
            TechWord("le câble Ethernet", "Ethernet cable"),
            TechWord("le câble", "cable"),
            TechWord("le processeur", "processor"),
            TechWord("la mémoire", "memory"),
            TechWord("la bibliothèque", "library"),
            TechWord("le module", "module"),
            TechWord("le logiciel", "software"),
            TechWord("le matériel", "hardware"),
            TechWord("l'émulation (f)", "emulation"),
            TechWord("la simulation", "simulation")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tech", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
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
            item {
                Text(
                    text = "Tech vocabulary for the French-speaking workplace. Abbreviations: (m) = masculine, (f) = feminine, (pl) = plural.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            techSections.forEach { section ->
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = FrenchBlue,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    HorizontalDivider(color = FrenchBlue.copy(alpha = 0.4f))
                    Spacer(modifier = Modifier.height(4.dp))
                }
                items(section.words) { word -> TechWordCard(word) }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun TechWordCard(word: TechWord) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = word.french,
                fontWeight = FontWeight.Bold,
                color = FrenchBlue,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = word.english,
                style = MaterialTheme.typography.bodyMedium,
                color = FrenchNavy,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
