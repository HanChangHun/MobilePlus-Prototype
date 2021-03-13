package android.bluetooth.le;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IAdvertisingSetCallback {
  private static final String DESCRIPTOR = "android.bluetooth.le.IAdvertisingSetCallback";
  
  static final int TRANSACTION_onAdvertisingDataSet = 5;
  
  static final int TRANSACTION_onAdvertisingEnabled = 4;
  
  static final int TRANSACTION_onAdvertisingParametersUpdated = 7;
  
  static final int TRANSACTION_onAdvertisingSetStarted = 1;
  
  static final int TRANSACTION_onAdvertisingSetStopped = 3;
  
  static final int TRANSACTION_onOwnAddressRead = 2;
  
  static final int TRANSACTION_onPeriodicAdvertisingDataSet = 9;
  
  static final int TRANSACTION_onPeriodicAdvertisingEnabled = 10;
  
  static final int TRANSACTION_onPeriodicAdvertisingParametersUpdated = 8;
  
  static final int TRANSACTION_onScanResponseDataSet = 6;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.le.IAdvertisingSetCallback");
  }
  
  public static IAdvertisingSetCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.le.IAdvertisingSetCallback");
    return (iInterface != null && iInterface instanceof IAdvertisingSetCallback) ? (IAdvertisingSetCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAdvertisingSetCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 10:
        return "onPeriodicAdvertisingEnabled";
      case 9:
        return "onPeriodicAdvertisingDataSet";
      case 8:
        return "onPeriodicAdvertisingParametersUpdated";
      case 7:
        return "onAdvertisingParametersUpdated";
      case 6:
        return "onScanResponseDataSet";
      case 5:
        return "onAdvertisingDataSet";
      case 4:
        return "onAdvertisingEnabled";
      case 3:
        return "onAdvertisingSetStopped";
      case 2:
        return "onOwnAddressRead";
      case 1:
        break;
    } 
    return "onAdvertisingSetStarted";
  }
  
  public static boolean setDefaultImpl(IAdvertisingSetCallback paramIAdvertisingSetCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAdvertisingSetCallback != null) {
        Proxy.sDefaultImpl = paramIAdvertisingSetCallback;
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
    if (paramInt1 != 1598968902) {
      boolean bool1 = false;
      boolean bool2 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 10:
          paramParcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          onPeriodicAdvertisingEnabled(paramInt1, bool2, paramParcel1.readInt());
          return true;
        case 9:
          paramParcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
          onPeriodicAdvertisingDataSet(paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 8:
          paramParcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
          onPeriodicAdvertisingParametersUpdated(paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 7:
          paramParcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
          onAdvertisingParametersUpdated(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 6:
          paramParcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
          onScanResponseDataSet(paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 5:
          paramParcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
          onAdvertisingDataSet(paramParcel1.readInt(), paramParcel1.readInt());
          return true;
        case 4:
          paramParcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
          paramInt1 = paramParcel1.readInt();
          bool2 = bool1;
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          onAdvertisingEnabled(paramInt1, bool2, paramParcel1.readInt());
          return true;
        case 3:
          paramParcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
          onAdvertisingSetStopped(paramParcel1.readInt());
          return true;
        case 2:
          paramParcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
          onOwnAddressRead(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.bluetooth.le.IAdvertisingSetCallback");
      onAdvertisingSetStarted(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt());
      return true;
    } 
    paramParcel2.writeString("android.bluetooth.le.IAdvertisingSetCallback");
    return true;
  }
  
  private static class Proxy implements IAdvertisingSetCallback {
    public static IAdvertisingSetCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.le.IAdvertisingSetCallback";
    }
    
    public void onAdvertisingDataSet(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(5, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingDataSet(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAdvertisingEnabled(int param2Int1, boolean param2Boolean, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param2Int1);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(4, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingEnabled(param2Int1, param2Boolean, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAdvertisingParametersUpdated(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(7, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingParametersUpdated(param2Int1, param2Int2, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAdvertisingSetStarted(int param2Int1, int param2Int2, int param2Int3) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (!this.mRemote.transact(1, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingSetStarted(param2Int1, param2Int2, param2Int3);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onAdvertisingSetStopped(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onAdvertisingSetStopped(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onOwnAddressRead(int param2Int1, int param2Int2, String param2String) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeString(param2String);
        if (!this.mRemote.transact(2, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onOwnAddressRead(param2Int1, param2Int2, param2String);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPeriodicAdvertisingDataSet(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(9, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingDataSet(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPeriodicAdvertisingEnabled(int param2Int1, boolean param2Boolean, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param2Int1);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(10, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingEnabled(param2Int1, param2Boolean, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onPeriodicAdvertisingParametersUpdated(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(8, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onPeriodicAdvertisingParametersUpdated(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onScanResponseDataSet(int param2Int1, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IAdvertisingSetCallback");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        if (!this.mRemote.transact(6, parcel, null, 1) && IAdvertisingSetCallback.Stub.getDefaultImpl() != null) {
          IAdvertisingSetCallback.Stub.getDefaultImpl().onScanResponseDataSet(param2Int1, param2Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IAdvertisingSetCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */