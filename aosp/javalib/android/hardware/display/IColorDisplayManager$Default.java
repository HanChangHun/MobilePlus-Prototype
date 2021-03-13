package android.hardware.display;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IColorDisplayManager {
  public IBinder asBinder() {
    return null;
  }
  
  public int getColorMode() throws RemoteException {
    return 0;
  }
  
  public int getNightDisplayAutoMode() throws RemoteException {
    return 0;
  }
  
  public int getNightDisplayAutoModeRaw() throws RemoteException {
    return 0;
  }
  
  public int getNightDisplayColorTemperature() throws RemoteException {
    return 0;
  }
  
  public Time getNightDisplayCustomEndTime() throws RemoteException {
    return null;
  }
  
  public Time getNightDisplayCustomStartTime() throws RemoteException {
    return null;
  }
  
  public int getTransformCapabilities() throws RemoteException {
    return 0;
  }
  
  public boolean isDeviceColorManaged() throws RemoteException {
    return false;
  }
  
  public boolean isDisplayWhiteBalanceEnabled() throws RemoteException {
    return false;
  }
  
  public boolean isNightDisplayActivated() throws RemoteException {
    return false;
  }
  
  public boolean isSaturationActivated() throws RemoteException {
    return false;
  }
  
  public boolean setAppSaturationLevel(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public void setColorMode(int paramInt) throws RemoteException {}
  
  public boolean setDisplayWhiteBalanceEnabled(boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean setNightDisplayActivated(boolean paramBoolean) throws RemoteException {
    return false;
  }
  
  public boolean setNightDisplayAutoMode(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setNightDisplayColorTemperature(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setNightDisplayCustomEndTime(Time paramTime) throws RemoteException {
    return false;
  }
  
  public boolean setNightDisplayCustomStartTime(Time paramTime) throws RemoteException {
    return false;
  }
  
  public boolean setSaturationLevel(int paramInt) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/IColorDisplayManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */