package android.hardware.radio;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICloseHandle extends IInterface {
  void close() throws RemoteException;
  
  public static class Default implements ICloseHandle {
    public IBinder asBinder() {
      return null;
    }
    
    public void close() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements ICloseHandle {
    private static final String DESCRIPTOR = "android.hardware.radio.ICloseHandle";
    
    static final int TRANSACTION_close = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.radio.ICloseHandle");
    }
    
    public static ICloseHandle asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.radio.ICloseHandle");
      return (iInterface != null && iInterface instanceof ICloseHandle) ? (ICloseHandle)iInterface : new Proxy(param1IBinder);
    }
    
    public static ICloseHandle getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "close";
    }
    
    public static boolean setDefaultImpl(ICloseHandle param1ICloseHandle) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ICloseHandle != null) {
          Proxy.sDefaultImpl = param1ICloseHandle;
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
        param1Parcel2.writeString("android.hardware.radio.ICloseHandle");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.radio.ICloseHandle");
      close();
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements ICloseHandle {
      public static ICloseHandle sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void close() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.radio.ICloseHandle");
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICloseHandle.Stub.getDefaultImpl() != null) {
            ICloseHandle.Stub.getDefaultImpl().close();
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
        return "android.hardware.radio.ICloseHandle";
      }
    }
  }
  
  private static class Proxy implements ICloseHandle {
    public static ICloseHandle sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void close() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.radio.ICloseHandle");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ICloseHandle.Stub.getDefaultImpl() != null) {
          ICloseHandle.Stub.getDefaultImpl().close();
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
      return "android.hardware.radio.ICloseHandle";
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/ICloseHandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */