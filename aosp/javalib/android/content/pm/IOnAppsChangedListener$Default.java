package android.content.pm;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;

public class Default implements IOnAppsChangedListener {
  public IBinder asBinder() {
    return null;
  }
  
  public void onPackageAdded(UserHandle paramUserHandle, String paramString) throws RemoteException {}
  
  public void onPackageChanged(UserHandle paramUserHandle, String paramString) throws RemoteException {}
  
  public void onPackageRemoved(UserHandle paramUserHandle, String paramString) throws RemoteException {}
  
  public void onPackagesAvailable(UserHandle paramUserHandle, String[] paramArrayOfString, boolean paramBoolean) throws RemoteException {}
  
  public void onPackagesSuspended(UserHandle paramUserHandle, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException {}
  
  public void onPackagesUnavailable(UserHandle paramUserHandle, String[] paramArrayOfString, boolean paramBoolean) throws RemoteException {}
  
  public void onPackagesUnsuspended(UserHandle paramUserHandle, String[] paramArrayOfString) throws RemoteException {}
  
  public void onShortcutChanged(UserHandle paramUserHandle, String paramString, ParceledListSlice paramParceledListSlice) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IOnAppsChangedListener$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */