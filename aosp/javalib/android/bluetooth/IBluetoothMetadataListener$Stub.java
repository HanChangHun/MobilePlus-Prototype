package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IBluetoothMetadataListener {
  private static final String DESCRIPTOR = "android.bluetooth.IBluetoothMetadataListener";
  
  static final int TRANSACTION_onMetadataChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.IBluetoothMetadataListener");
  }
  
  public static IBluetoothMetadataListener asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.IBluetoothMetadataListener");
    return (iInterface != null && iInterface instanceof IBluetoothMetadataListener) ? (IBluetoothMetadataListener)iInterface : new Proxy(paramIBinder);
  }
  
  public static IBluetoothMetadataListener getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onMetadataChanged";
  }
  
  public static boolean setDefaultImpl(IBluetoothMetadataListener paramIBluetoothMetadataListener) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIBluetoothMetadataListener != null) {
        Proxy.sDefaultImpl = paramIBluetoothMetadataListener;
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
      paramParcel2.writeString("android.bluetooth.IBluetoothMetadataListener");
      return true;
    } 
    paramParcel1.enforceInterface("android.bluetooth.IBluetoothMetadataListener");
    if (paramParcel1.readInt() != 0) {
      BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    onMetadataChanged((BluetoothDevice)paramParcel2, paramParcel1.readInt(), paramParcel1.createByteArray());
    return true;
  }
  
  private static class Proxy implements IBluetoothMetadataListener {
    public static IBluetoothMetadataListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothMetadataListener";
    }
    
    public void onMetadataChanged(BluetoothDevice param2BluetoothDevice, int param2Int, byte[] param2ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothMetadataListener");
        if (param2BluetoothDevice != null) {
          parcel.writeInt(1);
          param2BluetoothDevice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param2Int);
        parcel.writeByteArray(param2ArrayOfbyte);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothMetadataListener.Stub.getDefaultImpl() != null) {
          IBluetoothMetadataListener.Stub.getDefaultImpl().onMetadataChanged(param2BluetoothDevice, param2Int, param2ArrayOfbyte);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothMetadataListener$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */