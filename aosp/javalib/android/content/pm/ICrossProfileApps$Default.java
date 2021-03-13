package android.content.pm;

import android.app.IApplicationThread;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;

public class Default implements ICrossProfileApps {
  public IBinder asBinder() {
    return null;
  }
  
  public boolean canConfigureInteractAcrossProfiles(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean canInteractAcrossProfiles(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean canRequestInteractAcrossProfiles(String paramString) throws RemoteException {
    return false;
  }
  
  public boolean canUserAttemptToConfigureInteractAcrossProfiles(String paramString) throws RemoteException {
    return false;
  }
  
  public void clearInteractAcrossProfilesAppOps() throws RemoteException {}
  
  public List<UserHandle> getTargetUserProfiles(String paramString) throws RemoteException {
    return null;
  }
  
  public void resetInteractAcrossProfilesAppOps(List<String> paramList) throws RemoteException {}
  
  public void setInteractAcrossProfilesAppOp(String paramString, int paramInt) throws RemoteException {}
  
  public void startActivityAsUser(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, ComponentName paramComponentName, int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void startActivityAsUserByIntent(IApplicationThread paramIApplicationThread, String paramString1, String paramString2, Intent paramIntent, int paramInt, IBinder paramIBinder, Bundle paramBundle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ICrossProfileApps$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */