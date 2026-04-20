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
fun KernelVfsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "VFS — Virtual Filesystem",
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
                SectionCard(title = "What is the VFS") {
                    BodyText("The Virtual Filesystem Switch (VFS) is the kernel abstraction layer that lets every program use the same system calls (open, read, write, stat…) regardless of the underlying filesystem: ext4, tmpfs, NFS, procfs, or your own custom fs.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When user space calls read(), the kernel path is:")
                    CodeBlock(
                        """user: read(fd, buf, len)
  → syscall entry (sys_read)
    → fdget() — file* from the fd table
      → file->f_op->read_iter()
          → ext4_file_read_iter()   (if ext4)
          → tmpfs_file_read_iter()  (if tmpfs)
          → your_fs_read_iter()     (if yours)"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The VFS defines the interfaces (function pointer tables). Each filesystem fills them in. The caller never needs to know which fs it is talking to.")
                }
            }
            item {
                SectionCard(title = "Core VFS Data Structures") {
                    BodyText("Four objects model a mounted filesystem and the files within it:")
                    CodeBlock(
                        """superblock  — one per mounted filesystem instance.
              Holds fs-wide info: block size, ops table,
              root inode pointer.
              struct super_block, struct super_operations

inode       — one per file/directory/symlink on disk.
              Holds metadata: size, permissions, timestamps,
              data block pointers.
              struct inode, struct inode_operations

dentry      — one per path component in the kernel cache.
              Maps a name ("foo") to its inode.
              Kept in the dentry cache (dcache) for fast lookups.
              struct dentry

file        — one per open file descriptor in a process.
              Holds the current file offset and links to the
              dentry/inode.
              struct file, struct file_operations"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The ops tables are the key extension points:")
                    CodeBlock(
                        """struct file_operations {
    ssize_t (*read_iter)(struct kiocb *, struct iov_iter *);
    ssize_t (*write_iter)(struct kiocb *, struct iov_iter *);
    int     (*open)(struct inode *, struct file *);
    int     (*release)(struct inode *, struct file *);
    long    (*unlocked_ioctl)(struct file *, unsigned int,
                              unsigned long);
    loff_t  (*llseek)(struct file *, loff_t, int);
    ...
};

struct inode_operations {
    struct dentry * (*lookup)(struct inode *, struct dentry *,
                              unsigned int);
    int  (*create)(struct mnt_idmap *, struct inode *,
                   struct dentry *, umode_t, bool);
    int  (*mkdir)(struct mnt_idmap *, struct inode *,
                  struct dentry *, umode_t);
    int  (*unlink)(struct inode *, struct dentry *);
    ...
};

struct super_operations {
    struct inode * (*alloc_inode)(struct super_block *);
    void           (*destroy_inode)(struct inode *);
    int            (*statfs)(struct dentry *, struct kstatfs *);
    int            (*remount_fs)(struct super_block *, int *,
                                 char *);
    ...
};"""
                    )
                }
            }
            item {
                SectionCard(title = "System Calls → VFS") {
                    BodyText("Common syscalls and how they flow through the VFS layer:")
                    CodeBlock(
                        """open(path, flags)
  → vfs_open() → dentry_open()
    → file->f_op->open()

read(fd, buf, n)
  → vfs_read() → file->f_op->read_iter()

write(fd, buf, n)
  → vfs_write() → file->f_op->write_iter()

stat(path, statbuf)
  → vfs_getattr() → inode->i_op->getattr()

unlink(path)
  → vfs_unlink() → dir_inode->i_op->unlink()

mkdir(path, mode)
  → vfs_mkdir() → dir_inode->i_op->mkdir()

mount(src, target, fstype, flags, data)
  → do_mount() → find_filesystem(fstype)
    → fs_type->mount() → fill_super()
      → registers the superblock"""
                    )
                }
            }
            item {
                SectionCard(title = "Permission Checking — open() to read()/write()") {
                    BodyText(
                        "When open() is called, the kernel performs a full DAC (Discretionary Access Control) " +
                        "check against the inode's owner, group, and permission bits. The result is baked into " +
                        "the struct file — subsequent read()/write() calls skip the inode entirely and only " +
                        "check the flags stored in the open file object."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Call chain for open() — permission decision point:")
                    CodeBlock(
                        """open(path, O_RDWR)
  → do_sys_open() / do_sys_openat2()
    → do_filp_open()
      → path_openat()       /* resolve path, walk dentries */
        → vfs_open()
          → do_dentry_open() /* sets f_mode, calls f_op->open */

/* Permission check happens inside path_openat():
   may_open() → inode_permission(inode, MAY_READ|MAY_WRITE|MAY_OPEN) */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Inside inode_permission() — the DAC check:")
                    CodeBlock(
                        """inode_permission(inode, mask)
  → security_inode_permission()   /* LSM hooks: SELinux, AppArmor */
  → do_inode_permission()
      → inode->i_op->permission()  /* filesystem-specific, if set */
         else → generic_permission()

/* mask values passed to inode_permission(): */
MAY_READ   (0x04)  — check read permission
MAY_WRITE  (0x02)  — check write permission
MAY_EXEC   (0x01)  — check execute permission
MAY_OPEN   (0x20)  — set when the caller is open(), not just a test"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("generic_permission() — what it actually checks:")
                    CodeBlock(
                        """generic_permission(idmap, inode, mask)
{
    /* 1. Capabilities: CAP_DAC_OVERRIDE bypasses mode checks.
          CAP_DAC_READ_SEARCH bypasses read + exec checks.
          → root (uid 0) usually holds these, bypassing DAC */

    /* 2. Owner check: if current_uid() == inode->i_uid
          test the owner bits of inode->i_mode (rwx------) */

    /* 3. Group check: if in_group_p(inode->i_gid)
          test the group bits of inode->i_mode (---rwx---) */

    /* 4. Other: test the world bits  (------rwx) */

    /* 5. ACL check: posix_acl_permission() if ACLs are set */

    return 0 (allow) or -EACCES (deny)
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Where the permission decision is stored — struct file:")
                    CodeBlock(
                        """/* In do_dentry_open(), after permission passes: */
struct file {
    fmode_t  f_mode;    /* bitmask set at open time */
    ...
};

/* Flags stamped into f_mode: */
FMODE_READ   — file was opened for reading
FMODE_WRITE  — file was opened for writing
FMODE_EXEC   — file is being executed (mmap with PROT_EXEC)

/* If open() is denied (e.g. O_RDWR on a read-only file),
   open() returns -EACCES and no struct file is created. */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Subsequent read() and write() — no inode check repeated:")
                    CodeBlock(
                        """read(fd, buf, n)
  → vfs_read(file, buf, n, &pos)
      if (!(file->f_mode & FMODE_READ)) return -EBADF
      → file->f_op->read_iter()   /* go directly to filesystem */

write(fd, buf, n)
  → vfs_write(file, buf, n, &pos)
      if (!(file->f_mode & FMODE_WRITE)) return -EBADF
      → file->f_op->write_iter()

/* inode_permission() is NOT called again on each read/write.
   The kernel trusts the flag stamped at open time.
   Exception: LSMs (SELinux/AppArmor) may run a separate
   security_file_permission() hook per read/write for MAC policy. */"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Summary:")
                    CodeBlock(
                        """open()  → full check: inode uid/gid/mode + capabilities + LSM
           result stored in file->f_mode (FMODE_READ / FMODE_WRITE)

read()  → checks only file->f_mode & FMODE_READ
write() → checks only file->f_mode & FMODE_WRITE"""
                    )
                }
            }
            item {
                SectionCard(title = "Registering a Simple Filesystem") {
                    BodyText("A minimal read-only in-memory filesystem. The key callback is fill_super — it populates the superblock and creates the root inode.")
                    CodeBlock(
                        """#include <linux/fs.h>
#include <linux/init.h>
#include <linux/module.h>

#define MYFS_MAGIC 0xDEADF00D

/* file_operations for our files (read-only, no data) */
static const struct file_operations myfs_file_ops = {
    .owner   = THIS_MODULE,
    .read    = generic_read_dir,
    .iterate_shared = simple_dir_operations.iterate_shared,
};

static const struct inode_operations myfs_inode_ops = {
    .lookup = simple_lookup,  /* VFS helper for simple FSes */
};

static int myfs_fill_super(struct super_block *sb,
                            void *data, int silent)
{
    struct inode *root;

    sb->s_magic     = MYFS_MAGIC;
    sb->s_op        = &simple_super_operations;
    sb->s_blocksize = PAGE_SIZE;
    sb->s_blocksize_bits = PAGE_SHIFT;

    /* Allocate the root inode */
    root = new_inode(sb);
    if (!root) return -ENOMEM;

    root->i_ino  = 1;
    root->i_mode = S_IFDIR | 0755;
    root->i_op   = &myfs_inode_ops;
    root->i_fop  = &simple_dir_operations;
    inode_init_owner(&nop_mnt_idmap, root, NULL, root->i_mode);

    /* Create the root dentry */
    sb->s_root = d_make_root(root);
    if (!sb->s_root) return -ENOMEM;
    return 0;
}

static struct dentry *myfs_mount(struct file_system_type *fst,
    int flags, const char *dev, void *data)
{
    return mount_nodev(fst, flags, data, myfs_fill_super);
}

static struct file_system_type myfs_type = {
    .name    = "myfs",
    .mount   = myfs_mount,
    .kill_sb = kill_litter_super,
    .owner   = THIS_MODULE,
};

static int __init myfs_init(void) {
    return register_filesystem(&myfs_type);
}
static void __exit myfs_exit(void) {
    unregister_filesystem(&myfs_type);
}
module_init(myfs_init);
module_exit(myfs_exit);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Mount it from user space:")
                    CodeBlock(
                        """mount -t myfs none /mnt/myfs
# 'none' because there is no backing device
# (like tmpfs or proc)"""
                    )
                }
            }
            item {
                SectionCard(title = "procfs — /proc") {
                    BodyText("/proc is a virtual filesystem that exposes kernel data structures as files. No disk I/O happens — reads call kernel functions that generate content on the fly.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Create a /proc entry from a kernel module using the seq_file API (handles large outputs that span multiple read() calls):")
                    CodeBlock(
                        """#include <linux/proc_fs.h>
#include <linux/seq_file.h>

static int myproc_show(struct seq_file *m, void *v)
{
    seq_printf(m, "hello from kernel\n");
    seq_printf(m, "jiffies = %lu\n", jiffies);
    return 0;
}

static int myproc_open(struct inode *inode,
                       struct file *file)
{
    return single_open(file, myproc_show, NULL);
}

static const struct proc_ops myproc_ops = {
    .proc_open    = myproc_open,
    .proc_read    = seq_read,
    .proc_lseek   = seq_lseek,
    .proc_release = single_release,
};

static struct proc_dir_entry *pde;

static int __init mymod_init(void) {
    pde = proc_create("mydriver", 0444, NULL, &myproc_ops);
    return pde ? 0 : -ENOMEM;
}
static void __exit mymod_exit(void) {
    proc_remove(pde);
}"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock(
                        """# Result:
cat /proc/mydriver
# hello from kernel
# jiffies = 4398046511104"""
                    )
                }
            }
            item {
                SectionCard(title = "devfs — /dev") {
                    BodyText("/dev is managed by udev (user space) based on kernel events, but the kernel controls which device nodes appear via the class/device API (the same API used by char and block drivers).")
                    CodeBlock(
                        """/* Create /dev/mydevice automatically */

/* 1. Allocate a device number */
dev_t devno;
alloc_chrdev_region(&devno, 0, 1, "mydevice");

/* 2. Create a class (shows up in /sys/class/) */
struct class *cls = class_create("mydevice");

/* 3. Create the device — triggers udev to make /dev/mydevice */
struct device *dev = device_create(cls, NULL, devno,
                                   NULL, "mydevice");

/* Cleanup */
device_destroy(cls, devno);
class_destroy(cls);
unregister_chrdev_region(devno, 1);"""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("When device_create() is called, the kernel sends a uevent. udev reads it and creates the /dev/mydevice node with the correct major:minor numbers and permissions from udev rules.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Key /dev special files:")
                    CodeBlock(
                        """/dev/null   — discards all writes; reads return EOF
/dev/zero   — reads return infinite zero bytes
/dev/random — cryptographic random bytes (blocks if low entropy)
/dev/urandom — non-blocking random (safe for most uses)
/dev/mem    — raw physical memory (requires CAP_SYS_RAWIO)
/dev/sdX    — block device (hard disk / SSD partition)
/dev/ttyX   — terminal device"""
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
