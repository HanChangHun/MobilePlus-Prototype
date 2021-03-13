package android.content;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements ISyncAdapterUnsyncableAccountCallback {
  public static ISyncAdapterUnsyncableAccountCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.ISyncAdapterUnsyncableAccountCallback";
  }
  
  public void onUnsyncableAccountDone(boolean paramBoolean) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      parcel.writeInterfaceToken("android.content.ISyncAdapterUnsyncableAccountCallback");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (!this.mRemote.transact(1, parcel, null, 1) && ISyncAdapterUnsyncableAccountCallback.Stub.getDefaultImpl() != null) {
        ISyncAdapterUnsyncableAccountCallback.Stub.getDefaultImpl().onUnsyncableAccountDone(paramBoolean);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncAdapterUnsyncableAccountCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */