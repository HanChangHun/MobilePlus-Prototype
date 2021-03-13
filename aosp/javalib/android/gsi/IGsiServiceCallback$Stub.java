package android.gsi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IGsiServiceCallback {
  private static final String DESCRIPTOR = "android.gsi.IGsiServiceCallback";
  
  static final int TRANSACTION_onResult = 1;
  
  public Stub() {
    attachInterface(this, "android.gsi.IGsiServiceCallback");
  }
  
  public static IGsiServiceCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.gsi.IGsiServiceCallback");
    return (iInterface != null && iInterface instanceof IGsiServiceCallback) ? (IGsiServiceCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IGsiServiceCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onResult";
  }
  
  public static boolean setDefaultImpl(IGsiServiceCallback paramIGsiServiceCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIGsiServiceCallback != null) {
        Proxy.sDefaultImpl = paramIGsiServiceCallback;
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
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.gsi.IGsiServiceCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.gsi.IGsiServiceCallback");
    onResult(paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IGsiServiceCallback {
    public static IGsiServiceCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.gsi.IGsiServiceCallback";
    }
    
    public void onResult(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.gsi.IGsiServiceCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IGsiServiceCallback.Stub.getDefaultImpl() != null) {
          IGsiServiceCallback.Stub.getDefaultImpl().onResult(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/gsi/IGsiServiceCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */