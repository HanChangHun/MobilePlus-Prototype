package android.content.pm;

import android.content.IntentSender;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IPackageInstaller {
  public void abandonSession(int paramInt) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void bypassNextStagedInstallerCheck(boolean paramBoolean) throws RemoteException {}
  
  public int createSession(PackageInstaller.SessionParams paramSessionParams, String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public ParceledListSlice getAllSessions(int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getMySessions(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public PackageInstaller.SessionInfo getSessionInfo(int paramInt) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice getStagedSessions() throws RemoteException {
    return null;
  }
  
  public void installExistingPackage(String paramString, int paramInt1, int paramInt2, IntentSender paramIntentSender, int paramInt3, List<String> paramList) throws RemoteException {}
  
  public IPackageInstallerSession openSession(int paramInt) throws RemoteException {
    return null;
  }
  
  public void registerCallback(IPackageInstallerCallback paramIPackageInstallerCallback, int paramInt) throws RemoteException {}
  
  public void setPermissionsResult(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void uninstall(VersionedPackage paramVersionedPackage, String paramString, int paramInt1, IntentSender paramIntentSender, int paramInt2) throws RemoteException {}
  
  public void uninstallExistingPackage(VersionedPackage paramVersionedPackage, String paramString, IntentSender paramIntentSender, int paramInt) throws RemoteException {}
  
  public void unregisterCallback(IPackageInstallerCallback paramIPackageInstallerCallback) throws RemoteException {}
  
  public void updateSessionAppIcon(int paramInt, Bitmap paramBitmap) throws RemoteException {}
  
  public void updateSessionAppLabel(int paramInt, String paramString) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstaller$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */