package com.example.southkoreatravel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MedicalIssuesScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Medical Issues", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Medication Restrictions")
            BodyText(boldNames("Korea's Ministry of Food and Drug Safety (MFDS) requires advance approval before bringing in medications that contain controlled substances — this includes many common ADHD medications (like Ritalin/methylphenidate or Adderall/amphetamine-based drugs), as well as strong painkillers, sedatives, and other opioid- or stimulant-related medicines. Without approval, even a legally prescribed medication from home can cause serious problems at customs.", listOf("Ministry of Food and Drug Safety", "MFDS", "Ritalin", "Adderall")))
            BodyText(boldNames("As a general rule, up to about a 6-week supply of ordinary prescription medication is fine without extra paperwork, but anything in the controlled-substance categories above needs prior MFDS approval regardless of quantity. Also worth knowing: products containing poppy seeds (including some bagel seasoning) are actually illegal to bring into Korea.", listOf("MFDS")))
            BodyText(boldNames("Before you travel, check your specific medication against MFDS's own guidance rather than guessing — keep medicines in original packaging, carry your prescription and/or a doctor's letter, and if in doubt, contact the Korean embassy or consulate in your home country in advance to confirm whether an import permit is needed.", listOf("MFDS")))

            SectionHeader("Emergency Care")
            BodyText("For a genuine emergency (breathing difficulty, loss of consciousness, severe bleeding), call 119 — Korea's equivalent of 911. Say \"English, please\" and you'll be connected to an interpreter; ambulance transport is free unless you specifically request transfer to a particular private hospital.")
            BodyText(boldNames("For less urgent medical questions — unsure whether something needs a hospital tonight or can wait for a clinic tomorrow — call 1339, a 24/7 medical consultation line. There's also 1330, the Korea Tourism Organization's tourist hotline, useful for general travel problems and interpretation, not just medical ones.", listOf("Korea Tourism Organization")))
            BodyText(boldNames("For English-speaking hospital care, major hospitals with dedicated international clinics include Severance Hospital (Yonsei University) and Seoul National University Hospital's International Health Center, both in Seoul, with English-speaking staff and (at Severance) 24-hour emergency contact in English. For dental emergencies, several Seoul clinics specialize in treating international patients with English-speaking dentists — look for ones explicitly advertising foreigner/international patient services rather than a random local clinic, since English support varies a lot outside of those dedicated practices.", listOf("Severance Hospital", "Yonsei University", "Seoul National University Hospital's International Health Center")))
        }
    }
}
