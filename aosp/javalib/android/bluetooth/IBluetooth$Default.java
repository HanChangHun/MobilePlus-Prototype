package android.bluetooth;

import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.ResultReceiver;
import java.util.List;

public class Default implements IBluetooth {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean cancelBondProcess(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean cancelDiscovery() throws RemoteException {
    return false;
  }
  
  public boolean connectAllEnabledProfiles(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean createBond(BluetoothDevice paramBluetoothDevice, int paramInt, OobData paramOobData) throws RemoteException {
    return false;
  }
  
  public boolean disable() throws RemoteException {
    return false;
  }
  
  public boolean disconnectAllEnabledProfiles(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean enable(boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean factoryReset() throws RemoteException {
    return false;
  }
  
  public boolean fetchRemoteUuids(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public int getAdapterConnectionState() throws RemoteException {
    return 0;
  }
  
  public String getAddress() throws RemoteException {
    return null;
  }
  
  public int getBatteryLevel(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public BluetoothClass getBluetoothClass() throws RemoteException {
    return null;
  }
  
  public int getBondState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public BluetoothDevice[] getBondedDevices() throws RemoteException {
    return null;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public int getDiscoverableTimeout() throws RemoteException {
    return 0;
  }
  
  public long getDiscoveryEndMillis() throws RemoteException {
    return 0L;
  }
  
  public int getIoCapability() throws RemoteException {
    return 0;
  }
  
  public int getLeIoCapability() throws RemoteException {
    return 0;
  }
  
  public int getLeMaximumAdvertisingDataLength() throws RemoteException {
    return 0;
  }
  
  public int getMaxConnectedAudioDevices() throws RemoteException {
    return 0;
  }
  
  public int getMessageAccessPermission(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public byte[] getMetadata(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return null;
  }
  
  public List<BluetoothDevice> getMostRecentlyConnectedDevices() throws RemoteException {
    return null;
  }
  
  public String getName() throws RemoteException {
    return null;
  }
  
  public int getPhonebookAccessPermission(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public int getProfileConnectionState(int paramInt) throws RemoteException {
    return 0;
  }
  
  public String getRemoteAlias(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return null;
  }
  
  public int getRemoteClass(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public String getRemoteName(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return null;
  }
  
  public int getRemoteType(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public ParcelUuid[] getRemoteUuids(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return null;
  }
  
  public int getScanMode() throws RemoteException {
    return 0;
  }
  
  public boolean getSilenceMode(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public int getSimAccessPermission(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public IBluetoothSocketManager getSocketManager() throws RemoteException {
    return null;
  }
  
  public int getState() throws RemoteException {
    return 0;
  }
  
  public long getSupportedProfiles() throws RemoteException {
    return 0L;
  }
  
  public ParcelUuid[] getUuids() throws RemoteException {
    return null;
  }
  
  public boolean isActivityAndEnergyReportingSupported() throws RemoteException {
    return false;
  }
  
  public boolean isBondingInitiatedLocally(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean isDiscovering() throws RemoteException {
    return false;
  }
  
  public boolean isLe2MPhySupported() throws RemoteException {
    return false;
  }
  
  public boolean isLeCodedPhySupported() throws RemoteException {
    return false;
  }
  
  public boolean isLeExtendedAdvertisingSupported() throws RemoteException {
    return false;
  }
  
  public boolean isLePeriodicAdvertisingSupported() throws RemoteException {
    return false;
  }
  
  public boolean isMultiAdvertisementSupported() throws RemoteException {
    return false;
  }
  
  public boolean isOffloadedFilteringSupported() throws RemoteException {
    return false;
  }
  
  public boolean isOffloadedScanBatchingSupported() throws RemoteException {
    return false;
  }
  
  public void onBrEdrDown() throws RemoteException {}
  
  public void onLeServiceUp() throws RemoteException {}
  
  public void registerCallback(IBluetoothCallback paramIBluetoothCallback) throws RemoteException {}
  
  public boolean registerMetadataListener(IBluetoothMetadataListener paramIBluetoothMetadataListener, BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean removeActiveDevice(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean removeBond(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public BluetoothActivityEnergyInfo reportActivityInfo() throws RemoteException {
    return null;
  }
  
  public void requestActivityInfo(ResultReceiver paramResultReceiver) throws RemoteException {}
  
  public boolean sdpSearch(BluetoothDevice paramBluetoothDevice, ParcelUuid paramParcelUuid) throws RemoteException {
    return false;
  }
  
  public boolean setActiveDevice(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setBluetoothClass(BluetoothClass paramBluetoothClass) throws RemoteException {
    return false;
  }
  
  public boolean setDiscoverableTimeout(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setIoCapability(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setLeIoCapability(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setMessageAccessPermission(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setMetadata(BluetoothDevice paramBluetoothDevice, int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    return false;
  }
  
  public boolean setName(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean setPairingConfirmation(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean setPasskey(BluetoothDevice paramBluetoothDevice, boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    return false;
  }
  
  public boolean setPhonebookAccessPermission(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setPin(BluetoothDevice paramBluetoothDevice, boolean paramBoolean, int paramInt, byte[] paramArrayOfbyte) throws RemoteException {
    return false;
  }
  
  public boolean setRemoteAlias(BluetoothDevice paramBluetoothDevice, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean setScanMode(int paramInt1, int paramInt2) throws RemoteException {
    return false;
  }
  
  public boolean setSilenceMode(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean setSimAccessPermission(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean startDiscovery(String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public void unregisterCallback(IBluetoothCallback paramIBluetoothCallback) throws RemoteException {}
  
  public boolean unregisterMetadataListener(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetooth$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */