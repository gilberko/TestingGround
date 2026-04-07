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
fun FilePermissionsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "File Permissions & chmod",
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
                SectionCard(title = "The Permission Bits") {
                    BodyText("Every file and directory in Linux has a 9-bit permission field, split into three groups of three bits:")
                    CodeBlock("""  Owner (u)   Group (g)   Other (o)
    r w x       r w x       r w x
    4 2 1       4 2 1       4 2 1

r = read     (4)  — view file contents / list directory
w = write    (2)  — modify file contents / create/delete in dir
x = execute  (1)  — run as program / enter (cd into) directory""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Reading ls -l output:")
                    CodeBlock("""-rwxr-xr--  1  alice  devs  8192  Mar 10 12:00  myprog
│└──┬──┘└──┬──┘└──┬──┘
│  owner   group   other
│
└─ file type: '-' = regular, 'd' = directory,
              'l' = symlink, 'c' = char device, 'b' = block device""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("In the example: owner=alice can read/write/execute; group=devs can read/execute; everyone else can only read.")
                }
            }
            item {
                SectionCard(title = "chmod — Changing Permissions") {
                    BodyText("chmod changes permission bits. Two syntaxes: symbolic and octal.")
                    CodeBlock("""# Symbolic syntax
chmod u+x  file        # add execute for owner
chmod g-w  file        # remove write from group
chmod o=r  file        # set other to read-only
chmod a+r  file        # add read for all (a = ugo)
chmod u+x,g-w file     # multiple changes at once

# Octal syntax — each digit is owner/group/other
chmod 755 file         # rwxr-xr-x  (7=4+2+1, 5=4+1)
chmod 644 file         # rw-r--r--
chmod 600 file         # rw-------  (private key files)
chmod 777 file         # rwxrwxrwx  (everyone full access)

# Recursive
chmod -R 755 /var/www  # apply to directory tree""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Common octal values to memorize:")
                    CodeBlock("""400  r--------  read-only by owner (e.g. private keys)
600  rw-------  read+write by owner only
644  rw-r--r--  normal files (public read)
664  rw-rw-r--  collaborative files (group write)
700  rwx------  private executables / directories
755  rwxr-xr-x  normal executables / public directories
775  rwxrwxr-x  group-shared directories""")
                }
            }
            item {
                SectionCard(title = "chown & chgrp") {
                    BodyText("Ownership is separate from permissions. chown changes the owner; chgrp changes the group.")
                    CodeBlock("""chown alice       file       # change owner to alice
chown alice:devs  file       # change owner AND group
chown :devs       file       # change group only
chgrp devs        file       # same as chown :devs

chown -R www-data /var/www   # recursive ownership change""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("Only root can change file ownership. A regular user can change the group only to a group they belong to.")
                }
            }
            item {
                SectionCard(title = "Special Bits: setuid, setgid, sticky") {
                    BodyText("Three additional bits sit above the standard 9 permission bits.")
                    CodeBlock("""Setuid (SUID) — bit 4000
  On executables: process runs with the file owner's UID
  instead of the caller's UID.
  Example: /usr/bin/passwd is owned by root with SUID set —
  any user can run it and it temporarily gains root UID to
  update /etc/shadow.
  ls shows: -rwsr-xr-x  (s replaces x in owner's x bit)

Setgid (SGID) — bit 2000
  On executables: process runs with the file's GID.
  On directories: new files created inside inherit the
  directory's group, not the creator's primary group.
  Useful for shared project directories.
  ls shows: -rwxr-sr-x  (s in group's x bit)

Sticky bit — bit 1000
  On directories: users may only delete/rename files they own,
  even if they have write permission on the directory.
  Classic example: /tmp — everyone writes there, nobody can
  delete other users' files.
  ls shows: drwxrwxrwt  (t in other's x bit)""")
                    Spacer(modifier = Modifier.height(8.dp))
                    CodeBlock("""# Setting special bits
chmod u+s /usr/local/bin/myprog   # set SUID
chmod g+s /shared/project         # set SGID on directory
chmod +t  /tmp/mydir              # set sticky

# Octal: add 4000 / 2000 / 1000 in front
chmod 4755 myprog    # SUID + rwxr-xr-x
chmod 2775 /shared   # SGID + rwxrwxr-x
chmod 1777 /tmp      # sticky + rwxrwxrwx""")
                }
            }
            item {
                SectionCard(title = "umask — Default Permission Mask") {
                    BodyText("When a file is created, its permissions are determined by the syscall's mode argument minus the umask. umask bits are subtracted (masked out) from the default.")
                    CodeBlock("""Default creation modes (before umask):
  Regular files:   0666  (rw-rw-rw-)
  Directories:     0777  (rwxrwxrwx)

Typical umask = 022:
  0666 & ~022 = 0644  → new files get rw-r--r--
  0777 & ~022 = 0755  → new dirs  get rwxr-xr-x

Stricter umask = 077:
  0666 & ~077 = 0600  → new files get rw-------
  0777 & ~077 = 0700  → new dirs  get rwx------

# View and change umask
umask          # show current value
umask 022      # set in current shell session
umask 027      # group can read but not write; others nothing""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("umask is per-process and inherited by children. Set it in /etc/profile or ~/.bashrc for persistent defaults.")
                }
            }
            item {
                SectionCard(title = "How the Kernel Checks Permissions") {
                    BodyText("When a process tries to open/exec/write a file, the kernel performs a permission check inside may_open() / inode_permission(). The logic is:")
                    CodeBlock("""1. Is the caller's effective UID == 0 (root)?
      → Most permission checks are skipped for root.
        (Exception: execute bit must be set somewhere for root
         to exec a file — root still needs at least one x bit.)

2. Does the caller's effective UID match the file's owner UID?
      → Apply the owner (u) permission bits.

3. Is the caller's effective GID or any supplementary GID
   equal to the file's group GID?
      → Apply the group (g) permission bits.

4. Otherwise:
      → Apply the other (o) permission bits.

The first matching category wins — the check stops there.
If the required bit is set → access granted.
If not → EACCES returned.""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("The relevant kernel structs:")
                    CodeBlock("""struct inode {
    umode_t     i_mode;   // permission bits + file type
    kuid_t      i_uid;    // owner UID
    kgid_t      i_gid;    // owner GID
    ...
};

struct cred {
    kuid_t      uid;      // real UID
    kuid_t      euid;     // effective UID (used for checks)
    kgid_t      gid;
    kgid_t      egid;     // effective GID
    struct group_info *group_info; // supplementary groups
    kernel_cap_t cap_effective;    // POSIX capabilities
    ...
};""")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("After the basic permission check, LSM hooks run (SELinux, AppArmor, etc.) and can further restrict access even if DAC allows it.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("POSIX Capabilities: root's omnipotence is divided into ~40 capabilities (CAP_DAC_OVERRIDE, CAP_FOWNER, CAP_SYS_ADMIN, …). A process can have individual capabilities without being fully root. CAP_DAC_OVERRIDE lets a process bypass DAC checks entirely.")
                    CodeBlock("""# Check a process's capabilities
cat /proc/self/status | grep Cap
capsh --decode=<hex>

# Grant a capability to a binary (without SUID root)
setcap cap_net_bind_service+ep /usr/local/bin/myserver
# Now myserver can bind port 80 without running as root""")
                }
            }
            item {
                SectionCard(title = "Access Control Lists (ACLs)") {
                    BodyText("Standard Unix permissions support only one owner, one group, one other. ACLs (POSIX.1e) allow per-user and per-group permissions on the same file.")
                    CodeBlock("""# Requires filesystem mounted with acl option (ext4/xfs default)

# View ACL
getfacl myfile

# Grant bob read+write access
setfacl -m u:bob:rw myfile

# Grant the 'auditors' group read access
setfacl -m g:auditors:r myfile

# Remove a specific ACL entry
setfacl -x u:bob myfile

# Remove all ACL entries
setfacl -b myfile

# ls shows '+' suffix when ACL entries exist
-rw-r--r--+  1 alice devs  0 Mar 10  myfile""")
                    Spacer(modifier = Modifier.height(4.dp))
                    BodyText("When an ACL is present the kernel checks the ACL entries before falling back to the standard permission bits. The mask entry in the ACL limits the maximum effective permissions for named users and groups.")
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
