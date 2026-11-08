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
fun InterruptTriggeringScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Interrupt Triggering",
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
                SectionCard(title = "1. What Is an Interrupt?") {
                    BodyText("An INTERRUPT is a signal that makes the CPU stop what it is doing - between two instructions - and jump to a handler. When the handler finishes, the CPU resumes the interrupted code exactly where it left off, as if nothing happened.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Kinds of 'interrupts' on x86-64 (all go through the same IDT, Interrupt Descriptor Table, of 256 vectors):")
                    BodyText("  - HARDWARE interrupts (asynchronous): a device (NIC, disk, timer, keyboard) raises a line or sends an MSI message.\n  - IPIs (Inter-Processor Interrupts): one CPU interrupts another (reschedule, TLB shootdown, function call).\n  - EXCEPTIONS (synchronous, caused by the instruction itself): page fault, divide error, breakpoint (int3), general protection.\n  - NMI: non-maskable interrupt (see below).")
                    CodeBlock(
                        """Device --IRQ line--> IOAPIC --+
Device --MSI write-------------+--> Local APIC (per CPU) --vector N-->
Other CPU --IPI----------------+          CPU core
                                              |
                                  IDTR -> IDT[N] -> handler address"""
                    )
                    BodyText("See all interrupt counts per CPU: cat /proc/interrupts.")
                }
            }
            item {
                SectionCard(title = "2. What Happens When an Interrupt Triggers (x86-64)") {
                    BodyText("HARDWARE part (done by the CPU itself, no software involved):")
                    BodyText("  1. The CPU finishes the current instruction.\n  2. It checks RFLAGS.IF (Interrupt Flag). If IF=0, maskable interrupts wait (stay pending in the APIC).\n  3. It looks up IDT[vector].\n  4. If coming from user mode (ring 3), it switches to the kernel stack (address from the TSS). If the IDT entry specifies an IST slot (NMI, double fault, machine check, debug), it switches to that dedicated stack instead.\n  5. It PUSHES the minimal state needed to resume: SS, RSP, RFLAGS, CS, RIP (plus an error code for some exceptions).\n  6. For an 'interrupt gate' (Linux uses these for device interrupts) it CLEARS IF - so no further maskable interrupts on this CPU.\n  7. Jumps to the handler address.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("SOFTWARE part (Linux entry code, arch/x86/entry/ + kernel/irq/):")
                    CodeBlock(
                        """asm_common_interrupt                (IDT entry stub)
  PUSH_AND_CLEAR_REGS  -> save all GPRs: now a full struct pt_regs
  call common_interrupt(regs, vector)
    irqentry_enter()                  (RCU, context tracking, lockdep)
    switch to per-CPU IRQ stack       (if not already on it)
    irq_enter_rcu()                   preempt_count += HARDIRQ_OFFSET
    handle_irq(desc)
      desc->handle_irq()              flow handler: handle_edge_irq /
                                      handle_fasteoi_irq / ...
        irq_chip->irq_ack/mask        tell controller "got it"
        handle_irq_event()
          action->handler(irq, dev_id)  <-- YOUR driver handler
                                      returns IRQ_HANDLED / IRQ_NONE /
                                      IRQ_WAKE_THREAD
        irq_chip->irq_eoi/unmask      End Of Interrupt to the APIC
    irq_exit_rcu()                    preempt_count -= HARDIRQ_OFFSET
                                      pending softirqs? -> run them now
                                      (IRQs re-enabled) or ksoftirqd
    irqentry_exit()
      to user?   -> exit_to_user_mode_loop(): schedule, signals
      to kernel? -> PREEMPT && preempt_count==0 && need_resched
                    -> preempt_schedule_irq()
  POP_REGS             -> restore all GPRs from pt_regs
  iretq                -> pop RIP, CS, RFLAGS (IF back to 1), RSP, SS"""
                    )
                    BodyText("The driver side is just:")
                    CodeBlock(
                        """static irqreturn_t my_isr(int irq, void *dev_id)
{
    struct my_dev *d = dev_id;
    u32 st = readl(d->regs + STATUS);
    if (!(st & MY_IRQ_PENDING))
        return IRQ_NONE;               /* shared line, not ours */
    writel(st, d->regs + STATUS);      /* ack in the device     */
    return IRQ_WAKE_THREAD;            /* heavy work in thread  */
}

request_threaded_irq(irq, my_isr, my_thread_fn,
                     IRQF_SHARED, "mydev", d);"""
                    )
                }
            }
            item {
                SectionCard(title = "3. Not Getting Another Interrupt While Handling One") {
                    BodyText("Several layers cooperate:")
                    BodyText("  1. CPU level: the interrupt gate cleared IF, so the CPU will not accept any MASKABLE interrupt until iretq restores RFLAGS. Since Linux 2.6.35 ALL hard IRQ handlers run with local interrupts disabled (the old IRQF_DISABLED flag was removed - it became the only behaviour).\n  2. APIC level: while vector N is 'in service' (ISR bit set in the Local APIC), the APIC does not deliver vectors of the same or lower priority class until the handler writes EOI.\n  3. Line level: the irq_chip flow handler masks or acks the specific IRQ line (e.g. handle_level_irq masks it until the handler is done, so a still-asserted level line doesn't re-fire forever).\n  4. Cross-CPU: the generic IRQ layer marks the descriptor IRQD_IRQ_INPROGRESS. If the same IRQ fires on another CPU meanwhile, it is recorded as pending and replayed - the SAME handler never runs concurrently on two CPUs.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Note: the interrupt is not LOST - it stays pending in the APIC / device and is delivered right after IF becomes 1 again. Edge interrupts can coalesce though: two edges while masked may be seen as one.")
                }
            }
            item {
                SectionCard(title = "4. Interrupt Masking") {
                    BodyText("MASKING = temporarily preventing interrupts from being DELIVERED (they stay pending). Two levels:")
                    CodeBlock(
                        """CPU-LOCAL (all maskable interrupts, this CPU only) - clears IF:
local_irq_disable();              /* cli */
local_irq_enable();               /* sti */
local_irq_save(flags);            /* save IF + cli  (nest-safe) */
local_irq_restore(flags);         /* restore previous IF        */
irqs_disabled();                  /* query                      */
spin_lock_irqsave(&l, flags);     /* lock + local_irq_save      */

PER-LINE (one IRQ number, all CPUs) - programs the interrupt controller:
disable_irq(irq);                 /* mask + WAIT for running handlers */
disable_irq_nosync(irq);          /* mask, don't wait (usable in ISR) */
enable_irq(irq);                  /* nested count: must balance       */

SOFTIRQS only (bottom halves, this CPU):
local_bh_disable(); / local_bh_enable();"""
                    )
                    BodyText("Use local_irq_save() rather than disable/enable when you don't know whether interrupts were already off - otherwise you might turn them ON in a context that expected them OFF.")
                }
            }
            item {
                SectionCard(title = "5. Non-Maskable Interrupts (NMI)") {
                    BodyText("Yes - the NMI (vector 2) ignores IF; cli cannot block it. Sources: hardware errors, the perf PMU counter overflow (perf record uses NMIs to sample even code that runs with IRQs off), the hard-lockup watchdog, an 'NMI button' on servers, and NMI IPIs used for 'dump all CPU backtraces' (sysrq-l).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Properties:")
                    BodyText("  - Runs on its own IST stack, so it works even if the kernel stack is broken.\n  - The CPU blocks further NMIs until the next iret. Because an iret from an exception taken INSIDE the NMI handler (e.g. a breakpoint or page fault) would re-enable NMIs early, Linux's x86 NMI entry contains special 'nested NMI' handling that detects and defers a nested NMI.\n  - NMI context is the most restrictive: no locks that a normal context could hold (deadlock - you might have interrupted the holder), no printk directly (it is deferred), only NMI-safe primitives. nmi_enter() bumps the NMI field of preempt_count; in_nmi() tests it.\n  - Machine Check (#MC) behaves similarly: it can arrive even with IF=0.")
                }
            }
            item {
                SectionCard(title = "6. Registers and Stacks: Where Does the Interrupted State Go?") {
                    BodyText("A running thread's state = its CPU registers + its stack. When an interrupt arrives, the handler needs the same registers, so the interrupted values must be saved first:")
                    BodyText("  - The CPU pushes RIP, CS, RFLAGS, RSP, SS (just enough to come back).\n  - The entry code pushes ALL general purpose registers (RAX..R15) right below them.\n  - Together they form a struct pt_regs, saved on the KERNEL STACK of the interrupted task (or the IST stack for NMI/MC/DF/DB).\n  - The handler then switches to the per-CPU HARDIRQ STACK (x86-64: 16 KiB, hardirq_stack_ptr), so deep driver code doesn't overflow the task's small kernel stack.\n  - FPU/SIMD registers are NOT saved on interrupt - that's why kernel code may only use them inside kernel_fpu_begin()/kernel_fpu_end().")
                    CodeBlock(
                        """task's kernel stack (from user mode)        per-CPU IRQ stack
high +------------------+                    +------------------+
     |  SS              |  pushed by CPU      | handle_irq frame |
     |  RSP (user)      |                    | driver ISR frame |
     |  RFLAGS (IF=1)   |                    |   (its locals,   |
     |  CS              |                    |   its saved regs)|
     |  RIP (user)      |                    |       ...        |
     |  orig_ax/vector  |                    +------------------+
     |  RAX..R15        |  pushed by entry
low  +------------------+  code = pt_regs
     (thread_info / task is elsewhere)"""
                    )
                    BodyText("get_irq_regs() returns a pointer to that pt_regs inside a handler (used by profilers and sysrq to see 'where the CPU was').")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("On return the process is reversed: POP_REGS restores the general registers, iretq restores RIP/RSP/RFLAGS/CS/SS - the interrupted code continues with exactly the same register values.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("ARM64 is analogous: an exception switches the exception level, the CPU saves the return address in ELR_EL1 and PSTATE in SPSR_EL1, masks interrupts via PSTATE.DAIF bits, and kernel_entry saves x0-x30 into pt_regs.")
                }
            }
            item {
                SectionCard(title = "7. What Is Atomic Context?") {
                    BodyText("ATOMIC CONTEXT = any state in which the current code MUST NOT SLEEP (must not call schedule()). Either because there is no 'task' that can be put to sleep, or because sleeping would deadlock.")
                    CodeBlock(
                        """You are in atomic context when:
  in a hard IRQ handler            (in_hardirq())
  in a softirq / tasklet / timer   (in_serving_softirq())
  in an NMI                        (in_nmi())
  holding a spinlock               (preemption disabled)
  between preempt_disable/enable
  with local IRQs disabled
  inside rcu_read_lock() on non-preemptible RCU"""
                    )
                    BodyText("Forbidden in atomic context: mutex_lock, down(), msleep, wait_event, kmalloc(GFP_KERNEL) (use GFP_ATOMIC / GFP_NOWAIT), copy_to/from_user, vmalloc, synchronize_rcu, request_firmware...")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Why an IRQ handler can't sleep: it borrowed the CPU from whatever task was running. If it called schedule(), the scheduler would put THAT innocent task to sleep, and nothing would ever wake it for the handler's event. That's what threaded IRQs and workqueues are for: move sleeping work to a real kernel thread.")
                }
            }
            item {
                SectionCard(title = "8. Can Other Interrupts Trigger While a Handler Runs?") {
                    BodyText("On the SAME CPU, during the hard IRQ handler:")
                    BodyText("  - Maskable interrupts: NO - IF is 0. They wait until the handler returns.\n  - NMI and Machine Check: YES - they ignore IF.\n  - Exceptions: YES if the handler causes one (int3 from a kprobe, a page fault on a vmalloc area, a WARN() which uses ud2).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("On OTHER CPUs: yes, completely independently - each CPU has its own IF and its own IRQ stack. (But not the same IRQ line's handler - see section 3.)")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("During SOFTIRQ processing (run at irq_exit), hard interrupts are RE-ENABLED. So a network softirq can be interrupted by a new NIC interrupt - this is the main reason Linux splits work into a short 'top half' (hard IRQ, IRQs off) and a 'bottom half' (softirq/tasklet/workqueue/threaded IRQ, IRQs on).")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Threaded IRQ handlers (irq/NN-name kthreads) run in process context with IRQs enabled - they can be interrupted, preempted and can even sleep.")
                }
            }
            item {
                SectionCard(title = "9. Nested Interrupts: Is There a Stack of Registers?") {
                    BodyText("Yes - exactly that. Every time execution is interrupted, a new frame of saved registers (pt_regs) is pushed on whatever stack is active at that moment (or the IST stack for NMI). Returning pops them in LIFO order, each iret resuming the level below.")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText("Example flow: a user process -> interrupted by a NIC IRQ -> the NIC handler is interrupted by an NMI (perf sample):")
                    CodeBlock(
                        """ user process running                regs = U (user RIP/RSP/...)
   |  NIC IRQ arrives
   v
 [task kernel stack]  pt_regs #1 = U        <- user state saved
   -> switch to IRQ stack, run nic_isr()     regs = I (handler's own)
   |  NMI arrives (ignores IF=0)
   v
 [NMI IST stack]      pt_regs #2 = I        <- handler state saved
   -> run NMI handler (perf)                 regs = N
   |
   |  iretq  (pop #2)  ---> back in nic_isr() with registers = I
   |  nic_isr returns, irq_exit, (softirqs)
   |  iretq  (pop #1)  ---> back in user process with registers = U
   v
 user process continues as if nothing happened"""
                    )
                    BodyText("Key points:")
                    BodyText("  - The interrupt handler's OWN registers are saved the same way as the process's: when it gets interrupted, ITS values go into the next pt_regs frame.\n  - Frames may live on DIFFERENT stacks (task stack, IRQ stack, IST stacks), each pointing back via its saved RSP - logically it is still one LIFO chain.\n  - Depth is bounded: maskable IRQs don't nest on Linux (IF=0), so the typical maximum is task -> IRQ -> NMI/MCE (+ maybe an exception). That's why the IRQ stack can be a fixed 16 KiB.\n  - The kernel unwinder walks these frames to print a stack trace that crosses '<IRQ>' / '<NMI>' / '<TASK>' boundaries - exactly what you see in an oops.")
                    CodeBlock(
                        """Call Trace:
 <NMI>
  perf_event_nmi_handler+0x2d/0x50
  nmi_handle+0x5e/0x120
 </NMI>
 <IRQ>
  e1000_intr+0x41/0x110 [e1000]
  __handle_irq_event_percpu+0x46/0x190
  handle_fasteoi_irq+0x7d/0x1e0
 </IRQ>
 <TASK>
  asm_common_interrupt+0x26/0x40
  ...user/kernel code that was interrupted...
 </TASK>"""
                    )
                }
            }
        }
    }
}
