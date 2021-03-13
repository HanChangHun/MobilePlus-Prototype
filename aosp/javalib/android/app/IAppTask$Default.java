package android.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IAppTask {
  public IBinder asBinder() {
    return null;
  }
  
  public void finishAndRemoveTask() throws RemoteException {}
  
  public ActivityManager.RecentTaskInfo getTaskInfo() throws RemoteException {
    return null;
  }
  
  public void moveToFront(IApplicationThread paramIApplicationThread, String paramString) throws RemoteException {}
  
  public void setExcludeFromRecents(boolean paramBoolean) throws RemoteException {}
  
  public int startActivity(IBinder paramIBinder, String paramString1, String paramString2, Intent paramIntent, String paramString3, Bundle paramBundle) throws RemoteException {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAppTask$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */