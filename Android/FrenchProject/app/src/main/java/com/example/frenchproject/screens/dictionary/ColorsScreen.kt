package com.example.frenchproject.screens.dictionary

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenchproject.ui.theme.FrenchBlue
import com.example.frenchproject.ui.theme.FrenchNavy

private data class FrenchColor(
    val masculine: String,
    val feminine: String,
    val english: String,
    val swatch: Color,
    val invariable: Boolean = false,
    val note: String? = null
)

private val colors = listOf(
    FrenchColor("rouge", "rouge", "red", Color(0xFFCC0000)),
    FrenchColor("bleu", "bleue", "blue", Color(0xFF003189)),
    FrenchColor("vert", "verte", "green", Color(0xFF2E7D32)),
    FrenchColor("jaune", "jaune", "yellow", Color(0xFFF5C518)),
    FrenchColor("orange", "orange", "orange", Color(0xFFE65100), invariable = true, note = "Invariable — no agreement"),
    FrenchColor("violet", "violette", "purple / violet", Color(0xFF6A1B9A)),
    FrenchColor("rose", "rose", "pink", Color(0xFFE91E8C)),
    FrenchColor("blanc", "blanche", "white", Color(0xFFF5F5F5)),
    FrenchColor("noir", "noire", "black", Color(0xFF212121)),
    FrenchColor("gris", "grise", "gray", Color(0xFF757575)),
    FrenchColor("marron", "marron", "brown", Color(0xFF6D4C41), invariable = true, note = "Invariable — no agreement"),
    FrenchColor("beige", "beige", "beige", Color(0xFFF5F0DC), invariable = true, note = "Invariable — no agreement"),
    FrenchColor("doré", "dorée", "gold / golden", Color(0xFFFFB300)),
    FrenchColor("argenté", "argentée", "silver", Color(0xFFBDBDBD)),
    FrenchColor("turquoise", "turquoise", "turquoise", Color(0xFF00ACC1), invariable = true, note = "Invariable — no agreement"),
    FrenchColor("bordeaux", "bordeaux", "burgundy / dark red", Color(0xFF800020), invariable = true, note = "Invariable — no agreement"),
    FrenchColor("crème", "crème", "cream", Color(0xFFFFF8DC), invariable = true, note = "Invariable — no agreement"),
    FrenchColor("indigo", "indigo", "indigo", Color(0xFF3949AB), invariable = true),
    FrenchColor("lavande", "lavande", "lavender", Color(0xFF9575CD), invariable = true),
    FrenchColor("corail", "corail", "coral", Color(0xFFFF7043), invariable = true, note = "Invariable — no agreement")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Colors", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
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
            item {
                Text(
                    text = "In French, colors are adjectives and must agree in gender and number with the noun they describe. Most colors have a masculine and feminine form. Some colors are invariable — they never change.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "M = masculine  •  F = feminine",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                HorizontalDivider(color = FrenchBlue.copy(alpha = 0.3f))
                Spacer(modifier = Modifier.height(4.dp))
            }
            items(colors.size) { i -> ColorCard(colors[i]) }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun ColorCard(color: FrenchColor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color.swatch)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (color.invariable) color.masculine else "${color.masculine} / ${color.feminine}",
                        fontWeight = FontWeight.Bold,
                        color = FrenchBlue,
                        fontSize = 15.sp
                    )
                }
                Text(
                    text = color.english,
                    style = MaterialTheme.typography.bodySmall,
                    color = FrenchNavy
                )
                if (color.note != null) {
                    Text(
                        text = color.note,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else if (!color.invariable && color.masculine != color.feminine) {
                    Text(
                        text = "M: ${color.masculine}  •  F: ${color.feminine}",
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
