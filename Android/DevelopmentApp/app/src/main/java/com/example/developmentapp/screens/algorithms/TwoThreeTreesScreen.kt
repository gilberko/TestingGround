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
fun TwoThreeTreesScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "2-3 Trees",
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
                SectionCard("Structure and Properties") {
                    BodyText("A 2-3 tree is a balanced search tree where every internal node is either a 2-node (holds 1 key, has 2 children) or a 3-node (holds 2 keys, has 3 children). All leaves are at the same depth — the tree is always perfectly height-balanced.")
                    BodyText("Height is O(log n), guaranteeing O(log n) for all operations regardless of insertion order. This is unlike a plain BST which degenerates to O(n) for sorted input.")
                    CodeBlock("""
// 2-node: one key k, two children
//   left subtree  < k
//   right subtree > k
//
//       [10]
//      /    \
//   <10     >10

// 3-node: two keys k1 < k2, three children
//   left   subtree < k1
//   middle subtree between k1 and k2
//   right  subtree > k2
//
//      [10 | 20]
//     /    |    \
//  <10  10..20  >20

// All leaves at the same level — always balanced
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Lookup — O(log n)") {
                    BodyText("Search works exactly like a BST, extended to compare against both keys in a 3-node to choose among three children.")
                    CodeBlock("""
function lookup(node, key):
    if node is null: return NOT_FOUND

    if node is 2-node (key1):
        if key == key1: return FOUND
        if key < key1:  return lookup(node.left, key)
        else:           return lookup(node.right, key)

    if node is 3-node (key1, key2):   // key1 < key2
        if key == key1 or key == key2: return FOUND
        if key < key1:  return lookup(node.left,   key)
        if key < key2:  return lookup(node.middle, key)
        else:           return lookup(node.right,  key)
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Insertion — O(log n)") {
                    BodyText("Always insert into a leaf. If the leaf is a 2-node, add the key — it becomes a 3-node. Done. If the leaf is already a 3-node, it would become a 4-node (temporarily holding 3 keys). Split it: the middle key is pushed up to the parent, and the left and right keys become separate 2-nodes. Repeat upward if the parent also overflows. If the root overflows, split it and create a new root — this is the only way the tree grows taller.")
                    CodeBlock("""
function insert(tree, key):
    leaf = findLeaf(tree.root, key)    // descend to correct leaf

    if leaf is 2-node:
        add key to leaf → becomes 3-node
        return                         // done — no overflow

    // leaf is 3-node → temporarily 4-node [k1 | key | k2] (sorted)
    splitNode(leaf, tree)

function splitNode(node, tree):
    // node temporarily holds 3 keys: left, mid, right (sorted)
    left  = new 2-node(node.key1)     // smallest key
    right = new 2-node(node.key3)     // largest key
    mid   = node.key2                  // middle key bubbles up

    if node is root:
        newRoot = new 2-node(mid)
        newRoot.left  = left
        newRoot.right = right
        tree.root = newRoot
        return

    parent = node.parent
    replace node with left and right in parent, insert mid into parent

    if parent overflows (becomes 4-node):
        splitNode(parent, tree)        // propagate split upward

// Example — insert 6 into tree with root [5|8], leaves [2|4] [6|7] [9]
//   Leaf [6|7] becomes [6|6|7] → overflow
//   Split: left=[6], right=[7], mid=6 pushed to root
//   Root [5|6|8] → overflow → split again
//   New root [6], left child [5], right child [8]
//   Tree grows by one level
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(12.dp)) }

            item {
                SectionCard("Deletion — O(log n)") {
                    BodyText("If the key is in an internal node, swap it with its in-order predecessor or successor (which is always in a leaf), then delete from the leaf. The hard case is deleting from a leaf that is a 2-node — removing its only key would leave an empty node (underflow).")
                    BodyText("Fix underflow by first trying to rotate: if a sibling is a 3-node, borrow a key from it through the parent (rotate a key down from parent, push sibling key up). If no sibling is a 3-node, merge: combine the underflowing node with a sibling and pull a key down from the parent — the parent loses a key and may underflow in turn. Repeat upward. If the root becomes empty, delete it and the tree shrinks by one level.")
                    CodeBlock("""
function delete(tree, key):
    node = findNode(tree.root, key)
    if node is null: return   // not found

    if node is not a leaf:
        // Replace with in-order predecessor (rightmost key in left subtree)
        pred = rightmost(node.leftChild)
        node.key = pred.key
        node = pred   // now delete pred from its leaf

    // node is now a leaf containing key — remove it
    removeKeyFromLeaf(node, key)

    if node still has at least one key: return   // was a 3-node, now 2-node

    // node is now empty — fix underflow
    fixUnderflow(node, tree)

function fixUnderflow(node, tree):
    if node is root: shrink tree; return

    sibling = getSibling(node)
    parent  = node.parent

    if sibling is a 3-node:
        // Rotate: borrow one key via parent
        rotate key from parent down into node
        move sibling's adjacent key up into parent
        return

    // Merge: sibling is a 2-node
    merge node + sibling + one parent key into a single 3-node
    parent loses a key

    if parent now underflows:
        fixUnderflow(parent, tree)   // propagate upward

// Complexity summary:
//   lookup    O(log n)
//   insert    O(log n)   — at most O(log n) splits
//   delete    O(log n)   — at most O(log n) merges/rotations
//   space     O(n)
//
// 2-3 trees are the conceptual foundation of B-trees (used in databases)
// and Red-Black trees (used in std::map, std::set).
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
