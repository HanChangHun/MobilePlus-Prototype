package android.bluetooth.le;

import android.bluetooth.BluetoothDevice;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IPeriodicAdvertisingCallback {
  public static IPeriodicAdvertisingCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.le.IPeriodicAdvertisingCallback";
  }
  
  public void onPeriodicAdvertisingReport(PeriodicAdvertisingReport paramPeriodicAdvertisingReport) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
      if (paramPeriodicAdvertisingReport != null) {
        parcel.writeInt(1);
        paramPeriodicAdvertisingReport.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(2, parcel, null, 1) && IPeriodicAdvertisingCallback.Stub.getDefaultImpl() != null) {
        IPeriodicAdvertisingCallback.Stub.getDefaultImpl().onPeriodicAdvertisingReport(paramPeriodicAdvertisingReport);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSyncEstablished(int paramInt1, BluetoothDevice paramBluetoothDevice, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
      try {
        parcel.writeInt(paramInt1);
        if (paramBluetoothDevice != null) {
          parcel.writeInt(1);
          paramBluetoothDevice.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          parcel.writeInt(paramInt2);
          try {
            parcel.writeInt(paramInt3);
            try {
              parcel.writeInt(paramInt4);
              try {
                parcel.writeInt(paramInt5);
                try {
                  if (!this.mRemote.transact(1, parcel, null, 1) && IPeriodicAdvertisingCallback.Stub.getDefaultImpl() != null) {
                    IPeriodicAdvertisingCallback.Stub.getDefaultImpl().onSyncEstablished(paramInt1, paramBluetoothDevice, paramInt2, paramInt3, paramInt4, paramInt5);
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
    throw paramBluetoothDevice;
  }
  
  public void onSyncLost(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel, null, 1) && IPeriodicAdvertisingCallback.Stub.getDefaultImpl() != null) {
        IPeriodicAdvertisingCallback.Stub.getDefaultImpl().onSyncLost(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IPeriodicAdvertisingCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */