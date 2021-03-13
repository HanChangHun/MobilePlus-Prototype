package android.content;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IOnPrimaryClipChangedListener {
  public static IOnPrimaryClipChangedListener sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void dispatchPrimaryClipChanged() throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.IOnPrimaryClipChangedListener");
      if (!this.mRemote.transact(1, parcel, null, 1) && IOnPrimaryClipChangedListener.Stub.getDefaultImpl() != null) {
        IOnPrimaryClipChangedListener.Stub.getDefaultImpl().dispatchPrimaryClipChanged();
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.IOnPrimaryClipChangedListener";
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IOnPrimaryClipChangedListener$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */