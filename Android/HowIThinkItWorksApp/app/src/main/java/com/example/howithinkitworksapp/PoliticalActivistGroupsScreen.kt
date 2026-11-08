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

private val PagBg = Color(0xFF2D2D2D)
private val PagHeading = Color(0xFFDDDDDD)

private val politicalActivistGroupsSections = listOf(
    "The Pattern in Brief" to
        "Many political activist campaigns, whatever side they are on, follow a recognizable sequence:\n\n" +
        "1. A shocking story, image or video that is hard to ignore and leaves the viewer with an almost unbearable feeling: fear, grief, horror.\n" +
        "2. A cause and a culprit: here is who did this, and here is what must be done. The world splits into good and evil.\n" +
        "3. The unbearable feeling turns into anger at the culprit.\n" +
        "4. Slogans, chants and anthems, repeated until each one instantly brings back the whole story and its emotion.\n" +
        "5. A big crowd, which signals to newcomers that \"everyone sees it\".\n" +
        "6. Disruptive acts such as shouting, barricading and silencing other voices. To supporters these can look like the natural reaction of someone facing evil.\n\n" +
        "Research largely supports this picture. Steps 1 to 5 match well-studied mechanisms. Step 6 is more mixed: it works on people who already agree, but it often backfires with the wider public. The sections below go through each step.\n\n" +
        "A note on balance: the pattern is a tool, not a verdict on any cause. Groups across the whole political spectrum use it, and some shocking images expose real wrongs. The point here is how the persuasion works, not which side is right.",

    "Step 1: The Moral Shock" to
        "Sociologists James Jasper and Jane Poulsen (1995, Social Problems) studied how animal-rights and anti-nuclear movements recruit people. Many recruits did not come through friends already in the movement. They came through what Jasper and Poulsen called a \"moral shock\": an event or image so disturbing that it jolts a person into feeling that something in the world is deeply wrong and must be acted on. Movements produce these shocks on purpose, with graphic photos, footage of suffering and stories of victims.\n\n" +
        "Why it works: the brain is built to prioritize threat and suffering. Roy Baumeister and colleagues' review \"Bad Is Stronger Than Good\" (2001) found that negative information gets more attention, more processing and more memory than positive information of equal size. Arne Öhman and colleagues (2001) showed that threatening images, such as snakes or angry faces, are detected faster than neutral ones. Your attention is captured before you decide whether to pay attention.\n\n" +
        "This matches your observation: the first step is not an argument. It is an emotional wound, and it is hard to look away from.",

    "Step 2: Naming the Culprit (Good vs Evil)" to
        "Sociologists David Snow and Robert Benford (1988) described how movements build \"collective action frames\" with three jobs:\n\n" +
        "• Diagnostic framing: what is wrong, and who is to blame.\n" +
        "• Prognostic framing: what must be done.\n" +
        "• Motivational framing: why you, personally, must act now.\n\n" +
        "This is almost exactly the sequence you described. William Gamson (1992) added that the most mobilizing frame is the \"injustice frame\". Suffering alone is not enough; it must be caused by someone. A tragedy with no culprit produces grief. A tragedy with a culprit produces a cause.\n\n" +
        "Why the culprit becomes a monster: Kurt Gray and Daniel Wegner (2009) described \"moral typecasting\". In a moral story we place people into two roles, the agent who does harm and the victim who suffers. Once someone is cast as the agent, we stop seeing them as someone who can feel or suffer. That is why empathy for the \"enemy\" shuts off.\n\n" +
        "Why it becomes unthinkable to listen to them: Linda Skitka's research on moral conviction (2005, 2010) shows that once people see an issue as a matter of right and wrong, rather than preference, they tolerate disagreement less. They want more distance from people who disagree, and they may accept almost any means to the end. Listening to the other side starts to feel like collaborating with evil.",

    "Step 3: From Unbearable Feeling to Anger" to
        "Emotion researchers have shown that what we feel depends on how we interpret a situation, not only on the situation itself. This is appraisal theory (Smith & Ellsworth, 1985). The same bad event produces different emotions:\n\n" +
        "• If nobody is responsible, we feel sadness.\n" +
        "• If the outcome is uncertain and threatening, we feel fear.\n" +
        "• If someone is to blame, we feel anger.\n\n" +
        "So naming a culprit does more than explain the tragedy. It changes the emotion. Sadness and fear are passive and painful. Anger feels powerful and gives the pain a target. Jennifer Lerner and Dacher Keltner (2001) found that angry people feel more certain and more in control than fearful people. In a way, anger is a relief from helplessness.\n\n" +
        "Anger is also the emotion that moves people to act. Martijn van Zomeren and colleagues (2004, 2008) showed in their Social Identity Model of Collective Action that anger on behalf of one's group is one of the strongest predictors of joining protests. Online, William Brady and colleagues (2017, PNAS) analysed over half a million tweets on political issues. Each additional moral-emotional word, such as \"evil\", \"shame\" or \"hate\", raised the chance of being retweeted by about 20%. A later meta-analysis found a smaller but still real effect of about 12%. Most of that spread happened among people who already agreed.",

    "Step 4: Slogans and Anthems (Repetition Keeps the Wound Open)" to
        "A slogan is short, but it carries a lot. Through priming and spreading activation (see the Priming screen), a concept in memory is linked to everything that was active when it was learned. If a chant was first heard alongside the shocking images and the anger at the culprit, then hearing the chant later activates that whole network: the tragedy, the villain and the feeling. The slogan becomes a retrieval cue that reopens the wound.\n\n" +
        "Three mechanisms make repetition powerful:\n\n" +
        "• Emotional memories stick. James McGaugh's work (2004) showed that emotional arousal, through stress hormones and the amygdala, strengthens how memories are stored. The original shock is stored more deeply than ordinary information, which makes it easier to bring back.\n" +
        "• Repetition feels like truth. In the illusory truth effect (Hasher, Goldstein & Toppino, 1977; Fazio et al., 2015), statements we have heard repeatedly are rated as more true, even when people know better. Familiarity is mistaken for accuracy.\n" +
        "• Simplicity helps. Short, rhythmic phrases are easy to process, and things that are easy to process feel more true and more likeable.\n\n" +
        "Your point is correct: repetition keeps the emotion fresh. It does not let the original feeling fade the way most feelings naturally do.",

    "Step 5: The Crowd as Proof" to
        "In a classic street experiment, Stanley Milgram, Leonard Bickman and Lawrence Berkowitz (1969) had groups of different sizes, from 1 to 15 people, stop on a busy New York sidewalk and stare up at a building window. The bigger the staring group, the more passers-by looked up and the more stopped to join. Nobody explained anything. The crowd itself was the message.\n\n" +
        "Robert Cialdini (1984) called this social proof. When we are unsure, we treat what many others are doing as evidence of what is correct. A newcomer seeing thousands of people chanting does not see thousands of separate opinions. They see one signal: \"this is obviously real and obviously important\". The crowd also puts quiet pressure on doubters inside it. If everyone around you seems certain, voicing a doubt feels risky (see the Bandwagon Effect, and pluralistic ignorance on the Religion Is Evolving screen).",

    "Step 6: Disruption as \"Righteous Reaction\" (what research shows)" to
        "You suggested that aggressive acts, such as barricading, shouting and not letting others speak, can look like the genuine reaction of someone who was wronged by evil. Research says this is partly true, but it depends heavily on who is watching.\n\n" +
        "Where you are right, with people who already agree: Jillian Jordan and colleagues (2016, Nature) showed that people who visibly punish wrongdoing, even when they were not harmed themselves, are seen as more trustworthy. Outrage works as a signal of moral character. To someone who already accepts the good-vs-evil story, a person shouting down the \"monster\" looks brave and sincere, not aggressive.\n\n" +
        "Where it often backfires, with the wider public: Matthew Feinberg, Robb Willer and Chloe Kovacheff (2020, JPSP) called this \"The Activist's Dilemma\". Extreme tactics, such as blocking traffic, property damage or disrupting events, get attention, but they reduce public support for the cause. Observers see the activists as unreasonable and identify less with them. Omar Wasow (2020, American Political Science Review) found that violent protests in the 1960s US shifted nearby voters toward \"law and order\" candidates, while nonviolent protests shifted opinion toward the protesters' cause. Maria Stephan and Erica Chenoweth (2008, and their 2011 book Why Civil Resistance Works) studied 323 campaigns from 1900 to 2006. Nonviolent campaigns succeeded about 53% of the time, compared with 26% for violent ones.\n\n" +
        "The complication, the radical flank effect: Herbert Haines (1984) and later Brent Simpson and colleagues (2022) found that a radical wing can sometimes make the moderate wing of the same movement look more reasonable, and so increase support for the moderates.\n\n" +
        "In short, disruption strengthens the bond inside the group and can look righteous to sympathizers, but outsiders often see aggression rather than a reaction to evil.",

    "The Real Picture Is Complex, and Why Shaken Minds Want Simple Stories" to
        "Your closing point is well supported. Real conflicts rarely contain pure villains. Roy Baumeister, in Evil: Inside Human Violence and Cruelty (1997), called the pure villain \"the myth of pure evil\". People who do harm almost never see themselves as evil. They usually believe they were provoked, threatened or acting for a good reason. Lee Ross's fundamental attribution error (1977) adds that we explain other people's bad actions by their character (\"they are monsters\") while overlooking pressures, history and circumstances. A simplified story is useful for mobilizing people, but it is not an accurate map.\n\n" +
        "Why do people who have just been shaken prefer the simple version? Several lines of research point the same way:\n\n" +
        "• Need for cognitive closure. Arie Kruglanski and Donna Webster (1996) showed that under stress, time pressure or threat, people want a firm answer quickly and avoid ambiguity. A clear villain gives closure. \"It's complicated\" does not.\n" +
        "• Uncertainty–identity theory. Michael Hogg (2007) found that when people feel uncertain about themselves or the world, they are drawn to groups with clear boundaries, clear beliefs and strong leadership. The more uncertain they feel, the more extreme the groups they accept.\n" +
        "• The comfort of an enemy. Daniel Sullivan, Mark Landau and Zachary Rothschild (2010, JPSP), \"An Existential Function of Enemyship\", found that when people's sense of control was threatened, they gave more power and influence to an enemy. A world where bad things happen randomly is terrifying. A world where bad things happen because of a specific enemy is frightening but understandable, and an enemy can be fought. The enemy restores a sense of order.\n\n" +
        "This is the hidden link between Step 1 and Step 2. The shock creates an unbearable feeling of helplessness and chaos, and the culprit offers relief from it. Accepting that the world contains tragedies with tangled causes, and no single monster to blame, means staying with that discomfort. The simple story removes it, which is why it is so persuasive."
)

@Composable
fun PoliticalActivistGroupsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PagBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Political Activist Groups",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        politicalActivistGroupsSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PagHeading,
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
