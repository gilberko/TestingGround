package com.example.developmentapp.screens.algorithms

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
fun AlphaBetaPruningScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Alpha Beta Pruning",
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
            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("The Minimax Problem") {
                    BodyText("In a two-player zero-sum game (chess, checkers, tic-tac-toe), one player is the Maximizer (wants the highest score) and the other is the Minimizer (wants the lowest score). To choose the best move, a program looks N moves ahead, evaluating all possible positions and working backwards.")
                    BodyText("This is the Minimax algorithm: at Maximizer's turn, pick the child with the highest value; at Minimizer's turn, pick the child with the lowest value. The problem: the game tree grows exponentially. A branching factor of 30 and depth 6 gives 30^6 ≈ 700 million nodes.")
                    CodeBlock("""
// Pure minimax — no pruning
function minimax(node, depth, isMaximizing):
    if depth == 0 or node is terminal:
        return evaluate(node)   // heuristic score of the position

    if isMaximizing:
        best = -∞
        for each child of node:
            val = minimax(child, depth-1, false)
            best = max(best, val)
        return best
    else:
        best = +∞
        for each child of node:
            val = minimax(child, depth-1, true)
            best = min(best, val)
        return best
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Alpha and Beta — What They Mean") {
                    BodyText("Alpha (α) is the best value the Maximizer is already guaranteed somewhere in the search above the current node. The Maximizer will never accept anything lower than alpha.")
                    BodyText("Beta (β) is the best value the Minimizer is already guaranteed somewhere in the search above the current node. The Minimizer will never accept anything higher than beta.")
                    BodyText("We can prune (skip) a subtree whenever alpha ≥ beta — the current node will never be chosen by rational play. A Maximizer node whose value has climbed above beta can be cut: the Minimizer ancestor already has a better option and won't go here. A Minimizer node whose value has dropped below alpha can be cut: the Maximizer ancestor already has a better option and won't go here.")
                    CodeBlock("""
// Example tree — root is Maximizer
//
//          MAX
//         /   \
//        MIN   MIN
//       / \   / \
//      3   5 2   9
//
// Left MIN picks min(3,5) = 3
// Right MIN picks min(2,9) = 2 ... but when it sees 2 < 3 (alpha),
// it can stop — the root MAX will never choose this branch anyway.
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Alpha Beta Pruning — Pseudocode") {
                    BodyText("Pass alpha and beta down through every recursive call. Update alpha at Maximizer nodes and beta at Minimizer nodes. Prune (return early) whenever alpha ≥ beta.")
                    CodeBlock("""
// alpha = best score Maximizer can guarantee from ancestors
// beta  = best score Minimizer can guarantee from ancestors
// Call: alphaBeta(root, depth, -∞, +∞, true)

function alphaBeta(node, depth, alpha, beta, isMaximizing):
    if depth == 0 or node is terminal:
        return evaluate(node)

    if isMaximizing:
        value = -∞
        for each child of node:
            value = max(value, alphaBeta(child, depth-1, alpha, beta, false))
            alpha = max(alpha, value)
            if alpha >= beta:
                break          // Beta cut-off — Minimizer won't come here
        return value

    else:  // Minimizing
        value = +∞
        for each child of node:
            value = min(value, alphaBeta(child, depth-1, alpha, beta, true))
            beta = min(beta, value)
            if alpha >= beta:
                break          // Alpha cut-off — Maximizer won't come here
        return value
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Complexity and Move Ordering") {
                    BodyText("Worst case (random move order): same as plain minimax — O(b^d) nodes where b is branching factor and d is depth.")
                    BodyText("Best case (perfect move order — best moves examined first): O(b^(d/2)) — effectively doubles the search depth for the same cost. Examining captures and checks first approximates this in practice.")
                    BodyText("In chess engines, alpha-beta with good move ordering allows searching ~12 plies instead of ~6 for the same computation budget. Additional techniques like iterative deepening, transposition tables, and null-move pruning extend this further.")
                    CodeBlock("""
// Practical depth estimate (branching factor ~30)
//
// Plain minimax depth 6:  30^6  ≈ 729,000,000 nodes
// Alpha-beta best case:   30^3  ≈     27,000 nodes
// Alpha-beta average:     30^4.5 ≈  4,900,000 nodes
//
// Move ordering tips:
//   1. Captures (MVV-LVA: most valuable victim, least valuable attacker)
//   2. Killer moves (moves that caused beta cutoffs at same depth before)
//   3. History heuristic (moves that have been good historically)
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
