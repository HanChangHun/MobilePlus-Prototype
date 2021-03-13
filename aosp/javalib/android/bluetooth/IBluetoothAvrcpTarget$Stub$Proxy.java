package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IBluetoothAvrcpTarget {
  public static IBluetoothAvrcpTarget sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothAvrcpTarget";
  }
  
  public void sendVolumeChanged(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpTarget");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothAvrcpTarget.Stub.getDefaultImpl() != null) {
        IBluetoothAvrcpTarget.Stub.getDefaultImpl().sendVolumeChanged(paramInt);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothAvrcpTarget$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */