package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

private data class MovementEntry(val pt: String, val en: String)
private data class MovementCategory(val title: String, val entries: List<MovementEntry>)

private val movementCategories = listOf(
    MovementCategory(
        "Cardinal Directions",
        listOf(
            MovementEntry("norte", "north"),
            MovementEntry("sul", "south"),
            MovementEntry("este / leste", "east"),
            MovementEntry("oeste", "west"),
            MovementEntry("nordeste", "northeast"),
            MovementEntry("noroeste", "northwest"),
            MovementEntry("sudeste", "southeast"),
            MovementEntry("sudoeste", "southwest")
        )
    ),
    MovementCategory(
        "Direction",
        listOf(
            MovementEntry("em frente", "forwards / ahead"),
            MovementEntry("para trás", "backwards"),
            MovementEntry("à esquerda", "left"),
            MovementEntry("à direita", "right"),
            MovementEntry("em frente / a direito", "straight ahead")
        )
    ),
    MovementCategory(
        "Vertical",
        listOf(
            MovementEntry("para cima", "up / upwards"),
            MovementEntry("para baixo", "down / downwards"),
            MovementEntry("acima", "above"),
            MovementEntry("abaixo", "below")
        )
    ),
    MovementCategory(
        "Relative Position",
        listOf(
            MovementEntry("dentro", "inside / in"),
            MovementEntry("fora", "outside / out"),
            MovementEntry("perto", "near / close"),
            MovementEntry("longe", "far / away"),
            MovementEntry("ao lado", "beside / next to"),
            MovementEntry("em volta", "around"),
            MovementEntry("entre", "between"),
            MovementEntry("através", "through / across"),
            MovementEntry("ao fundo", "at the back / at the end")
        )
    ),
    MovementCategory(
        "Motion Verbs",
        listOf(
            MovementEntry("avançar", "to advance / go forward"),
            MovementEntry("recuar", "to go back / reverse"),
            MovementEntry("virar", "to turn"),
            MovementEntry("parar", "to stop"),
            MovementEntry("continuar", "to continue / go on")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movement") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(movementCategories) { category ->
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = category.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        category.entries.forEach { entry ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = entry.pt,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = entry.en,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
