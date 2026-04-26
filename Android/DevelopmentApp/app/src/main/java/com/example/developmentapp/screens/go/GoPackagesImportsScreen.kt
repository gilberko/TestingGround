package com.example.developmentapp.screens.go

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
fun GoPackagesImportsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text       = "Go — Packages and Imports",
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

            item {
                SectionCard(title = "What Is a Package") {
                    BodyText("Every .go file starts with a package declaration. A package is a named collection of .go files compiled together as a unit — it is Go's primary way of organizing code.")
                    BodyText("package main is special: it marks the entry point of an executable. It must contain a func main(). All other packages are library packages meant to be imported by other code.")
                    BodyText("The package name is the short identifier used in code when you reference things from that package — for example fmt, os, or sync.")
                    CodeBlock("""
// hello.go
package main          // executable entry point

import "fmt"

func main() {
    fmt.Println("Hello, Go!")
}
                    """.trimIndent())
                }
            }

            item {
                SectionCard(title = "Multiple Files in the Same Package") {
                    BodyText("You can split a package across as many .go files as you like. All files in the same directory must declare the same package name — the Go compiler enforces this.")
                    BodyText("Go compiles the entire directory as a single package. Splitting code into separate files is purely for readability; there is no concept of 'including' one file from another.")
                    CodeBlock("""
// user.go
package user

type User struct { Name string }

// user_validation.go
package user          // same package — same directory

func (u User) IsValid() bool {
    return u.Name != ""
}
                    """.trimIndent())
                }
            }

            item {
                SectionCard(title = "Exported vs Unexported Identifiers") {
                    BodyText("Go uses a single rule for visibility: an identifier is exported (visible outside the package) if and only if it starts with a capital letter.")
                    BodyText("Exported: MyFunc, MyType, MyConst, MyField\nUnexported: helper, internalState, count")
                    BodyText("This applies to functions, types, variables, constants, struct fields, and interface methods. There are no public/private/protected keywords.")
                    CodeBlock("""
package mathutil

// Exported — usable by any importer
func Add(a, b int) int { return a + b }

// Unexported — only usable inside this package
func clamp(v, lo, hi int) int {
    if v < lo { return lo }
    if v > hi { return hi }
    return v
}
                    """.trimIndent())
                    BodyText("Callers outside the package can call mathutil.Add() but cannot call mathutil.clamp() — the compiler will reject it.")
                }
            }

            item {
                SectionCard(title = "The import Directive") {
                    BodyText("Use import to bring another package into scope. The idiomatic style is a grouped import block.")
                    CodeBlock("""
import (
    "fmt"
    "os"
    "strings"
)
                    """.trimIndent())
                    BodyText("Alias import — rename the package locally:")
                    CodeBlock("""
import f "fmt"
f.Println("aliased")
                    """.trimIndent())
                    BodyText("Blank import — import for side effects only (e.g. registering a driver). The package's init() runs but no names are brought into scope:")
                    CodeBlock("""
import _ "image/png"   // registers PNG decoder
                    """.trimIndent())
                    BodyText("Dot import — brings all exported names into the current file's scope directly. This is rarely used and not recommended because it makes it unclear where names come from.")
                    CodeBlock("""
import . "fmt"
Println("no fmt. prefix needed") // works but confusing
                    """.trimIndent())
                    BodyText("Important: Go will not compile if you import a package and do not use at least one name from it. Unused imports are a compile error.")
                }
            }

            item {
                SectionCard(title = "Import Paths and Module Structure") {
                    BodyText("go.mod at the root of your project declares the module path — the base of all import paths in your project.")
                    CodeBlock("""
// go.mod
module github.com/alice/myapp

go 1.22
                    """.trimIndent())
                    BodyText("The import path of a package is: module path + directory path relative to the module root.")
                    CodeBlock("""
myapp/
  go.mod                    (module github.com/alice/myapp)
  main.go                   (package main)
  pkg/
    mathutil/
      calc.go               (package mathutil)
    stringutil/
      trim.go               (package stringutil)
                    """.trimIndent())
                    BodyText("In main.go, import the nested packages using their full path:")
                    CodeBlock("""
import (
    "github.com/alice/myapp/pkg/mathutil"
    "github.com/alice/myapp/pkg/stringutil"
)

func main() {
    sum := mathutil.Add(3, 4)
    s   := stringutil.Trim("  hello  ")
}
                    """.trimIndent())
                }
            }

            item {
                SectionCard(title = "Package Name vs Directory Name") {
                    BodyText("There is no language requirement that the package name matches the directory name — but the convention is very strong and Go tools expect it.")
                    BodyText("The import path is always based on the directory path. The package name is whatever is declared in the .go files. If they differ, callers must use the declared package name in their code, not the directory name.")
                    CodeBlock("""
// Directory: pkg/networking/
// File: conn.go
package net2              // name differs from directory "networking"

func Connect() {}
                    """.trimIndent())
                    CodeBlock("""
// Caller
import "github.com/alice/myapp/pkg/networking"

// Must use the declared package name "net2", not "networking"
net2.Connect()
                    """.trimIndent())
                    BodyText("Avoid this pattern — it confuses both humans and tools. Keep package names equal to their directory name. The one common exception is package main, which always lives in whatever directory holds your executable entry point.")
                }
            }

            item {
                SectionCard(title = "Building a Multi-Package Project") {
                    BodyText("The Go toolchain uses import paths to find and compile all packages automatically — no Makefile or build script is needed for pure Go projects.")
                    CodeBlock("""
go build ./...      # build all packages recursively
go run .            # run the main package in the current directory
go test ./...       # run all tests recursively
go vet ./...        # run static analysis on everything
                    """.trimIndent())
                    BodyText("go.mod pins dependency versions. go get adds or updates dependencies. go mod tidy removes unused ones.")
                    BodyText("For a project with multiple executables, put each main package in its own directory under cmd/:")
                    CodeBlock("""
myapp/
  go.mod
  cmd/
    server/
      main.go       (package main)
    cli/
      main.go       (package main)
  internal/
    db/
      db.go         (package db)
                    """.trimIndent())
                    CodeBlock("""
go build ./cmd/server   # build only the server binary
go build ./cmd/cli      # build only the CLI binary
                    """.trimIndent())
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
