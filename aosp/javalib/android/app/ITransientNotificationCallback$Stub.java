package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ITransientNotificationCallback {
  private static final String DESCRIPTOR = "android.app.ITransientNotificationCallback";
  
  static final int TRANSACTION_onToastHidden = 2;
  
  static final int TRANSACTION_onToastShown = 1;
  
  public Stub() {
    attachInterface(this, "android.app.ITransientNotificationCallback");
  }
  
  public static ITransientNotificationCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.ITransientNotificationCallback");
    return (iInterface != null && iInterface instanceof ITransientNotificationCallback) ? (ITransientNotificationCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static ITransientNotificationCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onToastHidden") : "onToastShown";
  }
  
  public static boolean setDefaultImpl(ITransientNotificationCallback paramITransientNotificationCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramITransientNotificationCallback != null) {
        Proxy.sDefaultImpl = paramITransientNotificationCallback;
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
        paramParcel2.writeString("android.app.ITransientNotificationCallback");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.ITransientNotificationCallback");
      onToastHidden();
      return true;
    } 
    paramParcel1.enforceInterface("android.app.ITransientNotificationCallback");
    onToastShown();
    return true;
  }
  
  private static class Proxy implements ITransientNotificationCallback {
    public static ITransientNotificationCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.ITransientNotificationCallback";
    }
    
    public void onToastHidden() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITransientNotificationCallback");
        if (!this.mRemote.transact(2, parcel, null, 1) && ITransientNotificationCallback.Stub.getDefaultImpl() != null) {
          ITransientNotificationCallback.Stub.getDefaultImpl().onToastHidden();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onToastShown() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITransientNotificationCallback");
        if (!this.mRemote.transact(1, parcel, null, 1) && ITransientNotificationCallback.Stub.getDefaultImpl() != null) {
          ITransientNotificationCallback.Stub.getDefaultImpl().onToastShown();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ITransientNotificationCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */