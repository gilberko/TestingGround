#pragma once

#include "Common.hpp"
#include "RingBuffer.hpp"

class Adapter {
public:
    Adapter(NDIS_HANDLE miniportHandle);
    ~Adapter();

    NDIS_STATUS Initialize(NDIS_MINIPORT_INIT_PARAMETERS* initParameters);
    void Halt(NDIS_HALT_ACTION haltAction);

    // Data Path
    void SendNetBufferLists(PNET_BUFFER_LIST netBufferLists, NDIS_PORT_NUMBER portNumber, ULONG sendFlags);
    void ReturnNetBufferLists(PNET_BUFFER_LIST netBufferLists, ULONG returnFlags);

    // IOCTL / CDO
    NDIS_STATUS RegisterControlDevice();
    void DeregisterControlDevice();
    NTSTATUS DispatchDeviceControl(PDEVICE_OBJECT deviceObject, PIRP irp);

    // Getters
    NDIS_HANDLE GetMiniportHandle() const { return m_MiniportHandle; }

private:
    NDIS_HANDLE m_MiniportHandle;
    NDIS_HANDLE m_NdisDeviceHandle;
    PDEVICE_OBJECT m_ControlDeviceObject;
    
    RingBuffer m_TxRingBuffer;
    RingBuffer m_RxRingBuffer;

    // CSQ for pended Tx reads
    IO_CSQ m_Csq;
    LIST_ENTRY m_PendingIrpList;
    KSPIN_LOCK m_CsqLock;

    NDIS_HANDLE m_NblPoolHandle;

    // CSQ Callbacks
    static void NTAPI CsqInsertIrp(PIO_CSQ csq, PIRP irp);
    static void NTAPI CsqRemoveIrp(PIO_CSQ csq, PIRP irp);
    static PIRP NTAPI CsqPeekNextIrp(PIO_CSQ csq, PIRP irp, PVOID peekContext);
    static void NTAPI CsqAcquireLock(PIO_CSQ csq, PKIRQL irql);
    static void NTAPI CsqReleaseLock(PIO_CSQ csq, KIRQL irql);
    static void NTAPI CsqCompleteCanceledIrp(PIO_CSQ csq, PIRP irp);

    void ProcessRxPackets();
};
