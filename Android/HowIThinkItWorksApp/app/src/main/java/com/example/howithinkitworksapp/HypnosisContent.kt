package com.example.howithinkitworksapp

data class TextSection(val heading: String, val body: String)
data class HypnosisTopic(val key: String, val title: String, val sections: List<TextSection>)

val hypnosisTopics = listOf(
    HypnosisTopic(
        key = "hypnosis",
        title = "Hypnosis",
        sections = listOf(
            TextSection(
                heading = "The Navigation App",
                body = "Imagine you are driving to a destination you are not entirely familiar with. You open a navigation app and start following its turn-by-turn directions. \"Turn left in 200 meters.\" You turn left. \"Keep straight for 2 kilometers.\" You keep straight.\n\nAt some point, you are no longer consciously evaluating each instruction against a mental map. You have decided to trust the app, and so you follow along — almost automatically. You are still the one driving, still alert, still capable of stopping if something feels wrong. But your decision-making for the route has been, in a sense, delegated.\n\nThis is a useful way to think about hypnosis."
            ),
            TextSection(
                heading = "The Car in the Fog",
                body = "Now imagine a different scenario. You are driving through dense fog. Visibility is low. You spot the tail-lights of a car ahead of you and decide to follow it — it seems to know where it is going, and following it feels safer than navigating the fog alone.\n\nAt first, you consciously choose to follow: you watch the distance, you make active decisions to stay behind it. But gradually, as the drive continues, your following becomes more automatic. You are no longer actively deciding — you are simply going where the car leads.\n\nThis, too, is a metaphor for the hypnotic state."
            ),
            TextSection(
                heading = "The Metaphor",
                body = "In both scenarios, you chose to delegate your navigation to something you trusted. You were not asleep, not unconscious — you were fully present, but operating in a mode of cooperative following rather than independent decision-making.\n\nHypnosis works similarly. The client does not lose consciousness or control. Instead, they enter a state where they are more willing to follow the suggestions of a trusted hypnotist, much like following the navigation app or the car in the fog.\n\nThis cooperation has limits. If the navigation app tells you to drive into a river, you stop following it. If the car ahead starts behaving erratically, you back off. Similarly, if a hypnotic suggestion feels too strange or threatening, the client will disengage. Hypnosis does not override a person's values or self-preservation."
            ),
            TextSection(
                heading = "Roles in the Session",
                body = "During a hypnosis session, the client's role is essentially to follow the suggestions — to allow the hypnotist to lead, just as you allowed the navigation app to guide your route. The more the client can relax into this cooperative following, the more effective the session tends to be.\n\nThe hypnotist's role is to initiate and maintain this state. At times, the hypnotist may deepen it — making the cooperative state stronger — and at the end, the hypnotist gently releases it, allowing the client to naturally resume full independent awareness and control."
            ),
            TextSection(
                heading = "Managing Attention",
                body = "At its core, what the hypnotist is really doing is managing the client's attention. By guiding where the client's focus goes — and keeping it there — the hypnotist sustains the state of cooperative following. When attention drifts, the hypnotic state weakens. When it is held steady or narrowed, the state deepens.\n\nThis is why many hypnotic inductions involve focusing exercises, rhythmic speech, or vivid mental imagery — all of these are tools for capturing and holding attention."
            )
        )
    ),
    HypnosisTopic(
        key = "suggestions",
        title = "Suggestions",
        sections = listOf(
            TextSection(
                heading = "What Is a Suggestion?",
                body = "In hypnosis, a suggestion is an instruction or idea offered by the hypnotist that the client accepts and acts upon — not because they are forced to, but because they are in a state of cooperative following.\n\nSuggestions can be direct (\"Your arm is becoming heavy\") or indirect (\"You might notice a pleasant feeling of relaxation\"). They can target physical sensations, emotions, thoughts, behaviors, or perceptions."
            ),
            TextSection(
                heading = "How They Work",
                body = "A suggestion works because the client, in the hypnotic state, tends to accept ideas more readily and translate them into experience. The critical, evaluating part of the mind — the part that might normally say \"no, my arm is not heavy, that's just words\" — becomes less active.\n\nThis is why the hypnotic state matters: suggestions given outside it are just words. Suggestions given inside it can become experience."
            ),
            TextSection(
                heading = "Post-Hypnotic Suggestions",
                body = "Suggestions can also be designed to take effect after the hypnosis session ends. These are called post-hypnotic suggestions. For example, a suggestion that whenever the client hears a specific word, they will feel calm.\n\nThe effectiveness of post-hypnotic suggestions varies widely between individuals and sessions."
            )
        )
    ),
    HypnosisTopic(
        key = "pretalk",
        title = "Pre Talk",
        sections = listOf(
            TextSection(
                heading = "Trust Before Anything Else",
                body = "Before hypnosis can work, the client needs to trust the hypnotist and believe, to some degree, that hypnosis is possible. This is the foundation.\n\nThink of it again through the metaphor: you only follow the navigation app if you believe it is reliable. You only follow the car in the fog if you sense it knows where it is going. Without that initial trust, cooperation does not begin."
            ),
            TextSection(
                heading = "The Pre Talk",
                body = "The pre talk is the conversation that happens before the hypnosis session. Its purpose is to establish trust, address the client's questions or fears, and set accurate expectations.\n\nIn a clinical or private setting, this might be a genuine discussion. In stage hypnosis shows, the pre talk often also serves to identify the best candidates from the audience — people who are responsive, cooperative, and willing."
            ),
            TextSection(
                heading = "Convincers and Ideo-Motor Response",
                body = "Stage hypnotists often use what are called convincers — demonstrations designed to make it feel as if the hypnotist has an unusual power over the body.\n\nOne well-known category exploits the ideo-motor response: the fact that vividly imagining a movement tends to produce small, unconscious physical movements in that direction. If you clearly imagine a pendulum swinging, your hand will often begin to sway slightly — not because you are moving it deliberately, but because the mental image triggers tiny motor signals.\n\nA stage hypnotist can use this principle to make your arm rise, your fingers interlock, or your body sway, apparently without you consciously willing it. To an audience — and often to the subject themselves — this feels remarkable."
            ),
            TextSection(
                heading = "Things You Cannot Do",
                body = "Another category of convincer takes advantage of physiological conditions where voluntary control is simply difficult or unreliable.\n\nA common example: close your eyes and roll them upward as if looking at the top of your forehead. While keeping your eyes rolled up, try to open your eyelids. Many people find this extremely difficult or impossible — the eye muscles are in a configuration that resists opening. This is not because the hypnotist has done anything. It is simply anatomy.\n\nBut in the context of a stage show, the hypnotist presents this as a demonstration of their power to make you unable to open your eyes. The audience, and often the subject, experiences it as evidence of the hypnotist's special ability."
            ),
            TextSection(
                heading = "Building Belief",
                body = "Both types of convincer serve the same purpose: they give the subject a concrete, felt experience that supports the belief that the hypnotist is capable of influencing their behavior.\n\nThis belief acts just like the trust in the navigation app, or the confidence that the car in the fog knows where it is going. It creates the conditions under which the subject will cooperate with suggestions — and that cooperation is what makes hypnosis work."
            ),
            TextSection(
                heading = "Selecting the Right Candidates",
                body = "On stage, these demonstrations also serve a practical purpose: they help the hypnotist identify who in the audience responds most readily. Not everyone is equally responsive to suggestion, and a stage show depends on working with those who are.\n\nBy observing whose arm rises most readily, or whose eyes resist opening most completely, the hypnotist can select the candidates most likely to produce a compelling performance."
            )
        )
    ),
    HypnosisTopic(
        key = "induction",
        title = "Induction",
        sections = listOf(
            TextSection(
                heading = "What Is Induction?",
                body = "Induction is the process by which the hypnotist helps the client enter the hypnotic state — sometimes referred to as a trance.\n\nIt is, in essence, the on-ramp. Before induction, the client is in their ordinary waking state. After induction, they are in the cooperative, suggestible state where hypnosis can operate."
            ),
            TextSection(
                heading = "What Induction Is Not",
                body = "Induction is not making someone unconscious. It is not sleep in the ordinary sense, even though the word \"sleep\" is sometimes used as a hypnotic suggestion — where it means something closer to \"let go and relax deeply.\"\n\nThe client remains aware throughout and, if a suggestion were too alarming or objectionable, could at any moment choose to stop."
            ),
            TextSection(
                heading = "Many Methods",
                body = "There are many induction techniques. Some are slow and relaxation-based — long, rhythmic progressions through relaxing different parts of the body. Some are rapid or even instantaneous.\n\nWhat they share is the goal of shifting the client's mode of processing: from active, evaluating, independent thinking, toward a more receptive, cooperative, following state."
            )
        )
    ),
    HypnosisTopic(
        key = "induction_confusion",
        title = "Induction by Confusion",
        sections = listOf(
            TextSection(
                heading = "Disrupting the Expected",
                body = "One powerful category of induction works by creating confusion in the client's mind. When something unexpected or incoherent happens, the mind immediately goes to work trying to make sense of it — searching for a pattern, a meaning, a way to resolve the ambiguity.\n\nThis search process is called Transderivational Search: the mind rapidly scans its memories, associations, and interpretive frameworks, trying to derive meaning from what it has received."
            ),
            TextSection(
                heading = "The Handshake Induction",
                body = "A well-known example is the handshake induction. A handshake is one of the most automatic social behaviors we have — we all know exactly how it feels and flows. When someone interrupts that pattern in an unexpected way — pausing mid-motion, repositioning the hand, holding it at an unusual angle — the brain's automatic program stutters.\n\nFor a brief moment, normal processing is suspended as the mind scrambles to re-categorize the situation. In that window of confusion, the client is highly open to direction."
            ),
            TextSection(
                heading = "The Fog Appears",
                body = "Return to the fog metaphor. The confusion induction creates the fog. The client, who already perceives the hypnotist as capable and trustworthy, suddenly finds themselves in an unclear, uncertain situation — their normal scripts have been disrupted.\n\nThey are looking for guidance."
            ),
            TextSection(
                heading = "The Car Arrives",
                body = "In that moment of confusion, the hypnotist delivers a clear, simple suggestion: \"Sleep.\" Or \"Relax.\"\n\nIn the context of hypnosis, \"sleep\" does not mean actual sleep — it means let go, stop trying to figure it out, soften into a receptive state.\n\nThis suggestion arrives as the guiding car in the fog. The client, faced with two options — remain in uncomfortable confusion, or accept the suggestion and follow it — finds it far more appealing to accept. A trusted person has just offered a clear path out of an ambiguous situation."
            ),
            TextSection(
                heading = "Compliance Feels Good",
                body = "The result is that the client enters a state of actively cooperating with the hypnotist's suggestions — and this feels natural and even pleasant compared to the preceding confusion.\n\nThe hypnotist has, in effect, created the need and then supplied the relief. The client's mind, now oriented toward compliance, is in the hypnotic state."
            )
        )
    ),
    HypnosisTopic(
        key = "induction_concentration",
        title = "Induction by Concentration",
        sections = listOf(
            TextSection(
                heading = "Come Back Soon",
                body = "More on this topic coming soon."
            )
        )
    )
)
