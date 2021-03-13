package android.content.om;

import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

public class Default implements IOverlayManager {
  public IBinder asBinder() {
    return null;
  }
  
  public Map getAllOverlays(int paramInt) throws RemoteException {
    return null;
  }
  
  public String[] getDefaultOverlayPackages() throws RemoteException {
    return null;
  }
  
  public OverlayInfo getOverlayInfo(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public List getOverlayInfosForTarget(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public void invalidateCachesForOverlay(String paramString, int paramInt) throws RemoteException {}
  
  public boolean setEnabled(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setEnabledExclusive(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setEnabledExclusiveInCategory(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setHighestPriority(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setLowestPriority(String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean setPriority(String paramString1, String paramString2, int paramInt) throws RemoteException {
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/om/IOverlayManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */