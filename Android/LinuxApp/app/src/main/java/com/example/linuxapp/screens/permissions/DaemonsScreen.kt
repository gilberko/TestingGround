package com.example.linuxapp.screens.permissions

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
fun DaemonsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Daemons",
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
                SectionCard(title = "What is a Daemon?") {
                    BodyText(
                        "A daemon is a long-running background process that has no controlling terminal " +
                        "(no TTY). It typically starts at boot, runs silently in the background, and " +
                        "provides a service. Names commonly end in 'd' as a convention."
                    )
                    BodyText("Well-known daemons:")
                    CodeBlock(
                        "sshd          — SSH server (remote login)\n" +
                        "crond         — cron job scheduler\n" +
                        "rsyslogd      — system log collector\n" +
                        "NetworkManager— network connection management\n" +
                        "httpd / nginx — web servers\n" +
                        "dockerd       — container runtime\n" +
                        "udevd         — device event handler\n" +
                        "dbus-daemon   — inter-process message bus\n" +
                        "bluetoothd    — Bluetooth management"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Are They Regular Programs?") {
                    BodyText(
                        "Yes. A daemon is compiled exactly like any other program. It uses the same " +
                        "syscalls, links against the same libraries, and runs in user space. " +
                        "There is no special kernel type for 'daemon'."
                    )
                    BodyText(
                        "What makes a daemon different is behavior, not privilege:"
                    )
                    CodeBlock(
                        "1. No controlling terminal (no stdin/stdout/stderr to a TTY)\n" +
                        "2. Session leader in its own session (setsid)\n" +
                        "3. Runs continuously until stopped\n" +
                        "4. Started and supervised by an init system (systemd)\n" +
                        "5. Ignores SIGHUP by default (terminal hangup has no meaning)"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "User Context — Who Does the Daemon Run As?") {
                    BodyText(
                        "Each daemon runs under a specific Unix user account. The choices:"
                    )
                    CodeBlock(
                        "root          — needs low-level access (sshd for port 22,\n" +
                        "               udevd, systemd itself)\n\n" +
                        "Dedicated user — most modern daemons drop to a service\n" +
                        "               account with minimal privileges:\n" +
                        "               www-data  (nginx, apache)\n" +
                        "               messagebus (dbus)\n" +
                        "               nobody    (minimal, often stateless)\n" +
                        "               postgres  (PostgreSQL)\n\n" +
                        "sshd pattern  — master process runs as root to bind port 22\n" +
                        "               and read host keys; each child session drops to\n" +
                        "               the authenticated user's UID/GID"
                    )
                    BodyText(
                        "In a systemd unit file, the User= and Group= directives set the account. " +
                        "DynamicUser=yes generates a temporary private user for the duration of the run."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Who Starts and Stops Them?") {
                    BodyText(
                        "On modern Linux, systemd (PID 1) owns daemon lifecycle:"
                    )
                    CodeBlock(
                        "systemctl start   my-service   # start now\n" +
                        "systemctl stop    my-service   # send SIGTERM, then SIGKILL\n" +
                        "systemctl restart my-service   # stop + start\n" +
                        "systemctl reload  my-service   # send SIGHUP (reload config)\n" +
                        "systemctl status  my-service   # show state + last log lines\n" +
                        "systemctl enable  my-service   # auto-start at boot\n" +
                        "systemctl disable my-service   # remove auto-start"
                    )
                    BodyText(
                        "Daemons should handle signals gracefully:"
                    )
                    CodeBlock(
                        "SIGTERM  — graceful shutdown (flush state, close FDs)\n" +
                        "SIGHUP   — reload configuration without stopping\n" +
                        "SIGKILL  — force kill (sent by systemd after timeout)"
                    )
                    BodyText(
                        "Legacy systems used SysV init scripts in /etc/init.d/ with " +
                        "start/stop/restart actions, but systemd has replaced this on all major distros."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Writing a Daemon — Traditional Way") {
                    BodyText(
                        "Before systemd, daemons had to detach from the terminal themselves. " +
                        "The classic double-fork technique:"
                    )
                    CodeBlock(
                        "int main() {\n" +
                        "    // 1. Fork — parent exits so the shell gets control back\n" +
                        "    if (fork() != 0) exit(0);\n\n" +
                        "    // 2. New session — detach from controlling terminal\n" +
                        "    setsid();\n\n" +
                        "    // 3. Fork again — prevent reacquiring a TTY\n" +
                        "    if (fork() != 0) exit(0);\n\n" +
                        "    // 4. Change to root dir (don't block unmounting)\n" +
                        "    chdir(\"/\");\n\n" +
                        "    // 5. Reset umask\n" +
                        "    umask(0);\n\n" +
                        "    // 6. Close all inherited file descriptors\n" +
                        "    for (int fd = sysconf(_SC_OPEN_MAX); fd >= 0; fd--)\n" +
                        "        close(fd);\n\n" +
                        "    // 7. Redirect stdin/stdout/stderr to /dev/null\n" +
                        "    int null_fd = open(\"/dev/null\", O_RDWR);\n" +
                        "    dup2(null_fd, STDIN_FILENO);\n" +
                        "    dup2(null_fd, STDOUT_FILENO);\n" +
                        "    dup2(null_fd, STDERR_FILENO);\n\n" +
                        "    // 8. Set up signal handlers\n" +
                        "    signal(SIGTERM, handle_shutdown);\n" +
                        "    signal(SIGHUP,  handle_reload);\n\n" +
                        "    // 9. Main loop\n" +
                        "    while (running) { do_work(); }\n" +
                        "    return 0;\n" +
                        "}"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Writing a Daemon — Modern systemd Way") {
                    BodyText(
                        "With systemd, you don't need any of the fork/setsid ceremony. Just write " +
                        "a normal program with a main loop. systemd captures stdout/stderr into the " +
                        "journal automatically. The unit file handles everything."
                    )
                    CodeBlock(
                        "// my_daemon.c — as simple as this:\n" +
                        "#include <signal.h>\n" +
                        "#include <stdio.h>\n" +
                        "#include <unistd.h>\n\n" +
                        "static volatile int running = 1;\n\n" +
                        "static void on_sigterm(int sig) { running = 0; }\n\n" +
                        "int main() {\n" +
                        "    signal(SIGTERM, on_sigterm);\n" +
                        "    signal(SIGHUP,  SIG_IGN);  // or implement reload\n\n" +
                        "    while (running) {\n" +
                        "        printf(\"daemon tick\\n\");\n" +
                        "        sleep(5);\n" +
                        "    }\n" +
                        "    printf(\"shutting down cleanly\\n\");\n" +
                        "    return 0;\n" +
                        "}"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "systemd Unit File") {
                    BodyText("Create /etc/systemd/system/my-daemon.service:")
                    CodeBlock(
                        "[Unit]\n" +
                        "Description=My Example Daemon\n" +
                        "After=network.target\n\n" +
                        "[Service]\n" +
                        "Type=simple\n" +
                        "ExecStart=/usr/local/bin/my_daemon\n" +
                        "User=myuser\n" +
                        "Group=mygroup\n" +
                        "WorkingDirectory=/var/lib/my-daemon\n" +
                        "Restart=on-failure\n" +
                        "RestartSec=5\n" +
                        "StandardOutput=journal\n" +
                        "StandardError=journal\n\n" +
                        "[Install]\n" +
                        "WantedBy=multi-user.target"
                    )
                    BodyText("Key Type= values:")
                    CodeBlock(
                        "Type=simple   — process in ExecStart IS the daemon\n" +
                        "               (use this for modern daemons)\n" +
                        "Type=forking  — legacy: ExecStart forks; parent exits\n" +
                        "Type=notify   — daemon calls sd_notify(READY=1)\n" +
                        "               systemd waits for the ready signal"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item {
                SectionCard(title = "Installing Your Daemon") {
                    CodeBlock(
                        "# 1. Copy the binary\n" +
                        "sudo cp my_daemon /usr/local/bin/\n" +
                        "sudo chmod 755 /usr/local/bin/my_daemon\n\n" +
                        "# 2. Install the unit file\n" +
                        "sudo cp my-daemon.service /etc/systemd/system/\n\n" +
                        "# 3. Reload systemd so it sees the new unit\n" +
                        "sudo systemctl daemon-reload\n\n" +
                        "# 4. Enable auto-start at boot\n" +
                        "sudo systemctl enable my-daemon\n" +
                        "# (creates a symlink in /etc/systemd/system/multi-user.target.wants/)\n\n" +
                        "# 5. Start it now\n" +
                        "sudo systemctl start my-daemon\n\n" +
                        "# 6. Check status\n" +
                        "systemctl status my-daemon\n\n" +
                        "# 7. Follow live logs\n" +
                        "journalctl -u my-daemon -f"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
