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
fun BlockDeviceScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Block Device",
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
                SectionCard(title = "What is a Block Device") {
                    BodyText("A block device exposes storage as an array of fixed-size blocks (typically 512 bytes or 4 KiB). The kernel's block layer handles buffering, reordering, and merging I/O requests before handing them to the driver.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Examples: hard disks (/dev/sda), SSDs (/dev/nvme0n1), RAM disks, loop devices (/dev/loop0). A filesystem (ext4, xfs…) is mounted on top of a block device.")
                    CodeBlock(
                        """User space: read()/write() / filesystem calls
    ↓
Page cache (kernel buffer)
    ↓
Block layer  ← merges/reorders BIOs into requests
    ↓
Block device driver  ← your driver lives here
    ↓
Hardware (disk, SSD, RAM…)"""
                    )
                }
            }
            item {
                SectionCard(title = "Key Structures") {
                    BodyText("Three structures you must know:")
                    CodeBlock(
                        """struct gendisk
  — represents the disk visible in /dev and /sys/block
  — holds: disk name, capacity, ops table, request queue
  — allocated with blk_alloc_disk()

struct block_device_operations
  — your ops table:
    .open, .release, .ioctl, .submit_bio (most important)

struct bio
  — a single I/O request from the block layer
  — direction: bio_data_dir(bio) → READ or WRITE
  — data:  bio_for_each_segment() iterates pages
  — completion: bio_endio(bio) to signal done"""
                    )
                }
            }
            item {
                SectionCard(title = "RAM-backed Block Device") {
                    BodyText("A complete minimal block driver that stores data in a vmalloc'd buffer. The submit_bio callback is the only required I/O handler.")
                    CodeBlock(
                        """#include <linux/blkdev.h>
#include <linux/module.h>
#include <linux/vmalloc.h>

#define DEVICE_NAME  "ramblk"
#define SECTOR_SIZE  512
#define DISK_SECTORS (2048)          /* 1 MiB */

static struct gendisk   *g_disk;
static u8               *g_data;     /* our RAM backing store */
static int               g_major;

/* Called by the block layer for every read/write BIO */
static void ramblk_submit_bio(struct bio *bio)
{
    struct bio_vec bvec;
    struct bvec_iter iter;
    sector_t sector = bio->bi_iter.bi_sector;

    bio_for_each_segment(bvec, bio, iter) {
        /* Map the page into kernel virtual address space */
        void *buf = kmap_local_page(bvec.bv_page)
                    + bvec.bv_offset;
        size_t len = bvec.bv_len;
        loff_t offset = sector * SECTOR_SIZE;

        if (bio_data_dir(bio) == WRITE)
            memcpy(g_data + offset, buf, len);
        else
            memcpy(buf, g_data + offset, len);

        kunmap_local(buf - bvec.bv_offset);
        sector += len / SECTOR_SIZE;
    }
    bio_endio(bio);   /* tell the block layer we're done */
}

static const struct block_device_operations ramblk_ops = {
    .owner      = THIS_MODULE,
    .submit_bio = ramblk_submit_bio,
};

static int __init ramblk_init(void)
{
    g_data = vmalloc(DISK_SECTORS * SECTOR_SIZE);
    if (!g_data) return -ENOMEM;

    g_major = register_blkdev(0, DEVICE_NAME);
    if (g_major < 0) { vfree(g_data); return g_major; }

    g_disk = blk_alloc_disk(NULL, NUMA_NO_NODE);
    if (IS_ERR(g_disk)) {
        unregister_blkdev(g_major, DEVICE_NAME);
        vfree(g_data);
        return PTR_ERR(g_disk);
    }

    g_disk->major       = g_major;
    g_disk->first_minor = 0;
    g_disk->minors      = 1;
    g_disk->fops        = &ramblk_ops;
    snprintf(g_disk->disk_name, DISK_NAME_LEN, DEVICE_NAME);

    /* Set capacity in 512-byte sectors */
    set_capacity(g_disk, DISK_SECTORS);

    /* Make the disk visible to the system */
    int err = add_disk(g_disk);
    if (err) {
        put_disk(g_disk);
        unregister_blkdev(g_major, DEVICE_NAME);
        vfree(g_data);
        return err;
    }
    pr_info("ramblk: /dev/%s registered (%d KiB)\n",
            DEVICE_NAME, DISK_SECTORS / 2);
    return 0;
}

static void __exit ramblk_exit(void)
{
    del_gendisk(g_disk);   /* remove from /dev and /sys */
    put_disk(g_disk);      /* release the gendisk reference */
    unregister_blkdev(g_major, DEVICE_NAME);
    vfree(g_data);
    pr_info("ramblk: removed\n");
}

module_init(ramblk_init);
module_exit(ramblk_exit);
MODULE_LICENSE("GPL");"""
                    )
                }
            }
            item {
                SectionCard(title = "Using the Driver") {
                    BodyText("After loading the module, the device appears as /dev/ramblk. You can format it and mount a filesystem on it:")
                    CodeBlock(
                        """insmod ramblk.ko

# Confirm it appeared
lsblk | grep ramblk
# ramblk  1M

# Format with ext4
mkfs.ext4 /dev/ramblk

# Mount it
mkdir /mnt/ram
mount /dev/ramblk /mnt/ram
echo "hello" > /mnt/ram/test.txt

# Unmount and remove
umount /mnt/ram
rmmod ramblk

# Data is lost when the module unloads (it's RAM-backed)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("To persist data across reloads, replace the vmalloc buffer with disk I/O via the request queue, or back the device with a file using the loop device pattern.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
