package android.companion;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements ICompanionDeviceManager {
  public IBinder asBinder() {
    return null;
  }
  
  public void associate(AssociationRequest paramAssociationRequest, IFindDeviceCallback paramIFindDeviceCallback, String paramString) throws RemoteException {}
  
  public void disassociate(String paramString1, String paramString2) throws RemoteException {}
  
  public List<String> getAssociations(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public boolean hasNotificationAccess(ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isDeviceAssociatedForWifiConnection(String paramString1, String paramString2, int paramInt) throws RemoteException {
    return false;
  }
  
  public PendingIntent requestNotificationAccess(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public void stopScan(AssociationRequest paramAssociationRequest, IFindDeviceCallback paramIFindDeviceCallback, String paramString) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/ICompanionDeviceManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */