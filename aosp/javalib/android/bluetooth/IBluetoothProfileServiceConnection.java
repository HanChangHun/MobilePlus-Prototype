package android.bluetooth;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBluetoothProfileServiceConnection extends IInterface {
  void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) throws RemoteException;
  
  void onServiceDisconnected(ComponentName paramComponentName) throws RemoteException;
  
  public static class Default implements IBluetoothProfileServiceConnection {
    public IBinder asBinder() {
      return null;
    }
    
    public void onServiceConnected(ComponentName param1ComponentName, IBinder param1IBinder) throws RemoteException {}
    
    public void onServiceDisconnected(ComponentName param1ComponentName) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBluetoothProfileServiceConnection {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothProfileServiceConnection";
    
    static final int TRANSACTION_onServiceConnected = 1;
    
    static final int TRANSACTION_onServiceDisconnected = 2;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothProfileServiceConnection");
    }
    
    public static IBluetoothProfileServiceConnection asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothProfileServiceConnection");
      return (iInterface != null && iInterface instanceof IBluetoothProfileServiceConnection) ? (IBluetoothProfileServiceConnection)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothProfileServiceConnection getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onServiceDisconnected") : "onServiceConnected";
    }
    
    public static boolean setDefaultImpl(IBluetoothProfileServiceConnection param1IBluetoothProfileServiceConnection) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothProfileServiceConnection != null) {
          Proxy.sDefaultImpl = param1IBluetoothProfileServiceConnection;
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
          param1Parcel2.writeString("android.bluetooth.IBluetoothProfileServiceConnection");
          return true;
        } 
        param1Parcel1.enforceInterface("android.bluetooth.IBluetoothProfileServiceConnection");
        if (param1Parcel1.readInt() != 0) {
          ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        onServiceDisconnected((ComponentName)param1Parcel1);
        return true;
      } 
      param1Parcel1.enforceInterface("android.bluetooth.IBluetoothProfileServiceConnection");
      if (param1Parcel1.readInt() != 0) {
        ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      onServiceConnected((ComponentName)param1Parcel2, param1Parcel1.readStrongBinder());
      return true;
    }
    
    private static class Proxy implements IBluetoothProfileServiceConnection {
      public static IBluetoothProfileServiceConnection sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.IBluetoothProfileServiceConnection";
      }
      
      public void onServiceConnected(ComponentName param2ComponentName, IBinder param2IBinder) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.IBluetoothProfileServiceConnection");
          if (param2ComponentName != null) {
            parcel.writeInt(1);
            param2ComponentName.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          parcel.writeStrongBinder(param2IBinder);
          if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothProfileServiceConnection.Stub.getDefaultImpl() != null) {
            IBluetoothProfileServiceConnection.Stub.getDefaultImpl().onServiceConnected(param2ComponentName, param2IBinder);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onServiceDisconnected(ComponentName param2ComponentName) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.IBluetoothProfileServiceConnection");
          if (param2ComponentName != null) {
            parcel.writeInt(1);
            param2ComponentName.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothProfileServiceConnection.Stub.getDefaultImpl() != null) {
            IBluetoothProfileServiceConnection.Stub.getDefaultImpl().onServiceDisconnected(param2ComponentName);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IBluetoothProfileServiceConnection {
    public static IBluetoothProfileServiceConnection sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothProfileServiceConnection";
    }
    
    public void onServiceConnected(ComponentName param1ComponentName, IBinder param1IBinder) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothProfileServiceConnection");
        if (param1ComponentName != null) {
          parcel.writeInt(1);
          param1ComponentName.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeStrongBinder(param1IBinder);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothProfileServiceConnection.Stub.getDefaultImpl() != null) {
          IBluetoothProfileServiceConnection.Stub.getDefaultImpl().onServiceConnected(param1ComponentName, param1IBinder);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onServiceDisconnected(ComponentName param1ComponentName) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothProfileServiceConnection");
        if (param1ComponentName != null) {
          parcel.writeInt(1);
          param1ComponentName.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IBluetoothProfileServiceConnection.Stub.getDefaultImpl() != null) {
          IBluetoothProfileServiceConnection.Stub.getDefaultImpl().onServiceDisconnected(param1ComponentName);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothProfileServiceConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */