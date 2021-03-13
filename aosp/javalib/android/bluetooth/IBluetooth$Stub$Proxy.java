package android.bluetooth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.ResultReceiver;
import java.util.List;

class Proxy implements IBluetooth {
  public static IBluetooth sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public boolean cancelBondProcess(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(26, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().cancelBondProcess(paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean cancelDiscovery() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(19, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().cancelDiscovery();
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
  
  public boolean connectAllEnabledProfiles(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(74, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().connectAllEnabledProfiles(paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean createBond(BluetoothDevice paramBluetoothDevice, int paramInt, OobData paramOobData) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (paramOobData != null) {
        parcel1.writeInt(1);
        paramOobData.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().createBond(paramBluetoothDevice, paramInt, paramOobData);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean disable() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(3, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().disable();
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
  
  public boolean disconnectAllEnabledProfiles(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(75, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().disconnectAllEnabledProfiles(paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean enable(boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        paramBoolean = IBluetooth.Stub.getDefaultImpl().enable(paramBoolean);
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
  
  public boolean factoryReset() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(56, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().factoryReset();
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
  
  public boolean fetchRemoteUuids(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(38, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().fetchRemoteUuids(paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getAdapterConnectionState() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getAdapterConnectionState(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getAddress() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getAddress(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getBatteryLevel(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(40, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getBatteryLevel(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public BluetoothClass getBluetoothClass() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      BluetoothClass bluetoothClass;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bluetoothClass = IBluetooth.Stub.getDefaultImpl().getBluetoothClass();
        return bluetoothClass;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        bluetoothClass = (BluetoothClass)BluetoothClass.CREATOR.createFromParcel(parcel2);
      } else {
        bluetoothClass = null;
      } 
      return bluetoothClass;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getBondState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(28, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getBondState(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public BluetoothDevice[] getBondedDevices() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getBondedDevices(); 
      parcel2.readException();
      return (BluetoothDevice[])parcel2.createTypedArray(BluetoothDevice.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(31, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getConnectionState(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getDiscoverableTimeout() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getDiscoverableTimeout(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getDiscoveryEndMillis() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getDiscoveryEndMillis(); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.bluetooth.IBluetooth";
  }
  
  public int getIoCapability() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getIoCapability(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getLeIoCapability() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getLeIoCapability(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getLeMaximumAdvertisingDataLength() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(65, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getLeMaximumAdvertisingDataLength(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getMaxConnectedAudioDevices() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(41, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getMaxConnectedAudioDevices(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getMessageAccessPermission(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(49, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getMessageAccessPermission(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public byte[] getMetadata(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(70, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getMetadata(paramBluetoothDevice, paramInt); 
      parcel2.readException();
      return parcel2.createByteArray();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<BluetoothDevice> getMostRecentlyConnectedDevices() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(77, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getMostRecentlyConnectedDevices(); 
      parcel2.readException();
      return parcel2.createTypedArrayList(BluetoothDevice.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getName() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getName(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getPhonebookAccessPermission(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(45, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getPhonebookAccessPermission(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getProfileConnectionState(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        paramInt = IBluetooth.Stub.getDefaultImpl().getProfileConnectionState(paramInt);
        return paramInt;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      return paramInt;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getRemoteAlias(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(34, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getRemoteAlias(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getRemoteClass(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(36, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getRemoteClass(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getRemoteName(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(32, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getRemoteName(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getRemoteType(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(33, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getRemoteType(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParcelUuid[] getRemoteUuids(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(37, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getRemoteUuids(paramBluetoothDevice); 
      parcel2.readException();
      return (ParcelUuid[])parcel2.createTypedArray(ParcelUuid.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getScanMode() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getScanMode(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean getSilenceMode(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(47, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().getSilenceMode(paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getSimAccessPermission(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(51, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getSimAccessPermission(paramBluetoothDevice); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBluetoothSocketManager getSocketManager() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(55, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getSocketManager(); 
      parcel2.readException();
      return IBluetoothSocketManager.Stub.asInterface(parcel2.readStrongBinder());
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int getState() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getState(); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getSupportedProfiles() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(30, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getSupportedProfiles(); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParcelUuid[] getUuids() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null)
        return IBluetooth.Stub.getDefaultImpl().getUuids(); 
      parcel2.readException();
      return (ParcelUuid[])parcel2.createTypedArray(ParcelUuid.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isActivityAndEnergyReportingSupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(60, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().isActivityAndEnergyReportingSupported();
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
  
  public boolean isBondingInitiatedLocally(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(29, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().isBondingInitiatedLocally(paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isDiscovering() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(20, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().isDiscovering();
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
  
  public boolean isLe2MPhySupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(61, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().isLe2MPhySupported();
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
  
  public boolean isLeCodedPhySupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(62, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().isLeCodedPhySupported();
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
  
  public boolean isLeExtendedAdvertisingSupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(63, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().isLeExtendedAdvertisingSupported();
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
  
  public boolean isLePeriodicAdvertisingSupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(64, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().isLePeriodicAdvertisingSupported();
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
  
  public boolean isMultiAdvertisementSupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(57, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().isMultiAdvertisementSupported();
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
  
  public boolean isOffloadedFilteringSupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(58, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().isOffloadedFilteringSupported();
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
  
  public boolean isOffloadedScanBatchingSupported() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(59, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().isOffloadedScanBatchingSupported();
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
  
  public void onBrEdrDown() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(73, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        IBluetooth.Stub.getDefaultImpl().onBrEdrDown();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void onLeServiceUp() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(72, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        IBluetooth.Stub.getDefaultImpl().onLeServiceUp();
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void registerCallback(IBluetoothCallback paramIBluetoothCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramIBluetoothCallback != null) {
        iBinder = paramIBluetoothCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(53, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        IBluetooth.Stub.getDefaultImpl().registerCallback(paramIBluetoothCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean registerMetadataListener(IBluetoothMetadataListener paramIBluetoothMetadataListener, BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramIBluetoothMetadataListener != null) {
        iBinder = paramIBluetoothMetadataListener.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(67, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().registerMetadataListener(paramIBluetoothMetadataListener, paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean removeActiveDevice(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(78, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().removeActiveDevice(paramInt);
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
  
  public boolean removeBond(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(27, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().removeBond(paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public BluetoothActivityEnergyInfo reportActivityInfo() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      BluetoothActivityEnergyInfo bluetoothActivityEnergyInfo;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (!this.mRemote.transact(66, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bluetoothActivityEnergyInfo = IBluetooth.Stub.getDefaultImpl().reportActivityInfo();
        return bluetoothActivityEnergyInfo;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        bluetoothActivityEnergyInfo = (BluetoothActivityEnergyInfo)BluetoothActivityEnergyInfo.CREATOR.createFromParcel(parcel2);
      } else {
        bluetoothActivityEnergyInfo = null;
      } 
      return bluetoothActivityEnergyInfo;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void requestActivityInfo(ResultReceiver paramResultReceiver) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramResultReceiver != null) {
        parcel.writeInt(1);
        paramResultReceiver.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(71, parcel, null, 1) && IBluetooth.Stub.getDefaultImpl() != null) {
        IBluetooth.Stub.getDefaultImpl().requestActivityInfo(paramResultReceiver);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public boolean sdpSearch(BluetoothDevice paramBluetoothDevice, ParcelUuid paramParcelUuid) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramParcelUuid != null) {
        parcel1.writeInt(1);
        paramParcelUuid.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(39, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().sdpSearch(paramBluetoothDevice, paramParcelUuid);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setActiveDevice(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(76, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setActiveDevice(paramBluetoothDevice, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setBluetoothClass(BluetoothClass paramBluetoothClass) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothClass != null) {
        parcel1.writeInt(1);
        paramBluetoothClass.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setBluetoothClass(paramBluetoothClass);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setDiscoverableTimeout(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(17, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setDiscoverableTimeout(paramInt);
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
  
  public boolean setIoCapability(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(11, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setIoCapability(paramInt);
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
  
  public boolean setLeIoCapability(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      parcel1.writeInt(paramInt);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(13, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setLeIoCapability(paramInt);
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
  
  public boolean setMessageAccessPermission(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(50, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setMessageAccessPermission(paramBluetoothDevice, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setMetadata(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(69, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setMetadata(paramBluetoothDevice, paramInt, paramArrayOfbyte);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setName(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(6, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setName(paramString);
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
  
  public boolean setPairingConfirmation(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(44, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        paramBoolean = IBluetooth.Stub.getDefaultImpl().setPairingConfirmation(paramBluetoothDevice, paramBoolean);
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
  
  public boolean setPasskey(BluetoothDevice paramBluetoothDevice, boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool1 = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      parcel1.writeInt(paramInt);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(43, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        paramBoolean = IBluetooth.Stub.getDefaultImpl().setPasskey(paramBluetoothDevice, paramBoolean, paramInt, paramArrayOfbyte);
        return paramBoolean;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setPhonebookAccessPermission(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(48, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setPhonebookAccessPermission(paramBluetoothDevice, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setPin(BluetoothDevice paramBluetoothDevice, boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool2;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool1 = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      parcel1.writeInt(bool2);
      parcel1.writeInt(paramInt);
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(42, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        paramBoolean = IBluetooth.Stub.getDefaultImpl().setPin(paramBluetoothDevice, paramBoolean, paramInt, paramArrayOfbyte);
        return paramBoolean;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      } 
      return paramBoolean;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setRemoteAlias(BluetoothDevice paramBluetoothDevice, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(35, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setRemoteAlias(paramBluetoothDevice, paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setScanMode(int paramInt1, int paramInt2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(15, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setScanMode(paramInt1, paramInt2);
        return bool;
      } 
      parcel2.readException();
      paramInt1 = parcel2.readInt();
      if (paramInt1 != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean setSilenceMode(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramBoolean) {
        i = 1;
      } else {
        i = 0;
      } 
      parcel1.writeInt(i);
      if (!this.mRemote.transact(46, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        paramBoolean = IBluetooth.Stub.getDefaultImpl().setSilenceMode(paramBluetoothDevice, paramBoolean);
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
  
  public boolean setSimAccessPermission(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(52, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().setSimAccessPermission(paramBluetoothDevice, paramInt);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean startDiscovery(String paramString1, String paramString2) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      parcel1.writeString(paramString1);
      parcel1.writeString(paramString2);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(18, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().startDiscovery(paramString1, paramString2);
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
  
  public void unregisterCallback(IBluetoothCallback paramIBluetoothCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      if (paramIBluetoothCallback != null) {
        iBinder = paramIBluetoothCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(54, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        IBluetooth.Stub.getDefaultImpl().unregisterCallback(paramIBluetoothCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean unregisterMetadataListener(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.bluetooth.IBluetooth");
      boolean bool = true;
      if (paramBluetoothDevice != null) {
        parcel1.writeInt(1);
        paramBluetoothDevice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(68, parcel1, parcel2, 0) && IBluetooth.Stub.getDefaultImpl() != null) {
        bool = IBluetooth.Stub.getDefaultImpl().unregisterMetadataListener(paramBluetoothDevice);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i == 0)
        bool = false; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetooth$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */