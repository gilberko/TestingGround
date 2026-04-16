package com.example.developmentapp.screens.algorithms

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
fun FourierTransformScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Algorithms — Fourier Transform",
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
            item { Spacer(Modifier.height(12.dp)) }

            // ── What Is a Signal? ──────────────────────────────────────────
            item {
                SectionCard(title = "What Is a Signal?") {
                    BodyText(
                        "A signal is a value that changes over time — for example, the air pressure variation " +
                        "that makes up a sound wave, or the voltage oscillation in a radio antenna."
                    )
                    BodyText(
                        "Simple signals have a single frequency: a pure musical note, a single radio channel. " +
                        "Real-world signals are complex: a spoken word contains dozens of frequencies at once, " +
                        "a chord contains multiple musical notes, a noisy environment mixes signal and interference."
                    )
                    BodyText(
                        "A fundamental mathematical insight (Fourier's theorem) states that any periodic signal " +
                        "can be expressed as a sum of simple sine waves, each at a specific frequency and amplitude. " +
                        "The Fourier Transform is the algorithm that decomposes a signal into those components — " +
                        "telling you which frequencies are present and how strongly."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── A Frequency as a Rotating Point ───────────────────────────
            item {
                SectionCard(title = "A Frequency as a Rotating Point") {
                    BodyText(
                        "Here is a useful mental model: imagine a single-frequency wave as a point rotating " +
                        "around a circle of radius 1 centered at (0, 0) — a unit circle."
                    )
                    BodyText(
                        "The point starts at (1, 0) and rotates counterclockwise. One full trip around the " +
                        "circle = one cycle of the wave. If the wave has frequency f Hz, the point completes " +
                        "f full rotations per second."
                    )
                    CodeBlock(
                        "At time t, the point is at angle:  θ = 2π·f·t  (radians)\n\n" +
                        "  x-coordinate = cos(2π·f·t)   ← this IS the cosine wave\n" +
                        "  y-coordinate = sin(2π·f·t)   ← this IS the sine wave\n\n" +
                        "Slower rotation  → lower frequency wave\n" +
                        "Faster rotation  → higher frequency wave\n" +
                        "Larger radius    → higher amplitude (louder, stronger signal)"
                    )
                    BodyText(
                        "This is not just an analogy — it is the actual mathematical definition. " +
                        "A complex exponential e^(i·2π·f·t) is a point rotating on the unit circle, " +
                        "with cos as its real part and sin as its imaginary part. " +
                        "The Fourier Transform is built entirely on this rotating-point idea."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Why Samples Cancel Out ─────────────────────────────────────
            item {
                SectionCard(title = "Why Samples Cancel Out") {
                    BodyText(
                        "Suppose we take N equally-spaced samples of a signal over time. Each sample captures " +
                        "the value of the signal at that moment — i.e., the position of the rotating point " +
                        "on the circle."
                    )
                    BodyText(
                        "If we simply add up all the sample positions, we get a sum of points spread around " +
                        "the circle. The positive x-values cancel negative x-values; the positive y-values " +
                        "cancel negative y-values. The total sum is approximately zero."
                    )
                    CodeBlock(
                        "8 evenly-spaced samples on a unit circle (one full rotation):\n\n" +
                        "  angles: 0°, 45°, 90°, 135°, 180°, 225°, 270°, 315°\n\n" +
                        "  sum of x: cos0 + cos45 + cos90 + ... + cos315\n" +
                        "          = 1 + 0.71 + 0 + (-0.71) + (-1) + (-0.71) + 0 + 0.71\n" +
                        "          = 0.00  ✓\n\n" +
                        "  sum of y: sin0 + sin45 + ... = 0.00  ✓\n\n" +
                        "No matter how many samples, if they are uniformly distributed\n" +
                        "around the circle, positive and negative values cancel."
                    )
                    BodyText(
                        "This cancellation is the foundation of the Fourier Transform: it is the reason " +
                        "that when we test for a frequency that is NOT present, we get zero."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Rewinding the Rotation ─────────────────────────────────────
            item {
                SectionCard(title = "\"Rewinding\" the Rotation") {
                    BodyText(
                        "To test whether a specific frequency f is present in the signal, we use a clever trick: " +
                        "for each sample taken at time t, we know exactly how far a point rotating at frequency f " +
                        "would have moved since time zero — it would be at angle 2π·f·t."
                    )
                    BodyText(
                        "We apply the reverse rotation to each sample: multiply by the point that is rotating " +
                        "in the opposite direction at the same speed. This 'unwinds' or 'rewinds' the sample " +
                        "back to the angle it would have had at time zero."
                    )
                    CodeBlock(
                        "For sample at time t, 'rewind' by multiplying by:\n" +
                        "  e^(-i·2π·f·t)  =  cos(2π·f·t) - i·sin(2π·f·t)\n\n" +
                        "If the signal truly contains frequency f:\n" +
                        "  every sample, after rewinding, points in the SAME direction\n" +
                        "  → they all pile up on one side of the circle\n" +
                        "  → their sum is large (proportional to N samples)\n" +
                        "  → divide by N → a meaningful non-zero amplitude\n\n" +
                        "Intuition: you're rotating your measurement frame at the same\n" +
                        "speed as the wave. From that frame, the wave looks stationary."
                    )
                    BodyText(
                        "This is exactly the DFT formula. For each candidate frequency k, compute the sum " +
                        "of all N samples each multiplied by their reverse-rotation factor. " +
                        "The result is the amplitude (and phase) of that frequency in the signal."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── When the Frequency Is Wrong ────────────────────────────────
            item {
                SectionCard(title = "When the Frequency Is Wrong") {
                    BodyText(
                        "What happens if we apply the rewind for frequency f, but the signal does not " +
                        "actually contain frequency f?"
                    )
                    BodyText(
                        "The rewind rotates each sample by a fixed angle, but since the signal has a different " +
                        "underlying rotation speed, the rewound samples still end up pointing in many different " +
                        "directions — spread around the circle."
                    )
                    CodeBlock(
                        "Signal contains frequency 3 Hz.\n" +
                        "We test for frequency 5 Hz (wrong).\n\n" +
                        "After rewinding by 5 Hz:\n" +
                        "  samples point in many directions → spread around circle\n" +
                        "  → positive and negative values cancel out\n" +
                        "  → sum ≈ 0\n" +
                        "  → divide by N → result ≈ 0\n\n" +
                        "Conclusion: frequency 5 Hz is NOT present in this signal."
                    )
                    BodyText(
                        "The larger N is (more samples), the more precise the cancellation. " +
                        "With enough samples, only the exact frequencies present in the signal produce " +
                        "non-zero output; all others collapse to zero. This is why longer recordings " +
                        "give sharper frequency analysis."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Detecting Multiple Frequencies ─────────────────────────────
            item {
                SectionCard(title = "Detecting Multiple Frequencies") {
                    BodyText(
                        "Real signals contain many frequencies simultaneously. For example, a musical chord " +
                        "is several notes (frequencies) played at once. A human voice contains a fundamental " +
                        "pitch plus many harmonics. A WiFi signal carries a carrier frequency modulated by data."
                    )
                    BodyText(
                        "We run the rewind test independently for each candidate frequency. Each test is " +
                        "independent: testing for frequency A does not interfere with testing for frequency B. " +
                        "Each test returns a non-zero amplitude only if that specific frequency is actually present."
                    )
                    CodeBlock(
                        "DFT (Discrete Fourier Transform) — conceptual pseudocode:\n\n" +
                        "  for k = 0 to N-1:             // each candidate frequency\n" +
                        "    X[k] = 0\n" +
                        "    for n = 0 to N-1:           // each sample\n" +
                        "      angle  = -2π * k * n / N\n" +
                        "      X[k] += sample[n] * (cos(angle) + i*sin(angle))\n\n" +
                        "  |X[k]| tells you the amplitude of frequency k\n" +
                        "  arg(X[k]) tells you the phase\n\n" +
                        "Time complexity: O(N²) — N frequencies, N samples each.\n\n" +
                        "FFT (Fast Fourier Transform) computes the same result\n" +
                        "in O(N log N) using a divide-and-conquer trick —\n" +
                        "the most important algorithm in signal processing."
                    )
                    BodyText(
                        "The FFT (invented by Cooley and Tukey in 1965, though known earlier) works by " +
                        "noticing that the DFT of an N-point signal can be split into two DFTs of N/2 points, " +
                        "recursively. This reduces O(N²) to O(N log N) — a massive speedup for large signals."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            // ── Practical Uses ─────────────────────────────────────────────
            item {
                SectionCard(title = "Practical Uses") {
                    BodyText("The Fourier Transform is one of the most widely used algorithms in engineering and science:")
                    BodyText(
                        "  • Audio processing — equalizers boost or cut specific frequency bands; " +
                        "MP3/AAC compression removes frequencies the human ear cannot hear well."
                    )
                    BodyText(
                        "  • Image processing — JPEG compression uses the Discrete Cosine Transform " +
                        "(a close relative of the DFT) to represent image data in the frequency domain, " +
                        "then discards high-frequency detail that is less visible."
                    )
                    BodyText(
                        "  • Signal filtering — noise removal: identify the frequency of unwanted noise, " +
                        "zero it out in the frequency domain, then convert back. " +
                        "Medical devices use this to extract clean ECG signals from electrical interference."
                    )
                    BodyText(
                        "  • Spectrum analyzers — real-time FFT on a microphone input displays the " +
                        "frequency content of sound; the same technique powers radio spectrum analyzers."
                    )
                    BodyText(
                        "  • Communications — WiFi, LTE, and 5G use OFDM (Orthogonal Frequency-Division " +
                        "Multiplexing), which relies on the FFT to pack many sub-carriers into a channel " +
                        "efficiently."
                    )
                    BodyText(
                        "  • Scientific computing — solving differential equations, fast polynomial " +
                        "multiplication, and large integer multiplication all reduce to FFT under the hood."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
