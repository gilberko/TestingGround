package com.example.developmentapp.screens.ai

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.developmentapp.screens.BodyText
import com.example.developmentapp.screens.CodeBlock
import com.example.developmentapp.screens.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttentionTransformersScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Attention and Transformers",
                        color      = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint               = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard("\"Attention Is All You Need\"") {
                    BodyText(
                        "In 2017, researchers at Google Brain published a paper titled " +
                        "\"Attention Is All You Need\" (Vaswani et al.). It introduced the " +
                        "Transformer architecture and argued that the dominant approach at the " +
                        "time — using recurrent networks (RNNs and LSTMs) to process sequences " +
                        "— could be replaced entirely by an attention mechanism."
                    )
                    BodyText(
                        "The key insight: to understand a word in a sentence, you don't need to " +
                        "read the sentence left-to-right and carry state forward. You can look " +
                        "at all words simultaneously and let each word decide which other words " +
                        "are relevant to its meaning. This is the attention mechanism."
                    )
                    BodyText(
                        "The practical consequence was enormous. RNNs process tokens one by one " +
                        "and cannot be parallelised across the sequence. Transformers process " +
                        "all tokens in parallel — making them dramatically faster to train on " +
                        "modern GPU hardware. This unlocked training on far larger datasets " +
                        "than was previously feasible."
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("What Is the Transformer?") {
                    BodyText(
                        "The Transformer is a neural network architecture designed to process " +
                        "sequences — originally text, but later applied to images, audio, " +
                        "proteins, and more. It consists of two main components: an encoder " +
                        "and a decoder, each built from stacked layers of attention and " +
                        "feed-forward sub-layers."
                    )
                    CodeBlock(
                        "Input sequence (tokens)\n" +
                        "       ↓\n" +
                        "  ┌──────────┐\n" +
                        "  │  Encoder │  ← builds a rich contextual representation\n" +
                        "  └──────────┘\n" +
                        "       ↓\n" +
                        "  ┌──────────┐\n" +
                        "  │  Decoder │  ← generates the output sequence token by token\n" +
                        "  └──────────┘\n" +
                        "       ↓\n" +
                        "Output sequence (tokens)"
                    )
                    BodyText(
                        "Not all Transformer-based models use both components. Some use only " +
                        "the encoder (e.g. BERT), some use only the decoder (e.g. GPT). The " +
                        "original paper used the full encoder-decoder design for machine " +
                        "translation."
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Tokens — The Unit of Input") {
                    BodyText(
                        "Before text enters a Transformer, it must be split into tokens. A " +
                        "token is not necessarily a whole word — it is a sub-word unit produced " +
                        "by a tokeniser algorithm (e.g. Byte-Pair Encoding). Common words " +
                        "become a single token; rare words are split into pieces."
                    )
                    CodeBlock(
                        "\"unhappiness\" → [\"un\", \"happiness\"]   (2 tokens)\n" +
                        "\"the\"         → [\"the\"]                 (1 token)\n" +
                        "\"ChatGPT\"     → [\"Chat\", \"G\", \"PT\"]  (3 tokens)"
                    )
                    BodyText(
                        "Each token is mapped to an integer ID from the model's vocabulary " +
                        "(typically 30 000–100 000 tokens). That integer is looked up in an " +
                        "embedding table to produce a dense vector — a list of hundreds or " +
                        "thousands of floating-point numbers that represent the token's meaning."
                    )
                    BodyText(
                        "Because the Transformer processes all tokens in parallel (with no " +
                        "inherent order), it also adds a positional encoding to each token " +
                        "embedding — a signal that tells the model where in the sequence each " +
                        "token sits. Without positional encoding the model would treat " +
                        "\"dog bites man\" and \"man bites dog\" identically."
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("The Encoder") {
                    BodyText(
                        "The encoder takes the full sequence of token embeddings as input and " +
                        "transforms them into a sequence of contextual representations — one " +
                        "vector per token, where each vector now encodes not just the token's " +
                        "identity but also its relationship to every other token in the " +
                        "sequence."
                    )
                    BodyText(
                        "Each encoder layer has two sub-layers:"
                    )
                    BodyText(
                        "  1. Multi-head self-attention: every token computes a weighted sum " +
                        "over all other tokens. The weights (attention scores) reflect how " +
                        "relevant each other token is. \"Multi-head\" means this happens in " +
                        "several parallel streams, each learning to attend to different kinds " +
                        "of relationships (e.g. one head might track syntactic structure, " +
                        "another coreference)."
                    )
                    BodyText(
                        "  2. Feed-forward network: a small fully-connected network applied " +
                        "independently to each token's updated representation."
                    )
                    BodyText(
                        "This makes representations contextual. The word \"bank\" produces a " +
                        "different vector in \"river bank\" versus \"savings bank\" because the " +
                        "surrounding tokens shift its attention-weighted representation. A " +
                        "typical encoder has 6–24 such layers stacked, each one refining the " +
                        "representations further."
                    )
                    CodeBlock(
                        "Input:  [\"The\", \"bank\", \"was\", \"flooded\"]\n" +
                        "        each token = a vector of e.g. 512 numbers\n\n" +
                        "After encoder:\n" +
                        "  \"bank\" vector now reflects river/flooding context,\n" +
                        "  not financial context — same word, different representation"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("The Decoder") {
                    BodyText(
                        "The decoder generates the output sequence one token at a time. At " +
                        "each step it produces the next token, which is then fed back as input " +
                        "for the next step. This is called auto-regressive generation."
                    )
                    BodyText(
                        "Each decoder layer has three sub-layers:"
                    )
                    BodyText(
                        "  1. Masked self-attention: the decoder can attend to all previously " +
                        "generated output tokens, but not to future ones (they don't exist yet). " +
                        "The masking prevents the model from cheating during training."
                    )
                    BodyText(
                        "  2. Cross-attention: the decoder attends to the encoder's output. " +
                        "This is how the decoder \"reads\" the input — each output token can " +
                        "look at the full encoded input and decide which parts are relevant."
                    )
                    BodyText(
                        "  3. Feed-forward network: same as in the encoder."
                    )
                    CodeBlock(
                        "Translation example: French → English\n\n" +
                        "Encoder input:  \"Le chat mange\"\n" +
                        "Encoder output: contextual vectors for each French token\n\n" +
                        "Decoder step 1: start token → attends to encoder → produces \"The\"\n" +
                        "Decoder step 2: \"The\"       → attends to encoder → produces \"cat\"\n" +
                        "Decoder step 3: \"The cat\"   → attends to encoder → produces \"eats\"\n" +
                        "Decoder step 4: \"The cat eats\" → produces end-of-sequence token → done"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Models Built on the Transformer") {
                    BodyText("Encoder-only (good at understanding/classifying text):")
                    BodyText(
                        "  • BERT (Google, 2018): trained to predict masked words using context " +
                        "from both sides. Used for sentiment analysis, question answering, " +
                        "named entity recognition."
                    )
                    BodyText(
                        "  • RoBERTa, ALBERT, DeBERTa: improved BERT variants."
                    )
                    BodyText("Decoder-only (good at generating text):")
                    BodyText(
                        "  • GPT-2, GPT-3, GPT-4 (OpenAI): trained to predict the next token. " +
                        "Powers ChatGPT, GitHub Copilot."
                    )
                    BodyText(
                        "  • LLaMA, Mistral, Gemma: open-weight decoder-only models used for " +
                        "fine-tuning and local deployment."
                    )
                    BodyText("Encoder-decoder (good at sequence-to-sequence tasks):")
                    BodyText(
                        "  • T5 (Google): frames every NLP task as text-to-text — the input " +
                        "and output are both strings. One model for translation, summarisation, " +
                        "classification, and more."
                    )
                    BodyText(
                        "  • BART: combines BERT-style encoder with GPT-style decoder; used " +
                        "for summarisation."
                    )
                    BodyText("Beyond text:")
                    BodyText(
                        "  • Vision Transformer (ViT, Google): splits an image into patches " +
                        "treated as tokens; achieves top image-classification accuracy.\n" +
                        "  • AlphaFold2 (DeepMind): uses attention to predict 3D protein " +
                        "structure from amino-acid sequences.\n" +
                        "  • Whisper (OpenAI): encoder-decoder for speech recognition.\n" +
                        "  • Stable Diffusion / DALL-E: use Transformer text encoders to " +
                        "condition image generation."
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Use Cases") {
                    BodyText(
                        "  • Machine translation: original use case; near-human quality for " +
                        "major language pairs (Google Translate).\n" +
                        "  • Text summarisation: condense a long document to a few sentences.\n" +
                        "  • Question answering: given a passage and a question, locate the " +
                        "answer span in the text.\n" +
                        "  • Code generation: GitHub Copilot, Cursor, and similar tools " +
                        "auto-complete and generate code from natural language.\n" +
                        "  • Conversational AI: ChatGPT, Claude, Gemini — large decoder-only " +
                        "models fine-tuned to follow instructions.\n" +
                        "  • Image synthesis: text-to-image models condition a diffusion " +
                        "process on Transformer-encoded text.\n" +
                        "  • Drug discovery: predict molecular properties from SMILES strings " +
                        "treated as token sequences."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
