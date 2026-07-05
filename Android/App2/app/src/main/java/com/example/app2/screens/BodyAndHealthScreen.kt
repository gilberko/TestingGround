package com.example.app2.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
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

private data class BodyHealthEntry(val en: String, val pt: String, val notes: String = "")

private val bodyParts = listOf(
    BodyHealthEntry("body", "corpo"),
    BodyHealthEntry("head", "cabeça"),
    BodyHealthEntry("hair", "cabelo"),
    BodyHealthEntry("eye / eyes", "olho / olhos"),
    BodyHealthEntry("nose", "nariz"),
    BodyHealthEntry("ear / ears", "orelha / orelhas"),
    BodyHealthEntry("mouth", "boca"),
    BodyHealthEntry("tooth / teeth", "dente / dentes"),
    BodyHealthEntry("forehead", "testa"),
    BodyHealthEntry("cheeks", "bochechas"),
    BodyHealthEntry("moustache", "bigode"),
    BodyHealthEntry("beard", "barba"),
    BodyHealthEntry("neck", "pescoço"),
    BodyHealthEntry("chest", "peito"),
    BodyHealthEntry("back", "costas"),
    BodyHealthEntry("stomach", "estômago"),
    BodyHealthEntry("leg / legs", "perna / pernas"),
    BodyHealthEntry("foot", "pé"),
    BodyHealthEntry("ankle", "tornozelo"),
    BodyHealthEntry("knee", "joelho"),
    BodyHealthEntry("hand", "mão"),
    BodyHealthEntry("arm", "braço"),
    BodyHealthEntry("finger", "dedo"),
    BodyHealthEntry("toes", "dedos do pé"),
    BodyHealthEntry("nails", "unhas"),
    BodyHealthEntry("toenails", "unhas dos pés")
)

private val hygieneAndGrooming = listOf(
    BodyHealthEntry("take a shower", "tomar duche"),
    BodyHealthEntry("wash your hands", "lavar as mãos"),
    BodyHealthEntry("wash your hair", "lavar o cabelo"),
    BodyHealthEntry("brush your teeth", "escovar os dentes"),
    BodyHealthEntry("clip your nails", "cortar as unhas"),
    BodyHealthEntry("clip your toenails", "cortar as unhas dos pés"),
    BodyHealthEntry("comb your hair", "pentear o cabelo"),
    BodyHealthEntry("toothbrush", "escova de dentes")
)

private val symptomsAndIllness = listOf(
    BodyHealthEntry("my head hurts", "dói-me a cabeça"),
    BodyHealthEntry("my tooth hurts", "dói-me o dente"),
    BodyHealthEntry("my back hurts", "doem-me as costas", "costas is plural, so doem-me"),
    BodyHealthEntry("I have a stomach ache", "tenho dor de estômago"),
    BodyHealthEntry("fever", "febre"),
    BodyHealthEntry("temperature", "temperatura"),
    BodyHealthEntry("virus", "vírus"),
    BodyHealthEntry("bacteria", "bactérias"),
    BodyHealthEntry("virus infection", "infeção viral"),
    BodyHealthEntry("bacterial infection", "infeção bacteriana"),
    BodyHealthEntry("flu", "gripe"),
    BodyHealthEntry("I have a cold", "estou constipado/a", "false friend: constipado = having a cold, NOT constipation"),
    BodyHealthEntry("runny nose", "nariz a pingar"),
    BodyHealthEntry("allergy", "alergia"),
    BodyHealthEntry("diarrhea", "diarreia"),
    BodyHealthEntry("constipation", "prisão de ventre", "not to be confused with constipado (a cold)"),
    BodyHealthEntry("I was dehydrated", "estava desidratado/a"),
    BodyHealthEntry("wound", "ferida")
)

private val medicalCareAndTreatment = listOf(
    BodyHealthEntry("doctor", "médico/a"),
    BodyHealthEntry("dentist", "dentista"),
    BodyHealthEntry("nurse", "enfermeiro/a"),
    BodyHealthEntry("pharmacist", "farmacêutico/a"),
    BodyHealthEntry("pharmacy", "farmácia"),
    BodyHealthEntry("hospital", "hospital"),
    BodyHealthEntry("clinic", "clínica"),
    BodyHealthEntry("treatment", "tratamento"),
    BodyHealthEntry("physical therapy", "fisioterapia"),
    BodyHealthEntry("treating a wound", "tratar uma ferida"),
    BodyHealthEntry("attending to a wound", "cuidar de uma ferida"),
    BodyHealthEntry("antibiotics", "antibióticos"),
    BodyHealthEntry("medicine", "medicamento"),
    BodyHealthEntry("pill", "comprimido"),
    BodyHealthEntry("balm", "bálsamo"),
    BodyHealthEntry("ointment", "pomada"),
    BodyHealthEntry("cream", "creme"),
    BodyHealthEntry("eye drops", "gotas para os olhos"),
    BodyHealthEntry("ear drops", "gotas para os ouvidos"),
    BodyHealthEntry("band aid", "penso rápido"),
    BodyHealthEntry("bandage", "ligadura"),
    BodyHealthEntry("stitches", "pontos"),
    BodyHealthEntry("thermometer", "termómetro"),
    BodyHealthEntry("reduce / lower the fever", "baixar a febre"),
    BodyHealthEntry("to sleep", "dormir")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyAndHealthScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Body and Health") },
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { SectionHeader("Body Parts") }
            items(bodyParts) { BodyHealthCard(it) }

            item { SectionHeader("Hygiene & Grooming") }
            items(hygieneAndGrooming) { BodyHealthCard(it) }

            item { SectionHeader("Symptoms & Illness") }
            items(symptomsAndIllness) { BodyHealthCard(it) }

            item { SectionHeader("Medical Care & Treatment") }
            items(medicalCareAndTreatment) { BodyHealthCard(it) }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 8.dp, bottom = 2.dp)
    )
}

@Composable
private fun BodyHealthCard(entry: BodyHealthEntry) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = entry.en,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = entry.pt,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (entry.notes.isNotEmpty()) {
                Text(
                    text = entry.notes,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
