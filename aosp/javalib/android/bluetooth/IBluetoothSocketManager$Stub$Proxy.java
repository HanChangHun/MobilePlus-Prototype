package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelUuid;
import android.os.RemoteException;

class Proxy implements IBluetoothSocketManager {
  public static IBluetoothSocketManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public ParcelFileDescriptor connectSocket(BluetoothDevice paramBluetoothDevice, int paramInt1, ParcelUuid paramParcelUuid, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt1);
      if (paramParcelUuid != null) {
        parcel1.writeInt(1);
        paramParcelUuid.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null)
        return IBluetoothSocketManager.Stub.getDefaultImpl().connectSocket(paramBluetoothDevice, paramInt1, paramParcelUuid, paramInt2, paramInt3); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
      } else {
        paramBluetoothDevice = null;
      } 
      return (ParcelFileDescriptor)paramBluetoothDevice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParcelFileDescriptor createSocketChannel(int paramInt1, String paramString, ParcelUuid paramParcelUuid, int paramInt2, int paramInt3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
      parcel1.writeInt(paramInt1);
      parcel1.writeString(paramString);
      if (paramParcelUuid != null) {
        parcel1.writeInt(1);
        paramParcelUuid.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null)
        return IBluetoothSocketManager.Stub.getDefaultImpl().createSocketChannel(paramInt1, paramString, paramParcelUuid, paramInt2, paramInt3); 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
      } else {
        paramString = null;
      } 
      return (ParcelFileDescriptor)paramString;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothSocketManager";
  }
  
  public void requestMaximumTxDataLength(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null) {
        IBluetoothSocketManager.Stub.getDefaultImpl().requestMaximumTxDataLength(paramBluetoothDevice);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothSocketManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */