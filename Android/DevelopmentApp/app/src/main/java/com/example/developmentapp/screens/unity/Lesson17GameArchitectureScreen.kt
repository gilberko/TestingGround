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
fun Lesson17GameArchitectureScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 17 — Game Architecture",
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
                        "• Managers\n\n" +
                        "A manager is a dedicated script responsible for one game system — GameManager " +
                        "(win/lose logic, score), AudioManager (playing sounds), UIManager (updating " +
                        "HUD), EnemyManager (spawning enemies). Splitting systems into managers keeps " +
                        "code organized and prevents giant \"god\" scripts. Place managers on empty " +
                        "GameObjects in the scene and mark them DontDestroyOnLoad if they need to " +
                        "persist across scenes."
                    )
                    BodyText(
                        "• Singleton pattern\n\n" +
                        "A Singleton ensures only one instance of a class exists and provides a global " +
                        "access point. In Unity, implement it in Awake(): if an instance already exists, " +
                        "destroy this duplicate; otherwise, assign this as the instance."
                    )
                    CodeBlock(
                        "public class GameManager : MonoBehaviour {\n" +
                        "    public static GameManager Instance;\n\n" +
                        "    void Awake() {\n" +
                        "        if (Instance != null) { Destroy(gameObject); return; }\n" +
                        "        Instance = this;\n" +
                        "        DontDestroyOnLoad(gameObject);\n" +
                        "    }\n\n" +
                        "    // Access from anywhere:\n" +
                        "    // GameManager.Instance.AddScore(10);\n" +
                        "}"
                    )
                    BodyText(
                        "• ScriptableObjects\n\n" +
                        "A ScriptableObject is a data container asset that lives in your Project (not " +
                        "in the scene). Use them to store configuration data — enemy stats, weapon " +
                        "properties, item databases — that you want to edit in the Inspector and share " +
                        "across scenes without hard-coded values. They decouple data from behavior."
                    )
                    CodeBlock(
                        "[CreateAssetMenu(menuName = \"Game/Enemy Stats\")]\n" +
                        "public class EnemyStats : ScriptableObject {\n" +
                        "    public float health = 100f;\n" +
                        "    public float speed = 3.5f;\n" +
                        "    public float damage = 10f;\n" +
                        "}\n\n" +
                        "// In the enemy script:\n" +
                        "public EnemyStats stats;  // drag the asset in the Inspector"
                    )
                    BodyText(
                        "• Event systems\n\n" +
                        "An event system decouples senders from receivers so systems don't need direct " +
                        "references to each other. Use C# events (or UnityEvents for Inspector wiring) " +
                        "to broadcast game events like OnPlayerDeath, OnScoreChanged, or OnEnemySpawned. " +
                        "Any system can subscribe without the sender knowing who is listening."
                    )
                    CodeBlock(
                        "public static event System.Action<int> OnScoreChanged;\n\n" +
                        "public void AddScore(int points) {\n" +
                        "    score += points;\n" +
                        "    OnScoreChanged?.Invoke(score);  // notify all listeners\n" +
                        "}\n\n" +
                        "// In UIManager:\n" +
                        "void OnEnable()  => GameManager.OnScoreChanged += UpdateScoreUI;\n" +
                        "void OnDisable() => GameManager.OnScoreChanged -= UpdateScoreUI;"
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
