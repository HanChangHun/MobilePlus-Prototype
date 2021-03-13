package android.hardware.camera2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICameraOfflineSession extends IInterface {
  void disconnect() throws RemoteException;
  
  public static class Default implements ICameraOfflineSession {
    public IBinder asBinder() {
      return null;
    }
    
    public void disconnect() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICameraOfflineSession {
    private static final String DESCRIPTOR = "android.hardware.camera2.ICameraOfflineSession";
    
    static final int TRANSACTION_disconnect = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.camera2.ICameraOfflineSession");
    }
    
    public static ICameraOfflineSession asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.camera2.ICameraOfflineSession");
      return (iInterface != null && iInterface instanceof ICameraOfflineSession) ? (ICameraOfflineSession)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICameraOfflineSession getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "disconnect";
    }
    
    public static boolean setDefaultImpl(ICameraOfflineSession param1ICameraOfflineSession) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICameraOfflineSession != null) {
          Proxy.sDefaultImpl = param1ICameraOfflineSession;
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
        param1Parcel2.writeString("android.hardware.camera2.ICameraOfflineSession");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.camera2.ICameraOfflineSession");
      disconnect();
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements ICameraOfflineSession {
      public static ICameraOfflineSession sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void disconnect() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.camera2.ICameraOfflineSession");
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICameraOfflineSession.Stub.getDefaultImpl() != null) {
            ICameraOfflineSession.Stub.getDefaultImpl().disconnect();
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.camera2.ICameraOfflineSession";
      }
    }
  }
  
  private static class Proxy implements ICameraOfflineSession {
    public static ICameraOfflineSession sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void disconnect() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.camera2.ICameraOfflineSession");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICameraOfflineSession.Stub.getDefaultImpl() != null) {
          ICameraOfflineSession.Stub.getDefaultImpl().disconnect();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.camera2.ICameraOfflineSession";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/ICameraOfflineSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */