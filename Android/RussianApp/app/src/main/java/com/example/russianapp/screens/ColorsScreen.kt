package com.example.russianapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

private data class RussianColor(
    val english: String,
    val masculine: String,       // masculine nominative singular (dictionary form)
    val feminine: String,        // feminine nominative singular
    val neuter: String,          // neuter nominative singular
    val plural: String,          // plural nominative
    val swatch: Color,           // approximate color for the visual swatch
    val swatchDark: Boolean = false  // true → use white label on swatch
)

private val colors = listOf(
    RussianColor("Red",        "красный",    "красная",    "красное",    "красные",    Color(0xFFD52B1E), swatchDark = true),
    RussianColor("Blue (dark)","синий",      "синяя",      "синее",      "синие",      Color(0xFF0039A6), swatchDark = true),
    RussianColor("Blue (light)","голубой",   "голубая",    "голубое",    "голубые",    Color(0xFF87CEEB)),
    RussianColor("Yellow",     "жёлтый",     "жёлтая",     "жёлтое",     "жёлтые",     Color(0xFFFFD700)),
    RussianColor("Green",      "зелёный",    "зелёная",    "зелёное",    "зелёные",    Color(0xFF4CAF50), swatchDark = true),
    RussianColor("Orange",     "оранжевый",  "оранжевая",  "оранжевое",  "оранжевые",  Color(0xFFFF8C00)),
    RussianColor("Purple",     "фиолетовый", "фиолетовая", "фиолетовое", "фиолетовые", Color(0xFF800080), swatchDark = true),
    RussianColor("Pink",       "розовый",    "розовая",    "розовое",    "розовые",    Color(0xFFFF69B4)),
    RussianColor("Brown",      "коричневый", "коричневая", "коричневое", "коричневые", Color(0xFF8B4513), swatchDark = true),
    RussianColor("Black",      "чёрный",     "чёрная",     "чёрное",     "чёрные",     Color(0xFF212121), swatchDark = true),
    RussianColor("White",      "белый",      "белая",      "белое",      "белые",      Color(0xFFF5F5F5)),
    RussianColor("Grey",       "серый",      "серая",      "серое",      "серые",      Color(0xFF9E9E9E)),
    RussianColor("Golden",     "золотой",    "золотая",    "золотое",    "золотые",    Color(0xFFFFD700)),
    RussianColor("Silver",     "серебряный", "серебряная", "серебряное", "серебряные", Color(0xFFC0C0C0)),
    RussianColor("Beige",      "бежевый",    "бежевая",    "бежевое",    "бежевые",    Color(0xFFF5F5DC)),
    RussianColor("Burgundy",   "бордовый",   "бордовая",   "бордовое",   "бордовые",   Color(0xFF800020), swatchDark = true)
)

@Composable
private fun ColorCard(color: RussianColor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Color swatch
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color.swatch)
            )
            Spacer(modifier = Modifier.width(12.dp))

            // Text info
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = color.masculine,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "  (${color.english})",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "F: ${color.feminine}  •  N: ${color.neuter}  •  Pl: ${color.plural}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Colors") },
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
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                Text(
                    text = "Colors in Russian are adjectives and must agree in gender, number, and case with the noun they describe. The forms shown here are nominative singular/plural.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "M = masculine  •  F = feminine  •  N = neuter  •  Pl = plural",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(colors) { color ->
                ColorCard(color)
            }
        }
    }
}
