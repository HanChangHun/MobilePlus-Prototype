package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ResultReceiver;
import java.util.List;

public abstract class Stub extends Binder implements IBluetooth {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetooth";
  
  static final int TRANSACTION_cancelBondProcess = 26;
  
  static final int TRANSACTION_cancelDiscovery = 19;
  
  static final int TRANSACTION_connectAllEnabledProfiles = 74;
  
  static final int TRANSACTION_createBond = 25;
  
  static final int TRANSACTION_disable = 3;
  
  static final int TRANSACTION_disconnectAllEnabledProfiles = 75;
  
  static final int TRANSACTION_enable = 2;
  
  static final int TRANSACTION_factoryReset = 56;
  
  static final int TRANSACTION_fetchRemoteUuids = 38;
  
  static final int TRANSACTION_getAdapterConnectionState = 22;
  
  static final int TRANSACTION_getAddress = 4;
  
  static final int TRANSACTION_getBatteryLevel = 40;
  
  static final int TRANSACTION_getBluetoothClass = 8;
  
  static final int TRANSACTION_getBondState = 28;
  
  static final int TRANSACTION_getBondedDevices = 24;
  
  static final int TRANSACTION_getConnectionState = 31;
  
  static final int TRANSACTION_getDiscoverableTimeout = 16;
  
  static final int TRANSACTION_getDiscoveryEndMillis = 21;
  
  static final int TRANSACTION_getIoCapability = 10;
  
  static final int TRANSACTION_getLeIoCapability = 12;
  
  static final int TRANSACTION_getLeMaximumAdvertisingDataLength = 65;
  
  static final int TRANSACTION_getMaxConnectedAudioDevices = 41;
  
  static final int TRANSACTION_getMessageAccessPermission = 49;
  
  static final int TRANSACTION_getMetadata = 70;
  
  static final int TRANSACTION_getMostRecentlyConnectedDevices = 77;
  
  static final int TRANSACTION_getName = 7;
  
  static final int TRANSACTION_getPhonebookAccessPermission = 45;
  
  static final int TRANSACTION_getProfileConnectionState = 23;
  
  static final int TRANSACTION_getRemoteAlias = 34;
  
  static final int TRANSACTION_getRemoteClass = 36;
  
  static final int TRANSACTION_getRemoteName = 32;
  
  static final int TRANSACTION_getRemoteType = 33;
  
  static final int TRANSACTION_getRemoteUuids = 37;
  
  static final int TRANSACTION_getScanMode = 14;
  
  static final int TRANSACTION_getSilenceMode = 47;
  
  static final int TRANSACTION_getSimAccessPermission = 51;
  
  static final int TRANSACTION_getSocketManager = 55;
  
  static final int TRANSACTION_getState = 1;
  
  static final int TRANSACTION_getSupportedProfiles = 30;
  
  static final int TRANSACTION_getUuids = 5;
  
  static final int TRANSACTION_isActivityAndEnergyReportingSupported = 60;
  
  static final int TRANSACTION_isBondingInitiatedLocally = 29;
  
  static final int TRANSACTION_isDiscovering = 20;
  
  static final int TRANSACTION_isLe2MPhySupported = 61;
  
  static final int TRANSACTION_isLeCodedPhySupported = 62;
  
  static final int TRANSACTION_isLeExtendedAdvertisingSupported = 63;
  
  static final int TRANSACTION_isLePeriodicAdvertisingSupported = 64;
  
  static final int TRANSACTION_isMultiAdvertisementSupported = 57;
  
  static final int TRANSACTION_isOffloadedFilteringSupported = 58;
  
  static final int TRANSACTION_isOffloadedScanBatchingSupported = 59;
  
  static final int TRANSACTION_onBrEdrDown = 73;
  
  static final int TRANSACTION_onLeServiceUp = 72;
  
  static final int TRANSACTION_registerCallback = 53;
  
  static final int TRANSACTION_registerMetadataListener = 67;
  
  static final int TRANSACTION_removeActiveDevice = 78;
  
  static final int TRANSACTION_removeBond = 27;
  
  static final int TRANSACTION_reportActivityInfo = 66;
  
  static final int TRANSACTION_requestActivityInfo = 71;
  
  static final int TRANSACTION_sdpSearch = 39;
  
  static final int TRANSACTION_setActiveDevice = 76;
  
  static final int TRANSACTION_setBluetoothClass = 9;
  
  static final int TRANSACTION_setDiscoverableTimeout = 17;
  
  static final int TRANSACTION_setIoCapability = 11;
  
  static final int TRANSACTION_setLeIoCapability = 13;
  
  static final int TRANSACTION_setMessageAccessPermission = 50;
  
  static final int TRANSACTION_setMetadata = 69;
  
