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


private val bodyParts = listOf(
    DictWord("corpo", "body"),
    DictWord("cabeça", "head"),
    DictWord("cabelo", "hair"),
    DictWord("olho / olhos", "eye / eyes"),
    DictWord("nariz", "nose"),
    DictWord("orelha / orelhas", "ear / ears"),
    DictWord("boca", "mouth"),
    DictWord("dente / dentes", "tooth / teeth"),
    DictWord("testa", "forehead"),
    DictWord("bochechas", "cheeks"),
    DictWord("bigode", "moustache"),
    DictWord("barba", "beard"),
    DictWord("pescoço", "neck"),
    DictWord("peito", "chest"),
    DictWord("costas", "back"),
    DictWord("estômago", "stomach"),
    DictWord("perna / pernas", "leg / legs"),
    DictWord("pé", "foot"),
    DictWord("tornozelo", "ankle"),
    DictWord("joelho", "knee"),
    DictWord("mão", "hand"),
    DictWord("braço", "arm"),
    DictWord("dedo", "finger"),
    DictWord("dedos do pé", "toes"),
    DictWord("unhas", "nails"),
    DictWord("unhas dos pés", "toenails")
)

private val hygieneAndGrooming = listOf(
    DictWord("tomar duche", "take a shower"),
    DictWord("lavar as mãos", "wash your hands"),
    DictWord("lavar o cabelo", "wash your hair"),
    DictWord("escovar os dentes", "brush your teeth"),
    DictWord("cortar as unhas", "clip your nails"),
    DictWord("cortar as unhas dos pés", "clip your toenails"),
    DictWord("pentear o cabelo", "comb your hair"),
    DictWord("escova de dentes", "toothbrush")
)

private val symptomsAndIllness = listOf(
    DictWord("dói-me a cabeça", "my head hurts"),
    DictWord("dói-me o dente", "my tooth hurts"),
    DictWord("doem-me as costas", "my back hurts", "costas is plural, so doem-me"),
    DictWord("tenho dor de estômago", "I have a stomach ache"),
    DictWord("febre", "fever"),
    DictWord("temperatura", "temperature"),
    DictWord("vírus", "virus"),
    DictWord("bactérias", "bacteria"),
    DictWord("infeção viral", "virus infection"),
    DictWord("infeção bacteriana", "bacterial infection"),
    DictWord("gripe", "flu"),
    DictWord("estou constipado/a", "I have a cold", "false friend: constipado = having a cold, NOT constipation"),
    DictWord("nariz a pingar", "runny nose"),
    DictWord("alergia", "allergy"),
    DictWord("diarreia", "diarrhea"),
    DictWord("prisão de ventre", "constipation", "not to be confused with constipado (a cold)"),
    DictWord("estava desidratado/a", "I was dehydrated"),
    DictWord("ferida", "wound")
)

private val medicalCareAndTreatment = listOf(
    DictWord("médico/a", "doctor"),
    DictWord("dentista", "dentist"),
    DictWord("enfermeiro/a", "nurse"),
    DictWord("farmacêutico/a", "pharmacist"),
    DictWord("farmácia", "pharmacy"),
    DictWord("hospital", "hospital"),
    DictWord("clínica", "clinic"),
    DictWord("tratamento", "treatment"),
    DictWord("fisioterapia", "physical therapy"),
    DictWord("tratar uma ferida", "treating a wound"),
    DictWord("cuidar de uma ferida", "attending to a wound"),
    DictWord("antibióticos", "antibiotics"),
    DictWord("medicamento", "medicine"),
    DictWord("comprimido", "pill"),
    DictWord("bálsamo", "balm"),
    DictWord("pomada", "ointment"),
    DictWord("creme", "cream"),
    DictWord("gotas para os olhos", "eye drops"),
    DictWord("gotas para os ouvidos", "ear drops"),
    DictWord("penso rápido", "band aid"),
    DictWord("ligadura", "bandage"),
    DictWord("pontos", "stitches"),
    DictWord("termómetro", "thermometer"),
    DictWord("baixar a febre", "reduce / lower the fever"),
    DictWord("dormir", "to sleep")
)

@Composable
fun BodyAndHealthScreen(onBack: () -> Unit) {
    DictScreenScaffold(title = "Body and Health", onBack = onBack) {
        item { DictCategoryCard("Body Parts", bodyParts) }
        item { DictCategoryCard("Hygiene & Grooming", hygieneAndGrooming) }
        item { DictCategoryCard("Symptoms & Illness", symptomsAndIllness) }
        item { DictCategoryCard("Medical Care & Treatment", medicalCareAndTreatment) }
    }
}
