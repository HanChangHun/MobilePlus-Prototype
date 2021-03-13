package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IBluetoothGattCallback {
  public static IBluetoothGattCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothGattCallback";
  }
  
  public void onCharacteristicRead(String paramString, int paramInt1, int paramInt2, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(6, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onCharacteristicRead(paramString, paramInt1, paramInt2, paramArrayOfbyte);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onCharacteristicWrite(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(7, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onCharacteristicWrite(paramString, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onClientConnectionState(int paramInt1, int paramInt2, boolean paramBoolean, String paramString) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      parcel.writeString(paramString);
      if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onClientConnectionState(paramInt1, paramInt2, paramBoolean, paramString);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onClientRegistered(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onClientRegistered(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onConfigureMTU(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(13, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onConfigureMTU(paramString, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onConnectionUpdated(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      parcel.writeInt(paramInt4);
      if (!this.mRemote.transact(14, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onConnectionUpdated(paramString, paramInt1, paramInt2, paramInt3, paramInt4);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onDescriptorRead(String paramString, int paramInt1, int paramInt2, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(9, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onDescriptorRead(paramString, paramInt1, paramInt2, paramArrayOfbyte);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onDescriptorWrite(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(10, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onDescriptorWrite(paramString, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onExecuteWrite(String paramString, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(8, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onExecuteWrite(paramString, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onNotify(String paramString, int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt);
      parcel.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(11, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onNotify(paramString, paramInt, paramArrayOfbyte);
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
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(4, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onPhyRead(paramString, paramInt1, paramInt2, paramInt3);
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
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (!this.mRemote.transact(3, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onPhyUpdate(paramString, paramInt1, paramInt2, paramInt3);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onReadRemoteRssi(String paramString, int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(12, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onReadRemoteRssi(paramString, paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSearchComplete(String paramString, List<BluetoothGattService> paramList, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothGattCallback");
      parcel.writeString(paramString);
      parcel.writeTypedList(paramList);
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(5, parcel, null, 1) && IBluetoothGattCallback.Stub.getDefaultImpl() != null) {
        IBluetoothGattCallback.Stub.getDefaultImpl().onSearchComplete(paramString, paramList, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothGattCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */