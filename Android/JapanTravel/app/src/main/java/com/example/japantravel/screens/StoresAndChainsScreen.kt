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
fun StoresAndChainsScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Stores and Chains", onBack = onBack) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SectionHeader("Convenience stores")
            BodyText(
                "7-Eleven, Lawson, and FamilyMart are the three big \"konbini\" chains, found " +
                    "on nearly every block. Open 24/7, they sell fresh food (onigiri, sandwiches, " +
                    "bento), drinks, and everyday items, and most have an ATM and let you pay " +
                    "bills, print, or ship packages."
            )

            SectionHeader("Don Quijote (\"Donki\")")
            BodyText(
                "A huge, maze-like discount variety store chain selling everything from snacks and " +
                    "cosmetics to electronics and souvenirs, often open 24 hours. Larger branches " +
                    "are called MEGA Don Quijote. Most locations have a tax-free counter for " +
                    "eligible tourist purchases."
            )

            SectionHeader("Clothing")
            BodyText(
                "Uniqlo is Japan's best-known casual clothing chain; its budget sister brand is GU. " +
                    "Muji sells minimalist clothing and household goods, and Shimamura is another " +
                    "affordable clothing chain worth knowing."
            )

            SectionHeader("Electronics")
            BodyText(
                "Bic Camera, Yodobashi Camera, and Yamada Denki (LABI) are the major electronics " +
                    "chains, selling cameras, appliances, and gadgets - many also offer tax-free " +
                    "shopping for tourists."
            )

            SectionHeader("100-yen shops")
            BodyText(
                "Daiso, Seria, and Can Do sell household goods, stationery, and souvenirs at " +
                    "flat, low prices (despite the name, some items cost more than ¥100) - good " +
                    "for cheap gifts and travel essentials."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
