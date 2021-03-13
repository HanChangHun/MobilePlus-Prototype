package android.database.sqlite;

import android.util.ArrayMap;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public final class SQLiteDatabaseConfiguration {
  private static final Pattern EMAIL_IN_DB_PATTERN = Pattern.compile("[\\w\\.\\-]+@[\\w\\.\\-]+");
  
  public static final String MEMORY_DB_PATH = ":memory:";
  
  public final ArrayMap<String, BinaryOperator<String>> customAggregateFunctions = new ArrayMap();
  
  public final ArrayMap<String, UnaryOperator<String>> customScalarFunctions = new ArrayMap();
  
  public boolean foreignKeyConstraintsEnabled;
  
  public long idleConnectionTimeoutMs = Long.MAX_VALUE;
  
  public String journalMode;
  
  public final String label;
  
  public Locale locale;
  
  public int lookasideSlotCount = -1;
  
  public int lookasideSlotSize = -1;
  
  public int maxSqlCacheSize;
  
  public int openFlags;
  
  public final String path;
  
  public final ArrayList<Pair<String, Object[]>> perConnectionSql = new ArrayList<>();
  
  public String syncMode;
  
  public SQLiteDatabaseConfiguration(SQLiteDatabaseConfiguration paramSQLiteDatabaseConfiguration) {
    if (paramSQLiteDatabaseConfiguration != null) {
      this.path = paramSQLiteDatabaseConfiguration.path;
      this.label = paramSQLiteDatabaseConfiguration.label;
      updateParametersFrom(paramSQLiteDatabaseConfiguration);
      return;
    } 
    throw new IllegalArgumentException("other must not be null.");
  }
  
  public SQLiteDatabaseConfiguration(String paramString, int paramInt) {
    if (paramString != null) {
      this.path = paramString;
      this.label = stripPathForLogs(paramString);
      this.openFlags = paramInt;
      this.maxSqlCacheSize = 25;
      this.locale = Locale.getDefault();
      return;
    } 
    throw new IllegalArgumentException("path must not be null.");
  }
  
  private static String stripPathForLogs(String paramString) {
    return (paramString.indexOf('@') == -1) ? paramString : EMAIL_IN_DB_PATTERN.matcher(paramString).replaceAll("XX@YY");
  }
  
  public boolean isInMemoryDb() {
    return this.path.equalsIgnoreCase(":memory:");
  }
  
  boolean isLegacyCompatibilityWalEnabled() {
    boolean bool;
    if (this.journalMode == null && this.syncMode == null && (this.openFlags & Integer.MIN_VALUE) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  boolean isLookasideConfigSet() {
    boolean bool;
    if (this.lookasideSlotCount >= 0 && this.lookasideSlotSize >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void updateParametersFrom(SQLiteDatabaseConfiguration paramSQLiteDatabaseConfiguration) {
    if (paramSQLiteDatabaseConfiguration != null) {
      if (this.path.equals(paramSQLiteDatabaseConfiguration.path)) {
        this.openFlags = paramSQLiteDatabaseConfiguration.openFlags;
        this.maxSqlCacheSize = paramSQLiteDatabaseConfiguration.maxSqlCacheSize;
        this.locale = paramSQLiteDatabaseConfiguration.locale;
        this.foreignKeyConstraintsEnabled = paramSQLiteDatabaseConfiguration.foreignKeyConstraintsEnabled;
        this.customScalarFunctions.clear();
        this.customScalarFunctions.putAll(paramSQLiteDatabaseConfiguration.customScalarFunctions);
        this.customAggregateFunctions.clear();
        this.customAggregateFunctions.putAll(paramSQLiteDatabaseConfiguration.customAggregateFunctions);
        this.perConnectionSql.clear();
        this.perConnectionSql.addAll(paramSQLiteDatabaseConfiguration.perConnectionSql);
        this.lookasideSlotSize = paramSQLiteDatabaseConfiguration.lookasideSlotSize;
        this.lookasideSlotCount = paramSQLiteDatabaseConfiguration.lookasideSlotCount;
        this.idleConnectionTimeoutMs = paramSQLiteDatabaseConfiguration.idleConnectionTimeoutMs;
        this.journalMode = paramSQLiteDatabaseConfiguration.journalMode;
        this.syncMode = paramSQLiteDatabaseConfiguration.syncMode;
        return;
      } 
      throw new IllegalArgumentException("other configuration must refer to the same database.");
    } 
    throw new IllegalArgumentException("other must not be null.");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteDatabaseConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */