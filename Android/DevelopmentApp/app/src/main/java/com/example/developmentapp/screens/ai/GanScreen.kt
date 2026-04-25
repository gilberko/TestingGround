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
fun GanScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Generative Adversarial Network (GAN)",
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
                SectionCard("What Is a GAN?") {
                    BodyText("A Generative Adversarial Network (GAN) is a framework in which two neural networks are trained in opposition to each other. The competition between them drives both networks to improve, ultimately producing a generator capable of creating highly realistic synthetic data.")
                    BodyText("The two networks are:")
                    BodyText("  • Discriminator (D): a classifier that learns to tell real data from fake\n  • Generator (G): learns to produce synthetic data convincing enough to fool the discriminator")
                    BodyText("Neither network is useful in isolation — it is the adversarial dynamic that makes GAN training work. The discriminator pushes the generator to improve; a better generator pushes the discriminator to improve further. This back-and-forth is why the word \"adversarial\" appears in the name.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("The Discriminator") {
                    BodyText("The discriminator is a standard classification neural network with one job: given a sample, output a probability that the sample is real (as opposed to generated).")
                    CodeBlock("""
Input:  an image (real or generated)
Output: D(x) ∈ [0, 1]
        → 1.0 means "almost certainly real"
        → 0.0 means "almost certainly fake"
                    """.trimIndent())
                    BodyText("The discriminator is trained first — before the generator exists — on real data from the training set (labeled 1) and on outputs from the generator (labeled 0). This is ordinary supervised binary classification with a squared or cross-entropy loss.")
                    BodyText("After this initial training the discriminator can reliably spot low-quality fakes. As the generator improves, the discriminator is continuously retrained to keep pace.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("The Generator") {
                    BodyText("The generator takes a random noise vector z as its input and maps it to a synthetic sample — for example a full image. The noise vector is the \"seed\"; different seeds produce different outputs.")
                    CodeBlock("""
Input:  z — random noise vector (e.g. 100 random floats
             sampled from a normal distribution)
Output: G(z) — a generated sample (e.g. a 64×64 RGB image)

Goal:   D(G(z)) → 1.0
        (fool the discriminator into thinking the
         generated image is real)
                    """.trimIndent())
                    BodyText("The generator is trained using the discriminator's output as its loss signal. The discriminator's weights are frozen during this phase — the generator is simply trying to maximise the discriminator's probability score for its outputs.")
                    BodyText("Early in training the generator produces noise. Gradually it learns the statistical structure of real data and its outputs become increasingly realistic.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("The Adversarial Training Loop") {
                    BodyText("Training alternates between two phases for each batch of data:")
                    CodeBlock("""
repeat for each batch:

  ── Phase 1: Train the Discriminator ──────────────
  1. Take a batch of real images  → label = 1
  2. Generate a batch of fakes    → label = 0
     (G's weights are frozen here)
  3. Update D weights to classify real vs fake better

  ── Phase 2: Train the Generator ──────────────────
  4. Generate a new batch of fake images
  5. Pass through D (D's weights are now frozen)
  6. Loss = how convincingly D says these are fake
     (we want D(G(z)) close to 1)
  7. Update G weights to produce more convincing fakes
                    """.trimIndent())
                    BodyText("The two networks improve in a cycle. As the discriminator gets better at spotting fakes, the generator is forced to produce more convincing images. As the generator improves, the discriminator must look more carefully.")
                    BodyText("The theoretical endpoint is a Nash equilibrium: the generator produces perfect fakes and the discriminator has no information to distinguish real from generated — it outputs 0.5 for everything. In practice training is stopped before this point, when generated samples look good enough.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Example: Generating Cat Photos") {
                    BodyText("Suppose we want to train a GAN to generate photorealistic images of cats.")
                    BodyText("Training data: 50 000 real photographs of cats. Each photo is a 64×64 pixel RGB image — a vector of 12 288 numbers.")
                    CodeBlock("""
Epoch 1:
  G outputs → random pixel noise (pure static)
  D easily classifies everything as fake (99% accuracy)
  G receives strong gradient signal → improve fast

Epoch 50:
  G outputs → blurry blobs with vague cat-like shapes
  D still mostly right but less confident
  Both networks continue improving

Epoch 500:
  G outputs → images with recognisable cat features:
              pointy ears, fur texture, eye placement
  D struggles — maybe 60% accuracy

Epoch 2000:
  G outputs → photorealistic cat images
              whiskers, iris detail, realistic fur
  D is at ~50% — no better than random guessing
  Training complete
                    """.trimIndent())
                    BodyText("The finished generator can produce unlimited unique cat images by sampling new random noise vectors z. None of the generated cats exist — they are entirely synthesised by the learned mapping G(z).")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Applications of GANs") {
                    BodyText("GANs have proven remarkably versatile. Notable applications include:")
                    BodyText("  • Image synthesis: StyleGAN generates photorealistic human faces (ThisPersonDoesNotExist.com). None of the faces are real people.\n  • Image-to-image translation (pix2pix): convert a rough sketch into a photorealistic image, or transform a daytime photo into a night scene.\n  • Medical imaging: generate synthetic training data for rare diseases where real patient data is scarce.\n  • Data augmentation: expand a small training dataset with realistic synthetic examples to improve other models.\n  • Video game asset generation: procedurally generate textures, characters, or environments.\n  • Deepfakes: swap faces in video — and the parallel field of deepfake detection that tries to catch them.\n  • Super-resolution (SRGAN): upscale low-resolution images to high resolution by hallucinating plausible detail.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
