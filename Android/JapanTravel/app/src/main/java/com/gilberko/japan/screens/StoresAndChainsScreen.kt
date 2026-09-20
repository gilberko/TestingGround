package com.gilberko.japan.screens

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
                "**7-Eleven**, **Lawson**, and **FamilyMart** are the three big \"konbini\" chains, found " +
                    "on nearly every block. Open 24/7, they sell fresh food (onigiri, sandwiches, " +
                    "bento), drinks, and everyday items, and most have an ATM and let you pay " +
                    "bills, print, or ship packages."
            )

            SectionHeader("Don Quijote (\"Donki\")")
            BodyText(
                "A huge, maze-like discount variety store chain selling everything from snacks and " +
                    "cosmetics to electronics and souvenirs, often open 24 hours. Larger branches " +
                    "are called **MEGA Don Quijote**. Most locations have a tax-free counter for " +
                    "eligible tourist purchases."
            )

            SectionHeader("Clothing")
            BodyText(
                "**Uniqlo** is Japan's best-known casual clothing chain; its budget sister brand is **GU**. " +
                    "**Muji** sells minimalist clothing and household goods, and **Shimamura** is another " +
                    "affordable clothing chain worth knowing. Uniqlo's flagship store in **Ginza**, Tokyo " +
                    "is especially notable - a 12-floor building with a whole floor devoted to its UT " +
                    "graphic T-shirt line, a top-floor cafe, and Ginza-exclusive merchandise."
            )

            SectionHeader("Electronics")
            BodyText(
                "**Bic Camera**, **Yodobashi Camera**, and **Yamada Denki** (**LABI**) are the major electronics " +
                    "chains, selling cameras, appliances, and gadgets - many also offer tax-free " +
                    "shopping for tourists."
            )

            SectionHeader("100-yen shops")
            BodyText(
                "**Daiso**, **Seria**, and **Can Do** sell household goods, stationery, and souvenirs at " +
                    "flat, low prices (despite the name, some items cost more than ¥100) - good " +
                    "for cheap gifts and travel essentials."
            )

            SectionHeader("Department Stores")
            BodyText(
                "Japanese department stores (depaato) are large multi-story retailers combining " +
                    "fashion boutiques, cosmetics counters, household goods, kimono, and gift " +
                    "departments, an art gallery floor, and - most famously - a basement food floor " +
                    "(depachika) selling bento, sweets, sake, and regional specialties, plus a " +
                    "restaurant floor near the top. Staff wear white gloves, bow to greet customers, " +
                    "and most stores offer gift-wrapping and tax-free counters for tourists."
            )
            BodyText(
                "Most department stores belong to one of a handful of historic groups: **Isetan " +
                    "Mitsukoshi** (part of the Mitsui Group; fashion-forward and prestigious), " +
                    "**Takashimaya** (an independent, luxury-focused chain), **Daimaru Matsuzakaya** " +
                    "(under **J. Front Retailing**, known for excellent food floors), **Hankyu " +
                    "Hanshin** (under **H2O Retailing**, tied to the Hankyu and Hanshin railway " +
                    "networks), and **Sogo & Seibu** (now owned by the US investment fund Fortress)."
            )
            BodyText(
                "Examples: **Mitsukoshi Nihonbashi** in Tokyo (Isetan Mitsukoshi group) is the " +
                    "oldest and most ornate department store in Japan; **Isetan Shinjuku** (Isetan " +
                    "Mitsukoshi group) is considered the trendiest; **Takashimaya** runs flagship " +
                    "stores in Tokyo, Osaka, and Kyoto (Takashimaya Group); **Hankyu Umeda** in " +
                    "Osaka (H2O Retailing) is one of the largest department stores in Japan by sales " +
                    "floor; **Daimaru** operates major stores in Kyoto, Osaka, and Tokyo (J. Front " +
                    "Retailing)."
            )
            BodyText(
                "Recommended: in **Osaka**, **Hankyu Umeda** and **Hanshin Umeda** (both right by " +
                    "Osaka Station, with an excellent basement food floor) or **Takashimaya Osaka** " +
                    "in Namba; in **Kyoto**, **Daimaru Kyoto** and **Takashimaya Kyoto**, both on " +
                    "Shijo-dori, or **Isetan** inside Kyoto Station; in **Tokyo**, **Isetan " +
                    "Shinjuku** for fashion, **Mitsukoshi Nihonbashi** for history and prestige, or " +
                    "**Daimaru Tokyo** right above Tokyo Station for convenience."
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
