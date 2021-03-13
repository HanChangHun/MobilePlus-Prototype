package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICamera extends IInterface {
  void disconnect() throws RemoteException;
  
  public static class Default implements ICamera {
    public IBinder asBinder() {
      return null;
    }
    
    public void disconnect() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICamera {
    private static final String DESCRIPTOR = "android.hardware.ICamera";
    
    static final int TRANSACTION_disconnect = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.ICamera");
    }
    
    public static ICamera asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.ICamera");
      return (iInterface != null && iInterface instanceof ICamera) ? (ICamera)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICamera getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "disconnect";
    }
    
    public static boolean setDefaultImpl(ICamera param1ICamera) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICamera != null) {
          Proxy.sDefaultImpl = param1ICamera;
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
        param1Parcel2.writeString("android.hardware.ICamera");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.ICamera");
      disconnect();
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements ICamera {
      public static ICamera sDefaultImpl;
      
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
          parcel1.writeInterfaceToken("android.hardware.ICamera");
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICamera.Stub.getDefaultImpl() != null) {
            ICamera.Stub.getDefaultImpl().disconnect();
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
        return "android.hardware.ICamera";
      }
    }
  }
  
  private static class Proxy implements ICamera {
    public static ICamera sDefaultImpl;
    
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
        parcel1.writeInterfaceToken("android.hardware.ICamera");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICamera.Stub.getDefaultImpl() != null) {
          ICamera.Stub.getDefaultImpl().disconnect();
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
      return "android.hardware.ICamera";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ICamera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */