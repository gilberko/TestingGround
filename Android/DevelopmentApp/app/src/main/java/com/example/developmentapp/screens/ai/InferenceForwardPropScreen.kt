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
fun InferenceForwardPropScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Inference - Forward Propagation",
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
                SectionCard("What Is Inference") {
                    BodyText("Inference is the act of using a trained neural network to make a prediction on new data. At inference time the weights and biases are frozen — no learning or weight updates happen.")
                    BodyText("During training the network sees thousands or millions of examples and uses backpropagation to gradually improve its weights. Inference is what you do after training is finished: you feed in a new input and let the signal flow forward through the network to get an output.")
                    BodyText("Only a forward pass is needed during inference — there is no need to compute gradients or store intermediate values for backpropagation. This makes inference significantly faster and cheaper than training.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Forward Propagation Step By Step") {
                    BodyText("The input x flows through each layer in sequence. Each layer takes the output of the previous layer, applies its weights and biases, and then passes through its activation function.")
                    CodeBlock("""
Given:
  x        — input vector
  Wˡ, bˡ  — weight matrix and bias vector for layer l
  f        — activation function

Layer 1:   a¹ = f( W¹ · x  + b¹ )
Layer 2:   a² = f( W² · a¹ + b² )
Layer 3:   a³ = f( W³ · a² + b³ )
  ⋮
Output:    ŷ  = g( Wᴸ · aᴸ⁻¹ + bᴸ )
               (g may be softmax, sigmoid, or linear
                depending on the task)
                    """.trimIndent())
                    BodyText("Each aˡ is called the activation of layer l. It is a vector — one number per neuron in that layer. The activation of the output layer is the final prediction ŷ.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Matrix Form and Why GPUs Are Used") {
                    BodyText("In practice, multiple inputs are processed at the same time in a batch. The batch of inputs is stacked into a matrix X (rows = samples, columns = features). Each layer's computation then becomes a matrix multiplication:")
                    CodeBlock("""
// Single sample (vector)
a = activation(W · x + b)

// Batch of N samples (matrix)
A = activation(W · X + b)
// X is [features × N], W is [neurons × features]
// A is [neurons × N] — all N samples processed at once
                    """.trimIndent())
                    BodyText("Matrix multiplication is the dominant operation in neural network inference. Modern GPUs have thousands of cores that can execute multiply-add operations in parallel, making them far faster than CPUs for this workload. TPUs (Tensor Processing Units) are purpose-built chips optimised specifically for matrix multiply.")
                    BodyText("Frameworks like PyTorch and TensorFlow express the entire forward pass as a sequence of matrix operations so the GPU can execute them without CPU involvement.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Reading the Output") {
                    BodyText("The output layer produces different things depending on the network's purpose:")
                    CodeBlock("""
Multi-class classification (softmax output):
  ŷ = [0.02, 0.91, 0.05, 0.02]  ← probabilities for 4 classes
  predicted class = argmax(ŷ) = class 1  (91% confidence)

Binary classification (sigmoid output):
  ŷ = 0.83
  predicted class = 1  (threshold 0.5 → positive)

Regression (linear output):
  ŷ = 247500.0  ← predicted house price in dollars
                    """.trimIndent())
                    BodyText("For classification the output is a probability distribution. The model does not output a hard decision by itself — a threshold (usually 0.5 for binary, argmax for multi-class) is applied after the network to obtain the final label.")
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Cost Compared to Training") {
                    BodyText("Training is expensive because for every forward pass the framework also:")
                    BodyText("  • stores all intermediate activations (needed for backpropagation)\n  • computes gradients for every weight in the network\n  • updates millions of parameters\n  • repeats this for every batch across many epochs")
                    BodyText("Inference only needs the forward pass. No activations need to be stored beyond what the current layer needs, and no gradients are computed. In PyTorch this is expressed explicitly:")
                    CodeBlock("""
# Training mode — gradients computed and stored
model.train()
output = model(x)
loss   = criterion(output, labels)
loss.backward()        # computes all gradients
optimizer.step()       # updates all weights

# Inference mode — gradients disabled, faster and less memory
model.eval()
with torch.no_grad():
    output = model(x)  # forward pass only
    predicted = output.argmax(dim=1)
                    """.trimIndent())
                    BodyText("This is why inference can run on a smartphone with a small battery while training the same model required weeks on a cluster of GPUs.")
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
