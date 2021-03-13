package android.content;

import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;

public class Default implements IRestrictionsManager {
  public IBinder asBinder() {
    return null;
  }
  
  public Intent createLocalApprovalIntent() throws RemoteException {
    return null;
  }
  
  public Bundle getApplicationRestrictions(String paramString) throws RemoteException {
    return null;
  }
  
  public boolean hasRestrictionsProvider() throws RemoteException {
    return false;
  }
  
  public void notifyPermissionResponse(String paramString, PersistableBundle paramPersistableBundle) throws RemoteException {}
  
  public void requestPermission(String paramString1, String paramString2, String paramString3, PersistableBundle paramPersistableBundle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IRestrictionsManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */