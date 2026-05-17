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

private val GrabAttentionBg = Color(0xFF2D2D2D)
private val GrabAttentionHeading = Color(0xFFDDDDDD)

private val grabAttentionSections = listOf(
    "The Attention Problem — Noise Is the Default" to
        "Human attention did not evolve for the modern media environment. The brain is wired to filter, to habituate — repeated stimuli fade from conscious awareness quickly (Sokolov, 1963). An ad seen once is noticed; seen fifty times in the same form, it is no longer seen. Every message competes with every other message, and most of them lose before the content has a chance to work. The first problem any marketer, filmmaker, or communicator faces is not persuasion — it is simply getting through the noise. Every other technique is irrelevant until the audience has actually looked.",

    "Primal Triggers — Sex and Food" to
        "The fastest route to attention is through what the brain was built to care about before culture existed: survival and reproduction. Food and sex are processed at the level of the limbic system — evolutionarily ancient, fast, and largely involuntary. A perfume ad with an attractive person triggers this system before the viewer has consciously decided to pay attention. A food ad with an extreme close-up of melting chocolate activates appetite circuits reflexively. These cues cut through noise because they are addressed to a part of the brain that cannot easily be switched off. The attention arrives first; the reasoning about why comes after, if at all.\n\nThe catch is the connection. Grabbing attention with an attractive person or an extreme food shot only works if the advertiser can then bridge that attention to the actual message. If the link between the hook and the product is weak or unclear, the audience remembers the attractive person — not the brand. The attention was captured; the message was not delivered. The primal trigger is the door; getting through it is a separate task.",

    "The Knowledge Gap — The Mind Hates the Unresolved" to
        "George Loewenstein (1994) described the knowledge gap — the psychological discomfort created when we notice that there is something we don't know but feel we should. The brain is a prediction machine; something that cannot be predicted or categorized creates a kind of irritation, a drive to resolve the ambiguity. Advertisers exploit this through deliberate strangeness: an image that doesn't make sense, a scene cut before it resolves, a headline that means nothing until you read the next line. The viewer cannot look away because looking away would mean leaving the gap open. Curiosity is discomfort in search of relief, and the ad has made itself the source of that relief.\n\nThere is a practical constraint, however. The knowledge gap requires at least minimal attention to register — someone passively listening to the radio or glancing at a billboard for two seconds is not going to puzzle over an unresolved image. In those contexts, a full riddle goes unprocessed. The technique works best when the audience is already paying attention, or when the knowledge gap is paired with something that grabs attention first — a visual anomaly, a provocative sound, something that earns the attention required for the curiosity to take hold.",

    "Surprise — The Interrupted Expectation" to
        "A summer day in New York City. \"Summer in the City\" by The Lovin' Spoonful plays cheerfully over slow shots of people going about their day. Everything is normal. Then a Bonwit Teller department store explodes. The opening of Die Hard With a Vengeance (1995) is a textbook example of surprise as an attention mechanism: the audience was not expecting it, the contrast between the cheerful music and the sudden violence is extreme, and the result is that everyone in the theatre is immediately, completely awake.\n\nShortly after, the villain Simon Gruber calls the NYPD and begins his demands — launching a game of \"Simon Says\" with the city. The scene functions as if the film itself is answering the question forming in every viewer's mind: do I have your attention now? When the villain steps forward and claims the moment, he is not just talking to the police — he is addressing the audience too, verbalizing exactly what they are already experiencing. A character who names the audience's inner state creates an unsettling sense of being known, which is a version of the same mechanism that makes pacing powerful in hypnosis: the one speaking appears to have access to what you are thinking. Here it makes the villain feel more threatening, not less — because it is not comfort he is offering, but control.\n\nSurprise works because the brain is a prediction engine that allocates attention based on what it expects to happen next. When expectation is violated — when something happens that the predictive model did not account for — the brain responds with a sharp spike of orienting response: resources are reallocated, attention snaps to the source of the violation, the processing priority of everything else drops. This is not a choice; it is automatic. The greater the violation of expectation, and the stronger the contrast with what came before, the more powerful the snap. The cheerful music made the explosion more shocking; without it, the explosion would have been jarring but ordinary. Contrast amplifies surprise, and surprise commands attention that nothing else could have earned as quickly.\n\nBloopers work by the same mechanism. A polished performance sets up a strong expectation of control — the actor knows their lines, the presenter is composed, the scene is running as planned. When something goes wrong unexpectedly — a stumble, a flubbed line, an unplanned prop collapse — the prediction is violated and attention snaps to it completely. The blooper captures attention partly because it is funny, but also because it is real in a way the surrounding performance was not. The unscripted moment breaks the surface of the constructed world, and the brain, wired to prioritize genuine, uncontrolled events over staged ones, cannot look away. It is a reminder that surprise does not need to be dramatic or violent to be effective — it only needs to be genuinely unexpected.",

    "Contrast — What Stands Out Against the Background" to
        "The brain does not perceive the world in absolute terms — it perceives differences. The visual cortex is packed with neurons that respond not to uniform brightness or uniform colour but to edges: the boundary between light and dark, between one hue and the next. David Hubel and Torsten Wiesel's Nobel Prize–winning work in the early 1960s established that the primary visual cortex is organised around contrast detection at the level of individual cells. The brain is, at its most fundamental level, a contrast-detection machine, and this shapes not just what is seen but what is noticed.\n\nThe mechanism behind the orienting response described by Sokolov (1963) is a specific case of this: if the environment is uniform — the same sounds, the same visuals, the same rhythm — the brain habituates. Something that breaks that uniformity demands processing resources. The break can be visual (a bright colour against a dull background), auditory (a single voice in silence), spatial (one simple image surrounded by complex ones), temporal (a pause in rapid speech), or conceptual (a childlike drawing in a formal report). In every case, the mechanism is the same: the difference signals potential relevance, and attention follows automatically.\n\nSurprise, described in the previous section, is contrast in the temporal dimension — what just happened versus what was expected. Contrast is the more general case. A product displayed in white against a black background in a magazine full of colourful spreads will attract the eye before the reader has made any decision. The Apple '1984' advertisement placed a lone running figure in vivid colour against a grey, uniform crowd. The single red coat in Schindler's List (1993) draws the eye through a black-and-white scene. A presenter who whispers after speaking at normal volume for fifteen minutes will silence a room.\n\nThe practical consequence is that contrast works in proportion to its environment. The loudest ad in a quiet medium is more effective than the same ad in a loud one. A calm, minimalist message in a space full of visual noise will be noticed for its restraint. The brain is not responding to the absolute level of any feature — it is responding to the departure from the surrounding baseline. Whatever the baseline is, the thing that breaks it will draw the eye.",

    "Triggering a Known Pattern — Out of the Blue" to
        "Sometimes the hook is not novelty but familiarity arriving somewhere it has no business being. There is a key distinction: a familiar pattern in its expected context does nothing. A Coca-Cola logo on a billboard at a beach party is entirely unremarkable — it belongs there, it fits, the brain files it away and moves on. But if the speakers at that same beach party suddenly start playing the Nokia ringtone, something different happens entirely. The brain pattern-matches immediately — that sound, that specific sound — and then the context crashes against it. A Nokia ringtone? Here? At a beach party? The collision between the deeply familiar pattern and the completely wrong context produces an involuntary \"say what?\" — attention snaps to the source before any conscious decision is made.\n\nThis is the mechanism: recognition is involuntary, but recognition in an unexpected place is doubly powerful. The familiar pattern triggers an automatic response — the brain has been tuned to react to things it already knows — and the mismatch with context adds a layer of mild disorientation that demands resolution. A well-known logo in an expected context is wallpaper. The same logo in a wildly unexpected context becomes a question. The Nokia ringtone at the beach party is not just familiar; it is familiar and wrong, and the brain cannot let that go unresolved. The familiar thing arrives out of the blue, the response follows automatically — and in that moment, the message has a window."
)

@Composable
fun GrabTheirAttentionScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrabAttentionBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Grab Their Attention",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        grabAttentionSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = GrabAttentionHeading,
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
