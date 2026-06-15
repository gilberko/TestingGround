#pragma once

#include <ntddk.h>
#include <ndis.h>

// Modern Pool Allocation (Windows 10 2004+)
inline void* __cdecl operator new(size_t size, POOL_TYPE pool, ULONG tag = 'C++T') {
    return ExAllocatePool2(pool, size, tag);
}

// Global operator delete cannot be inline in all C++ versions
void __cdecl operator delete(void* p, size_t size);
void __cdecl operator delete(void* p);
