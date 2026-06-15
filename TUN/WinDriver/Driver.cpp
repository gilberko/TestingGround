#include "Adapter.hpp"

extern "C" {

NDIS_HANDLE DriverHandle = nullptr;

// NDIS Callbacks
MINIPORT_INITIALIZE MiniportInitialize;
MINIPORT_HALT MiniportHalt;
MINIPORT_PAUSE MiniportPause;
MINIPORT_RESTART MiniportRestart;
MINIPORT_OID_REQUEST MiniportOidRequest;
MINIPORT_SEND_NET_BUFFER_LISTS MiniportSendNetBufferLists;
MINIPORT_RETURN_NET_BUFFER_LISTS MiniportReturnNetBufferLists;
MINIPORT_CANCEL_SEND MiniportCancelSend;
MINIPORT_CHECK_FOR_HANG MiniportCheckForHang;
MINIPORT_RESET MiniportReset;
MINIPORT_DEVICE_PNP_EVENT_NOTIFY MiniportPnpEventNotify;
MINIPORT_SHUTDOWN MiniportShutdown;

DRIVER_INITIALIZE DriverEntry;
DRIVER_UNLOAD DriverUnload;

NDIS_STATUS MiniportInitialize(NDIS_HANDLE MiniportAdapterHandle, NDIS_HANDLE MiniportDriverContext, PNDIS_MINIPORT_INIT_PARAMETERS MiniportInitParameters) {
    UNREFERENCED_PARAMETER(MiniportDriverContext);
    
    Adapter* adapter = new(NonPagedPoolNx, 'AdpT') Adapter(MiniportAdapterHandle);
    if (!adapter) return NDIS_STATUS_RESOURCES;

    NDIS_STATUS status = adapter->Initialize(MiniportInitParameters);
    if (status != NDIS_STATUS_SUCCESS) {
        delete adapter;
        return status;
    }

    NDIS_MINIPORT_ADAPTER_REGISTRATION_ATTRIBUTES registrationAttributes;
    NdisZeroMemory(&registrationAttributes, sizeof(registrationAttributes));
    registrationAttributes.Header.Type = NDIS_OBJECT_TYPE_MINIPORT_ADAPTER_REGISTRATION_ATTRIBUTES;
    registrationAttributes.Header.Revision = NDIS_MINIPORT_ADAPTER_REGISTRATION_ATTRIBUTES_REVISION_1;
    registrationAttributes.Header.Size = sizeof(registrationAttributes);
    registrationAttributes.MiniportAdapterContext = (NDIS_HANDLE)adapter;
    registrationAttributes.AttributeFlags = NDIS_MINIPORT_ATTRIBUTES_SURPRISE_REMOVE_OK | NDIS_MINIPORT_ATTRIBUTES_NDIS_WDM;
    registrationAttributes.CheckForHangTimeInSeconds = 0;
    registrationAttributes.InterfaceType = NdisInterfaceInternal;

    status = NdisMSetMiniportAttributes(MiniportAdapterHandle, (PNDIS_MINIPORT_ADAPTER_ATTRIBUTES)&registrationAttributes);
    
    return status;
}

void MiniportHalt(NDIS_HANDLE MiniportAdapterContext, NDIS_HALT_ACTION HaltAction) {
    Adapter* adapter = (Adapter*)MiniportAdapterContext;
    adapter->Halt(HaltAction);
    delete adapter;
}

void MiniportSendNetBufferLists(NDIS_HANDLE MiniportAdapterContext, PNET_BUFFER_LIST NetBufferLists, NDIS_PORT_NUMBER PortNumber, ULONG SendFlags) {
    Adapter* adapter = (Adapter*)MiniportAdapterContext;
    adapter->SendNetBufferLists(NetBufferLists, PortNumber, SendFlags);
}

void MiniportReturnNetBufferLists(NDIS_HANDLE MiniportAdapterContext, PNET_BUFFER_LIST NetBufferLists, ULONG ReturnFlags) {
    Adapter* adapter = (Adapter*)MiniportAdapterContext;
    adapter->ReturnNetBufferLists(NetBufferLists, ReturnFlags);
}

NDIS_STATUS MiniportPause(NDIS_HANDLE MiniportAdapterContext, PNDIS_MINIPORT_PAUSE_PARAMETERS PauseParameters) {
    UNREFERENCED_PARAMETER(MiniportAdapterContext);
    UNREFERENCED_PARAMETER(PauseParameters);
    return NDIS_STATUS_SUCCESS;
}

NDIS_STATUS MiniportRestart(NDIS_HANDLE MiniportAdapterContext, PNDIS_MINIPORT_RESTART_PARAMETERS RestartParameters) {
    UNREFERENCED_PARAMETER(MiniportAdapterContext);
    UNREFERENCED_PARAMETER(RestartParameters);
    return NDIS_STATUS_SUCCESS;
}

NDIS_STATUS MiniportOidRequest(NDIS_HANDLE MiniportAdapterContext, PNDIS_OID_REQUEST OidRequest) {
    UNREFERENCED_PARAMETER(MiniportAdapterContext);
    
    // For a real driver, we must handle mandatory OIDs here.
    // Returning NOT_SUPPORTED allows NDIS to handle some default behaviors
    // or report that the miniport doesn't support the specific query.
    return NDIS_STATUS_NOT_SUPPORTED; 
}

void MiniportCancelSend(NDIS_HANDLE MiniportAdapterContext, PVOID CancelId) {
    UNREFERENCED_PARAMETER(MiniportAdapterContext);
    UNREFERENCED_PARAMETER(CancelId);
}

BOOLEAN MiniportCheckForHang(NDIS_HANDLE MiniportAdapterContext) {
    UNREFERENCED_PARAMETER(MiniportAdapterContext);
    return FALSE;
}

NDIS_STATUS MiniportReset(NDIS_HANDLE MiniportAdapterContext, PBOOLEAN AddressingReset) {
    UNREFERENCED_PARAMETER(MiniportAdapterContext);
    *AddressingReset = FALSE;
    return NDIS_STATUS_SUCCESS;
}

void MiniportPnpEventNotify(NDIS_HANDLE MiniportAdapterContext, PNET_DEVICE_PNP_EVENT NetDevicePnPEvent) {
    UNREFERENCED_PARAMETER(MiniportAdapterContext);
    UNREFERENCED_PARAMETER(NetDevicePnPEvent);
}

void MiniportShutdown(NDIS_HANDLE MiniportAdapterContext, NDIS_SHUTDOWN_ACTION ShutdownAction) {
    UNREFERENCED_PARAMETER(MiniportAdapterContext);
    UNREFERENCED_PARAMETER(ShutdownAction);
}

NTSTATUS MiniportDispatch(PDEVICE_OBJECT DeviceObject, PIRP Irp) {
    PIO_STACK_LOCATION irpStack = IoGetCurrentIrpStackLocation(Irp);
    Adapter* adapter = *(Adapter**)DeviceObject->DeviceExtension;
    NTSTATUS status = STATUS_SUCCESS;

    if (irpStack->MajorFunction == IRP_MJ_CREATE || irpStack->MajorFunction == IRP_MJ_CLOSE) {
        Irp->IoStatus.Status = STATUS_SUCCESS;
        Irp->IoStatus.Information = 0;
        IoCompleteRequest(Irp, IO_NO_INCREMENT);
        return STATUS_SUCCESS;
    } else if (irpStack->MajorFunction == IRP_MJ_DEVICE_CONTROL) {
        return adapter->DispatchDeviceControl(DeviceObject, Irp);
    }

    Irp->IoStatus.Status = STATUS_INVALID_DEVICE_REQUEST;
    Irp->IoStatus.Information = 0;
    IoCompleteRequest(Irp, IO_NO_INCREMENT);
    return STATUS_INVALID_DEVICE_REQUEST;
}

NTSTATUS DriverEntry(PDRIVER_OBJECT DriverObject, PUNICODE_STRING RegistryPath) {
    NDIS_MINIPORT_DRIVER_CHARACTERISTICS characteristics;
    NdisZeroMemory(&characteristics, sizeof(characteristics));

    characteristics.Header.Type = NDIS_OBJECT_TYPE_MINIPORT_DRIVER_CHARACTERISTICS;
    characteristics.Header.Revision = NDIS_MINIPORT_DRIVER_CHARACTERISTICS_REVISION_2;
    characteristics.Header.Size = sizeof(characteristics);

    characteristics.MajorNdisVersion = 6;
    characteristics.MinorNdisVersion = 30;

    characteristics.InitializeHandlerEx = MiniportInitialize;
    characteristics.HaltHandlerEx = MiniportHalt;
    characteristics.PauseHandler = MiniportPause;
    characteristics.RestartHandler = MiniportRestart;
    characteristics.OidRequestHandler = MiniportOidRequest;
    characteristics.SendNetBufferListsHandler = MiniportSendNetBufferLists;
    characteristics.ReturnNetBufferListsHandler = MiniportReturnNetBufferLists;
    characteristics.CancelSendHandler = MiniportCancelSend;
    characteristics.CheckForHangHandlerEx = MiniportCheckForHang;
    characteristics.ResetHandlerEx = MiniportReset;
    characteristics.DevicePnPEventNotifyHandler = MiniportPnpEventNotify;
    characteristics.ShutdownHandlerEx = MiniportShutdown;

    return NdisMRegisterMiniportDriver(DriverObject, RegistryPath, nullptr, &characteristics, &DriverHandle);
}

void DriverUnload(PDRIVER_OBJECT DriverObject) {
    UNREFERENCED_PARAMETER(DriverObject);
    if (DriverHandle) {
        NdisMDeregisterMiniportDriver(DriverHandle);
    }
}

} // extern "C"
