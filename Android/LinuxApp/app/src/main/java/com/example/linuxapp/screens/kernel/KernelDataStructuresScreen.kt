package com.example.linuxapp.screens.kernel

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KernelDataStructuresScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Kernel Data Structures",
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
                SectionCard(title = "Fixed-Width Integer Types") {
                    BodyText("The kernel avoids plain int and unsigned int because their width is platform-dependent. Instead, use explicit-width types from <linux/types.h>:")
                    CodeBlock(
                        """Unsigned:  u8   u16   u32   u64
Signed:    s8   s16   s32   s64

/* Example struct with explicit-width fields */
struct my_header {
    u32 magic;       /* always 32 bits */
    u16 version;     /* always 16 bits */
    u8  flags;       /* always 8 bits  */
    u8  reserved;
};"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For protocol headers that cross the wire, annotate the endianness so sparse (the kernel static checker) can catch bugs:")
                    CodeBlock(
                        """__le16  __le32  __le64   /* little-endian */
__be16  __be32  __be64   /* big-endian   */

/* Example: IPv4 header fields */
struct iphdr {
    __be16 tot_len;    /* big-endian on the wire */
    __be32 saddr;
    __be32 daddr;
    ...
};

/* Conversion helpers */
u32 host = be32_to_cpu(hdr->saddr);  /* BE → host */
hdr->saddr = cpu_to_be32(host);      /* host → BE */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("For lock-free reference counters, use atomic_t instead of a plain integer:")
                    CodeBlock(
                        """atomic_t    refcount;          /* 32-bit atomic */
atomic64_t  byte_count;        /* 64-bit atomic */

atomic_set(&refcount, 1);
atomic_inc(&refcount);
if (atomic_dec_and_test(&refcount))
    kfree(obj);                /* free when hits zero */"""
                    )
                }
            }
            item {
                SectionCard(title = "Doubly Linked Lists — list_head") {
                    BodyText("The kernel uses intrusive linked lists: you embed struct list_head inside your own struct rather than the list owning your data. This avoids extra allocations and works with any type.")
                    CodeBlock(
                        """#include <linux/list.h>

/* 1. Define your struct with an embedded list_head */
struct my_device {
    int         id;
    char        name[32];
    struct list_head list;  /* linkage node */
};

/* 2. Declare and initialise the list head (static) */
static LIST_HEAD(device_list);

/* 3. Add entries */
struct my_device *dev = kmalloc(sizeof(*dev), GFP_KERNEL);
dev->id = 42;
INIT_LIST_HEAD(&dev->list);       /* init the node */
list_add(&dev->list, &device_list);      /* prepend */
list_add_tail(&dev->list, &device_list); /* append  */

/* 4. Iterate — container_of recovers the outer struct */
struct my_device *pos;
list_for_each_entry(pos, &device_list, list) {
    pr_info("device id=%d name=%s\n", pos->id, pos->name);
}

/* 5. Remove */
list_del(&dev->list);
kfree(dev);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("container_of(ptr, type, member) works by subtracting the compile-time offset of member from ptr, yielding a pointer to the enclosing struct. The kernel uses it everywhere.")
                }
            }
            item {
                SectionCard(title = "Hash Tables — DEFINE_HASHTABLE") {
                    BodyText("The kernel provides a simple chained hash table built on hlist (singly-linked with a back-pointer). Declare it with a power-of-two bucket count.")
                    CodeBlock(
                        """#include <linux/hashtable.h>

/* Declare a hash table with 2^8 = 256 buckets */
DEFINE_HASHTABLE(pid_table, 8);

/* Your struct embeds struct hlist_node */
struct pid_entry {
    pid_t            pid;
    char             comm[TASK_COMM_LEN];
    struct hlist_node hlist;
};

/* Add */
struct pid_entry *e = kmalloc(sizeof(*e), GFP_KERNEL);
e->pid = current->pid;
strscpy(e->comm, current->comm, TASK_COMM_LEN);
hash_add(pid_table, &e->hlist, e->pid);

/* Lookup by key */
struct pid_entry *pos;
hash_for_each_possible(pid_table, pos, hlist, target_pid) {
    if (pos->pid == target_pid)
        pr_info("found: %s\n", pos->comm);
}

/* Iterate all entries */
int bkt;
hash_for_each(pid_table, bkt, pos, hlist) {
    pr_info("pid=%d comm=%s\n", pos->pid, pos->comm);
}

/* Remove */
hash_del(&e->hlist);
kfree(e);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The hash key can be any integer — the macro applies a Jenkins-style mix internally. Use a larger bit count (e.g. 10–12) for tables with many entries to reduce collision chains.")
                }
            }
            item {
                SectionCard(title = "Red-Black Trees — rbtree") {
                    BodyText("The kernel's rbtree is a sorted, self-balancing binary search tree. It is used internally for VMAs (mm_struct), the timer wheel, and CFS scheduling. Unlike list_head, you must write the comparison and insertion logic yourself.")
                    CodeBlock(
                        """#include <linux/rbtree.h>

/* Embed rb_node in your struct */
struct int_node {
    int             value;
    struct rb_node  rb;
};

/* Root is just a pointer — initialise to empty */
struct rb_root myroot = RB_ROOT;

/* Insert — walk the tree, then call rb_insert_color */
static void insert(struct rb_root *root, struct int_node *new)
{
    struct rb_node **link = &root->rb_node;
    struct rb_node  *parent = NULL;

    while (*link) {
        struct int_node *this =
            rb_entry(*link, struct int_node, rb);
        parent = *link;
        if (new->value < this->value)
            link = &(*link)->rb_left;
        else if (new->value > this->value)
            link = &(*link)->rb_right;
        else
            return; /* duplicate */
    }
    rb_link_node(&new->rb, parent, link);
    rb_insert_color(&new->rb, root);
}

/* Search */
static struct int_node *search(struct rb_root *root, int val)
{
    struct rb_node *node = root->rb_node;
    while (node) {
        struct int_node *d = rb_entry(node, struct int_node, rb);
        if (val < d->value)      node = node->rb_left;
        else if (val > d->value) node = node->rb_right;
        else                     return d;
    }
    return NULL;
}

/* Remove */
rb_erase(&node->rb, &myroot);
kfree(node);

/* In-order traversal */
struct rb_node *n;
for (n = rb_first(&myroot); n; n = rb_next(n)) {
    struct int_node *d = rb_entry(n, struct int_node, rb);
    pr_info("value=%d\n", d->value);
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Use rbtree when you need O(log n) ordered lookup, range queries, or in-order traversal. For unordered lookup by integer key, prefer the hashtable instead.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
