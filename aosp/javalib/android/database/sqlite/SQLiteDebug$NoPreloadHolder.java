package android.database.sqlite;

import android.os.Build;
import android.os.Process;
import android.os.SystemProperties;
import android.util.Log;

public final class NoPreloadHolder {
  public static final boolean DEBUG_LOG_DETAILED;
  
  public static final boolean DEBUG_LOG_SLOW_QUERIES;
  
  public static final boolean DEBUG_SQL_LOG = Log.isLoggable("SQLiteLog", 2);
  
  public static final boolean DEBUG_SQL_STATEMENTS = Log.isLoggable("SQLiteStatements", 2);
  
  public static final boolean DEBUG_SQL_TIME = Log.isLoggable("SQLiteTime", 2);
  
  private static final String SLOW_QUERY_THRESHOLD_PROP = "db.log.slow_query_threshold";
  
  private static final String SLOW_QUERY_THRESHOLD_UID_PROP;
  
  static {
    DEBUG_LOG_SLOW_QUERIES = Build.IS_DEBUGGABLE;
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


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteDebug$NoPreloadHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */