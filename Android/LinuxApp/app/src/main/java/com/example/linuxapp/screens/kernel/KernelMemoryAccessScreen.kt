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
fun KernelMemoryAccessScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Kernel Memory Access",
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
                SectionCard(title = "Accessing User Memory") {
                    BodyText(
                        "In certain kernel operations — file operations, ioctl handlers, syscall implementations — " +
                        "you receive a pointer that lives in user-space virtual memory. You cannot simply dereference " +
                        "it as you would a kernel pointer."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "You must use copy_from_user() and copy_to_user() to transfer data across the user/kernel " +
                        "boundary. These functions are only valid when running in the context of a user process — " +
                        "not in interrupt context, not in a workqueue spawned without a user process context."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "They perform three critical roles: validate that the pointer actually points into user " +
                        "space, handle page faults gracefully if the page is not yet mapped, and enforce hardware " +
                        "memory protection (SMAP — Supervisor Mode Access Prevention) that would otherwise cause " +
                        "a kernel oops if you dereferenced a user pointer directly."
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "copy_from_user / copy_to_user") {
                    BodyText("Signatures:")
                    CodeBlock(
                        """#include <linux/uaccess.h>

unsigned long copy_from_user(void       *dst,
                              const void __user *src,
                              unsigned long n);

unsigned long copy_to_user(void __user *dst,
                            const void  *src,
                            unsigned long n);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(
                        "Return value: the number of bytes that could NOT be copied. " +
                        "Zero means complete success. If nonzero, return -EFAULT to the caller."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example — write file operation:")
                    CodeBlock(
                        """static ssize_t my_write(struct file *f,
                         const char __user *buf,
                         size_t len, loff_t *off)
{
    char kbuf[128];
    size_t to_copy = min(len, sizeof(kbuf));

    if (copy_from_user(kbuf, buf, to_copy))
        return -EFAULT;

    /* process kbuf here */
    *off += to_copy;
    return to_copy;
}"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "kmalloc") {
                    BodyText(
                        "kmalloc() allocates physically and virtually contiguous memory from the SLUB allocator. " +
                        "It is fast and DMA-safe, but limited to roughly 4–8 MB per allocation."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("GFP (Get Free Pages) flags — choose based on your context:")
                    CodeBlock(
                        """GFP_KERNEL  — may sleep; use in process context (most common)
GFP_ATOMIC  — never sleeps; use in interrupt handlers or
               while holding a spinlock
GFP_DMA     — allocates in low physical memory for legacy
               ISA DMA devices
GFP_NOWAIT  — like GFP_ATOMIC but without emergency reserves"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Usage:")
                    CodeBlock(
                        """#include <linux/slab.h>

void *ptr = kmalloc(size, GFP_KERNEL);
if (!ptr)
    return -ENOMEM;

void *zptr = kzalloc(size, GFP_KERNEL); /* zero-fills */

kfree(ptr);   /* always check ptr != NULL before calling,
                  or simply call — kfree(NULL) is safe */"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "vmalloc") {
                    BodyText(
                        "vmalloc() allocates memory that is virtually contiguous but physically scattered across " +
                        "pages. It can satisfy very large allocations that kmalloc cannot (hundreds of megabytes), " +
                        "but at a cost: it must set up kernel page-table entries for each page, causing TLB pressure " +
                        "and slower access. It is NOT suitable for DMA."
                    )
                    CodeBlock(
                        """#include <linux/vmalloc.h>

void *ptr = vmalloc(size);
if (!ptr)
    return -ENOMEM;

void *zptr = vzalloc(size); /* zero-fills */

vfree(ptr);"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "kvmalloc") {
                    BodyText(
                        "kvmalloc() tries kmalloc() first. If the allocation fails (e.g., too large for a " +
                        "physically contiguous block), it falls back to vmalloc(). This is a good default when " +
                        "you want physical contiguity when possible but can tolerate the fallback."
                    )
                    CodeBlock(
                        """#include <linux/mm.h>

void *ptr = kvmalloc(size, GFP_KERNEL);
if (!ptr)
    return -ENOMEM;

void *zptr = kvzalloc(size, GFP_KERNEL); /* zero-fills */

kvfree(ptr); /* automatically calls kfree or vfree
                based on how the memory was allocated */"""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                SectionCard(title = "Comparison") {
                    CodeBlock(
                        """                  kmalloc     vmalloc     kvmalloc
Phys. contiguous  yes         no          best-effort
DMA safe          yes         no          only if kmalloc wins
Max size          ~4-8 MB     very large  very large
Interrupt context yes(ATOMIC) no          no
Speed             fast        slower      fast (usually)
Free with         kfree()     vfree()     kvfree()"""
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
