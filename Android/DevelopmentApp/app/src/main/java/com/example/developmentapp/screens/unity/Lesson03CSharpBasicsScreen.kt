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
fun Lesson03CSharpBasicsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 3 — C# Basics",
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
                        "• Variables\n\n" +
                        "A variable is a named storage location for a value. In C# you declare a variable " +
                        "by writing the type, then the name, then optionally an initial value."
                    )
                    CodeBlock(
                        "int score = 0;\n" +
                        "float speed = 5.5f;\n" +
                        "bool isAlive = true;\n" +
                        "string playerName = \"Hero\";"
                    )
                    BodyText(
                        "• Types\n\n" +
                        "C# is statically typed — every variable has a fixed type. Common types used in " +
                        "Unity: int (whole numbers), float (decimal numbers, always write the f suffix), " +
                        "bool (true/false), string (text), Vector2/Vector3 (Unity position/direction), " +
                        "GameObject (reference to any Unity object)."
                    )
                    BodyText(
                        "• Methods\n\n" +
                        "A method is a named block of code that performs a task. It can accept parameters " +
                        "and return a value (or void if it returns nothing)."
                    )
                    CodeBlock(
                        "int Add(int a, int b) {\n" +
                        "    return a + b;\n" +
                        "}\n\n" +
                        "void PrintScore() {\n" +
                        "    Debug.Log(\"Score: \" + score);\n" +
                        "}"
                    )
                    BodyText(
                        "• Classes\n\n" +
                        "A class is a blueprint for objects. In Unity every script is a class. Fields " +
                        "declared inside the class are accessible throughout it; public fields appear in " +
                        "the Inspector."
                    )
                    CodeBlock(
                        "public class Player {\n" +
                        "    public int health = 100;\n" +
                        "    public float speed = 5f;\n\n" +
                        "    public void TakeDamage(int amount) {\n" +
                        "        health -= amount;\n" +
                        "    }\n" +
                        "}"
                    )
                    BodyText(
                        "• if/else\n\n" +
                        "Conditional statements execute code only when a condition is true."
                    )
                    CodeBlock(
                        "if (health <= 0) {\n" +
                        "    Debug.Log(\"Game Over\");\n" +
                        "} else if (health < 25) {\n" +
                        "    Debug.Log(\"Low health!\");\n" +
                        "} else {\n" +
                        "    Debug.Log(\"Healthy\");\n" +
                        "}"
                    )
                    BodyText(
                        "• Loops\n\n" +
                        "Loops repeat a block of code. The for loop is used when you know the number of " +
                        "iterations; foreach iterates over a collection; while runs as long as a condition " +
                        "is true."
                    )
                    CodeBlock(
                        "for (int i = 0; i < 5; i++) {\n" +
                        "    Debug.Log(\"Count: \" + i);\n" +
                        "}\n\n" +
                        "foreach (GameObject enemy in enemies) {\n" +
                        "    enemy.SetActive(false);\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText("• Print messages\n\nCreate a new C# script, attach it to a GameObject, and use Debug.Log() in Start() to print your name, the current score, and whether the player is alive.")
                    BodyText("• Move objects\n\nIn Update(), use transform.Translate(Vector3.right * Time.deltaTime) to make an object slide to the right each frame.")
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Homework") {
                    BodyText("• Simple calculator script\n\nWrite a script with four methods: Add, Subtract, Multiply, Divide. Call each in Start() with hardcoded values and print the results to the Console. Handle division by zero with an if check.")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
