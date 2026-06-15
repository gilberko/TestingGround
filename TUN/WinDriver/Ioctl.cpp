#include "Common.hpp"
#include "Adapter.hpp"

// Explicitly include NDIS headers for device registration
#define NDIS_MINIPORT_DRIVER 1
#include <ndis.h>
#include <ntddk.h>

extern "C" {
    NTSTATUS MiniportDispatch(PDEVICE_OBJECT DeviceObject, PIRP Irp);
}

NDIS_STATUS Adapter::RegisterControlDevice() {
    NDIS_STATUS status = NDIS_STATUS_SUCCESS;
    UNICODE_STRING deviceName;
    UNICODE_STRING symbolicName;
    PDRIVER_DISPATCH dispatchTable[IRP_MJ_MAXIMUM_FUNCTION + 1];

    NdisInitUnicodeString(&deviceName, TUN_DEVICE_NAME_STRING);
    NdisInitUnicodeString(&symbolicName, TUN_SYMBOLIC_NAME_STRING);

    NdisZeroMemory(dispatchTable, sizeof(dispatchTable));
    
    // NdisRegisterDeviceEx expects C-style linkage for the dispatch functions
    dispatchTable[IRP_MJ_CREATE] = MiniportDispatch;
    dispatchTable[IRP_MJ_CLOSE] = MiniportDispatch;
    dispatchTable[IRP_MJ_DEVICE_CONTROL] = MiniportDispatch;

    NDIS_DEVICE_OBJECT_ATTRIBUTES deviceAttributes;
    NdisZeroMemory(&deviceAttributes, sizeof(deviceAttributes));
    deviceAttributes.Header.Type = NDIS_OBJECT_TYPE_DEVICE_OBJECT_ATTRIBUTES;
    deviceAttributes.Header.Revision = NDIS_DEVICE_OBJECT_ATTRIBUTES_REVISION_1;
    deviceAttributes.Header.Size = sizeof(deviceAttributes);
    deviceAttributes.DeviceName = &deviceName;
    deviceAttributes.SymbolicName = &symbolicName;
    deviceAttributes.MajorFunctions = dispatchTable;
    deviceAttributes.ExtensionSize = sizeof(Adapter*); // Store 'this' pointer

    // In modern NDIS, NdisRegisterDeviceEx is used instead of NdisMRegisterDeviceEx
    status = NdisRegisterDeviceEx(m_MiniportHandle, &deviceAttributes, &m_ControlDeviceObject, &m_NdisDeviceHandle);
    
    if (status == NDIS_STATUS_SUCCESS) {
        *(Adapter**)m_ControlDeviceObject->DeviceExtension = this;
    }

    return status;
}

void Adapter::DeregisterControlDevice() {
    if (m_NdisDeviceHandle) {
        NdisDeregisterDeviceEx(m_NdisDeviceHandle);
        m_NdisDeviceHandle = nullptr;
        m_ControlDeviceObject = nullptr;
    }
}

NTSTATUS Adapter::DispatchDeviceControl(PDEVICE_OBJECT deviceObject, PIRP irp) {
    UNREFERENCED_PARAMETER(deviceObject);
    PIO_STACK_LOCATION irpStack = IoGetCurrentIrpStackLocation(irp);
    ULONG ioctlCode = irpStack->Parameters.DeviceIoControl.IoControlCode;
    NTSTATUS status = STATUS_SUCCESS;
    ULONG information = 0;

    switch (ioctlCode) {
        case IOCTL_TUN_GET_TX_PACKET: {
            if (irpStack->Parameters.DeviceIoControl.OutputBufferLength < sizeof(TUN_PACKET)) {
                status = STATUS_BUFFER_TOO_SMALL;
            } else {
                TUN_PACKET packet;
                if (m_TxRingBuffer.Pop(&packet)) {
                    RtlCopyMemory(irp->AssociatedIrp.SystemBuffer, &packet, sizeof(TUN_PACKET));
                    information = sizeof(TUN_PACKET);
                    status = STATUS_SUCCESS;
                } else {
                    // Pend the IRP
                    IoCsqInsertIrp(&m_Csq, irp, nullptr);
                    return STATUS_PENDING;
                }
            }
            break;
        }

        case IOCTL_TUN_PUT_RX_PACKET: {
            if (irpStack->Parameters.DeviceIoControl.InputBufferLength < sizeof(TUN_PACKET)) {
                status = STATUS_BUFFER_TOO_SMALL;
            } else {
                TUN_PACKET* packet = (TUN_PACKET*)irp->AssociatedIrp.SystemBuffer;
                if (m_RxRingBuffer.Push(packet->Data, packet->Length)) {
                    status = STATUS_SUCCESS;
                    ProcessRxPackets();
                } else {
                    status = STATUS_INSUFFICIENT_RESOURCES;
                }
            }
            break;
        }

        default:
            status = STATUS_INVALID_DEVICE_REQUEST;
            break;
    }

    irp->IoStatus.Status = status;
    irp->IoStatus.Information = information;
    IoCompleteRequest(irp, IO_NO_INCREMENT);
    return status;
}

void Adapter::ProcessRxPackets() {
    TUN_PACKET packet;
    while (m_RxRingBuffer.Pop(&packet)) {
        PNET_BUFFER_LIST nbl = NdisAllocateNetBufferAndNetBufferList(m_NblPoolHandle, 0, 0, nullptr, 0, 0);
        if (nbl) {
            PVOID buffer = ExAllocatePool2(POOL_FLAG_NON_PAGED, packet.Length, 'RxPT');
            if (buffer) {
                RtlCopyMemory(buffer, packet.Data, packet.Length);
                PMDL mdl = IoAllocateMdl(buffer, packet.Length, FALSE, FALSE, nullptr);
                if (mdl) {
                    MmBuildMdlForNonPagedPool(mdl);
                    NET_BUFFER_FIRST_MDL(NET_BUFFER_LIST_FIRST_NB(nbl)) = mdl;
                    NET_BUFFER_DATA_LENGTH(NET_BUFFER_LIST_FIRST_NB(nbl)) = packet.Length;
                    NET_BUFFER_DATA_OFFSET(NET_BUFFER_LIST_FIRST_NB(nbl)) = 0;

                    NdisMIndicateReceiveNetBufferLists(m_MiniportHandle, nbl, NDIS_DEFAULT_PORT_NUMBER, 1, 0);
                } else {
                    ExFreePool(buffer);
                    NdisFreeNetBufferList(nbl);
                }
            } else {
                NdisFreeNetBufferList(nbl);
            }
        }
    }
}
