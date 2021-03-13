package android.app;

import android.content.res.Configuration;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IWindowToken {
  public static IWindowToken sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.IWindowToken";
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration, int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IWindowToken");
      if (paramConfiguration != null) {
        parcel.writeInt(1);
        paramConfiguration.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IWindowToken.Stub.getDefaultImpl() != null) {
        IWindowToken.Stub.getDefaultImpl().onConfigurationChanged(paramConfiguration, paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onWindowTokenRemoved() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.IWindowToken");
      if (!this.mRemote.transact(2, parcel, null, 1) && IWindowToken.Stub.getDefaultImpl() != null) {
        IWindowToken.Stub.getDefaultImpl().onWindowTokenRemoved();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IWindowToken$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */