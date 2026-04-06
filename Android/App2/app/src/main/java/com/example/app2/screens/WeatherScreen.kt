package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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

private data class WeatherEntry(val pt: String, val en: String, val note: String = "")

private val generalWeatherWords = listOf(
    WeatherEntry("o tempo", "weather / time", "\"O tempo\" means both — context makes it clear"),
    WeatherEntry("a previsão do tempo", "weather forecast"),
    WeatherEntry("a temperatura", "temperature"),
    WeatherEntry("os graus", "degrees"),
    WeatherEntry("Celsius / centígrados", "Celsius")
)

private val hotColdWords = listOf(
    WeatherEntry("quente", "hot"),
    WeatherEntry("frio / fria", "cold"),
    WeatherEntry("o calor", "the heat"),
    WeatherEntry("o frio", "the cold"),
    WeatherEntry("a onda de calor", "heatwave"),
    WeatherEntry("a geada", "frost"),
    WeatherEntry("o gelo", "ice")
)

private val rainStormWords = listOf(
    WeatherEntry("a chuva", "rain"),
    WeatherEntry("chover", "to rain"),
    WeatherEntry("a tempestade", "storm"),
    WeatherEntry("o trovão", "thunder"),
    WeatherEntry("o relâmpago", "lightning"),
    WeatherEntry("a inundação", "flood", "\"a cheia\" also used in EP"),
    WeatherEntry("o granizo", "hail"),
    WeatherEntry("a neve", "snow"),
    WeatherEntry("nevar", "to snow")
)

private val windSkyWords = listOf(
    WeatherEntry("o vento", "wind"),
    WeatherEntry("a brisa", "breeze"),
    WeatherEntry("o furacão", "hurricane"),
    WeatherEntry("o nevoeiro", "fog", "EP prefers \"nevoeiro\" over \"névoa\""),
    WeatherEntry("a nuvem", "cloud"),
    WeatherEntry("nublado / nublada", "cloudy"),
    WeatherEntry("o sol", "sun"),
    WeatherEntry("soalheiro / soalheira", "sunny", "EP term"),
    WeatherEntry("o céu", "sky"),
    WeatherEntry("o arco-íris", "rainbow")
)

private val otherWeatherWords = listOf(
    WeatherEntry("a humidade", "humidity", "EP spelling (BP: \"umidade\")"),
    WeatherEntry("a seca", "drought"),
    WeatherEntry("o orvalho", "dew"),
    WeatherEntry("o guarda-chuva", "umbrella"),
    WeatherEntry("as estações do ano", "the seasons"),
    WeatherEntry("a primavera", "spring"),
    WeatherEntry("o verão", "summer"),
    WeatherEntry("o outono", "autumn"),
    WeatherEntry("o inverno", "winter")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather") },
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
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { WeatherCard("The Weather — O Tempo", generalWeatherWords) }
            item { WeatherCard("Hot & Cold", hotColdWords) }
            item { WeatherCard("Rain & Storms", rainStormWords) }
            item { WeatherCard("Wind & Sky", windSkyWords) }
            item { WeatherCard("Other Weather Words", otherWeatherWords) }
        }
    }
}

@Composable
private fun WeatherCard(title: String, entries: List<WeatherEntry>) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            entries.forEach { entry ->
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
                if (entry.note.isNotEmpty()) {
                    Text(
                        text = entry.note,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}
