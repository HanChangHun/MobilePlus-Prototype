package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBluetoothGattServerCallback {
  public static IBluetoothGattServerCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothGattServerCallback";
  }
  
  public void onCharacteristicReadRequest(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(4, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattServerCallback.Stub.getDefaultImpl().onCharacteristicReadRequest(paramString, paramInt1, paramInt2, paramBoolean, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onCharacteristicWriteRequest(String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      try {
        parcel.writeString(paramString);
        try {
          parcel.writeInt(paramInt1);
          try {
            parcel.writeInt(paramInt2);
            try {
              parcel.writeInt(paramInt3);
              boolean bool1 = false;
              if (paramBoolean1) {
                bool2 = true;
              } else {
                bool2 = false;
              } 
              parcel.writeInt(bool2);
              boolean bool2 = bool1;
              if (paramBoolean2)
                bool2 = true; 
              parcel.writeInt(bool2);
              try {
                parcel.writeInt(paramInt4);
                parcel.writeByteArray(paramArrayOfbyte);
                if (!this.mRemote.transact(6, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
                  IBluetoothGattServerCallback.Stub.getDefaultImpl().onCharacteristicWriteRequest(paramString, paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2, paramInt4, paramArrayOfbyte);
                  parcel.recycle();
                  return;
                } 
                parcel.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel.recycle();
    throw paramString;
  }
  
  public void onConnectionUpdated(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      parcel.writeInt(paramInt4);
      if (!this.mRemote.transact(13, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattServerCallback.Stub.getDefaultImpl().onConnectionUpdated(paramString, paramInt1, paramInt2, paramInt3, paramInt4);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onDescriptorReadRequest(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(5, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattServerCallback.Stub.getDefaultImpl().onDescriptorReadRequest(paramString, paramInt1, paramInt2, paramBoolean, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onDescriptorWriteRequest(String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      try {
        parcel.writeString(paramString);
        try {
          parcel.writeInt(paramInt1);
          try {
            parcel.writeInt(paramInt2);
            try {
              parcel.writeInt(paramInt3);
              boolean bool1 = false;
              if (paramBoolean1) {
                bool2 = true;
              } else {
                bool2 = false;
              } 
              parcel.writeInt(bool2);
              boolean bool2 = bool1;
              if (paramBoolean2)
                bool2 = true; 
              parcel.writeInt(bool2);
              try {
                parcel.writeInt(paramInt4);
                parcel.writeByteArray(paramArrayOfbyte);
                if (!this.mRemote.transact(7, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
                  IBluetoothGattServerCallback.Stub.getDefaultImpl().onDescriptorWriteRequest(paramString, paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2, paramInt4, paramArrayOfbyte);
                  parcel.recycle();
                  return;
                } 
                parcel.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel.recycle();
    throw paramString;
  }
  
  public void onExecuteWrite(String paramString, int paramInt, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(8, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattServerCallback.Stub.getDefaultImpl().onExecuteWrite(paramString, paramInt, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onMtuChanged(String paramString, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(10, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattServerCallback.Stub.getDefaultImpl().onMtuChanged(paramString, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onNotificationSent(String paramString, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(9, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattServerCallback.Stub.getDefaultImpl().onNotificationSent(paramString, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPhyRead(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(12, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattServerCallback.Stub.getDefaultImpl().onPhyRead(paramString, paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onPhyUpdate(String paramString, int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(11, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattServerCallback.Stub.getDefaultImpl().onPhyUpdate(paramString, paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onServerConnectionState(int paramInt1, int paramInt2, boolean paramBoolean, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattServerCallback.Stub.getDefaultImpl().onServerConnectionState(paramInt1, paramInt2, paramBoolean, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onServerRegistered(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattServerCallback.Stub.getDefaultImpl().onServerRegistered(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onServiceAdded(int paramInt, BluetoothGattService paramBluetoothGattService) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattServerCallback");
      parcel.writeInt(paramInt);
      if (paramBluetoothGattService != null) {
        parcel.writeInt(1);
        paramBluetoothGattService.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel, null, 1) && IBluetoothGattServerCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattServerCallback.Stub.getDefaultImpl().onServiceAdded(paramInt, paramBluetoothGattService);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothGattServerCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */