package android.app.backup;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;

public class Default implements IBackupManager {
  public void acknowledgeFullBackupOrRestore(int paramInt, boolean paramBoolean, String paramString1, String paramString2, IFullBackupRestoreObserver paramIFullBackupRestoreObserver) throws RemoteException {}
  
  public void acknowledgeFullBackupOrRestoreForUser(int paramInt1, int paramInt2, boolean paramBoolean, String paramString1, String paramString2, IFullBackupRestoreObserver paramIFullBackupRestoreObserver) throws RemoteException {}
  
  public void adbBackup(int paramInt, ParcelFileDescriptor paramParcelFileDescriptor, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7, boolean paramBoolean8, String[] paramArrayOfString) throws RemoteException {}
  
  public void adbRestore(int paramInt, ParcelFileDescriptor paramParcelFileDescriptor) throws RemoteException {}
  
  public void agentConnected(String paramString, IBinder paramIBinder) throws RemoteException {}
  
  public void agentConnectedForUser(int paramInt, String paramString, IBinder paramIBinder) throws RemoteException {}
  
  public void agentDisconnected(String paramString) throws RemoteException {}
  
  public void agentDisconnectedForUser(int paramInt, String paramString) throws RemoteException {}
  
  public IBinder asBinder() {
    return null;
  }
  
  public void backupNow() throws RemoteException {}
  
  public void backupNowForUser(int paramInt) throws RemoteException {}
  
  public IRestoreSession beginRestoreSessionForUser(int paramInt, String paramString1, String paramString2) throws RemoteException {
    return null;
  }
  
  public void cancelBackups() throws RemoteException {}
  
  public void cancelBackupsForUser(int paramInt) throws RemoteException {}
  
  public void clearBackupData(String paramString1, String paramString2) throws RemoteException {}
  
  public void clearBackupDataForUser(int paramInt, String paramString1, String paramString2) throws RemoteException {}
  
  public void dataChanged(String paramString) throws RemoteException {}
  
  public void dataChangedForUser(int paramInt, String paramString) throws RemoteException {}
  
  public void excludeKeysFromRestore(String paramString, List<String> paramList) throws RemoteException {}
  
  public String[] filterAppsEligibleForBackupForUser(int paramInt, String[] paramArrayOfString) throws RemoteException {
    return null;
  }
  
  public void fullTransportBackupForUser(int paramInt, String[] paramArrayOfString) throws RemoteException {}
  
  public long getAvailableRestoreTokenForUser(int paramInt, String paramString) throws RemoteException {
    return 0L;
  }
  
  public Intent getConfigurationIntent(String paramString) throws RemoteException {
    return null;
  }
  
  public Intent getConfigurationIntentForUser(int paramInt, String paramString) throws RemoteException {
    return null;
  }
  
  public String getCurrentTransport() throws RemoteException {
    return null;
  }
  
  public ComponentName getCurrentTransportComponentForUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public String getCurrentTransportForUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public Intent getDataManagementIntent(String paramString) throws RemoteException {
    return null;
  }
  
  public Intent getDataManagementIntentForUser(int paramInt, String paramString) throws RemoteException {
    return null;
  }
  
  public CharSequence getDataManagementLabelForUser(int paramInt, String paramString) throws RemoteException {
    return null;
  }
  
  public String getDestinationString(String paramString) throws RemoteException {
    return null;
  }
  
  public String getDestinationStringForUser(int paramInt, String paramString) throws RemoteException {
    return null;
  }
  
  public String[] getTransportWhitelist() throws RemoteException {
    return null;
  }
  
  public UserHandle getUserForAncestralSerialNumber(long paramLong) throws RemoteException {
    return null;
  }
  
  public boolean hasBackupPassword() throws RemoteException {
    return false;
  }
  
  public void initializeTransportsForUser(int paramInt, String[] paramArrayOfString, IBackupObserver paramIBackupObserver) throws RemoteException {}
  
  public boolean isAppEligibleForBackupForUser(int paramInt, String paramString) throws RemoteException {
    return false;
  }
  
  public boolean isBackupEnabled() throws RemoteException {
    return false;
  }
  
  public boolean isBackupEnabledForUser(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isBackupServiceActive(int paramInt) throws RemoteException {
    return false;
  }
  
  public boolean isUserReadyForBackup(int paramInt) throws RemoteException {
    return false;
  }
  
  public ComponentName[] listAllTransportComponentsForUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public String[] listAllTransports() throws RemoteException {
    return null;
  }
  
  public String[] listAllTransportsForUser(int paramInt) throws RemoteException {
    return null;
  }
  
  public void opComplete(int paramInt, long paramLong) throws RemoteException {}
  
  public void opCompleteForUser(int paramInt1, int paramInt2, long paramLong) throws RemoteException {}
  
  public int requestBackup(String[] paramArrayOfString, IBackupObserver paramIBackupObserver, IBackupManagerMonitor paramIBackupManagerMonitor, int paramInt) throws RemoteException {
    return 0;
  }
  
  public int requestBackupForUser(int paramInt1, String[] paramArrayOfString, IBackupObserver paramIBackupObserver, IBackupManagerMonitor paramIBackupManagerMonitor, int paramInt2) throws RemoteException {
    return 0;
  }
  
  public void restoreAtInstall(String paramString, int paramInt) throws RemoteException {}
  
  public void restoreAtInstallForUser(int paramInt1, String paramString, int paramInt2) throws RemoteException {}
  
  public String selectBackupTransport(String paramString) throws RemoteException {
    return null;
  }
  
  public void selectBackupTransportAsyncForUser(int paramInt, ComponentName paramComponentName, ISelectBackupTransportCallback paramISelectBackupTransportCallback) throws RemoteException {}
  
  public String selectBackupTransportForUser(int paramInt, String paramString) throws RemoteException {
    return null;
  }
  
  public void setAncestralSerialNumber(long paramLong) throws RemoteException {}
  
  public void setAutoRestore(boolean paramBoolean) throws RemoteException {}
  
  public void setAutoRestoreForUser(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void setBackupEnabled(boolean paramBoolean) throws RemoteException {}
  
  public void setBackupEnabledForUser(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public boolean setBackupPassword(String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public void setBackupServiceActive(int paramInt, boolean paramBoolean) throws RemoteException {}
  
  public void updateTransportAttributesForUser(int paramInt, ComponentName paramComponentName, String paramString1, Intent paramIntent1, String paramString2, Intent paramIntent2, CharSequence paramCharSequence) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/IBackupManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */