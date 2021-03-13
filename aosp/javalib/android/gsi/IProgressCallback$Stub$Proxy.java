package android.gsi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IProgressCallback {
  public static IProgressCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.gsi.IProgressCallback";
  }
  
  public void onProgress(long paramLong1, long paramLong2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.gsi.IProgressCallback");
      parcel1.writeLong(paramLong1);
      parcel1.writeLong(paramLong2);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IProgressCallback.Stub.getDefaultImpl() != null) {
        IProgressCallback.Stub.getDefaultImpl().onProgress(paramLong1, paramLong2);
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


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IProgressCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */