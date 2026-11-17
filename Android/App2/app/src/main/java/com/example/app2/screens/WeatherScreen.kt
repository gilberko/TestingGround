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


private val generalWeatherWords = listOf(
    DictWord("o tempo", "weather / time", "\"O tempo\" means both — context makes it clear"),
    DictWord("a previsão do tempo", "weather forecast"),
    DictWord("a temperatura", "temperature"),
    DictWord("os graus", "degrees"),
    DictWord("Celsius / centígrados", "Celsius")
)

private val hotColdWords = listOf(
    DictWord("quente", "hot"),
    DictWord("frio / fria", "cold"),
    DictWord("o calor", "the heat"),
    DictWord("o frio", "the cold"),
    DictWord("a onda de calor", "heatwave"),
    DictWord("a geada", "frost"),
    DictWord("o gelo", "ice")
)

private val rainStormWords = listOf(
    DictWord("a chuva", "rain"),
    DictWord("chover", "to rain"),
    DictWord("a tempestade", "storm"),
    DictWord("o trovão", "thunder"),
    DictWord("o relâmpago", "lightning"),
    DictWord("a inundação", "flood", "\"a cheia\" also used in EP"),
    DictWord("o granizo", "hail"),
    DictWord("a neve", "snow"),
    DictWord("nevar", "to snow")
)

private val windSkyWords = listOf(
    DictWord("o vento", "wind"),
    DictWord("a brisa", "breeze"),
    DictWord("o furacão", "hurricane"),
    DictWord("o nevoeiro", "fog", "EP prefers \"nevoeiro\" over \"névoa\""),
    DictWord("a nuvem", "cloud"),
    DictWord("nublado / nublada", "cloudy"),
    DictWord("o sol", "sun"),
    DictWord("soalheiro / soalheira", "sunny", "EP term"),
    DictWord("o céu", "sky"),
    DictWord("o arco-íris", "rainbow")
)

private val otherWeatherWords = listOf(
    DictWord("a humidade", "humidity", "EP spelling (BP: \"umidade\")"),
    DictWord("a seca", "drought"),
    DictWord("o orvalho", "dew"),
    DictWord("o guarda-chuva", "umbrella"),
    DictWord("as estações do ano", "the seasons"),
    DictWord("a primavera", "spring"),
    DictWord("o verão", "summer"),
    DictWord("o outono", "autumn"),
    DictWord("o inverno", "winter")
)

@Composable
fun WeatherScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Weather", onBack = onBack) {
        item { DictCategoryCard("The Weather — O Tempo", generalWeatherWords) }
        item { DictCategoryCard("Hot & Cold", hotColdWords) }
        item { DictCategoryCard("Rain & Storms", rainStormWords) }
        item { DictCategoryCard("Wind & Sky", windSkyWords) }
        item { DictCategoryCard("Other Weather Words", otherWeatherWords) }
    }
}
