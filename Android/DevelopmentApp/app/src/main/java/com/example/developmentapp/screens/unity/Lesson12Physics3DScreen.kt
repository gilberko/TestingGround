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
fun Lesson12Physics3DScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 12 — 3D Physics",
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
                        "• Rigidbody\n\n" +
                        "The 3D Rigidbody component (not Rigidbody2D) puts an object under the Unity " +
                        "physics engine. It applies gravity, handles collisions with other colliders, " +
                        "and responds to AddForce and AddTorque calls. Set Is Kinematic to control the " +
                        "object purely via script without physics forces. Use Freeze Position/Rotation " +
                        "constraints to prevent unwanted movement on specific axes."
                    )
                    BodyText(
                        "• Colliders\n\n" +
                        "3D colliders define the physical shape: BoxCollider (cubes/rectangular objects), " +
                        "SphereCollider (balls), CapsuleCollider (characters — two hemispheres and a " +
                        "cylinder, efficient for bipeds), MeshCollider (exact mesh shape, expensive). " +
                        "For characters, prefer CapsuleCollider over MeshCollider. Set the collider " +
                        "material (Physic Material) to control friction and bounciness."
                    )
                    BodyText(
                        "• Forces\n\n" +
                        "Apply forces to Rigidbodies to simulate physical pushes, explosions, and jumps. " +
                        "ForceMode.Force adds a continuous force over time; ForceMode.Impulse adds an " +
                        "instant velocity change (best for jumps and explosions). AddExplosionForce " +
                        "applies an outward blast from a point — useful for bomb explosions."
                    )
                    CodeBlock(
                        "void Jump() {\n" +
                        "    rb.AddForce(Vector3.up * jumpForce, ForceMode.Impulse);\n" +
                        "}\n\n" +
                        "void Explode(Vector3 center) {\n" +
                        "    Collider[] cols = Physics.OverlapSphere(center, blastRadius);\n" +
                        "    foreach (var col in cols) {\n" +
                        "        Rigidbody r = col.GetComponent<Rigidbody>();\n" +
                        "        if (r != null)\n" +
                        "            r.AddExplosionForce(force, center, blastRadius);\n" +
                        "    }\n" +
                        "}"
                    )
                    BodyText(
                        "• Raycasting\n\n" +
                        "A raycast shoots an invisible ray from a point in a direction and returns " +
                        "information about the first collider it hits. Use it for hit detection in " +
                        "shooters, ground checks, and line-of-sight tests."
                    )
                    CodeBlock(
                        "void Shoot() {\n" +
                        "    RaycastHit hit;\n" +
                        "    if (Physics.Raycast(muzzle.position, muzzle.forward, out hit, range)) {\n" +
                        "        Debug.Log(\"Hit: \" + hit.collider.name);\n" +
                        "        hit.collider.GetComponent<Health>()?.TakeDamage(damage);\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Physics objects\n\n" +
                        "Create a 3D scene with a large Plane floor and several ramps (rotated Cubes). " +
                        "Spawn spheres and boxes from above and watch them tumble down the ramps. Add " +
                        "Physics Materials with different bounciness values (0 = no bounce, 1 = full " +
                        "bounce) and observe the difference."
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Mini Project") {
                    BodyText(
                        "• Physics obstacle course\n\n" +
                        "Design a course with ramps, spinning obstacles (use FixedUpdate + rb.MoveRotation), " +
                        "bouncy surfaces, and a goal trigger at the end. Create a ball the player pushes " +
                        "with WASD using AddForce. Display a timer UI and show the elapsed time when the " +
                        "ball hits the goal trigger."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
