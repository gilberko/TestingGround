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
    )
)
