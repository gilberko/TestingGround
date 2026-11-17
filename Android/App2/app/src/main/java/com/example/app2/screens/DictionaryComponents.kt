package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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

/**
 * Shared Simple Dictionary look (the Clothes / Sports design):
 * one ElevatedCard per category, bold title + divider, Portuguese italic on the left,
 * English on the right, an optional tertiary note under the row.
 */
data class DictWord(
    val pt: String,
    val en: String,
    val note: String = "",
    val gender: String = ""
)

data class DictCategory(
    val title: String,
    val entries: List<DictWord>,
    val headerNote: String = "",
    val stacked: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictScreenScaffold(
    title: String,
    onBack: () -> Unit,
    content: LazyListScope.() -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
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
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = content
        )
    }
}

@Composable
fun DictCardShell(
    title: String,
    headerNote: String = "",
    content: @Composable () -> Unit
) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            if (headerNote.isNotEmpty()) {
                Text(
                    text = headerNote,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
            content()
        }
    }
}

@Composable
fun DictCategoryCard(
    title: String,
    entries: List<DictWord>,
    headerNote: String = "",
    stacked: Boolean = false
) {
    DictCardShell(title = title, headerNote = headerNote) {
        entries.forEach { DictWordRow(it, stacked) }
    }
}

@Composable
fun DictCategoryCard(category: DictCategory) {
    DictCategoryCard(category.title, category.entries, category.headerNote, category.stacked)
}

@Composable
fun DictWordRow(entry: DictWord, stacked: Boolean = false) {
    val ptText = if (entry.gender.isNotEmpty()) "${entry.pt} ${entry.gender}" else entry.pt
    Column(modifier = Modifier.fillMaxWidth()) {
        if (stacked) {
            Text(
                text = ptText,
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = entry.en,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = ptText,
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = entry.en,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
            }
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

/** A Portuguese example sentence (italic) with its English translation underneath. */
@Composable
fun DictExample(pt: String, en: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "• $pt",
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "   $en",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
