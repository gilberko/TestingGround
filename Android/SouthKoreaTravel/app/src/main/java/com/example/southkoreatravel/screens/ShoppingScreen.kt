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
fun ShoppingScreen(onBack: () -> Unit) {
    ScreenScaffold(title = "Shopping", onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionHeader("Beauty & Lifestyle")
            BodyText(boldNames("Olive Young is Korea's dominant health-and-beauty chain, found on nearly every major shopping street — the go-to for K-beauty skincare and cosmetics, both well-known brands and Olive Young's own affordable lines.", listOf("Olive Young")))
            BodyText(boldNames("Daiso is Korea's answer to a budget variety/dollar store — despite the shared name-feel with Japan's Daiso, this is actually a Korean-founded chain, and most items run between ₩1,000 and ₩5,000. It's genuinely useful for travel basics, souvenirs, and household odds and ends, and Myeongdong has a large multi-floor flagship location worth a browse even just for the range of goods. Unlike Japan's Don Quijote (which mixes electronics, snacks, and novelty items in a chaotic warehouse-style store), Daiso is more purely a low-price variety store — for the closer Don Quijote-style, a-bit-of-everything experience, look to large multi-floor duty-free stores or big Myeongdong/Hongdae souvenir shops instead.", listOf("Daiso", "Don Quijote")))

            SectionHeader("Clothing & Shoes")
            BodyText(boldNames("Musinsa started as an online platform but now has physical stores too — it's the biggest name in Korean streetwear and fashion, spanning trendy local labels up to higher-end brands, and a good first stop for Korean fashion generally.", listOf("Musinsa")))
            BodyText(boldNames("For shoes and general clothing at accessible prices, look for Korean mall-brand chains like SPAO, 8seconds, and Uniqlo/Zara-style fast-fashion stores clustered in shopping districts like Myeongdong, Hongdae, and Gangnam's underground malls.", listOf("SPAO", "8seconds", "Uniqlo", "Zara")))

            SectionHeader("Electronics")
            BodyText(boldNames("For general consumer electronics, Hi-Mart is a nationwide electronics retail chain, and Samsung has its own branded stores for its own products. For the widest selection under one roof — especially for cameras, computer parts, and hard-to-find gadgets — Yongsan Electronics Market in Seoul is a legendary multi-building electronics district.", listOf("Hi-Mart", "Samsung", "Yongsan Electronics Market")))

            SectionHeader("Books")
            BodyText(boldNames("Kyobo Book Centre is Korea's major nationwide bookstore chain, with large flagship branches (notably near Gwanghwamun in Seoul) that are worth a visit even just to browse — most stock at least a modest English-language section alongside the Korean titles.", listOf("Kyobo Book Centre")))

            SectionHeader("Convenience Stores")
            BodyText(boldNames("Convenience stores are everywhere and genuinely useful, not just for snacks — CU, GS25, 7-Eleven, Emart24, and Ministop are the main chains, and any of them sell T-money transit cards, can top up your T-money balance, and carry basics like SIM cards, toiletries, and surprisingly decent ready-to-eat meals (try a convenience-store gimbap or instant ramyeon with the in-store hot water dispenser).", listOf("CU", "GS25", "7-Eleven", "Emart24", "Ministop", "T-money")))
        }
    }
}
