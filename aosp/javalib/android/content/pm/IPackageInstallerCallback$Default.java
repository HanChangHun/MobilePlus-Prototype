package android.content.pm;

import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IPackageInstallerCallback {
  public IBinder asBinder() {
    return null;
  }
  
  public void onSessionActiveChanged(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void onSessionBadgingChanged(int paramInt) throws RemoteException {}
  
  public void onSessionCreated(int paramInt) throws RemoteException {}
  
  public void onSessionFinished(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void onSessionProgressChanged(int paramInt, float paramFloat) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallerCallback$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */