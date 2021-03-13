package android.hardware;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements ISensorPrivacyManager {
  public void addSensorPrivacyListener(ISensorPrivacyListener paramISensorPrivacyListener) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public boolean isSensorPrivacyEnabled() throws RemoteException {
    return false;
  }
  
  public void removeSensorPrivacyListener(ISensorPrivacyListener paramISensorPrivacyListener) throws RemoteException {}
  
  public void setSensorPrivacy(boolean paramBoolean) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ISensorPrivacyManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */