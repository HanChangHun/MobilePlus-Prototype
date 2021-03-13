package android.bluetooth;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IBluetoothHeadsetClient {
  public boolean acceptCall(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public boolean connect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean connectAudio(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public BluetoothHeadsetClientCall dial(BluetoothDevice paramBluetoothDevice, String paramString) throws RemoteException {
    return null;
  }
  
  public boolean disconnect(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean disconnectAudio(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean enterPrivateMode(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean explicitCallTransfer(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean getAudioRouteAllowed(BluetoothDevice paramBluetoothDevice) throws RemoteException {
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
  
  public Bundle getCurrentAgEvents(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return null;
  }
  
  public Bundle getCurrentAgFeatures(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return null;
  }
  
  public List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return null;
  }
  
  public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public boolean getLastVoiceTagNumber(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean holdCall(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean rejectCall(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean sendDTMF(BluetoothDevice paramBluetoothDevice, byte paramByte) throws RemoteException {
    return false;
  }
  
  public boolean sendVendorAtCommand(BluetoothDevice paramBluetoothDevice, int paramInt, String paramString) throws RemoteException {
    return false;
  }
  
  public void setAudioRouteAllowed(BluetoothDevice paramBluetoothDevice, boolean paramBoolean) throws RemoteException {}
  
  public boolean setConnectionPolicy(BluetoothDevice paramBluetoothDevice, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean startVoiceRecognition(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean stopVoiceRecognition(BluetoothDevice paramBluetoothDevice) throws RemoteException {
    return false;
  }
  
  public boolean terminateCall(BluetoothDevice paramBluetoothDevice, BluetoothHeadsetClientCall paramBluetoothHeadsetClientCall) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHeadsetClient$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */