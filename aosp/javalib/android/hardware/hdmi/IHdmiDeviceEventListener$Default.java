package android.hardware.hdmi;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IHdmiDeviceEventListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onStatusChanged(HdmiDeviceInfo paramHdmiDeviceInfo, int paramInt) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiDeviceEventListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */