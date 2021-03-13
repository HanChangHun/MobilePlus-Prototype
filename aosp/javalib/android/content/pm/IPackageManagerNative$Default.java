package android.content.pm;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IPackageManagerNative {
  public IBinder asBinder() {
    return null;
  }
  
  public String[] getAllPackages() throws RemoteException {
    return null;
  }
  
  public String getInstallerForPackage(String paramString) throws RemoteException {
    return null;
  }
  
  public int getLocationFlags(String paramString) throws RemoteException {
    return 0;
  }
  
  public String getModuleMetadataPackageName() throws RemoteException {
    return null;
  }
  
  public String[] getNamesForUids(int[] paramArrayOfint) throws RemoteException {
    return null;
  }
  
  public int getTargetSdkVersionForPackage(String paramString) throws RemoteException {
    return 0;
  }
  
  public long getVersionCodeForPackage(String paramString) throws RemoteException {
    return 0L;
  }
  
  public boolean[] isAudioPlaybackCaptureAllowed(String[] paramArrayOfString) throws RemoteException {
    return null;
  }
  
  public void registerPackageChangeObserver(IPackageChangeObserver paramIPackageChangeObserver) throws RemoteException {}
  
  public void unregisterPackageChangeObserver(IPackageChangeObserver paramIPackageChangeObserver) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageManagerNative$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */