package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBluetoothAvrcpTarget {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothAvrcpTarget";
  
  static final int TRANSACTION_sendVolumeChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothAvrcpTarget");
  }
  
  public static IBluetoothAvrcpTarget asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothAvrcpTarget");
    return (iInterface != null && iInterface instanceof IBluetoothAvrcpTarget) ? (IBluetoothAvrcpTarget)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothAvrcpTarget getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "sendVolumeChanged";
  }
  
  public static boolean setDefaultImpl(IBluetoothAvrcpTarget paramIBluetoothAvrcpTarget) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothAvrcpTarget != null) {
        Proxy.sDefaultImpl = paramIBluetoothAvrcpTarget;
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
      paramParcel2.writeString("android.bluetooth.IBluetoothAvrcpTarget");
      return true;
    } 
    paramParcel1.enforceInterface("android.bluetooth.IBluetoothAvrcpTarget");
    sendVolumeChanged(paramParcel1.readInt());
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IBluetoothAvrcpTarget {
    public static IBluetoothAvrcpTarget sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothAvrcpTarget";
    }
    
    public void sendVolumeChanged(int param2Int) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothAvrcpTarget");
        parcel1.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothAvrcpTarget.Stub.getDefaultImpl() != null) {
          IBluetoothAvrcpTarget.Stub.getDefaultImpl().sendVolumeChanged(param2Int);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothAvrcpTarget$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */