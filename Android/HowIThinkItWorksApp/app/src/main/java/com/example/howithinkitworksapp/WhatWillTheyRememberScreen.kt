package com.example.howithinkitworksapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

private val WwtrBg = Color(0xFF2D2D2D)
private val WwtrHeading = Color(0xFFDDDDDD)

private val whatWillTheyRememberSections = listOf(
    "What Gets Remembered — and What Gets Lost" to
        "When people are exposed to a sequence of information — a list, a talk, a lesson, a presentation — recall is not uniform across positions. A characteristic U-shaped curve emerges: the beginning and the end are recalled well; the middle falls off. This pattern, known as the serial position effect, was first documented by Hermann Ebbinghaus in his 1885 work on memory and has since become one of the most robustly replicated findings in memory research, reproduced across formats, languages, cultures, and ages.\n\nA lecturer who delivers an hour-long talk may feel that the dense, carefully prepared middle section was the most important part. For most of the audience, it will be the least retained. What sticks is what opened the talk and what closed it.",

    "Why the Beginning Sticks — Primacy and the Orienting Response" to
        "Two mechanisms drive the primacy effect — the strong recall of early material.\n\nThe first is biological. Entering a new environment — a new room, a new voice, an unfamiliar context — triggers what Evgeny Sokolov (1963) described as the orienting response: a brief spike of alertness in which the brain elevates attention and directs processing toward potential significance. The novelty signals that something may be important, and the noradrenergic system sharpens encoding during this window. What happens in those first few minutes is processed with the heightened attention that novelty commands. The brain is, in effect, scanning for what matters.\n\nThe second mechanism is about encoding time. First items in a sequence have more opportunity to be rehearsed before the next wave of information arrives. That rehearsal consolidates material into long-term memory more deeply than later items receive, because later items are followed immediately by more incoming content and have less time to consolidate.\n\nBoth mechanisms operate simultaneously and point in the same direction: the beginning of a session has structural advantages that the middle does not.",

    "The Forgettable Middle" to
        "Attention is not an infinite resource. After the initial orienting spike subsides — novelty has been assessed, no alarm was necessary — sustained concentration drifts toward its natural lower baseline. The brain is no longer on alert; no closure signal has yet arrived. The middle of a session exists in this attentional trough.\n\nBut biological attention drift is not the only explanation. Interference theory contributes substantially. Proactive interference: material from the beginning actively interferes with the encoding of middle items, competing for memory resources. Retroactive interference: material from the end reaches back and interferes with the retrieval of middle items. The middle is squeezed from both sides.\n\nIt gets displaced not just because no one was paying attention at the time, but because both adjacent parts of the sequence actively compete with it during encoding and during later recall. The middle is structurally disadvantaged.",

    "Why the End Sticks — Recency and the Closure Signal" to
        "The recency effect — strong recall of the most recent material — works through a different mechanism from primacy. George Miller's (1956, Psychological Review) foundational work on working memory established that it holds approximately 7 items (plus or minus 2) at any given moment. Items at the end of a sequence are still present in working memory when the session concludes. They haven't been displaced yet by additional incoming material and can be retrieved directly, without the decay that affects older information.\n\nA second mechanism: the end of a session is itself a signal. The wrap-up, the \"in conclusion,\" the shift in the speaker's tone, the visual cue that things are finishing — these are change events that re-trigger orienting attention. The last few things said fall into a window of renewed alertness, similar to but smaller than the opening window.\n\nDaniel Kahneman and Barbara Fredrickson's peak-end rule (1993) adds a further dimension: in evaluating an experience overall, people weight the peak moment and the ending disproportionately — not just what they remember, but how they feel about the entire session, is shaped by how it ended. A presentation that trails off with logistics and housekeeping leaves a different emotional residue than one that closes with a clear, memorable conclusion.",

    "The Von Restorff Effect — Surprise the Middle" to
        "Hedwig von Restorff (1933) showed that an item which stands out distinctively from its surroundings — different in color, format, category, emotional tone, or level of surprise — is recalled far better than surrounding items of the same type. This is the isolation effect: distinctiveness draws automatic attention, and what gets attention gets encoded more deeply.\n\nThe mechanism is the orienting response applied to a specific moment rather than to the opening of a session. If something surprising, vivid, emotionally resonant, or structurally unexpected appears amid otherwise uniform content, the brain notices the break in pattern and allocates the same quality of attention it would give to a novel situation.\n\nA well-placed story, a striking example, an unexpected demonstration, a counterintuitive claim, a moment of humor — any of these can effectively create a second encoding peak in the middle of a session. The middle is not doomed to be forgotten. It requires deliberate design to compete with the structural advantages of the bookends.",

    "Practical Implications — Designing What Gets Remembered" to
        "The structure of a presentation, lesson, or conversation matters as much as its content, because where information appears in the sequence reliably predicts whether it will survive in memory.\n\nThe most important messages belong at the beginning or at the end — not buried in the middle. The opening should use its elevated attention window intentionally rather than spending it on agenda-setting, logistics, and housekeeping. The closing should deliver a clear, memorable conclusion while recency and renewed attention are both available, not trail off with administrative details.\n\nIn the middle, use the von Restorff effect deliberately. A story, a striking image, a concrete example that runs counter to expectation, a strong question posed and left briefly open — any structural break that re-triggers the orienting response creates a new encoding peak. The middle can hold material worth remembering; it just needs something that makes it distinct.\n\nFor long sessions, deliberate chunking helps: breaking a single extended block into shorter segments, each with their own opening and closing, multiplies the number of high-retention windows available. Each new segment brings a small primacy spike; each closing brings a recency advantage. The remembered content from a session structured this way substantially exceeds what survives from the same content delivered as a single unbroken block."
)

@Composable
fun WhatWillTheyRememberScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WwtrBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "What Will They Remember?",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        whatWillTheyRememberSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = WwtrHeading,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = body,
                fontSize = 16.sp,
                color = Color.White,
                lineHeight = 24.sp,
                modifier = Modifier.padding(bottom = 28.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { navController.popBackStack() },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Back", color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
