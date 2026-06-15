#pragma once

#include "Protocol.h"

class RingBuffer {
public:
    RingBuffer();
    ~RingBuffer();

    bool Push(const UCHAR* data, USHORT length);
    bool Pop(TUN_PACKET* packet);
    bool IsEmpty() const;

private:
    TUN_PACKET* m_Buffer;
    LONG m_Head; // Oldest data
    LONG m_Tail; // Next write position
    mutable KSPIN_LOCK m_Lock;

    static const LONG Capacity = TUN_RING_BUFFER_SIZE;
};
