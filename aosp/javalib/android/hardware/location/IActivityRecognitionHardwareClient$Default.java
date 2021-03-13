package android.hardware.location;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IActivityRecognitionHardwareClient {
  public IBinder asBinder() {
    return null;
  }
  
  public void onAvailabilityChanged(boolean paramBoolean, IActivityRecognitionHardware paramIActivityRecognitionHardware) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardwareClient$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */