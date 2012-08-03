This package allows porting OpenNFC 4.4.2 to standard or extended version of ICS4.0.4.

The structure of the package is as follows:
ROOT_OF_PACKAGE
├── Demo - ChangePolicy
├── Kernel
│   ├── kernel_for_AOSP
│   │   └── nfc
│   └── kernel_for_NFCRealDevice
│       └── nfc
├── libnfc-opennfc
│   ├── java
│   │   ├── jni
│   │   └── src
│   │       └── com
│   │           └── opennfc
│   │               └── extension
│   │                   └── nfc
│   │                       └── api
│   ├── ndef
│   ├── open_nfc
│   │   ├── hardware
│   │   │   └── libhardware
│   │   │       ├── include
│   │   │       │   └── hardware
│   │   │       └── modules
│   │   │           └── nfcc
│   │   │               ├── nfcc
│   │   │               ├── nfc_hal_microread
│   │   │               │   ├── interfaces
│   │   │               │   ├── porting
│   │   │               │   └── sources
│   │   │               └── nfc_hal_simulator
│   │   │                   ├── interfaces
│   │   │                   ├── porting
│   │   │                   └── sources
│   │   ├── open_nfc
│   │   │   ├── interfaces
│   │   │   ├── porting
│   │   │   │   ├── client
│   │   │   │   │   └── jni
│   │   │   │   │       └── headers
│   │   │   │   ├── common
│   │   │   │   └── server
│   │   │   │       └── jni
│   │   │   └── sources
│   │   └── standalone_server
│   │       └── src
│   ├── open_nfc_extension
│   ├── patches
│   └── test
└── Nfc
    └── jni

- The subfolder "Demo - ChangePolicy" gives an android application, which is based on OpenNFC stack, and allows changing policy of security element, UICC on NFC device.
- The subfolder "Kernel" is used for OpenNFC static kernel compilation (please see section 2.9.3 of the doc MAN_NFC_1205-326 Open NFC - Android ICS 4.0.4 - Porting Guide v0.1.pdf)
- The subfolder "libnfc-opennfc" includes all the necessary OpenNFC source as well as the patches which can be applied for AOSP ICS4.0.4 (please see section 2.7 of the doc MAN_NFC_1205-326 Open NFC - Android ICS 4.0.4 - Porting Guide v0.1.pdf)
- The subfolder "Nfc" includes the jni(c++ part) which connects AOSP java (Nfc) to OpenNFC stack. (please see section 2.7 of the doc MAN_NFC_1205-326 Open NFC - Android ICS 4.0.4 - Porting Guide v0.1.pdf)
- MAN_NFC_1205-326 Open NFC - Android ICS 4.0.4 - Porting Guide v0.1.pdf gives a description of porting OpenNFC4.4.2 to standard or extended version of ICS 4.0.4.
- REN_NFC_1205-325 Open NFC for Android ICS 4.0.4 - Release Notes v0.2.pdf gives a description of release note
- BTP_NFC_1204-322 Open NFC (New Architecture) for Android - Test Plan v0.1.pdf includes CTS, extended CTS results as well as scenario testing of OpenNFC4.4.2.
