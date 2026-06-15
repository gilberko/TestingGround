package com.example.windowsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.windowsapp.ui.theme.HackerGreen

@Composable
fun WFPSublayersScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "WFP SUBLAYERS",
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "─".repeat(28),
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        SectionHeader("SUBLAYER CONCEPT")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Within each WFP layer, multiple sublayers can exist. A sublayer is a named, weighted grouping for filters. You add a sublayer with FwpmSublayerAdd.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "FWPM_SUBLAYER0 sublayer = {0};\n" +
            "sublayer.sublayerKey = MY_SUBLAYER_GUID;\n" +
            "sublayer.displayData.name = L\"MyDriver Sublayer\";\n" +
            "sublayer.weight = 0x8000; // mid-range weight\n\n" +
            "FwpmSublayerAdd(engineHandle, &sublayer, NULL);"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Weight is a UINT16 (0–0xFFFF). Higher weight means the sublayer is evaluated first. Filters within a sublayer are matched in priority order — the first matching filter wins for that sublayer.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("WFP includes built-in sublayers: FWPM_SUBLAYER_UNIVERSAL (weight 0, always present) and FWPM_SUBLAYER_INSPECTION. If you don't specify a sublayer when adding a filter, it goes into FWPM_SUBLAYER_UNIVERSAL.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("HOW ARBITRATION WORKS")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Each sublayer independently evaluates its filters and produces a verdict: PERMIT or BLOCK.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("The verdicts from all sublayers are then arbitrated. The arbitration rule is simple: the highest-weight sublayer's verdict takes priority. Lower-weight sublayers cannot override a decision made by a higher-weight sublayer.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Sublayer A (weight=0xFFFF) -> BLOCK\n" +
            "Sublayer B (weight=0x8000) -> PERMIT\n" +
            "Sublayer C (weight=0x0001) -> BLOCK\n\n" +
            "Result: BLOCK  (sublayer A wins)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "Sublayer A (weight=0xFFFF) -> PERMIT\n" +
            "Sublayer B (weight=0x8000) -> BLOCK\n\n" +
            "Result: PERMIT  (sublayer A wins)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("If the highest-weight sublayer has no matching filter, the next sublayer's verdict is used, and so on down to FWPM_SUBLAYER_UNIVERSAL.")

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader("VETO AND ALLOW OVERRIDE")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Can a callout cast a veto if a higher sublayer approved? No. A lower-weight sublayer cannot override a PERMIT from a higher-weight sublayer — it simply loses the arbitration.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("Can a callout allow what was blocked by a higher sublayer? Also no — the higher-weight BLOCK wins.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("To block traffic that another driver permits, your filter must be in a sublayer with higher weight than the permitting filter. Weight determines who has the final say.")
        Spacer(modifier = Modifier.height(8.dp))
        BodyText("There is one additional mechanism: FWPS_FILTER_FLAG_CLEAR_ACTION_RIGHT. When a terminating callout sets this flag along with a BLOCK verdict, lower-weight sublayers are prevented from overriding it even if they would normally have the right.")
        Spacer(modifier = Modifier.height(8.dp))
        CodeBlock(
            "// In classifyFn, make a non-overridable block:\n" +
            "if (classifyOut->rights & FWPS_RIGHT_ACTION_WRITE) {\n" +
            "    classifyOut->actionType = FWP_ACTION_BLOCK;\n" +
            "    classifyOut->rights &= ~FWPS_RIGHT_ACTION_WRITE;\n" +
            "    // clears the right so lower sublayers cannot permit\n" +
            "}"
        )

        Spacer(modifier = Modifier.height(32.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
}