  static final int TRANSACTION_setName = 6;
  
  static final int TRANSACTION_setPairingConfirmation = 44;
  
  static final int TRANSACTION_setPasskey = 43;
  
  static final int TRANSACTION_setPhonebookAccessPermission = 48;
  
  static final int TRANSACTION_setPin = 42;
  
  static final int TRANSACTION_setRemoteAlias = 35;
  
  static final int TRANSACTION_setScanMode = 15;
  
  static final int TRANSACTION_setSilenceMode = 46;
  
  static final int TRANSACTION_setSimAccessPermission = 52;
  
  static final int TRANSACTION_startDiscovery = 18;
  
  static final int TRANSACTION_unregisterCallback = 54;
  
  static final int TRANSACTION_unregisterMetadataListener = 68;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetooth");
  }
  
  public static IBluetooth asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetooth");
    return (iInterface != null && iInterface instanceof IBluetooth) ? (IBluetooth)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetooth getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 78:
        return "removeActiveDevice";
      case 77:
        return "getMostRecentlyConnectedDevices";
      case 76:
        return "setActiveDevice";
      case 75:
        return "disconnectAllEnabledProfiles";
      case 74:
        return "connectAllEnabledProfiles";
      case 73:
        return "onBrEdrDown";
      case 72:
        return "onLeServiceUp";
      case 71:
        return "requestActivityInfo";
      case 70:
        return "getMetadata";
      case 69:
        return "setMetadata";
      case 68:
        return "unregisterMetadataListener";
      case 67:
        return "registerMetadataListener";
      case 66:
        return "reportActivityInfo";
      case 65:
        return "getLeMaximumAdvertisingDataLength";
      case 64:
        return "isLePeriodicAdvertisingSupported";
      case 63:
        return "isLeExtendedAdvertisingSupported";
      case 62:
        return "isLeCodedPhySupported";
      case 61:
        return "isLe2MPhySupported";
      case 60:
        return "isActivityAndEnergyReportingSupported";
      case 59:
        return "isOffloadedScanBatchingSupported";
      case 58:
        return "isOffloadedFilteringSupported";
      case 57:
        return "isMultiAdvertisementSupported";
      case 56:
        return "factoryReset";
      case 55:
        return "getSocketManager";
      case 54:
        return "unregisterCallback";
      case 53:
        return "registerCallback";
      case 52:
        return "setSimAccessPermission";
      case 51:
        return "getSimAccessPermission";
      case 50:
        return "setMessageAccessPermission";
      case 49:
        return "getMessageAccessPermission";
      case 48:
        return "setPhonebookAccessPermission";
      case 47:
        return "getSilenceMode";
      case 46:
        return "setSilenceMode";
      case 45:
        return "getPhonebookAccessPermission";
      case 44:
        return "setPairingConfirmation";
      case 43:
        return "setPasskey";
      case 42:
        return "setPin";
      case 41:
        return "getMaxConnectedAudioDevices";
      case 40:
        return "getBatteryLevel";
      case 39:
        return "sdpSearch";
      case 38:
        return "fetchRemoteUuids";
      case 37:
        return "getRemoteUuids";
      case 36:
        return "getRemoteClass";
      case 35:
        return "setRemoteAlias";
      case 34:
        return "getRemoteAlias";
      case 33:
        return "getRemoteType";
      case 32:
        return "getRemoteName";
      case 31:
        return "getConnectionState";
      case 30:
        return "getSupportedProfiles";
      case 29:
        return "isBondingInitiatedLocally";
      case 28:
        return "getBondState";
      case 27:
        return "removeBond";
      case 26:
        return "cancelBondProcess";
      case 25:
        return "createBond";
      case 24:
        return "getBondedDevices";
      case 23:
        return "getProfileConnectionState";
      case 22:
        return "getAdapterConnectionState";
      case 21:
        return "getDiscoveryEndMillis";
      case 20:
        return "isDiscovering";
      case 19:
        return "cancelDiscovery";
      case 18:
        return "startDiscovery";
      case 17:
        return "setDiscoverableTimeout";
      case 16:
        return "getDiscoverableTimeout";
      case 15:
        return "setScanMode";
      case 14:
        return "getScanMode";
      case 13:
        return "setLeIoCapability";
      case 12:
        return "getLeIoCapability";
      case 11:
        return "setIoCapability";
      case 10:
        return "getIoCapability";
      case 9:
        return "setBluetoothClass";
      case 8:
        return "getBluetoothClass";
      case 7:
        return "getName";
      case 6:
        return "setName";
      case 5:
        return "getUuids";
      case 4:
        return "getAddress";
      case 3:
        return "disable";
      case 2:
        return "enable";
      case 1:
        break;
    } 
    return "getState";
  }
  
  public static boolean setDefaultImpl(IBluetooth paramIBluetooth) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetooth != null) {
        Proxy.sDefaultImpl = paramIBluetooth;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1598968902) {
      boolean bool15;
      int i10;
      boolean bool14;
      int i9;
      boolean bool13;
      int i8;
      boolean bool12;
      int i7;
      boolean bool11;
      int i6;
      boolean bool10;
      int i5;
      boolean bool9;
      int i4;
      boolean bool8;
      int i3;
      boolean bool7;
      int i2;
      boolean bool6;
      int i1;
      boolean bool5;
      int n;
      boolean bool4;
      int m;
      boolean bool3;
      int k;
      boolean bool2;
      int j;
      boolean bool1;
      List<BluetoothDevice> list;
      byte[] arrayOfByte;
      BluetoothActivityEnergyInfo bluetoothActivityEnergyInfo;
      IBluetoothSocketManager iBluetoothSocketManager;
      ParcelUuid[] arrayOfParcelUuid2;
      String str3;
      BluetoothDevice[] arrayOfBluetoothDevice;
      BluetoothClass bluetoothClass;
      String str2;
      ParcelUuid[] arrayOfParcelUuid1;
      String str1;
      BluetoothDevice bluetoothDevice;
      IBluetoothMetadataListener iBluetoothMetadataListener;
      long l;
      boolean bool16 = false;
      boolean bool17 = false;
      boolean bool18 = false;
      boolean bool19 = false;
      boolean bool20 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 78:
          paramParcel1.enforceInterface("android.bluetooth.IBluetooth");
          bool15 = removeActiveDevice(paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool15);
          return true;
        case 77:
          paramParcel1.enforceInterface("android.bluetooth.IBluetooth");
          list = getMostRecentlyConnectedDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedList(list);
          return true;
        case 76:
          list.enforceInterface("android.bluetooth.IBluetooth");
          if (list.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            bluetoothDevice = null;
          } 
          bool15 = setActiveDevice(bluetoothDevice, list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool15);
          return true;
        case 75:
          list.enforceInterface("android.bluetooth.IBluetooth");
          if (list.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          bool15 = disconnectAllEnabledProfiles((BluetoothDevice)list);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool15);
          return true;
        case 74:
          list.enforceInterface("android.bluetooth.IBluetooth");
          if (list.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          bool15 = connectAllEnabledProfiles((BluetoothDevice)list);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool15);
          return true;
        case 73:
          list.enforceInterface("android.bluetooth.IBluetooth");
          onBrEdrDown();
          paramParcel2.writeNoException();
          return true;
        case 72:
          list.enforceInterface("android.bluetooth.IBluetooth");
          onLeServiceUp();
          paramParcel2.writeNoException();
          return true;
        case 71:
          list.enforceInterface("android.bluetooth.IBluetooth");
          if (list.readInt() != 0) {
            ResultReceiver resultReceiver = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel((Parcel)list);
          } else {
            list = null;
          } 
          requestActivityInfo((ResultReceiver)list);
          return true;
        case 70:
          list.enforceInterface("android.bluetooth.IBluetooth");
          if (list.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)list);
          } else {
            bluetoothDevice = null;
          } 
          arrayOfByte = getMetadata(bluetoothDevice, list.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeByteArray(arrayOfByte);
          return true;
        case 69:
          arrayOfByte.enforceInterface("android.bluetooth.IBluetooth");
          if (arrayOfByte.readInt() != 0) {
            bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)arrayOfByte);
          } else {
            bluetoothDevice = null;
          } 
          bool15 = setMetadata(bluetoothDevice, arrayOfByte.readInt(), arrayOfByte.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool15);
          return true;
        case 68:
          arrayOfByte.enforceInterface("android.bluetooth.IBluetooth");
          if (arrayOfByte.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)arrayOfByte);
          } else {
            arrayOfByte = null;
          } 
          bool15 = unregisterMetadataListener((BluetoothDevice)arrayOfByte);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool15);
          return true;
        case 67:
          arrayOfByte.enforceInterface("android.bluetooth.IBluetooth");
          iBluetoothMetadataListener = IBluetoothMetadataListener.Stub.asInterface(arrayOfByte.readStrongBinder());
          if (arrayOfByte.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)arrayOfByte);
          } else {
            arrayOfByte = null;
          } 
          bool15 = registerMetadataListener(iBluetoothMetadataListener, (BluetoothDevice)arrayOfByte);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool15);
          return true;
        case 66:
          arrayOfByte.enforceInterface("android.bluetooth.IBluetooth");
          bluetoothActivityEnergyInfo = reportActivityInfo();
          paramParcel2.writeNoException();
          if (bluetoothActivityEnergyInfo != null) {
            paramParcel2.writeInt(1);
            bluetoothActivityEnergyInfo.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 65:
          bluetoothActivityEnergyInfo.enforceInterface("android.bluetooth.IBluetooth");
          i10 = getLeMaximumAdvertisingDataLength();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i10);
          return true;
        case 64:
          bluetoothActivityEnergyInfo.enforceInterface("android.bluetooth.IBluetooth");
          bool14 = isLePeriodicAdvertisingSupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 63:
          bluetoothActivityEnergyInfo.enforceInterface("android.bluetooth.IBluetooth");
          bool14 = isLeExtendedAdvertisingSupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 62:
          bluetoothActivityEnergyInfo.enforceInterface("android.bluetooth.IBluetooth");
          bool14 = isLeCodedPhySupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 61:
          bluetoothActivityEnergyInfo.enforceInterface("android.bluetooth.IBluetooth");
          bool14 = isLe2MPhySupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 60:
          bluetoothActivityEnergyInfo.enforceInterface("android.bluetooth.IBluetooth");
          bool14 = isActivityAndEnergyReportingSupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 59:
          bluetoothActivityEnergyInfo.enforceInterface("android.bluetooth.IBluetooth");
          bool14 = isOffloadedScanBatchingSupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 58:
          bluetoothActivityEnergyInfo.enforceInterface("android.bluetooth.IBluetooth");
          bool14 = isOffloadedFilteringSupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 57:
          bluetoothActivityEnergyInfo.enforceInterface("android.bluetooth.IBluetooth");
          bool14 = isMultiAdvertisementSupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 56:
          bluetoothActivityEnergyInfo.enforceInterface("android.bluetooth.IBluetooth");
          bool14 = factoryReset();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 55:
          bluetoothActivityEnergyInfo.enforceInterface("android.bluetooth.IBluetooth");
          iBluetoothSocketManager = getSocketManager();
          paramParcel2.writeNoException();
          if (iBluetoothSocketManager != null) {
            IBinder iBinder = iBluetoothSocketManager.asBinder();
          } else {
            iBluetoothSocketManager = null;
          } 
          paramParcel2.writeStrongBinder((IBinder)iBluetoothSocketManager);
          return true;
        case 54:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          unregisterCallback(IBluetoothCallback.Stub.asInterface(iBluetoothSocketManager.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 53:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          registerCallback(IBluetoothCallback.Stub.asInterface(iBluetoothSocketManager.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 52:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothMetadataListener = null;
          } 
          bool14 = setSimAccessPermission((BluetoothDevice)iBluetoothMetadataListener, iBluetoothSocketManager.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool14);
          return true;
        case 51:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothSocketManager = null;
          } 
          i9 = getSimAccessPermission((BluetoothDevice)iBluetoothSocketManager);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i9);
          return true;
        case 50:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothMetadataListener = null;
          } 
          bool13 = setMessageAccessPermission((BluetoothDevice)iBluetoothMetadataListener, iBluetoothSocketManager.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool13);
          return true;
        case 49:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothSocketManager = null;
          } 
          i8 = getMessageAccessPermission((BluetoothDevice)iBluetoothSocketManager);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i8);
          return true;
        case 48:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothMetadataListener = null;
          } 
          bool12 = setPhonebookAccessPermission((BluetoothDevice)iBluetoothMetadataListener, iBluetoothSocketManager.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool12);
          return true;
        case 47:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothSocketManager = null;
          } 
          bool12 = getSilenceMode((BluetoothDevice)iBluetoothSocketManager);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool12);
          return true;
        case 46:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothMetadataListener = null;
          } 
          if (iBluetoothSocketManager.readInt() != 0)
            bool20 = true; 
          bool12 = setSilenceMode((BluetoothDevice)iBluetoothMetadataListener, bool20);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool12);
          return true;
        case 45:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothSocketManager = null;
          } 
          i7 = getPhonebookAccessPermission((BluetoothDevice)iBluetoothSocketManager);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i7);
          return true;
        case 44:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothMetadataListener = null;
          } 
          bool20 = bool16;
          if (iBluetoothSocketManager.readInt() != 0)
            bool20 = true; 
          bool11 = setPairingConfirmation((BluetoothDevice)iBluetoothMetadataListener, bool20);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 43:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothMetadataListener = null;
          } 
          bool20 = bool17;
          if (iBluetoothSocketManager.readInt() != 0)
            bool20 = true; 
          bool11 = setPasskey((BluetoothDevice)iBluetoothMetadataListener, bool20, iBluetoothSocketManager.readInt(), iBluetoothSocketManager.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 42:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothMetadataListener = null;
          } 
          bool20 = bool18;
          if (iBluetoothSocketManager.readInt() != 0)
            bool20 = true; 
          bool11 = setPin((BluetoothDevice)iBluetoothMetadataListener, bool20, iBluetoothSocketManager.readInt(), iBluetoothSocketManager.createByteArray());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool11);
          return true;
        case 41:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          i6 = getMaxConnectedAudioDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i6);
          return true;
        case 40:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothSocketManager = null;
          } 
          i6 = getBatteryLevel((BluetoothDevice)iBluetoothSocketManager);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i6);
          return true;
        case 39:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothMetadataListener = null;
          } 
          if (iBluetoothSocketManager.readInt() != 0) {
            ParcelUuid parcelUuid = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothSocketManager = null;
          } 
          bool10 = sdpSearch((BluetoothDevice)iBluetoothMetadataListener, (ParcelUuid)iBluetoothSocketManager);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 38:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothSocketManager = null;
          } 
          bool10 = fetchRemoteUuids((BluetoothDevice)iBluetoothSocketManager);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool10);
          return true;
        case 37:
          iBluetoothSocketManager.enforceInterface("android.bluetooth.IBluetooth");
          if (iBluetoothSocketManager.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)iBluetoothSocketManager);
          } else {
            iBluetoothSocketManager = null;
          } 
          arrayOfParcelUuid2 = getRemoteUuids((BluetoothDevice)iBluetoothSocketManager);
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfParcelUuid2, 1);
          return true;
        case 36:
          arrayOfParcelUuid2.enforceInterface("android.bluetooth.IBluetooth");
          if (arrayOfParcelUuid2.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)arrayOfParcelUuid2);
          } else {
            arrayOfParcelUuid2 = null;
          } 
          i5 = getRemoteClass((BluetoothDevice)arrayOfParcelUuid2);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i5);
          return true;
        case 35:
          arrayOfParcelUuid2.enforceInterface("android.bluetooth.IBluetooth");
          if (arrayOfParcelUuid2.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)arrayOfParcelUuid2);
          } else {
            iBluetoothMetadataListener = null;
          } 
          bool9 = setRemoteAlias((BluetoothDevice)iBluetoothMetadataListener, arrayOfParcelUuid2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool9);
          return true;
        case 34:
          arrayOfParcelUuid2.enforceInterface("android.bluetooth.IBluetooth");
          if (arrayOfParcelUuid2.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)arrayOfParcelUuid2);
          } else {
            arrayOfParcelUuid2 = null;
          } 
          str3 = getRemoteAlias((BluetoothDevice)arrayOfParcelUuid2);
          paramParcel2.writeNoException();
          paramParcel2.writeString(str3);
          return true;
        case 33:
          str3.enforceInterface("android.bluetooth.IBluetooth");
          if (str3.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)str3);
          } else {
            str3 = null;
          } 
          i4 = getRemoteType((BluetoothDevice)str3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i4);
          return true;
        case 32:
          str3.enforceInterface("android.bluetooth.IBluetooth");
          if (str3.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)str3);
          } else {
            str3 = null;
          } 
          str3 = getRemoteName((BluetoothDevice)str3);
          paramParcel2.writeNoException();
          paramParcel2.writeString(str3);
          return true;
        case 31:
          str3.enforceInterface("android.bluetooth.IBluetooth");
          if (str3.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)str3);
          } else {
            str3 = null;
          } 
          i4 = getConnectionState((BluetoothDevice)str3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i4);
          return true;
        case 30:
          str3.enforceInterface("android.bluetooth.IBluetooth");
          l = getSupportedProfiles();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 29:
          str3.enforceInterface("android.bluetooth.IBluetooth");
          if (str3.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)str3);
          } else {
            str3 = null;
          } 
          bool8 = isBondingInitiatedLocally((BluetoothDevice)str3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool8);
          return true;
        case 28:
          str3.enforceInterface("android.bluetooth.IBluetooth");
          if (str3.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)str3);
          } else {
            str3 = null;
          } 
          i3 = getBondState((BluetoothDevice)str3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i3);
          return true;
        case 27:
          str3.enforceInterface("android.bluetooth.IBluetooth");
          if (str3.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)str3);
          } else {
            str3 = null;
          } 
          bool7 = removeBond((BluetoothDevice)str3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
          return true;
        case 26:
          str3.enforceInterface("android.bluetooth.IBluetooth");
          if (str3.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)str3);
          } else {
            str3 = null;
          } 
          bool7 = cancelBondProcess((BluetoothDevice)str3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool7);
          return true;
        case 25:
          str3.enforceInterface("android.bluetooth.IBluetooth");
          if (str3.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)str3);
          } else {
            iBluetoothMetadataListener = null;
          } 
          i2 = str3.readInt();
          if (str3.readInt() != 0) {
            OobData oobData = (OobData)OobData.CREATOR.createFromParcel((Parcel)str3);
          } else {
            str3 = null;
          } 
          bool6 = createBond((BluetoothDevice)iBluetoothMetadataListener, i2, (OobData)str3);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool6);
          return true;
        case 24:
          str3.enforceInterface("android.bluetooth.IBluetooth");
          arrayOfBluetoothDevice = getBondedDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfBluetoothDevice, 1);
          return true;
        case 23:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          i1 = getProfileConnectionState(arrayOfBluetoothDevice.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          return true;
        case 22:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          i1 = getAdapterConnectionState();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          return true;
        case 21:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          l = getDiscoveryEndMillis();
          paramParcel2.writeNoException();
          paramParcel2.writeLong(l);
          return true;
        case 20:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          bool5 = isDiscovering();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool5);
          return true;
        case 19:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          bool5 = cancelDiscovery();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool5);
          return true;
        case 18:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          bool5 = startDiscovery(arrayOfBluetoothDevice.readString(), arrayOfBluetoothDevice.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool5);
          return true;
        case 17:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          bool5 = setDiscoverableTimeout(arrayOfBluetoothDevice.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool5);
          return true;
        case 16:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          n = getDiscoverableTimeout();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(n);
          return true;
        case 15:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          bool4 = setScanMode(arrayOfBluetoothDevice.readInt(), arrayOfBluetoothDevice.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool4);
          return true;
        case 14:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          m = getScanMode();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(m);
          return true;
        case 13:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          bool3 = setLeIoCapability(arrayOfBluetoothDevice.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool3);
          return true;
        case 12:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          k = getLeIoCapability();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(k);
          return true;
        case 11:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          bool2 = setIoCapability(arrayOfBluetoothDevice.readInt());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool2);
          return true;
        case 10:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          j = getIoCapability();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(j);
          return true;
        case 9:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          if (arrayOfBluetoothDevice.readInt() != 0) {
            BluetoothClass bluetoothClass1 = (BluetoothClass)BluetoothClass.CREATOR.createFromParcel((Parcel)arrayOfBluetoothDevice);
          } else {
            arrayOfBluetoothDevice = null;
          } 
          bool1 = setBluetoothClass((BluetoothClass)arrayOfBluetoothDevice);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 8:
          arrayOfBluetoothDevice.enforceInterface("android.bluetooth.IBluetooth");
          bluetoothClass = getBluetoothClass();
          paramParcel2.writeNoException();
          if (bluetoothClass != null) {
            paramParcel2.writeInt(1);
            bluetoothClass.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        case 7:
          bluetoothClass.enforceInterface("android.bluetooth.IBluetooth");
          str2 = getName();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          return true;
        case 6:
          str2.enforceInterface("android.bluetooth.IBluetooth");
          bool1 = setName(str2.readString());
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 5:
          str2.enforceInterface("android.bluetooth.IBluetooth");
          arrayOfParcelUuid1 = getUuids();
          paramParcel2.writeNoException();
          paramParcel2.writeTypedArray((Parcelable[])arrayOfParcelUuid1, 1);
          return true;
        case 4:
          arrayOfParcelUuid1.enforceInterface("android.bluetooth.IBluetooth");
          str1 = getAddress();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          return true;
        case 3:
          str1.enforceInterface("android.bluetooth.IBluetooth");
          bool1 = disable();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 2:
          str1.enforceInterface("android.bluetooth.IBluetooth");
          bool20 = bool19;
          if (str1.readInt() != 0)
            bool20 = true; 
          bool1 = enable(bool20);
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool1);
          return true;
        case 1:
          break;
      } 
      str1.enforceInterface("android.bluetooth.IBluetooth");
      int i = getState();
      paramParcel2.writeNoException();
      paramParcel2.writeInt(i);
      return true;
    } 
    paramParcel2.writeString("android.bluetooth.IBluetooth");
    return true;
  }
  
  private static class Proxy implements IBluetooth {
    public static IBluetooth sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public boolean cancelBondProcess(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().cancelBondProcess(param2BluetoothDevice);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean cancelDiscovery() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(19, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().cancelDiscovery();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean connectAllEnabledProfiles(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().connectAllEnabledProfiles(param2BluetoothDevice);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean createBond(BluetoothDevice param2BluetoothDevice, int param2Int, OobData param2OobData) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (param2OobData != null) {
          parcel1.writeInt(1);
          param2OobData.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().createBond(param2BluetoothDevice, param2Int, param2OobData);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean disable() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(3, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().disable();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean disconnectAllEnabledProfiles(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().disconnectAllEnabledProfiles(param2BluetoothDevice);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean enable(boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          param2Boolean = IBluetooth.Stub.getDefaultImpl().enable(param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean factoryReset() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(56, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().factoryReset();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean fetchRemoteUuids(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().fetchRemoteUuids(param2BluetoothDevice);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getAdapterConnectionState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getAdapterConnectionState(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getAddress() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getAddress(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getBatteryLevel(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getBatteryLevel(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public BluetoothClass getBluetoothClass() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        BluetoothClass bluetoothClass;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bluetoothClass = IBluetooth.Stub.getDefaultImpl().getBluetoothClass();
          return bluetoothClass;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          bluetoothClass = (BluetoothClass)BluetoothClass.CREATOR.createFromParcel(parcel2);
        } else {
          bluetoothClass = null;
        } 
        return bluetoothClass;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getBondState(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getBondState(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public BluetoothDevice[] getBondedDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getBondedDevices(); 
        parcel2.readException();
        return (BluetoothDevice[])parcel2.createTypedArray(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getConnectionState(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getConnectionState(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getDiscoverableTimeout() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getDiscoverableTimeout(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getDiscoveryEndMillis() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getDiscoveryEndMillis(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetooth";
    }
    
    public int getIoCapability() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getIoCapability(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getLeIoCapability() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getLeIoCapability(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getLeMaximumAdvertisingDataLength() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getLeMaximumAdvertisingDataLength(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getMaxConnectedAudioDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getMaxConnectedAudioDevices(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getMessageAccessPermission(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getMessageAccessPermission(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public byte[] getMetadata(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getMetadata(param2BluetoothDevice, param2Int); 
        parcel2.readException();
        return parcel2.createByteArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothDevice> getMostRecentlyConnectedDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(77, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getMostRecentlyConnectedDevices(); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getName() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getName(); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getPhonebookAccessPermission(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getPhonebookAccessPermission(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getProfileConnectionState(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          param2Int = IBluetooth.Stub.getDefaultImpl().getProfileConnectionState(param2Int);
          return param2Int;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        return param2Int;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getRemoteAlias(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getRemoteAlias(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getRemoteClass(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getRemoteClass(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getRemoteName(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getRemoteName(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readString();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getRemoteType(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getRemoteType(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelUuid[] getRemoteUuids(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getRemoteUuids(param2BluetoothDevice); 
        parcel2.readException();
        return (ParcelUuid[])parcel2.createTypedArray(ParcelUuid.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getScanMode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getScanMode(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean getSilenceMode(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().getSilenceMode(param2BluetoothDevice);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getSimAccessPermission(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getSimAccessPermission(param2BluetoothDevice); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBluetoothSocketManager getSocketManager() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getSocketManager(); 
        parcel2.readException();
        return IBluetoothSocketManager.Stub.asInterface(parcel2.readStrongBinder());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getState(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public long getSupportedProfiles() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getSupportedProfiles(); 
        parcel2.readException();
        return parcel2.readLong();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelUuid[] getUuids() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
          return IBluetooth.Stub.getDefaultImpl().getUuids(); 
        parcel2.readException();
        return (ParcelUuid[])parcel2.createTypedArray(ParcelUuid.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isActivityAndEnergyReportingSupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(60, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().isActivityAndEnergyReportingSupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isBondingInitiatedLocally(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().isBondingInitiatedLocally(param2BluetoothDevice);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isDiscovering() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(20, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().isDiscovering();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isLe2MPhySupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(61, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().isLe2MPhySupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isLeCodedPhySupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(62, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().isLeCodedPhySupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isLeExtendedAdvertisingSupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(63, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().isLeExtendedAdvertisingSupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isLePeriodicAdvertisingSupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(64, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().isLePeriodicAdvertisingSupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isMultiAdvertisementSupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(57, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().isMultiAdvertisementSupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isOffloadedFilteringSupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(58, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().isOffloadedFilteringSupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isOffloadedScanBatchingSupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(59, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().isOffloadedScanBatchingSupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onBrEdrDown() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          IBluetooth.Stub.getDefaultImpl().onBrEdrDown();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void onLeServiceUp() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(72, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          IBluetooth.Stub.getDefaultImpl().onLeServiceUp();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerCallback(IBluetoothCallback param2IBluetoothCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2IBluetoothCallback != null) {
          iBinder = param2IBluetoothCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          IBluetooth.Stub.getDefaultImpl().registerCallback(param2IBluetoothCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean registerMetadataListener(IBluetoothMetadataListener param2IBluetoothMetadataListener, BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2IBluetoothMetadataListener != null) {
          iBinder = param2IBluetoothMetadataListener.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().registerMetadataListener(param2IBluetoothMetadataListener, param2BluetoothDevice);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeActiveDevice(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(78, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().removeActiveDevice(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean removeBond(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().removeBond(param2BluetoothDevice);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public BluetoothActivityEnergyInfo reportActivityInfo() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        BluetoothActivityEnergyInfo bluetoothActivityEnergyInfo;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (!this.mRemote.transact(66, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bluetoothActivityEnergyInfo = IBluetooth.Stub.getDefaultImpl().reportActivityInfo();
          return bluetoothActivityEnergyInfo;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          bluetoothActivityEnergyInfo = (BluetoothActivityEnergyInfo)BluetoothActivityEnergyInfo.CREATOR.createFromParcel(parcel2);
        } else {
          bluetoothActivityEnergyInfo = null;
        } 
        return bluetoothActivityEnergyInfo;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestActivityInfo(ResultReceiver param2ResultReceiver) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2ResultReceiver != null) {
          parcel.writeInt(1);
          param2ResultReceiver.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(71, parcel, null, 1) && IBluetooth.Stub.getDefaultImpl() != null) {
          IBluetooth.Stub.getDefaultImpl().requestActivityInfo(param2ResultReceiver);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public boolean sdpSearch(BluetoothDevice param2BluetoothDevice, ParcelUuid param2ParcelUuid) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ParcelUuid != null) {
          parcel1.writeInt(1);
          param2ParcelUuid.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().sdpSearch(param2BluetoothDevice, param2ParcelUuid);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setActiveDevice(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setActiveDevice(param2BluetoothDevice, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setBluetoothClass(BluetoothClass param2BluetoothClass) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothClass != null) {
          parcel1.writeInt(1);
          param2BluetoothClass.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setBluetoothClass(param2BluetoothClass);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setDiscoverableTimeout(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(17, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setDiscoverableTimeout(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setIoCapability(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(11, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setIoCapability(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setLeIoCapability(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        parcel1.writeInt(param2Int);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(13, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setLeIoCapability(param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setMessageAccessPermission(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(50, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setMessageAccessPermission(param2BluetoothDevice, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setMetadata(BluetoothDevice param2BluetoothDevice, int param2Int, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setMetadata(param2BluetoothDevice, param2Int, param2ArrayOfbyte);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setName(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(6, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setName(param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPairingConfirmation(BluetoothDevice param2BluetoothDevice, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          param2Boolean = IBluetooth.Stub.getDefaultImpl().setPairingConfirmation(param2BluetoothDevice, param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPasskey(BluetoothDevice param2BluetoothDevice, boolean param2Boolean, int param2Int, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool1 = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeInt(param2Int);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          param2Boolean = IBluetooth.Stub.getDefaultImpl().setPasskey(param2BluetoothDevice, param2Boolean, param2Int, param2ArrayOfbyte);
          return param2Boolean;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0) {
          param2Boolean = bool1;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPhonebookAccessPermission(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setPhonebookAccessPermission(param2BluetoothDevice, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setPin(BluetoothDevice param2BluetoothDevice, boolean param2Boolean, int param2Int, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool2;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool1 = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        parcel1.writeInt(bool2);
        parcel1.writeInt(param2Int);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          param2Boolean = IBluetooth.Stub.getDefaultImpl().setPin(param2BluetoothDevice, param2Boolean, param2Int, param2ArrayOfbyte);
          return param2Boolean;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0) {
          param2Boolean = bool1;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setRemoteAlias(BluetoothDevice param2BluetoothDevice, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setRemoteAlias(param2BluetoothDevice, param2String);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setScanMode(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(15, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setScanMode(param2Int1, param2Int2);
          return bool;
        } 
        parcel2.readException();
        param2Int1 = parcel2.readInt();
        if (param2Int1 != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setSilenceMode(BluetoothDevice param2BluetoothDevice, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2Boolean) {
          i = 1;
        } else {
          i = 0;
        } 
        parcel1.writeInt(i);
        if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          param2Boolean = IBluetooth.Stub.getDefaultImpl().setSilenceMode(param2BluetoothDevice, param2Boolean);
          return param2Boolean;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0) {
          param2Boolean = bool;
        } else {
          param2Boolean = false;
        } 
        return param2Boolean;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean setSimAccessPermission(BluetoothDevice param2BluetoothDevice, int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().setSimAccessPermission(param2BluetoothDevice, param2Int);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean startDiscovery(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(18, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().startDiscovery(param2String1, param2String2);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterCallback(IBluetoothCallback param2IBluetoothCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        if (param2IBluetoothCallback != null) {
          iBinder = param2IBluetoothCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          IBluetooth.Stub.getDefaultImpl().unregisterCallback(param2IBluetoothCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean unregisterMetadataListener(BluetoothDevice param2BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
        boolean bool = true;
        if (param2BluetoothDevice != null) {
          parcel1.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
          bool = IBluetooth.Stub.getDefaultImpl().unregisterMetadataListener(param2BluetoothDevice);
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i == 0)
          bool = false; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetooth$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */