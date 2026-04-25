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
fun PerceptronScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Perceptron",
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
                SectionCard("What Is a Perceptron") {
                    BodyText("A perceptron is the simplest model of an artificial neuron and the building block of neural networks. It is essentially a linear statistical classifier — a weighted sum of inputs passed through a threshold function.")
                    BodyText("A perceptron has:")
                    BodyText("  • n inputs  x₁, x₂, …, xₙ  (real-valued numbers, not limited to binary)\n  • one weight per input  w₁, w₂, …, wₙ\n  • a bias constant  b\n  • an activation function (classically a step/threshold function)")
                    BodyText("The output is computed in two steps:\n  1. Weighted sum:  z = w₁·x₁ + w₂·x₂ + … + wₙ·xₙ + b\n  2. Activation:    output = 1 if z ≥ 0,  else 0")
                    BodyText("This is a form of statistical regression — just like linear regression it fits a linear relationship between inputs and output, but here the output is a binary class label rather than a continuous value.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Computation Example") {
                    BodyText("Suppose we want to classify whether a tumour is malignant based on two measurements: size and density.")
                    CodeBlock("""
inputs  x = [size, density] = [0.8, 0.6]
weights w = [1.5, 2.0]
bias    b = -1.8

z = (1.5 * 0.8) + (2.0 * 0.6) + (-1.8)
  = 1.2 + 1.2 - 1.8
  = 0.6

output = step(z) = 1   (z ≥ 0 → malignant)
                    """.trimIndent())
                    BodyText("The geometric interpretation: the equation w·x + b = 0 defines a hyperplane (a line in 2D) that divides the input space into two regions. The perceptron predicts which side of that line a new input falls on.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Training — Adjusting the Weights") {
                    BodyText("Training uses labelled examples. For each example we compare the perceptron's prediction to the correct answer and nudge the weights in the direction that would have given a better output.")
                    BodyText("Perceptron learning rule — for each training sample (x, y_expected):")
                    CodeBlock("""
prediction = forward_pass(x)          // 0 or 1
error      = y_expected - prediction  // 0, +1, or -1

// Update every weight and the bias:
for i in range(len(weights)):
    weights[i] += learning_rate * error * x[i]
bias += learning_rate * error
                    """.trimIndent())
                    BodyText("If the prediction is already correct (error = 0) nothing changes. If the perceptron predicted 0 but the answer is 1 (error = +1), weights for active inputs are increased, making the perceptron more likely to fire on similar inputs. The reverse happens for error = −1.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Learning Rate") {
                    BodyText("The learning rate η (eta) is a hyperparameter — a value you choose before training begins — that controls how large each weight update step is.")
                    BodyText("  • Too large: the perceptron overshoots and oscillates around the solution without converging\n  • Too small: convergence is very slow, requiring many more passes through the data\n  • Typical range: 0.001 – 0.1")
                    BodyText("Unlike the weights and bias, the learning rate is not learned automatically. Choosing a good value is part of the art of training machine-learning models.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Epochs") {
                    BodyText("One epoch is one complete pass through every sample in the training dataset. A single pass is rarely enough — the weights usually need many rounds of adjustment before they converge.")
                    CodeBlock("""
for epoch in range(max_epochs):
    total_error = 0
    for x, y in training_data:
        prediction  = forward_pass(x)
        error       = y - prediction
        total_error += abs(error)
        update_weights(x, error, learning_rate)

    if total_error == 0:
        print(f"Converged after {epoch+1} epochs")
        break
                    """.trimIndent())
                    BodyText("The perceptron convergence theorem guarantees that if the data is linearly separable (there exists a hyperplane that perfectly separates the two classes), the algorithm will find it in a finite number of epochs. If the data is not linearly separable, the algorithm never converges — it keeps updating weights indefinitely.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Limitations") {
                    BodyText("A single perceptron can only solve linearly separable problems — those where a straight line (or hyperplane) can separate the classes.")
                    BodyText("The classic example of failure is XOR: the output is 1 when exactly one input is 1, and no straight line can separate the (0,1) and (1,0) cases from (0,0) and (1,1).")
                    BodyText("The solution is to stack perceptrons in layers. The output of one layer becomes the input of the next, allowing the network to learn non-linear boundaries. This is a multilayer perceptron (MLP) — the foundation of modern neural networks.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
