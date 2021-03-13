package android.app;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IServiceConnection {
  public static IServiceConnection sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void connected(ComponentName paramComponentName, IBinder paramIBinder, boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IServiceConnection");
      boolean bool = false;
      if (paramComponentName != null) {
        parcel.writeInt(1);
        paramComponentName.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeStrongBinder(paramIBinder);
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(1, parcel, null, 1) && IServiceConnection.Stub.getDefaultImpl() != null) {
        IServiceConnection.Stub.getDefaultImpl().connected(paramComponentName, paramIBinder, paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IServiceConnection";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IServiceConnection$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */