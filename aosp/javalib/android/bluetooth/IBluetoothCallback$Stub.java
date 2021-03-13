package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBluetoothCallback {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothCallback";
  
  static final int TRANSACTION_onBluetoothStateChange = 1;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothCallback");
  }
  
  public static IBluetoothCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothCallback");
    return (iInterface != null && iInterface instanceof IBluetoothCallback) ? (IBluetoothCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onBluetoothStateChange";
  }
  
  public static boolean setDefaultImpl(IBluetoothCallback paramIBluetoothCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothCallback != null) {
        Proxy.sDefaultImpl = paramIBluetoothCallback;
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
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.bluetooth.IBluetoothCallback");
      return true;
    } 
    paramParcel1.enforceInterface("android.bluetooth.IBluetoothCallback");
    onBluetoothStateChange(paramParcel1.readInt(), paramParcel1.readInt());
    paramParcel2.writeNoException();
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */