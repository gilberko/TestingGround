package com.example.developmentapp.screens.unity

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
fun Lesson19OptimizationScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 19 — Optimization",
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
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp)) {
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Topics") {
                    BodyText(
                        "• Draw calls\n\n" +
                        "A draw call is a command sent from the CPU to the GPU to render a mesh. " +
                        "Each unique material on each visible object generates at least one draw call. " +
                        "Too many draw calls is a common performance bottleneck on mobile. Reduce them " +
                        "with Static Batching (combine non-moving objects sharing a material), Dynamic " +
                        "Batching (automatic for small meshes), and GPU Instancing (render many identical " +
                        "objects with one draw call). Use the Stats window in the Game view or the Frame " +
                        "Debugger to count draw calls."
                    )
                    BodyText(
                        "• Object pooling\n\n" +
                        "Instantiate() and Destroy() are expensive because they trigger memory allocation " +
                        "and garbage collection. For frequently spawned objects (bullets, particles, " +
                        "enemies), use an object pool: pre-create a set of objects at startup, disable " +
                        "them, and re-enable/reuse them instead of creating new ones. Unity 2021+ " +
                        "includes a built-in ObjectPool<T> class."
                    )
                    CodeBlock(
                        "Queue<GameObject> pool = new Queue<GameObject>();\n\n" +
                        "GameObject GetBullet() {\n" +
                        "    if (pool.Count > 0) {\n" +
                        "        var b = pool.Dequeue();\n" +
                        "        b.SetActive(true);\n" +
                        "        return b;\n" +
                        "    }\n" +
                        "    return Instantiate(bulletPrefab);\n" +
                        "}\n\n" +
                        "void ReturnBullet(GameObject b) {\n" +
                        "    b.SetActive(false);\n" +
                        "    pool.Enqueue(b);\n" +
                        "}"
                    )
                    BodyText(
                        "• Profiling\n\n" +
                        "The Unity Profiler (Window → Analysis → Profiler) shows a frame-by-frame " +
                        "breakdown of CPU time, GPU time, memory, and rendering statistics. Record a " +
                        "gameplay session, then click on a slow frame to see which methods consumed the " +
                        "most time. Deep Profile mode shows per-method timing but has significant overhead " +
                        "— use it only when tracking down a specific bottleneck."
                    )
                    BodyText(
                        "• Garbage collection\n\n" +
                        "C# uses automatic garbage collection (GC). When the GC runs it can cause brief " +
                        "frame spikes (\"GC hiccups\"). Avoid allocations in Update() — string concatenation, " +
                        "LINQ queries, and GetComponent calls create garbage. Cache component references " +
                        "in Start(). Use StringBuilder for string building in hot paths. Pre-allocate " +
                        "arrays and lists instead of creating new ones every frame."
                    )
                    CodeBlock(
                        "// Bad - allocates a new string every frame\n" +
                        "void Update() { scoreText.text = \"Score: \" + score; }\n\n" +
                        "// Better - no allocation\n" +
                        "void Update() { scoreText.text = score.ToString(); }\n\n" +
                        "// Cache in Start, not Update\n" +
                        "Rigidbody rb;\n" +
                        "void Start() { rb = GetComponent<Rigidbody>(); }"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
