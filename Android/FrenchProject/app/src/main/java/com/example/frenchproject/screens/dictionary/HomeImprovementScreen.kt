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

private data class HomeItem(val french: String, val english: String)
private data class HomeSection(val title: String, val items: List<HomeItem>)

private val homeSections = listOf(
    HomeSection(
        "Structural Problems", listOf(
            HomeItem("la peinture s'écaille", "paint is peeling"),
            HomeItem("les tuyaux éclatent", "pipes bursting"),
            HomeItem("l'inondation (f)", "flood"),
            HomeItem("la moisissure", "mould"),
            HomeItem("la fuite", "leak"),
            HomeItem("la fissure", "crack")
        )
    ),
    HomeSection(
        "Plumbing", listOf(
            HomeItem("le plombier", "plumber"),
            HomeItem("réparer un tuyau", "to fix a pipe"),
            HomeItem("le tuyau", "pipe"),
            HomeItem("le robinet", "tap / faucet"),
            HomeItem("le caniveau bouché", "clogged drain"),
            HomeItem("l'égout (m)", "drain / sewer"),
            HomeItem("la soupape", "valve")
        )
    ),
    HomeSection(
        "Electrical", listOf(
            HomeItem("l'électricien (m)", "electrician"),
            HomeItem("le court-circuit", "short circuit"),
            HomeItem("une panne de courant", "power outage"),
            HomeItem("remplacer", "to replace"),
            HomeItem("l'ampoule (f)", "light bulb"),
            HomeItem("le disjoncteur", "circuit breaker"),
            HomeItem("le fusible", "fuse")
        )
    ),
    HomeSection(
        "Tools", listOf(
            HomeItem("le marteau", "hammer"),
            HomeItem("le clou", "nail"),
            HomeItem("la vis", "screw"),
            HomeItem("le tournevis", "screwdriver"),
            HomeItem("la perceuse", "drill"),
            HomeItem("percer", "to drill"),
            HomeItem("la scie", "saw")
        )
    ),
    HomeSection(
        "Renovation & Painting", listOf(
            HomeItem("rénover", "to renovate"),
            HomeItem("peindre", "to paint"),
            HomeItem("le pinceau", "paintbrush"),
            HomeItem("le rouleau", "roller"),
            HomeItem("la peinture", "paint"),
            HomeItem("le papier peint", "wallpaper"),
            HomeItem("poncer", "to sand")
        )
    ),
    HomeSection(
        "Floors & Tiles", listOf(
            HomeItem("les carreaux cassés", "broken tiles"),
            HomeItem("carreler", "to tile"),
            HomeItem("le parquet", "hardwood floor"),
            HomeItem("la moquette", "carpet"),
            HomeItem("le carrelage", "tiling")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeImprovementScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home Improvement", color = Color.White) },
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
            homeSections.forEach { section ->
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
                    HomeItemCard(section.items[i])
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun HomeItemCard(item: HomeItem) {
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
