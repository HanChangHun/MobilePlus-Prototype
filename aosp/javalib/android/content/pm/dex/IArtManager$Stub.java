package android.content.pm.dex;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IArtManager {
  private static final String DESCRIPTOR = "android.content.pm.dex.IArtManager";
  
  static final int TRANSACTION_isRuntimeProfilingEnabled = 2;
  
  static final int TRANSACTION_snapshotRuntimeProfile = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.dex.IArtManager");
  }
  
  public static IArtManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.dex.IArtManager");
    return (iInterface != null && iInterface instanceof IArtManager) ? (IArtManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IArtManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "isRuntimeProfilingEnabled") : "snapshotRuntimeProfile";
  }
  
  public static boolean setDefaultImpl(IArtManager paramIArtManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIArtManager != null) {
        Proxy.sDefaultImpl = paramIArtManager;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.content.pm.dex.IArtManager");
        return true;
      } 
      paramParcel1.enforceInterface("android.content.pm.dex.IArtManager");
      boolean bool = isRuntimeProfilingEnabled(paramParcel1.readInt(), paramParcel1.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeInt(bool);
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.dex.IArtManager");
    snapshotRuntimeProfile(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString(), ISnapshotRuntimeProfileCallback.Stub.asInterface(paramParcel1.readStrongBinder()), paramParcel1.readString());
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IArtManager {
    public static IArtManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.dex.IArtManager";
    }
    
    public boolean isRuntimeProfilingEnabled(int param2Int, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.pm.dex.IArtManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String);
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(2, parcel1, parcel2, 0) && IArtManager.Stub.getDefaultImpl() != null) {
          bool = IArtManager.Stub.getDefaultImpl().isRuntimeProfilingEnabled(param2Int, param2String);
          return bool;
        } 
        parcel2.readException();
        param2Int = parcel2.readInt();
        if (param2Int != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void snapshotRuntimeProfile(int param2Int, String param2String1, String param2String2, ISnapshotRuntimeProfileCallback param2ISnapshotRuntimeProfileCallback, String param2String3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.content.pm.dex.IArtManager");
        parcel1.writeInt(param2Int);
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (param2ISnapshotRuntimeProfileCallback != null) {
          iBinder = param2ISnapshotRuntimeProfileCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(param2String3);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IArtManager.Stub.getDefaultImpl() != null) {
          IArtManager.Stub.getDefaultImpl().snapshotRuntimeProfile(param2Int, param2String1, param2String2, param2ISnapshotRuntimeProfileCallback, param2String3);
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
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/IArtManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */