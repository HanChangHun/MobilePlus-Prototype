package android.content.pm.dex;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IArtManager {
  public static IArtManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.pm.dex.IArtManager";
  }
  
  public boolean isRuntimeProfilingEnabled(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.pm.dex.IArtManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(2, parcel1, parcel2, 0) && IArtManager.Stub.getDefaultImpl() != null) {
        bool = IArtManager.Stub.getDefaultImpl().isRuntimeProfilingEnabled(paramInt, paramString);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void snapshotRuntimeProfile(int paramInt, String paramString1, String paramString2, ISnapshotRuntimeProfileCallback paramISnapshotRuntimeProfileCallback, String paramString3) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.content.pm.dex.IArtManager");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (paramISnapshotRuntimeProfileCallback != null) {
        iBinder = paramISnapshotRuntimeProfileCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString3);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IArtManager.Stub.getDefaultImpl() != null) {
        IArtManager.Stub.getDefaultImpl().snapshotRuntimeProfile(paramInt, paramString1, paramString2, paramISnapshotRuntimeProfileCallback, paramString3);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/IArtManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */