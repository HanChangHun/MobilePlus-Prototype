package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelUuid;
import android.os.RemoteException;

public interface IBluetoothSocketManager extends IInterface {
  ParcelFileDescriptor connectSocket(BluetoothDevice paramBluetoothDevice, int paramInt1, ParcelUuid paramParcelUuid, int paramInt2, int paramInt3) throws RemoteException;
  
  ParcelFileDescriptor createSocketChannel(int paramInt1, String paramString, ParcelUuid paramParcelUuid, int paramInt2, int paramInt3) throws RemoteException;
  
  void requestMaximumTxDataLength(BluetoothDevice paramBluetoothDevice) throws RemoteException;
  
  public static class Default implements IBluetoothSocketManager {
    public IBinder asBinder() {
      return null;
    }
    
    public ParcelFileDescriptor connectSocket(BluetoothDevice param1BluetoothDevice, int param1Int1, ParcelUuid param1ParcelUuid, int param1Int2, int param1Int3) throws RemoteException {
      return null;
    }
    
    public ParcelFileDescriptor createSocketChannel(int param1Int1, String param1String, ParcelUuid param1ParcelUuid, int param1Int2, int param1Int3) throws RemoteException {
      return null;
    }
    
    public void requestMaximumTxDataLength(BluetoothDevice param1BluetoothDevice) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IBluetoothSocketManager {
    private static final String DESCRIPTOR = "android.bluetooth.IBluetoothSocketManager";
    
    static final int TRANSACTION_connectSocket = 1;
    
    static final int TRANSACTION_createSocketChannel = 2;
    
    static final int TRANSACTION_requestMaximumTxDataLength = 3;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.IBluetoothSocketManager");
    }
    
