package android.database.sqlite;

final class PreparedStatement {
  public boolean mInCache;
  
  public boolean mInUse;
  
  public int mNumParameters;
  
  public PreparedStatement mPoolNext;
  
  public boolean mReadOnly;
  
  public String mSql;
  
  public long mStatementPtr;
  
  public int mType;
  
  private PreparedStatement() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteConnection$PreparedStatement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */