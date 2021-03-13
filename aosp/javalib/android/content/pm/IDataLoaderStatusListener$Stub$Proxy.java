package android.content.pm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IDataLoaderStatusListener {
  public static IDataLoaderStatusListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.IDataLoaderStatusListener";
  }
  
  public void onStatusChanged(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.pm.IDataLoaderStatusListener");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      if (!this.mRemote.transact(1, parcel, null, 1) && IDataLoaderStatusListener.Stub.getDefaultImpl() != null) {
        IDataLoaderStatusListener.Stub.getDefaultImpl().onStatusChanged(paramInt1, paramInt2);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IDataLoaderStatusListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */