package android.app.admin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IKeyguardClient extends IInterface {
  void onCreateKeyguardSurface(IBinder paramIBinder, IKeyguardCallback paramIKeyguardCallback) throws RemoteException;
  
  public static class Default implements IKeyguardClient {
    public IBinder asBinder() {
      return null;
    }
    
    public void onCreateKeyguardSurface(IBinder param1IBinder, IKeyguardCallback param1IKeyguardCallback) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IKeyguardClient {
    private static final String DESCRIPTOR = "android.app.admin.IKeyguardClient";
    
    static final int TRANSACTION_onCreateKeyguardSurface = 1;
    
    public Stub() {
      attachInterface(this, "android.app.admin.IKeyguardClient");
    }
    
    public static IKeyguardClient asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.admin.IKeyguardClient");
      return (iInterface != null && iInterface instanceof IKeyguardClient) ? (IKeyguardClient)iInterface : new Proxy(param1IBinder);
    }
    
    public static IKeyguardClient getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onCreateKeyguardSurface";
    }
    
    public static boolean setDefaultImpl(IKeyguardClient param1IKeyguardClient) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IKeyguardClient != null) {
          Proxy.sDefaultImpl = param1IKeyguardClient;
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
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.app.admin.IKeyguardClient");
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.admin.IKeyguardClient");
      onCreateKeyguardSurface(param1Parcel1.readStrongBinder(), IKeyguardCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
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
  
  private static class Proxy implements IKeyguardClient {
    public static IKeyguardClient sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.admin.IKeyguardClient";
    }
    
    public void onCreateKeyguardSurface(IBinder param1IBinder, IKeyguardCallback param1IKeyguardCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.admin.IKeyguardClient");
        parcel.writeStrongBinder(param1IBinder);
        if (param1IKeyguardCallback != null) {
          iBinder = param1IKeyguardCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IKeyguardClient.Stub.getDefaultImpl() != null) {
          IKeyguardClient.Stub.getDefaultImpl().onCreateKeyguardSurface(param1IBinder, param1IKeyguardCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/IKeyguardClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */