package com.example.czechapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    onBack: () -> Unit,
    onVerbs: () -> Unit,
    onColors: () -> Unit,
    onNumbers: () -> Unit,
    onMovement: () -> Unit,
    onAdjectives: () -> Unit,
    onAdverbs: () -> Unit,
    onFood: () -> Unit,
    onPlaces: () -> Unit,
    onPeopleAnimals: () -> Unit,
    onWork: () -> Unit,
    onHouse: () -> Unit,
    onQuantifiers: () -> Unit,
    onClothes: () -> Unit,
    onVacation: () -> Unit,
    onCooking: () -> Unit,
    onOfficeSchool: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simple Dictionary") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        val buttons = listOf(
            "Verbs"            to onVerbs,
            "Colors"           to onColors,
            "Numbers"          to onNumbers,
            "Movement"         to onMovement,
            "Adjectives"       to onAdjectives,
            "Adverbs"          to onAdverbs,
            "Food"             to onFood,
            "Places"           to onPlaces,
            "People & Animals" to onPeopleAnimals,
            "Work & Jobs"      to onWork,
            "House"            to onHouse,
            "Many, Few, A Lot" to onQuantifiers,
            "Clothes"          to onClothes,
            "Vacation"         to onVacation,
            "Cooking"          to onCooking,
            "Office & School"  to onOfficeSchool
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            buttons.chunked(2).forEachIndexed { rowIndex, pair ->
                if (rowIndex > 0) item { Spacer(modifier = Modifier.height(12.dp)) }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        pair.forEach { (label, action) ->
                            OutlinedButton(
                                onClick = action,
                                modifier = Modifier.weight(1f)
                            ) { Text(label) }
                        }
                        if (pair.size < 2) Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
