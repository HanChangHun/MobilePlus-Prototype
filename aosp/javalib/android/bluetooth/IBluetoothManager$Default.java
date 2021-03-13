package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBluetoothManager {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean bindBluetoothProfileService(int paramInt, IBluetoothProfileServiceConnection paramIBluetoothProfileServiceConnection) throws RemoteException {
    return false;
  }
  
  public boolean disable(String paramString, boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean disableBle(String paramString, IBinder paramIBinder) throws RemoteException {
    return false;
  }
  
  public boolean enable(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean enableBle(String paramString, IBinder paramIBinder) throws RemoteException {
    return false;
  }
  
  public boolean enableNoAutoConnect(String paramString) throws RemoteException {
    return false;
  }
  
  public String getAddress() throws RemoteException {
    return null;
  }
  
  public IBluetoothGatt getBluetoothGatt() throws RemoteException {
    return null;
  }
  
  public String getName() throws RemoteException {
    return null;
  }
  
  public int getState() throws RemoteException {
    return 0;
  }
  
  public List<String> getSystemConfigEnabledProfilesForPackage(String paramString) throws RemoteException {
    return null;
  }
  
  public boolean isBleAppPresent() throws RemoteException {
    return false;
  }
  
  public boolean isBleScanAlwaysAvailable() throws RemoteException {
    return false;
  }
  
  public boolean isEnabled() throws RemoteException {
    return false;
  }
  
  public boolean isHearingAidProfileSupported() throws RemoteException {
    return false;
  }
  
  public boolean onFactoryReset() throws RemoteException {
    return false;
  }
  
  public IBluetooth registerAdapter(IBluetoothManagerCallback paramIBluetoothManagerCallback) throws RemoteException {
    return null;
  }
  
  public void registerStateChangeCallback(IBluetoothStateChangeCallback paramIBluetoothStateChangeCallback) throws RemoteException {}
  
  public void unbindBluetoothProfileService(int paramInt, IBluetoothProfileServiceConnection paramIBluetoothProfileServiceConnection) throws RemoteException {}
  
  public void unregisterAdapter(IBluetoothManagerCallback paramIBluetoothManagerCallback) throws RemoteException {}
  
  public void unregisterStateChangeCallback(IBluetoothStateChangeCallback paramIBluetoothStateChangeCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */