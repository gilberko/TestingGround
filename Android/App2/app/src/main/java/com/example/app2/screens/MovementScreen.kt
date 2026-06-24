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

private data class MovementEntry(val pt: String, val en: String, val note: String = "")
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
    ),
    MovementCategory(
        "Giving Directions",
        listOf(
            MovementEntry("virar à esquerda", "turn left"),
            MovementEntry("virar à direita", "turn right"),
            MovementEntry("voltar atrás", "turn back"),
            MovementEntry("dar a volta / virar-se", "turn around"),
            MovementEntry("continuar em frente", "continue straight"),
            MovementEntry("seguir os sinais / seguir as placas", "follow the signs"),
            MovementEntry("siga-me", "follow me"),
            MovementEntry("siga-o", "follow him"),
            MovementEntry("siga-a", "follow her"),
            MovementEntry("consultar o mapa", "check the map"),
            MovementEntry("usar o GPS", "use GPS"),
            MovementEntry("usar o Google Maps", "use Google Maps"),
            MovementEntry("usar o Waze", "use Waze")
        )
    ),
    MovementCategory(
        "Inside Buildings",
        listOf(
            MovementEntry("apanhar o elevador", "take the elevator"),
            MovementEntry("apanhar as escadas rolantes", "take the escalators"),
            MovementEntry("apanhar as escadas", "take the stairs"),
            MovementEntry(
                "primeiro andar",
                "first floor",
                "EP follows the UK convention: rés-do-chão is the ground floor, primeiro andar is one level above it (≈ US \"2nd floor\")"
            ),
            MovementEntry("segundo andar", "second floor"),
            MovementEntry("cave", "basement"),
            MovementEntry("sair do edifício", "exit the building"),
            MovementEntry("entrar no edifício", "enter the building"),
            MovementEntry("subir ao 3º andar", "climb to the 3rd floor")
        )
    ),
    MovementCategory(
        "Transport & Traffic",
        listOf(
            MovementEntry("apanhar o autocarro", "take a bus"),
            MovementEntry("apanhar um táxi", "take a taxi / cab"),
            MovementEntry("ir de carro até / conduzir até", "drive to"),
            MovementEntry("engarrafamento", "traffic jam"),
            MovementEntry("estar preso no trânsito / estar preso num engarrafamento", "to be stuck in traffic")
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
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = entry.pt,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontStyle = FontStyle.Italic
                                    )
                                    if (entry.note.isNotEmpty()) {
                                        Text(
                                            text = entry.note,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontStyle = FontStyle.Italic,
                                            color = MaterialTheme.colorScheme.tertiary
                                        )
                                    }
                                }
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
