package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBluetoothMetadataListener extends IInterface {
  void onMetadataChanged(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfbyte) throws RemoteException;
  
  public static class Default implements IBluetoothMetadataListener {
    public IBinder asBinder() {
      return null;
    }
    
    public void onMetadataChanged(BluetoothDevice param1BluetoothDevice, int param1Int, byte[] param1ArrayOfbyte) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBluetoothMetadataListener {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothMetadataListener";
    
    static final int TRANSACTION_onMetadataChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothMetadataListener");
    }
    
    public static IBluetoothMetadataListener asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothMetadataListener");
      return (iInterface != null && iInterface instanceof IBluetoothMetadataListener) ? (IBluetoothMetadataListener)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothMetadataListener getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onMetadataChanged";
    }
    
    public static boolean setDefaultImpl(IBluetoothMetadataListener param1IBluetoothMetadataListener) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothMetadataListener != null) {
          Proxy.sDefaultImpl = param1IBluetoothMetadataListener;
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
        param1Parcel2.writeString("android.bluetooth.IBluetoothMetadataListener");
        return true;
      } 
      param1Parcel1.enforceInterface("android.bluetooth.IBluetoothMetadataListener");
      if (param1Parcel1.readInt() != 0) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      onMetadataChanged((BluetoothDevice)param1Parcel2, param1Parcel1.readInt(), param1Parcel1.createByteArray());
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
  
  private static class Proxy implements IBluetoothMetadataListener {
    public static IBluetoothMetadataListener sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothMetadataListener";
    }
    
    public void onMetadataChanged(BluetoothDevice param1BluetoothDevice, int param1Int, byte[] param1ArrayOfbyte) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.IBluetoothMetadataListener");
        if (param1BluetoothDevice != null) {
          parcel.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        parcel.writeInt(param1Int);
        parcel.writeByteArray(param1ArrayOfbyte);
        if (!this.mRemote.transact(1, parcel, null, 1) && IBluetoothMetadataListener.Stub.getDefaultImpl() != null) {
          IBluetoothMetadataListener.Stub.getDefaultImpl().onMetadataChanged(param1BluetoothDevice, param1Int, param1ArrayOfbyte);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothMetadataListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */