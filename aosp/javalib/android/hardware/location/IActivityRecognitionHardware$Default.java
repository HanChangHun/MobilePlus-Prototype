package android.hardware.location;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IActivityRecognitionHardware {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean disableActivityEvent(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean enableActivityEvent(String paramString, int paramInt, long paramLong) throws RemoteException {
    return false;
  }
  
  public boolean flush() throws RemoteException {
    return false;
  }
  
  public String[] getSupportedActivities() throws RemoteException {
    return null;
  }
  
  public boolean isActivitySupported(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean registerSink(IActivityRecognitionHardwareSink paramIActivityRecognitionHardwareSink) throws RemoteException {
    return false;
  }
  
  public boolean unregisterSink(IActivityRecognitionHardwareSink paramIActivityRecognitionHardwareSink) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardware$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */