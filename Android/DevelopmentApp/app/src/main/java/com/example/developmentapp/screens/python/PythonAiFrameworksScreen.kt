package com.example.developmentapp.screens.python

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
fun PythonAiFrameworksScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "AI Frameworks in Python",
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
                SectionCard("The Three Frameworks") {
                    BodyText(
                        "Three libraries dominate neural-network development in Python:\n\n" +
                        "TensorFlow — developed by Google. Production-grade, widely deployed, has " +
                        "TensorBoard for visualizing training, and TensorFlow Lite for running models " +
                        "on Android and iOS.\n\n" +
                        "Keras — a high-level API designed for ease of use. It used to be a separate " +
                        "library but is now bundled inside TensorFlow as tf.keras. You define models " +
                        "in very few lines. Keras is what most tutorials use.\n\n" +
                        "PyTorch — developed by Meta. Uses dynamic computation graphs, meaning the " +
                        "graph is built as code runs rather than up front. This makes debugging much " +
                        "easier and is why most AI researchers prefer it. PyTorch is also used in " +
                        "production (TorchServe) and on mobile (PyTorch Mobile).\n\n" +
                        "Rule of thumb: use PyTorch for learning and research. Use TensorFlow/Keras " +
                        "if you need to deploy to Android with TFLite."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Defining a Neural Network") {
                    BodyText(
                        "A network with 3 layers and 4 neurons each. The number of neurons per layer " +
                        "is set by the first argument to Dense (Keras) or Linear (PyTorch).\n\n" +
                        "Keras (Sequential API):"
                    )
                    CodeBlock(
                        """
import tensorflow as tf
from tensorflow import keras

input_size  = 8   # number of input features
output_size = 1   # number of outputs

model = keras.Sequential([
    keras.layers.Dense(4, activation='relu',
                       input_shape=(input_size,)),
    keras.layers.Dense(4, activation='relu'),
    keras.layers.Dense(output_size, activation='sigmoid')
])

model.summary()  # prints layer shapes and parameter counts
                        """.trimIndent()
                    )
                    BodyText("PyTorch (class-based):")
                    CodeBlock(
                        """
import torch
import torch.nn as nn

input_size  = 8
output_size = 1

class MyNet(nn.Module):
    def __init__(self):
        super().__init__()
        self.fc1 = nn.Linear(input_size, 4)
        self.fc2 = nn.Linear(4, 4)
        self.fc3 = nn.Linear(4, output_size)

    def forward(self, x):
        x = torch.relu(self.fc1(x))
        x = torch.relu(self.fc2(x))
        return torch.sigmoid(self.fc3(x))

model = MyNet()
print(model)
                        """.trimIndent()
                    )
                    BodyText(
                        "nn.Linear(in, out) is a fully-connected layer: every input neuron connects " +
                        "to every output neuron. The layer has out × in weights plus out bias values, " +
                        "all randomly initialized."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Activation Functions") {
                    BodyText(
                        "Without activation functions, stacking layers is pointless — the whole network " +
                        "collapses to a single linear transformation. Activations introduce non-linearity, " +
                        "which is what lets a network learn complex patterns.\n\n" +
                        "ReLU (Rectified Linear Unit): f(x) = max(0, x)\n" +
                        "Use for hidden layers. Simple, fast, and avoids the vanishing gradient problem " +
                        "that plagued older activations like sigmoid and tanh.\n\n" +
                        "Sigmoid: f(x) = 1 / (1 + e^-x), output range 0–1\n" +
                        "Use on the output layer for binary classification (is this a cat or not?).\n\n" +
                        "Softmax: converts a vector of numbers to probabilities that sum to 1\n" +
                        "Use on the output layer for multi-class classification (which of these 10 digits?).\n\n" +
                        "Tanh: output range –1 to 1. Occasionally used in RNNs.\n\n" +
                        "Rule of thumb: ReLU for all hidden layers. " +
                        "Sigmoid for binary output. Softmax for multi-class output. " +
                        "Nothing (linear) for regression output."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Loss Functions") {
                    BodyText(
                        "The loss function (also called error function or criterion) measures how wrong " +
                        "the model's predictions are. The optimizer's job is to minimize this number.\n\n" +
                        "MSE — Mean Squared Error: average of (prediction - actual)^2\n" +
                        "Use for regression tasks (predicting a continuous number like house price).\n\n" +
                        "BinaryCrossentropy: use for binary classification (sigmoid output).\n\n" +
                        "CategoricalCrossentropy: use for multi-class classification when labels are " +
                        "one-hot encoded ([0,1,0] for class 1).\n\n" +
                        "SparseCategoricalCrossentropy: same as above but labels are plain integers " +
                        "(1 for class 1). Prefer this over CategoricalCrossentropy to avoid manually " +
                        "one-hot encoding."
                    )
                    CodeBlock(
                        """
# Keras — specify as string or object
model.compile(loss='binary_crossentropy', ...)
model.compile(loss='sparse_categorical_crossentropy', ...)
model.compile(loss='mse', ...)

# PyTorch equivalents
criterion = nn.BCELoss()           # binary cross-entropy
criterion = nn.CrossEntropyLoss()  # multi-class (includes softmax)
criterion = nn.MSELoss()           # regression
                        """.trimIndent()
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Optimizers — Gradient Descent & Beyond") {
                    BodyText(
                        "Gradient descent is the core algorithm. After each batch the optimizer " +
                        "computes the gradient of the loss with respect to every weight, then updates " +
                        "each weight by stepping in the opposite direction:\n\n" +
                        "  weight = weight - learning_rate × gradient\n\n" +
                        "Plain SGD (Stochastic Gradient Descent): one gradient step per mini-batch. " +
                        "Simple but sensitive to learning rate choice and slow to converge.\n\n" +
                        "Adam (Adaptive Moment Estimation): the most popular optimizer. Maintains a " +
                        "per-parameter adaptive learning rate using estimates of the first and second " +
                        "moments of the gradient. Converges faster and is far more robust than plain SGD. " +
                        "Start here for almost everything.\n\n" +
                        "RMSprop: similar to Adam without momentum. Works well for RNNs.\n\n" +
                        "AdaGrad: accumulates all past squared gradients. Eventually learning rate " +
                        "shrinks to near zero — generally not recommended."
                    )
                    CodeBlock(
                        """
# Keras
keras.optimizers.Adam(learning_rate=0.001)   # default lr
keras.optimizers.SGD(learning_rate=0.01)
keras.optimizers.RMSprop(learning_rate=0.001)

# PyTorch
torch.optim.Adam(model.parameters(), lr=0.001)
torch.optim.SGD(model.parameters(), lr=0.01, momentum=0.9)
                        """.trimIndent()
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Training Configuration") {
                    BodyText(
                        "Learning rate: controls how large each weight update step is. The Adam default " +
                        "of 0.001 is a good starting point. Too high: loss diverges or oscillates. " +
                        "Too low: training is slow and may get stuck.\n\n" +
                        "Epochs: one epoch = one full pass through the entire training set. " +
                        "Typical range: 10 to 200 depending on dataset size and complexity.\n\n" +
                        "Batch size: how many samples are fed through the network before each weight " +
                        "update. Common values: 32 or 64. Smaller = noisier gradient but uses less memory. " +
                        "Larger = smoother gradient but needs more GPU memory."
                    )
                    CodeBlock(
                        """
# Keras — one call does everything
model.compile(
    optimizer=keras.optimizers.Adam(learning_rate=0.001),
    loss='binary_crossentropy',
    metrics=['accuracy']
)
history = model.fit(
    X_train, y_train,
    epochs=50,
    batch_size=32,
    validation_split=0.2  # use 20% of training data for validation
)
                        """.trimIndent()
                    )
                    CodeBlock(
                        """
# PyTorch — manual training loop
optimizer = torch.optim.Adam(model.parameters(), lr=0.001)
criterion = nn.BCELoss()

for epoch in range(50):
    for X_batch, y_batch in dataloader:
        optimizer.zero_grad()       # clear old gradients
        output = model(X_batch)     # forward pass
        loss = criterion(output, y_batch)
        loss.backward()             # compute gradients
        optimizer.step()            # update weights
    print(f"Epoch {epoch}: loss={loss.item():.4f}")
                        """.trimIndent()
                    )
                    BodyText(
                        "optimizer.zero_grad() must be called before loss.backward() each step — " +
                        "PyTorch accumulates gradients by default and you need to clear them manually."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Feeding the Training Set") {
                    BodyText(
                        "Keras accepts NumPy arrays directly in model.fit(). For large datasets, use " +
                        "tf.data.Dataset which supports lazy loading and preprocessing pipelines.\n\n" +
                        "PyTorch uses Dataset and DataLoader:"
                    )
                    CodeBlock(
                        """
from torch.utils.data import TensorDataset, DataLoader
import torch

X_tensor = torch.FloatTensor(X_train)
y_tensor = torch.FloatTensor(y_train).unsqueeze(1)

dataset    = TensorDataset(X_tensor, y_tensor)
dataloader = DataLoader(dataset,
                        batch_size=32,
                        shuffle=True)   # shuffle every epoch

for X_batch, y_batch in dataloader:
    ...
                        """.trimIndent()
                    )
                    BodyText(
                        "Always shuffle training data — if samples are ordered (e.g. all class 0 first, " +
                        "then all class 1), the model will see biased batches and train poorly.\n\n" +
                        "Standard split: 70% train, 15% validation, 15% test. Validation data is used " +
                        "during training to monitor overfitting. Test data is only used once at the end " +
                        "to measure real-world performance. Never tune your model using test data."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Inference") {
                    BodyText(
                        "Yes — using a trained model to make predictions is called inference (or prediction " +
                        "or forward pass). During inference no weights are updated; the model is read-only."
                    )
                    CodeBlock(
                        """
# Keras
predictions = model.predict(X_new)  # returns numpy array
# predictions[i] is the output for sample i
                        """.trimIndent()
                    )
                    CodeBlock(
                        """
# PyTorch
model.eval()                     # switch to evaluation mode
with torch.no_grad():            # disable gradient tracking
    output = model(X_new_tensor) # forward pass only
    probs = output.numpy()
                        """.trimIndent()
                    )
                    BodyText(
                        "model.eval() is important in PyTorch. Without it, layers like Dropout " +
                        "(which randomly zeros neurons during training) and BatchNorm (which uses " +
                        "running statistics) behave as if training is still happening, producing " +
                        "different and inconsistent results each call.\n\n" +
                        "torch.no_grad() tells PyTorch not to build the gradient computation graph, " +
                        "which saves memory and speeds up inference."
                    )
                }
            }
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("GPU Support") {
                    BodyText(
                        "The GPU is NOT used automatically — you have to move both the model and " +
                        "the input data to the device explicitly. A common crash is putting the model " +
                        "on GPU but leaving the input tensors on CPU.\n\n" +
                        "PyTorch:"
                    )
                    CodeBlock(
                        """
# Check for CUDA (NVIDIA GPU)
device = 'cuda' if torch.cuda.is_available() else 'cpu'
print(f"Using device: {device}")

# Move model to device
model = MyNet().to(device)

# Move data to the same device (must match!)
X_tensor = X_tensor.to(device)
y_tensor = y_tensor.to(device)

# Apple Silicon (M1/M2/M3)
device = 'mps' if torch.backends.mps.is_available() else 'cpu'
                        """.trimIndent()
                    )
                    BodyText("TensorFlow/Keras — GPU detection is automatic:")
                    CodeBlock(
                        """
import tensorflow as tf

# See what TF can use
print(tf.config.list_physical_devices())
print(tf.config.list_physical_devices('GPU'))
# Output: [] means no GPU found — training falls back to CPU

# TF uses the GPU automatically if one is found;
# no .to(device) calls needed in Keras
                        """.trimIndent()
                    )
                    BodyText(
                        "Common pitfall (PyTorch): RuntimeError: Expected all tensors to be on the " +
                        "same device. This means your model is on CUDA but an input tensor is still " +
                        "on CPU (or vice versa). Fix: call .to(device) on both.\n\n" +
                        "No GPU? Google Colab provides free NVIDIA GPU access. After opening a notebook " +
                        "go to Runtime → Change runtime type → GPU. Then torch.cuda.is_available() " +
                        "returns True and training can be 10–50× faster for large networks."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
