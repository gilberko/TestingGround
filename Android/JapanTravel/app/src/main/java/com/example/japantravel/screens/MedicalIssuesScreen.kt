package com.example.japantravel.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MedicalIssuesScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Medical Issues", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Bringing medication")
            BodyText(
                "Prescription medicine: up to a 1-month supply is allowed into Japan for " +
                    "personal use without any special certificate. Over-the-counter/quasi-drug " +
                    "medicine: up to a 2-month supply is allowed without a certificate."
            )
            BodyText(
                "Beyond those limits - or for certain narcotics, psychotropics, and stimulant " +
                    "raw materials regardless of quantity - you need a \"Yunyu Kakunin-sho\" " +
                    "(the current name for what used to be called Yakkan Shoumei), an import " +
                    "certificate applied for in advance (ideally at least 2 weeks before travel) " +
                    "and presented to customs on arrival."
            )
            BodyText(
                "Yes, bring your printed prescription (the original or a clear copy), ideally " +
                    "along with a short doctor's note stating the medication name and why you " +
                    "need it - useful if customs asks questions."
            )

            SectionHeader("Medications that are not allowed")
            BodyText(
                "Some medications are banned outright, even with a valid foreign prescription and " +
                    "even if a Yunyu Kakunin-sho is obtained. The two most common ones travelers " +
                    "get caught out by: stimulant-based ADHD medication (Adderall and similar " +
                    "amphetamine-based drugs), and pseudoephedrine-containing products (Sudafed, " +
                    "and some Vicks inhaler formulations). There is no permit that makes these " +
                    "legal to bring in - check for an alternative medication with your doctor " +
                    "before the trip."
            )

            SectionHeader("Where to check the official list")
            BodyText(
                "Japan's Ministry of Health, Labour and Welfare (mhlw.go.jp) is the official " +
                    "source for the current controlled-substance list and Yunyu Kakunin-sho " +
                    "application process. If a medication's status is unclear, check there or " +
                    "with the nearest Japanese embassy/consulate before you travel."
            )

            SectionHeader("Emergency medical and dental services")
            BodyText(
                "Hospitals and clinics in Japan are open to the public, including tourists, but " +
                    "you'll typically need to pay upfront - Japan's national health insurance " +
                    "doesn't cover visitors, so travel insurance is worth having."
            )
            BodyText(
                "To find a clinic or hospital, especially one with English support: the AMDA " +
                    "International Medical Information Center offers free multilingual phone " +
                    "interpretation and can help locate an appropriate facility; **Tokyo**'s Himawari " +
                    "service lets you search facilities by area, department, language support, " +
                    "and hours (including holidays/nights); the Japan Visitor Hotline (JNTO, " +
                    "050-3816-2787) is a 24/7 multilingual line useful for accidents, illness, or " +
                    "general guidance. For a non-emergency but urgent question, #7119 connects you " +
                    "to a nurse who can advise whether to call an ambulance (119) or see a doctor " +
                    "later - though this line only covers part of the country so far. Dental " +
                    "emergencies can be handled the same way - call one of these lines to find an " +
                    "English-speaking dentist or a city's holiday/night emergency dental clinic."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
