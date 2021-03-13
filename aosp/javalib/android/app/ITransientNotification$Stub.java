package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements ITransientNotification {
  private static final String DESCRIPTOR = "android.app.ITransientNotification";
  
  static final int TRANSACTION_hide = 2;
  
  static final int TRANSACTION_show = 1;
  
  public Stub() {
    attachInterface(this, "android.app.ITransientNotification");
  }
  
  public static ITransientNotification asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.ITransientNotification");
    return (iInterface != null && iInterface instanceof ITransientNotification) ? (ITransientNotification)iInterface : new Proxy(paramIBinder);
  }
  
  public static ITransientNotification getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "hide") : "show";
  }
  
  public static boolean setDefaultImpl(ITransientNotification paramITransientNotification) {
    if (Proxy.sDefaultImpl == null) {
      if (paramITransientNotification != null) {
        Proxy.sDefaultImpl = paramITransientNotification;
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
        paramParcel2.writeString("android.app.ITransientNotification");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.ITransientNotification");
      hide();
      return true;
    } 
    paramParcel1.enforceInterface("android.app.ITransientNotification");
    show(paramParcel1.readStrongBinder());
    return true;
  }
  
  private static class Proxy implements ITransientNotification {
    public static ITransientNotification sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.ITransientNotification";
    }
    
    public void hide() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITransientNotification");
        if (!this.mRemote.transact(2, parcel, null, 1) && ITransientNotification.Stub.getDefaultImpl() != null) {
          ITransientNotification.Stub.getDefaultImpl().hide();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void show(IBinder param2IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.ITransientNotification");
        parcel.writeStrongBinder(param2IBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && ITransientNotification.Stub.getDefaultImpl() != null) {
          ITransientNotification.Stub.getDefaultImpl().show(param2IBinder);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ITransientNotification$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */