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

private val StorytellingBg = Color(0xFF2D2D2D)
private val StorytellingHeading = Color(0xFFDDDDDD)

private val storytellingSections = listOf(
    "A Story Bypasses the Guard" to
        "When someone presents you with facts and arguments, a critical evaluative process activates. You assess the source, weigh the evidence, notice the gaps, and form a judgement about whether to update your beliefs. This is what engagement with explicit persuasion looks like: effortful, sceptical, adversarial to the message. A story does something different. Melanie Green and Timothy Brock (2000) described the transportation-imagery model: when a person becomes absorbed in a narrative, they are 'transported' into the story world, and during that transportation, their critical defences lower substantially. Beliefs change not because the argument was evaluated and found convincing, but because the transported reader or viewer has, in some sense, experienced what the story depicts. The story did not persuade them — it let them persuade themselves.\n\nGreen and Brock found that transported readers showed significantly greater belief change and attitude shifts than those who read the same information in non-narrative form — even when the facts were identical. The mechanism is not comprehension; it is immersion. The message enters sideways, through a door that critical evaluation does not guard.",

    "The Brain on Story vs. the Brain on Data" to
        "Uri Hasson's research at Princeton (2008) used fMRI to compare neural activity in listeners processing a meaningful narrative versus a meaningless version of the same recording. During the story, the listener's brain activity showed coupling with the speaker's — not just in language areas, but in sensory cortex, motor cortex, and emotional processing regions. The story was not just being decoded as language; it was being partially simulated. A description of running activates motor cortex. A description of a smell activates olfactory regions. A description of a character's fear activates the amygdala. The brain does not cleanly separate understanding from experiencing.\n\nData does not do this. A graph or a table activates Broca's area and Wernicke's area — the language processing centres — and comparatively little else. The same information presented as narrative activates a substantially larger and more emotionally engaged network. This is not a failure of rational processing; it is simply how the brain distributes cognitive resources. Stories engage more of the brain because more of the brain finds them relevant. Engagement, once multi-sensory and emotional, is also harder to disengage from — which is why a compelling story holds attention in a way that a well-made chart does not.",

    "Character, Stakes, and Choosing a Side" to
        "The engine of narrative is a character whose outcome is uncertain. Theory of Mind — the cognitive capacity to model the mental states of other beings, described by Premack and Woodruff (1978) — activates automatically when we encounter a character making decisions. We simulate their perspective, their desires, their fears. We predict what they will do and feel something when we are right or wrong. This happens before reasoning begins.\n\nPaul Zak's research at Claremont Graduate University (2013) demonstrated that character-driven narratives with a clear arc of tension and resolution caused participants to release oxytocin — the neurochemical associated with social bonding and trust — and that the degree of oxytocin release predicted subsequent prosocial behaviour, including charitable donations. The story was not just engaging; it was chemically constructing a bond between the listener and the character, and that bond transferred to behaviour.\n\nChoosing a side amplifies this. Once a viewer or reader identifies with one character rather than another, they are invested — they have something at stake in the outcome. The tension becomes personal. This is why sports are gripping even for people with no connection to either team if they have picked a side, and why the same film feels completely different depending on which character the viewer's sympathy falls on. Storytellers construct this identification deliberately: the first thing a film does is establish who you are supposed to root for. Once that identification is made, attention follows almost automatically, because the outcome now feels like something that matters personally.",

    "The Individual Over the Statistics — The Identifiable Victim Effect" to
        "Paul Slovic (2007) documented what he called the collapse of compassion and the identifiable victim effect: people respond far more emotionally and behaviourally to the story of a single, identified individual in need than to statistics about large numbers of people in the same situation. In one study, donations to aid a single named child were substantially higher than donations triggered by describing the same child as one of millions affected. Adding the statistical context — millions are suffering — did not increase generosity; it reduced it. The vastness of the problem seemed to deactivate the emotional response, not amplify it.\n\nThe mechanism appears to be that the emotional system can model one person: a name, a face, a specific situation. It cannot model three million people. Statistical victims do not trigger the same oxytocin and empathy response that an individual story does, because the emotional system did not evolve to process abstractions of that scale. Stalin is reputed to have said that one death is a tragedy, a million deaths a statistic — a cynical observation that turns out to be neurologically accurate.\n\nThe practical consequence for anyone trying to move people is that the individual story is almost always more powerful than the aggregate figure. The charity ad that shows one child, named, with a specific situation, consistently outperforms the one that leads with country-wide statistics. The business case built around one customer's journey will often land harder in a boardroom than a slide of survey data. The story makes it real in a way the numbers cannot.",

    "A Graph Tells You What Happened; a Story Makes You Feel Like You Were There" to
        "The comparison between story and presentation is not really about format — it is about which parts of the audience's brain are engaged and for how long. A presentation asks the audience to process information and draw conclusions. A story asks the audience to follow, feel, and experience. The first mode keeps a distance between the listener and the material; the second collapses it.\n\nSteve Jobs's 2005 Stanford commencement address is a case study in applied storytelling. He had data that could have been presented — Apple's market performance, the success of the Mac, the growth of Pixar. He told three stories instead: about dropping out, about being fired, about cancer. Each story had a character (himself, younger and uncertain), stakes (what would become of him), and resolution (something was learned). He said almost nothing in the abstract register; nearly the entire speech is specific, sensory, narrative. Nike's most effective advertising almost never mentions shoe specifications — it follows athletes through struggle toward achievement. The Marlboro Man was a character, not a fact sheet about nicotine.\n\nNone of this means data is useless — it means data lands inside a story, not instead of one. The story creates the frame; the data provides the proof within it. An audience that has been transported and emotionally engaged will accept evidence they might have rejected in the cold register of a slide deck. The story is not a decoration around the argument — it is the argument's most effective delivery system."
)

@Composable
fun StorytellingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StorytellingBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Storytelling — Keeping the Attention",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        storytellingSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = StorytellingHeading,
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
