package com.example.linuxapp.screens.permissions.namespaces

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
fun IpcNamespaceScreen(onBack: () -> Unit) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "IPC Namespace",
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
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                SectionCard(title = "What IPC Namespace Does") {
                    BodyText("An IPC namespace isolates System V IPC objects and POSIX message queues. IPC objects created inside one namespace are completely invisible to processes in any other namespace.")
                    BodyText("Two containers can each create a SysV shared memory segment with the same numeric key — they will not interfere because they live in separate IPC namespaces.")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "System V IPC Objects") {
                    BodyText("System V IPC objects are identified by a numeric key within the calling process's IPC namespace. The three object types are:")
                    BodyText("Semaphore sets — semget() / semop() / semctl()")
                    BodyText("Message queues — msgget() / msgsnd() / msgrcv() / msgctl()")
                    BodyText("Shared memory segments — shmget() / shmat() / shmdt() / shmctl()")
                    CodeBlock("""// List all SysV IPC objects:
// ipcs -a

// Create a shared memory segment in the calling ns:
key_t key = ftok("/tmp/myapp", 1);
int shmid = shmget(key, 4096, IPC_CREAT | 0666);
void *ptr = shmat(shmid, NULL, 0);

// A process in a different IPC namespace cannot access
// this segment — shmget() with the same key will create
// a NEW, separate segment in that namespace.""")
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
                SectionCard(title = "POSIX Message Queues") {
                    BodyText("/dev/mqueue is a per-IPC-namespace virtual filesystem. Each namespace gets its own independent mqueue mount.")
                    BodyText("POSIX message queues are isolated the same way: mq_open() creates queues that only processes in the same IPC namespace can access.")
                    CodeBlock("""// POSIX message queue API:
mqd_t mq = mq_open("/myqueue", O_CREAT | O_RDWR, 0644, NULL);
mq_send(mq, "hello", 5, 0);

char buf[256];
mq_receive(mq, buf, sizeof(buf), NULL);

mq_close(mq);
mq_unlink("/myqueue"); // removes from /dev/mqueue in this ns only""")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
