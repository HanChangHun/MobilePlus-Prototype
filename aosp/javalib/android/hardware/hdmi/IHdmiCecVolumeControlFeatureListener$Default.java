package android.hardware.hdmi;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IHdmiCecVolumeControlFeatureListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onHdmiCecVolumeControlFeature(boolean paramBoolean) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/hdmi/IHdmiCecVolumeControlFeatureListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */