package android.database.sqlite;

final class ConnectionWaiter {
  public SQLiteConnection mAssignedConnection;
  
  public int mConnectionFlags;
  
  public RuntimeException mException;
  
  public ConnectionWaiter mNext;
  
  public int mNonce;
  
  public int mPriority;
  
  public String mSql;
  
  public long mStartTime;
  
  public Thread mThread;
  
  public boolean mWantPrimaryConnection;
  
  private ConnectionWaiter() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteConnectionPool$ConnectionWaiter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */