package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IStopUserCallback {
  private static final String DESCRIPTOR = "android.app.IStopUserCallback";
  
  static final int TRANSACTION_userStopAborted = 2;
  
  static final int TRANSACTION_userStopped = 1;
  
  public Stub() {
    attachInterface(this, "android.app.IStopUserCallback");
  }
  
  public static IStopUserCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IStopUserCallback");
    return (iInterface != null && iInterface instanceof IStopUserCallback) ? (IStopUserCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IStopUserCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "userStopAborted") : "userStopped";
  }
  
  public static boolean setDefaultImpl(IStopUserCallback paramIStopUserCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIStopUserCallback != null) {
        Proxy.sDefaultImpl = paramIStopUserCallback;
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
        paramParcel2.writeString("android.app.IStopUserCallback");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.IStopUserCallback");
      userStopAborted(paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IStopUserCallback");
    userStopped(paramParcel1.readInt());
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IStopUserCallback {
    public static IStopUserCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IStopUserCallback";
    }
    
    public void userStopAborted(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IStopUserCallback");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IStopUserCallback.Stub.getDefaultImpl() != null) {
          IStopUserCallback.Stub.getDefaultImpl().userStopAborted(param2Int);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void userStopped(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.app.IStopUserCallback");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IStopUserCallback.Stub.getDefaultImpl() != null) {
          IStopUserCallback.Stub.getDefaultImpl().userStopped(param2Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IStopUserCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */