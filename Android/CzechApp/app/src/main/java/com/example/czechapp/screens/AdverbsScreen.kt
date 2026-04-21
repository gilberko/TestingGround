package com.example.czechapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private val adverbs = listOf(
    "here" to "tady / zde", "there" to "tam", "somewhere" to "někde",
    "nowhere" to "nikde", "everywhere" to "všude",
    "now" to "teď / nyní", "then" to "tehdy / potom",
    "today" to "dnes / dneska", "yesterday" to "včera", "tomorrow" to "zítra",
    "soon" to "brzy", "later" to "později", "already" to "už / již",
    "still / yet" to "ještě", "no longer" to "už ne / již ne",
    "always" to "vždy / pořád", "never" to "nikdy",
    "often" to "často", "sometimes" to "někdy", "rarely" to "zřídka",
    "very" to "velmi / hodně", "quite / fairly" to "docela",
    "a little / a bit" to "trochu / trocha", "too (much)" to "příliš / moc",
    "enough" to "dost / dostatečně",
    "more" to "více / víc", "less" to "méně / míň",
    "fast / quickly" to "rychle", "slowly" to "pomalu",
    "well" to "dobře", "badly" to "špatně",
    "exactly" to "přesně", "almost" to "skoro / téměř",
    "perhaps / maybe" to "možná / snad",
    "certainly / definitely" to "určitě / jistě",
    "together" to "spolu / dohromady", "apart / separately" to "odděleně"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdverbsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adverbs") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                            Text("Czech", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        adverbs.forEachIndexed { i, (en, cz) ->
                            val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(0.4f) else MaterialTheme.colorScheme.surface
                            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp), colors = CardDefaults.cardColors(bg), elevation = CardDefaults.cardElevation(0.dp)) {
                                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
                                    Text(en, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.5f))
                                    Text(cz, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.5f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
