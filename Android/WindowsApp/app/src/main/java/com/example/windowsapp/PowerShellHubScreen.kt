package com.example.windowsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

private val powerShellLessons = listOf(
    "01 ABOUT\nPOWERSHELL" to "ps_01_about",
    "02 YOUR FIRST\nSCRIPT" to "ps_02_first_script",
    "03 CMDLETS AND\nTHE PIPELINE" to "ps_03_cmdlets",
    "04 VARIABLES AND\nDATA TYPES" to "ps_04_variables",
    "05 OPERATORS AND\nCONDITIONS" to "ps_05_operators",
    "06 LOOPS AND\nITERATION" to "ps_06_loops",
    "07 FUNCTIONS" to "ps_07_functions",
    "08 MODULES" to "ps_08_modules",
    "09 VARIABLE SCOPE\nAND LIFETIME" to "ps_09_scope",
    "10 ERROR AND\nEXCEPTION HANDLING" to "ps_10_errors",
    "11 SCRIPT BLOCKS\nAND CLOSURES" to "ps_11_scriptblocks",
    "12 FILES AND\nDIRECTORIES" to "ps_12_files",
    "13 THE REGISTRY" to "ps_13_registry",
    "14 PROCESSES AND\nSERVICES" to "ps_14_processes",
    "15 NETWORKING" to "ps_15_networking",
    "16 THE OBJECT\nPIPELINE" to "ps_16_objects",
    "17 OBJECT-ORIENTED\nPROGRAMMING" to "ps_17_oop",
    "18 CLASSES AND\nENUMS" to "ps_18_classes",
    "19 REGULAR\nEXPRESSIONS" to "ps_19_regex",
    "20 COM\nAUTOMATION" to "ps_20_com",
    "21 WMI AND CIM" to "ps_21_wmi_cim",
    "22 USING .NET" to "ps_22_dotnet",
    "23 REFLECTION" to "ps_23_reflection",
    "24 ADVANCED\nFUNCTIONS" to "ps_24_adv_functions",
    "25 POWERSHELL\nSECURITY" to "ps_25_security",
    "26 PRACTICAL\nPROJECT" to "ps_26_project",
)

@Composable
fun PowerShellHubScreen(navController: NavController) {
    HubBackground {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "POWERSHELL",
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

        powerShellLessons.chunked(2).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { (label, route) ->
                    Box(modifier = Modifier.weight(1f)) {
                        HackerButton(label, fontSize = 12.sp) { navController.navigate(route) }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        HackerButton("BACK") { navController.popBackStack() }
    }
    }
}

// ── Shared scaffold and helpers for the PowerShell lessons ──────────────────
// PowerShell code is full of '$', which Kotlin treats as a string template.
// Lesson files write '§' instead and these helpers swap it for '$' at runtime,
// so lesson source never contains a raw '$'.

private fun ps(text: String): String = text.trimIndent().replace('§', '$')

@Composable
fun PsLessonScreen(
    navController: NavController,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "─".repeat(28),
            color = HackerGreen,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        content()

        Spacer(modifier = Modifier.height(24.dp))
        HackerButton("BACK") { navController.popBackStack() }
    }
}

@Composable
fun PsSection(title: String) {
    Spacer(modifier = Modifier.height(8.dp))
    SectionHeader(title)
}

@Composable
fun PsText(text: String) = BodyText(ps(text))

@Composable
fun PsCode(code: String) = CodeBlock(ps(code))

@Composable
fun PsOutput(text: String) = OutputBlock(ps(text))

@Composable
fun PsWarn(text: String) = WarningText(ps(text))

@Composable
fun PsExercise(text: String) = ExerciseBlock("EXERCISE", ps(text))

@Composable
fun PsChallenge(text: String) = ExerciseBlock("CHALLENGE", ps(text))
