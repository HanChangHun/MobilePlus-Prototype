package android.app.usage;

import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.RemoteException;

public class Default implements IStorageStatsManager {
  public IBinder asBinder() {
    return null;
  }
  
  public long getCacheBytes(String paramString1, String paramString2) throws RemoteException {
    return 0L;
  }
  
  public long getCacheQuotaBytes(String paramString1, int paramInt, String paramString2) throws RemoteException {
    return 0L;
  }
  
  public long getFreeBytes(String paramString1, String paramString2) throws RemoteException {
    return 0L;
  }
  
  public long getTotalBytes(String paramString1, String paramString2) throws RemoteException {
    return 0L;
  }
  
  public boolean isQuotaSupported(String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public boolean isReservedSupported(String paramString1, String paramString2) throws RemoteException {
    return false;
  }
  
  public ParceledListSlice queryCratesForPackage(String paramString1, String paramString2, int paramInt, String paramString3) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice queryCratesForUid(String paramString1, int paramInt, String paramString2) throws RemoteException {
    return null;
  }
  
  public ParceledListSlice queryCratesForUser(String paramString1, int paramInt, String paramString2) throws RemoteException {
    return null;
  }
  
  public ExternalStorageStats queryExternalStatsForUser(String paramString1, int paramInt, String paramString2) throws RemoteException {
    return null;
  }
  
  public StorageStats queryStatsForPackage(String paramString1, String paramString2, int paramInt, String paramString3) throws RemoteException {
    return null;
  }
  
  public StorageStats queryStatsForUid(String paramString1, int paramInt, String paramString2) throws RemoteException {
    return null;
  }
  
  public StorageStats queryStatsForUser(String paramString1, int paramInt, String paramString2) throws RemoteException {
    return null;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/usage/IStorageStatsManager$Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */