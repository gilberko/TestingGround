package com.example.linuxapp.screens.ebpf

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
fun EbpfCompilingScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — Compiling",
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
                SectionCard(title = "Compiler: clang") {
                    BodyText("eBPF programs must be compiled with clang from the LLVM toolchain. gcc does not support the BPF target. Minimum version is clang 10; clang 12 or later is recommended.")
                    CodeBlock("""# Ubuntu / Debian
sudo apt install clang llvm libbpf-dev bpftool \
    libelf-dev zlib1g-dev linux-headers-$(uname -r)

# Fedora / RHEL / Rocky
sudo dnf install clang llvm libbpf-devel bpftool \
    elfutils-libelf-devel kernel-devel

# Arch Linux
sudo pacman -S clang llvm libbpf linux-headers bpf

# Check version
clang --version
# clang version 14.0.6 (...)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("LLVM is both the backend that generates BPF bytecode and the source of the clang front end. Both packages are needed.")
                }
            }
            item {
                SectionCard(title = "Basic Compilation") {
                    BodyText("The core compile command turns your .c source into a BPF ELF object file (.o). The .o contains BPF bytecode (not native machine code) in named ELF sections.")
                    CodeBlock("""clang -O2 -g -target bpf -c prog.bpf.c -o prog.bpf.o""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What each flag does:")
                    CodeBlock("""-O2           Required. The BPF verifier expects code that LLVM's
              optimiser has cleaned up. Unoptimised IR often
              contains patterns the verifier cannot follow.

-g            Generates BTF (BPF Type Format) debug information
              embedded in the .o. libbpf uses this for CO-RE
              field-offset relocations at load time.

-target bpf   Emit BPF bytecode ELF, not native x86/arm code.
              Use 'bpfel' for explicit little-endian or
              'bpfeb' for big-endian targets.

-c            Compile to object file only; do not link.

prog.bpf.c    Source file. Convention: name BPF source
              files with .bpf.c to distinguish them from
              user-space source files.

prog.bpf.o    Output BPF ELF object.""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Additional useful flags:")
                    CodeBlock("""-D__TARGET_ARCH_x86          Needed for bpf_tracing.h PT_REGS macros.
                             Replace x86 with arm64/s390/etc.

-I/usr/include/x86_64-linux-gnu   Path to system BTF/libbpf headers
                                   on Debian/Ubuntu multi-arch.

-Wall -Wno-unused-value            Enable warnings; suppress common
                                   false positives from BPF macros.""")
                }
            }
            item {
                SectionCard(title = "Including Kernel Headers") {
                    BodyText("There are two approaches for getting kernel type definitions into your BPF program:")
                    CodeBlock("""// Approach 1 — Traditional (version-tied, fragile)
// Requires kernel source headers installed on the build machine.
// Breaks when the kernel version changes.
#include <linux/types.h>
#include <linux/bpf.h>
#include <linux/if_ether.h>
// ... many includes needed

// Approach 2 — Modern CO-RE (recommended)
// Single generated header contains every kernel type.
// libbpf handles portability at load time.
#include "vmlinux.h"              // all kernel types
#include <bpf/bpf_helpers.h>     // SEC(), bpf_printk(), etc.
#include <bpf/bpf_tracing.h>     // PT_REGS_*, BPF_PROG
#include <bpf/bpf_core_read.h>   // BPF_CORE_READ()""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The bpf/bpf_helpers.h and bpf/bpf_core_read.h headers come from the libbpf-dev package. They are part of the user-space libbpf API, not kernel headers.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When using vmlinux.h, do NOT also include the matching kernel headers — they will conflict. vmlinux.h redefines all the kernel types and is self-contained.")
                }
            }
            item {
                SectionCard(title = "Generating vmlinux.h") {
                    BodyText("vmlinux.h is generated from the running kernel's BTF (BPF Type Format) blob. It is specific to the kernel version it was generated from, but CO-RE makes the resulting BPF program portable across kernels.")
                    CodeBlock("""# Check that BTF is available (required):
ls /sys/kernel/btf/vmlinux
# Must print: /sys/kernel/btf/vmlinux
# If missing, your kernel lacks CONFIG_DEBUG_INFO_BTF=y

# Generate (run this once per kernel version):
bpftool btf dump file /sys/kernel/btf/vmlinux format c \
    > vmlinux.h

# Place vmlinux.h in the same directory as your .bpf.c files.
# Check the file was created:
wc -l vmlinux.h
# Typically 200,000 – 400,000 lines

# Commit vmlinux.h to version control so your build is
# reproducible on machines running different kernel versions.""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Modern Linux distributions (Ubuntu 20.04+, Fedora 31+, Debian 11+) ship kernels with BTF enabled by default. On older or custom kernels, rebuild with:")
                    CodeBlock("""CONFIG_DEBUG_INFO_BTF=y
CONFIG_DEBUG_INFO=y""")
                }
            }
            item {
                SectionCard(title = "Loading with bpftool") {
                    BodyText("bpftool can load and attach BPF programs directly from the command line, without writing a user-space loader. Useful for quick tests.")
                    CodeBlock("""# Load a BPF program and pin it to the BPF filesystem:
sudo bpftool prog load prog.bpf.o /sys/fs/bpf/my_prog \
    type xdp

# List all loaded programs:
sudo bpftool prog list
# 42: xdp  name my_xdp  ...

# Attach XDP program to a network interface:
sudo ip link set dev eth0 xdpgeneric \
    obj prog.bpf.o sec xdp
# xdpgeneric = software fallback (slower, always works)
# xdpdrv     = native driver mode (fastest)
# xdpoffload  = hardware offload (NIC must support it)

# Attach using a pinned program:
sudo bpftool net attach xdpgeneric \
    pinned /sys/fs/bpf/my_prog dev eth0

# Detach:
sudo ip link set dev eth0 xdp off

# Show attached programs:
sudo bpftool net list

# Inspect a loaded program (disassemble):
sudo bpftool prog dump xlated id 42
sudo bpftool prog dump jited  id 42""")
                }
            }
            item {
                SectionCard(title = "Generating a libbpf Skeleton") {
                    BodyText("A libbpf skeleton is a type-safe C header auto-generated from a BPF object. It wraps all the open/load/attach/destroy lifecycle calls into functions named after your specific program and maps, so the compiler catches typos.")
                    CodeBlock("""# Generate the skeleton header from the compiled .o:
bpftool gen skeleton prog.bpf.o > prog.bpf.skel.h

# The generated header contains a struct and these functions:
#   prog_bpf__open()           — parse ELF, prepare
#   prog_bpf__load()           — load into kernel, verify
#   prog_bpf__attach()         — attach to all SEC()-declared hooks
#   prog_bpf__destroy()        — detach, unload, free
#   prog_bpf__open_and_load()  — open + load in one call""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example user-space loader using the skeleton:")
                    CodeBlock("""#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include "prog.bpf.skel.h"   // generated header

static volatile int running = 1;
static void sig_handler(int s) { running = 0; }

int main(void)
{
    struct prog_bpf *skel;
    int err;

    // Open + load + verify
    skel = prog_bpf__open_and_load();
    if (!skel) { perror("open_and_load"); return 1; }

    // Attach to all SEC()-declared attach points
    err = prog_bpf__attach(skel);
    if (err) { fprintf(stderr, "attach failed\n"); goto out; }

    // Optionally read a map value directly via the skeleton:
    // skel->maps.open_counts  — typed struct bpf_map *
    // skel->bss->my_global    — access global variables

    signal(SIGINT, sig_handler);
    printf("Running... Ctrl-C to stop\n");
    while (running) sleep(1);

out:
    prog_bpf__destroy(skel);   // detach + unload + free
    return err ? 1 : 0;
}""")
                }
            }
            item {
                SectionCard(title = "User Space Loader (Without Skeleton)") {
                    BodyText("For dynamic use cases (selecting programs at runtime, multiple BPF objects), use the lower-level libbpf API directly:")
                    CodeBlock("""#include <bpf/libbpf.h>
#include <bpf/bpf.h>
#include <net/if.h>

int main(void)
{
    struct bpf_object *obj;
    struct bpf_program *prog;
    struct bpf_map *map;
    int prog_fd, map_fd, ifindex, err;

    // Open the BPF ELF object
    obj = bpf_object__open_file("prog.bpf.o", NULL);
    if (libbpf_get_error(obj)) return 1;

    // Load all programs and maps into the kernel
    err = bpf_object__load(obj);
    if (err) return 1;

    // Find a specific program by SEC() name
    prog = bpf_object__find_program_by_name(obj, "xdp_filter");
    prog_fd = bpf_program__fd(prog);

    // Find and use a map
    map = bpf_object__find_map_by_name(obj, "packet_count");
    map_fd = bpf_map__fd(map);

    // Attach XDP to interface manually
    ifindex = if_nametoindex("eth0");
    bpf_xdp_attach(ifindex, prog_fd, XDP_FLAGS_DRV_MODE, NULL);

    // ... use the program, then clean up:
    bpf_xdp_detach(ifindex, XDP_FLAGS_DRV_MODE, NULL);
    bpf_object__close(obj);
    return 0;
}

// Compile user-space loader:
// gcc loader.c -lbpf -lelf -lz -o loader""")
                }
            }
            item {
                SectionCard(title = "Full Makefile Example") {
                    BodyText("A complete Makefile for the typical two-file project: one BPF source and one user-space loader.")
                    CodeBlock("""# Variables
CLANG    ?= clang
CC       ?= gcc
BPFTOOL  ?= bpftool
ARCH     := $(shell uname -m | sed 's/x86_64/x86/' | \
                sed 's/aarch64/arm64/')

LIBBPF_INCLUDES := $(shell pkg-config --cflags libbpf 2>/dev/null \
                    || echo "-I/usr/include")
LIBBPF_LIBS     := $(shell pkg-config --libs   libbpf 2>/dev/null \
                    || echo "-lbpf")

BPF_CFLAGS := -g -O2 -target bpf \
              -D__TARGET_ARCH_$(ARCH) \
              $(LIBBPF_INCLUDES)

USER_CFLAGS := -g -O2 $(LIBBPF_INCLUDES)
USER_LIBS   := $(LIBBPF_LIBS) -lelf -lz

.PHONY: all clean

all: loader

# Step 1: generate vmlinux.h (once per kernel version)
vmlinux.h:
	$(BPFTOOL) btf dump file /sys/kernel/btf/vmlinux \
	    format c > $@

# Step 2: compile BPF C -> BPF ELF object
prog.bpf.o: prog.bpf.c vmlinux.h
	$(CLANG) $(BPF_CFLAGS) -c $< -o $@

# Step 3: generate type-safe skeleton header
prog.bpf.skel.h: prog.bpf.o
	$(BPFTOOL) gen skeleton $< > $@

# Step 4: compile the user-space loader
loader: loader.c prog.bpf.skel.h
	$(CC) $(USER_CFLAGS) $< -o $@ $(USER_LIBS)

clean:
	rm -f loader prog.bpf.o prog.bpf.skel.h vmlinux.h""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Build and run:")
                    CodeBlock("""make               # builds everything
sudo ./loader      # loads and runs the BPF program (needs root)
make clean         # removes all generated files""")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
