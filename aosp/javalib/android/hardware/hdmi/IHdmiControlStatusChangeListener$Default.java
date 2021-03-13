package android.hardware.hdmi;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IHdmiControlStatusChangeListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onStatusChange(boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiControlStatusChangeListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */