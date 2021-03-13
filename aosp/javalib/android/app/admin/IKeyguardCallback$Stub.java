package android.app.admin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControlViewHost;

public abstract class Stub extends Binder implements IKeyguardCallback {
  private static final String DESCRIPTOR = "android.app.admin.IKeyguardCallback";
  
  static final int TRANSACTION_onDismiss = 2;
  
  static final int TRANSACTION_onRemoteContentReady = 1;
  
  public Stub() {
    attachInterface(this, "android.app.admin.IKeyguardCallback");
  }
  
  public static IKeyguardCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.admin.IKeyguardCallback");
    return (iInterface != null && iInterface instanceof IKeyguardCallback) ? (IKeyguardCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IKeyguardCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onDismiss") : "onRemoteContentReady";
  }
  
  public static boolean setDefaultImpl(IKeyguardCallback paramIKeyguardCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIKeyguardCallback != null) {
        Proxy.sDefaultImpl = paramIKeyguardCallback;
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
        paramParcel2.writeString("android.app.admin.IKeyguardCallback");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.admin.IKeyguardCallback");
      onDismiss();
      return true;
    } 
    paramParcel1.enforceInterface("android.app.admin.IKeyguardCallback");
    if (paramParcel1.readInt() != 0) {
      SurfaceControlViewHost.SurfacePackage surfacePackage = (SurfaceControlViewHost.SurfacePackage)SurfaceControlViewHost.SurfacePackage.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onRemoteContentReady((SurfaceControlViewHost.SurfacePackage)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IKeyguardCallback {
    public static IKeyguardCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.admin.IKeyguardCallback";
    }
    
    public void onDismiss() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.admin.IKeyguardCallback");
        if (!this.mRemote.transact(2, parcel, null, 1) && IKeyguardCallback.Stub.getDefaultImpl() != null) {
          IKeyguardCallback.Stub.getDefaultImpl().onDismiss();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage param2SurfacePackage) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.admin.IKeyguardCallback");
        if (param2SurfacePackage != null) {
          parcel.writeInt(1);
          param2SurfacePackage.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IKeyguardCallback.Stub.getDefaultImpl() != null) {
          IKeyguardCallback.Stub.getDefaultImpl().onRemoteContentReady(param2SurfacePackage);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IKeyguardCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */