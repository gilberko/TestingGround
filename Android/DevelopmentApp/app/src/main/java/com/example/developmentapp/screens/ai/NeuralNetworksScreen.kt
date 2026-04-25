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
fun NeuralNetworksScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Neural Networks",
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
                SectionCard("Layered Structure") {
                    BodyText("A neural network is organised into layers of neurons:")
                    BodyText("  • Input layer — receives the raw input values (one neuron per feature). No computation happens here; it just distributes values to the first hidden layer.\n  • Hidden layers — one or more intermediate layers where the actual learning happens. The network can have any number of them.\n  • Output layer — produces the final result (a class probability, a number, etc.).")
                    BodyText("The layers are fully connected (also called dense): every neuron in layer L receives the output of every neuron in layer L−1 as its inputs. This is the standard architecture; other topologies (convolutional, recurrent) connect neurons differently.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Inside Each Neuron") {
                    BodyText("Every neuron in every hidden or output layer does the same two-step computation as a perceptron:")
                    CodeBlock("""
z   = w₁·a₁ + w₂·a₂ + … + wₙ·aₙ + b
out = activation_function(z)
                    """.trimIndent())
                    BodyText("where a₁…aₙ are the outputs (activations) from the previous layer, w₁…wₙ are this neuron's own weights, and b is its own bias. Each neuron has an independent set of weights and bias — these are the parameters the network learns during training.")
                    BodyText("The outputs of all neurons in a layer become the inputs for all neurons in the next layer.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Activation Functions") {
                    BodyText("Without an activation function every layer would just be a linear transformation, and stacking linear transformations is still linear — the whole network would collapse to a single layer. Activation functions introduce non-linearity, allowing the network to learn complex patterns.")
                    BodyText("Common activation functions:")
                    CodeBlock("""
Sigmoid:      σ(z) = 1 / (1 + e^(-z))
              output range (0, 1)
              historically popular, now mostly used in output layers
              for binary classification

Tanh:         tanh(z) = (e^z - e^(-z)) / (e^z + e^(-z))
              output range (-1, 1)
              zero-centred, often better than sigmoid in hidden layers

ReLU:         f(z) = max(0, z)
              most common choice for hidden layers today
              fast, avoids vanishing gradient for positive values
              neurons with z < 0 output 0 (can "die" and never update)

Leaky ReLU:   f(z) = max(0.01·z, z)
              small negative slope prevents dying neurons

Softmax:      softmax(z)ᵢ = e^zᵢ / Σ e^zⱼ
              converts a vector of numbers to a probability
              distribution (all outputs sum to 1)
              used in the output layer for multi-class classification
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Architecture Diagram") {
                    CodeBlock("""
  INPUT       HIDDEN 1     HIDDEN 2     OUTPUT
  LAYER        LAYER        LAYER        LAYER

   x1 ─┐       (N) ─┐       (N) ─┐      y1
        │       (N)  │       (N)  │      y2
   x2 ─┼──→    (N) ─┼──→    (N) ─┼──→
        │       (N)  │       (N)  │      y3
   x3 ─┘       (N) ─┘       (N) ─┘

  fully connected — every neuron receives ALL outputs
  from the previous layer as inputs

  Each neuron N:
    z   = Σ(weight · input) + bias
    out = activation(z)
                    """.trimIndent())
                    BodyText("The arrows represent the full connection — in a real diagram there would be an arrow from every neuron in one layer to every neuron in the next.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Types of Networks") {
                    BodyText("Classification network — output layer uses Softmax, producing a probability for each possible class. Take argmax to get the predicted label. Example: image recognition (cat / dog / car). This type of output is called a probability distribution over classes.")
                    BodyText("Regression network — output layer uses a linear (identity) activation, producing a continuous numerical value. Example: predicting house prices, estimating temperature.")
                    BodyText("CNN (Convolutional Neural Network) — uses convolutional layers that scan for spatial patterns. The same filter is applied across the entire input rather than having independent weights for every position. Used for images, audio spectrograms, and other grid-like data.")
                    BodyText("RNN / LSTM (Recurrent Neural Network / Long Short-Term Memory) — neurons have connections that loop back, giving the network memory of previous inputs in a sequence. Used for text, speech, time series, and any data where order matters.")
                    BodyText("Transformer — uses an attention mechanism that lets each position in a sequence look at all other positions directly. The basis of GPT, BERT, and most modern large language models.")
                    BodyText("Autoencoder — trained to compress its input down to a small bottleneck representation and then reconstruct the original. Used for dimensionality reduction, anomaly detection, and generative models.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Neural Networks as Statistical Regression") {
                    BodyText("At its core a neural network is a universal function approximator. Given enough neurons and layers, it can approximate any continuous function to arbitrary precision (Universal Approximation Theorem).")
                    BodyText("Training is an optimisation problem: find the weights W and biases b that minimise a loss function measuring how wrong the predictions are.")
                    CodeBlock("""
Loss functions:
  MSE (Mean Squared Error)     — regression tasks
      L = (1/n) Σ (ŷᵢ - yᵢ)²

  Cross-Entropy                — classification tasks
      L = -(1/n) Σ yᵢ · log(ŷᵢ)

Optimisation:
  Gradient descent — compute how much each weight
  contributes to the loss (via backpropagation),
  then take a small step in the downhill direction:
      w ← w - η · ∂L/∂w
                    """.trimIndent())
                    BodyText("Modern networks have millions or billions of parameters. Training them is essentially a very high-dimensional regression problem solved iteratively over many epochs with large batches of data.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
