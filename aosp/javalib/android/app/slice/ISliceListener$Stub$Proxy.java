package android.app.slice;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ISliceListener {
  public static ISliceListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.app.slice.ISliceListener";
  }
  
  public void onSliceUpdated(Slice paramSlice) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.slice.ISliceListener");
      if (paramSlice != null) {
        parcel.writeInt(1);
        paramSlice.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && ISliceListener.Stub.getDefaultImpl() != null) {
        ISliceListener.Stub.getDefaultImpl().onSliceUpdated(paramSlice);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/ISliceListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */