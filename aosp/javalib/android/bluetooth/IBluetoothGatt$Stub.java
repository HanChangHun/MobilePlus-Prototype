package android.bluetooth;

import android.app.PendingIntent;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertisingSetParameters;
import android.bluetooth.le.IAdvertisingSetCallback;
import android.bluetooth.le.IPeriodicAdvertisingCallback;
import android.bluetooth.le.IScannerCallback;
import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.WorkSource;
import java.util.List;

public abstract class Stub extends Binder implements IBluetoothGatt {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothGatt";
  
  static final int TRANSACTION_addService = 48;
  
  static final int TRANSACTION_beginReliableWrite = 36;
  
  static final int TRANSACTION_clearServices = 50;
  
  static final int TRANSACTION_clientConnect = 23;
  
  static final int TRANSACTION_clientDisconnect = 24;
  
  static final int TRANSACTION_clientReadPhy = 26;
  
  static final int TRANSACTION_clientSetPreferredPhy = 25;
  
  static final int TRANSACTION_configureMTU = 39;
  
  static final int TRANSACTION_connectionParameterUpdate = 40;
  
  static final int TRANSACTION_disconnectAll = 53;
  
  static final int TRANSACTION_discoverServiceByUuid = 29;
  
  static final int TRANSACTION_discoverServices = 28;
  
  static final int TRANSACTION_enableAdvertisingSet = 12;
  
  static final int TRANSACTION_endReliableWrite = 37;
  
  static final int TRANSACTION_flushPendingBatchResults = 8;
  
  static final int TRANSACTION_getDevicesMatchingConnectionStates = 1;
  
  static final int TRANSACTION_getOwnAddress = 11;
  
  static final int TRANSACTION_leConnectionUpdate = 41;
  
  static final int TRANSACTION_numHwTrackFiltersAvailable = 55;
  
  static final int TRANSACTION_readCharacteristic = 30;
  
  static final int TRANSACTION_readDescriptor = 33;
  
  static final int TRANSACTION_readRemoteRssi = 38;
  
  static final int TRANSACTION_readUsingCharacteristicUuid = 31;
  
  static final int TRANSACTION_refreshDevice = 27;
  
  static final int TRANSACTION_registerClient = 21;
  
  static final int TRANSACTION_registerForNotification = 35;
  
  static final int TRANSACTION_registerScanner = 2;
  
  static final int TRANSACTION_registerServer = 42;
  
  static final int TRANSACTION_registerSync = 19;
  
  static final int TRANSACTION_removeService = 49;
  
  static final int TRANSACTION_sendNotification = 52;
  
  static final int TRANSACTION_sendResponse = 51;
  
  static final int TRANSACTION_serverConnect = 44;
  
  static final int TRANSACTION_serverDisconnect = 45;
  
  static final int TRANSACTION_serverReadPhy = 47;
  
  static final int TRANSACTION_serverSetPreferredPhy = 46;
  
  static final int TRANSACTION_setAdvertisingData = 13;
  
  static final int TRANSACTION_setAdvertisingParameters = 15;
  
  static final int TRANSACTION_setPeriodicAdvertisingData = 17;
  
  static final int TRANSACTION_setPeriodicAdvertisingEnable = 18;
  
  static final int TRANSACTION_setPeriodicAdvertisingParameters = 16;
  
  static final int TRANSACTION_setScanResponseData = 14;
  
  static final int TRANSACTION_startAdvertisingSet = 9;
  
  static final int TRANSACTION_startScan = 4;
  
  static final int TRANSACTION_startScanForIntent = 5;
  
  static final int TRANSACTION_stopAdvertisingSet = 10;
  
  static final int TRANSACTION_stopScan = 7;
  
  static final int TRANSACTION_stopScanForIntent = 6;
  
  static final int TRANSACTION_unregAll = 54;
  
  static final int TRANSACTION_unregisterClient = 22;
  
  static final int TRANSACTION_unregisterScanner = 3;
  
  static final int TRANSACTION_unregisterServer = 43;
  
  static final int TRANSACTION_unregisterSync = 20;
  
  static final int TRANSACTION_writeCharacteristic = 32;
  
