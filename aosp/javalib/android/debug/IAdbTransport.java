package android.debug;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAdbTransport extends IInterface {
  void onAdbEnabled(boolean paramBoolean, byte paramByte) throws RemoteException;
  
  public static class Default implements IAdbTransport {
    public IBinder asBinder() {
      return null;
    }
    
    public void onAdbEnabled(boolean param1Boolean, byte param1Byte) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAdbTransport {
    private static final String DESCRIPTOR = "android.debug.IAdbTransport";
    
    static final int TRANSACTION_onAdbEnabled = 1;
    
    public Stub() {
      attachInterface(this, "android.debug.IAdbTransport");
    }
    
    public static IAdbTransport asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.debug.IAdbTransport");
      return (iInterface != null && iInterface instanceof IAdbTransport) ? (IAdbTransport)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAdbTransport getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onAdbEnabled";
    }
    
    public static boolean setDefaultImpl(IAdbTransport param1IAdbTransport) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAdbTransport != null) {
          Proxy.sDefaultImpl = param1IAdbTransport;
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
      boolean bool;
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.debug.IAdbTransport");
        return true;
      } 
      param1Parcel1.enforceInterface("android.debug.IAdbTransport");
      if (param1Parcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      onAdbEnabled(bool, param1Parcel1.readByte());
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements IAdbTransport {
      public static IAdbTransport sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.debug.IAdbTransport";
      }
      
      public void onAdbEnabled(boolean param2Boolean, byte param2Byte) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.debug.IAdbTransport");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          parcel1.writeByte(param2Byte);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAdbTransport.Stub.getDefaultImpl() != null) {
            IAdbTransport.Stub.getDefaultImpl().onAdbEnabled(param2Boolean, param2Byte);
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
  
  private static class Proxy implements IAdbTransport {
    public static IAdbTransport sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.debug.IAdbTransport";
    }
    
    public void onAdbEnabled(boolean param1Boolean, byte param1Byte) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.debug.IAdbTransport");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeByte(param1Byte);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAdbTransport.Stub.getDefaultImpl() != null) {
          IAdbTransport.Stub.getDefaultImpl().onAdbEnabled(param1Boolean, param1Byte);
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


/* Location:              /home/chun/Desktop/temp/!/android/debug/IAdbTransport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */