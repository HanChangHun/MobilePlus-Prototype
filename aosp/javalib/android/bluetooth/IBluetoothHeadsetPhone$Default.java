package android.bluetooth;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IBluetoothHeadsetPhone {
  public boolean answerCall() throws RemoteException {
    return false;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public void cdmaSetSecondCallState(boolean paramBoolean) throws RemoteException {}
  
  public void cdmaSwapSecondCallState() throws RemoteException {}
  
  public String getNetworkOperator() throws RemoteException {
    return null;
  }
  
  public String getSubscriberNumber() throws RemoteException {
    return null;
  }
  
  public boolean hangupCall() throws RemoteException {
    return false;
  }
  
  public boolean listCurrentCalls() throws RemoteException {
    return false;
  }
  
  public boolean processChld(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean queryPhoneState() throws RemoteException {
    return false;
  }
  
  public boolean sendDtmf(int paramInt) throws RemoteException {
    return false;
  }
  
  public void updateBtHandsfreeAfterRadioTechnologyChange() throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/IBluetoothHeadsetPhone$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */