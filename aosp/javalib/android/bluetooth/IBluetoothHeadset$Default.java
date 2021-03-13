package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBluetoothHeadset {
  public IBinder asBinder() {
    return null;
  }
  
  public void clccResponse(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, String paramString, int paramInt5) throws RemoteException {}
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean connectAudio() throws RemoteException {
    return false;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean disconnectAudio() throws RemoteException {
    return false;
  }
  
  public BluetoothDevice getActiveDevice() throws RemoteException {
    return null;
  }
  
  public boolean getAudioRouteAllowed() throws RemoteException {
    return false;
  }
  
  public int getAudioState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
    return null;
  }
  
  public int getConnectionPolicy(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public int getConnectionState(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public int getPriority(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return 0;
  }
  
  public boolean isAudioConnected(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean isAudioOn() throws RemoteException {
    return false;
  }
  
  public boolean isInbandRingingEnabled() throws RemoteException {
    return false;
  }
  
  public void phoneStateChanged(int paramInt1, int paramInt2, int paramInt3, String paramString1, int paramInt4, String paramString2) throws RemoteException {}
  
  public boolean sendVendorSpecificResultCode(BluetoothDevice paramBluetoothDevice, String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public boolean setActiveDevice(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public void setAudioRouteAllowed(boolean paramBoolean) throws RemoteException {}
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public void setForceScoAudio(boolean paramBoolean) throws RemoteException {}
  
  public boolean setPriority(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean startScoUsingVirtualVoiceCall() throws RemoteException {
    return false;
  }
  
  public boolean startVoiceRecognition(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean stopScoUsingVirtualVoiceCall() throws RemoteException {
    return false;
  }
  
  public boolean stopVoiceRecognition(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHeadset$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */