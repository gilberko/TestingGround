package com.example.app2.data.repository

import android.content.Context
import com.example.app2.data.model.Verb
import kotlinx.serialization.json.Json

class VerbRepository(private val context: Context) {
    private val json = Json { ignoreUnknownKeys = true }

    val verbs: List<Verb> by lazy {
        val text = context.assets.open("verbs.json").bufferedReader().use { it.readText() }
        json.decodeFromString(text)
    }
}
