package android.database.sqlite;

enum AcquiredConnectionStatus {
  DISCARD, NORMAL, RECONFIGURE;
  
  static {
    AcquiredConnectionStatus acquiredConnectionStatus = new AcquiredConnectionStatus("DISCARD", 2);
    DISCARD = acquiredConnectionStatus;
    $VALUES = new AcquiredConnectionStatus[] { NORMAL, RECONFIGURE, acquiredConnectionStatus };
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteConnectionPool$AcquiredConnectionStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */