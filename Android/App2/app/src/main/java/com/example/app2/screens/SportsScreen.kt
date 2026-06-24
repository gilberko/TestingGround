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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class SportEntry(val pt: String, val en: String, val note: String = "")
private data class SportCategory(val title: String, val entries: List<SportEntry>)

private val sportCategories = listOf(
    SportCategory(
        "Venues & Facilities",
        listOf(
            SportEntry("estádio", "stadium"),
            SportEntry("ringue", "ring"),
            SportEntry("campo", "field / pitch"),
            SportEntry("piscina", "pool"),
            SportEntry("ginásio", "gym"),
            SportEntry("court / tribunal", "court"),
            SportEntry("pista", "track"),
            SportEntry("pavilhão desportivo", "sports hall")
        )
    ),
    SportCategory(
        "People",
        listOf(
            SportEntry("árbitro", "referee"),
            SportEntry("treinador", "coach"),
            SportEntry("atleta", "athlete"),
            SportEntry("equipa", "team"),
            SportEntry("jogador", "player"),
            SportEntry("adversário", "opponent"),
            SportEntry("adepto / fã", "fan / supporter"),
            SportEntry("capitão", "captain")
        )
    ),
    SportCategory(
        "Equipment",
        listOf(
            SportEntry("bola", "ball"),
            SportEntry("raquete", "racket"),
            SportEntry("rede", "net"),
            SportEntry("equipamento", "equipment"),
            SportEntry("capacete", "helmet"),
            SportEntry("luvas", "gloves"),
            SportEntry("calções", "shorts"),
            SportEntry("chuteiras", "football boots / cleats")
        )
    ),
    SportCategory(
        "Actions",
        listOf(
            SportEntry("treinar", "to train"),
            SportEntry("correr", "to run"),
            SportEntry("ganhar", "to win"),
            SportEntry("perder", "to lose"),
            SportEntry("empatar", "to draw / tie"),
            SportEntry("competir", "to compete"),
            SportEntry("marcar (um golo)", "to score (a goal)"),
            SportEntry("arbitrar", "to referee"),
            SportEntry("lesionar-se", "to get injured"),
            SportEntry("jogar futebol", "to play soccer"),
            SportEntry("jogar golfe", "to play golf")
        )
    ),
    SportCategory(
        "Sports",
        listOf(
            SportEntry("atletismo", "athletics"),
            SportEntry("luta livre / wrestling", "wrestling"),
            SportEntry("futebol", "soccer / football"),
            SportEntry("basquetebol", "basketball"),
            SportEntry("râguebi", "rugby"),
            SportEntry("golfe", "golf"),
            SportEntry("ténis", "tennis"),
            SportEntry("natação", "swimming"),
            SportEntry("salto com vara", "pole vaulting"),
            SportEntry("levantamento de pesos", "weight lifting"),
            SportEntry("ciclismo", "cycling"),
            SportEntry("boxe", "boxing"),
            SportEntry("ginástica", "gymnastics"),
            SportEntry("vela", "sailing"),
            SportEntry("futebol americano", "american football"),
            SportEntry("curling", "curling"),
            SportEntry("esqui", "skiing"),
            SportEntry("patinagem no gelo", "ice skating"),
            SportEntry("surf", "surfing"),
            SportEntry("paddle surf / stand-up paddle", "paddle board surfing"),
            SportEntry("caiaque / canoagem", "kayaking")
        )
    ),
    SportCategory(
        "Competitions & Results",
        listOf(
            SportEntry("jogo / partida", "match / game"),
            SportEntry("torneio", "tournament"),
            SportEntry("concurso / prova", "contest / event"),
            SportEntry("empate", "draw / tie"),
            SportEntry("vitória", "victory"),
            SportEntry("derrota", "defeat"),
            SportEntry("campeão", "champion"),
            SportEntry("recorde", "record"),
            SportEntry("medalha", "medal"),
            SportEntry("troféu", "trophy"),
            SportEntry("classificação", "standings / ranking"),
            SportEntry("taça", "cup"),
            SportEntry("mundial / campeonato do mundo", "world cup"),
            SportEntry("liga", "league"),
            SportEntry("vencedor", "winner"),
            SportEntry("medalha de ouro", "gold medal"),
            SportEntry("medalha de prata", "silver medal"),
            SportEntry("medalha de bronze", "bronze medal"),
            SportEntry("pódio", "podium"),
            SportEntry("Jogos Olímpicos", "olympics")
        )
    ),
    SportCategory(
        "Soccer & Officiating",
        listOf(
            SportEntry("substituição", "substitution"),
            SportEntry("suplente", "substitute player"),
            SportEntry("estar no banco", "sitting on the bench"),
            SportEntry("fora de jogo", "offside"),
            SportEntry("grande penalidade / pénalti", "penalty kick"),
            SportEntry("VAR", "VAR (video assistant referee)"),
            SportEntry("consultar o VAR", "checking the VAR"),
            SportEntry("falta", "foul"),
            SportEntry("driblar", "to dribble"),
            SportEntry("lesão", "injury")
        )
    ),
    SportCategory(
        "Basketball & Tennis",
        listOf(
            SportEntry("afundanço", "dunk", "European Portuguese; Brazilian Portuguese says \"enterrada\""),
            SportEntry("cesto", "basket (the score / hoop)"),
            SportEntry("aro", "hoop / rim"),
            SportEntry("set", "set"),
            SportEntry("desempate", "tie breaker"),
            SportEntry("ponto de jogo", "game point"),
            SportEntry("ponto / pontos", "point / points"),
            SportEntry("bola de ténis", "tennis ball"),
            SportEntry("raquete de ténis", "tennis racket")
        )
    ),
    SportCategory(
        "Race Day & Equipment",
        listOf(
            SportEntry("taco de basebol", "baseball bat"),
            SportEntry("corrida", "race"),
            SportEntry("pista de corrida", "racetrack"),
            SportEntry("tempo morto", "time out"),
            SportEntry(
                "Às suas marcas, prontos, já!",
                "On your marks, get set, go!",
                "official athletics uses a starting pistol rather than saying \"go\" out loud"
            )
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sports") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sportCategories, key = { it.title }) { category ->
                SportCategoryCard(category)
            }
        }
    }
}

@Composable
private fun SportCategoryCard(category: SportCategory) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = category.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            category.entries.forEach { entry ->
                SportRow(entry)
            }
        }
    }
}

@Composable
private fun SportRow(entry: SportEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = entry.pt,
                style = MaterialTheme.typography.bodySmall,
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
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
    }
}
