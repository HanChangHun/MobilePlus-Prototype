package android.app.usage;

import android.app.PendingIntent;
import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IUsageStatsManager {
  public IBinder asBinder() {
    return null;
  }
  
  public void forceUsageSourceSettingRead() throws RemoteException {}
  
  public int getAppStandbyBucket(String paramString1, String paramString2, int paramInt) throws RemoteException {
    return 0;
  }
  
  public ParceledListSlice getAppStandbyBuckets(String paramString, int paramInt) throws RemoteException {
    return null;
  }
  
  public int getUsageSource() throws RemoteException {
    return 0;
  }
  
  public boolean isAppInactive(String paramString1, int paramInt, String paramString2) throws RemoteException {
    return false;
  }
  
  public void onCarrierPrivilegedAppsChanged() throws RemoteException {}
  
  public ParceledListSlice queryConfigurationStats(int paramInt, long paramLong1, long paramLong2, String paramString) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice queryEventStats(int paramInt, long paramLong1, long paramLong2, String paramString) throws RemoteException {
    return null;
  }
  
  public UsageEvents queryEvents(long paramLong1, long paramLong2, String paramString) throws RemoteException {
    return null;
  }
  
  public UsageEvents queryEventsForPackage(long paramLong1, long paramLong2, String paramString) throws RemoteException {
    return null;
  }
  
  public UsageEvents queryEventsForPackageForUser(long paramLong1, long paramLong2, int paramInt, String paramString1, String paramString2) throws RemoteException {
    return null;
  }
  
  public UsageEvents queryEventsForUser(long paramLong1, long paramLong2, int paramInt, String paramString) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice queryUsageStats(int paramInt, long paramLong1, long paramLong2, String paramString) throws RemoteException {
    return null;
  }
  
  public void registerAppUsageLimitObserver(int paramInt, String[] paramArrayOfString, long paramLong1, long paramLong2, PendingIntent paramPendingIntent, String paramString) throws RemoteException {}
  
  public void registerAppUsageObserver(int paramInt, String[] paramArrayOfString, long paramLong, PendingIntent paramPendingIntent, String paramString) throws RemoteException {}
  
  public void registerUsageSessionObserver(int paramInt, String[] paramArrayOfString, long paramLong1, long paramLong2, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String paramString) throws RemoteException {}
  
  public void reportChooserSelection(String paramString1, int paramInt, String paramString2, String[] paramArrayOfString, String paramString3) throws RemoteException {}
  
  public void reportPastUsageStart(IBinder paramIBinder, String paramString1, long paramLong, String paramString2) throws RemoteException {}
  
  public void reportUsageStart(IBinder paramIBinder, String paramString1, String paramString2) throws RemoteException {}
  
  public void reportUsageStop(IBinder paramIBinder, String paramString1, String paramString2) throws RemoteException {}
  
  public void setAppInactive(String paramString, boolean paramBoolean, int paramInt) throws RemoteException {}
  
  public void setAppStandbyBucket(String paramString, int paramInt1, int paramInt2) throws RemoteException {}
  
  public void setAppStandbyBuckets(ParceledListSlice paramParceledListSlice, int paramInt) throws RemoteException {}
  
  public void unregisterAppUsageLimitObserver(int paramInt, String paramString) throws RemoteException {}
  
  public void unregisterAppUsageObserver(int paramInt, String paramString) throws RemoteException {}
  
  public void unregisterUsageSessionObserver(int paramInt, String paramString) throws RemoteException {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/IUsageStatsManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */