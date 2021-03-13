package android.bluetooth;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBluetoothProfileServiceConnection {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothProfileServiceConnection";
  
  static final int TRANSACTION_onServiceConnected = 1;
  
  static final int TRANSACTION_onServiceDisconnected = 2;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothProfileServiceConnection");
  }
  
  public static IBluetoothProfileServiceConnection asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothProfileServiceConnection");
    return (iInterface != null && iInterface instanceof IBluetoothProfileServiceConnection) ? (IBluetoothProfileServiceConnection)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothProfileServiceConnection getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onServiceDisconnected") : "onServiceConnected";
  }
  
  public static boolean setDefaultImpl(IBluetoothProfileServiceConnection paramIBluetoothProfileServiceConnection) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothProfileServiceConnection != null) {
        Proxy.sDefaultImpl = paramIBluetoothProfileServiceConnection;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.bluetooth.IBluetoothProfileServiceConnection");
        return true;
      } 
      paramParcel1.enforceInterface("android.bluetooth.IBluetoothProfileServiceConnection");
      if (paramParcel1.readInt() != 0) {
        ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      onServiceDisconnected((ComponentName)paramParcel1);
      return true;
    } 
    paramParcel1.enforceInterface("android.bluetooth.IBluetoothProfileServiceConnection");
    if (paramParcel1.readInt() != 0) {
      ComponentName componentName = (ComponentName)ComponentName.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    onServiceConnected((ComponentName)paramParcel2, paramParcel1.readStrongBinder());
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothProfileServiceConnection$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */