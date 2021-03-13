package android.debug;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IAdbTransport {
  public static IAdbTransport sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.debug.IAdbTransport";
  }
  
  public void onAdbEnabled(boolean paramBoolean, byte paramByte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.debug.IAdbTransport");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeByte(paramByte);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAdbTransport.Stub.getDefaultImpl() != null) {
        IAdbTransport.Stub.getDefaultImpl().onAdbEnabled(paramBoolean, paramByte);
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


/* Location:              /home/chun/Desktop/temp/!/android/debug/IAdbTransport$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */