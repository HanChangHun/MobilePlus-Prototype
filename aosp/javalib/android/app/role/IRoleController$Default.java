package android.app.role;

import android.os.IBinder;
import android.os.RemoteCallback;
import android.os.RemoteException;

public class Default implements IRoleController {
  public IBinder asBinder() {
    return null;
  }
  
  public void grantDefaultRoles(RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public void isApplicationQualifiedForRole(String paramString1, String paramString2, RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public void isApplicationVisibleForRole(String paramString1, String paramString2, RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public void isRoleVisible(String paramString, RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public void onAddRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public void onClearRoleHolders(String paramString, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public void onRemoveRoleHolder(String paramString1, String paramString2, int paramInt, RemoteCallback paramRemoteCallback) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/IRoleController$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */