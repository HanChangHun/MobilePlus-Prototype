package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBluetoothCallback extends IInterface {
  void onBluetoothStateChange(int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IBluetoothCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onBluetoothStateChange(int param1Int1, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBluetoothCallback {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothCallback";
    
    static final int TRANSACTION_onBluetoothStateChange = 1;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothCallback");
    }
    
    public static IBluetoothCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothCallback");
      return (iInterface != null && iInterface instanceof IBluetoothCallback) ? (IBluetoothCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onBluetoothStateChange";
    }
    
    public static boolean setDefaultImpl(IBluetoothCallback param1IBluetoothCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothCallback != null) {
          Proxy.sDefaultImpl = param1IBluetoothCallback;
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
        param1Parcel2.writeString("android.bluetooth.IBluetoothCallback");
        return true;
      } 
      param1Parcel1.enforceInterface("android.bluetooth.IBluetoothCallback");
      onBluetoothStateChange(param1Parcel1.readInt(), param1Parcel1.readInt());
      param1Parcel2.writeNoException();
      return true;
    }
    
    private static class Proxy implements IBluetoothCallback {
      public static IBluetoothCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.IBluetoothCallback";
      }
      
      public void onBluetoothStateChange(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothCallback");
          parcel1.writeInt(param2Int1);
          parcel1.writeInt(param2Int2);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothCallback.Stub.getDefaultImpl() != null) {
            IBluetoothCallback.Stub.getDefaultImpl().onBluetoothStateChange(param2Int1, param2Int2);
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
  
  private static class Proxy implements IBluetoothCallback {
    public static IBluetoothCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothCallback";
    }
    
    public void onBluetoothStateChange(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothCallback");
        parcel1.writeInt(param1Int1);
        parcel1.writeInt(param1Int2);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothCallback.Stub.getDefaultImpl() != null) {
          IBluetoothCallback.Stub.getDefaultImpl().onBluetoothStateChange(param1Int1, param1Int2);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */