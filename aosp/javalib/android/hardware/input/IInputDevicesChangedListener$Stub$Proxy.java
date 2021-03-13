package android.hardware.input;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IInputDevicesChangedListener {
  public static IInputDevicesChangedListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.input.IInputDevicesChangedListener";
  }
  
  public void onInputDevicesChanged(int[] paramArrayOfint) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.hardware.input.IInputDevicesChangedListener");
      parcel.writeIntArray(paramArrayOfint);
      if (!this.mRemote.transact(1, parcel, null, 1) && IInputDevicesChangedListener.Stub.getDefaultImpl() != null) {
        IInputDevicesChangedListener.Stub.getDefaultImpl().onInputDevicesChanged(paramArrayOfint);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/input/IInputDevicesChangedListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */