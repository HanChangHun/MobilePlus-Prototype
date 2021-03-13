package android.hardware.hdmi;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IHdmiInputChangeListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onChanged(HdmiDeviceInfo paramHdmiDeviceInfo) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiInputChangeListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */