package com.example.czechapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private val quantifiers = listOf(
    Triple("many / a lot of", "mnoho / hodně", "Mám hodně přátel. (I have many friends.)"),
    Triple("a few / some", "několik / pár", "Přečetl jsem několik knih. (I read a few books.)"),
    Triple("little / a little", "málo / trochu", "Mám málo času. (I have little time.)"),
    Triple("all / every", "všechen / každý", "Každý den. (Every day.)"),
    Triple("whole / entire", "celý", "Celý den. (The whole day.)"),
    Triple("enough", "dost / dostatek", "Mám dost peněz. (I have enough money.)"),
    Triple("too much / too many", "příliš mnoho / moc", "To je moc. (That's too much.)"),
    Triple("more", "více / víc", "Chci více. (I want more.)"),
    Triple("less", "méně / míň", "Méně je více. (Less is more.)"),
    Triple("none / no", "žádný", "Nemám žádné peníze. (I have no money.)"),
    Triple("both", "oba / obě", "Oba přišli. (Both came.)"),
    Triple("each / every one", "každý", "Každý z nás. (Each of us.)"),
    Triple("half", "polovina / půl", "Půl hodiny. (Half an hour.)"),
    Triple("most / the majority", "většina", "Většina lidí. (Most people.)"),
    Triple("a pair / couple of", "pár / dvojice", "Pár dní. (A couple of days.)")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuantifiersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Many, Few, A Lot") },
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
                            Text("English", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.2f))
                            Text("Czech", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.2f))
                            Text("Example", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.weight(2f))
                        }
                        HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
                        quantifiers.forEachIndexed { i, (en, cz, ex) ->
                            val bg = if (i % 2 == 0) MaterialTheme.colorScheme.surfaceVariant.copy(0.4f) else MaterialTheme.colorScheme.surface
                            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp), colors = CardDefaults.cardColors(bg), elevation = CardDefaults.cardElevation(0.dp)) {
                                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp)) {
                                    Text(en, style = MaterialTheme.typography.bodySmall, modifier = Modifier.weight(1.2f))
                                    Text(cz, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.weight(1.2f))
                                    Text(ex, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, fontStyle = FontStyle.Italic, modifier = Modifier.weight(2f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
