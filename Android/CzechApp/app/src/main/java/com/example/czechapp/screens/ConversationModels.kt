package com.example.czechapp.screens

internal data class DialogueLine(val speaker: String, val czech: String, val english: String)
internal data class ConversationSection(val title: String, val lines: List<DialogueLine>)