  static final int TRANSACTION_writeDescriptor = 34;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothGatt");
  }
  
  public static IBluetoothGatt asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothGatt");
    return (iInterface != null && iInterface instanceof IBluetoothGatt) ? (IBluetoothGatt)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothGatt getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 55:
        return "numHwTrackFiltersAvailable";
      case 54:
        return "unregAll";
      case 53:
        return "disconnectAll";
      case 52:
        return "sendNotification";
      case 51:
        return "sendResponse";
      case 50:
        return "clearServices";
      case 49:
        return "removeService";
      case 48:
        return "addService";
      case 47:
        return "serverReadPhy";
      case 46:
        return "serverSetPreferredPhy";
      case 45:
        return "serverDisconnect";
      case 44:
        return "serverConnect";
      case 43:
        return "unregisterServer";
      case 42:
        return "registerServer";
      case 41:
        return "leConnectionUpdate";
      case 40:
        return "connectionParameterUpdate";
      case 39:
        return "configureMTU";
      case 38:
        return "readRemoteRssi";
      case 37:
        return "endReliableWrite";
      case 36:
        return "beginReliableWrite";
      case 35:
        return "registerForNotification";
      case 34:
        return "writeDescriptor";
      case 33:
        return "readDescriptor";
      case 32:
        return "writeCharacteristic";
      case 31:
        return "readUsingCharacteristicUuid";
      case 30:
        return "readCharacteristic";
      case 29:
        return "discoverServiceByUuid";
      case 28:
        return "discoverServices";
      case 27:
        return "refreshDevice";
      case 26:
        return "clientReadPhy";
      case 25:
        return "clientSetPreferredPhy";
      case 24:
        return "clientDisconnect";
      case 23:
        return "clientConnect";
      case 22:
        return "unregisterClient";
      case 21:
        return "registerClient";
      case 20:
        return "unregisterSync";
      case 19:
        return "registerSync";
      case 18:
        return "setPeriodicAdvertisingEnable";
      case 17:
        return "setPeriodicAdvertisingData";
      case 16:
        return "setPeriodicAdvertisingParameters";
      case 15:
        return "setAdvertisingParameters";
      case 14:
        return "setScanResponseData";
      case 13:
        return "setAdvertisingData";
      case 12:
        return "enableAdvertisingSet";
      case 11:
        return "getOwnAddress";
      case 10:
        return "stopAdvertisingSet";
      case 9:
        return "startAdvertisingSet";
      case 8:
        return "flushPendingBatchResults";
      case 7:
        return "stopScan";
      case 6:
        return "stopScanForIntent";
      case 5:
        return "startScanForIntent";
      case 4:
        return "startScan";
      case 3:
        return "unregisterScanner";
      case 2:
        return "registerScanner";
      case 1:
        break;
    } 
    return "getDevicesMatchingConnectionStates";
  }
  
  public static boolean setDefaultImpl(IBluetoothGatt paramIBluetoothGatt) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothGatt != null) {
        Proxy.sDefaultImpl = paramIBluetoothGatt;
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
      String str1;
      IScannerCallback iScannerCallback;
      String str2;
      AdvertiseData advertiseData1;
      PeriodicAdvertisingParameters periodicAdvertisingParameters;
      AdvertiseData advertiseData2;
      boolean bool1 = false;
      boolean bool2 = false;
      boolean bool3 = false;
      boolean bool4 = false;
      boolean bool5 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 55:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = numHwTrackFiltersAvailable();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(paramInt1);
          return true;
        case 54:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          unregAll();
          paramParcel2.writeNoException();
          return true;
        case 53:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          disconnectAll();
          paramParcel2.writeNoException();
          return true;
        case 52:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          str1 = paramParcel1.readString();
          paramInt2 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool5 = true;
          } else {
            bool5 = false;
          } 
          sendNotification(paramInt1, str1, paramInt2, bool5, paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 51:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          sendResponse(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 50:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          clearServices(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 49:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          removeService(paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 48:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            BluetoothGattService bluetoothGattService = (BluetoothGattService)BluetoothGattService.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          addService(paramInt1, (BluetoothGattService)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 47:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          serverReadPhy(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 46:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          serverSetPreferredPhy(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 45:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          serverDisconnect(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 44:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          str1 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0)
            bool5 = true; 
          serverConnect(paramInt1, str1, bool5, paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 43:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          unregisterServer(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 42:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          if (paramParcel1.readInt() != 0) {
            ParcelUuid parcelUuid = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          registerServer((ParcelUuid)str1, IBluetoothGattServerCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 41:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          leConnectionUpdate(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 40:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          connectionParameterUpdate(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 39:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          configureMTU(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 38:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          readRemoteRssi(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 37:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          str1 = paramParcel1.readString();
          bool5 = bool1;
          if (paramParcel1.readInt() != 0)
            bool5 = true; 
          endReliableWrite(paramInt1, str1, bool5);
          paramParcel2.writeNoException();
          return true;
        case 36:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          beginReliableWrite(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 35:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          str1 = paramParcel1.readString();
          paramInt2 = paramParcel1.readInt();
          bool5 = bool2;
          if (paramParcel1.readInt() != 0)
            bool5 = true; 
          registerForNotification(paramInt1, str1, paramInt2, bool5);
          paramParcel2.writeNoException();
          return true;
        case 34:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          writeDescriptor(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 33:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          readDescriptor(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 32:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          writeCharacteristic(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.createByteArray());
          paramParcel2.writeNoException();
          return true;
        case 31:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          str2 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            ParcelUuid parcelUuid = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          readUsingCharacteristicUuid(paramInt1, str2, (ParcelUuid)str1, paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 30:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          readCharacteristic(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 29:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          str1 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            ParcelUuid parcelUuid = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          discoverServiceByUuid(paramInt1, str1, (ParcelUuid)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 28:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          discoverServices(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 27:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          refreshDevice(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 26:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          clientReadPhy(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 25:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          clientSetPreferredPhy(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 24:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          clientDisconnect(paramParcel1.readInt(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 23:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          str1 = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            bool5 = true;
          } else {
            bool5 = false;
          } 
          paramInt2 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          clientConnect(paramInt1, str1, bool5, paramInt2, bool1, paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 22:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          unregisterClient(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 21:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          if (paramParcel1.readInt() != 0) {
            ParcelUuid parcelUuid = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          registerClient((ParcelUuid)str1, IBluetoothGattCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 20:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          unregisterSync(IPeriodicAdvertisingCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 19:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          if (paramParcel1.readInt() != 0) {
            ScanResult scanResult = (ScanResult)ScanResult.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          registerSync((ScanResult)str1, paramParcel1.readInt(), paramParcel1.readInt(), IPeriodicAdvertisingCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 18:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          bool5 = bool3;
          if (paramParcel1.readInt() != 0)
            bool5 = true; 
          setPeriodicAdvertisingEnable(paramInt1, bool5);
          paramParcel2.writeNoException();
          return true;
        case 17:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            AdvertiseData advertiseData = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          setPeriodicAdvertisingData(paramInt1, (AdvertiseData)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 16:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            PeriodicAdvertisingParameters periodicAdvertisingParameters1 = (PeriodicAdvertisingParameters)PeriodicAdvertisingParameters.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          setPeriodicAdvertisingParameters(paramInt1, (PeriodicAdvertisingParameters)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 15:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            AdvertisingSetParameters advertisingSetParameters = (AdvertisingSetParameters)AdvertisingSetParameters.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          setAdvertisingParameters(paramInt1, (AdvertisingSetParameters)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 14:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            AdvertiseData advertiseData = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          setScanResponseData(paramInt1, (AdvertiseData)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 13:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            AdvertiseData advertiseData = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          setAdvertisingData(paramInt1, (AdvertiseData)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 12:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          bool5 = bool4;
          if (paramParcel1.readInt() != 0)
            bool5 = true; 
          enableAdvertisingSet(paramInt1, bool5, paramParcel1.readInt(), paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 11:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          getOwnAddress(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 10:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          stopAdvertisingSet(IAdvertisingSetCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 9:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          if (paramParcel1.readInt() != 0) {
            AdvertisingSetParameters advertisingSetParameters = (AdvertisingSetParameters)AdvertisingSetParameters.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            AdvertiseData advertiseData = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(paramParcel1);
          } else {
            str2 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            advertiseData1 = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(paramParcel1);
          } else {
            advertiseData1 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            periodicAdvertisingParameters = (PeriodicAdvertisingParameters)PeriodicAdvertisingParameters.CREATOR.createFromParcel(paramParcel1);
          } else {
            periodicAdvertisingParameters = null;
          } 
          if (paramParcel1.readInt() != 0) {
            advertiseData2 = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(paramParcel1);
          } else {
            advertiseData2 = null;
          } 
          startAdvertisingSet((AdvertisingSetParameters)str1, (AdvertiseData)str2, advertiseData1, periodicAdvertisingParameters, advertiseData2, paramParcel1.readInt(), paramParcel1.readInt(), IAdvertisingSetCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        case 8:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          flushPendingBatchResults(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 7:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          stopScan(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 6:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          if (paramParcel1.readInt() != 0) {
            PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          stopScanForIntent((PendingIntent)str1, paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 5:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          if (paramParcel1.readInt() != 0) {
            PendingIntent pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          if (paramParcel1.readInt() != 0) {
            ScanSettings scanSettings = (ScanSettings)ScanSettings.CREATOR.createFromParcel(paramParcel1);
          } else {
            str2 = null;
          } 
          startScanForIntent((PendingIntent)str1, (ScanSettings)str2, paramParcel1.createTypedArrayList(ScanFilter.CREATOR), paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 4:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            ScanSettings scanSettings = (ScanSettings)ScanSettings.CREATOR.createFromParcel(paramParcel1);
          } else {
            str1 = null;
          } 
          startScan(paramInt1, (ScanSettings)str1, paramParcel1.createTypedArrayList(ScanFilter.CREATOR), paramParcel1.readArrayList(getClass().getClassLoader()), paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 3:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          unregisterScanner(paramParcel1.readInt());
          paramParcel2.writeNoException();
          return true;
        case 2:
          paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
          iScannerCallback = IScannerCallback.Stub.asInterface(paramParcel1.readStrongBinder());
          if (paramParcel1.readInt() != 0) {
            WorkSource workSource = (WorkSource)WorkSource.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          registerScanner(iScannerCallback, (WorkSource)paramParcel1);
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.bluetooth.IBluetoothGatt");
      List<BluetoothDevice> list = getDevicesMatchingConnectionStates(paramParcel1.createIntArray());
      paramParcel2.writeNoException();
      paramParcel2.writeTypedList(list);
      return true;
    } 
    paramParcel2.writeString("android.bluetooth.IBluetoothGatt");
    return true;
  }
  
  private static class Proxy implements IBluetoothGatt {
    public static IBluetoothGatt sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void addService(int param2Int, BluetoothGattService param2BluetoothGattService) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (param2BluetoothGattService != null) {
          parcel1.writeInt(1);
          param2BluetoothGattService.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().addService(param2Int, param2BluetoothGattService);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void beginReliableWrite(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().beginReliableWrite(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clearServices(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(50, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().clearServices(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clientConnect(int param2Int1, String param2String, boolean param2Boolean1, int param2Int2, boolean param2Boolean2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        try {
          parcel1.writeInt(param2Int1);
          try {
            boolean bool2;
            parcel1.writeString(param2String);
            boolean bool1 = true;
            if (param2Boolean1) {
              bool2 = true;
            } else {
              bool2 = false;
            } 
            parcel1.writeInt(bool2);
            try {
              parcel1.writeInt(param2Int2);
              if (param2Boolean2) {
                bool2 = bool1;
              } else {
                bool2 = false;
              } 
              parcel1.writeInt(bool2);
              try {
                parcel1.writeInt(param2Int3);
                try {
                  if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                    IBluetoothGatt.Stub.getDefaultImpl().clientConnect(param2Int1, param2String, param2Boolean1, param2Int2, param2Boolean2, param2Int3);
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } 
                  parcel2.readException();
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public void clientDisconnect(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().clientDisconnect(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clientReadPhy(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().clientReadPhy(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void clientSetPreferredPhy(int param2Int1, String param2String, int param2Int2, int param2Int3, int param2Int4) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        parcel1.writeInt(param2Int4);
        if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().clientSetPreferredPhy(param2Int1, param2String, param2Int2, param2Int3, param2Int4);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void configureMTU(int param2Int1, String param2String, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().configureMTU(param2Int1, param2String, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void connectionParameterUpdate(int param2Int1, String param2String, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().connectionParameterUpdate(param2Int1, param2String, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void disconnectAll() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().disconnectAll();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void discoverServiceByUuid(int param2Int, String param2String, ParcelUuid param2ParcelUuid) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (param2ParcelUuid != null) {
          parcel1.writeInt(1);
          param2ParcelUuid.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().discoverServiceByUuid(param2Int, param2String, param2ParcelUuid);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void discoverServices(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().discoverServices(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enableAdvertisingSet(int param2Int1, boolean param2Boolean, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().enableAdvertisingSet(param2Int1, param2Boolean, param2Int2, param2Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void endReliableWrite(int param2Int, String param2String, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().endReliableWrite(param2Int, param2String, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void flushPendingBatchResults(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().flushPendingBatchResults(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] param2ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeIntArray(param2ArrayOfint);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null)
          return IBluetoothGatt.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(param2ArrayOfint); 
        parcel2.readException();
        return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothGatt";
    }
    
    public void getOwnAddress(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().getOwnAddress(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void leConnectionUpdate(int param2Int1, String param2String, int param2Int2, int param2Int3, int param2Int4, int param2Int5, int param2Int6, int param2Int7) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        try {
          parcel1.writeInt(param2Int1);
          try {
            parcel1.writeString(param2String);
            try {
              parcel1.writeInt(param2Int2);
              try {
                parcel1.writeInt(param2Int3);
                parcel1.writeInt(param2Int4);
                parcel1.writeInt(param2Int5);
                parcel1.writeInt(param2Int6);
                parcel1.writeInt(param2Int7);
                if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                  IBluetoothGatt.Stub.getDefaultImpl().leConnectionUpdate(param2Int1, param2String, param2Int2, param2Int3, param2Int4, param2Int5, param2Int6, param2Int7);
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } 
                parcel2.readException();
                parcel2.recycle();
                parcel1.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public int numHwTrackFiltersAvailable() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null)
          return IBluetoothGatt.Stub.getDefaultImpl().numHwTrackFiltersAvailable(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void readCharacteristic(int param2Int1, String param2String, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().readCharacteristic(param2Int1, param2String, param2Int2, param2Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void readDescriptor(int param2Int1, String param2String, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().readDescriptor(param2Int1, param2String, param2Int2, param2Int3);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void readRemoteRssi(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().readRemoteRssi(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void readUsingCharacteristicUuid(int param2Int1, String param2String, ParcelUuid param2ParcelUuid, int param2Int2, int param2Int3, int param2Int4) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        try {
          parcel1.writeInt(param2Int1);
          try {
            parcel1.writeString(param2String);
            if (param2ParcelUuid != null) {
              parcel1.writeInt(1);
              param2ParcelUuid.writeToParcel(parcel1, 0);
            } else {
              parcel1.writeInt(0);
            } 
            try {
              parcel1.writeInt(param2Int2);
              try {
                parcel1.writeInt(param2Int3);
                try {
                  parcel1.writeInt(param2Int4);
                  if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                    IBluetoothGatt.Stub.getDefaultImpl().readUsingCharacteristicUuid(param2Int1, param2String, param2ParcelUuid, param2Int2, param2Int3, param2Int4);
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } 
                  parcel2.readException();
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public void refreshDevice(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().refreshDevice(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerClient(ParcelUuid param2ParcelUuid, IBluetoothGattCallback param2IBluetoothGattCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (param2ParcelUuid != null) {
          parcel1.writeInt(1);
          param2ParcelUuid.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IBluetoothGattCallback != null) {
          iBinder = param2IBluetoothGattCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().registerClient(param2ParcelUuid, param2IBluetoothGattCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerForNotification(int param2Int1, String param2String, int param2Int2, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int2);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().registerForNotification(param2Int1, param2String, param2Int2, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerScanner(IScannerCallback param2IScannerCallback, WorkSource param2WorkSource) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (param2IScannerCallback != null) {
          iBinder = param2IScannerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (param2WorkSource != null) {
          parcel1.writeInt(1);
          param2WorkSource.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().registerScanner(param2IScannerCallback, param2WorkSource);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerServer(ParcelUuid param2ParcelUuid, IBluetoothGattServerCallback param2IBluetoothGattServerCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (param2ParcelUuid != null) {
          parcel1.writeInt(1);
          param2ParcelUuid.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IBluetoothGattServerCallback != null) {
          iBinder = param2IBluetoothGattServerCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().registerServer(param2ParcelUuid, param2IBluetoothGattServerCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void registerSync(ScanResult param2ScanResult, int param2Int1, int param2Int2, IPeriodicAdvertisingCallback param2IPeriodicAdvertisingCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (param2ScanResult != null) {
          parcel1.writeInt(1);
          param2ScanResult.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (param2IPeriodicAdvertisingCallback != null) {
          iBinder = param2IPeriodicAdvertisingCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().registerSync(param2ScanResult, param2Int1, param2Int2, param2IPeriodicAdvertisingCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void removeService(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().removeService(param2Int1, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendNotification(int param2Int1, String param2String, int param2Int2, boolean param2Boolean, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int2);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().sendNotification(param2Int1, param2String, param2Int2, param2Boolean, param2ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void sendResponse(int param2Int1, String param2String, int param2Int2, int param2Int3, int param2Int4, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        try {
          parcel1.writeInt(param2Int1);
          try {
            parcel1.writeString(param2String);
            try {
              parcel1.writeInt(param2Int2);
              try {
                parcel1.writeInt(param2Int3);
                try {
                  parcel1.writeInt(param2Int4);
                  try {
                    parcel1.writeByteArray(param2ArrayOfbyte);
                    if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                      IBluetoothGatt.Stub.getDefaultImpl().sendResponse(param2Int1, param2String, param2Int2, param2Int3, param2Int4, param2ArrayOfbyte);
                      parcel2.recycle();
                      parcel1.recycle();
                      return;
                    } 
                    parcel2.readException();
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public void serverConnect(int param2Int1, String param2String, boolean param2Boolean, int param2Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeInt(param2Int2);
        if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().serverConnect(param2Int1, param2String, param2Boolean, param2Int2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void serverDisconnect(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().serverDisconnect(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void serverReadPhy(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().serverReadPhy(param2Int, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void serverSetPreferredPhy(int param2Int1, String param2String, int param2Int2, int param2Int3, int param2Int4) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        parcel1.writeInt(param2Int4);
        if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().serverSetPreferredPhy(param2Int1, param2String, param2Int2, param2Int3, param2Int4);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAdvertisingData(int param2Int, AdvertiseData param2AdvertiseData) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (param2AdvertiseData != null) {
          parcel1.writeInt(1);
          param2AdvertiseData.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().setAdvertisingData(param2Int, param2AdvertiseData);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setAdvertisingParameters(int param2Int, AdvertisingSetParameters param2AdvertisingSetParameters) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (param2AdvertisingSetParameters != null) {
          parcel1.writeInt(1);
          param2AdvertisingSetParameters.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().setAdvertisingParameters(param2Int, param2AdvertisingSetParameters);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPeriodicAdvertisingData(int param2Int, AdvertiseData param2AdvertiseData) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (param2AdvertiseData != null) {
          parcel1.writeInt(1);
          param2AdvertiseData.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().setPeriodicAdvertisingData(param2Int, param2AdvertiseData);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPeriodicAdvertisingEnable(int param2Int, boolean param2Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().setPeriodicAdvertisingEnable(param2Int, param2Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setPeriodicAdvertisingParameters(int param2Int, PeriodicAdvertisingParameters param2PeriodicAdvertisingParameters) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (param2PeriodicAdvertisingParameters != null) {
          parcel1.writeInt(1);
          param2PeriodicAdvertisingParameters.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().setPeriodicAdvertisingParameters(param2Int, param2PeriodicAdvertisingParameters);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void setScanResponseData(int param2Int, AdvertiseData param2AdvertiseData) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (param2AdvertiseData != null) {
          parcel1.writeInt(1);
          param2AdvertiseData.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().setScanResponseData(param2Int, param2AdvertiseData);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void startAdvertisingSet(AdvertisingSetParameters param2AdvertisingSetParameters, AdvertiseData param2AdvertiseData1, AdvertiseData param2AdvertiseData2, PeriodicAdvertisingParameters param2PeriodicAdvertisingParameters, AdvertiseData param2AdvertiseData3, int param2Int1, int param2Int2, IAdvertisingSetCallback param2IAdvertisingSetCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (param2AdvertisingSetParameters != null) {
          try {
            parcel1.writeInt(1);
            param2AdvertisingSetParameters.writeToParcel(parcel1, 0);
          } finally {}
        } else {
          parcel1.writeInt(0);
        } 
        if (param2AdvertiseData1 != null) {
          parcel1.writeInt(1);
          param2AdvertiseData1.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2AdvertiseData2 != null) {
          parcel1.writeInt(1);
          param2AdvertiseData2.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2PeriodicAdvertisingParameters != null) {
          parcel1.writeInt(1);
          param2PeriodicAdvertisingParameters.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2AdvertiseData3 != null) {
          parcel1.writeInt(1);
          param2AdvertiseData3.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param2Int1);
        parcel1.writeInt(param2Int2);
        if (param2IAdvertisingSetCallback != null) {
          iBinder = param2IAdvertisingSetCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt iBluetoothGatt = IBluetoothGatt.Stub.getDefaultImpl();
          try {
            iBluetoothGatt.startAdvertisingSet(param2AdvertisingSetParameters, param2AdvertiseData1, param2AdvertiseData2, param2PeriodicAdvertisingParameters, param2AdvertiseData3, param2Int1, param2Int2, param2IAdvertisingSetCallback);
            parcel2.recycle();
            parcel1.recycle();
            return;
          } finally {}
        } else {
          Parcel parcel = parcel2;
          parcel.readException();
          parcel.recycle();
          parcel1.recycle();
          return;
        } 
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2AdvertisingSetParameters;
    }
    
    public void startScan(int param2Int, ScanSettings param2ScanSettings, List<ScanFilter> param2List, List param2List1, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        try {
          parcel1.writeInt(param2Int);
          if (param2ScanSettings != null) {
            parcel1.writeInt(1);
            param2ScanSettings.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeTypedList(param2List);
            try {
              parcel1.writeList(param2List1);
              try {
                parcel1.writeString(param2String1);
                try {
                  parcel1.writeString(param2String2);
                  if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                    IBluetoothGatt.Stub.getDefaultImpl().startScan(param2Int, param2ScanSettings, param2List, param2List1, param2String1, param2String2);
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } 
                  parcel2.readException();
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2ScanSettings;
    }
    
    public void startScanForIntent(PendingIntent param2PendingIntent, ScanSettings param2ScanSettings, List<ScanFilter> param2List, String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (param2PendingIntent != null) {
          parcel1.writeInt(1);
          param2PendingIntent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2ScanSettings != null) {
          parcel1.writeInt(1);
          param2ScanSettings.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeTypedList(param2List);
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().startScanForIntent(param2PendingIntent, param2ScanSettings, param2List, param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopAdvertisingSet(IAdvertisingSetCallback param2IAdvertisingSetCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (param2IAdvertisingSetCallback != null) {
          iBinder = param2IAdvertisingSetCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().stopAdvertisingSet(param2IAdvertisingSetCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopScan(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().stopScan(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void stopScanForIntent(PendingIntent param2PendingIntent, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (param2PendingIntent != null) {
          parcel1.writeInt(1);
          param2PendingIntent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().stopScanForIntent(param2PendingIntent, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregAll() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().unregAll();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterClient(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().unregisterClient(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterScanner(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().unregisterScanner(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterServer(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().unregisterServer(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unregisterSync(IPeriodicAdvertisingCallback param2IPeriodicAdvertisingCallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        if (param2IPeriodicAdvertisingCallback != null) {
          iBinder = param2IPeriodicAdvertisingCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().unregisterSync(param2IPeriodicAdvertisingCallback);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void writeCharacteristic(int param2Int1, String param2String, int param2Int2, int param2Int3, int param2Int4, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        try {
          parcel1.writeInt(param2Int1);
          try {
            parcel1.writeString(param2String);
            try {
              parcel1.writeInt(param2Int2);
              try {
                parcel1.writeInt(param2Int3);
                try {
                  parcel1.writeInt(param2Int4);
                  try {
                    parcel1.writeByteArray(param2ArrayOfbyte);
                    if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                      IBluetoothGatt.Stub.getDefaultImpl().writeCharacteristic(param2Int1, param2String, param2Int2, param2Int3, param2Int4, param2ArrayOfbyte);
                      parcel2.recycle();
                      parcel1.recycle();
                      return;
                    } 
                    parcel2.readException();
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel2.recycle();
      parcel1.recycle();
      throw param2String;
    }
    
    public void writeDescriptor(int param2Int1, String param2String, int param2Int2, int param2Int3, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
        parcel1.writeInt(param2Int1);
        parcel1.writeString(param2String);
        parcel1.writeInt(param2Int2);
        parcel1.writeInt(param2Int3);
        parcel1.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
          IBluetoothGatt.Stub.getDefaultImpl().writeDescriptor(param2Int1, param2String, param2Int2, param2Int3, param2ArrayOfbyte);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothGatt$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */