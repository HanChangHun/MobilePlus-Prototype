package android.app.admin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControlViewHost;

public interface IKeyguardCallback extends IInterface {
  void onDismiss() throws RemoteException;
  
  void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage paramSurfacePackage) throws RemoteException;
  
  public static class Default implements IKeyguardCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onDismiss() throws RemoteException {}
    
    public void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage param1SurfacePackage) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IKeyguardCallback {
    private static final String DESCRIPTOR = "android.app.admin.IKeyguardCallback";
    
    static final int TRANSACTION_onDismiss = 2;
    
    static final int TRANSACTION_onRemoteContentReady = 1;
    
    public Stub() {
      attachInterface(this, "android.app.admin.IKeyguardCallback");
    }
    
    public static IKeyguardCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.admin.IKeyguardCallback");
      return (iInterface != null && iInterface instanceof IKeyguardCallback) ? (IKeyguardCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IKeyguardCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onDismiss") : "onRemoteContentReady";
    }
    
    public static boolean setDefaultImpl(IKeyguardCallback param1IKeyguardCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IKeyguardCallback != null) {
          Proxy.sDefaultImpl = param1IKeyguardCallback;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.app.admin.IKeyguardCallback");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.admin.IKeyguardCallback");
        onDismiss();
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.admin.IKeyguardCallback");
      if (param1Parcel1.readInt() != 0) {
        SurfaceControlViewHost.SurfacePackage surfacePackage = (SurfaceControlViewHost.SurfacePackage)SurfaceControlViewHost.SurfacePackage.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onRemoteContentReady((SurfaceControlViewHost.SurfacePackage)param1Parcel1);
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
  
  private static class Proxy implements IKeyguardCallback {
    public static IKeyguardCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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
    
    public void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage param1SurfacePackage) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.admin.IKeyguardCallback");
        if (param1SurfacePackage != null) {
          parcel.writeInt(1);
          param1SurfacePackage.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IKeyguardCallback.Stub.getDefaultImpl() != null) {
          IKeyguardCallback.Stub.getDefaultImpl().onRemoteContentReady(param1SurfacePackage);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IKeyguardCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */