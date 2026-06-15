#include "Adapter.hpp"

// Forward declarations for NDIS callbacks if needed
extern "C" {
    NTSTATUS MiniportDispatch(PDEVICE_OBJECT DeviceObject, PIRP Irp);
}

Adapter::Adapter(NDIS_HANDLE miniportHandle) 
    : m_MiniportHandle(miniportHandle), m_NdisDeviceHandle(nullptr), 
      m_ControlDeviceObject(nullptr), m_NblPoolHandle(nullptr) {
    
    InitializeListHead(&m_PendingIrpList);
    KeInitializeSpinLock(&m_CsqLock);

    IoCsqInitialize(&m_Csq, CsqInsertIrp, CsqRemoveIrp, CsqPeekNextIrp, 
                   CsqAcquireLock, CsqReleaseLock, CsqCompleteCanceledIrp);
}

Adapter::~Adapter() {
    if (m_NblPoolHandle) {
        NdisFreeNetBufferListPool(m_NblPoolHandle);
    }
}

NDIS_STATUS Adapter::Initialize(NDIS_MINIPORT_INIT_PARAMETERS* initParameters) {
    UNREFERENCED_PARAMETER(initParameters);

    NET_BUFFER_LIST_POOL_PARAMETERS poolParams;
    NdisZeroMemory(&poolParams, sizeof(poolParams));
    poolParams.Header.Type = NDIS_OBJECT_TYPE_DEFAULT;
    poolParams.Header.Revision = NET_BUFFER_LIST_POOL_PARAMETERS_REVISION_1;
    poolParams.Header.Size = sizeof(poolParams);
    poolParams.ProtocolId = NDIS_PROTOCOL_ID_DEFAULT;
    poolParams.ContextSize = 0;
    poolParams.fAllocateNetBuffer = TRUE;
    poolParams.DataSize = 0;
    poolParams.PoolTag = 'PLuT';

    m_NblPoolHandle = NdisAllocateNetBufferListPool(m_MiniportHandle, &poolParams);
    if (!m_NblPoolHandle) return NDIS_STATUS_RESOURCES;

    return RegisterControlDevice();
}

void Adapter::Halt(NDIS_HALT_ACTION haltAction) {
    UNREFERENCED_PARAMETER(haltAction);
    DeregisterControlDevice();
    
    // Cancel all pending IRPs
    PIRP irp;
    while ((irp = IoCsqRemoveNextIrp(&m_Csq, nullptr)) != nullptr) {
        irp->IoStatus.Status = STATUS_CANCELLED;
        irp->IoStatus.Information = 0;
        IoCompleteRequest(irp, IO_NO_INCREMENT);
    }
}

void Adapter::SendNetBufferLists(PNET_BUFFER_LIST netBufferLists, NDIS_PORT_NUMBER portNumber, ULONG sendFlags) {
    UNREFERENCED_PARAMETER(portNumber);
    PNET_BUFFER_LIST currNbl = netBufferLists;

    while (currNbl) {
        PNET_BUFFER currNb = NET_BUFFER_LIST_FIRST_NB(currNbl);
        while (currNb) {
            ULONG dataLength = NET_BUFFER_DATA_LENGTH(currNb);
            if (dataLength <= TUN_MAX_PACKET_SIZE) {
                UCHAR tempBuf[TUN_MAX_PACKET_SIZE];
                PVOID mappedBuf = NdisGetDataBuffer(currNb, dataLength, tempBuf, 1, 0);
                if (mappedBuf) {
                    // Try to complete a pended IRP first
                    PIRP pendedIrp = IoCsqRemoveNextIrp(&m_Csq, nullptr);
                    if (pendedIrp) {
                        TUN_PACKET* userBuf = (TUN_PACKET*)pendedIrp->AssociatedIrp.SystemBuffer;
                        userBuf->Length = (USHORT)dataLength;
                        RtlCopyMemory(userBuf->Data, mappedBuf, dataLength);
                        
                        pendedIrp->IoStatus.Status = STATUS_SUCCESS;
                        pendedIrp->IoStatus.Information = sizeof(TUN_PACKET);
                        IoCompleteRequest(pendedIrp, IO_NETWORK_INCREMENT);
                    } else {
                        // Otherwise, push to ring buffer
                        m_TxRingBuffer.Push((const UCHAR*)mappedBuf, (USHORT)dataLength);
                    }
                }
            }
            currNb = NET_BUFFER_NEXT_NB(currNb);
        }
        currNbl = NET_BUFFER_LIST_NEXT_NBL(currNbl);
    }

    NdisMSendNetBufferListsComplete(m_MiniportHandle, netBufferLists, 
                                   NDIS_TEST_SEND_AT_DISPATCH_LEVEL(sendFlags) ? NDIS_SEND_COMPLETE_FLAGS_DISPATCH_LEVEL : 0);
}

