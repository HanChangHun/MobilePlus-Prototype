package android.bluetooth.le;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IPeriodicAdvertisingCallback {
  private static final String DESCRIPTOR = "android.bluetooth.le.IPeriodicAdvertisingCallback";
  
  static final int TRANSACTION_onPeriodicAdvertisingReport = 2;
  
  static final int TRANSACTION_onSyncEstablished = 1;
  
  static final int TRANSACTION_onSyncLost = 3;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.le.IPeriodicAdvertisingCallback");
  }
  
  public static IPeriodicAdvertisingCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
    return (iInterface != null && iInterface instanceof IPeriodicAdvertisingCallback) ? (IPeriodicAdvertisingCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IPeriodicAdvertisingCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? null : "onSyncLost") : "onPeriodicAdvertisingReport") : "onSyncEstablished";
  }
  
  public static boolean setDefaultImpl(IPeriodicAdvertisingCallback paramIPeriodicAdvertisingCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIPeriodicAdvertisingCallback != null) {
        Proxy.sDefaultImpl = paramIPeriodicAdvertisingCallback;
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
          paramParcel2.writeString("android.bluetooth.le.IPeriodicAdvertisingCallback");
          return true;
        } 
        paramParcel1.enforceInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
        onSyncLost(paramParcel1.readInt());
        return true;
      } 
      paramParcel1.enforceInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
      if (paramParcel1.readInt() != 0) {
        PeriodicAdvertisingReport periodicAdvertisingReport = (PeriodicAdvertisingReport)PeriodicAdvertisingReport.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      onPeriodicAdvertisingReport((PeriodicAdvertisingReport)paramParcel1);
      return true;
    } 
    paramParcel1.enforceInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
    paramInt1 = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      BluetoothDevice bluetoothDevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    onSyncEstablished(paramInt1, (BluetoothDevice)paramParcel2, paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IPeriodicAdvertisingCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */