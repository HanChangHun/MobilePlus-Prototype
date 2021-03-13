package android.hardware.location;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IActivityRecognitionHardwareSink {
  public IBinder asBinder() {
    return null;
  }
  
  public void onActivityChanged(ActivityChangedEvent paramActivityChangedEvent) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardwareSink$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */