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
fun Lesson15AIBasicsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 15 — AI Basics",
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
                        "• NavMesh\n\n" +
                        "NavMesh (Navigation Mesh) is a simplified walkable surface baked from your scene " +
                        "geometry. Unity analyzes all static geometry and computes where an agent can " +
                        "walk, accounting for step height, slope, and drop limits. Bake it in Window → " +
                        "AI → Navigation → Bake. Mark static floor geometry as Navigation Static. The " +
                        "NavMesh stores which areas are walkable and pre-computes connectivity for fast " +
                        "pathfinding at runtime."
                    )
                    BodyText(
                        "• Enemy chasing\n\n" +
                        "Add a NavMeshAgent component to an enemy GameObject. The agent automatically " +
                        "handles pathfinding and steering. Set its destination to the player's position " +
                        "every frame (or on a timer) to make it chase. The agent respects the baked " +
                        "NavMesh so it walks around obstacles rather than through them."
                    )
                    CodeBlock(
                        "using UnityEngine.AI;\n\n" +
                        "NavMeshAgent agent;\n" +
                        "Transform player;\n\n" +
                        "void Start() {\n" +
                        "    agent = GetComponent<NavMeshAgent>();\n" +
                        "    player = GameObject.FindWithTag(\"Player\").transform;\n" +
                        "}\n\n" +
                        "void Update() {\n" +
                        "    agent.SetDestination(player.position);\n" +
                        "}"
                    )
                    BodyText(
                        "• Patrol systems\n\n" +
                        "For patrol behavior, give the enemy an array of waypoints and cycle through them. " +
                        "When the agent is close enough to the current waypoint (check agent.remainingDistance), " +
                        "advance to the next one. Add a brief wait at each waypoint using a coroutine."
                    )
                    CodeBlock(
                        "Transform[] waypoints;\n" +
                        "int index = 0;\n\n" +
                        "IEnumerator Patrol() {\n" +
                        "    while (true) {\n" +
                        "        agent.SetDestination(waypoints[index].position);\n" +
                        "        yield return new WaitUntil(() => agent.remainingDistance < 0.5f);\n" +
                        "        yield return new WaitForSeconds(waitTime);\n" +
                        "        index = (index + 1) % waypoints.Length;\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Enemy AI\n\n" +
                        "Create a scene with a floor and walls. Bake a NavMesh. Add an enemy with a " +
                        "NavMeshAgent. Implement a simple state machine: if the player is within detection " +
                        "range (Physics.CheckSphere), switch to Chase state and set destination to the " +
                        "player. Otherwise, Patrol between three waypoints. Show the current state in the " +
                        "Console for debugging."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
