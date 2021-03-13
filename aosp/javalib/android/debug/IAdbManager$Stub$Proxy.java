package android.debug;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

class Proxy implements IAdbManager {
  public static IAdbManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void allowDebugging(boolean paramBoolean, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.debug.IAdbManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
        IAdbManager.Stub.getDefaultImpl().allowDebugging(paramBoolean, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void allowWirelessDebugging(boolean paramBoolean, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.debug.IAdbManager");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
        IAdbManager.Stub.getDefaultImpl().allowWirelessDebugging(paramBoolean, paramString);
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
  
  public void enablePairingByQrCode(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.debug.IAdbManager");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
        IAdbManager.Stub.getDefaultImpl().enablePairingByQrCode(paramString1, paramString2);
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
  
  public void unpairDevice(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.debug.IAdbManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IAdbManager.Stub.getDefaultImpl() != null) {
        IAdbManager.Stub.getDefaultImpl().unpairDevice(paramString);
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


/* Location:              /home/chun/Desktop/temp/!/android/debug/IAdbManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */