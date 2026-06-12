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
fun EbpfBasicCSyntaxScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "eBPF — Basic C Syntax",
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
                SectionCard(title = "File Organization") {
                    BodyText("You do not have to put everything in one file. The typical convention is one .c file per program type (xdp_drop.bpf.c, trace_exec.bpf.c, etc.), each compiled independently to its own BPF ELF object.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Shared helper code goes in header files as static inline functions. The BPF verifier requires that all called functions are either inlined or compiled as BPF-to-BPF calls. The __always_inline attribute forces inlining:")
                    CodeBlock("""// helpers.h  — shared utilities included by multiple .bpf.c files
static __always_inline int is_root(__u32 uid)
{
    return uid == 0;
}

// prog1.bpf.c
#include "helpers.h"
SEC("kprobe/sys_open")
int trace_open(struct pt_regs *ctx) { ... }

// prog2.bpf.c
#include "helpers.h"
SEC("xdp")
int xdp_filter(struct xdp_md *ctx) { ... }""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Since Linux 4.16, BPF-to-BPF calls (non-inlined function calls within the same BPF program) are supported. But inlining is still the safest default for helper utilities shared across files.")
                }
            }
            item {
                SectionCard(title = "Differences from Regular C") {
                    BodyText("eBPF C is a restricted subset of C. The key differences:")
                    CodeBlock("""No standard library        — no #include <stdio.h>, no printf,
                              no malloc, no string.h
No dynamic memory          — malloc() and free() do not exist;
                              use BPF maps for persistent storage
512-byte stack limit       — all local variables together cannot
                              exceed 512 bytes per program
No global variables        — use BPF maps instead; the only
                              allowed globals are maps and license
No floating point          — no float or double
No variable-length arrays  — array sizes must be compile-time constants
No function pointers       — not allowed (older kernels);
                              newer kernels allow them in limited contexts
Bounded loops only         — the verifier rejects infinite loops;
                              all loops must be provably terminating
Verified memory access     — every pointer dereference is checked;
                              you cannot freely cast and dereference
No recursion               — BPF does not support recursive calls""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("What you CAN use: if/else, for/while/do-while (bounded), switch/case, structs, enums, typedef, bitfields, static inline functions, and most arithmetic operators.")
                }
            }
            item {
                SectionCard(title = "Control Flow: if/else") {
                    BodyText("Standard C if/else if/else syntax is fully supported and behaves exactly as in regular C. The verifier traces all branches.")
                    CodeBlock("""SEC("kprobe/vfs_open")
int trace_open(struct pt_regs *ctx)
{
    __u32 pid = bpf_get_current_pid_tgid() >> 32;

    if (pid == 0) {
        // kernel thread — skip
        return 0;
    } else if (pid < 1000) {
        bpf_printk("system process: pid=%u\n", pid);
    } else {
        bpf_printk("user process: pid=%u\n", pid);
    }
    return 0;
}""")
                }
            }
            item {
                SectionCard(title = "Loops: for/while/do-while") {
                    BodyText("All three loop forms are syntactically supported, but the BPF verifier must be able to prove they terminate. Loops with a fixed constant bound are always accepted:")
                    CodeBlock("""// OK — bounded by a constant
for (int i = 0; i < 16; i++) {
    buf[i] = 0;
}

// OK — bounded by a constant
int i = 0;
while (i < 8) {
    arr[i++] = 1;
}

// REJECTED — verifier cannot prove termination
while (some_condition) {   // condition could stay true forever
    ...
}""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For larger or runtime-bounded iterations (e.g. iterate over all map entries), use bpf_loop() introduced in Linux 5.17:")
                    CodeBlock("""// bpf_loop(nr_loops, callback, ctx, flags)
// callback returns 1 to continue, 0 to stop early
static int my_cb(u32 index, void *data)
{
    // called with index = 0, 1, 2, ...
    bpf_printk("index = %u\n", index);
    return 0;  // keep going
}

bpf_loop(100, my_cb, NULL, 0);  // calls my_cb 100 times""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Older kernels (pre-5.3) had very tight loop limits. Since Linux 5.3, loops up to BPF_MAX_LOOPS (8 million) are allowed if the verifier can bound them.")
                }
            }
            item {
                SectionCard(title = "switch/case") {
                    BodyText("switch/case is fully supported. The verifier handles all branches individually. Useful for dispatching on protocol types, event types, etc.:")
                    CodeBlock("""SEC("xdp")
int xdp_proto_filter(struct xdp_md *ctx)
{
    void *data     = (void *)(long)ctx->data;
    void *data_end = (void *)(long)ctx->data_end;
    struct ethhdr *eth = data;

    if ((void *)(eth + 1) > data_end)
        return XDP_PASS;

    switch (bpf_ntohs(eth->h_proto)) {
    case ETH_P_IP:
        bpf_printk("IPv4 packet\n");
        return XDP_PASS;
    case ETH_P_IPV6:
        bpf_printk("IPv6 packet\n");
        return XDP_PASS;
    case ETH_P_ARP:
        return XDP_DROP;
    default:
        return XDP_PASS;
    }
}""")
                }
            }
            item {
                SectionCard(title = "Memory Allocation") {
                    BodyText("There is no malloc() or free() in eBPF. For persistent storage, use BPF maps. For temporary per-invocation scratch space, use the stack (max 512 bytes).")
                    CodeBlock("""// Stack — temporary, gone after the program returns
char buf[64];                     // fine, counts toward 512B stack
struct my_event event = {};       // zero-initialise on stack

// BPF map — persistent across invocations, survives program exit
struct {
    __uint(type, BPF_MAP_TYPE_ARRAY);
    __uint(max_entries, 1);
    __type(key, __u32);
    __type(value, __u64);
} counter_map SEC(".maps");

// Per-CPU array — one value per CPU, no locking needed
struct {
    __uint(type, BPF_MAP_TYPE_PERCPU_ARRAY);
    __uint(max_entries, 256);
    __type(key, __u32);
    __type(value, __u64);
} per_cpu_counts SEC(".maps");""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For heap-like scratch buffers larger than the stack allows, use a single-entry per-CPU array as a 'scratch map': allocate max_entries=1, key=0, value=struct with your large buffer.")
                }
            }
            item {
                SectionCard(title = "Pointer Arithmetic") {
                    BodyText("Direct arithmetic on kernel pointers is heavily restricted. The verifier tracks pointer types and rejects unsafe dereferences. The rules differ by pointer kind:")
                    CodeBlock("""// Context pointer (xdp_md, pt_regs, etc.) — access fields directly
struct xdp_md *ctx;
__u32 ingress = ctx->ingress_ifindex;  // OK

// Packet data pointers — arithmetic allowed, but MUST bounds-check
void *data     = (void *)(long)ctx->data;
void *data_end = (void *)(long)ctx->data_end;
struct iphdr *ip = data + sizeof(struct ethhdr);
if ((void *)(ip + 1) > data_end)      // REQUIRED bounds check
    return XDP_PASS;
// Now safe to read ip->saddr, ip->protocol, etc.

// Kernel memory pointers — CANNOT dereference directly
// Use bpf_probe_read_kernel() to safely copy
struct task_struct *task = bpf_get_current_task_btf();
__u32 uid;
bpf_probe_read_kernel(&uid, sizeof(uid),
                      &task->cred->uid.val);  // safe copy

// Maps — access via helpers only
__u64 *val = bpf_map_lookup_elem(&my_map, &key);
if (val)              // NULL check is mandatory before deref
    *val += 1;""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("With CO-RE (vmlinux.h + libbpf), use BPF_CORE_READ() instead of raw pointer access. It handles kernel version differences automatically:")
                    CodeBlock("""__u32 tgid = BPF_CORE_READ(task, tgid);  // CO-RE safe read""")
                }
            }
            item {
                SectionCard(title = "License Requirement") {
                    BodyText("Every eBPF program must declare its license. The verifier checks this to decide which BPF helpers the program may call — most helpers are GPL-only.")
                    CodeBlock("""// Required in every .bpf.c file
char _license[] SEC("license") = "GPL";

// Other valid license strings:
// "GPL v2", "GPL and additional rights", "Dual BSD/GPL",
// "Dual MIT/GPL", "Dual MPL/GPL"
// Non-GPL example:
// char _license[] SEC("license") = "Proprietary";""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Non-GPL programs may only use a small set of non-GPL helpers:")
                    CodeBlock("""Non-GPL helpers (available to any license):
  bpf_map_lookup_elem()
  bpf_map_update_elem()
  bpf_map_delete_elem()
  bpf_map_push_elem()
  bpf_map_pop_elem()
  bpf_map_peek_elem()
  bpf_ktime_get_ns()
  bpf_get_prandom_u32()
  bpf_get_smp_processor_id()
  bpf_tail_call()
  bpf_redirect()
  bpf_sk_redirect_map()

GPL-only helpers (require GPL license) — examples:
  bpf_probe_read_kernel()
  bpf_probe_read_user()
  bpf_get_current_pid_tgid()
  bpf_get_current_comm()
  bpf_trace_printk()
  bpf_ringbuf_reserve()
  bpf_ringbuf_submit()
  bpf_perf_event_output()
  bpf_override_return()
  bpf_send_signal()
  bpf_get_current_task()
  bpf_get_socket_cookie()""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("If your program calls a GPL-only helper without a GPL license, the kernel rejects the program at load time with -EPERM.")
                }
            }
            item {
                SectionCard(title = "The SEC() Macro") {
                    BodyText("SEC() (from <bpf/bpf_helpers.h>) places the function in a named ELF section. The loader (libbpf, bpftool, BCC) reads the section name to determine the program type and attach point.")
                    CodeBlock("""#include <bpf/bpf_helpers.h>

// XDP — runs at NIC driver level on incoming packets
SEC("xdp")
int my_xdp(struct xdp_md *ctx) { ... }

// kprobe — fires at entry of kernel function
SEC("kprobe/vfs_open")
int trace_vfs_open(struct pt_regs *ctx) { ... }

// kretprobe — fires at return of kernel function
SEC("kretprobe/vfs_open")
int trace_vfs_open_ret(struct pt_regs *ctx) { ... }

// fentry/fexit — faster BTF-based alternative to kprobe/kretprobe
// (requires kernel 5.5+ and CONFIG_DEBUG_INFO_BTF)
SEC("fentry/tcp_sendmsg")
int BPF_PROG(tcp_send_entry, struct sock *sk) { ... }

SEC("fexit/tcp_sendmsg")
int BPF_PROG(tcp_send_exit, struct sock *sk, int ret) { ... }

// Tracepoint — stable kernel ABI event hook
SEC("tracepoint/syscalls/sys_enter_openat")
int trace_openat(struct trace_event_raw_sys_enter *ctx) { ... }

// LSM — security hook
SEC("lsm/security_file_open")
int BPF_PROG(file_open, struct file *file, int ret) { ... }

// Socket filter
SEC("socket")
int my_filter(struct __sk_buff *skb) { ... }""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Note: SEC() predicates (like /comm == \"bash\"/) are a bpftrace concept only. In C eBPF programs, use an if statement at the start of the function body for the same effect.")
                }
            }
            item {
                SectionCard(title = "kprobe vs kretprobe") {
                    BodyText("kprobes attach to kernel functions. kprobe fires at function entry; kretprobe fires when the function returns. Both give you access to the CPU registers at that point.")
                    CodeBlock("""#include "vmlinux.h"
#include <bpf/bpf_helpers.h>
#include <bpf/bpf_tracing.h>

char _license[] SEC("license") = "GPL";

// kprobe — fires when do_sys_openat2() is called
// ctx is struct pt_regs; use PT_REGS_PARM macros for args
SEC("kprobe/do_sys_openat2")
int kprobe_openat(struct pt_regs *ctx)
{
    // PT_REGS_PARM1 = first argument (int dfd)
    // PT_REGS_PARM2 = second argument (const char __user *filename)
    __u64 pid = bpf_get_current_pid_tgid() >> 32;
    bpf_printk("openat called by pid=%llu\n", pid);
    return 0;
}

// kretprobe — fires when do_sys_openat2() is about to return
// PT_REGS_RC gives the return value
SEC("kretprobe/do_sys_openat2")
int kretprobe_openat(struct pt_regs *ctx)
{
    long retval = PT_REGS_RC(ctx);
    if (retval < 0)
        bpf_printk("openat failed: errno=%ld\n", -retval);
    return 0;
}""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For newer kernels (5.5+), prefer fentry/fexit over kprobe/kretprobe. fentry/fexit use BTF-based tracing which is faster (no int3 trap) and gives you typed function arguments directly:")
                    CodeBlock("""// fentry — same effect as kprobe but faster, typed args
SEC("fentry/do_sys_openat2")
int BPF_PROG(fentry_openat, int dfd, const char *filename,
             struct open_how *how)
{
    bpf_printk("openat: dfd=%d\n", dfd);
    return 0;
}

// fexit — same effect as kretprobe, gets both args and return value
SEC("fexit/do_sys_openat2")
int BPF_PROG(fexit_openat, int dfd, const char *filename,
             struct open_how *how, long ret)
{
    bpf_printk("openat returned: %ld\n", ret);
    return 0;
}""")
                }
            }
            item {
                SectionCard(title = "XDP Programs") {
                    BodyText("XDP (eXpress Data Path) programs run at the earliest point in the network receive path — inside the NIC driver, before the kernel's networking stack. They are the fastest place to filter or redirect packets.")
                    CodeBlock("""// Context: struct xdp_md — describes the received packet buffer
// ctx->data     = start of packet data
// ctx->data_end = end of packet data
// ctx->ingress_ifindex = interface the packet arrived on

SEC("xdp")
int xdp_drop_icmp(struct xdp_md *ctx)
{
    void *data     = (void *)(long)ctx->data;
    void *data_end = (void *)(long)ctx->data_end;

    struct ethhdr *eth = data;
    // Bounds check REQUIRED before every pointer access
    if ((void *)(eth + 1) > data_end)
        return XDP_PASS;

    if (bpf_ntohs(eth->h_proto) != ETH_P_IP)
        return XDP_PASS;

    struct iphdr *ip = (void *)(eth + 1);
    if ((void *)(ip + 1) > data_end)
        return XDP_PASS;

    // Drop all ICMP packets
    if (ip->protocol == IPPROTO_ICMP)
        return XDP_DROP;

    return XDP_PASS;
}""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("XDP return codes:")
                    CodeBlock("""XDP_PASS      — pass the packet up to the normal network stack
XDP_DROP      — discard the packet (zero-copy, very fast)
XDP_TX        — transmit the packet back out on the same interface
XDP_REDIRECT  — forward to another interface or socket
XDP_ABORTED   — drop with an error trace event (debugging)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("XDP programs run in an atomic NAPI softirq context — they cannot sleep, allocate memory, or call any blocking operations. They are for observation and forwarding only, not for arbitrary kernel actions.")
                }
            }
            item {
                SectionCard(title = "vmlinux.h and BTF") {
                    BodyText("vmlinux.h is a single auto-generated header containing all kernel type definitions. Including it gives your eBPF program access to every kernel struct (task_struct, sk_buff, file, inode, etc.) without needing kernel source headers.")
                    CodeBlock("""// Generate vmlinux.h from the running kernel's BTF info:
bpftool btf dump file /sys/kernel/btf/vmlinux format c > vmlinux.h

// Verify BTF is available on your kernel:
ls /sys/kernel/btf/vmlinux       // must exist
# Requires CONFIG_DEBUG_INFO_BTF=y (enabled by default
# in Ubuntu 20.04+, Fedora 31+, Debian 11+)

// In your .bpf.c file — use instead of kernel headers:
#include "vmlinux.h"              // all kernel types
#include <bpf/bpf_helpers.h>     // SEC, bpf_printk, etc.
#include <bpf/bpf_core_read.h>   // BPF_CORE_READ macro""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("BTF (BPF Type Format) is DWARF-like debug information embedded in the kernel. It describes every struct, union, typedef, and enum in the kernel with full field names and offsets.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("CO-RE (Compile Once, Run Everywhere): libbpf reads the BTF at load time to fix up field offsets in your BPF bytecode. This means a BPF object compiled against kernel 5.15's vmlinux.h will still run correctly on kernel 6.1, even if struct layouts changed. Use BPF_CORE_READ() instead of direct dereference for full CO-RE portability:")
                    CodeBlock("""// Direct dereference — NOT CO-RE portable
__u32 tgid = task->tgid;           // breaks if offset changes

// BPF_CORE_READ — CO-RE portable, libbpf fixes offset at load
__u32 tgid = BPF_CORE_READ(task, tgid);  // safe across kernels""")
                }
            }
            item {
                SectionCard(title = "Defining Maps") {
                    BodyText("BPF maps are the primary mechanism for sharing state between eBPF programs, between multiple programs, and between eBPF and user space. Define them with the SEC(\".maps\") annotation using a struct with __uint/__type helper macros:")
                    CodeBlock("""#include "vmlinux.h"
#include <bpf/bpf_helpers.h>

// Hash map: key=pid (__u32), value=open count (__u64)
struct {
    __uint(type, BPF_MAP_TYPE_HASH);
    __uint(max_entries, 4096);
    __type(key,   __u32);
    __type(value, __u64);
} open_counts SEC(".maps");

// Array map: fixed-size array indexed 0..N-1
struct {
    __uint(type, BPF_MAP_TYPE_ARRAY);
    __uint(max_entries, 256);
    __type(key,   __u32);
    __type(value, __u64);
} stats SEC(".maps");

// Ring buffer: for streaming events to user space (kernel 5.8+)
struct {
    __uint(type, BPF_MAP_TYPE_RINGBUF);
    __uint(max_entries, 1 << 24);    // 16 MB ring
} events SEC(".maps");""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Accessing maps from within eBPF:")
                    CodeBlock("""__u32 key = bpf_get_current_pid_tgid() >> 32;

// Lookup — returns pointer or NULL
__u64 *count = bpf_map_lookup_elem(&open_counts, &key);
if (count)
    __sync_fetch_and_add(count, 1);   // atomic increment

// Insert / update
__u64 zero = 0;
bpf_map_update_elem(&open_counts, &key, &zero, BPF_NOEXIST);

// Delete
bpf_map_delete_elem(&open_counts, &key);

// Ring buffer — reserve, fill, submit
struct my_event *e = bpf_ringbuf_reserve(&events,
                                          sizeof(*e), 0);
if (e) {
    e->pid = key;
    bpf_ringbuf_submit(e, 0);
}""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Accessing maps from user space with libbpf:")
                    CodeBlock("""// After bpf_object__load(obj):
struct bpf_map *map = bpf_object__find_map_by_name(obj,
                                                    "open_counts");
int map_fd = bpf_map__fd(map);

// bpf() syscall wrappers
__u32 key = 1234;
__u64 value;
bpf_map_lookup_elem(map_fd, &key, &value);
bpf_map_update_elem(map_fd, &key, &value, BPF_ANY);""")
                }
            }
            item {
                SectionCard(title = "Pinning Maps to the Filesystem") {
                    BodyText("By default, a BPF map is destroyed when the last file descriptor referring to it is closed (i.e., when your loader process exits). Pinning saves a reference on the BPF filesystem so the map survives.")
                    CodeBlock("""// Mount the BPF filesystem (usually already mounted):
mount -t bpf bpf /sys/fs/bpf
# Or check: mount | grep bpf

// Pin a map via bpftool:
bpftool map pin id 42 /sys/fs/bpf/my_open_counts
# 42 is the map's ID from: bpftool map list

// Retrieve a pinned map from a new process:
int map_fd = bpf_obj_get("/sys/fs/bpf/my_open_counts");

// Unpin (remove the pin, map is freed when all FDs are closed):
rm /sys/fs/bpf/my_open_counts""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Pin a map programmatically with libbpf:")
                    CodeBlock("""// After loading the BPF object:
struct bpf_map *map = bpf_object__find_map_by_name(obj, "open_counts");
int err = bpf_map__pin(map, "/sys/fs/bpf/open_counts");
if (err)
    fprintf(stderr, "pin failed: %d\n", err);

// In a different process, open the pinned map:
int fd = bpf_obj_get("/sys/fs/bpf/open_counts");
// Use fd with bpf_map_lookup_elem(), etc.
close(fd);""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Pinning is the standard way for user-space tools like bpftool, Cilium, and Katran to share maps between processes or to inspect maps after a BPF program's loader has exited.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