    public static IBluetoothSocketManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.IBluetoothSocketManager");
      return (iInterface != null && iInterface instanceof IBluetoothSocketManager) ? (IBluetoothSocketManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IBluetoothSocketManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? null : "requestMaximumTxDataLength") : "createSocketChannel") : "connectSocket";
    }
    
    public static boolean setDefaultImpl(IBluetoothSocketManager param1IBluetoothSocketManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IBluetoothSocketManager != null) {
          Proxy.sDefaultImpl = param1IBluetoothSocketManager;
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
      ParcelUuid parcelUuid;
      BluetoothDevice bluetoothDevice;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 1598968902)
              return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
            param1Parcel2.writeString("android.bluetooth.IBluetoothSocketManager");
            return true;
          } 
          param1Parcel1.enforceInterface("android.bluetooth.IBluetoothSocketManager");
          if (param1Parcel1.readInt() != 0) {
            BluetoothDevice bluetoothDevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
          } else {
            param1Parcel1 = null;
          } 
          requestMaximumTxDataLength((BluetoothDevice)param1Parcel1);
          param1Parcel2.writeNoException();
          return true;
        } 
        param1Parcel1.enforceInterface("android.bluetooth.IBluetoothSocketManager");
        param1Int1 = param1Parcel1.readInt();
        parcelUuid = (ParcelUuid)param1Parcel1.readString();
        if (param1Parcel1.readInt() != 0) {
          bluetoothDevice = (BluetoothDevice)ParcelUuid.CREATOR.createFromParcel(param1Parcel1);
        } else {
          bluetoothDevice = null;
        } 
        parcelFileDescriptor = createSocketChannel(param1Int1, (String)parcelUuid, (ParcelUuid)bluetoothDevice, param1Parcel1.readInt(), param1Parcel1.readInt());
        param1Parcel2.writeNoException();
        if (parcelFileDescriptor != null) {
          param1Parcel2.writeInt(1);
          parcelFileDescriptor.writeToParcel(param1Parcel2, 1);
        } else {
          param1Parcel2.writeInt(0);
        } 
        return true;
      } 
      parcelFileDescriptor.enforceInterface("android.bluetooth.IBluetoothSocketManager");
      if (parcelFileDescriptor.readInt() != 0) {
        bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
      } else {
        bluetoothDevice = null;
      } 
      param1Int1 = parcelFileDescriptor.readInt();
      if (parcelFileDescriptor.readInt() != 0) {
        parcelUuid = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel((Parcel)parcelFileDescriptor);
      } else {
        parcelUuid = null;
      } 
      ParcelFileDescriptor parcelFileDescriptor = connectSocket(bluetoothDevice, param1Int1, parcelUuid, parcelFileDescriptor.readInt(), parcelFileDescriptor.readInt());
      param1Parcel2.writeNoException();
      if (parcelFileDescriptor != null) {
        param1Parcel2.writeInt(1);
        parcelFileDescriptor.writeToParcel(param1Parcel2, 1);
      } else {
        param1Parcel2.writeInt(0);
      } 
      return true;
    }
    
    private static class Proxy implements IBluetoothSocketManager {
      public static IBluetoothSocketManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public ParcelFileDescriptor connectSocket(BluetoothDevice param2BluetoothDevice, int param2Int1, ParcelUuid param2ParcelUuid, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int1);
          if (param2ParcelUuid != null) {
            parcel1.writeInt(1);
            param2ParcelUuid.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null)
            return IBluetoothSocketManager.Stub.getDefaultImpl().connectSocket(param2BluetoothDevice, param2Int1, param2ParcelUuid, param2Int2, param2Int3); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
          } else {
            param2BluetoothDevice = null;
          } 
          return (ParcelFileDescriptor)param2BluetoothDevice;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ParcelFileDescriptor createSocketChannel(int param2Int1, String param2String, ParcelUuid param2ParcelUuid, int param2Int2, int param2Int3) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
          parcel1.writeInt(param2Int1);
          parcel1.writeString(param2String);
          if (param2ParcelUuid != null) {
            parcel1.writeInt(1);
            param2ParcelUuid.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeInt(param2Int2);
          parcel1.writeInt(param2Int3);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null)
            return IBluetoothSocketManager.Stub.getDefaultImpl().createSocketChannel(param2Int1, param2String, param2ParcelUuid, param2Int2, param2Int3); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (ParcelFileDescriptor)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.IBluetoothSocketManager";
      }
      
      public void requestMaximumTxDataLength(BluetoothDevice param2BluetoothDevice) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
          if (param2BluetoothDevice != null) {
            parcel1.writeInt(1);
            param2BluetoothDevice.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null) {
            IBluetoothSocketManager.Stub.getDefaultImpl().requestMaximumTxDataLength(param2BluetoothDevice);
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
  
  private static class Proxy implements IBluetoothSocketManager {
    public static IBluetoothSocketManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public ParcelFileDescriptor connectSocket(BluetoothDevice param1BluetoothDevice, int param1Int1, ParcelUuid param1ParcelUuid, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int1);
        if (param1ParcelUuid != null) {
          parcel1.writeInt(1);
          param1ParcelUuid.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null)
          return IBluetoothSocketManager.Stub.getDefaultImpl().connectSocket(param1BluetoothDevice, param1Int1, param1ParcelUuid, param1Int2, param1Int3); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param1BluetoothDevice = null;
        } 
        return (ParcelFileDescriptor)param1BluetoothDevice;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor createSocketChannel(int param1Int1, String param1String, ParcelUuid param1ParcelUuid, int param1Int2, int param1Int3) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
        parcel1.writeInt(param1Int1);
        parcel1.writeString(param1String);
        if (param1ParcelUuid != null) {
          parcel1.writeInt(1);
          param1ParcelUuid.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeInt(param1Int2);
        parcel1.writeInt(param1Int3);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null)
          return IBluetoothSocketManager.Stub.getDefaultImpl().createSocketChannel(param1Int1, param1String, param1ParcelUuid, param1Int2, param1Int3); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (ParcelFileDescriptor)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.IBluetoothSocketManager";
    }
    
    public void requestMaximumTxDataLength(BluetoothDevice param1BluetoothDevice) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.bluetooth.IBluetoothSocketManager");
        if (param1BluetoothDevice != null) {
          parcel1.writeInt(1);
          param1BluetoothDevice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothSocketManager.Stub.getDefaultImpl() != null) {
          IBluetoothSocketManager.Stub.getDefaultImpl().requestMaximumTxDataLength(param1BluetoothDevice);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothSocketManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */