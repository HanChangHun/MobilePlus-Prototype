package android.app.role;

import android.os.IBinder;
import android.os.RemoteCallback;
import android.os.RemoteException;
import java.util.List;

public class Default implements IRoleManager {
  public void addOnRoleHoldersChangedListenerAsUser(IOnRoleHoldersChangedListener paramIOnRoleHoldersChangedListener, int paramInt) throws RemoteException {}
  
  public void addRoleHolderAsUser(String paramString1, String paramString2, int paramInt1, int paramInt2, RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public boolean addRoleHolderFromController(String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public IBinder asBinder() {
    return null;
  }
  
  public void clearRoleHoldersAsUser(String paramString, int paramInt1, int paramInt2, RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public String getDefaultSmsPackage(int paramInt) throws RemoteException {
    return null;
  }
  
  public List<String> getHeldRolesFromController(String paramString) throws RemoteException {
    return null;
  }
  
  public List<String> getRoleHoldersAsUser(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public boolean isRoleAvailable(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isRoleHeld(String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public void removeOnRoleHoldersChangedListenerAsUser(IOnRoleHoldersChangedListener paramIOnRoleHoldersChangedListener, int paramInt) throws RemoteException {}
  
  public void removeRoleHolderAsUser(String paramString1, String paramString2, int paramInt1, int paramInt2, RemoteCallback paramRemoteCallback) throws RemoteException {}
  
  public boolean removeRoleHolderFromController(String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public void setRoleNamesFromController(List<String> paramList) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/role/IRoleManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */