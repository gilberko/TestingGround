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
fun TrainingBackwardPropScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Training - Backward Propagation",
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
                SectionCard("Training Data — The Learning Problem") {
                    BodyText("Training a neural network requires a dataset of known examples. Each example is a pair: an input vector (the data) and the expected output (the correct answer). This is called supervised learning.")
                    BodyText("For example, to build a network that predicts whether a customer will churn, the input might be a vector of customer attributes (age, usage, tenure) and the expected output is 1 (will churn) or 0 (will not churn).")
                    BodyText("The goal of training is to adjust the network's weights and biases so that for each input in the dataset, the network's output is as close as possible to the expected output.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("What Is an Epoch") {
                    BodyText("An epoch is one full pass through the entire training dataset. During each epoch, every training example is fed through the network, an error is computed for each, and the weights are updated accordingly.")
                    BodyText("One epoch is rarely enough to train a network. Training typically runs for tens, hundreds, or thousands of epochs. After each epoch the network has improved slightly, and over many epochs it converges toward a good solution.")
                    BodyText("Think of it like studying a textbook — reading it once gives you a rough idea, but reading it multiple times builds a solid understanding.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("The Error (Loss) Function") {
                    BodyText("After the network produces an output for a training example, we need to measure how wrong it was. This is the job of the error (or loss) function.")
                    BodyText("A simple choice is the raw difference between expected and actual output:")
                    CodeBlock("""
error = expected - output
                    """.trimIndent())
                    BodyText("The problem is that this can be positive or negative. If you sum errors across a batch, positive and negative errors cancel out, hiding how poorly the network is doing.")
                    BodyText("A better choice is Squared Error — squaring the difference makes it always non-negative, and penalises large mistakes much more than small ones:")
                    CodeBlock("""
E = (expected - output)²

Examples:
  expected=1, output=0.9  →  E = (0.1)² = 0.01   ← small miss
  expected=1, output=0.1  →  E = (0.9)² = 0.81   ← large miss
                    """.trimIndent())
                    BodyText("The goal of training is to minimise E. When E = 0, the network's output perfectly matches the expected output for that example.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Error as a Function of All Parameters") {
                    BodyText("Given a fixed training example, the network's output is completely determined by its weights and biases. Changing any weight or bias changes the output, which changes the error.")
                    BodyText("So the error E can be written as a function of every weight and bias in the network:")
                    CodeBlock("""
E = f(w₁, w₂, w₃, …, wₙ, b₁, b₂, …, bₘ)
                    """.trimIndent())
                    BodyText("A real network might have millions of parameters. Finding the values of all of them that minimise E is a high-dimensional optimisation problem. This is the mathematical heart of neural network training.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Partial Derivatives and the Gradient") {
                    BodyText("Calculus tells us that to find the minimum of a function we can use derivatives — they tell us the slope of the function at any point. With many parameters we use partial derivatives: hold all parameters fixed except one, and ask \"how does E change as I change just this one parameter?\"")
                    CodeBlock("""
∂E/∂wᵢ  — partial derivative of E with respect to weight wᵢ

If ∂E/∂wᵢ > 0 : increasing wᵢ increases the error
                              → we should decrease wᵢ

If ∂E/∂wᵢ < 0 : increasing wᵢ decreases the error
                              → we should increase wᵢ

If ∂E/∂wᵢ = 0 : we are at a flat point (possibly a minimum)
                    """.trimIndent())
                    BodyText("Imagine the error surface as a hilly landscape and you are standing somewhere on it. The gradient vector ∇E points in the direction of steepest uphill. To reach the valley (the minimum) you walk in the opposite direction — downhill.")
                    BodyText("The gradient ∇E is simply the collection of all partial derivatives for every weight and bias in the network. Its magnitude tells you how steeply the surface rises in the uphill direction — a large gradient means a steep slope, a small gradient means you are near flat ground.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Backward Propagation") {
                    BodyText("To compute ∂E/∂wᵢ for every weight in the network we use the chain rule from calculus. A weight in an early layer affects the output only through the layers that come after it — so we can decompose the derivative into a chain of simpler derivatives multiplied together.")
                    BodyText("Crucially, weights in layer L affect only layers L+1, L+2, … onwards, never earlier layers. This means we can compute the derivatives efficiently by starting at the output layer — where we directly know the error — and working backward layer by layer:")
                    CodeBlock("""
1. Compute ∂E/∂w for every weight in the output layer
   (easy — error is right there)

2. Use the chain rule to propagate the error signal
   back to the previous layer:
   ∂E/∂wᵢ = (∂E/∂aₒᵤₜ) · (∂aₒᵤₜ/∂wᵢ)

3. Repeat, layer by layer, all the way back to layer 1
                    """.trimIndent())
                    BodyText("This backward sweep is called backpropagation (or backprop). It is the algorithm that makes training deep networks computationally feasible — without it, computing all the gradients from scratch for each parameter independently would be impossibly slow.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Gradient Descent and Learning Rate") {
                    BodyText("Once we have the gradient for every parameter, we update each one by taking a small step in the downhill direction:")
                    CodeBlock("""
w ← w - η · (∂E/∂w)
b ← b - η · (∂E/∂b)

η (eta) = learning rate, a small positive number
Typical values: 0.001 to 0.1
                    """.trimIndent())
                    BodyText("The learning rate η controls how large a step we take. Why not just take a huge step and get to the minimum in one go?")
                    BodyText("The answer is that we are adjusting millions of parameters simultaneously, and the error surface is complex. A large step for one parameter can overshoot its minimum and make things worse. Worse, the parameters interact — moving one changes the optimal values for others. Using a small η ensures each update is a careful, stable improvement rather than a chaotic leap.")
                    BodyText("Gradient descent is the general method: repeatedly move parameters in the direction opposite to the gradient. Over many iterations this descends the error surface toward a minimum.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Other Optimisation Methods") {
                    BodyText("Plain gradient descent (using the full dataset for each update) can be slow. Several variations and improvements are widely used:")
                    CodeBlock("""
Stochastic GD (SGD):
  Update after every single sample.
  Fast but noisy — gradient estimate is rough.

Mini-batch GD:
  Update after a small batch (32–256 samples).
  Best balance: less noise than SGD, less memory
  than full-dataset GD. Standard in practice.

Momentum:
  Accumulates a velocity vector in the gradient
  direction. Accelerates in consistent directions,
  dampens oscillation.

RMSprop:
  Adapts the learning rate per parameter using a
  moving average of recent squared gradients.
  Helps when gradients vary widely across params.

Adam (Adaptive Moment Estimation):
  Combines momentum + RMSprop.
  Adapts η per parameter, works well out-of-the-box.
  Most widely used optimizer in deep learning today.
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Tensor Cores and Parallel Computation") {
                    BodyText("All the weight updates within a single layer are independent of each other — the gradient for w₁ does not depend on the gradient for w₂. This means they can all be computed in parallel.")
                    BodyText("Computing the gradients for a whole layer boils down to matrix multiply-accumulate operations — multiplying large matrices of activations by large matrices of weights and summing the results. This is exactly what GPUs are designed to do at massive scale.")
                    BodyText("Modern NVIDIA GPUs contain dedicated Tensor Cores — specialised hardware units that perform a 4×4 matrix multiply-accumulate operation in a single clock cycle. A high-end GPU may have thousands of tensor cores all operating simultaneously.")
                    CodeBlock("""
One tensor core operation (per clock cycle):
  D = A × B + C
  where A, B, C, D are 4×4 matrices

A GPU with 5000 tensor cores running at 2 GHz
can perform roughly 320 trillion such operations
per second (320 TFLOPS).
                    """.trimIndent())
                    BodyText("This parallelism is why training a large model on a GPU cluster takes hours, whereas the same training on a CPU would take months.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Iterations, Convergence, and Overfitting") {
                    BodyText("Each epoch descends the error surface a little. As training progresses, the error on the training data steadily decreases. Eventually it plateaus — further training gives diminishing returns. This is called convergence.")
                    BodyText("However, there is a trap: overfitting. The network can get so good at the training data that it memorises the specific examples — including their noise and quirks — rather than learning the underlying pattern. The result is high accuracy on training data but poor accuracy on new, unseen data. This is like memorising past exam questions word for word instead of understanding the subject.")
                    CodeBlock("""
Epoch  1: training loss = 0.82, validation loss = 0.85
Epoch 10: training loss = 0.41, validation loss = 0.43
Epoch 50: training loss = 0.18, validation loss = 0.19  ← good
Epoch 100: training loss = 0.07, validation loss = 0.27 ← overfitting!
  (training loss falls, but validation loss rises)
                    """.trimIndent())
                    BodyText("Common defences against overfitting:")
                    BodyText("  • Dropout: randomly disable a fraction of neurons during each training step, forcing the network to learn redundant representations\n  • L2 regularisation (weight decay): add a penalty for large weights to the loss function\n  • Early stopping: monitor validation loss and stop training when it starts to rise\n  • More training data: the best defence — a larger dataset is harder to memorise")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
