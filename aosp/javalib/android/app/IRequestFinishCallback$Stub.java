package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IRequestFinishCallback {
  private static final String DESCRIPTOR = "android.app.IRequestFinishCallback";
  
  static final int TRANSACTION_requestFinish = 1;
  
  public Stub() {
    attachInterface(this, "android.app.IRequestFinishCallback");
  }
  
  public static IRequestFinishCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IRequestFinishCallback");
    return (iInterface != null && iInterface instanceof IRequestFinishCallback) ? (IRequestFinishCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IRequestFinishCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "requestFinish";
  }
  
  public static boolean setDefaultImpl(IRequestFinishCallback paramIRequestFinishCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIRequestFinishCallback != null) {
        Proxy.sDefaultImpl = paramIRequestFinishCallback;
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
      paramParcel2.writeString("android.app.IRequestFinishCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IRequestFinishCallback");
    requestFinish();
    return true;
  }
  
  private static class Proxy implements IRequestFinishCallback {
    public static IRequestFinishCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IRequestFinishCallback";
    }
    
    public void requestFinish() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IRequestFinishCallback");
        if (!this.mRemote.transact(1, parcel, null, 1) && IRequestFinishCallback.Stub.getDefaultImpl() != null) {
          IRequestFinishCallback.Stub.getDefaultImpl().requestFinish();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IRequestFinishCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */