package android.content;

import android.accounts.Account;
import android.database.IContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.List;

public class Default implements IContentService {
  public void addPeriodicSync(Account paramAccount, String paramString, Bundle paramBundle, long paramLong) throws RemoteException {}
  
  public void addStatusChangeListener(int paramInt, ISyncStatusObserver paramISyncStatusObserver) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void cancelRequest(SyncRequest paramSyncRequest) throws RemoteException {}
  
  public void cancelSync(Account paramAccount, String paramString, ComponentName paramComponentName) throws RemoteException {}
  
  public void cancelSyncAsUser(Account paramAccount, String paramString, ComponentName paramComponentName, int paramInt) throws RemoteException {}
  
  public Bundle getCache(String paramString, Uri paramUri, int paramInt) throws RemoteException {
    return null;
  }
  
  public List<SyncInfo> getCurrentSyncs() throws RemoteException {
    return null;
  }
  
  public List<SyncInfo> getCurrentSyncsAsUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public int getIsSyncable(Account paramAccount, String paramString) throws RemoteException {
    return 0;
  }
  
  public int getIsSyncableAsUser(Account paramAccount, String paramString, int paramInt) throws RemoteException {
    return 0;
  }
  
  public boolean getMasterSyncAutomatically() throws RemoteException {
    return false;
  }
  
  public boolean getMasterSyncAutomaticallyAsUser(int paramInt) throws RemoteException {
    return false;
  }
  
  public List<PeriodicSync> getPeriodicSyncs(Account paramAccount, String paramString, ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public String[] getSyncAdapterPackagesForAuthorityAsUser(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public SyncAdapterType[] getSyncAdapterTypes() throws RemoteException {
    return null;
  }
  
  public SyncAdapterType[] getSyncAdapterTypesAsUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public boolean getSyncAutomatically(Account paramAccount, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean getSyncAutomaticallyAsUser(Account paramAccount, String paramString, int paramInt) throws RemoteException {
    return false;
  }
  
  public SyncStatusInfo getSyncStatus(Account paramAccount, String paramString, ComponentName paramComponentName) throws RemoteException {
    return null;
  }
  
  public SyncStatusInfo getSyncStatusAsUser(Account paramAccount, String paramString, ComponentName paramComponentName, int paramInt) throws RemoteException {
    return null;
  }
  
  public boolean isSyncActive(Account paramAccount, String paramString, ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isSyncPending(Account paramAccount, String paramString, ComponentName paramComponentName) throws RemoteException {
    return false;
  }
  
  public boolean isSyncPendingAsUser(Account paramAccount, String paramString, ComponentName paramComponentName, int paramInt) throws RemoteException {
    return false;
  }
  
  public void notifyChange(Uri[] paramArrayOfUri, IContentObserver paramIContentObserver, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, String paramString) throws RemoteException {}
  
  public void onDbCorruption(String paramString1, String paramString2, String paramString3) throws RemoteException {}
  
  public void putCache(String paramString, Uri paramUri, Bundle paramBundle, int paramInt) throws RemoteException {}
  
  public void registerContentObserver(Uri paramUri, boolean paramBoolean, IContentObserver paramIContentObserver, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void removePeriodicSync(Account paramAccount, String paramString, Bundle paramBundle) throws RemoteException {}
  
  public void removeStatusChangeListener(ISyncStatusObserver paramISyncStatusObserver) throws RemoteException {}
  
  public void requestSync(Account paramAccount, String paramString1, Bundle paramBundle, String paramString2) throws RemoteException {}
  
  public void resetTodayStats() throws RemoteException {}
  
  public void setIsSyncable(Account paramAccount, String paramString, int paramInt) throws RemoteException {}
  
  public void setIsSyncableAsUser(Account paramAccount, String paramString, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void setMasterSyncAutomatically(boolean paramBoolean) throws RemoteException {}
  
  public void setMasterSyncAutomaticallyAsUser(boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public void setSyncAutomatically(Account paramAccount, String paramString, boolean paramBoolean) throws RemoteException {}
  
  public void setSyncAutomaticallyAsUser(Account paramAccount, String paramString, boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public void sync(SyncRequest paramSyncRequest, String paramString) throws RemoteException {}
  
  public void syncAsUser(SyncRequest paramSyncRequest, int paramInt, String paramString) throws RemoteException {}
  
  public void unregisterContentObserver(IContentObserver paramIContentObserver) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IContentService$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */