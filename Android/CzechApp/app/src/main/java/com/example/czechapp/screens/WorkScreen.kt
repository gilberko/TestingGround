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

private val workWords = listOf(
    "work / job" to "práce", "workplace" to "pracoviště",
    "office" to "kancelář", "company / firm" to "firma / společnost",
    "boss / manager" to "šéf / manažer", "employee" to "zaměstnanec",
    "colleague" to "kolega", "team" to "tým",
    "meeting" to "schůzka / porada", "conference" to "konference",
    "project" to "projekt", "deadline" to "termín / deadline",
    "salary / wage" to "plat / mzda", "contract" to "smlouva",
    "interview" to "pohovor", "resume / CV" to "životopis",
    "promotion" to "povýšení", "raise (salary)" to "přidání / zvýšení platu",
    "vacation / holiday" to "dovolená", "sick leave" to "nemocenská",
    "overtime" to "přesčas", "shift" to "směna",
    "freelancer" to "freelancer / živnostník",
    "to work" to "pracovat", "to hire" to "přijmout / najmout",
    "to fire / dismiss" to "propustit / vyhodit",
    "to resign / quit" to "dát výpověď / odejít",
    "to apply (for a job)" to "ucházet se (o práci)",
    "to present" to "prezentovat", "to report" to "hlásit / reportovat",
    "doctor / physician" to "lékař / doktor",
    "teacher" to "učitel / učitelka",
    "engineer" to "inženýr", "lawyer" to "právník",
    "programmer / developer" to "programátor",
    "architect" to "architekt", "accountant" to "účetní",
    "nurse" to "zdravotní sestra / zdravotnický pracovník",
    "police officer" to "policista", "firefighter" to "hasič",
    "chef / cook" to "šéfkuchař / kuchař",
    "waiter / waitress" to "číšník / servírka",
    "driver" to "řidič", "pilot" to "pilot"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Work & Jobs") },
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
                        workWords.forEachIndexed { i, (en, cz) ->
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
