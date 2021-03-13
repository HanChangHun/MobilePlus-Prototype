package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ISearchManagerCallback {
  private static final String DESCRIPTOR = "android.app.ISearchManagerCallback";
  
  static final int TRANSACTION_onCancel = 2;
  
  static final int TRANSACTION_onDismiss = 1;
  
  public Stub() {
    attachInterface(this, "android.app.ISearchManagerCallback");
  }
  
  public static ISearchManagerCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.ISearchManagerCallback");
    return (iInterface != null && iInterface instanceof ISearchManagerCallback) ? (ISearchManagerCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static ISearchManagerCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onCancel") : "onDismiss";
  }
  
  public static boolean setDefaultImpl(ISearchManagerCallback paramISearchManagerCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramISearchManagerCallback != null) {
        Proxy.sDefaultImpl = paramISearchManagerCallback;
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
        paramParcel2.writeString("android.app.ISearchManagerCallback");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.ISearchManagerCallback");
      onCancel();
      return true;
    } 
    paramParcel1.enforceInterface("android.app.ISearchManagerCallback");
    onDismiss();
    return true;
  }
  
  private static class Proxy implements ISearchManagerCallback {
    public static ISearchManagerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.ISearchManagerCallback";
    }
    
    public void onCancel() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ISearchManagerCallback");
        if (!this.mRemote.transact(2, parcel, null, 1) && ISearchManagerCallback.Stub.getDefaultImpl() != null) {
          ISearchManagerCallback.Stub.getDefaultImpl().onCancel();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDismiss() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ISearchManagerCallback");
        if (!this.mRemote.transact(1, parcel, null, 1) && ISearchManagerCallback.Stub.getDefaultImpl() != null) {
          ISearchManagerCallback.Stub.getDefaultImpl().onDismiss();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ISearchManagerCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */