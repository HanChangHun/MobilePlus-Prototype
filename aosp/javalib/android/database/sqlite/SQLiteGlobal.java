package android.database.sqlite;

import android.content.res.Resources;
import android.os.StatFs;
import android.os.SystemProperties;

public final class SQLiteGlobal {
  public static final String SYNC_MODE_FULL = "FULL";
  
  private static final String TAG = "SQLiteGlobal";
  
  static final String WIPE_CHECK_FILE_SUFFIX = "-wipecheck";
  
  private static int sDefaultPageSize;
  
  public static volatile String sDefaultSyncMode;
  
  private static final Object sLock = new Object();
  
  public static boolean checkDbWipe() {
    return false;
  }
  
  public static String getDefaultJournalMode() {
    return SystemProperties.get("debug.sqlite.journalmode", Resources.getSystem().getString(17040026));
  }
  
  public static int getDefaultPageSize() {
    synchronized (sLock) {
      if (sDefaultPageSize == 0) {
        StatFs statFs = new StatFs();
        this("/data");
        sDefaultPageSize = statFs.getBlockSize();
      } 
      return SystemProperties.getInt("debug.sqlite.pagesize", sDefaultPageSize);
    } 
  }
  
  public static String getDefaultSyncMode() {
    String str = sDefaultSyncMode;
    return (str != null) ? str : SystemProperties.get("debug.sqlite.syncmode", Resources.getSystem().getString(17040027));
  }
  
  public static int getIdleConnectionTimeout() {
    return SystemProperties.getInt("debug.sqlite.idle_connection_timeout", Resources.getSystem().getInteger(17694927));
  }
  
  public static int getJournalSizeLimit() {
    return SystemProperties.getInt("debug.sqlite.journalsizelimit", Resources.getSystem().getInteger(17694928));
  }
  
  public static int getWALAutoCheckpoint() {
    return Math.max(1, SystemProperties.getInt("debug.sqlite.wal.autocheckpoint", Resources.getSystem().getInteger(17694929)));
  }
  
  public static int getWALConnectionPoolSize() {
    return Math.max(2, SystemProperties.getInt("debug.sqlite.wal.poolsize", Resources.getSystem().getInteger(17694926)));
  }
  
  public static String getWALSyncMode() {
    String str = sDefaultSyncMode;
    return (str != null) ? str : SystemProperties.get("debug.sqlite.wal.syncmode", Resources.getSystem().getString(17040028));
  }
  
  public static long getWALTruncateSize() {
    long l = SQLiteCompatibilityWalFlags.getTruncateSize();
    return (l >= 0L) ? l : SystemProperties.getInt("debug.sqlite.wal.truncatesize", Resources.getSystem().getInteger(17694930));
  }
  
  private static native int nativeReleaseMemory();
  
  public static int releaseMemory() {
    return nativeReleaseMemory();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteGlobal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */