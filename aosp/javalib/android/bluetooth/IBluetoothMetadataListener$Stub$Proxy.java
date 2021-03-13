package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBluetoothMetadataListener {
  public static IBluetoothMetadataListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothMetadataListener";
  }
  
  public void onMetadataChanged(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetoothMetadataListener");
      if (paramBluetoothDevice != null) {
        parcel.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      parcel.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothMetadataListener.Stub.getDefaultImpl() != null) {
        IBluetoothMetadataListener.Stub.getDefaultImpl().onMetadataChanged(paramBluetoothDevice, paramInt, paramArrayOfbyte);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothMetadataListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */