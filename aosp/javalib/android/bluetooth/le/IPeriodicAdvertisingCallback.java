package android.bluetooth.le;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPeriodicAdvertisingCallback extends IInterface {
  void onPeriodicAdvertisingReport(PeriodicAdvertisingReport paramPeriodicAdvertisingReport) throws RemoteException;
  
  void onSyncEstablished(int paramInt1, BluetoothDevice paramBluetoothDevice, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws RemoteException;
  
  void onSyncLost(int paramInt) throws RemoteException;
  
  public static class Default implements IPeriodicAdvertisingCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onPeriodicAdvertisingReport(PeriodicAdvertisingReport param1PeriodicAdvertisingReport) throws RemoteException {}
    
    public void onSyncEstablished(int param1Int1, BluetoothDevice param1BluetoothDevice, int param1Int2, int param1Int3, int param1Int4, int param1Int5) throws RemoteException {}
    
    public void onSyncLost(int param1Int) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IPeriodicAdvertisingCallback {
    private static final String DESCRIPTOR = "android.bluetooth.le.IPeriodicAdvertisingCallback";
    
    static final int TRANSACTION_onPeriodicAdvertisingReport = 2;
    
    static final int TRANSACTION_onSyncEstablished = 1;
    
    static final int TRANSACTION_onSyncLost = 3;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.le.IPeriodicAdvertisingCallback");
    }
    
    public static IPeriodicAdvertisingCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
      return (iInterface != null && iInterface instanceof IPeriodicAdvertisingCallback) ? (IPeriodicAdvertisingCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IPeriodicAdvertisingCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? null : "onSyncLost") : "onPeriodicAdvertisingReport") : "onSyncEstablished";
    }
    
    public static boolean setDefaultImpl(IPeriodicAdvertisingCallback param1IPeriodicAdvertisingCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IPeriodicAdvertisingCallback != null) {
          Proxy.sDefaultImpl = param1IPeriodicAdvertisingCallback;
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
            param1Parcel2.writeString("android.bluetooth.le.IPeriodicAdvertisingCallback");
            return true;
          } 
          param1Parcel1.enforceInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
          onSyncLost(param1Parcel1.readInt());
          return true;
        } 
        param1Parcel1.enforceInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
        if (param1Parcel1.readInt() != 0) {
          PeriodicAdvertisingReport periodicAdvertisingReport = (PeriodicAdvertisingReport)PeriodicAdvertisingReport.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        onPeriodicAdvertisingReport((PeriodicAdvertisingReport)param1Parcel1);
        return true;
      } 
      param1Parcel1.enforceInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
      param1Int1 = param1Parcel1.readInt();
      if (param1Parcel1.readInt() != 0) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      onSyncEstablished(param1Int1, (BluetoothDevice)param1Parcel2, param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt(), param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements IPeriodicAdvertisingCallback {
      public static IPeriodicAdvertisingCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.le.IPeriodicAdvertisingCallback";
      }
      
      public void onPeriodicAdvertisingReport(PeriodicAdvertisingReport param2PeriodicAdvertisingReport) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
          if (param2PeriodicAdvertisingReport != null) {
            parcel.writeInt(1);
            param2PeriodicAdvertisingReport.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel, null, 1) && IPeriodicAdvertisingCallback.Stub.getDefaultImpl() != null) {
            IPeriodicAdvertisingCallback.Stub.getDefaultImpl().onPeriodicAdvertisingReport(param2PeriodicAdvertisingReport);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onSyncEstablished(int param2Int1, BluetoothDevice param2BluetoothDevice, int param2Int2, int param2Int3, int param2Int4, int param2Int5) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
          try {
            parcel.writeInt(param2Int1);
            if (param2BluetoothDevice != null) {
              parcel.writeInt(1);
              param2BluetoothDevice.writeToParcel(parcel, 0);
            } else {
              parcel.writeInt(0);
            } 
            try {
              parcel.writeInt(param2Int2);
              try {
                parcel.writeInt(param2Int3);
                try {
                  parcel.writeInt(param2Int4);
                  try {
                    parcel.writeInt(param2Int5);
                    try {
                      if (!this.mRemote.transact(1, parcel, null, 1) && IPeriodicAdvertisingCallback.Stub.getDefaultImpl() != null) {
                        IPeriodicAdvertisingCallback.Stub.getDefaultImpl().onSyncEstablished(param2Int1, param2BluetoothDevice, param2Int2, param2Int3, param2Int4, param2Int5);
                        parcel.recycle();
                        return;
                      } 
                      parcel.recycle();
                      return;
                    } finally {}
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
        parcel.recycle();
        throw param2BluetoothDevice;
      }
      
      public void onSyncLost(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(3, parcel, null, 1) && IPeriodicAdvertisingCallback.Stub.getDefaultImpl() != null) {
            IPeriodicAdvertisingCallback.Stub.getDefaultImpl().onSyncLost(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IPeriodicAdvertisingCallback {
    public static IPeriodicAdvertisingCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.le.IPeriodicAdvertisingCallback";
    }
    
    public void onPeriodicAdvertisingReport(PeriodicAdvertisingReport param1PeriodicAdvertisingReport) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
        if (param1PeriodicAdvertisingReport != null) {
          parcel.writeInt(1);
          param1PeriodicAdvertisingReport.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IPeriodicAdvertisingCallback.Stub.getDefaultImpl() != null) {
          IPeriodicAdvertisingCallback.Stub.getDefaultImpl().onPeriodicAdvertisingReport(param1PeriodicAdvertisingReport);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSyncEstablished(int param1Int1, BluetoothDevice param1BluetoothDevice, int param1Int2, int param1Int3, int param1Int4, int param1Int5) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
        try {
          parcel.writeInt(param1Int1);
          if (param1BluetoothDevice != null) {
            parcel.writeInt(1);
            param1BluetoothDevice.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          try {
            parcel.writeInt(param1Int2);
            try {
              parcel.writeInt(param1Int3);
              try {
                parcel.writeInt(param1Int4);
                try {
                  parcel.writeInt(param1Int5);
                  try {
                    if (!this.mRemote.transact(1, parcel, null, 1) && IPeriodicAdvertisingCallback.Stub.getDefaultImpl() != null) {
                      IPeriodicAdvertisingCallback.Stub.getDefaultImpl().onSyncEstablished(param1Int1, param1BluetoothDevice, param1Int2, param1Int3, param1Int4, param1Int5);
                      parcel.recycle();
                      return;
                    } 
                    parcel.recycle();
                    return;
                  } finally {}
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param1BluetoothDevice;
    }
    
    public void onSyncLost(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IPeriodicAdvertisingCallback.Stub.getDefaultImpl() != null) {
          IPeriodicAdvertisingCallback.Stub.getDefaultImpl().onSyncLost(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IPeriodicAdvertisingCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */