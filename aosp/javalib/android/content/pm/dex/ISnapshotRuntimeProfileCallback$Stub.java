package android.content.pm.dex;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISnapshotRuntimeProfileCallback {
  private static final String DESCRIPTOR = "android.content.pm.dex.ISnapshotRuntimeProfileCallback";
  
  static final int TRANSACTION_onError = 2;
  
  static final int TRANSACTION_onSuccess = 1;
  
  public Stub() {
    attachInterface(this, "android.content.pm.dex.ISnapshotRuntimeProfileCallback");
  }
  
  public static ISnapshotRuntimeProfileCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
    return (iInterface != null && iInterface instanceof ISnapshotRuntimeProfileCallback) ? (ISnapshotRuntimeProfileCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISnapshotRuntimeProfileCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onError") : "onSuccess";
  }
  
  public static boolean setDefaultImpl(ISnapshotRuntimeProfileCallback paramISnapshotRuntimeProfileCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISnapshotRuntimeProfileCallback != null) {
        Proxy.sDefaultImpl = paramISnapshotRuntimeProfileCallback;
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
        paramParcel2.writeString("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
        return true;
      } 
      paramParcel1.enforceInterface("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
      onError(paramParcel1.readInt());
      return true;
    } 
    paramParcel1.enforceInterface("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
    if (paramParcel1.readInt() != 0) {
      ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onSuccess((ParcelFileDescriptor)paramParcel1);
    return true;
  }
  
  private static class Proxy implements ISnapshotRuntimeProfileCallback {
    public static ISnapshotRuntimeProfileCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.pm.dex.ISnapshotRuntimeProfileCallback";
    }
    
    public void onError(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl() != null) {
          ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl().onError(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSuccess(ParcelFileDescriptor param2ParcelFileDescriptor) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.pm.dex.ISnapshotRuntimeProfileCallback");
        if (param2ParcelFileDescriptor != null) {
          parcel.writeInt(1);
          param2ParcelFileDescriptor.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl() != null) {
          ISnapshotRuntimeProfileCallback.Stub.getDefaultImpl().onSuccess(param2ParcelFileDescriptor);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/ISnapshotRuntimeProfileCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */