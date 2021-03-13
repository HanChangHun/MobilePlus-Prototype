package android.debug;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

public abstract class Stub extends Binder implements IAdbManager {
  private static final String DESCRIPTOR = "android.debug.IAdbManager";
  
  static final int TRANSACTION_allowDebugging = 1;
  
  static final int TRANSACTION_allowWirelessDebugging = 4;
  
  static final int TRANSACTION_clearDebuggingKeys = 3;
  
  static final int TRANSACTION_denyDebugging = 2;
  
  static final int TRANSACTION_denyWirelessDebugging = 5;
  
  static final int TRANSACTION_disablePairing = 11;
  
  static final int TRANSACTION_enablePairingByPairingCode = 8;
  
  static final int TRANSACTION_enablePairingByQrCode = 9;
  
  static final int TRANSACTION_getAdbWirelessPort = 10;
  
  static final int TRANSACTION_getPairedDevices = 6;
  
  static final int TRANSACTION_isAdbWifiQrSupported = 13;
  
  static final int TRANSACTION_isAdbWifiSupported = 12;
  
  static final int TRANSACTION_unpairDevice = 7;
  
  public Stub() {
    attachInterface(this, "android.debug.IAdbManager");
  }
  
  public static IAdbManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.debug.IAdbManager");
    return (iInterface != null && iInterface instanceof IAdbManager) ? (IAdbManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAdbManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 13:
        return "isAdbWifiQrSupported";
      case 12:
        return "isAdbWifiSupported";
      case 11:
        return "disablePairing";
      case 10:
        return "getAdbWirelessPort";
      case 9:
        return "enablePairingByQrCode";
      case 8:
        return "enablePairingByPairingCode";
      case 7:
        return "unpairDevice";
      case 6:
        return "getPairedDevices";
      case 5:
        return "denyWirelessDebugging";
      case 4:
        return "allowWirelessDebugging";
      case 3:
        return "clearDebuggingKeys";
      case 2:
        return "denyDebugging";
      case 1:
        break;
    } 
    return "allowDebugging";
  }
  
  public static boolean setDefaultImpl(IAdbManager paramIAdbManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAdbManager != null) {
        Proxy.sDefaultImpl = paramIAdbManager;
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
      boolean bool;
      int i;
      Map map;
      boolean bool1 = false;
      boolean bool2 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 13:
          paramParcel1.enforceInterface("android.debug.IAdbManager");
          bool = isAdbWifiQrSupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 12:
          paramParcel1.enforceInterface("android.debug.IAdbManager");
          bool = isAdbWifiSupported();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(bool);
          return true;
        case 11:
          paramParcel1.enforceInterface("android.debug.IAdbManager");
          disablePairing();
          paramParcel2.writeNoException();
          return true;
        case 10:
          paramParcel1.enforceInterface("android.debug.IAdbManager");
          i = getAdbWirelessPort();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i);
          return true;
        case 9:
          paramParcel1.enforceInterface("android.debug.IAdbManager");
          enablePairingByQrCode(paramParcel1.readString(), paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 8:
          paramParcel1.enforceInterface("android.debug.IAdbManager");
          enablePairingByPairingCode();
          paramParcel2.writeNoException();
          return true;
        case 7:
          paramParcel1.enforceInterface("android.debug.IAdbManager");
          unpairDevice(paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        case 6:
          paramParcel1.enforceInterface("android.debug.IAdbManager");
          map = getPairedDevices();
          paramParcel2.writeNoException();
          paramParcel2.writeMap(map);
          return true;
        case 5:
          map.enforceInterface("android.debug.IAdbManager");
          denyWirelessDebugging();
          paramParcel2.writeNoException();
          return true;
        case 4:
          map.enforceInterface("android.debug.IAdbManager");
          if (map.readInt() != 0)
            bool2 = true; 
          allowWirelessDebugging(bool2, map.readString());
          paramParcel2.writeNoException();
          return true;
        case 3:
          map.enforceInterface("android.debug.IAdbManager");
          clearDebuggingKeys();
          paramParcel2.writeNoException();
          return true;
        case 2:
          map.enforceInterface("android.debug.IAdbManager");
          denyDebugging();
          paramParcel2.writeNoException();
          return true;
        case 1:
          break;
      } 
      map.enforceInterface("android.debug.IAdbManager");
      bool2 = bool1;
      if (map.readInt() != 0)
        bool2 = true; 
      allowDebugging(bool2, map.readString());
      paramParcel2.writeNoException();
      return true;
    } 
    paramParcel2.writeString("android.debug.IAdbManager");
    return true;
  }
  
  private static class Proxy implements IAdbManager {
    public static IAdbManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public void allowDebugging(boolean param2Boolean, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
          IAdbManager.Stub.getDefaultImpl().allowDebugging(param2Boolean, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void allowWirelessDebugging(boolean param2Boolean, String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
          IAdbManager.Stub.getDefaultImpl().allowWirelessDebugging(param2Boolean, param2String);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void clearDebuggingKeys() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
          IAdbManager.Stub.getDefaultImpl().clearDebuggingKeys();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void denyDebugging() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
          IAdbManager.Stub.getDefaultImpl().denyDebugging();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void denyWirelessDebugging() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
          IAdbManager.Stub.getDefaultImpl().denyWirelessDebugging();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void disablePairing() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
          IAdbManager.Stub.getDefaultImpl().disablePairing();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enablePairingByPairingCode() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
          IAdbManager.Stub.getDefaultImpl().enablePairingByPairingCode();
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void enablePairingByQrCode(String param2String1, String param2String2) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        parcel1.writeString(param2String1);
        parcel1.writeString(param2String2);
        if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
          IAdbManager.Stub.getDefaultImpl().enablePairingByQrCode(param2String1, param2String2);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int getAdbWirelessPort() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null)
          return IAdbManager.Stub.getDefaultImpl().getAdbWirelessPort(); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.debug.IAdbManager";
    }
    
    public Map getPairedDevices() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null)
          return IAdbManager.Stub.getDefaultImpl().getPairedDevices(); 
        parcel2.readException();
        return parcel2.readHashMap(getClass().getClassLoader());
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAdbWifiQrSupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(13, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
          bool = IAdbManager.Stub.getDefaultImpl().isAdbWifiQrSupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public boolean isAdbWifiSupported() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(12, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
          bool = IAdbManager.Stub.getDefaultImpl().isAdbWifiSupported();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void unpairDevice(String param2String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.debug.IAdbManager");
        parcel1.writeString(param2String);
        if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
          IAdbManager.Stub.getDefaultImpl().unpairDevice(param2String);
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


/* Location:              /home/chun/Desktop/temp/!/android/debug/IAdbManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */