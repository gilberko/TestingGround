package com.example.southkoreatravel.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
    )
}

@Composable
fun BodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun BodyText(text: AnnotatedString) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

fun boldNames(text: String, names: List<String>): AnnotatedString = buildAnnotatedString {
    val sorted = names.sortedByDescending { it.length }
    var i = 0
    while (i < text.length) {
        val match = sorted.firstOrNull { text.startsWith(it, i) }
        if (match != null) {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(match) }
            i += match.length
        } else {
            append(text[i])
            i++
        }
    }
}
