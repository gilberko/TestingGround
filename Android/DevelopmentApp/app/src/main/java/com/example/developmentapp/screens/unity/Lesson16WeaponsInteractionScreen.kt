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
fun Lesson16WeaponsInteractionScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 16 — Weapons & Interaction",
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
                        "• Shooting\n\n" +
                        "Shooting in Unity can be implemented two ways: projectile-based (instantiate a " +
                        "bullet Prefab and let physics move it) or hitscan (instant raycast). Hitscan is " +
                        "simpler and works well for rifles and pistols. Projectile-based is better for " +
                        "grenades, rockets, and slow projectiles where travel time matters."
                    )
                    BodyText(
                        "• Raycast weapons\n\n" +
                        "Hitscan weapons fire a Physics.Raycast from the camera (or muzzle) along the " +
                        "camera's forward direction. On hit, spawn a bullet hole decal as a child of the " +
                        "hit surface, apply damage to any Health component on the hit object, and play " +
                        "a hit particle effect."
                    )
                    CodeBlock(
                        "public float damage = 25f;\n" +
                        "public float range = 100f;\n" +
                        "public GameObject bulletHolePrefab;\n\n" +
                        "void Shoot() {\n" +
                        "    RaycastHit hit;\n" +
                        "    Ray ray = Camera.main.ScreenPointToRay(\n" +
                        "        new Vector3(Screen.width/2, Screen.height/2));\n" +
                        "    if (Physics.Raycast(ray, out hit, range)) {\n" +
                        "        Instantiate(bulletHolePrefab, hit.point,\n" +
                        "            Quaternion.LookRotation(hit.normal));\n" +
                        "        hit.collider.GetComponent<Health>()?.TakeDamage(damage);\n" +
                        "    }\n" +
                        "}"
                    )
                    BodyText(
                        "• Pickups\n\n" +
                        "Pickups are trigger colliders that give the player items on contact. Tag them " +
                        "(e.g. \"Ammo\", \"Health\") and detect them in the player's OnTriggerEnter. " +
                        "Destroy the pickup GameObject after collection and apply the effect (add ammo, " +
                        "restore health). Use a slowly rotating animation (transform.Rotate in Update) " +
                        "and a bobbing motion to make pickups visually obvious."
                    )
                    BodyText(
                        "• Interactions\n\n" +
                        "General interactions (opening doors, pressing buttons, picking up objects) use " +
                        "a raycast from the player camera. When the ray hits an object within range, " +
                        "check for an IInteractable interface and call its Interact() method. Show an " +
                        "on-screen prompt (\"Press E to open\") when aiming at an interactable."
                    )
                    CodeBlock(
                        "interface IInteractable { void Interact(); }\n\n" +
                        "void Update() {\n" +
                        "    RaycastHit hit;\n" +
                        "    if (Physics.Raycast(cam.position, cam.forward, out hit, 3f)) {\n" +
                        "        var interactable = hit.collider.GetComponent<IInteractable>();\n" +
                        "        if (interactable != null) {\n" +
                        "            promptUI.SetActive(true);\n" +
                        "            if (Input.GetKeyDown(KeyCode.E))\n" +
                        "                interactable.Interact();\n" +
                        "        } else promptUI.SetActive(false);\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• FPS weapon system\n\n" +
                        "Build an FPS scene with the Lesson 13 character controller. Add a gun model " +
                        "parented to the camera. Implement hitscan shooting on left-click with damage, " +
                        "a bullet hole decal, and a muzzle flash particle effect. Add ammo pickup " +
                        "collectibles. Implement an interactable door that opens when the player presses E."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
