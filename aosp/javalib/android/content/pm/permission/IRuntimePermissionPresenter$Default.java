package android.content.pm.permission;

import android.os.IBinder;
import android.os.RemoteCallback;
import android.os.RemoteException;

public class Default implements IRuntimePermissionPresenter {
  public IBinder asBinder() {
    return null;
  }
  
  public void getAppPermissions(String paramString, RemoteCallback paramRemoteCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/permission/IRuntimePermissionPresenter$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */