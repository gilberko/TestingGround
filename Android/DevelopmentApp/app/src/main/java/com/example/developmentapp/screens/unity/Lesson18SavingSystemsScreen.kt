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
fun Lesson18SavingSystemsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 18 — Saving Systems",
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
                        "• PlayerPrefs\n\n" +
                        "PlayerPrefs is Unity's built-in key-value store for simple persistent data — " +
                        "best for settings (volume, graphics quality) and small values (high score, " +
                        "tutorial completed flag). Data persists across game sessions and is stored in " +
                        "the Windows registry or a platform-specific file. Do not use PlayerPrefs for " +
                        "large amounts of structured game data — it is not encrypted and not designed " +
                        "for complex saves."
                    )
                    CodeBlock(
                        "// Save\n" +
                        "PlayerPrefs.SetInt(\"HighScore\", score);\n" +
                        "PlayerPrefs.SetFloat(\"MusicVolume\", 0.8f);\n" +
                        "PlayerPrefs.Save();\n\n" +
                        "// Load\n" +
                        "int high = PlayerPrefs.GetInt(\"HighScore\", 0);  // 0 = default\n" +
                        "float vol = PlayerPrefs.GetFloat(\"MusicVolume\", 1f);"
                    )
                    BodyText(
                        "• JSON save files\n\n" +
                        "For full game saves (player position, inventory, level state), serialize a " +
                        "C# data class to JSON and write it to Application.persistentDataPath — a " +
                        "platform-appropriate folder where the app has write permission. Use " +
                        "JsonUtility (built-in, simple) or Newtonsoft.Json (more powerful) to convert " +
                        "between objects and JSON strings."
                    )
                    CodeBlock(
                        "[System.Serializable]\n" +
                        "public class SaveData {\n" +
                        "    public int level;\n" +
                        "    public float health;\n" +
                        "    public Vector3 position;\n" +
                        "}\n\n" +
                        "void Save() {\n" +
                        "    SaveData data = new SaveData { level=3, health=80f,\n" +
                        "        position=player.position };\n" +
                        "    string json = JsonUtility.ToJson(data);\n" +
                        "    File.WriteAllText(Application.persistentDataPath + \"/save.json\", json);\n" +
                        "}\n\n" +
                        "void Load() {\n" +
                        "    string path = Application.persistentDataPath + \"/save.json\";\n" +
                        "    if (File.Exists(path)) {\n" +
                        "        SaveData data = JsonUtility.FromJson<SaveData>(File.ReadAllText(path));\n" +
                        "        player.position = data.position;\n" +
                        "    }\n" +
                        "}"
                    )
                    BodyText(
                        "• Serialization\n\n" +
                        "For a class to be serialized by JsonUtility it must be marked [System.Serializable] " +
                        "and contain only serializable fields (primitives, strings, arrays, other " +
                        "serializable classes). Unity structs like Vector3 and Quaternion are supported. " +
                        "Note that JsonUtility cannot serialize Dictionaries — use arrays of key-value " +
                        "pairs instead, or switch to Newtonsoft.Json."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Save/load game progress\n\n" +
                        "Add a SaveData class with the player's current level, score, and health. Write " +
                        "Save() and Load() methods using JsonUtility and Application.persistentDataPath. " +
                        "Call Save() when the player finishes a level and Load() at game start. Add a " +
                        "\"Continue\" button on the main menu that is only visible if a save file exists " +
                        "(check with File.Exists)."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
