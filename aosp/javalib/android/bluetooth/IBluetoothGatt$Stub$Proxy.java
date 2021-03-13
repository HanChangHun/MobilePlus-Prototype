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
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.WorkSource;
import java.util.List;

class Proxy implements IBluetoothGatt {
  public static IBluetoothGatt sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addService(int paramInt, BluetoothGattService paramBluetoothGattService) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (paramBluetoothGattService != null) {
        parcel1.writeInt(1);
        paramBluetoothGattService.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().addService(paramInt, paramBluetoothGattService);
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
  
  public void beginReliableWrite(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().beginReliableWrite(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clearServices(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(50, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().clearServices(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clientConnect(int paramInt1, String paramString, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      try {
        parcel1.writeInt(paramInt1);
        try {
          boolean bool2;
          parcel1.writeString(paramString);
          boolean bool1 = true;
          if (paramBoolean1) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          parcel1.writeInt(bool2);
          try {
            parcel1.writeInt(paramInt2);
            if (paramBoolean2) {
              bool2 = bool1;
            } else {
              bool2 = false;
            } 
            parcel1.writeInt(bool2);
            try {
              parcel1.writeInt(paramInt3);
              try {
                if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                  IBluetoothGatt.Stub.getDefaultImpl().clientConnect(paramInt1, paramString, paramBoolean1, paramInt2, paramBoolean2, paramInt3);
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
    throw paramString;
  }
  
  public void clientDisconnect(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().clientDisconnect(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clientReadPhy(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().clientReadPhy(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void clientSetPreferredPhy(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      parcel1.writeInt(paramInt4);
      if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().clientSetPreferredPhy(paramInt1, paramString, paramInt2, paramInt3, paramInt4);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void configureMTU(int paramInt1, String paramString, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().configureMTU(paramInt1, paramString, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void connectionParameterUpdate(int paramInt1, String paramString, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().connectionParameterUpdate(paramInt1, paramString, paramInt2);
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
  
  public void discoverServiceByUuid(int paramInt, String paramString, ParcelUuid paramParcelUuid) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (paramParcelUuid != null) {
        parcel1.writeInt(1);
        paramParcelUuid.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().discoverServiceByUuid(paramInt, paramString, paramParcelUuid);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void discoverServices(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().discoverServices(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void enableAdvertisingSet(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().enableAdvertisingSet(paramInt1, paramBoolean, paramInt2, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void endReliableWrite(int paramInt, String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().endReliableWrite(paramInt, paramString, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void flushPendingBatchResults(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().flushPendingBatchResults(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null)
        return IBluetoothGatt.Stub.getDefaultImpl().getDevicesMatchingConnectionStates(paramArrayOfint); 
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
  
  public void getOwnAddress(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().getOwnAddress(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void leConnectionUpdate(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeString(paramString);
          try {
            parcel1.writeInt(paramInt2);
            try {
              parcel1.writeInt(paramInt3);
              parcel1.writeInt(paramInt4);
              parcel1.writeInt(paramInt5);
              parcel1.writeInt(paramInt6);
              parcel1.writeInt(paramInt7);
              if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                IBluetoothGatt.Stub.getDefaultImpl().leConnectionUpdate(paramInt1, paramString, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
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
    throw paramString;
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
  
  public void readCharacteristic(int paramInt1, String paramString, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().readCharacteristic(paramInt1, paramString, paramInt2, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void readDescriptor(int paramInt1, String paramString, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().readDescriptor(paramInt1, paramString, paramInt2, paramInt3);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void readRemoteRssi(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().readRemoteRssi(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void readUsingCharacteristicUuid(int paramInt1, String paramString, ParcelUuid paramParcelUuid, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeString(paramString);
          if (paramParcelUuid != null) {
            parcel1.writeInt(1);
            paramParcelUuid.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          try {
            parcel1.writeInt(paramInt2);
            try {
              parcel1.writeInt(paramInt3);
              try {
                parcel1.writeInt(paramInt4);
                if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                  IBluetoothGatt.Stub.getDefaultImpl().readUsingCharacteristicUuid(paramInt1, paramString, paramParcelUuid, paramInt2, paramInt3, paramInt4);
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
    throw paramString;
  }
  
  public void refreshDevice(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().refreshDevice(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerClient(ParcelUuid paramParcelUuid, IBluetoothGattCallback paramIBluetoothGattCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      if (paramParcelUuid != null) {
        parcel1.writeInt(1);
        paramParcelUuid.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIBluetoothGattCallback != null) {
        iBinder = paramIBluetoothGattCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().registerClient(paramParcelUuid, paramIBluetoothGattCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerForNotification(int paramInt1, String paramString, int paramInt2, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().registerForNotification(paramInt1, paramString, paramInt2, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerScanner(IScannerCallback paramIScannerCallback, WorkSource paramWorkSource) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      if (paramIScannerCallback != null) {
        iBinder = paramIScannerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (paramWorkSource != null) {
        parcel1.writeInt(1);
        paramWorkSource.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().registerScanner(paramIScannerCallback, paramWorkSource);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerServer(ParcelUuid paramParcelUuid, IBluetoothGattServerCallback paramIBluetoothGattServerCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      if (paramParcelUuid != null) {
        parcel1.writeInt(1);
        paramParcelUuid.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIBluetoothGattServerCallback != null) {
        iBinder = paramIBluetoothGattServerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().registerServer(paramParcelUuid, paramIBluetoothGattServerCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerSync(ScanResult paramScanResult, int paramInt1, int paramInt2, IPeriodicAdvertisingCallback paramIPeriodicAdvertisingCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      if (paramScanResult != null) {
        parcel1.writeInt(1);
        paramScanResult.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramIPeriodicAdvertisingCallback != null) {
        iBinder = paramIPeriodicAdvertisingCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().registerSync(paramScanResult, paramInt1, paramInt2, paramIPeriodicAdvertisingCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeService(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().removeService(paramInt1, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sendNotification(int paramInt1, String paramString, int paramInt2, boolean paramBoolean, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().sendNotification(paramInt1, paramString, paramInt2, paramBoolean, paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void sendResponse(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeString(paramString);
          try {
            parcel1.writeInt(paramInt2);
            try {
              parcel1.writeInt(paramInt3);
              try {
                parcel1.writeInt(paramInt4);
                try {
                  parcel1.writeByteArray(paramArrayOfbyte);
                  if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                    IBluetoothGatt.Stub.getDefaultImpl().sendResponse(paramInt1, paramString, paramInt2, paramInt3, paramInt4, paramArrayOfbyte);
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
    throw paramString;
  }
  
  public void serverConnect(int paramInt1, String paramString, boolean paramBoolean, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeInt(paramInt2);
      if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().serverConnect(paramInt1, paramString, paramBoolean, paramInt2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void serverDisconnect(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().serverDisconnect(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void serverReadPhy(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().serverReadPhy(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void serverSetPreferredPhy(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      parcel1.writeInt(paramInt4);
      if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().serverSetPreferredPhy(paramInt1, paramString, paramInt2, paramInt3, paramInt4);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAdvertisingData(int paramInt, AdvertiseData paramAdvertiseData) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (paramAdvertiseData != null) {
        parcel1.writeInt(1);
        paramAdvertiseData.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().setAdvertisingData(paramInt, paramAdvertiseData);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setAdvertisingParameters(int paramInt, AdvertisingSetParameters paramAdvertisingSetParameters) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (paramAdvertisingSetParameters != null) {
        parcel1.writeInt(1);
        paramAdvertisingSetParameters.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().setAdvertisingParameters(paramInt, paramAdvertisingSetParameters);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPeriodicAdvertisingData(int paramInt, AdvertiseData paramAdvertiseData) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (paramAdvertiseData != null) {
        parcel1.writeInt(1);
        paramAdvertiseData.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().setPeriodicAdvertisingData(paramInt, paramAdvertiseData);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPeriodicAdvertisingEnable(int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().setPeriodicAdvertisingEnable(paramInt, paramBoolean);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setPeriodicAdvertisingParameters(int paramInt, PeriodicAdvertisingParameters paramPeriodicAdvertisingParameters) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (paramPeriodicAdvertisingParameters != null) {
        parcel1.writeInt(1);
        paramPeriodicAdvertisingParameters.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().setPeriodicAdvertisingParameters(paramInt, paramPeriodicAdvertisingParameters);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setScanResponseData(int paramInt, AdvertiseData paramAdvertiseData) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (paramAdvertiseData != null) {
        parcel1.writeInt(1);
        paramAdvertiseData.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().setScanResponseData(paramInt, paramAdvertiseData);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startAdvertisingSet(AdvertisingSetParameters paramAdvertisingSetParameters, AdvertiseData paramAdvertiseData1, AdvertiseData paramAdvertiseData2, PeriodicAdvertisingParameters paramPeriodicAdvertisingParameters, AdvertiseData paramAdvertiseData3, int paramInt1, int paramInt2, IAdvertisingSetCallback paramIAdvertisingSetCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      if (paramAdvertisingSetParameters != null) {
        try {
          parcel1.writeInt(1);
          paramAdvertisingSetParameters.writeToParcel(parcel1, 0);
        } finally {}
      } else {
        parcel1.writeInt(0);
      } 
      if (paramAdvertiseData1 != null) {
        parcel1.writeInt(1);
        paramAdvertiseData1.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramAdvertiseData2 != null) {
        parcel1.writeInt(1);
        paramAdvertiseData2.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramPeriodicAdvertisingParameters != null) {
        parcel1.writeInt(1);
        paramPeriodicAdvertisingParameters.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramAdvertiseData3 != null) {
        parcel1.writeInt(1);
        paramAdvertiseData3.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramIAdvertisingSetCallback != null) {
        iBinder = paramIAdvertisingSetCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt iBluetoothGatt = IBluetoothGatt.Stub.getDefaultImpl();
        try {
          iBluetoothGatt.startAdvertisingSet(paramAdvertisingSetParameters, paramAdvertiseData1, paramAdvertiseData2, paramPeriodicAdvertisingParameters, paramAdvertiseData3, paramInt1, paramInt2, paramIAdvertisingSetCallback);
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
    throw paramAdvertisingSetParameters;
  }
  
  public void startScan(int paramInt, ScanSettings paramScanSettings, List<ScanFilter> paramList, List paramList1, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      try {
        parcel1.writeInt(paramInt);
        if (paramScanSettings != null) {
          parcel1.writeInt(1);
          paramScanSettings.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        try {
          parcel1.writeTypedList(paramList);
          try {
            parcel1.writeList(paramList1);
            try {
              parcel1.writeString(paramString1);
              try {
                parcel1.writeString(paramString2);
                if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                  IBluetoothGatt.Stub.getDefaultImpl().startScan(paramInt, paramScanSettings, paramList, paramList1, paramString1, paramString2);
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
    throw paramScanSettings;
  }
  
  public void startScanForIntent(PendingIntent paramPendingIntent, ScanSettings paramScanSettings, List<ScanFilter> paramList, String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      if (paramPendingIntent != null) {
        parcel1.writeInt(1);
        paramPendingIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramScanSettings != null) {
        parcel1.writeInt(1);
        paramScanSettings.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeTypedList(paramList);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().startScanForIntent(paramPendingIntent, paramScanSettings, paramList, paramString1, paramString2);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void stopAdvertisingSet(IAdvertisingSetCallback paramIAdvertisingSetCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      if (paramIAdvertisingSetCallback != null) {
        iBinder = paramIAdvertisingSetCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().stopAdvertisingSet(paramIAdvertisingSetCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void stopScan(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().stopScan(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void stopScanForIntent(PendingIntent paramPendingIntent, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      if (paramPendingIntent != null) {
        parcel1.writeInt(1);
        paramPendingIntent.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().stopScanForIntent(paramPendingIntent, paramString);
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
  
  public void unregisterClient(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().unregisterClient(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterScanner(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().unregisterScanner(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterServer(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().unregisterServer(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterSync(IPeriodicAdvertisingCallback paramIPeriodicAdvertisingCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      if (paramIPeriodicAdvertisingCallback != null) {
        iBinder = paramIPeriodicAdvertisingCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().unregisterSync(paramIPeriodicAdvertisingCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void writeCharacteristic(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      try {
        parcel1.writeInt(paramInt1);
        try {
          parcel1.writeString(paramString);
          try {
            parcel1.writeInt(paramInt2);
            try {
              parcel1.writeInt(paramInt3);
              try {
                parcel1.writeInt(paramInt4);
                try {
                  parcel1.writeByteArray(paramArrayOfbyte);
                  if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
                    IBluetoothGatt.Stub.getDefaultImpl().writeCharacteristic(paramInt1, paramString, paramInt2, paramInt3, paramInt4, paramArrayOfbyte);
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
    throw paramString;
  }
  
  public void writeDescriptor(int paramInt1, String paramString, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IBluetoothGatt.Stub.getDefaultImpl() != null) {
        IBluetoothGatt.Stub.getDefaultImpl().writeDescriptor(paramInt1, paramString, paramInt2, paramInt3, paramArrayOfbyte);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothGatt$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */