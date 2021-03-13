package android.app;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IUiModeManager {
  public IBinder asBinder() {
    return null;
  }
  
  public void disableCarMode(int paramInt) throws RemoteException {}
  
  public void disableCarModeByCallingPackage(int paramInt, String paramString) throws RemoteException {}
  
  public void enableCarMode(int paramInt1, int paramInt2, String paramString) throws RemoteException {}
  
  public int getCurrentModeType() throws RemoteException {
    return 0;
  }
  
  public long getCustomNightModeEnd() throws RemoteException {
    return 0L;
  }
  
  public long getCustomNightModeStart() throws RemoteException {
    return 0L;
  }
  
  public int getNightMode() throws RemoteException {
    return 0;
  }
  
  public boolean isNightModeLocked() throws RemoteException {
    return false;
  }
  
  public boolean isUiModeLocked() throws RemoteException {
    return false;
  }
  
  public void setCustomNightModeEnd(long paramLong) throws RemoteException {}
  
  public void setCustomNightModeStart(long paramLong) throws RemoteException {}
  
  public void setNightMode(int paramInt) throws RemoteException {}
  
  public boolean setNightModeActivated(boolean paramBoolean) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IUiModeManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */