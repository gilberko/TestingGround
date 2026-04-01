package com.example.linuxapp.screens.usermode

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
fun UserModeNetworkingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Networking",
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
                SectionCard(title = "Berkeley Sockets Overview") {
                    BodyText("The BSD socket API is the standard interface for network programming on Linux. A socket is a file descriptor — you can read, write, and close it like any file.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Required headers:")
                    CodeBlock(
                        """#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>   /* sockaddr_in */
#include <arpa/inet.h>    /* inet_pton, htons */
#include <unistd.h>       /* close */
#include <string.h>       /* memset */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("TCP server flow:   socket → bind → listen → accept → recv/send → close")
                    BodyText("TCP client flow:   socket → connect → send/recv → close")
                }
            }
            item {
                SectionCard(title = "Creating a Socket") {
                    CodeBlock(
                        """int sockfd = socket(domain, type, protocol);

/* domain:   AF_INET  = IPv4
             AF_INET6 = IPv6
             AF_UNIX  = local (same machine, via filesystem)

   type:     SOCK_STREAM = TCP (reliable, ordered)
             SOCK_DGRAM  = UDP (unreliable, unordered)

   protocol: 0 = auto-select (correct for TCP and UDP) */

/* TCP/IPv4: */
int sockfd = socket(AF_INET, SOCK_STREAM, 0);
if (sockfd < 0) { perror("socket"); exit(1); }"""
                    )
                }
            }
            item {
                SectionCard(title = "TCP Server") {
                    BodyText("Step 1 — bind to an address and port:")
                    CodeBlock(
                        """struct sockaddr_in addr;
memset(&addr, 0, sizeof(addr));
addr.sin_family      = AF_INET;
addr.sin_addr.s_addr = INADDR_ANY;   /* all interfaces */
addr.sin_port        = htons(8080);  /* host-to-network byte order */

/* Allow reuse of the address immediately after restart */
int opt = 1;
setsockopt(sockfd, SOL_SOCKET, SO_REUSEADDR,
           &opt, sizeof(opt));

if (bind(sockfd, (struct sockaddr *)&addr,
         sizeof(addr)) < 0) {
    perror("bind"); exit(1);
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 2 — listen for incoming connections:")
                    CodeBlock(
                        """/* backlog = max pending connections in the queue */
listen(sockfd, 10);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Step 3 — accept a connection (blocks until client connects):")
                    CodeBlock(
                        """struct sockaddr_in client_addr;
socklen_t client_len = sizeof(client_addr);

int client_fd = accept(sockfd,
                       (struct sockaddr *)&client_addr,
                       &client_len);
/* client_fd is a NEW socket for this specific client.
   sockfd remains open for more accept() calls.

   Get client's IP and port: */
char ip[INET_ADDRSTRLEN];
inet_ntop(AF_INET, &client_addr.sin_addr, ip, sizeof(ip));
printf("Client connected: %s:%d\n",
       ip, ntohs(client_addr.sin_port));"""
                    )
                }
            }
            item {
                SectionCard(title = "TCP Client") {
                    BodyText("Create a socket, fill in the server address, then connect:")
                    CodeBlock(
                        """int sockfd = socket(AF_INET, SOCK_STREAM, 0);

struct sockaddr_in server;
memset(&server, 0, sizeof(server));
server.sin_family = AF_INET;
server.sin_port   = htons(8080);

/* Convert "127.0.0.1" string to binary: */
inet_pton(AF_INET, "127.0.0.1", &server.sin_addr);

/* connect blocks until connection established (or error): */
if (connect(sockfd, (struct sockaddr *)&server,
            sizeof(server)) < 0) {
    perror("connect"); exit(1);
}
printf("Connected to server\n");"""
                    )
                }
            }
            item {
                SectionCard(title = "Sending and Receiving Data") {
                    BodyText("send() and recv() work on a connected socket:")
                    CodeBlock(
                        """/* Send data: */
const char *msg = "Hello, server!\n";
ssize_t sent = send(client_fd, msg, strlen(msg), 0);
/* flags = 0 for basic usage
   MSG_NOSIGNAL — don't raise SIGPIPE on broken connection */

/* Receive data (blocks until data available): */
char buf[1024];
ssize_t n = recv(client_fd, buf, sizeof(buf) - 1, 0);
if (n == 0) {
    printf("Connection closed by peer\n");
} else if (n < 0) {
    perror("recv");
} else {
    buf[n] = '\0';
    printf("Received: %s\n", buf);
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Important: TCP is a stream protocol — one send() may arrive as multiple recv() calls, or multiple sends may arrive in one recv(). Always loop until you have received your expected number of bytes:")
                    CodeBlock(
                        """/* Reliable receive of exactly 'total' bytes: */
ssize_t recv_all(int fd, char *buf, size_t total)
{
    size_t received = 0;
    while (received < total) {
        ssize_t n = recv(fd, buf + received,
                         total - received, 0);
        if (n <= 0) return n;  /* error or closed */
        received += n;
    }
    return (ssize_t)received;
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Close the socket when done:")
                    CodeBlock(
                        """close(client_fd);   /* close this client connection */
close(sockfd);      /* close the listening socket */"""
                    )
                }
            }
            item {
                SectionCard(title = "select() — Non-Blocking I/O on Multiple Fds") {
                    BodyText("select() monitors multiple file descriptors simultaneously, blocking until at least one is ready. This avoids blocking in recv() on a single connection.")
                    CodeBlock(
                        """#include <sys/select.h>

fd_set read_fds;
FD_ZERO(&read_fds);            /* clear the set */
FD_SET(listen_fd, &read_fds);  /* watch listening socket */
FD_SET(client_fd, &read_fds);  /* watch connected client */

int max_fd = client_fd;  /* highest fd number */

/* Timeout: block for up to 5 seconds */
struct timeval tv = { .tv_sec = 5, .tv_usec = 0 };

int ready = select(max_fd + 1,
                   &read_fds,   /* watch for readability */
                   NULL,        /* watch for writability (unused) */
                   NULL,        /* watch for exceptions (unused) */
                   &tv);

if (ready < 0) {
    perror("select");
} else if (ready == 0) {
    printf("Timeout — no events in 5 seconds\n");
} else {
    if (FD_ISSET(listen_fd, &read_fds)) {
        /* New client waiting — safe to call accept() */
        int new_fd = accept(listen_fd, NULL, NULL);
    }
    if (FD_ISSET(client_fd, &read_fds)) {
        /* Data available — safe to call recv() without blocking */
        char buf[256];
        ssize_t n = recv(client_fd, buf, sizeof(buf), 0);
    }
}"""
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Note: select() rebuilds fd_sets on each call — put FD_ZERO/FD_SET inside your event loop.")
                }
            }
            item {
                SectionCard(title = "poll() and epoll()") {
                    BodyText("select() has two limitations: fd_set has a size limit (typically 1024), and it scans all fds on every call. For high-performance servers use poll() or epoll().")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("poll() — no fd limit, cleaner API:")
                    CodeBlock(
                        """#include <poll.h>

struct pollfd fds[2];
fds[0].fd      = listen_fd;
fds[0].events  = POLLIN;   /* notify on readable */
fds[1].fd      = client_fd;
fds[1].events  = POLLIN;

int ready = poll(fds, 2, 5000);  /* timeout in ms */

if (fds[0].revents & POLLIN) { /* accept() */ }
if (fds[1].revents & POLLIN) { /* recv()   */ }"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("epoll() — O(1) event notification, scales to hundreds of thousands of connections:")
                    CodeBlock(
                        """#include <sys/epoll.h>

int epfd = epoll_create1(0);

struct epoll_event ev;
ev.events  = EPOLLIN;
ev.data.fd = listen_fd;
epoll_ctl(epfd, EPOLL_CTL_ADD, listen_fd, &ev);

/* Event loop: */
struct epoll_event events[64];
while (1) {
    int n = epoll_wait(epfd, events, 64, -1); /* -1 = no timeout */
    for (int i = 0; i < n; i++) {
        if (events[i].data.fd == listen_fd) {
            int new_fd = accept(listen_fd, NULL, NULL);
            ev.events  = EPOLLIN | EPOLLET; /* edge-triggered */
            ev.data.fd = new_fd;
            epoll_ctl(epfd, EPOLL_CTL_ADD, new_fd, &ev);
        } else {
            /* recv on events[i].data.fd */
        }
    }
}
close(epfd);"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
