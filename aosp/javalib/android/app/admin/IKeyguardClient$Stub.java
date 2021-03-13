package android.app.admin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IKeyguardClient {
  private static final String DESCRIPTOR = "android.app.admin.IKeyguardClient";
  
  static final int TRANSACTION_onCreateKeyguardSurface = 1;
  
  public Stub() {
    attachInterface(this, "android.app.admin.IKeyguardClient");
  }
  
  public static IKeyguardClient asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.admin.IKeyguardClient");
    return (iInterface != null && iInterface instanceof IKeyguardClient) ? (IKeyguardClient)iInterface : new Proxy(paramIBinder);
  }
  
  public static IKeyguardClient getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onCreateKeyguardSurface";
  }
  
  public static boolean setDefaultImpl(IKeyguardClient paramIKeyguardClient) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIKeyguardClient != null) {
        Proxy.sDefaultImpl = paramIKeyguardClient;
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
      paramParcel2.writeString("android.app.admin.IKeyguardClient");
      return true;
    } 
    paramParcel1.enforceInterface("android.app.admin.IKeyguardClient");
    onCreateKeyguardSurface(paramParcel1.readStrongBinder(), IKeyguardCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
    return true;
  }
  
  private static class Proxy implements IKeyguardClient {
    public static IKeyguardClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.admin.IKeyguardClient";
    }
    
    public void onCreateKeyguardSurface(IBinder param2IBinder, IKeyguardCallback param2IKeyguardCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.admin.IKeyguardClient");
        parcel.writeStrongBinder(param2IBinder);
        if (param2IKeyguardCallback != null) {
          iBinder = param2IKeyguardCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IKeyguardClient.Stub.getDefaultImpl() != null) {
          IKeyguardClient.Stub.getDefaultImpl().onCreateKeyguardSurface(param2IBinder, param2IKeyguardCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IKeyguardClient$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */