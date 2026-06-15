#include "Common.hpp"
#include "RingBuffer.hpp"

void __cdecl operator delete(void* p, size_t size) {
    UNREFERENCED_PARAMETER(size);
    if (p) ExFreePool(p);
}

void __cdecl operator delete(void* p) {
    if (p) ExFreePool(p);
}

RingBuffer::RingBuffer() : m_Head(0), m_Tail(0) {
    KeInitializeSpinLock(&m_Lock);
    m_Buffer = (TUN_PACKET*)ExAllocatePool2(POOL_FLAG_NON_PAGED, sizeof(TUN_PACKET) * Capacity, 'BRuT');
    if (m_Buffer) {
        RtlZeroMemory(m_Buffer, sizeof(TUN_PACKET) * Capacity);
    }
}

RingBuffer::~RingBuffer() {
    if (m_Buffer) {
        ExFreePoolWithTag(m_Buffer, 'BRuT');
    }
}

bool RingBuffer::Push(const UCHAR* data, USHORT length) {
    if (!m_Buffer || length > TUN_MAX_PACKET_SIZE) return false;

    KLOCK_QUEUE_HANDLE lockHandle;
    KeAcquireInStackQueuedSpinLock(&m_Lock, &lockHandle);

    LONG nextTail = (m_Tail + 1) % Capacity;

    // Overwrite logic: if full, move head forward
    if (nextTail == m_Head) {
        m_Head = (m_Head + 1) % Capacity;
    }

    m_Buffer[m_Tail].Length = length;
    RtlCopyMemory(m_Buffer[m_Tail].Data, data, length);
    
    m_Tail = nextTail;

    KeReleaseInStackQueuedSpinLock(&lockHandle);
    return true;
}

bool RingBuffer::Pop(TUN_PACKET* packet) {
    if (!m_Buffer) return false;

    KLOCK_QUEUE_HANDLE lockHandle;
    KeAcquireInStackQueuedSpinLock(&m_Lock, &lockHandle);

    if (m_Head == m_Tail) {
        KeReleaseInStackQueuedSpinLock(&lockHandle);
        return false;
    }

    *packet = m_Buffer[m_Head];
    m_Head = (m_Head + 1) % Capacity;

    KeReleaseInStackQueuedSpinLock(&lockHandle);
    return true;
}

bool RingBuffer::IsEmpty() const {
    return m_Head == m_Tail;
}
