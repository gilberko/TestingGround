package com.example.italytravel.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.italytravel.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

/** Every screen except Main uses this: top bar with a back arrow to the previous screen. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenScaffold(
    title: String,
    onBack: () -> Unit,
    showBackground: Boolean = false,
    content: @Composable () -> Unit
) {
    val scaffold: @Composable () -> Unit = {
        Scaffold(
            containerColor = if (showBackground) Color.Transparent else MaterialTheme.colorScheme.background,
            topBar = {
                TopAppBar(
                    title = { Text(title) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = if (showBackground) {
                        TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Black.copy(alpha = 0.35f),
                            titleContentColor = Color.White,
                            navigationIconContentColor = Color.White
                        )
                    } else {
                        TopAppBarDefaults.topAppBarColors()
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                content()
            }
        }
    }
    if (showBackground) AppBackground { scaffold() } else scaffold()
}

/** The app's illustrated background (AppBack) with a light dark scrim - used on all selection screens. */
@Composable
fun AppBackground(content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.app_back),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.35f)))
        content()
    }
}

/** Readable text on top of the background image: white text on a translucent dark card. */
@Composable
fun OverlayText(text: String, style: TextStyle = MaterialTheme.typography.bodyLarge, bold: Boolean = false) {
    Text(
        text = text,
        style = style,
        color = Color.White,
        fontWeight = if (bold) FontWeight.Bold else null,
        textAlign = if (bold) TextAlign.Center else null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
            .padding(12.dp)
    )
}

/** Select screen: a titled list of buttons, each opening another screen. */
@Composable
fun MenuScreen(
    title: String,
    onBack: () -> Unit,
    items: List<Pair<String, () -> Unit>>
) {
    ScreenScaffold(title = title, onBack = onBack, showBackground = true) {
        MenuButtons(items)
    }
}

@Composable
fun MenuButtons(items: List<Pair<String, () -> Unit>>, header: (@Composable () -> Unit)? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        header?.invoke()
        items.forEachIndexed { index, (label, onClick) ->
            if (index > 0) Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
                Text(label)
            }
        }
    }
}

/** Information screen: scrollable text content. */
@Composable
fun InfoScreen(
    title: String,
    onBack: () -> Unit,
    content: @Composable () -> Unit
) {
    ScreenScaffold(title = title, onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            content()
        }
    }
}

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
fun GroupHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 24.dp, bottom = 4.dp)
    )
}

/** One fact line with a bold label, e.g. "Hours: ...". */
@Composable
fun LabeledText(label: String, text: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("$label: ") }
            append(text)
        },
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

/** A place (café, restaurant, gelateria) as a header plus labeled fact lines, skipping blank ones. */
@Composable
fun PlaceEntry(name: String, vararg facts: Pair<String, String>) {
    SectionHeader(name)
    placePhotos[name]?.let { PlacePhoto(it, name) }
    facts.filter { it.second.isNotBlank() }.forEach { (label, text) -> LabeledText(label, text) }
    HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
}

/** One store row: ✓ (has a branch), ✗ (no branch), ? (unconfirmed), with an optional note. */
@Composable
fun StoreCheck(store: String, has: Boolean?, note: String = "") {
    val (mark, color) = when (has) {
        true -> "✓" to Color(0xFF2E7D32)
        false -> "✗" to Color(0xFFC62828)
        null -> "?" to Color(0xFF757575)
    }
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(color = color, fontWeight = FontWeight.Bold)) { append("$mark  ") }
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(store) }
            if (note.isNotBlank()) append(" - $note")
        },
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
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
