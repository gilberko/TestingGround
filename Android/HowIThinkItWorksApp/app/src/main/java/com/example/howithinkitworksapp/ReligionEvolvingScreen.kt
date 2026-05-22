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

private val ReligionScreenGray = Color(0xFF2D2D2D)
private val ReligionHeadingColor = Color(0xFFDDDDDD)

private val religionEvolvingSections = listOf(
    "How Religions Change Over Time" to "Religious doctrines are not static. History shows repeated cycles of interpretation, reinterpretation, schism, and gradual drift — even in traditions that claim unchanging truth.\n\nThe Protestant Reformation (1517) began as Martin Luther's challenge to specific Catholic practices and evolved into wholesale theological separation. Vatican II (1962–1965) produced changes in Catholic mass language, interfaith relations, and the doctrine of religious freedom that would have been unrecognizable a generation earlier. The Church of Jesus Christ of Latter-day Saints shifted doctrine on polygamy and racial priesthood eligibility at specific historical moments. Several traditions institutionalize the mechanism directly through \"progressive revelation\" — the doctrine that God continues to reveal new truth to current leaders, making doctrinal evolution theologically legitimate.\n\nThe mechanism is often gradual rather than sudden. Small reinterpretations accumulate. Language shifts slightly. New emphasis replaces old. Scriptures are read through new lenses. Over decades, a tradition can shift substantially without any single change appearing dramatic enough to contest. By the time the shift is large enough to notice, it has been normalized through repeated small steps and is defended by the same authority structures that produced it.",

    "The Leader's Position — Preserving and Innovating" to "Religious leaders occupy an inherently dual role: they are simultaneously guardians of tradition and its authoritative interpreters. The authority to interpret is effectively the authority to evolve — and this authority is often invisible as such.\n\nMax Weber's concept of charismatic authority describes the power dynamic: authority derived from a leader's perceived special connection to the divine, rather than from institution or rationally established rules. Followers believe the leader has access to truth that others do not. This makes the leader's interpretations authoritative almost by definition, independent of their actual relationship to established doctrine.\n\nThe reframing mechanism is reliable: new interpretations can almost always be presented as \"what the tradition has always truly meant.\" Supportive historical precedents are cited; complicating ones are recontextualized. Followers who lack deep theological literacy — the majority in most traditions — have no independent basis to evaluate this. Leaders who are sincere may present genuine opinions as settled doctrine without recognizing the distinction themselves. Those who are less careful may do so deliberately. In both cases, the listener receives the message under the same authority signal, and the listener's processing of it is the same.",

    "Can Believers Spot Mistakes vs. Opinions?" to "Believers who have practiced setting aside critical thinking in the religious domain find it very difficult to reactivate it selectively for specific statements within that domain.\n\nGervais and Norenzayan (2012, Science) demonstrated experimentally that analytical thinking — deliberate, systematic evaluation of evidence — reduces religious belief intensity when primed. The implication runs both ways: higher commitment to religious belief correlates with reduced analytical engagement in that domain. When a domain has consistently been processed intuitively and emotionally, switching to analytical evaluation for specific statements within it is not a simple act of will.\n\nAtran and Ginges (2012, Science) introduced the concept of sacred values — beliefs treated as absolute and non-negotiable, placed outside cost-benefit reasoning entirely. Once a belief is sacralized, challenging it triggers moral outrage rather than rational evaluation. Tetlock and colleagues (2000) showed that even asking people to consider trade-offs involving sacred values produces emotional distress and counter-reasoning. The protective response is automatic, not chosen.\n\nAuthority bias amplifies this further (Milgram 1963; Cialdini 1984): when a perceived divine authority speaks, the listener's default is to seek reasons to agree rather than evaluate critically. A devout believer receiving a leader's opinion may not experience it as an opinion at all. The distinction between mistake, opinion, and established doctrine may simply not be accessible from inside the believing stance.",

    "The Silence of Private Doubt — Pluralistic Ignorance" to "Even when individual doubt exists, it is systematically concealed — and that concealment creates a false picture of unanimous belief that keeps everyone's private doubts private.\n\nThis is pluralistic ignorance, documented by Prentice and Miller (1993, Journal of Personality and Social Psychology): most members of a group privately hold a belief or doubt that differs from the perceived group norm, while each assumes they are the deviant exception. Everyone sees the public display of belief around them and interprets it as evidence that the others genuinely believe. Their own doubt is private, so they conclude they are the outlier.\n\nThe result: a religious community may harbor substantial internal doubt and yet appear — to observers and to each other — as uniformly committed. No one speaks because speaking means being the only one to speak. Asch's conformity experiments (1951) showed how powerfully a unanimous apparent consensus suppresses individual dissent even on factual matters with an obvious correct answer. In a religious community, the stakes of being the lone dissenter are far higher than in a psychology lab.\n\nAccounts of people who have left high-commitment religious communities consistently describe this pattern: years of private doubt during which they were certain that everyone around them genuinely believed, followed by the discovery after leaving that others had quietly doubted throughout as well.",

    "The Cost of Speaking Up — Community and Shunning" to "Even when a believer is willing to voice doubt, the social consequences can be severe enough to make the calculation simple: stay silent.\n\nShunning — formal exclusion from the community for heresy, doubt, or transgression — is documented in several high-commitment traditions. Jehovah's Witnesses practice a formalized shunning extending to family members who leave the faith. Similar mechanisms exist in ultra-orthodox Jewish communities, certain Mormon contexts, and some evangelical Protestant groups. The threatened loss is not abstract: it is the entire social network, family relationships, daily community life, professional connections within the faith, and sometimes housing or employment.\n\nBaumeister and Leary (1995, Psychological Bulletin) documented the need to belong as a fundamental human motivation, as basic as hunger. Threat of exclusion activates physiological threat responses comparable to physical danger. Research by Williams (2007) shows that even brief, trivial social exclusion produces lasting effects. Permanent exclusion from one's entire community is one of the most powerful behavioral controls available — and it does not need to be exercised to function. The credible threat is sufficient.\n\nThe cost structure mirrors a public goods problem: the benefit of speaking up accrues to the community as a whole, while the cost — potential exclusion — falls entirely on the individual who speaks. Rational actors do not speak up in this structure, even when they want to, even when they believe they should.",

    "Religion as Existential Anchor — Why Doubt Is Dangerous Even Inside" to "The deepest barrier is internal. Even a believer who has identified a mistake and is willing to accept social risk faces a profound internal obstacle: the religion is the framework through which they organize reality and manage existential fear.\n\nTerror Management Theory, developed by Greenberg, Pyszczynski, and Solomon (1986) based on Ernest Becker's \"The Denial of Death\" (1973), proposes that cultural worldviews — including religion — function as anxiety buffers against awareness of mortality and existential uncertainty. When mortality awareness is activated, people defend their cultural worldviews more intensely and reject challenges to them more forcefully. Dozens of experiments across cultures support this: mortality salience consistently increases worldview defense.\n\nPascal Boyer (2001, \"Religion Explained\") and Scott Atran (2002, \"In Gods We Trust\") situate religion within cognitive science: it addresses questions that have no satisfying secular alternative — where do we come from, why do bad things happen to good people, what happens after death, why is there suffering? These questions generate genuine distress if left unanswered. Religion makes the unanswerable bearable.\n\nFor the believer whose entire understanding of life, death, suffering, and meaning is organized around a religious framework, questioning that framework is not merely questioning a set of propositions. It is threatening the only structure that renders existence manageable. The internal resistance to doubt is not primarily cognitive — it is existential. This is not weakness or cowardice. It is a fully rational response to an existential threat. The person who might lose their faith does not lose a set of beliefs. They lose the anchor that has made everything else make sense."
)

@Composable
fun ReligionEvolvingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ReligionScreenGray)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Religion Is Evolving — How?",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        religionEvolvingSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = ReligionHeadingColor,
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
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Back", color = Color.White, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
