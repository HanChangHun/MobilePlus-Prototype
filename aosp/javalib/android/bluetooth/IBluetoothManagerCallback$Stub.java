package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBluetoothManagerCallback {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothManagerCallback";
  
  static final int TRANSACTION_onBluetoothServiceDown = 2;
  
  static final int TRANSACTION_onBluetoothServiceUp = 1;
  
  static final int TRANSACTION_onBrEdrDown = 3;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothManagerCallback");
  }
  
  public static IBluetoothManagerCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothManagerCallback");
    return (iInterface != null && iInterface instanceof IBluetoothManagerCallback) ? (IBluetoothManagerCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothManagerCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "onBrEdrDown") : "onBluetoothServiceDown") : "onBluetoothServiceUp";
  }
  
  public static boolean setDefaultImpl(IBluetoothManagerCallback paramIBluetoothManagerCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothManagerCallback != null) {
        Proxy.sDefaultImpl = paramIBluetoothManagerCallback;
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
        if (paramInt1 != 3) {
          if (paramInt1 != 1598968902)
            return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
          paramParcel2.writeString("android.bluetooth.IBluetoothManagerCallback");
          return true;
        } 
        paramParcel1.enforceInterface("android.bluetooth.IBluetoothManagerCallback");
        onBrEdrDown();
        return true;
      } 
      paramParcel1.enforceInterface("android.bluetooth.IBluetoothManagerCallback");
      onBluetoothServiceDown();
      return true;
    } 
    paramParcel1.enforceInterface("android.bluetooth.IBluetoothManagerCallback");
    onBluetoothServiceUp(IBluetooth.Stub.asInterface(paramParcel1.readStrongBinder()));
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothManagerCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */