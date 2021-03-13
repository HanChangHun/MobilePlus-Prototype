package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IBluetoothManager {
  public static IBluetoothManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public boolean bindBluetoothProfileService(int paramInt, IBluetoothProfileServiceConnection paramIBluetoothProfileServiceConnection) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      parcel1.writeInt(paramInt);
      if (paramIBluetoothProfileServiceConnection != null) {
        iBinder = paramIBluetoothProfileServiceConnection.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(11, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        bool = IBluetoothManager.Stub.getDefaultImpl().bindBluetoothProfileService(paramInt, paramIBluetoothProfileServiceConnection);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean disable(String paramString, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      parcel1.writeString(paramString);
      boolean bool = true;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        paramBoolean = IBluetoothManager.Stub.getDefaultImpl().disable(paramString, paramBoolean);
        return paramBoolean;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0) {
        paramBoolean = bool;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean disableBle(String paramString, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      parcel1.writeString(paramString);
      parcel1.writeStrongBinder(paramIBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(18, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        bool = IBluetoothManager.Stub.getDefaultImpl().disableBle(paramString, paramIBinder);
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
  
  public boolean enable(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(6, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        bool = IBluetoothManager.Stub.getDefaultImpl().enable(paramString);
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
  
  public boolean enableBle(String paramString, IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      parcel1.writeString(paramString);
      parcel1.writeStrongBinder(paramIBinder);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(17, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        bool = IBluetoothManager.Stub.getDefaultImpl().enableBle(paramString, paramIBinder);
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
  
  public boolean enableNoAutoConnect(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(7, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        bool = IBluetoothManager.Stub.getDefaultImpl().enableNoAutoConnect(paramString);
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
  
  public String getAddress() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
        return IBluetoothManager.Stub.getDefaultImpl().getAddress(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBluetoothGatt getBluetoothGatt() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
        return IBluetoothManager.Stub.getDefaultImpl().getBluetoothGatt(); 
      parcel2.readException();
      return IBluetoothGatt.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetoothManager";
  }
  
  public String getName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
        return IBluetoothManager.Stub.getDefaultImpl().getName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getState() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
        return IBluetoothManager.Stub.getDefaultImpl().getState(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<String> getSystemConfigEnabledProfilesForPackage(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
        return IBluetoothManager.Stub.getDefaultImpl().getSystemConfigEnabledProfilesForPackage(paramString); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isBleAppPresent() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(19, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        bool = IBluetoothManager.Stub.getDefaultImpl().isBleAppPresent();
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
  
  public boolean isBleScanAlwaysAvailable() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(16, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        bool = IBluetoothManager.Stub.getDefaultImpl().isBleScanAlwaysAvailable();
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
  
  public boolean isEnabled() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(5, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        bool = IBluetoothManager.Stub.getDefaultImpl().isEnabled();
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
  
  public boolean isHearingAidProfileSupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(20, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        bool = IBluetoothManager.Stub.getDefaultImpl().isHearingAidProfileSupported();
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
  
  public boolean onFactoryReset() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(15, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        bool = IBluetoothManager.Stub.getDefaultImpl().onFactoryReset();
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
  
  public IBluetooth registerAdapter(IBluetoothManagerCallback paramIBluetoothManagerCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      if (paramIBluetoothManagerCallback != null) {
        iBinder = paramIBluetoothManagerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null)
        return IBluetoothManager.Stub.getDefaultImpl().registerAdapter(paramIBluetoothManagerCallback); 
      parcel2.readException();
      return IBluetooth.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerStateChangeCallback(IBluetoothStateChangeCallback paramIBluetoothStateChangeCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      if (paramIBluetoothStateChangeCallback != null) {
        iBinder = paramIBluetoothStateChangeCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        IBluetoothManager.Stub.getDefaultImpl().registerStateChangeCallback(paramIBluetoothStateChangeCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unbindBluetoothProfileService(int paramInt, IBluetoothProfileServiceConnection paramIBluetoothProfileServiceConnection) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      parcel1.writeInt(paramInt);
      if (paramIBluetoothProfileServiceConnection != null) {
        iBinder = paramIBluetoothProfileServiceConnection.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        IBluetoothManager.Stub.getDefaultImpl().unbindBluetoothProfileService(paramInt, paramIBluetoothProfileServiceConnection);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterAdapter(IBluetoothManagerCallback paramIBluetoothManagerCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      if (paramIBluetoothManagerCallback != null) {
        iBinder = paramIBluetoothManagerCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        IBluetoothManager.Stub.getDefaultImpl().unregisterAdapter(paramIBluetoothManagerCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void unregisterStateChangeCallback(IBluetoothStateChangeCallback paramIBluetoothStateChangeCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetoothManager");
      if (paramIBluetoothStateChangeCallback != null) {
        iBinder = paramIBluetoothStateChangeCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetoothManager.Stub.getDefaultImpl() != null) {
        IBluetoothManager.Stub.getDefaultImpl().unregisterStateChangeCallback(paramIBluetoothStateChangeCallback);
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */