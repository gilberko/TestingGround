#include <windows.h>
#include <winioctl.h>
#include <iostream>
#include <vector>
#include <thread>
#include <iomanip>

// Copy of relevant parts from Protocol.h to make this standalone for the user app
#define TUN_DEVICE_NAME_STRING     L"\\\\.\\NdisTun"
#define TUN_MAX_PACKET_SIZE        1514

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

void HexDump(const unsigned char* data, size_t size) {
    for (size_t i = 0; i < size; i += 16) {
        std::cout << std::setw(4) << std::setfill('0') << std::hex << i << ": ";
        for (size_t j = 0; j < 16; ++j) {
            if (i + j < size)
                std::cout << std::setw(2) << std::setfill('0') << (int)data[i + j] << " ";
            else
                std::cout << "   ";
        }
        std::cout << " | ";
        for (size_t j = 0; j < 16; ++j) {
            if (i + j < size) {
                char c = data[i + j];
                std::cout << (isprint(c) ? c : '.');
            }
        }
        std::cout << std::endl;
    }
}

void ReaderThread(HANDLE hDevice) {
    OVERLAPPED overlapped = { 0 };
    overlapped.hEvent = CreateEvent(NULL, TRUE, FALSE, NULL);
    
    TUN_PACKET packet;
    DWORD bytesRead;

    std::cout << "[Reader] Starting async read loop..." << std::endl;

    while (true) {
        ResetEvent(overlapped.hEvent);
        
        // This will likely pend in the driver if no data is available
        BOOL result = DeviceIoControl(
            hDevice,
            IOCTL_TUN_GET_TX_PACKET,
            NULL, 0,
            &packet, sizeof(packet),
            &bytesRead,
            &overlapped
        );

        if (!result && GetLastError() == ERROR_IO_PENDING) {
            std::cout << "[Reader] IRP pended, waiting for packet..." << std::endl;
            WaitForSingleObject(overlapped.hEvent, INFINITE);
            GetOverlappedResult(hDevice, &overlapped, &bytesRead, FALSE);
        } else if (!result) {
            std::cerr << "[Reader] DeviceIoControl failed: " << GetLastError() << std::endl;
            break;
        }

        std::cout << "\n[Reader] Received packet (" << packet.Length << " bytes):" << std::endl;
        HexDump(packet.Data, packet.Length);
    }

    CloseHandle(overlapped.hEvent);
}

int main() {
    HANDLE hDevice = CreateFile(
        TUN_DEVICE_NAME_STRING,
        GENERIC_READ | GENERIC_WRITE,
        0,
        NULL,
        OPEN_EXISTING,
        FILE_FLAG_OVERLAPPED,
        NULL
    );

    if (hDevice == INVALID_HANDLE_VALUE) {
        std::cerr << "Failed to open TUN device: " << GetLastError() << std::endl;
        std::cerr << "Make sure the driver is installed and the adapter is enabled." << std::endl;
        return 1;
    }

    std::cout << "Successfully opened TUN device." << std::endl;

    // Start a thread to listen for outgoing packets (Tx from driver perspective)
    std::thread reader(ReaderThread, hDevice);
    reader.detach();

    std::cout << "Press 'i' to inject a sample ICMPv4 Echo Request, 'q' to quit." << std::endl;

    char choice;
    while (std::cin >> choice && choice != 'q') {
        if (choice == 'i') {
            TUN_PACKET inject;
            
            // Dummy ICMP packet (simplified for demonstration)
            // In a real app, you'd construct valid IP/ICMP headers here
            unsigned char dummyData[] = {
                0x45, 0x00, 0x00, 0x1c, // IP: Version 4, Header Length 20, Total Length 28
                0x12, 0x34, 0x00, 0x00, // IP: ID 0x1234, Flags/Offset 0
                0x40, 0x01, 0x00, 0x00, // IP: TTL 64, Protocol ICMP (1), Checksum (invalid)
                0x0a, 0x00, 0x00, 0x01, // IP: Src 10.0.0.1
                0x0a, 0x00, 0x00, 0x02, // IP: Dst 10.0.0.2
                0x08, 0x00, 0xf7, 0xff, // ICMP: Type 8 (Echo), Code 0, Checksum (invalid)
                0x00, 0x01, 0x00, 0x01  // ICMP: ID 1, Seq 1
            };

            inject.Length = sizeof(dummyData);
            memcpy(inject.Data, dummyData, sizeof(dummyData));

            DWORD bytesReturned;
            BOOL result = DeviceIoControl(
                hDevice,
                IOCTL_TUN_PUT_RX_PACKET,
                &inject, sizeof(inject),
                NULL, 0,
                &bytesReturned,
                NULL
            );

            if (result) {
                std::cout << "[Writer] Injected dummy packet." << std::endl;
            } else {
                std::cerr << "[Writer] Failed to inject packet: " << GetLastError() << std::endl;
            }
        }
    }

    CloseHandle(hDevice);
    return 0;
}
