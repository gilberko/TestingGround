package com.example.arabicapp

import com.example.arabicapp.screens.ReadingForm
import com.example.arabicapp.screens.ReadingForm.FINAL
import com.example.arabicapp.screens.ReadingForm.INITIAL
import com.example.arabicapp.screens.ReadingForm.ISOLATED
import com.example.arabicapp.screens.ReadingForm.MEDIAL
import com.example.arabicapp.screens.readingBreakdown
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ReadingBreakdownTest {
    private fun forms(word: String): List<ReadingForm> = readingBreakdown(word).map { it.form }

    @Test fun beit() = assertEquals(listOf(INITIAL, MEDIAL, FINAL), forms("بيت"))

    @Test fun daar_allNonConnectors() = assertEquals(listOf(ISOLATED, ISOLATED, ISOLATED), forms("دار"))

    @Test fun sayyaara_shaddaAndTaMarbuta() {
        val b = readingBreakdown("سيّارة")
        assertEquals(listOf(INITIAL, MEDIAL, FINAL, ISOLATED, ISOLATED), b.map { it.form })
        assertTrue(b[1].notes.any { "shadda" in it })
        assertEquals("ة", b[4].shape)
    }

    @Test fun la_ligature() {
        val b = readingBreakdown("لا")
        assertEquals(listOf(INITIAL, FINAL), b.map { it.form })
        assertTrue(b[0].notes.any { "lam-alef" in it })
    }

    @Test fun shukran_tanween() {
        val b = readingBreakdown("شكراً")
        assertEquals(4, b.size)
        assertEquals(listOf(INITIAL, MEDIAL, FINAL, ISOLATED), b.map { it.form })
        assertTrue(b[3].notes.any { "tanween" in it })
    }

    @Test fun suaal_hamzaOnWaw() =
        assertEquals(listOf(INITIAL, FINAL, ISOLATED, ISOLATED), forms("سؤال"))

    @Test fun punctuationAndBidiIgnored() = assertEquals(3, readingBreakdown("بيت؟").size)
}
