package android.debug;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.Map;

public class Default implements IAdbManager {
  public void allowDebugging(boolean paramBoolean, String paramString) throws RemoteException {}
  
  public void allowWirelessDebugging(boolean paramBoolean, String paramString) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void clearDebuggingKeys() throws RemoteException {}
  
  public void denyDebugging() throws RemoteException {}
  
  public void denyWirelessDebugging() throws RemoteException {}
  
  public void disablePairing() throws RemoteException {}
  
  public void enablePairingByPairingCode() throws RemoteException {}
  
  public void enablePairingByQrCode(String paramString1, String paramString2) throws RemoteException {}
  
  public int getAdbWirelessPort() throws RemoteException {
    return 0;
  }
  
  public Map getPairedDevices() throws RemoteException {
    return null;
  }
  
  public boolean isAdbWifiQrSupported() throws RemoteException {
    return false;
  }
  
  public boolean isAdbWifiSupported() throws RemoteException {
    return false;
  }
  
  public void unpairDevice(String paramString) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/debug/IAdbManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */