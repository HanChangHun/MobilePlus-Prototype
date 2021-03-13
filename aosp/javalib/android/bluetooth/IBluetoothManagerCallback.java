package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBluetoothManagerCallback extends IInterface {
  void onBluetoothServiceDown() throws RemoteException;
  
  void onBluetoothServiceUp(IBluetooth paramIBluetooth) throws RemoteException;
  
  void onBrEdrDown() throws RemoteException;
  
  public static class Default implements IBluetoothManagerCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onBluetoothServiceDown() throws RemoteException {}
    
    public void onBluetoothServiceUp(IBluetooth param1IBluetooth) throws RemoteException {}
    
    public void onBrEdrDown() throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBluetoothManagerCallback {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothManagerCallback";
    
    static final int TRANSACTION_onBluetoothServiceDown = 2;
    
    static final int TRANSACTION_onBluetoothServiceUp = 1;
    
    static final int TRANSACTION_onBrEdrDown = 3;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothManagerCallback");
    }
    
    public static IBluetoothManagerCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothManagerCallback");
      return (iInterface != null && iInterface instanceof IBluetoothManagerCallback) ? (IBluetoothManagerCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothManagerCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? null : "onBrEdrDown") : "onBluetoothServiceDown") : "onBluetoothServiceUp";
    }
    
    public static boolean setDefaultImpl(IBluetoothManagerCallback param1IBluetoothManagerCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothManagerCallback != null) {
          Proxy.sDefaultImpl = param1IBluetoothManagerCallback;
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
          if (param1Int1 != 3) {
            if (param1Int1 != 1598968902)
              return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
            param1Parcel2.writeString("android.bluetooth.IBluetoothManagerCallback");
            return true;
          } 
          param1Parcel1.enforceInterface("android.bluetooth.IBluetoothManagerCallback");
          onBrEdrDown();
          return true;
        } 
        param1Parcel1.enforceInterface("android.bluetooth.IBluetoothManagerCallback");
        onBluetoothServiceDown();
        return true;
      } 
      param1Parcel1.enforceInterface("android.bluetooth.IBluetoothManagerCallback");
      onBluetoothServiceUp(IBluetooth.Stub.asInterface(param1Parcel1.readStrongBinder()));
      return true;
    }
    
    private static class Proxy implements IBluetoothManagerCallback {
      public static IBluetoothManagerCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.IBluetoothManagerCallback";
      }
      
      public void onBluetoothServiceDown() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
          if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothManagerCallback.Stub.getDefaultImpl() != null) {
            IBluetoothManagerCallback.Stub.getDefaultImpl().onBluetoothServiceDown();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onBluetoothServiceUp(IBluetooth param2IBluetooth) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
          if (param2IBluetooth != null) {
            iBinder = param2IBluetooth.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothManagerCallback.Stub.getDefaultImpl() != null) {
            IBluetoothManagerCallback.Stub.getDefaultImpl().onBluetoothServiceUp(param2IBluetooth);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onBrEdrDown() throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
          if (!this.mRemote.transact(3, parcel, null, 1) && IBluetoothManagerCallback.Stub.getDefaultImpl() != null) {
            IBluetoothManagerCallback.Stub.getDefaultImpl().onBrEdrDown();
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IBluetoothManagerCallback {
    public static IBluetoothManagerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothManagerCallback";
    }
    
    public void onBluetoothServiceDown() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
        if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothManagerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothManagerCallback.Stub.getDefaultImpl().onBluetoothServiceDown();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onBluetoothServiceUp(IBluetooth param1IBluetooth) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
        if (param1IBluetooth != null) {
          iBinder = param1IBluetooth.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothManagerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothManagerCallback.Stub.getDefaultImpl().onBluetoothServiceUp(param1IBluetooth);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onBrEdrDown() throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothManagerCallback");
        if (!this.mRemote.transact(3, parcel, null, 1) && IBluetoothManagerCallback.Stub.getDefaultImpl() != null) {
          IBluetoothManagerCallback.Stub.getDefaultImpl().onBrEdrDown();
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothManagerCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */