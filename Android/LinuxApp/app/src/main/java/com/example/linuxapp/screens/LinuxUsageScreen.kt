package com.example.linuxapp.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.linuxapp.screens.kernel.BodyText
import com.example.linuxapp.screens.kernel.CodeBlock
import com.example.linuxapp.screens.kernel.SectionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinuxUsageScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Linux Usage",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                SectionCard(title = "Navigation & Listing") {
                    BodyText("pwd — print the current working directory:")
                    CodeBlock("$ pwd\n/home/alice/projects")
                    BodyText("cd — change directory:")
                    CodeBlock(
                        """$ cd /etc           # go to absolute path
$ cd projects       # go to relative path
$ cd ..             # go up one level
$ cd ~              # go to your home directory
$ cd -              # go back to previous directory"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("ls — list directory contents:")
                    CodeBlock(
                        """$ ls                # basic listing
$ ls -l             # long format: permissions, owner, size, date
$ ls -a             # show hidden files (starting with .)
$ ls -la            # long format + hidden files
$ ls -lh            # long format with human-readable sizes (KB, MB)
$ ls -lt            # sort by modification time, newest first"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Long format columns: permissions | links | owner | group | size | date | name")
                    CodeBlock("-rwxr-xr-x 1 alice dev 4096 Mar 10 14:22 script.sh")
                }
            }
            item {
                SectionCard(title = "Viewing Files") {
                    BodyText("cat — print file contents to the terminal:")
                    CodeBlock(
                        """$ cat file.txt          # print entire file
$ cat -n file.txt       # print with line numbers"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("head and tail — view the beginning or end of a file:")
                    CodeBlock(
                        """$ head file.txt         # first 10 lines (default)
$ head -n 20 file.txt   # first 20 lines
$ tail file.txt         # last 10 lines (default)
$ tail -n 20 file.txt   # last 20 lines
$ tail -f /var/log/syslog   # follow: print new lines as they are added
                            # (great for watching live logs, Ctrl+C to stop)"""
                    )
                }
            }
            item {
                SectionCard(title = "Copying & Moving") {
                    BodyText("cp — copy files or directories:")
                    CodeBlock(
                        """$ cp src.txt dst.txt        # copy file
$ cp src.txt /tmp/          # copy file into a directory
$ cp -r src_dir/ dst_dir/   # copy directory recursively
$ cp -p src.txt dst.txt     # preserve timestamps and permissions"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("mv — move (or rename) files and directories:")
                    CodeBlock(
                        """$ mv old.txt new.txt        # rename a file
$ mv file.txt /tmp/         # move file to another directory
$ mv dir1/ dir2/            # rename or move a directory"""
                    )
                }
            }
            item {
                SectionCard(title = "Deleting") {
                    BodyText("rm — remove files or directories:")
                    CodeBlock(
                        """$ rm file.txt           # delete a file
$ rm -r dir/            # delete a directory and all its contents
$ rm -i file.txt        # prompt before each deletion
$ rm -rf dir/           # force-delete recursively, no prompts"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Warning: rm -rf is permanent and irreversible. There is no Recycle Bin on Linux. Double-check the path before running it.")
                }
            }
            item {
                SectionCard(title = "echo") {
                    BodyText("echo — print text or variable values:")
                    CodeBlock(
                        """$ echo "Hello, world!"          # print a string
$ echo ${'$'}HOME                    # print a variable's value
$ echo ${'$'}PATH                    # print the executable search path

# Redirect output to a file:
$ echo "line one" > file.txt    # overwrite (creates file if needed)
$ echo "line two" >> file.txt   # append (does not overwrite)"""
                    )
                }
            }
            item {
                SectionCard(title = "grep — Search Text") {
                    BodyText("grep searches for a pattern inside files and prints matching lines:")
                    CodeBlock(
                        """$ grep "error" log.txt          # lines containing "error"
$ grep -i "error" log.txt       # case-insensitive search
$ grep -n "error" log.txt       # show line numbers
$ grep -v "error" log.txt       # invert: lines NOT containing "error"
$ grep -r "TODO" src/           # search recursively in a directory
$ grep -l "error" *.log         # print only filenames that match"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("grep supports regular expressions by default. Use grep -E or egrep for extended regex (|, +, ?, groups):")
                    CodeBlock("""$ grep -E "error|warning" log.txt   # match either word""")
                }
            }
            item {
                SectionCard(title = "Pipes  |  — Chaining Commands") {
                    BodyText("The pipe character | sends the output of one command as the input to the next. This lets you compose small tools into powerful pipelines:")
                    CodeBlock(
                        """# Find lines with "error" in a log file:
$ cat syslog | grep "error"

# Search for a running process by name:
$ ps aux | grep nginx

# List only .txt files in long format:
$ ls -la | grep ".txt"

# Count how many lines contain "warning":
$ grep -i "warning" log.txt | wc -l

# View a long file one screen at a time:
$ cat bigfile.txt | less

# Chain multiple pipes — output flows left to right:
$ cat access.log | grep "404" | grep -v "bot" | wc -l"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Each command in the pipeline runs in its own process. The kernel connects stdout of one to stdin of the next via an in-memory pipe buffer.")
                }
            }
            item {
                SectionCard(title = "Users & Permissions") {
                    BodyText("sudo — run a single command as root (superuser). You stay as your own user for everything else:")
                    CodeBlock(
                        """$ sudo apt update           # run as root
$ sudo nano /etc/hosts      # edit a root-owned file
$ sudo -i                   # open a root shell (use carefully)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("su — switch user. Without an argument, switches to root:")
                    CodeBlock(
                        """$ su                  # become root (prompts for root password)
$ su -                # become root with root's environment
$ su alice            # switch to user 'alice'
$ exit                # return to the previous user"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("chmod — change file permissions:")
                    CodeBlock(
                        """$ chmod +x script.sh        # make a file executable
$ chmod -x script.sh        # remove execute permission
$ chmod 755 script.sh       # rwxr-xr-x (owner: rwx, group+others: r-x)
$ chmod 644 file.txt        # rw-r--r-- (owner: rw, group+others: r)
$ chmod -R 755 dir/         # apply recursively to a directory"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Octal digits: 4=read, 2=write, 1=execute. Each digit = owner / group / others.")
                }
            }
            item {
                SectionCard(title = "Processes: ps & kill") {
                    BodyText("ps — list running processes:")
                    CodeBlock(
                        """$ ps               # your processes in the current shell
$ ps aux           # ALL processes on the system (most common)
$ ps aux | grep nginx   # find a process by name"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("ps aux column breakdown:")
                    CodeBlock(
                        """USER   PID  %CPU %MEM    VSZ   RSS TTY  STAT  COMMAND
alice  1234  0.0  0.1  12345  4567 pts/0  S+   nginx: worker

USER    — who owns the process
PID     — process ID
%CPU    — CPU usage percentage
%MEM    — memory usage percentage
VSZ     — virtual memory size (KB)
RSS     — resident set size — actual RAM used (KB)
STAT    — process state: S=sleeping, R=running, Z=zombie, D=uninterruptible
COMMAND — the command that started the process"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("kill — send a signal to a process by PID:")
                    CodeBlock(
                        """$ kill 1234          # send SIGTERM (polite request to stop)
$ kill -9 1234       # send SIGKILL (cannot be ignored — force kill)
$ kill -15 1234      # send SIGTERM explicitly (same as plain kill)
$ killall nginx      # kill all processes named "nginx" by name"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("SIGTERM (15) asks the process to clean up and exit. SIGKILL (9) forces termination immediately — the kernel removes the process without giving it a chance to clean up.")
                }
            }
            item {
                SectionCard(title = "Background Execution  &") {
                    BodyText("Appending & to a command runs it in the background. The shell immediately returns the prompt so you can continue working:")
                    CodeBlock(
                        """$ sleep 60 &         # run in background; shell returns immediately
[1] 4521             # [job number] PID

$ make -j8 &         # compile in background"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Managing background jobs:")
                    CodeBlock(
                        """$ jobs               # list all background jobs in this shell
[1]+  Running    sleep 60 &

$ fg %1              # bring job 1 to the foreground
$ bg %1              # resume a stopped job in the background
$ kill %1            # kill job 1 by job number"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Background jobs still write to your terminal by default. Redirect to silence them:")
                    CodeBlock(
                        """$ ./server > server.log 2>&1 &
#  stdout goes to server.log
#  2>&1 means: send stderr to the same place as stdout
#  & runs the whole thing in the background"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Note: background jobs are tied to the shell session. They are killed when you close the terminal unless you use nohup or disown.")
                }
            }
            item {
                SectionCard(title = "Other Useful Commands") {
                    CodeBlock(
                        """$ mkdir dirname        # create a new directory
$ mkdir -p a/b/c       # create nested directories in one step

$ man ls               # open the manual page for any command
                       # (press q to quit, / to search)

$ which gcc            # show the full path of an executable
$ history              # list recently run commands
$ clear                # clear the terminal screen

$ wc -l file.txt       # count lines in a file
$ wc -w file.txt       # count words

$ find . -name "*.c"   # find all .c files under current directory
$ find /var -name "*.log" -mtime +7   # logs older than 7 days"""
                    )
                }
            }
            item {
                SectionCard(title = "Text Editor: vi / vim") {
                    BodyText("vi (and its improved version vim) is available on virtually every Linux system. It is a modal editor — the same keys do different things depending on the current mode.")
                    CodeBlock(
                        """$ vi filename          # open or create a file
$ vim filename         # open with vim (more features)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The three main modes:")
                    CodeBlock(
                        """Normal mode   — default on open; keys are commands, not text
Insert mode   — type text; enter with: i (before cursor),
                a (after cursor), o (new line below)
Command mode  — enter with: :  (from Normal mode)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Essential commands (all entered from Normal mode unless noted):")
                    CodeBlock(
                        """i          enter Insert mode before cursor
Esc        return to Normal mode from any mode

:w         save (write) the file
:q         quit (fails if unsaved changes exist)
:wq        save and quit
:q!        quit without saving (discard changes)

h j k l    move cursor: left / down / up / right
gg         go to the first line
G          go to the last line
/pattern   search forward for a pattern (n = next match)

dd         delete (cut) the current line
yy         yank (copy) the current line
p          paste after the cursor
u          undo last change
Ctrl+r     redo"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Quick tip: if you accidentally open vi and cannot figure out how to exit, press Esc then type :q! and press Enter.")
                }
            }
            item {
                SectionCard(title = "Text Editor: nano") {
                    BodyText("nano is a simpler, beginner-friendly editor. Unlike vi, it always shows a shortcut bar at the bottom of the screen so you never need to memorise commands.")
                    CodeBlock("$ nano filename        # open or create a file")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The ^ symbol means Ctrl. Common shortcuts:")
                    CodeBlock(
                        """Ctrl+O    save (Write Out) — prompts for filename, then Enter
Ctrl+X    exit (asks to save if there are unsaved changes)
Ctrl+W    search (Where is) — type pattern, Enter to find
Ctrl+K    cut the current line
Ctrl+U    paste (uncut) the line
Ctrl+G    show help
Ctrl+/    go to a specific line number"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("nano is not always installed by default on minimal systems. On Debian/Ubuntu: sudo apt install nano")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
