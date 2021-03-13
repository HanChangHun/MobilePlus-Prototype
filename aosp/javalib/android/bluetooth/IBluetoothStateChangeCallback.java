package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBluetoothStateChangeCallback extends IInterface {
  void onBluetoothStateChange(boolean paramBoolean) throws RemoteException;
  
  public static class Default implements IBluetoothStateChangeCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onBluetoothStateChange(boolean param1Boolean) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBluetoothStateChangeCallback {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothStateChangeCallback";
    
    static final int TRANSACTION_onBluetoothStateChange = 1;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothStateChangeCallback");
    }
    
    public static IBluetoothStateChangeCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothStateChangeCallback");
      return (iInterface != null && iInterface instanceof IBluetoothStateChangeCallback) ? (IBluetoothStateChangeCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothStateChangeCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onBluetoothStateChange";
    }
    
    public static boolean setDefaultImpl(IBluetoothStateChangeCallback param1IBluetoothStateChangeCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothStateChangeCallback != null) {
          Proxy.sDefaultImpl = param1IBluetoothStateChangeCallback;
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
        param1Parcel2.writeString("android.bluetooth.IBluetoothStateChangeCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.bluetooth.IBluetoothStateChangeCallback");
      if (param1Parcel1.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      onBluetoothStateChange(bool);
      return true;
    }
    
    private static class Proxy implements IBluetoothStateChangeCallback {
      public static IBluetoothStateChangeCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.IBluetoothStateChangeCallback";
      }
      
      public void onBluetoothStateChange(boolean param2Boolean) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.bluetooth.IBluetoothStateChangeCallback");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothStateChangeCallback.Stub.getDefaultImpl() != null) {
            IBluetoothStateChangeCallback.Stub.getDefaultImpl().onBluetoothStateChange(param2Boolean);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IBluetoothStateChangeCallback {
    public static IBluetoothStateChangeCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothStateChangeCallback";
    }
    
    public void onBluetoothStateChange(boolean param1Boolean) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothStateChangeCallback");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothStateChangeCallback.Stub.getDefaultImpl() != null) {
          IBluetoothStateChangeCallback.Stub.getDefaultImpl().onBluetoothStateChange(param1Boolean);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothStateChangeCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */