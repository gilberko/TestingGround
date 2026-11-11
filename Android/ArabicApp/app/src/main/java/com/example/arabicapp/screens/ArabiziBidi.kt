package com.example.arabicapp.screens

// Arabizi words that start with a digit (3amm, 7abiib, 2aal) get pulled into the
// preceding Arabic run by the Unicode bidi algorithm (rule W2: a European digit
// after Arabic letters is treated as an Arabic number), so "عمّ 3amm" renders as
// "3 عمّamm". A left-to-right mark (U+200E) before the digit keeps it with its word.
private val ARABIZI_DIGIT_START = Regex("(?<![A-Za-z0-9‎])(?=[0-9][A-Za-z])")

internal fun fixArabizi(s: String): String = s.replace(ARABIZI_DIGIT_START, "‎")
