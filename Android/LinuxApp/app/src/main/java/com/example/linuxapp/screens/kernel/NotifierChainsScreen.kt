package com.example.linuxapp.screens.kernel

import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotifierChainsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Notifier Chains",
                        color = Color(0xFF00FF41),
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
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
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "What Are Notifier Chains") {
                    BodyText("A notifier chain is the kernel's publish/subscribe mechanism. It lets one subsystem broadcast events to any number of listeners without knowing who they are. Listeners register a callback; the publisher calls the chain and every registered callback fires in priority order.")
                    BodyText("Key properties:")
                    BodyText("• Decoupled — the publisher does not import or call the listeners directly\n• Dynamic — listeners register and unregister at runtime\n• Ordered — callbacks fire from highest priority to lowest (same priority: LIFO order of registration)\n• Composable — returning NOTIFY_STOP from a callback aborts the remaining calls")
                    BodyText("Notifier chains are used throughout the kernel to signal events such as network interface state changes, CPU hotplug, system shutdown, power management transitions, and memory pressure. A kernel module can subscribe to any of these chains and also define its own.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "The Four Chain Types") {
                    BodyText("The type determines locking and execution context:")
                    CodeBlock("""
Type          Head struct                  Locking         Context          Sleep?
─────────────────────────────────────────────────────────────────────────────────
Atomic        atomic_notifier_head         RCU + spinlock  Any (IRQ safe)   No
Blocking      blocking_notifier_head       rwsem           Process only     Yes
Raw           raw_notifier_head            None            Caller decides   Caller
SRCU          srcu_notifier_head           SRCU            Process only     Yes""".trimIndent())
                    BodyText("Atomic — fastest; safe from interrupt handlers and atomic context; no sleeping callbacks allowed. The internal spinlock is per-chain; RCU protects read-side traversal.")
                    BodyText("Blocking — the most common choice for module-defined chains. An rwsem guards the list. Callbacks run in process context and may sleep, allocate memory with GFP_KERNEL, or acquire mutexes.")
                    BodyText("Raw — no locking at all. The caller is responsible for providing mutual exclusion. Useful when you already hold a lock that covers the chain, or in very hot paths where you control concurrency precisely.")
                    BodyText("SRCU — like Blocking but uses Sleepable RCU instead of rwsem. Allows parallel read-side execution (multiple notifications can run simultaneously) while still allowing callbacks to sleep. Requires explicit cleanup with srcu_cleanup_notifier_head().")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "struct notifier_block") {
                    BodyText("Every subscriber defines one struct notifier_block:")
                    CodeBlock("""
struct notifier_block {
    notifier_fn_t  notifier_call; /* your callback function        */
    struct notifier_block *next;  /* internal linked list — don't touch */
    int            priority;      /* higher = called first; default 0   */
};

typedef int (*notifier_fn_t)(struct notifier_block *nb,
                              unsigned long action,
                              void *data);""".trimIndent())
                    BodyText("notifier_call — your callback. nb is the notifier_block itself (use container_of if you embedded it in a larger struct to recover private data). action is the event type (defined by the chain owner). data is an optional event-specific pointer (cast to the appropriate struct).")
                    BodyText("priority — integers; higher values are called first. Most subscribers use 0 (the default). Values like INT_MAX are reserved for special users (e.g., ELAM-like watchers that must run first).")
                    BodyText("Callback return values:")
                    CodeBlock("""
NOTIFY_OK      /* handled; continue calling remaining notifiers  */
NOTIFY_DONE    /* not interested in this event; continue         */
NOTIFY_BAD     /* error occurred; continue (advisory)            */
NOTIFY_STOP    /* stop — do NOT call remaining notifiers         */
               /* (use when you know no one else should handle it) */""".trimIndent())
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Subscribing to Existing Kernel Chains") {
                    BodyText("The kernel exports many ready-to-use chains. You register with a single function call and must unregister in module_exit.")
                    BodyText("Common examples:")
                    CodeBlock("""
/* Network device events (NETDEV_UP/DOWN/REGISTER/UNREGISTER/…) */
register_netdevice_notifier(struct notifier_block *nb);
unregister_netdevice_notifier(struct notifier_block *nb);

/* System reboot/halt/power-off */
register_reboot_notifier(struct notifier_block *nb);
unregister_reboot_notifier(struct notifier_block *nb);

/* IPv4 address add/remove */
register_inetaddr_notifier(struct notifier_block *nb);
unregister_inetaddr_notifier(struct notifier_block *nb);

/* Power management (suspend/resume) */
register_pm_notifier(struct notifier_block *nb);
unregister_pm_notifier(struct notifier_block *nb);""".trimIndent())
                    BodyText("Full example — subscribe to network device events:")
                    CodeBlock("""
#include <linux/module.h>
#include <linux/netdevice.h>

static int my_netdev_event(struct notifier_block *nb,
                            unsigned long event, void *ptr)
{
    struct net_device *dev = netdev_notifier_info_to_dev(ptr);

    switch (event) {
    case NETDEV_UP:
        pr_info("mymod: %s came UP\n", dev->name);
        break;
    case NETDEV_DOWN:
        pr_info("mymod: %s went DOWN\n", dev->name);
        break;
    case NETDEV_REGISTER:
        pr_info("mymod: %s registered\n", dev->name);
        break;
    default:
        break;
    }
    return NOTIFY_OK;
}

static struct notifier_block my_nb = {
    .notifier_call = my_netdev_event,
    .priority      = 0,
};

static int __init mymod_init(void)
{
    return register_netdevice_notifier(&my_nb);
}

static void __exit mymod_exit(void)
{
    unregister_netdevice_notifier(&my_nb);
}

module_init(mymod_init);
module_exit(mymod_exit);
MODULE_LICENSE("GPL");""".trimIndent())
                    BodyText("The netdevice chain is Blocking — callbacks run in process context and may sleep. The data pointer is a struct netdev_notifier_info *; netdev_notifier_info_to_dev() extracts the net_device from it.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Creating Your Own Chain") {
                    BodyText("Declare the chain head. For a module-owned chain this is typically a static variable:")
                    CodeBlock("""
#include <linux/notifier.h>

/* Static declaration — Blocking (most common for modules) */
static BLOCKING_NOTIFIER_HEAD(my_event_chain);

/* Static declaration — Atomic */
static ATOMIC_NOTIFIER_HEAD(my_event_chain);

/* Dynamic initialization (e.g., inside a struct) */
struct my_device {
    struct atomic_notifier_head events;
    /* … */
};
ATOMIC_INIT_NOTIFIER_HEAD(&mydev->events);

/* SRCU — requires explicit init AND cleanup */
struct srcu_notifier_head my_srcu_chain;
srcu_init_notifier_head(&my_srcu_chain);   /* in module_init */
srcu_cleanup_notifier_head(&my_srcu_chain); /* in module_exit */""".trimIndent())
                    BodyText("Provide register/unregister functions for subscribers. The API per type:")
                    CodeBlock("""
/* Blocking */
blocking_notifier_chain_register(struct blocking_notifier_head *,
                                  struct notifier_block *);
blocking_notifier_chain_unregister(struct blocking_notifier_head *,
                                    struct notifier_block *);

/* Atomic */
atomic_notifier_chain_register(struct atomic_notifier_head *,
                                struct notifier_block *);
atomic_notifier_chain_unregister(struct atomic_notifier_head *,
                                  struct notifier_block *);

/* Raw */
raw_notifier_chain_register(struct raw_notifier_head *,
                              struct notifier_block *);
raw_notifier_chain_unregister(struct raw_notifier_head *,
                               struct notifier_block *);

/* SRCU */
srcu_notifier_chain_register(struct srcu_notifier_head *,
                               struct notifier_block *);
srcu_notifier_chain_unregister(struct srcu_notifier_head *,
                                struct notifier_block *);""".trimIndent())
                    BodyText("Fire all registered callbacks (notify):")
                    CodeBlock("""
/* Blocking */
int blocking_notifier_call_chain(struct blocking_notifier_head *nh,
                                  unsigned long val, void *v);

/* Atomic */
int atomic_notifier_call_chain(struct atomic_notifier_head *nh,
                                unsigned long val, void *v);

/* Raw */
int raw_notifier_call_chain(struct raw_notifier_head *nh,
                             unsigned long val, void *v);

/* SRCU */
int srcu_notifier_call_chain(struct srcu_notifier_head *nh,
                               unsigned long val, void *v);""".trimIndent())
                    BodyText("val — an unsigned long you define as event codes (e.g., MY_EVENT_UP = 1, MY_EVENT_DOWN = 2). v — an optional pointer to event-specific data; pass NULL if unused. The return value is the OR of all NOTIFY_* return codes from callbacks (use notifier_to_errno() to convert NOTIFY_BAD to an errno).")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Exporting Your Chain to Other Modules") {
                    BodyText("Best practice: never EXPORT_SYMBOL the raw notifier head. If other modules can access the head directly they can call *_notifier_call_chain() themselves, bypassing any validation logic you add later. They can also corrupt the internal list structure.")
                    BodyText("Instead, export wrapper functions that own the head internally:")
                    CodeBlock("""
/* myevents.h — shared header for owner and all subscribers */
#ifndef MY_EVENTS_H
#define MY_EVENTS_H
#include <linux/notifier.h>

/* Event codes */
#define MY_EVENT_STARTED  1
#define MY_EVENT_STOPPED  2
#define MY_EVENT_ERROR    3

int  my_events_register(struct notifier_block *nb);
int  my_events_unregister(struct notifier_block *nb);
#endif""".trimIndent())
                    CodeBlock("""
/* In owner.c */
#include "myevents.h"

static BLOCKING_NOTIFIER_HEAD(my_event_chain);

int my_events_register(struct notifier_block *nb)
{
    return blocking_notifier_chain_register(&my_event_chain, nb);
}
EXPORT_SYMBOL(my_events_register);

int my_events_unregister(struct notifier_block *nb)
{
    return blocking_notifier_chain_unregister(&my_event_chain, nb);
}
EXPORT_SYMBOL(my_events_unregister);

/* Called internally to fire the chain */
static void notify_event(unsigned long event, void *data)
{
    blocking_notifier_call_chain(&my_event_chain, event, data);
}""".trimIndent())
                    BodyText("Other modules include myevents.h and call my_events_register(). At insmod time the kernel resolves my_events_register from the live symbol table — so the owner module must be loaded before the subscriber (same rule as any EXPORT_SYMBOL dependency). Use EXPORT_SYMBOL_GPL instead of EXPORT_SYMBOL to restrict use to GPL-licensed modules.")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "rmmod Safety: Can You Remove Your Module While Others Are Registered?") {
                    BodyText("NO automatic protection. The kernel does NOT increment the module refcount when another module registers to your chain. If you rmmod the owner while a subscriber is still registered:")
                    BodyText("• The notifier_head (in the owner's .bss) is freed\n• If the subscriber later calls my_events_unregister() → use-after-free → kernel panic\n• If the owner's chain fires while the head memory is gone → crash")
                    BodyText("The correct solution: use try_module_get(THIS_MODULE) in your register wrapper. This increments the owner's refcount for every active subscriber. rmmod will then return -EBUSY until all subscribers call unregister:")
                    CodeBlock("""
int my_events_register(struct notifier_block *nb)
{
    /* Grab a reference to THIS module.
       If the module is being unloaded, try_module_get returns false. */
    if (!try_module_get(THIS_MODULE))
        return -ENODEV;

    return blocking_notifier_chain_register(&my_event_chain, nb);
}
EXPORT_SYMBOL(my_events_register);

int my_events_unregister(struct notifier_block *nb)
{
    int ret = blocking_notifier_chain_unregister(&my_event_chain, nb);
    /* Release the reference taken in register */
    module_put(THIS_MODULE);
    return ret;
}
EXPORT_SYMBOL(my_events_unregister);""".trimIndent())
                    BodyText("With this pattern: each call to my_events_register increments the owner's use count by 1; each my_events_unregister decrements it. As long as any subscriber is registered, the owner module's refcount is > 0 and rmmod returns -EBUSY. Only when the last subscriber unregisters can the owner be removed.")
                    CodeBlock("""
$ insmod owner.ko
$ insmod subscriber.ko     # calls my_events_register → owner refcnt = 1

$ rmmod owner.ko
rmmod: ERROR: Module owner is in use   ← blocked (refcnt = 1)

$ rmmod subscriber.ko      # calls my_events_unregister → owner refcnt = 0
$ rmmod owner.ko           ← succeeds now""".trimIndent())
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Forcefully Disconnecting Notifiers") {
                    BodyText("There is no kernel built-in API to force-remove all registered notifiers from a chain at once. The linked list is internal to the notifier subsystem; direct manipulation bypasses its locking.")
                    BodyText("Disconnecting a specific notifier: call the unregister function directly. No special API needed — this is always safe:")
                    CodeBlock("""
/* Remove one specific subscriber from within the owner module */
my_events_unregister(&target_nb);
/* or the low-level variant: */
blocking_notifier_chain_unregister(&my_event_chain, &target_nb);""".trimIndent())
                    BodyText("For a force-flush-all scenario (e.g., emergency shutdown), you can walk the chain manually. For a Blocking chain (protected by rwsem internally), the safest approach is to atomically replace the head pointer:")
                    CodeBlock("""
/* Force-clear a blocking notifier chain — use sparingly */
static void flush_blocking_chain(struct blocking_notifier_head *nh)
{
    struct notifier_block *nb;

    down_write(&nh->rwsem);
    nb = nh->head;
    nh->head = NULL;      /* detach the entire list atomically */
    up_write(&nh->rwsem);

    /* At this point no new notifications reach old subscribers.
       Walk the detached list to call module_put for each one
       (only if you used try_module_get in register). */
    while (nb) {
        struct notifier_block *next = nb->next;
        module_put(/* owner of nb — requires a wrapper struct */);
        nb = next;
    }
}""".trimIndent())
                    BodyText("To associate a module pointer with each registered notifier_block (so you can call module_put during flush), wrap notifier_block in a larger struct:")
                    CodeBlock("""
struct my_subscriber_entry {
    struct notifier_block nb;
    struct module         *owner;  /* module that registered */
};

int my_events_register(struct notifier_block *nb)
{
    struct my_subscriber_entry *entry =
        container_of(nb, struct my_subscriber_entry, nb);
    /* or allocate a wrapper and copy nb into it */
    entry->owner = nb->owner;  /* caller sets nb->owner = THIS_MODULE */
    if (!try_module_get(THIS_MODULE)) return -ENODEV;
    return blocking_notifier_chain_register(&my_event_chain, nb);
}""".trimIndent())
                    BodyText("In practice, force-flushing is a design smell. The recommended patterns are:\n\n1. try_module_get in register — prevents the owner from unloading, so the flush problem never arises\n2. Provide an emergency_detach callback in a wrapper struct — the owner calls it during shutdown; each subscriber removes itself voluntarily\n3. EXPORT_SYMBOL_GPL — limits subscribers to GPL modules; doesn't solve ordering but narrows the space\n4. WARN_ON + pr_err in module_exit if the chain is non-empty — documents the invariant violation clearly")
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SectionCard(title = "Full End-to-End Example") {
                    BodyText("Two modules: owner.c defines the chain and fires events; subscriber.c registers a callback. The owner uses try_module_get for safe unloading.")
                    CodeBlock("""
/* ── myevents.h ─────────────────────────────────────────── */
#ifndef MY_EVENTS_H
#define MY_EVENTS_H
#include <linux/notifier.h>
#define MY_EVENT_STARTED  1
#define MY_EVENT_STOPPED  2
int my_events_register(struct notifier_block *nb);
int my_events_unregister(struct notifier_block *nb);
#endif""".trimIndent())
                    CodeBlock("""
/* ── owner.c ────────────────────────────────────────────── */
#include <linux/module.h>
#include <linux/init.h>
#include "myevents.h"

static BLOCKING_NOTIFIER_HEAD(my_event_chain);

int my_events_register(struct notifier_block *nb)
{
    if (!try_module_get(THIS_MODULE))
        return -ENODEV;
    return blocking_notifier_chain_register(&my_event_chain, nb);
}
EXPORT_SYMBOL(my_events_register);

int my_events_unregister(struct notifier_block *nb)
{
    int ret = blocking_notifier_chain_unregister(&my_event_chain, nb);
    module_put(THIS_MODULE);
    return ret;
}
EXPORT_SYMBOL(my_events_unregister);

static int __init owner_init(void)
{
    pr_info("owner: loaded, firing MY_EVENT_STARTED\n");
    blocking_notifier_call_chain(&my_event_chain,
                                  MY_EVENT_STARTED, NULL);
    return 0;
}

static void __exit owner_exit(void)
{
    pr_info("owner: unloading, firing MY_EVENT_STOPPED\n");
    blocking_notifier_call_chain(&my_event_chain,
                                  MY_EVENT_STOPPED, NULL);
    /* Chain must be empty here — guaranteed by try_module_get */
}

module_init(owner_init);
module_exit(owner_exit);
MODULE_LICENSE("GPL");""".trimIndent())
                    CodeBlock("""
/* ── subscriber.c ───────────────────────────────────────── */
#include <linux/module.h>
#include <linux/init.h>
#include "myevents.h"

static int my_handler(struct notifier_block *nb,
                       unsigned long event, void *data)
{
    switch (event) {
    case MY_EVENT_STARTED:
        pr_info("subscriber: owner started!\n");
        break;
    case MY_EVENT_STOPPED:
        pr_info("subscriber: owner stopped!\n");
        break;
    default:
        return NOTIFY_DONE;
    }
    return NOTIFY_OK;
}

static struct notifier_block my_nb = {
    .notifier_call = my_handler,
    .priority      = 0,
};

static int __init subscriber_init(void)
{
    int ret = my_events_register(&my_nb);
    if (ret)
        pr_err("subscriber: register failed: %d\n", ret);
    else
        pr_info("subscriber: registered\n");
    return ret;
}

static void __exit subscriber_exit(void)
{
    my_events_unregister(&my_nb);
    pr_info("subscriber: unregistered\n");
}

module_init(subscriber_init);
module_exit(subscriber_exit);
MODULE_LICENSE("GPL");""".trimIndent())
                    CodeBlock("""
# Makefile
obj-m += owner.o subscriber.o

# Load order:
insmod owner.ko          # fires MY_EVENT_STARTED (no subscribers yet)
insmod subscriber.ko     # registers; owner refcnt = 1

# Try removing owner while subscriber is loaded:
rmmod owner.ko           # -EBUSY (refcnt = 1)

# Correct teardown:
rmmod subscriber.ko      # unregisters; owner refcnt = 0
rmmod owner.ko           # succeeds; fires MY_EVENT_STOPPED (no listeners)""".trimIndent())
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}
