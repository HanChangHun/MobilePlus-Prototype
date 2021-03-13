package android.bluetooth;

import android.app.PendingIntent;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertisingSetParameters;
import android.bluetooth.le.IAdvertisingSetCallback;
import android.bluetooth.le.IPeriodicAdvertisingCallback;
import android.bluetooth.le.IScannerCallback;
import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.WorkSource;
import java.util.List;

public class Default implements IBluetoothGatt {
  public void addService(int paramInt, BluetoothGattService paramBluetoothGattService) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void beginReliableWrite(int paramInt, String paramString) throws RemoteException {}
  
  public void clearServices(int paramInt) throws RemoteException {}
  
  public void clientConnect(int paramInt1, String paramString, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, int paramInt3) throws RemoteException {}
  
  public void clientDisconnect(int paramInt, String paramString) throws RemoteException {}
  
  public void clientReadPhy(int paramInt, String paramString) throws RemoteException {}
  
  public void clientSetPreferredPhy(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {}
  
  public void configureMTU(int paramInt1, String paramString, int paramInt2) throws RemoteException {}
  
  public void connectionParameterUpdate(int paramInt1, String paramString, int paramInt2) throws RemoteException {}
  
  public void disconnectAll() throws RemoteException {}
  
  public void discoverServiceByUuid(int paramInt, String paramString, ParcelUuid paramParcelUuid) throws RemoteException {}
  
  public void discoverServices(int paramInt, String paramString) throws RemoteException {}
  
  public void enableAdvertisingSet(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void endReliableWrite(int paramInt, String paramString, boolean paramBoolean) throws RemoteException {}
  
  public void flushPendingBatchResults(int paramInt) throws RemoteException {}
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public void getOwnAddress(int paramInt) throws RemoteException {}
  
  public void leConnectionUpdate(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) throws RemoteException {}
  
  public int numHwTrackFiltersAvailable() throws RemoteException {
    return 0;
  }
  
  public void readCharacteristic(int paramInt1, String paramString, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void readDescriptor(int paramInt1, String paramString, int paramInt2, int paramInt3) throws RemoteException {}
  
  public void readRemoteRssi(int paramInt, String paramString) throws RemoteException {}
  
  public void readUsingCharacteristicUuid(int paramInt1, String paramString, ParcelUuid paramParcelUuid, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {}
  
  public void refreshDevice(int paramInt, String paramString) throws RemoteException {}
  
  public void registerClient(ParcelUuid paramParcelUuid, IBluetoothGattCallback paramIBluetoothGattCallback) throws RemoteException {}
  
  public void registerForNotification(int paramInt1, String paramString, int paramInt2, boolean paramBoolean) throws RemoteException {}
  
  public void registerScanner(IScannerCallback paramIScannerCallback, WorkSource paramWorkSource) throws RemoteException {}
  
  public void registerServer(ParcelUuid paramParcelUuid, IBluetoothGattServerCallback paramIBluetoothGattServerCallback) throws RemoteException {}
  
  public void registerSync(ScanResult paramScanResult, int paramInt1, int paramInt2, IPeriodicAdvertisingCallback paramIPeriodicAdvertisingCallback) throws RemoteException {}
  
  public void removeService(int paramInt1, int paramInt2) throws RemoteException {}
  
  public void sendNotification(int paramInt1, String paramString, int paramInt2, boolean paramBoolean, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void sendResponse(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void serverConnect(int paramInt1, String paramString, boolean paramBoolean, int paramInt2) throws RemoteException {}
  
  public void serverDisconnect(int paramInt, String paramString) throws RemoteException {}
  
  public void serverReadPhy(int paramInt, String paramString) throws RemoteException {}
  
  public void serverSetPreferredPhy(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {}
  
  public void setAdvertisingData(int paramInt, AdvertiseData paramAdvertiseData) throws RemoteException {}
  
  public void setAdvertisingParameters(int paramInt, AdvertisingSetParameters paramAdvertisingSetParameters) throws RemoteException {}
  
  public void setPeriodicAdvertisingData(int paramInt, AdvertiseData paramAdvertiseData) throws RemoteException {}
  
  public void setPeriodicAdvertisingEnable(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setPeriodicAdvertisingParameters(int paramInt, PeriodicAdvertisingParameters paramPeriodicAdvertisingParameters) throws RemoteException {}
  
  public void setScanResponseData(int paramInt, AdvertiseData paramAdvertiseData) throws RemoteException {}
  
  public void startAdvertisingSet(AdvertisingSetParameters paramAdvertisingSetParameters, AdvertiseData paramAdvertiseData1, AdvertiseData paramAdvertiseData2, PeriodicAdvertisingParameters paramPeriodicAdvertisingParameters, AdvertiseData paramAdvertiseData3, int paramInt1, int paramInt2, IAdvertisingSetCallback paramIAdvertisingSetCallback) throws RemoteException {}
  
  public void startScan(int paramInt, ScanSettings paramScanSettings, List<ScanFilter> paramList, List paramList1, String paramString1, String paramString2) throws RemoteException {}
  
  public void startScanForIntent(PendingIntent paramPendingIntent, ScanSettings paramScanSettings, List<ScanFilter> paramList, String paramString1, String paramString2) throws RemoteException {}
  
  public void stopAdvertisingSet(IAdvertisingSetCallback paramIAdvertisingSetCallback) throws RemoteException {}
  
  public void stopScan(int paramInt) throws RemoteException {}
  
  public void stopScanForIntent(PendingIntent paramPendingIntent, String paramString) throws RemoteException {}
  
  public void unregAll() throws RemoteException {}
  
  public void unregisterClient(int paramInt) throws RemoteException {}
  
  public void unregisterScanner(int paramInt) throws RemoteException {}
  
  public void unregisterServer(int paramInt) throws RemoteException {}
  
  public void unregisterSync(IPeriodicAdvertisingCallback paramIPeriodicAdvertisingCallback) throws RemoteException {}
  
  public void writeCharacteristic(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfbyte) throws RemoteException {}
  
  public void writeDescriptor(int paramInt1, String paramString, int paramInt2, int paramInt3, byte[] paramArrayOfbyte) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothGatt$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */