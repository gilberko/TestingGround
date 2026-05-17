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

private val GetThemToActBg = Color(0xFF2D2D2D)
private val GetThemToActHeading = Color(0xFFDDDDDD)

private val getThemToActSections = listOf(
    "Attention Without Action Is Advertising Without Revenue" to
        "There is a common confusion in marketing and persuasion: treating attention as the goal rather than as the precondition. Grabbing someone's attention is necessary but not sufficient. A viewer who watches an ad all the way through, finds it funny, and shares it with friends — but never buys the product — has provided the advertiser with entertainment costs and nothing else. Attention is the door; action is the room beyond it.\n\nThe psychological challenge of driving action is distinct from the challenge of capturing attention, and it requires different tools. The brain that was curious and engaged a moment ago is now being asked to commit, to pay, to change something about its current state. Inertia is powerful. The default is to do nothing. The question is how to make doing something feel easier than doing nothing.",

    "Ask for a Commitment, Not the Act Itself" to
        "Robert Cialdini's work on the commitment and consistency principle (Influence, 1984) established one of the most replicated findings in social psychology: once people commit to a position or a course of action, they tend to act in ways consistent with that commitment, often regardless of whether the original reason for the commitment still applies. The internal pressure to remain consistent with a stated position is strong — inconsistency creates cognitive dissonance, which the mind treats as a threat to self-image.\n\nThe practical application is to separate the commitment from the act. Rather than asking someone to buy immediately, ask whether they are the kind of person who cares about this. Rather than asking for a signature, ask whether they agree in principle. The Freedman and Fraser (1966) foot-in-the-door study showed that homeowners who agreed to place a small sign in their window were later far more likely to agree to a much larger and more intrusive request than those who had not made the initial commitment. The first commitment, however small, shifted self-perception: 'I am someone who does this.' The barrier to the actual act — buying, joining, signing — is substantially lower after a genuine commitment has been made, because now acting is consistent with identity and not acting is a form of self-betrayal.",

    "The Yes Ladder — Build the Habit of Agreeing" to
        "A related but distinct technique builds agreement progressively through a sequence of small requests, each easier than the last, before arriving at the actual ask. The principle is that saying yes is, to a small degree, a habit. Each yes reduces internal resistance slightly. A sequence of yeses in a row creates a kind of psychological momentum — a running score of cooperation — and by the time the actual request arrives, the pattern of agreeing is already established.\n\nThis is related to Cialdini's consistency principle but operates slightly differently: it is less about identity and more about momentum. 'I've been saying yes this whole time — why stop now?' It is used extensively in sales conversations: 'You'd love to save money on your bills, wouldn't you? And you care about your family's security? So you'd want to know if there was a way to do both at once?' Each question is designed to produce a yes. None of them are the actual ask. But by the third yes, the listener has been guided into a frame where agreeing is the established pattern. The request, when it comes, feels like the natural continuation of something already underway rather than a new and separate decision.\n\nThe technique requires care. If any of the small questions feel manipulative or disconnected from the actual offer, the effect reverses — the listener recognises the pattern and distrust takes over. The questions need to be genuine, and the eventual ask needs to follow naturally from them. Used with integrity, it is simply good conversation; used cynically, it is a manipulation that most adults will detect.",

    "Flip the Default — Opt-Out Instead of Opt-In" to
        "One of the most powerful insights from behavioural economics is that the default option — the outcome that occurs if the person does nothing — has a disproportionate effect on behaviour. Richard Thaler and Cass Sunstein (Nudge, 2008) documented this extensively. Eric Johnson and Daniel Goldstein's 2003 study of organ donation rates across European countries found that countries using an opt-out system had donation consent rates of 85–99%, while opt-in countries had rates of 4–27%. The same population, largely the same values — entirely different outcomes because of which way the default ran.\n\nThe commercial application is the free trial with automatic enrolment. Instead of asking someone to take a positive action to join, the offer is structured so that they receive the product for free for a period — and must then take a positive action to stop it. The friction that previously blocked sign-up now blocks cancellation instead. This is the endowment effect in action: once people have something — even briefly, even for free — they experience its potential removal as a loss rather than simply the absence of a gain. Kahneman, Knetsch, and Thaler (1990) established that losses are psychologically roughly twice as painful as equivalent gains. The gym membership auto-renewed, the streaming service after the trial, the software subscription that requires a cancellation call — all are versions of this structure.\n\nThe ethical boundary matters. A free trial that requires a credit card upfront, buries the cancellation process, and sends no reminder before charging is a dark pattern — it exploits inertia rather than offering genuine value. The same structure used transparently — a genuine free period with a clear reminder and easy cancellation — is simply good product design that reduces the friction of starting. Both use the same psychology; the difference is whether the default was set in the customer's interest or against it."
)

@Composable
fun GetThemToActScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GetThemToActBg)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Get Them To Act",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        getThemToActSections.forEach { (heading, body) ->
            Text(
                text = heading,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = GetThemToActHeading,
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
