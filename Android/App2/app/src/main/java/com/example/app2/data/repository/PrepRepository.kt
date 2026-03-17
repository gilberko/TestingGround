package com.example.app2.data.repository

import android.content.Context
import com.example.app2.data.model.PrepQuestion
import kotlinx.serialization.json.Json

class PrepRepository(private val context: Context) {
    private val json = Json { ignoreUnknownKeys = true }

    val questions: List<PrepQuestion> by lazy {
        val text = context.assets.open("prepositions.json").bufferedReader().use { it.readText() }
        json.decodeFromString(text)
    }
}
