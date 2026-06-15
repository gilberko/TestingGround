#pragma once

#include <ntddk.h>
#include <ndis.h>

#define TUN_DEVICE_NAME_STRING     L"\\Device\\NdisTunDevice"
#define TUN_SYMBOLIC_NAME_STRING   L"\\DosDevices\\NdisTun"

#define TUN_MAX_PACKET_SIZE        1514
#define TUN_RING_BUFFER_SIZE       50

// IOCTL Codes
#define IOCTL_TUN_GET_TX_PACKET \
    CTL_CODE(FILE_DEVICE_NETWORK, 0x801, METHOD_BUFFERED, FILE_ANY_ACCESS)

#define IOCTL_TUN_PUT_RX_PACKET \
    CTL_CODE(FILE_DEVICE_NETWORK, 0x802, METHOD_BUFFERED, FILE_ANY_ACCESS)

#pragma pack(push, 1)
struct TUN_PACKET {
    USHORT Length;
    UCHAR Data[TUN_MAX_PACKET_SIZE];
};
#pragma pack(pop)
