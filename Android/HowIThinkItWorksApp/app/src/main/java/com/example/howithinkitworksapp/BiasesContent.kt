package com.example.howithinkitworksapp

data class BiasTopic(val key: String, val title: String, val sections: List<TextSection>)

val biasTopics = listOf(
    BiasTopic(
        key = "anchoring",
        title = "Anchoring",
        sections = listOf(
            TextSection(
                heading = "What Is Anchoring",
                body = "The first number you encounter when making an estimate — no matter how arbitrary — becomes a reference point your brain anchors to. Every subsequent number you consider gets pulled toward it. This is not a minor statistical curiosity. It is a robust, repeatable distortion of judgment.\n\nThe effect was documented by psychologists Amos Tversky and Daniel Kahneman in their landmark 1974 paper \"Judgment Under Uncertainty: Heuristics and Biases,\" published in the journal Science. It helped earn Kahneman a Nobel Prize in Economics in 2002."
            ),
            TextSection(
                heading = "The Classic Experiments",
                body = "Tversky and Kahneman rigged a wheel to land only on 10 or 65. Participants watched it spin, then answered: \"Is the percentage of African nations in the UN higher or lower than that number? What's your estimate?\"\n\nThe wheel result was obviously random — participants knew it. And yet: the group seeing 65 gave a median estimate of 45%. The group seeing 10 gave a median estimate of 25%. The same question, the same knowledge, opposite anchors — and a 20-point gap in estimates determined by nothing more than a rigged wheel.\n\nIn a second experiment, participants had 5 seconds to estimate either 1×2×3×4×5×6×7×8 or 8×7×6×5×4×3×2×1. The first group anchored on small early numbers — median estimate: 512. The second group anchored on large early numbers — median estimate: 2,250. The correct answer is 40,320. Both groups were wildly wrong, but in opposite directions determined entirely by which end of the sequence they saw first."
            ),
            TextSection(
                heading = "Why It Happens",
                body = "Two mechanisms are at work.\n\nThe first is anchoring-and-adjustment: you start from the anchor and adjust outward until the answer feels plausible. But the adjustment is effortful and you stop too soon — always clustering closer to the anchor than the truth warrants.\n\nThe second, and arguably more powerful, is selective accessibility: when you consider the anchor, your mind automatically retrieves information consistent with it. Asked \"is it more or less than 65%?\", your memory searches for evidence of high quantities. That biased evidence pool then shapes the estimate you ultimately produce — before you've even started adjusting. You're working with a skewed set of facts from the outset.\n\nBoth mechanisms operate together, and neither is easy to override consciously."
            ),
            TextSection(
                heading = "You Can't Ignore It Even When You Know",
                body = "The deeply counterintuitive finding is that knowing about anchoring doesn't protect you from it.\n\nIn experiments where participants were explicitly told the anchor was random and instructed to ignore it, the anchor still influenced their estimates. They moved further from it than participants who received no such warning — but not nearly far enough. The effect persisted even after deliberate, motivated attempts at correction.\n\nThis means the feeling of having \"compensated for\" an anchor is often an illusion. You may have moved away from it somewhat, but probably not enough."
            ),
            TextSection(
                heading = "Everyday Examples",
                body = "Retail pricing: The crossed-out \"original price\" displayed above a sale price exists almost entirely as an anchor. A product marked \"was $120, now $79\" feels like a deal not because $79 is inherently fair but because $120 is lodged in your head. The anchor was designed and placed there deliberately.\n\nSalary negotiation: Research shows that whoever names a number first gains an anchoring advantage, pulling the entire negotiation toward their figure. Candidates who open with ambitious but credible numbers achieve higher outcomes on average. Employers who anchor low first drag the conversation toward their reference point.\n\nLegal sentencing: Studies involving experienced judges found that higher prosecutor sentencing recommendations produced longer sentences — even when the recommendation was randomly assigned. The anchor outweighed independent judgment.\n\nReal estate: Listing prices anchor buyers' expectations. Higher listing prices correlate with higher final sale prices, even after controlling for actual property value."
            ),
            TextSection(
                heading = "Defending Against It",
                body = "The most effective defense is forming your own estimate before encountering any external number. Research the actual value independently before entering a negotiation. Know your number before the other side speaks. When you see a crossed-out \"original price,\" ask yourself: what would I think of this price if there were no comparison shown?\n\nIn negotiations, establishing the first anchor — ambitious but credible — gives you the structural advantage rather than leaving it to the other side.\n\nThe goal is not to eliminate anchoring — that is probably impossible. It is to ensure that when anchoring happens, the anchor working on you is one you chose rather than one someone else placed."
            )
        )
    ),
    BiasTopic(
        key = "misattribution",
        title = "Misattribution - A few effects / biases",
        sections = listOf(
            TextSection(
                heading = "What Is Misattribution of Arousal",
                body = "Emotions have two ingredients: a physical state and a label your brain attaches to that state. The label is what turns a raw physiological feeling into a named emotion — fear, excitement, attraction, anger.\n\nThe problem is that most emotions produce similar physical states. Your heart pounds when you're scared and when you're attracted to someone. Your palms sweat before a job interview and at a first date. Because the body speaks in a limited vocabulary, the label your brain assigns depends heavily on what's happening around you at the moment — not only on what actually caused the feeling.\n\nThis is the foundation of Misattribution of Arousal, first described through Schachter and Singer's Two-Factor Theory of Emotion (1962). When your body is already in an aroused state from one source and a new stimulus appears, your brain may assign the arousal to the new stimulus rather than its real origin."
            ),
            TextSection(
                heading = "The Shaky Bridge",
                body = "In 1974, psychologists Donald Dutton and Arthur Aron conducted one of psychology's most discussed experiments. They used two bridges in Vancouver: a narrow, rickety suspension bridge 230 feet above a rocky gorge, and a sturdy, wide, low bridge a short distance away.\n\nAn attractive female researcher approached male participants on each bridge, asked them to complete a brief survey, and offered her phone number for follow-up questions. The results were stark: 39% of men on the high bridge called her back, compared to 9% on the low bridge. Men on the high bridge also wrote stories with significantly more romantic and sexual content in a story-completion task. A control condition using a male researcher showed no such difference.\n\nThe most likely explanation: crossing the swaying bridge produced real physiological arousal — elevated heart rate, alertness, adrenaline. When the researcher appeared, the brain needed to label that arousal. \"Attraction\" was the most available label given the context. The fear had become attraction — not through any trick of reasoning, but because the brain found the nearest plausible cause and assigned responsibility to it."
            ),
            TextSection(
                heading = "The Fluency Heuristic",
                body = "A related misattribution happens when you read.\n\nWhen text is easy to process — clear font, short sentences, plain vocabulary, logical flow — the brain experiences cognitive ease. This ease generates a mild positive feeling. The problem is that the brain often misattributes this ease to the content rather than the presentation: information that flows easily feels more credible, more trustworthy, more like a good idea — not because the reasoning is stronger, but because reading it cost less effort.\n\nPsychologists Adam Alter and Daniel Oppenheimer demonstrated this across multiple studies. Stocks with pronounceable ticker symbols slightly outperformed those with unpronounceable ones shortly after IPO — a real market effect produced by nothing but processing ease. Rhyming aphorisms are rated as more true than non-rhyming versions of the same statement, even when the content is identical. Instructions printed in a clear font are rated as taking less time to complete than the same instructions in a hard-to-read font, before anyone has attempted them.\n\nIn a business context: a well-formatted, clearly written proposal doesn't just communicate better — it feels better. The cognitive ease is mistaken for intellectual quality or soundness. The same proposal in dense jargon would trigger more skepticism, not because the logic changed, but because the friction did."
            ),
            TextSection(
                heading = "The Illusory Truth Effect",
                body = "A third version operates through repetition.\n\nWhen you've heard a statement many times, it becomes easier to process — the brain has already laid down pathways for it. That fluency generates a mild sense of familiarity and rightness. And the brain, again, tends to misattribute this ease: \"this feels like something I know to be true.\"\n\nThis was first documented by psychologists Lynn Hasher, David Goldstein, and Thomas Toppino in 1977. Participants rated obscure trivia statements — a mix of true and false — across multiple sessions. Statements heard in earlier sessions were consistently rated as more true than new statements, regardless of whether they were actually true.\n\nMore disturbingly, Lisa Fazio and colleagues found in 2019 that repetition increases perceived truth even for statements participants already knew to be false — as long as the falsehood was at least plausible. Prior correct knowledge does not fully protect against the effect. The feeling of familiarity overrides the stored correct answer.\n\nThis explains why myths like \"you only use 10% of your brain\" survive decades of debunking. The repetition has made them feel true in a way that corrections alone cannot undo."
            ),
            TextSection(
                heading = "What These Three Have in Common",
                body = "Misattribution of Arousal, the Fluency Heuristic, and the Illusory Truth Effect share a common structure.\n\nIn each case, the brain generates a feeling that is completely real: physiological arousal, cognitive ease, or recognition fluency. The feeling is not imaginary. But the label the brain attaches to it is wrong. The brain looks around for the most salient nearby cause, finds something plausible, and assigns responsibility to it instead of the actual source.\n\nThe result is that unrelated factors — a shaky bridge, a clean font, a repeated phrase — end up shaping judgments of attraction, quality, and truth. Not because the person was deceived in a conventional sense, but because the brain's labeling system works by context and availability, not by causal accuracy.\n\nThis is also why the effects are hard to dismiss as rare or exotic. Every day, you process information whose format influences how true it feels, encounter arguments whose familiarity substitutes for their validity, and experience emotional states that color how you interpret whoever is in front of you."
            ),
            TextSection(
                heading = "Why the Brain Does This",
                body = "This is not a defect in any deep sense. In most natural environments, these shortcuts work.\n\nEase of processing usually does mean familiarity, and familiarity usually means something encountered before, and things encountered before are often safe and reliable. Physiological arousal in the presence of another person usually does have something to do with that person. Statements heard many times are often repeated because they are true or at least useful.\n\nThe mismatch arises when cause and context are artificially separated — by a bridge, a font choice, a media campaign, or a design decision. In those cases, the brain's efficient labeling system produces confident but incorrect attributions.\n\nKnowing this doesn't eliminate the effects — as with most cognitive biases, awareness helps less than you'd expect. But it does give you a question worth pausing to ask: is what I'm feeling actually about what I think it's about?"
            )
        )
    ),
    BiasTopic(
        key = "mere_exposure",
        title = "Mere Exposure Effect",
        sections = listOf(
            TextSection(
                heading = "What Is the Mere Exposure Effect",
                body = "Simply encountering something repeatedly — even without interaction, even without consciously noticing — tends to increase how much you like it. The stimulus doesn't get objectively better. It becomes familiar, and familiarity, to the brain, signals safety.\n\nThis is one of the most robustly replicated findings in social psychology, with hundreds of peer-reviewed studies across more than fifty years. Robert Zajonc documented it in 1968 in a paper titled \"Attitudinal Effects of Mere Exposure,\" published in the Journal of Personality and Social Psychology. He tested photographs, nonsense words, Chinese characters, and geometric shapes — in every case, items seen more frequently were consistently rated more positively."
            ),
            TextSection(
                heading = "Subliminal Exposures Still Work",
                body = "Zajonc went further. He tested exposures so brief that participants had no conscious awareness of seeing them — flashed below the threshold of perception. Even then, those stimuli were later rated more positively than unexposed ones.\n\nThe brain was updating its familiarity ratings below the surface, without any conscious recognition. This is significant: you do not need to deliberately notice something for the mere exposure effect to accumulate. It runs silently in the background.\n\nThis explains why people often feel vaguely positive about brands, faces, or products they have been exposed to through advertising they were never consciously paying attention to. The exposure happened. The familiarity registered. The positive affect followed."
            ),
            TextSection(
                heading = "The Classroom Study",
                body = "Moreland and Beach (1992), published in the Journal of Experimental Social Psychology, conducted a study specifically designed to test mere exposure on interpersonal attraction in a real-world setting.\n\nFour female confederates of similar age and appearance attended a large undergraduate lecture class a different number of times: 0, 5, 10, or 15 sessions. The key constraint: they were never allowed to interact with any student. No introductions, no eye contact, no conversation. They simply attended.\n\nAt the end of the semester, students were shown photographs and asked to rate each woman. Attractiveness ratings climbed steadily with the number of sessions attended: 3.62 at zero visits rising to 4.38 at fifteen. Perceived similarity — how much students felt this person was like them — showed the same pattern.\n\nPassive co-presence alone, with zero social interaction, was enough to make a person measurably more attractive."
            ),
            TextSection(
                heading = "What This Means for the Classroom Scenario",
                body = "This directly explains the pattern of a man becoming more inclined to approach a woman he has seen throughout a semester.\n\nHer face has been processed repeatedly. Processing her has become easier — it requires less cognitive effort than it did at the start of term. That ease is experienced as a vague positive feeling. The brain attributes this feeling to her as a person. What feels like attraction or interest has been built, in significant part, by repetition.\n\nHe is not wrong that he has a genuine positive feeling — he does. But he may substantially underestimate how much of that feeling was constructed by exposure rather than by anything she specifically said or did. The familiarity did most of the work, operating below his awareness the entire time."
            ),
            TextSection(
                heading = "Why It Works — The Mechanism",
                body = "Two processes operate together.\n\nFirst, processing fluency: a stimulus encountered before requires less cognitive effort to process. The subjective experience of this ease is a mild positive feeling, which gets attributed to the stimulus itself — \"this feels right,\" \"I like this\" — without any conscious reasoning.\n\nSecond, reduced apprehensiveness: novel stimuli trigger a mild automatic alertness — an evolutionary reflex from environments where unfamiliar things might be dangerous. Repeated safe encounters with the same stimulus gradually reduce this baseline wariness. As the low-level alarm quiets, what remains is comfort. Comfort reads as positive affect, and positive affect reads as liking.\n\nNeither process requires conscious thought. Both run automatically."
            ),
            TextSection(
                heading = "Where It Has Limits",
                body = "The effect follows an inverted-U curve. Initial and moderate exposures increase liking reliably. At very high repetition — particularly in controlled lab settings with dozens of back-to-back repetitions — the effect plateaus and eventually reverses into boredom or irritation. The stimulus has become monotonous rather than comfortingly familiar.\n\nIf the initial encounter was clearly negative, additional exposure tends to amplify the dislike rather than neutralize it. The effect strengthens whatever feeling was there to begin with. It does not automatically convert negative impressions into positive ones.\n\nResearch has also found that high trait anxiety reduces the effect: more anxious individuals are less soothed by familiarity alone, possibly because the threat-detection system remains more sensitive regardless of prior safe exposures."
            )
        )
    ),
    BiasTopic(
        key = "information_gap",
        title = "Riddles and Quizzes - Information Gap / Curiosity Gap",
        sections = listOf(
            TextSection(
                heading = "What Is the Information Gap",
                body = "George Loewenstein (1994) defined curiosity as the uncomfortable feeling that arises from a perceived gap between what we know and what we want to know. The larger the gap, the stronger the drive to close it. This is not a metaphor — it produces measurable tension states that motivate real behaviour, just as hunger motivates eating. Loewenstein called it the information gap theory of curiosity, and it remains the most influential framework in the field."
            ),
            TextSection(
                heading = "The Tension State",
                body = "While the gap is open, you are in a state of motivated discomfort. People describe it as an itch, a nagging feeling, or mild anxiety. This is why half-finished riddles are so hard to abandon, why you read the last page of a thriller standing in a bookshop, and why cliffhanger episodes make you immediately start the next one. The brain treats unresolved information as an open loop that demands closure."
            ),
            TextSection(
                heading = "Dopamine and Information Seeking",
                body = "Bromberg-Martin and Hikosaka (2009) showed that dopamine neurons in the brain fire not just for physical rewards but in anticipation of information, even when that information has no direct material benefit. The brain treats the prospect of resolving a knowledge gap as intrinsically rewarding.\n\nGruber, Heschl, and Krebs (2014, Neuron) found that states of curiosity activate the VTA (a key dopamine source) and the hippocampus simultaneously, which is why things learned while curious are remembered far better than things learned from obligation."
            ),
            TextSection(
                heading = "The Resolution Reward",
                body = "When the gap closes — you solve the riddle, find the answer, hear the reveal — dopamine signals a prediction resolved. This produces satisfaction, sometimes even a small surge of pride if the task felt hard. Schultz et al.'s (1997) prediction-error framework explains this: the brain releases dopamine to mark \"this worked; do it again.\"\n\nThat is why people seek out puzzles repeatedly: the cycle of tension, search, and resolution is itself the reward loop. Curiosity is not just the precursor to learning — it is reinforcing."
            ),
            TextSection(
                heading = "Information Gap in Everyday Life",
                body = "Clickbait headlines exploit the gap deliberately. A headline like \"You won't believe what happened next\" creates a gap; the click closes it. Listicles (\"7 things you didn't know about X\") open multiple small gaps at once. True crime podcasts and murder mysteries sustain a large gap across hours or episodes.\n\nTrivia games and riddle books make the gap-resolution cycle the entire entertainment. Teachers who ask a question before explaining a concept are, consciously or not, using Loewenstein's framework: the gap makes the subsequent explanation stick."
            ),
            TextSection(
                heading = "Why It Works So Well",
                body = "Evolutionary accounts suggest that information reducing uncertainty about the environment has always had survival value — knowing where predators are, where food is, what a stranger's intentions are. The brain may therefore treat unresolved information as a genuine threat to preparedness, and dopamine-driven curiosity as the mechanism to fix it.\n\nThe same system that once drove ancestors to investigate unfamiliar sounds now compels you to read one more chapter, watch one more episode, or click one more link."
            )
        )
    ),
    BiasTopic(
        key = "reactance",
        title = "Don't Tell Me What To Do - Reactance / Psychological Reactance",
        sections = listOf(
            TextSection(
                heading = "What Is Psychological Reactance",
                body = "Jack Brehm (1966) proposed that when people perceive a free behaviour as threatened or eliminated, they experience a motivational state — reactance — aimed at restoring that freedom. The stronger the perceived threat, the stronger the reactance. Crucially, the object of the restriction becomes more desirable, not less. Brehm's core finding: telling people they cannot have or do something reliably increases their desire for exactly that thing."
            ),
            TextSection(
                heading = "The Forbidden Fruit Effect",
                body = "Worchel, Lee, and Adewole (1975) put cookies in two jars — one with many, one with just a few. Participants rated the scarce-jar cookies as significantly tastier, even though they were identical. The scarcity implied restriction, and restriction triggered desire.\n\nSeparately, Driscoll, Davis, and Lipetz (1972) found that parental interference in a romantic relationship — the Romeo and Juliet effect — increased reported love between partners. External pressure to end a relationship made the partners value it more."
            ),
            TextSection(
                heading = "Classic Studies and Everyday Evidence",
                body = "The US \"Parental Advisory\" label introduced in 1985 increased sales of the labelled albums, particularly among teenagers — the restriction signalled something worth having. Children told \"do not play with that toy\" subsequently rated it as more attractive than before the warning.\n\nPublic service announcements that open with \"Don't drink and drive\" sometimes increase the salience of the prohibited act in the minds of the very audience they are trying to deter. Warning labels on violent video games have repeatedly been shown to increase their appeal to young people."
            ),
            TextSection(
                heading = "Why the Brain Does This",
                body = "Self-Determination Theory (Deci and Ryan, 1985) identifies autonomy — the experience of acting from one's own agency — as a core psychological need. A perceived threat to autonomy feels like a threat to the self. Reactance is the corrective response: by wanting the restricted thing more and sometimes pursuing it despite the restriction, the person psychologically reasserts agency.\n\nThe rebel feeling is precisely this — it is not irrationality, it is the brain defending its sense of freedom."
            ),
            TextSection(
                heading = "Reactance in Marketing and Persuasion",
                body = "Hard-sell tactics (\"You must buy today\") often backfire because they signal pressure on the customer's choice freedom. Conversely, reverse psychology advertising (\"Not for everyone\") exploits reactance deliberately — the implied exclusion makes the product more desirable.\n\nGiving people explicit permission to say no (\"Feel free to refuse\") paradoxically increases agreement (Guéguen and Pascal, 2000). Effective persuasion generally preserves the sense of autonomy: presenting information and letting people decide outperforms telling people what they should do."
            ),
            TextSection(
                heading = "Reactance in Everyday Life",
                body = "Parents who forbid certain music, films, or friendships often intensify exactly the interest they were trying to extinguish. Managers who micromanage can trigger deliberate slowdown or error as a form of covert autonomy restoration. Rules that are over-explained with \"because I said so\" generate more defiance than rules accompanied by a clear rationale.\n\nReactance also explains why unsolicited advice (\"You should really...\") tends to harden rather than change people's positions — the advice is heard as a threat to their right to choose."
            )
        )
    ),
    BiasTopic(
        key = "mirror_neurons",
        title = "Mirror Neurons",
        sections = listOf(
            TextSection(
                heading = "The Discovery",
                body = "Mirror neurons were discovered serendipitously in the early 1990s by Giacomo Rizzolatti and colleagues — including Vittorio Gallese — at the University of Parma. They were recording from individual neurons in the premotor cortex of macaque monkeys (area F5), the region responsible for planning hand and mouth movements. The finding was unexpected: the same neurons that fired when the monkey reached for food also fired when the monkey watched a researcher reach for the same food. The monkey did nothing. Its hand did not move. But its brain responded as though it had. These were named mirror neurons — they mirrored observed actions in the observer's own motor system."
            ),
            TextSection(
                heading = "What They Do",
                body = "Mirror neurons respond to goal-directed actions — reaching, grasping, bringing food to the mouth — both when performed and when observed. The crucial element is the goal, not the movement. When a researcher picks up a peanut, the neurons fire. When the researcher makes the same hand motion without an object present, the response is weaker or absent. The system appears to encode what someone is doing and why, not merely the mechanics of the motion.\n\nIn humans, brain imaging studies show similar activation in premotor and parietal areas during both action observation and action execution — consistent with a human mirror system. Direct recording of individual mirror neurons in humans has been limited to rare surgical contexts, but the broader pattern is well-replicated."
            ),
            TextSection(
                heading = "Vicarious Experience — Why Observation Is Not Passive",
                body = "The practical consequence is that watching is not neutral. Observing an action partially recruits the same neural machinery as performing it. Watching someone eat activates motor and gustatory circuits in the observer. Watching someone in pain activates pain-associated regions. Watching an athlete jump activates motor areas linked to jumping.\n\nThis is one reason why watching someone unbox a new product can generate something resembling genuine excitement: the viewer's nervous system is running a partial simulation of the experience — the anticipation, the unwrapping, the reveal. The mechanism is not metaphorical. Some part of the neural substrate of the experience is engaged, even at a distance."
            ),
            TextSection(
                heading = "The Controversy",
                body = "Mirror neurons became one of the most overclaimed concepts in modern neuroscience. They were proposed as the neural basis of empathy, language acquisition, imitation, and — most influentially — autism (the \"broken mirror\" theory, suggesting autistic individuals had a defective mirror neuron system).\n\nMost of these extensions were not supported. The autism-mirror-neuron theory was directly contradicted by studies finding intact or stronger mirror responses in autistic individuals. Research also showed that people can understand actions they have never performed and cannot physically perform — which limits the motor-simulation account considerably.\n\nWhat remains well-supported is narrower: a mechanism linking observed and executed goal-directed actions exists, it is real, and it has genuine implications for imitation, learning, and vicarious experience. The broader claims — that mirror neurons explain the full range of human social cognition — are where the science overreached."
            ),
            TextSection(
                heading = "Examples in Everyday Life",
                body = "A crowd watching a gymnast stumble winces collectively — their motor systems registered the error before the mind formed a thought. A film audience flinches during a punch even though nothing touched them. Sports fans watching a penalty kick feel tension in their own bodies. Watching skilled cooking activates appetite; watching someone yawn is almost contagious.\n\nThese are not metaphors. They reflect the mirror system running partial simulations of what it observes — automatically, without deliberate effort. Marketing that shows desirable experience — travel, luxury, warmth, connection — exploits exactly this: showing the experience is not merely informative, it partially delivers it. Observation and experience are not as separate as they appear."
            )
        )
    ),
    BiasTopic(
        key = "authority_bias",
        title = "Authority Bias",
        sections = listOf(
            TextSection(
                heading = "What Is Authority Bias",
                body = "The tendency to attribute greater accuracy and credibility to the opinions and instructions of an authority figure, and to comply with those instructions even when they conflict with personal judgment or available evidence. Perceived authority — signaled by titles, uniforms, institutional affiliations, or confident manner — reliably increases compliance and reduces independent evaluation.\n\nThe phenomenon operates before conscious reasoning engages. The visual or social cue of authority is processed quickly and triggers a compliance disposition that must be actively overridden, rather than one that requires active activation."
            ),
            TextSection(
                heading = "The Milgram Experiment",
                body = "In 1961, Yale psychologist Stanley Milgram began a series of experiments that would become among the most discussed in the history of social science. Participants were recruited through newspaper advertisements for a \"study on memory and learning.\" They arrived at a lab and were told they would play the role of \"teacher\" in a learning experiment.\n\nA second person — introduced as another participant, but in fact a trained actor — was designated the \"learner\" and was strapped to a chair in an adjacent room. The teacher was shown a shock generator with switches running from 15 volts (\"Slight Shock\") to 450 volts (\"XXX — Danger: Severe Shock\"), in 15-volt increments.\n\nThe procedure: the learner was given word pairs to memorize. For each wrong answer, the teacher was instructed to administer a shock and increase the voltage by one step. As the voltages rose, the learner — via pre-recorded audio — called out in pain, demanded to be released, complained of a heart condition, and eventually went silent. When participants hesitated or refused, the experimenter — wearing a grey lab coat and presenting as a scientist — used a set of scripted prompts: \"Please continue,\" \"The experiment requires you to continue,\" \"It is absolutely essential that you continue,\" \"You have no other choice but to continue.\"\n\nNo actual shocks were administered. The learner was an actor. The shocks were not real.\n\nResult: 65 percent of participants — 26 of 40 — administered the full 450 volts. Every single participant administered at least 300 volts. These were ordinary people who had shown no unusual aggression beforehand. Milgram published his findings in 1963 in the Journal of Abnormal and Social Psychology, and later expanded them in the 1974 book Obedience to Authority."
            ),
            TextSection(
                heading = "Why the White Coat Mattered — Variations That Revealed the Mechanism",
                body = "Milgram ran eighteen variations that systematically manipulated the authority cues. The results were instructive.\n\nWhen the experimenter gave instructions by telephone rather than being physically present in the room, compliance dropped from 65 percent to approximately 20 percent. When the experimenter left and was replaced by \"another participant\" (also an actor) with no apparent credentials, compliance dropped sharply. When the location was changed from Yale University's prestigious campus to a run-down office suite in Bridgeport, Connecticut — removing the institutional authority signal — compliance dropped from 65 percent to 47.5 percent. When two experimenters gave contradictory instructions, no participant continued.\n\nThe authority doing the work was not the logic of the instructions — the instructions made no sense from any ethical standpoint. It was the combination of the lab coat, the institutional setting, the confident demeanor, and the scripted escalation. Strip any one of those elements and compliance fell substantially."
            ),
            TextSection(
                heading = "Authority Bias Beyond the Lab",
                body = "Leonard Bickman (1974) tested authority compliance in a natural street setting. Confederates dressed in three ways — as a civilian, as a milkman, or as a security guard — made small requests of strangers: \"Pick up that bag,\" or \"Give that person money for a parking meter.\" Compliance with the uniformed guard was significantly higher than with the civilian, for requests that carried no legitimate enforcement power.\n\nThe effect extends to medicine: studies have found that patients routinely take medications at incorrect doses, or fail to flag symptoms they consider \"too minor to bother the doctor with\" — deferring to the physician's authority in situations where their own judgment or questions would have been medically relevant.\n\nIn advertising, fictional doctors, scientists, and authority figures increase purchase intent even when the audience knows the figures are actors. The white coat triggers the response before the reasoning arrives."
            ),
            TextSection(
                heading = "The Everyday Implications",
                body = "Uniforms, titles, credentials, job seniority, and confident manner all reliably trigger authority bias — and they can be easily faked or misapplied. The authority triggering compliance may have genuine expertise in a completely different domain from the one they are speaking about.\n\nIn workplaces, authority bias suppresses dissent: junior employees with better information hesitate to challenge senior figures, sometimes with significant operational consequences. Aviation took this problem seriously enough to redesign cockpit communication protocols after crashes were traced to co-pilots failing to effectively override pilots who were wrong.\n\nThe rational response is not to discount authority entirely — genuine expertise is a legitimate signal. It is to ask two questions before complying: does this person's authority actually apply to this specific situation, and is there any reason to independently verify the instruction before acting on it?"
            )
        )
    ),
    BiasTopic(
        key = "sunk_cost_fallacy",
        title = "Sunk Cost Fallacy",
        sections = listOf(
            TextSection(
                heading = "What Is the Sunk Cost Fallacy",
                body = "A sunk cost is any cost — money, time, effort, emotional investment — that has already been incurred and cannot be recovered, regardless of what you decide going forward. If you have spent a million dollars on a business, that million is gone. It will not come back whether you invest another million or walk away.\n\nThe sunk cost fallacy is the tendency to factor that unrecoverable past investment into forward-looking decisions — to prefer options that continue or validate prior spending over options that would be better evaluated purely on their future merits. It is a decision-making error because the question \"what should I do now?\" should be answered by looking forward, not backward. The money already spent is no longer a variable. Only future costs and future returns are.\n\nStandard economic and financial theory is unambiguous on this: sunk costs are irrelevant to rational decision-making. In practice, they reliably distort it."
            ),
            TextSection(
                heading = "The Investment Dilemma",
                body = "Suppose you invested one million dollars in a business. A year later, it is failing — not recovering on its own, and requiring another million to have any chance of survival. Meanwhile, other investment opportunities are available with better expected returns.\n\nThe rational question is: given the money I have available today, which option offers the best expected future return? The prior investment is not a variable in that calculation. It is gone either way.\n\nThe sunk cost fallacy produces a different question instead: \"If I don't invest more, I might lose everything I already put in.\" But this reasoning is flawed. The original million is already lost — that outcome is not in your control anymore. The decision is only about what to do with the money you have now. Choosing the failing business over a better option because of the prior investment means selecting a worse expected future outcome to feel consistent with a past decision. You are not protecting the original million. You are simply adding to the loss.\n\nThe rational investor views each option as if encountered fresh: which allocation of available capital produces the best risk-adjusted expected return? Past investments are relevant only insofar as they generate future cash flows — and that is a future-looking assessment, not a backward-looking one."
            ),
            TextSection(
                heading = "Arkes and Blumer (1985) — The Classic Research",
                body = "The foundational empirical paper on sunk cost behavior is Hal Arkes and Catherine Blumer's \"The Psychology of Sunk Cost,\" published in Organizational Behavior and Human Decision Processes in 1985.\n\nIn one experiment, participants were told they had spent \$100 on a ski trip and then discovered a \$50 ski trip to a resort they expected to enjoy more. Most chose to go on the \$100 trip — the one they expected to enjoy less — to avoid \"wasting\" the sunk cost.\n\nIn a business simulation scenario, participants who had received reports that an investment in an aircraft project was failing continued funding it at higher rates when they had personally been responsible for the original investment decision than when the prior investment had been made by someone else. The sunk cost only distorted decisions when the subject felt personally responsible for it — suggesting that ego and identity are part of the mechanism, not just loss aversion.\n\nIn a theater subscription study, participants who had paid more for a season ticket attended more performances — including ones they had little interest in — because they had paid for them. The payment created an obligation the brain treated as real even after the money was irrecoverable."
            ),
            TextSection(
                heading = "The Concorde Fallacy",
                body = "The supersonic Concorde aircraft is one of the most cited real-world examples of the sunk cost fallacy operating at national scale. By the mid-1960s, internal government analyses in both the United Kingdom and France indicated that the project would never be commercially viable — the operating costs would always exceed what passengers could reasonably be charged.\n\nBoth governments continued funding development anyway. One significant factor was the sheer scale of what had already been committed: the research, the engineering, the political capital, the national prestige. Walking away would mean acknowledging that it had all been a mistake. Continuing meant the prior investment still had a story. The plane flew for 27 years before retirement, never turning a profit on commercial routes.\n\n\"Concorde fallacy\" has since entered the vocabulary of behavioral economics as a shorthand for continuing a failing project because of prior investment. It has been documented in defense procurement, software development, pharmaceutical research, and public infrastructure projects worldwide."
            ),
            TextSection(
                heading = "Why the Brain Does This",
                body = "Two mechanisms drive the sunk cost fallacy.\n\nThe first is loss aversion, described by Kahneman and Tversky in Prospect Theory (1979): losses are experienced as roughly twice as psychologically painful as equivalent gains feel good. When you abandon a sunk investment, the loss becomes psychologically finalized — it is confirmed, it is over. Continuing the investment keeps open the possibility, however remote, of recovery. The brain treats that possibility as worth paying for, even when the expected return is worse.\n\nThe second is ego consistency. Abandoning an investment that you chose is an admission that the original decision was wrong. The brain is strongly motivated to believe that past decisions were good ones — it is uncomfortable to believe otherwise. Continuing the investment allows the narrative \"I made a sound investment and I'm seeing it through\" to survive. Walking away forces the narrative \"I made a bad investment.\" The former is less painful, so the brain promotes it, regardless of the financial reality.\n\nBoth mechanisms work below conscious awareness. The person experiencing them usually does not feel like they are rationalizing — they feel like they are being sensible, loyal, or persistent."
            ),
            TextSection(
                heading = "The Rational Approach",
                body = "The practical corrective is to ask a single reframing question before any investment decision: \"If I had not already spent anything on this, and I were evaluating all available options from scratch today, which would I choose?\"\n\nThat question strips the sunk cost out of the analysis. It forces a comparison on future terms only. If the answer is \"I would choose a different option,\" that is the rational choice — regardless of what was spent before.\n\nThe sunk cost itself may still generate real future value: a business that has been built has infrastructure, relationships, and knowledge that a new investment would not have. That is a legitimate future-looking consideration. But it should be evaluated as a future asset, not used as a backward-looking justification. The question is always what these assets are worth going forward — not how much it cost to build them.\n\nKnowing about sunk cost bias does not automatically prevent it. The emotional pull of consistency and loss avoidance is real and immediate. The most effective protection is a structured, explicit decision process that forces forward-looking comparison — making the question visible enough that the backward pull cannot operate without being noticed."
            )
        )
    )
)
