package android.hardware.lights;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements ILightsManager {
  public IBinder asBinder() {
    return null;
  }
  
  public void closeSession(IBinder paramIBinder) throws RemoteException {}
  
  public LightState getLightState(int paramInt) throws RemoteException {
    return null;
  }
  
  public List<Light> getLights() throws RemoteException {
    return null;
  }
  
  public void openSession(IBinder paramIBinder) throws RemoteException {}
  
  public void setLightStates(IBinder paramIBinder, int[] paramArrayOfint, LightState[] paramArrayOfLightState) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/lights/ILightsManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */