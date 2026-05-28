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
fun Lesson07CharacterController2DScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Unity — Lesson 7 — Character Controller (2D)",
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
                        "• Movement\n\n" +
                        "For smooth horizontal movement read Input.GetAxis(\"Horizontal\") (returns -1 to 1) " +
                        "and set the Rigidbody2D velocity directly. Setting velocity rather than using " +
                        "AddForce gives more responsive, game-feel-friendly movement because it doesn't " +
                        "accumulate momentum."
                    )
                    CodeBlock(
                        "void Update() {\n" +
                        "    float h = Input.GetAxis(\"Horizontal\");\n" +
                        "    rb.linearVelocity = new Vector2(h * moveSpeed, rb.linearVelocity.y);\n" +
                        "    // preserve Y velocity (gravity) while controlling X\n" +
                        "}"
                    )
                    BodyText(
                        "• Jumping\n\n" +
                        "For a jump, apply an upward impulse force when the player presses Space, but only " +
                        "if the character is grounded. Check grounded state with an overlap test at the " +
                        "character's feet against a Ground layer."
                    )
                    CodeBlock(
                        "bool IsGrounded() {\n" +
                        "    return Physics2D.OverlapCircle(feetPos.position, 0.1f, groundLayer);\n" +
                        "}\n\n" +
                        "void Update() {\n" +
                        "    if (Input.GetKeyDown(KeyCode.Space) && IsGrounded())\n" +
                        "        rb.AddForce(Vector2.up * jumpForce, ForceMode2D.Impulse);\n" +
                        "}"
                    )
                    BodyText(
                        "• Double jump\n\n" +
                        "Allow a second jump in mid-air by tracking how many jumps remain. Reset the count " +
                        "when the character lands. Decrement it each time the player jumps."
                    )
                    CodeBlock(
                        "int jumpsLeft = 2;\n\n" +
                        "void Update() {\n" +
                        "    if (IsGrounded()) jumpsLeft = 2;\n" +
                        "    if (Input.GetKeyDown(KeyCode.Space) && jumpsLeft > 0) {\n" +
                        "        rb.linearVelocity = new Vector2(rb.linearVelocity.x, jumpForce);\n" +
                        "        jumpsLeft--;\n" +
                        "    }\n" +
                        "}"
                    )
                    BodyText(
                        "• Dash\n\n" +
                        "A dash applies a burst of horizontal speed for a short time. Use a coroutine to " +
                        "apply the dash velocity and then restore normal movement after a fixed duration. " +
                        "Add a cooldown bool so the player cannot spam the dash."
                    )
                    CodeBlock(
                        "IEnumerator Dash() {\n" +
                        "    isDashing = true;\n" +
                        "    rb.linearVelocity = new Vector2(dashSpeed * facingDir, rb.linearVelocity.y);\n" +
                        "    yield return new WaitForSeconds(dashDuration);\n" +
                        "    isDashing = false;\n" +
                        "    yield return new WaitForSeconds(dashCooldown);\n" +
                        "    canDash = true;\n" +
                        "}"
                    )
                }
            }
            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Practice") {
                    BodyText(
                        "• Create a platformer controller\n\n" +
                        "Implement a complete 2D character controller with: horizontal movement, single " +
                        "jump with ground check, double jump counter, and dash on Shift key. Expose " +
                        "moveSpeed, jumpForce, dashSpeed, and dashDuration as public fields for Inspector " +
                        "tuning. Test on a level with several platforms."
                    )
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