void Adapter::ReturnNetBufferLists(PNET_BUFFER_LIST netBufferLists, ULONG returnFlags) {
    UNREFERENCED_PARAMETER(returnFlags);
    // In our case, we allocate NBLs for Rx, so we free them here.
    PNET_BUFFER_LIST currNbl = netBufferLists;
    while (currNbl) {
        PNET_BUFFER_LIST nextNbl = NET_BUFFER_LIST_NEXT_NBL(currNbl);
        
        // Free MDLs and data if we allocated them manually
        PMDL mdl = NET_BUFFER_FIRST_MDL(NET_BUFFER_LIST_FIRST_NB(currNbl));
        if (mdl) {
            PVOID buffer = MmGetSystemAddressForMdlSafe(mdl, LowPagePriority | MdlMappingNoExecute);
            IoFreeMdl(mdl);
            if (buffer) ExFreePool(buffer);
        }

        NdisFreeNetBufferList(currNbl);
        currNbl = nextNbl;
    }
}

// IOCTL Callbacks
void NTAPI Adapter::CsqInsertIrp(PIO_CSQ csq, PIRP irp) {
    Adapter* adapter = CONTAINING_RECORD(csq, Adapter, m_Csq);
    InsertTailList(&adapter->m_PendingIrpList, &irp->Tail.Overlay.ListEntry);
}

void NTAPI Adapter::CsqRemoveIrp(PIO_CSQ csq, PIRP irp) {
    UNREFERENCED_PARAMETER(csq);
    RemoveEntryList(&irp->Tail.Overlay.ListEntry);
}

PIRP NTAPI Adapter::CsqPeekNextIrp(PIO_CSQ csq, PIRP irp, PVOID peekContext) {
    Adapter* adapter = CONTAINING_RECORD(csq, Adapter, m_Csq);
    PLIST_ENTRY nextEntry;
    PIRP nextIrp = nullptr;

    if (irp == nullptr) {
        nextEntry = adapter->m_PendingIrpList.Flink;
    } else {
        nextEntry = irp->Tail.Overlay.ListEntry.Flink;
    }

    while (nextEntry != &adapter->m_PendingIrpList) {
        nextIrp = CONTAINING_RECORD(nextEntry, IRP, Tail.Overlay.ListEntry);
        if (peekContext == nullptr || nextIrp->Tail.Overlay.DriverContext[0] == peekContext) {
            break;
        }
        nextIrp = nullptr;
        nextEntry = nextEntry->Flink;
    }

    return nextIrp;
}

void NTAPI Adapter::CsqAcquireLock(PIO_CSQ csq, PKIRQL irql) {
    Adapter* adapter = CONTAINING_RECORD(csq, Adapter, m_Csq);
    KeAcquireSpinLock(&adapter->m_CsqLock, irql);
}

void NTAPI Adapter::CsqReleaseLock(PIO_CSQ csq, KIRQL irql) {
    Adapter* adapter = CONTAINING_RECORD(csq, Adapter, m_Csq);
    KeReleaseSpinLock(&adapter->m_CsqLock, irql);
}

void NTAPI Adapter::CsqCompleteCanceledIrp(PIO_CSQ csq, PIRP irp) {
    UNREFERENCED_PARAMETER(csq);
    irp->IoStatus.Status = STATUS_CANCELLED;
    irp->IoStatus.Information = 0;
    IoCompleteRequest(irp, IO_NO_INCREMENT);
}
