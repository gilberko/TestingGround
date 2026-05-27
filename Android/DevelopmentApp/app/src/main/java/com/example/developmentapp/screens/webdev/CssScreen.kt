package com.example.developmentapp.screens.webdev

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
fun CssScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "CSS — Cascading Style Sheets",
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
                SectionCard(title = "What Is CSS?") {
                    BodyText(
                        "CSS (Cascading Style Sheets) is the language that controls the visual " +
                        "presentation of HTML documents. It separates content (HTML) from " +
                        "presentation (CSS), making both easier to maintain.\n\n" +
                        "\"Cascading\" means styles can come from multiple sources (browser defaults, " +
                        "your stylesheet, inline styles) and a defined algorithm determines which " +
                        "rule wins — called the cascade.\n\n" +
                        "CSS is standardized by the W3C. CSS3 is not a single monolithic version — " +
                        "it is a collection of independent modules (Flexbox, Grid, Animations, etc.), " +
                        "each with its own specification and browser support timeline.\n\n" +
                        "CSS does not have state or logic by itself — it declares rules. JavaScript " +
                        "can dynamically add/remove CSS classes to change styles at runtime."
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "How CSS Is Applied") {
                    BodyText(
                        "Three ways to add CSS, in increasing order of preference:\n\n" +
                        "1. Inline style attribute — highest specificity, hardest to maintain:\n" +
                        "   <p style=\"color: red; font-size: 14px\">\n\n" +
                        "2. Internal <style> tag in <head> — affects only this page.\n\n" +
                        "3. External .css file linked with <link> — best practice; one file " +
                        "can style many pages; browser caches it.\n\n" +
                        "The Cascade determines which rule wins when multiple rules target " +
                        "the same element. Priority (highest first):\n" +
                        "  !important > Inline style > ID selector > Class / pseudo-class > " +
                        "Element selector > Browser defaults\n\n" +
                        "When specificity is equal, the last rule wins (source order)."
                    )
                    CodeBlock(
                        "/* External file: styles.css */\n" +
                        "<link rel=\"stylesheet\" href=\"styles.css\">\n\n" +
                        "/* Basic rule structure */\n" +
                        "selector {\n" +
                        "    property: value;\n" +
                        "    another-property: value;\n" +
                        "}\n\n" +
                        "h1 { color: lime; font-size: 2rem; }\n" +
                        ".warning { background: red; color: white; }\n" +
                        "#hero { height: 100vh; }"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Selectors") {
                    BodyText(
                        "Selectors target which HTML elements a rule applies to:\n\n" +
                        "Element   p, h1, div, a — targets all elements of that tag\n" +
                        "Class     .btn, .card, .active — targets elements with class attr\n" +
                        "ID        #header, #main — targets the one element with that id\n" +
                        "Attribute [type=\"email\"], [disabled] — targets elements with attr\n" +
                        "Pseudo-class  :hover, :focus, :nth-child(2), :first-child,\n" +
                        "              :last-child, :checked, :not(.hidden), :root\n" +
                        "Pseudo-element  ::before, ::after, ::placeholder, ::selection\n" +
                        "Combinators:\n" +
                        "  A B   (space) — B is a descendant of A\n" +
                        "  A > B         — B is a direct child of A\n" +
                        "  A + B         — B is immediately after A (sibling)\n" +
                        "  A ~ B         — B is any sibling after A\n" +
                        "  A, B          — comma: matches A OR B"
                    )
                    CodeBlock(
                        "/* All paragraphs */\n" +
                        "p { margin: 0 0 1rem; }\n\n" +
                        "/* Elements with class 'btn' */\n" +
                        ".btn { padding: 8px 16px; border-radius: 4px; }\n\n" +
                        "/* Hover state */\n" +
                        ".btn:hover { opacity: 0.8; }\n\n" +
                        "/* First child of any parent */\n" +
                        "li:first-child { font-weight: bold; }\n\n" +
                        "/* Direct children */\n" +
                        ".nav > a { text-decoration: none; color: white; }\n\n" +
                        "/* Generated content */\n" +
                        ".required::after { content: ' *'; color: red; }\n\n" +
                        "/* Complex: input type email that has focus */\n" +
                        "input[type=\"email\"]:focus { border-color: blue; }"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Box Model") {
                    BodyText(
                        "Every HTML element is a rectangular box with four layers:\n" +
                        "  Content — the actual text/image\n" +
                        "  Padding — transparent space between content and border\n" +
                        "  Border — visible (or transparent) line around the padding\n" +
                        "  Margin — transparent space outside the border, between elements\n\n" +
                        "By default (content-box) width only applies to the content area — " +
                        "padding and border add to the total size. This is confusing.\n\n" +
                        "box-sizing: border-box — width includes padding and border. " +
                        "Almost always what you want. Apply globally:\n" +
                        "  *, *::before, *::after { box-sizing: border-box; }\n\n" +
                        "overflow: hidden | scroll | auto | visible — what happens when " +
                        "content is larger than the box."
                    )
                    CodeBlock(
                        "/* Reset to border-box everywhere */\n" +
                        "*, *::before, *::after { box-sizing: border-box; }\n\n" +
                        ".card {\n" +
                        "    width: 300px;       /* total width including padding+border */\n" +
                        "    padding: 16px;\n" +
                        "    border: 1px solid #ccc;\n" +
                        "    margin: 8px;        /* space outside the card */\n" +
                        "    overflow: hidden;   /* clip content that overflows */\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Layout — Flexbox and Grid") {
                    BodyText(
                        "Flexbox — one-dimensional layout (row OR column). Perfect for nav bars, " +
                        "button groups, centering content.\n\n" +
                        "Key flex container properties:\n" +
                        "  display: flex\n" +
                        "  flex-direction: row | column | row-reverse | column-reverse\n" +
                        "  justify-content: flex-start | center | flex-end | space-between | space-around\n" +
                        "  align-items: stretch | center | flex-start | flex-end | baseline\n" +
                        "  flex-wrap: nowrap | wrap\n" +
                        "  gap: 16px — space between flex items\n\n" +
                        "CSS Grid — two-dimensional layout (rows AND columns). Perfect for " +
                        "page layouts and card grids.\n\n" +
                        "  display: grid\n" +
                        "  grid-template-columns: repeat(3, 1fr) | 200px auto 1fr\n" +
                        "  grid-template-rows: auto | 100px 1fr\n" +
                        "  gap: 16px\n" +
                        "  grid-area: header — place items into named areas"
                    )
                    CodeBlock(
                        "/* Flexbox: center horizontally and vertically */\n" +
                        ".container {\n" +
                        "    display: flex;\n" +
                        "    justify-content: center;\n" +
                        "    align-items: center;\n" +
                        "    gap: 8px;\n" +
                        "    flex-wrap: wrap;\n" +
                        "}\n\n" +
                        "/* CSS Grid: 3-column responsive card layout */\n" +
                        ".grid {\n" +
                        "    display: grid;\n" +
                        "    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));\n" +
                        "    gap: 16px;\n" +
                        "}\n\n" +
                        "/* Grid named areas: classic page layout */\n" +
                        ".page {\n" +
                        "    display: grid;\n" +
                        "    grid-template-areas:\n" +
                        "        'header header'\n" +
                        "        'sidebar main'\n" +
                        "        'footer footer';\n" +
                        "    grid-template-columns: 200px 1fr;\n" +
                        "}\n" +
                        "header { grid-area: header; }\n" +
                        "aside  { grid-area: sidebar; }"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Common Properties") {
                    BodyText(
                        "Color and background:\n" +
                        "  color: #00FF41 | rgb(0,255,65) | hsl(127,100%,50%) | lime\n" +
                        "  background: #000 | url('img.png') center/cover no-repeat\n" +
                        "  opacity: 0.8 — affects element and all children\n\n" +
                        "Typography:\n" +
                        "  font-family: 'Roboto', Arial, sans-serif\n" +
                        "  font-size: 16px | 1rem | 1.2em\n" +
                        "  font-weight: 400 | bold | 700\n" +
                        "  line-height: 1.5\n" +
                        "  text-align: left | center | right | justify\n" +
                        "  text-decoration: none | underline\n\n" +
                        "Sizing and spacing:\n" +
                        "  width, height, max-width, min-height\n" +
                        "  padding: 8px 16px — top/bottom then left/right\n" +
                        "  margin: 0 auto — center block element horizontally\n\n" +
                        "Visual:\n" +
                        "  border: 1px solid #ccc\n" +
                        "  border-radius: 8px | 50% (circle)\n" +
                        "  box-shadow: 0 4px 8px rgba(0,0,0,0.2)\n" +
                        "  cursor: pointer\n" +
                        "  display: block | inline | inline-block | flex | grid | none\n" +
                        "  visibility: hidden (hidden but still takes space) vs display:none"
                    )
                    CodeBlock(
                        ".button {\n" +
                        "    display: inline-block;\n" +
                        "    padding: 10px 24px;\n" +
                        "    background: #00FF41;\n" +
                        "    color: #000;\n" +
                        "    font-weight: 700;\n" +
                        "    border: none;\n" +
                        "    border-radius: 4px;\n" +
                        "    cursor: pointer;\n" +
                        "    transition: opacity 0.2s;\n" +
                        "}\n" +
                        ".button:hover { opacity: 0.8; }"
                    )
                }
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                SectionCard(title = "Animations and Responsive Design") {
                    BodyText(
                        "transition — smooth change when a property changes (e.g. on hover):\n" +
                        "  transition: property duration timing-function delay\n" +
                        "  transition: all 0.3s ease;\n\n" +
                        "@keyframes + animation — define a sequence of frames:\n" +
                        "  animation: name duration timing iteration direction\n\n" +
                        "@media queries — apply different styles at different viewport widths:\n" +
                        "  @media (max-width: 768px) { ... }  — phone\n" +
                        "  @media (min-width: 1200px) { ... } — large screen\n\n" +
                        "Responsive units:\n" +
                        "  px — fixed pixels\n" +
                        "  % — relative to parent\n" +
                        "  em — relative to element's font-size\n" +
                        "  rem — relative to root font-size (html element); most predictable\n" +
                        "  vh / vw — percentage of viewport height / width\n\n" +
                        "Mobile-first: write base styles for small screens, then use " +
                        "min-width media queries to add complexity for larger screens."
                    )
                    CodeBlock(
                        "/* Fade in animation */\n" +
                        "@keyframes fadeIn {\n" +
                        "    from { opacity: 0; transform: translateY(-10px); }\n" +
                        "    to   { opacity: 1; transform: translateY(0); }\n" +
                        "}\n\n" +
                        ".modal {\n" +
                        "    animation: fadeIn 0.3s ease forwards;\n" +
                        "}\n\n" +
                        "/* Responsive: single column on mobile, 3 on desktop */\n" +
                        ".grid {\n" +
                        "    display: grid;\n" +
                        "    grid-template-columns: 1fr; /* mobile: 1 column */\n" +
                        "    gap: 16px;\n" +
                        "}\n\n" +
                        "@media (min-width: 768px) {\n" +
                        "    .grid {\n" +
                        "        grid-template-columns: repeat(3, 1fr);\n" +
                        "    }\n" +
                        "}"
                    )
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
