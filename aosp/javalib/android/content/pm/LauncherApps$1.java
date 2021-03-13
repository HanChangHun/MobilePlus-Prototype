package android.content.pm;

import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.Iterator;
import java.util.List;

class null extends IOnAppsChangedListener.Stub {
  public void onPackageAdded(UserHandle paramUserHandle, String paramString) throws RemoteException {
    synchronized (LauncherApps.this) {
      Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.access$100(LauncherApps.this).iterator();
      while (iterator.hasNext())
        ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackageAdded(paramString, paramUserHandle); 
      return;
    } 
  }
  
  public void onPackageChanged(UserHandle paramUserHandle, String paramString) throws RemoteException {
    synchronized (LauncherApps.this) {
      Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.access$100(LauncherApps.this).iterator();
      while (iterator.hasNext())
        ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackageChanged(paramString, paramUserHandle); 
      return;
    } 
  }
  
  public void onPackageRemoved(UserHandle paramUserHandle, String paramString) throws RemoteException {
    synchronized (LauncherApps.this) {
      Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.access$100(LauncherApps.this).iterator();
      while (iterator.hasNext())
        ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackageRemoved(paramString, paramUserHandle); 
      return;
    } 
  }
  
  public void onPackagesAvailable(UserHandle paramUserHandle, String[] paramArrayOfString, boolean paramBoolean) throws RemoteException {
    synchronized (LauncherApps.this) {
      Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.access$100(LauncherApps.this).iterator();
      while (iterator.hasNext())
        ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackagesAvailable(paramArrayOfString, paramUserHandle, paramBoolean); 
      return;
    } 
  }
  
  public void onPackagesSuspended(UserHandle paramUserHandle, String[] paramArrayOfString, Bundle paramBundle) throws RemoteException {
    synchronized (LauncherApps.this) {
      Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.access$100(LauncherApps.this).iterator();
      while (iterator.hasNext())
        ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackagesSuspended(paramArrayOfString, paramBundle, paramUserHandle); 
      return;
    } 
  }
  
  public void onPackagesUnavailable(UserHandle paramUserHandle, String[] paramArrayOfString, boolean paramBoolean) throws RemoteException {
    synchronized (LauncherApps.this) {
      Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.access$100(LauncherApps.this).iterator();
      while (iterator.hasNext())
        ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackagesUnavailable(paramArrayOfString, paramUserHandle, paramBoolean); 
      return;
    } 
  }
  
  public void onPackagesUnsuspended(UserHandle paramUserHandle, String[] paramArrayOfString) throws RemoteException {
    synchronized (LauncherApps.this) {
      Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.access$100(LauncherApps.this).iterator();
      while (iterator.hasNext())
        ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnPackagesUnsuspended(paramArrayOfString, paramUserHandle); 
      return;
    } 
  }
  
  public void onShortcutChanged(UserHandle paramUserHandle, String paramString, ParceledListSlice paramParceledListSlice) {
    List<ShortcutInfo> list = paramParceledListSlice.getList();
    synchronized (LauncherApps.this) {
      Iterator<LauncherApps.CallbackMessageHandler> iterator = LauncherApps.access$100(LauncherApps.this).iterator();
      while (iterator.hasNext())
        ((LauncherApps.CallbackMessageHandler)iterator.next()).postOnShortcutChanged(paramString, paramUserHandle, list); 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LauncherApps$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */