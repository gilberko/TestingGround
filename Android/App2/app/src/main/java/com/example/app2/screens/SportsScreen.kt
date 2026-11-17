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


private val sportCategories = listOf(
    DictCategory(
        "Venues & Facilities",
        listOf(
            DictWord("estádio", "stadium"),
            DictWord("ringue", "ring"),
            DictWord("campo", "field / pitch"),
            DictWord("piscina", "pool"),
            DictWord("ginásio", "gym"),
            DictWord("court / tribunal", "court"),
            DictWord("pista", "track"),
            DictWord("pavilhão desportivo", "sports hall")
        )
    ),
    DictCategory(
        "People",
        listOf(
            DictWord("árbitro", "referee"),
            DictWord("treinador", "coach"),
            DictWord("atleta", "athlete"),
            DictWord("equipa", "team"),
            DictWord("jogador", "player"),
            DictWord("adversário", "opponent"),
            DictWord("adepto / fã", "fan / supporter"),
            DictWord("capitão", "captain")
        )
    ),
    DictCategory(
        "Equipment",
        listOf(
            DictWord("bola", "ball"),
            DictWord("raquete", "racket"),
            DictWord("rede", "net"),
            DictWord("equipamento", "equipment"),
            DictWord("capacete", "helmet"),
            DictWord("luvas", "gloves"),
            DictWord("calções", "shorts"),
            DictWord("chuteiras", "football boots / cleats")
        )
    ),
    DictCategory(
        "Actions",
        listOf(
            DictWord("treinar", "to train"),
            DictWord("correr", "to run"),
            DictWord("ganhar", "to win"),
            DictWord("perder", "to lose"),
            DictWord("empatar", "to draw / tie"),
            DictWord("competir", "to compete"),
            DictWord("marcar (um golo)", "to score (a goal)"),
            DictWord("arbitrar", "to referee"),
            DictWord("lesionar-se", "to get injured"),
            DictWord("jogar futebol", "to play soccer"),
            DictWord("jogar golfe", "to play golf")
        )
    ),
    DictCategory(
        "Sports",
        listOf(
            DictWord("atletismo", "athletics"),
            DictWord("luta livre / wrestling", "wrestling"),
            DictWord("futebol", "soccer / football"),
            DictWord("basquetebol", "basketball"),
            DictWord("râguebi", "rugby"),
            DictWord("golfe", "golf"),
            DictWord("ténis", "tennis"),
            DictWord("natação", "swimming"),
            DictWord("salto com vara", "pole vaulting"),
            DictWord("levantamento de pesos", "weight lifting"),
            DictWord("ciclismo", "cycling"),
            DictWord("boxe", "boxing"),
            DictWord("ginástica", "gymnastics"),
            DictWord("vela", "sailing"),
            DictWord("futebol americano", "american football"),
            DictWord("curling", "curling"),
            DictWord("esqui", "skiing"),
            DictWord("patinagem no gelo", "ice skating"),
            DictWord("surf", "surfing"),
            DictWord("paddle surf / stand-up paddle", "paddle board surfing"),
            DictWord("caiaque / canoagem", "kayaking")
        )
    ),
    DictCategory(
        "Competitions & Results",
        listOf(
            DictWord("jogo / partida", "match / game"),
            DictWord("torneio", "tournament"),
            DictWord("concurso / prova", "contest / event"),
            DictWord("empate", "draw / tie"),
            DictWord("vitória", "victory"),
            DictWord("derrota", "defeat"),
            DictWord("campeão", "champion"),
            DictWord("recorde", "record"),
            DictWord("medalha", "medal"),
            DictWord("troféu", "trophy"),
            DictWord("classificação", "standings / ranking"),
            DictWord("taça", "cup"),
            DictWord("mundial / campeonato do mundo", "world cup"),
            DictWord("liga", "league"),
            DictWord("vencedor", "winner"),
            DictWord("medalha de ouro", "gold medal"),
            DictWord("medalha de prata", "silver medal"),
            DictWord("medalha de bronze", "bronze medal"),
            DictWord("pódio", "podium"),
            DictWord("Jogos Olímpicos", "olympics")
        )
    ),
    DictCategory(
        "Soccer & Officiating",
        listOf(
            DictWord("substituição", "substitution"),
            DictWord("suplente", "substitute player"),
            DictWord("estar no banco", "sitting on the bench"),
            DictWord("fora de jogo", "offside"),
            DictWord("grande penalidade / pénalti", "penalty kick"),
            DictWord("VAR", "VAR (video assistant referee)"),
            DictWord("consultar o VAR", "checking the VAR"),
            DictWord("falta", "foul"),
            DictWord("driblar", "to dribble"),
            DictWord("lesão", "injury")
        )
    ),
    DictCategory(
        "Basketball & Tennis",
        listOf(
            DictWord("afundanço", "dunk", "European Portuguese; Brazilian Portuguese says \"enterrada\""),
            DictWord("cesto", "basket (the score / hoop)"),
            DictWord("aro", "hoop / rim"),
            DictWord("set", "set"),
            DictWord("desempate", "tie breaker"),
            DictWord("ponto de jogo", "game point"),
            DictWord("ponto / pontos", "point / points"),
            DictWord("bola de ténis", "tennis ball"),
            DictWord("raquete de ténis", "tennis racket")
        )
    ),
    DictCategory(
        "Race Day & Equipment",
        listOf(
            DictWord("taco de basebol", "baseball bat"),
            DictWord("corrida", "race"),
            DictWord("pista de corrida", "racetrack"),
            DictWord("tempo morto", "time out"),
            DictWord(
                "Às suas marcas, prontos, já!",
                "On your marks, get set, go!",
                "official athletics uses a starting pistol rather than saying \"go\" out loud"
            )
        )
    )
)

@Composable
fun SportsScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Sports", onBack = onBack) {
        items(sportCategories) { DictCategoryCard(it) }
    }
}
