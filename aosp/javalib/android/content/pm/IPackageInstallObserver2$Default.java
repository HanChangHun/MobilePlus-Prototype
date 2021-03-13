package android.content.pm;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IPackageInstallObserver2 {
  public IBinder asBinder() {
    return null;
  }
  
  public void onPackageInstalled(String paramString1, int paramInt, String paramString2, Bundle paramBundle) throws RemoteException {}
  
  public void onUserActionRequired(Intent paramIntent) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/IPackageInstallObserver2$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */