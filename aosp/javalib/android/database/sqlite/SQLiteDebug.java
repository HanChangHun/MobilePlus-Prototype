package android.database.sqlite;

import android.os.Build;
import android.os.Process;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Printer;
import java.util.ArrayList;

public final class SQLiteDebug {
  public static void dump(Printer paramPrinter, String[] paramArrayOfString) {
    dump(paramPrinter, paramArrayOfString, false);
  }
  
  public static void dump(Printer paramPrinter, String[] paramArrayOfString, boolean paramBoolean) {
    boolean bool = false;
    int i = paramArrayOfString.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfString[b].equals("-v"))
        bool = true; 
    } 
    SQLiteDatabase.dumpAll(paramPrinter, bool, paramBoolean);
  }
  
  public static PagerStats getDatabaseInfo() {
    PagerStats pagerStats = new PagerStats();
    nativeGetPagerStats(pagerStats);
    pagerStats.dbStats = SQLiteDatabase.getDbStats();
    return pagerStats;
  }
  
  private static native void nativeGetPagerStats(PagerStats paramPagerStats);
  
  public static boolean shouldLogSlowQuery(long paramLong) {
    boolean bool;
    if (paramLong >= Math.min(SystemProperties.getInt("db.log.slow_query_threshold", 2147483647), SystemProperties.getInt(NoPreloadHolder.SLOW_QUERY_THRESHOLD_UID_PROP, 2147483647))) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static class DbStats {
    public String cache;
    
    public String dbName;
    
    public long dbSize;
    
    public int lookaside;
    
    public long pageSize;
    
    public DbStats(String param1String, long param1Long1, long param1Long2, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      this.dbName = param1String;
      this.pageSize = param1Long2 / 1024L;
      this.dbSize = param1Long1 * param1Long2 / 1024L;
      this.lookaside = param1Int1;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(param1Int2);
      stringBuilder.append("/");
      stringBuilder.append(param1Int3);
      stringBuilder.append("/");
      stringBuilder.append(param1Int4);
      this.cache = stringBuilder.toString();
    }
  }
  
  public static final class NoPreloadHolder {
    public static final boolean DEBUG_LOG_DETAILED;
    
    public static final boolean DEBUG_LOG_SLOW_QUERIES = Build.IS_DEBUGGABLE;
    
    public static final boolean DEBUG_SQL_LOG = Log.isLoggable("SQLiteLog", 2);
    
    public static final boolean DEBUG_SQL_STATEMENTS = Log.isLoggable("SQLiteStatements", 2);
    
    public static final boolean DEBUG_SQL_TIME = Log.isLoggable("SQLiteTime", 2);
    
    private static final String SLOW_QUERY_THRESHOLD_PROP = "db.log.slow_query_threshold";
    
    private static final String SLOW_QUERY_THRESHOLD_UID_PROP;
    
    static {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("db.log.slow_query_threshold.");
      stringBuilder.append(Process.myUid());
      SLOW_QUERY_THRESHOLD_UID_PROP = stringBuilder.toString();
      boolean bool = Build.IS_DEBUGGABLE;
      boolean bool1 = false;
      if (bool && SystemProperties.getBoolean("db.log.detailed", false))
        bool1 = true; 
      DEBUG_LOG_DETAILED = bool1;
    }
  }
  
  public static class PagerStats {
    public ArrayList<SQLiteDebug.DbStats> dbStats;
    
    public int largestMemAlloc;
    
    public int memoryUsed;
    
    public int pageCacheOverflow;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */