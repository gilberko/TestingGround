package com.example.linuxapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShellScriptingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Linux Shell Scripting",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF00FF41))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp, end = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = innerPadding.calculateBottomPadding() + 8.dp
            )
        ) {

            // 1. What is Bash Scripting
            item {
                SectionCard(title = "What is Bash Scripting") {
                    BodyText("A bash script is a text file containing shell commands that bash executes in sequence. Bash (Bourne Again SHell) is the default shell on most Linux distros.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Scripts are run directly in the bash shell — you can test any line interactively before putting it in a script. The .sh extension is conventional but not required.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Two ways to run a script:")
                    CodeBlock(
                        """bash script.sh          # run with bash explicitly
./script.sh             # run directly (needs execute permission)"""
                    )
                }
                Spacer(Modifier.height(8.dp))
            }

            // 2. Script Structure
            item {
                SectionCard(title = "Script Structure") {
                    BodyText("The first line should be the shebang: #! followed by the path to the interpreter. This tells the OS which program to use to run the file.")
                    Spacer(Modifier.height(8.dp))
                    BodyText("Make the script executable with chmod, then run it directly:")
                    CodeBlock(
                        """chmod +x script.sh
./script.sh"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Minimal script example:")
                    CodeBlock(
                        """#!/bin/bash
echo "Hello, Linux!"
echo "Script is running" """
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("The shebang (#!) must be the very first line with no leading spaces. Without it, the script may run in the wrong shell or fail entirely.")
                }
                Spacer(Modifier.height(8.dp))
            }

            // 3. Comments
            item {
                SectionCard(title = "Comments") {
                    BodyText("# starts a comment — bash ignores everything from # to the end of the line. There is no multi-line comment syntax; use multiple # lines.")
                    CodeBlock(
                        """#!/bin/bash
# This is a full-line comment
echo "Running..."   # inline comment after a command

# You can comment out a command to disable it:
# rm -rf /tmp/old_files"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("The shebang (#!) on line 1 is technically a comment to bash itself, but it has special meaning to the OS loader.")
                }
                Spacer(Modifier.height(8.dp))
            }

            // 4. Variables
            item {
                SectionCard(title = "Variables") {
                    BodyText("Assign a variable with = and no spaces on either side. Access it by prefixing with ${'$'}. Use ${'$'}{var} when the variable name is adjacent to other text.")
                    CodeBlock(
                        """name="Alice"
count=42
echo "Hello, ${'$'}name"          # Hello, Alice
echo "Count: ${'$'}{count}x"      # Count: 42x"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Always quote variables in double quotes to safely handle values with spaces:")
                    CodeBlock(
                        """file="my file.txt"
echo "${'$'}file"         # correct: my file.txt
echo ${'$'}file           # wrong: treated as two args"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Special variable modifiers:")
                    CodeBlock(
                        """readonly MAX=100      # constant — cannot be changed
unset name            # delete a variable

declare -i num=5      # integer type (arithmetic enforced)
declare -r PI=3.14    # same as readonly
declare -x VAR="val"  # export to environment (like export)"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("By default variables are untyped strings. Bash doesn't distinguish between \"5\" and 5 unless you use declare -i or arithmetic context (( )).")
                }
                Spacer(Modifier.height(8.dp))
            }

            // 5. Arrays
            item {
                SectionCard(title = "Arrays") {
                    BodyText("Indexed arrays (0-based). Elements are space-separated inside parentheses:")
                    CodeBlock(
                        """fruits=("apple" "banana" "cherry")

echo "${'$'}{fruits[0]}"      # apple  (single element)
echo "${'$'}{fruits[@]}"      # apple banana cherry  (all elements)
echo "${'$'}{#fruits[@]}"     # 3  (length)

fruits+=("date")          # append element
fruits[1]="blueberry"     # overwrite element

# Iterate over all elements
for fruit in "${'$'}{fruits[@]}"; do
    echo "${'$'}fruit"
done"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Associative arrays (key-value, bash 4+). Must be declared with declare -A:")
                    CodeBlock(
                        """declare -A capitals
capitals["France"]="Paris"
capitals["Japan"]="Tokyo"

echo "${'$'}{capitals["France"]}"    # Paris
echo "${'$'}{!capitals[@]}"          # all keys
echo "${'$'}{capitals[@]}"           # all values

for country in "${'$'}{!capitals[@]}"; do
    echo "${'$'}country => ${'$'}{capitals[${'$'}country]}"
done"""
                    )
                }
                Spacer(Modifier.height(8.dp))
            }

            // 6. Comparisons and if/else
            item {
                SectionCard(title = "Comparisons and if/else") {
                    BodyText("Use [[ ]] for conditions — it's safer than [ ] and supports && and || inside without quoting issues. The structure is: if / elif / else / fi.")
                    CodeBlock(
                        """age=20
if [[ ${'$'}age -ge 18 ]]; then
    echo "Adult"
elif [[ ${'$'}age -ge 13 ]]; then
    echo "Teen"
else
    echo "Child"
fi"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Integer comparison operators:")
                    CodeBlock(
                        """-eq   equal               (==)
-ne   not equal            (!=)
-lt   less than            (<)
-le   less than or equal   (<=)
-gt   greater than         (>)
-ge   greater than or equal (>=)"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("String comparison operators:")
                    CodeBlock(
                        """[[ ${'$'}a == ${'$'}b ]]    # equal
[[ ${'$'}a != ${'$'}b ]]    # not equal
[[ -z ${'$'}a ]]       # true if empty string
[[ -n ${'$'}a ]]       # true if non-empty string

name="bash"
if [[ ${'$'}name == "bash" ]]; then
    echo "It's bash!"
fi"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("File test operators:")
                    CodeBlock(
                        """-f file    # file exists and is a regular file
-d file    # file exists and is a directory
-e file    # file or directory exists
-r file    # file is readable
-w file    # file is writable
-x file    # file is executable

if [[ -f "/etc/passwd" ]]; then
    echo "File exists"
fi"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Combine conditions with && (and) and || (or):")
                    CodeBlock(
                        """if [[ ${'$'}age -ge 18 && ${'$'}age -lt 65 ]]; then
    echo "Working age"
fi"""
                    )
                }
                Spacer(Modifier.height(8.dp))
            }

            // 7. Functions
            item {
                SectionCard(title = "Functions") {
                    BodyText("Define a function with name() { } or function name { }. Call it by name — no parentheses when calling. Arguments arrive as ${'$'}1, ${'$'}2, etc. ${'$'}@ is all arguments, ${'$'}# is the count.")
                    CodeBlock(
                        """greet() {
    echo "Hello, ${'$'}1!"
}
greet "World"     # Hello, World!
greet "Linux"     # Hello, Linux!"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Return values — return only sends an integer exit code (0–255), not a string. To \"return\" a string, echo it and capture with \$():")
                    CodeBlock(
                        """# Return a string via echo
get_greeting() {
    local name="${'$'}1"
    echo "Hello, ${'$'}name!"    # this is the "return value"
}
msg=${'$'}(get_greeting "Alice")
echo "${'$'}msg"                 # Hello, Alice!

# Return an integer status code
is_even() {
    local n=${'$'}1
    return ${'$'}(( n % 2 ))     # 0 = even (success), 1 = odd
}
is_even 4 && echo "Even" || echo "Odd"
is_even 7 && echo "Even" || echo "Odd"   # Odd"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Variable scope — all variables are global by default. Use local inside a function to restrict scope to that function:")
                    CodeBlock(
                        """x="global"

modify() {
    local x="local"       # does NOT affect the outer x
    x_global="set from function"   # modifies global (no local)
    echo "Inside: ${'$'}x"
}
modify
echo "Outside: ${'$'}x"           # global (unchanged)
echo "${'$'}x_global"             # set from function"""
                    )
                }
                Spacer(Modifier.height(8.dp))
            }

            // 8. Checking Return Status
            item {
                SectionCard(title = "Checking Return Status (${'$'}?)") {
                    BodyText("Every command exits with a status code. 0 means success; any non-zero value means failure. ${'$'}? holds the exit code of the last command — read it immediately before running another command overwrites it.")
                    CodeBlock(
                        """ls /etc/passwd
if [[ ${'$'}? -eq 0 ]]; then
    echo "Command succeeded"
else
    echo "Command failed"
fi"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Capture the status into a variable to check it later:")
                    CodeBlock(
                        """grep -q "root" /etc/passwd
status=${'$'}?
echo "grep exit code: ${'$'}status"   # 0 if found, 1 if not found

# Common exit codes:
# 0   = success
# 1   = general error
# 2   = misuse of shell command
# 127 = command not found"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Short-circuit operators — && runs next command only on success, || runs it only on failure:")
                    CodeBlock(
                        """mkdir /tmp/mydir && echo "Directory created"
rm /nonexistent 2>/dev/null || echo "Delete failed (expected)"

# Chain multiple commands — stop on first failure:
./configure && make && make install"""
                    )
                    Spacer(Modifier.height(8.dp))
                    BodyText("Use set -e at the top of a script to automatically exit if any command fails (except in if conditions and || chains):")
                    CodeBlock(
                        """#!/bin/bash
set -e          # exit on any error
set -u          # treat unset variables as errors
set -o pipefail # catch errors inside pipes

cp important.txt backup.txt   # script stops here if this fails
echo "Backup created"         # only runs if cp succeeded"""
                    )
                }
                Spacer(Modifier.height(8.dp))
            }

        }
    }
}
