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


private val movementCategories = listOf(
    DictCategory(
        "Cardinal Directions",
        listOf(
            DictWord("norte", "north"),
            DictWord("sul", "south"),
            DictWord("este / leste", "east"),
            DictWord("oeste", "west"),
            DictWord("nordeste", "northeast"),
            DictWord("noroeste", "northwest"),
            DictWord("sudeste", "southeast"),
            DictWord("sudoeste", "southwest")
        )
    ),
    DictCategory(
        "Direction",
        listOf(
            DictWord("em frente", "forwards / ahead"),
            DictWord("para trás", "backwards"),
            DictWord("à esquerda", "left"),
            DictWord("à direita", "right"),
            DictWord("em frente / a direito", "straight ahead")
        )
    ),
    DictCategory(
        "Vertical",
        listOf(
            DictWord("para cima", "up / upwards"),
            DictWord("para baixo", "down / downwards"),
            DictWord("acima", "above"),
            DictWord("abaixo", "below")
        )
    ),
    DictCategory(
        "Relative Position",
        listOf(
            DictWord("dentro", "inside / in"),
            DictWord("fora", "outside / out"),
            DictWord("perto", "near / close"),
            DictWord("longe", "far / away"),
            DictWord("ao lado", "beside / next to"),
            DictWord("em volta", "around"),
            DictWord("entre", "between"),
            DictWord("através", "through / across"),
            DictWord("ao fundo", "at the back / at the end")
        )
    ),
    DictCategory(
        "Motion Verbs",
        listOf(
            DictWord("avançar", "to advance / go forward"),
            DictWord("recuar", "to go back / reverse"),
            DictWord("virar", "to turn"),
            DictWord("parar", "to stop"),
            DictWord("continuar", "to continue / go on")
        )
    ),
    DictCategory(
        "Giving Directions",
        listOf(
            DictWord("virar à esquerda", "turn left"),
            DictWord("virar à direita", "turn right"),
            DictWord("voltar atrás", "turn back"),
            DictWord("dar a volta / virar-se", "turn around"),
            DictWord("continuar em frente", "continue straight"),
            DictWord("seguir os sinais / seguir as placas", "follow the signs"),
            DictWord("siga-me", "follow me"),
            DictWord("siga-o", "follow him"),
            DictWord("siga-a", "follow her"),
            DictWord("consultar o mapa", "check the map"),
            DictWord("usar o GPS", "use GPS"),
            DictWord("usar o Google Maps", "use Google Maps"),
            DictWord("usar o Waze", "use Waze")
        )
    ),
    DictCategory(
        "Inside Buildings",
        listOf(
            DictWord("apanhar o elevador", "take the elevator"),
            DictWord("apanhar as escadas rolantes", "take the escalators"),
            DictWord("apanhar as escadas", "take the stairs"),
            DictWord(
                "primeiro andar",
                "first floor",
                "EP follows the UK convention: rés-do-chão is the ground floor, primeiro andar is one level above it (≈ US \"2nd floor\")"
            ),
            DictWord("segundo andar", "second floor"),
            DictWord("cave", "basement"),
            DictWord("sair do edifício", "exit the building"),
            DictWord("entrar no edifício", "enter the building"),
            DictWord("subir ao 3º andar", "climb to the 3rd floor")
        )
    ),
    DictCategory(
        "Transport & Traffic",
        listOf(
            DictWord("apanhar o autocarro", "take a bus"),
            DictWord("apanhar um táxi", "take a taxi / cab"),
            DictWord("ir de carro até / conduzir até", "drive to"),
            DictWord("engarrafamento", "traffic jam"),
            DictWord("estar preso no trânsito / estar preso num engarrafamento", "to be stuck in traffic")
        )
    )
)

@Composable
fun MovementScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Movement", onBack = onBack) {
        items(movementCategories) { DictCategoryCard(it) }
    }
}
