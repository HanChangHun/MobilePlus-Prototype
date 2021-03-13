package android.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISyncAdapterUnsyncableAccountCallback {
  private static final String DESCRIPTOR = "android.content.ISyncAdapterUnsyncableAccountCallback";
  
  static final int TRANSACTION_onUnsyncableAccountDone = 1;
  
  public Stub() {
    attachInterface(this, "android.content.ISyncAdapterUnsyncableAccountCallback");
  }
  
  public static ISyncAdapterUnsyncableAccountCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.ISyncAdapterUnsyncableAccountCallback");
    return (iInterface != null && iInterface instanceof ISyncAdapterUnsyncableAccountCallback) ? (ISyncAdapterUnsyncableAccountCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISyncAdapterUnsyncableAccountCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onUnsyncableAccountDone";
  }
  
  public static boolean setDefaultImpl(ISyncAdapterUnsyncableAccountCallback paramISyncAdapterUnsyncableAccountCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISyncAdapterUnsyncableAccountCallback != null) {
        Proxy.sDefaultImpl = paramISyncAdapterUnsyncableAccountCallback;
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
    boolean bool;
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.content.ISyncAdapterUnsyncableAccountCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.ISyncAdapterUnsyncableAccountCallback");
    if (paramParcel1.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    onUnsyncableAccountDone(bool);
    return true;
  }
  
  private static class Proxy implements ISyncAdapterUnsyncableAccountCallback {
    public static ISyncAdapterUnsyncableAccountCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.ISyncAdapterUnsyncableAccountCallback";
    }
    
    public void onUnsyncableAccountDone(boolean param2Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.content.ISyncAdapterUnsyncableAccountCallback");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && ISyncAdapterUnsyncableAccountCallback.Stub.getDefaultImpl() != null) {
          ISyncAdapterUnsyncableAccountCallback.Stub.getDefaultImpl().onUnsyncableAccountDone(param2Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ISyncAdapterUnsyncableAccountCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */