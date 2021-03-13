package android.app;

import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements ISearchManager {
  public IBinder asBinder() {
    return null;
  }
  
  public List<ResolveInfo> getGlobalSearchActivities() throws RemoteException {
    return null;
  }
  
  public ComponentName getGlobalSearchActivity() throws RemoteException {
    return null;
  }
  
  public SearchableInfo getSearchableInfo(ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public List<SearchableInfo> getSearchablesInGlobalSearch() throws RemoteException {
    return null;
  }
  
  public ComponentName getWebSearchActivity() throws RemoteException {
    return null;
  }
  
  public void launchAssist(int paramInt, Bundle paramBundle) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ISearchManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */