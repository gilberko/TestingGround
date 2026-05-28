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
fun Lesson13CharacterController3DScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 13 — Character Controller (3D)",
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
                        "• FPS controller\n\n" +
                        "A first-person controller moves the camera with the mouse and the character body " +
                        "with WASD. Rotate the player object horizontally with mouse X input and rotate " +
                        "the camera (child of the player) vertically with mouse Y input. Clamp the " +
                        "vertical rotation to prevent flipping. Use CharacterController.Move() for " +
                        "collision-safe movement without needing a Rigidbody."
                    )
                    CodeBlock(
                        "CharacterController cc;\n" +
                        "float xRotation = 0f;\n\n" +
                        "void Update() {\n" +
                        "    float mouseX = Input.GetAxis(\"Mouse X\") * mouseSens;\n" +
                        "    float mouseY = Input.GetAxis(\"Mouse Y\") * mouseSens;\n" +
                        "    xRotation = Mathf.Clamp(xRotation - mouseY, -90f, 90f);\n" +
                        "    cam.localRotation = Quaternion.Euler(xRotation, 0, 0);\n" +
                        "    transform.Rotate(Vector3.up * mouseX);\n" +
                        "    // WASD movement\n" +
                        "    Vector3 move = transform.right * h + transform.forward * v;\n" +
                        "    cc.Move(move * speed * Time.deltaTime);\n" +
                        "}"
                    )
                    BodyText(
                        "• Third-person movement\n\n" +
                        "In third-person the camera sits behind and above the character. Move the " +
                        "character relative to the camera's forward direction so WASD always moves " +
                        "in the direction the camera is pointing. Project the camera's forward vector " +
                        "onto the horizontal plane (zero out Y, normalize) to keep movement level."
                    )
                    CodeBlock(
                        "void MoveCharacter(float h, float v) {\n" +
                        "    Vector3 camForward = cam.forward;\n" +
                        "    camForward.y = 0;\n" +
                        "    camForward.Normalize();\n" +
                        "    Vector3 move = camForward * v + cam.right * h;\n" +
                        "    cc.Move(move * speed * Time.deltaTime);\n" +
                        "    if (move != Vector3.zero)\n" +
                        "        transform.forward = move;  // face movement direction\n" +
                        "}"
                    )
                    BodyText(
                        "• Camera follow\n\n" +
                        "A simple follow camera sets its position every LateUpdate() to the player's " +
                        "position plus an offset. Using LateUpdate (after all Update calls) prevents " +
                        "jitter because the character has already moved for this frame. For smoother " +
                        "motion use Vector3.Lerp or Vector3.SmoothDamp to gradually close the gap."
                    )
                    CodeBlock(
                        "public Vector3 offset = new Vector3(0, 5, -7);\n\n" +
                        "void LateUpdate() {\n" +
                        "    Vector3 target = player.position + offset;\n" +
                        "    transform.position = Vector3.SmoothDamp(\n" +
                        "        transform.position, target, ref velocity, smoothTime);\n" +
                        "    transform.LookAt(player);\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Basic 3D movement\n\n" +
                        "Create a capsule-shaped player with a CharacterController. Implement FPS look " +
                        "and WASD movement. Lock the cursor with Cursor.lockState = CursorLockMode.Locked. " +
                        "Add gravity manually (apply downward velocity each frame, reset when grounded). " +
                        "Test on a simple level with ramps and obstacles."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
