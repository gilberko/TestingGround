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
fun AnomalyDetectionScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Anomaly Detection",
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
                SectionCard("What Is Anomaly Detection?") {
                    BodyText(
                        "Anomaly detection is the task of identifying data points that differ " +
                        "significantly from the majority of the data — things that are unusual, " +
                        "unexpected, or potentially indicative of a problem."
                    )
                    BodyText(
                        "The core challenge is that anomalies are rare and diverse. A fraud " +
                        "transaction, a network intrusion, a faulty sensor reading, or a cancer " +
                        "cell in a scan all look different from each other — but they share the " +
                        "property of not fitting the pattern of normal data."
                    )
                    BodyText(
                        "Common applications:\n" +
                        "  • Fraud detection: flag unusual credit card transactions\n" +
                        "  • Network intrusion detection: spot traffic patterns unlike normal use\n" +
                        "  • Industrial monitoring: detect machine faults from sensor streams\n" +
                        "  • Medical diagnosis: identify abnormal readings in patient data\n" +
                        "  • Log analysis: surface rare error patterns in system logs"
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Statistical Methods") {
                    BodyText(
                        "Statistical approaches model the distribution of normal data and flag " +
                        "points that are unlikely under that distribution. The assumption is " +
                        "that normal data clusters around a centre, and anomalies are far away."
                    )
                    BodyText("Z-score (standard score):")
                    BodyText(
                        "Measure how many standard deviations a point is from the mean. A " +
                        "threshold is set (e.g. |z| > 3) — anything beyond it is flagged."
                    )
                    CodeBlock(
                        "z = (x - mean) / std_dev\n\n" +
                        "mean = 100, std_dev = 10\n" +
                        "x = 135  →  z = 3.5  →  flagged as anomaly\n" +
                        "x = 108  →  z = 0.8  →  normal"
                    )
                    BodyText("IQR (Interquartile Range):")
                    BodyText(
                        "Compute Q1 (25th percentile) and Q3 (75th percentile). Flag values " +
                        "below Q1 − 1.5×IQR or above Q3 + 1.5×IQR. More robust than z-score " +
                        "when the data is not perfectly Gaussian."
                    )
                    BodyText(
                        "Advantages: simple, fast, interpretable, no training required. " +
                        "Limitations: assume a specific distribution shape (often Gaussian); " +
                        "struggle with multi-dimensional data where anomalies are subtle " +
                        "combinations of normal individual values."
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Distance-Based Methods") {
                    BodyText(
                        "Distance-based approaches define anomalies as points that are far from " +
                        "their neighbours in the feature space. They make no assumption about " +
                        "the shape of the data distribution."
                    )
                    BodyText("k-Nearest Neighbours (k-NN) distance:")
                    BodyText(
                        "For each point, compute the average distance to its k nearest " +
                        "neighbours. Normal points have nearby neighbours; anomalies are " +
                        "isolated and have large k-NN distances."
                    )
                    BodyText("Local Outlier Factor (LOF):")
                    BodyText(
                        "Compares a point's local density to the density of its neighbours. " +
                        "If a point is in a much less dense region than its neighbours, it " +
                        "is likely an anomaly — even if its absolute distance is not large. " +
                        "This handles datasets with clusters of varying density."
                    )
                    CodeBlock(
                        "Dense cluster A: points 1mm apart\n" +
                        "Dense cluster B: points 5mm apart\n\n" +
                        "Point P is 10mm from cluster A:\n" +
                        "  k-NN distance is large relative to cluster A → anomaly\n\n" +
                        "LOF compares local density of P to its neighbours'\n" +
                        "densities — more robust than raw distance alone"
                    )
                    BodyText("Isolation Forest:")
                    BodyText(
                        "Builds many random decision trees. Anomalies are isolated earlier " +
                        "(with fewer splits) because they sit in sparse regions. Fast and " +
                        "effective for high-dimensional data — one of the most widely used " +
                        "practical methods."
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Unsupervised Learning — What It Means") {
                    BodyText(
                        "Machine learning is typically divided into three paradigms based on " +
                        "what kind of data the model is trained on:"
                    )
                    BodyText(
                        "  • Supervised learning: the training data consists of (input, label) " +
                        "pairs. The model learns to map inputs to known outputs. Example: " +
                        "10 000 emails each labeled 'spam' or 'not spam'. The model learns " +
                        "to predict the label for new emails."
                    )
                    BodyText(
                        "  • Unsupervised learning: the training data has no labels — just " +
                        "inputs. The model must find structure, patterns, or organisation in " +
                        "the data on its own. Nobody tells it what to look for; it discovers " +
                        "groupings, relationships, or regularities from the raw data alone."
                    )
                    BodyText(
                        "  • Semi-supervised learning: a small amount of labeled data combined " +
                        "with a large amount of unlabeled data. Common when labeling is " +
                        "expensive but raw data is abundant."
                    )
                    BodyText(
                        "Common unsupervised learning tasks:\n" +
                        "  • Clustering: group similar points together (k-means, DBSCAN)\n" +
                        "  • Dimensionality reduction: compress data to fewer dimensions " +
                        "while preserving structure (PCA, t-SNE, UMAP)\n" +
                        "  • Density estimation: learn the probability distribution of the data\n" +
                        "  • Generative modelling: learn to produce new samples that resemble " +
                        "the training data (GANs, VAEs)"
                    )
                    BodyText(
                        "Why unsupervised learning is natural for anomaly detection: anomalies " +
                        "are rare events that are hard to collect and expensive to label. You " +
                        "typically have abundant normal data but few or no labeled anomalies. " +
                        "Unsupervised methods learn what 'normal' looks like and flag deviations " +
                        "— no anomaly examples needed during training."
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Clustering for Anomaly Detection") {
                    BodyText(
                        "Clustering groups data points so that similar points end up in the " +
                        "same cluster. The idea for anomaly detection: normal behaviour forms " +
                        "tight clusters; anomalies either land far from every cluster centre " +
                        "or fail to join any cluster at all."
                    )
                    BodyText("k-means approach:")
                    CodeBlock(
                        "Training phase:\n" +
                        "  1. Run k-means on normal (unlabeled) data\n" +
                        "  2. Store the k cluster centres\n" +
                        "  3. Compute the 95th percentile of distances from\n" +
                        "     training points to their nearest cluster centre\n" +
                        "     → use this as the anomaly threshold\n\n" +
                        "Inference phase:\n" +
                        "  For each new point:\n" +
                        "    d = distance to nearest cluster centre\n" +
                        "    if d > threshold → flag as anomaly"
                    )
                    BodyText("DBSCAN approach:")
                    BodyText(
                        "DBSCAN (Density-Based Spatial Clustering of Applications with Noise) " +
                        "groups points that are densely packed together and explicitly labels " +
                        "points in sparse regions as 'noise'. Those noise points are the " +
                        "anomalies — no threshold tuning required."
                    )
                    BodyText(
                        "DBSCAN has two parameters: epsilon (the neighbourhood radius) and " +
                        "minPts (minimum points to form a dense region). Points with fewer " +
                        "than minPts neighbours within epsilon are noise."
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Autoencoder-Based Detection") {
                    BodyText(
                        "An autoencoder is a neural network trained to compress its input into " +
                        "a compact representation (the latent space) and then reconstruct the " +
                        "original input from that compressed form. Training objective: minimise " +
                        "the reconstruction error — the difference between input and output."
                    )
                    CodeBlock(
                        "Input → [Encoder] → latent vector → [Decoder] → Reconstruction\n\n" +
                        "Loss = || Input - Reconstruction ||²  (reconstruction error)"
                    )
                    BodyText(
                        "The model is trained on normal data only. It learns to reconstruct " +
                        "normal patterns efficiently. When an anomaly is presented at inference " +
                        "time, the autoencoder cannot reconstruct it well — it has never learned " +
                        "that pattern — so the reconstruction error is high."
                    )
                    CodeBlock(
                        "Manufacturing example (camera inspection of circuit boards):\n\n" +
                        "Training: thousands of photos of good boards\n" +
                        "  → autoencoder learns what a normal board looks like\n" +
                        "  → reconstruction error on training data: ~0.003\n\n" +
                        "Inference:\n" +
                        "  Good board   → error 0.004  → below threshold → pass\n" +
                        "  Defective board → error 0.21   → above threshold → flag"
                    )
                    BodyText(
                        "Autoencoders are especially powerful for high-dimensional data like " +
                        "images, time series, and network packet traces — where statistical " +
                        "and distance-based methods struggle."
                    )
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Choosing the Right Approach") {
                    BodyText(
                        "  • Statistical (z-score, IQR): low-dimensional data with a known or " +
                        "approximately Gaussian distribution. Fast, interpretable, no model " +
                        "training. Good first baseline.\n\n" +
                        "  • Distance-based (k-NN, LOF, Isolation Forest): general-purpose; " +
                        "no distribution assumption; works in higher dimensions. Isolation " +
                        "Forest is the practical first choice for tabular data.\n\n" +
                        "  • Clustering (k-means, DBSCAN): when you expect distinct groups of " +
                        "normal behaviour. DBSCAN is parameter-sensitive but gives explicit " +
                        "noise labels.\n\n" +
                        "  • Autoencoder: high-dimensional inputs (images, sequences, " +
                        "multivariate time series) where structure is complex. Requires " +
                        "enough normal training data to learn a good reconstruction.\n\n" +
                        "  • Supervised or semi-supervised: if you have labeled anomaly " +
                        "examples, use them — supervised classifiers will outperform " +
                        "unsupervised methods when anomaly labels are available."
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
