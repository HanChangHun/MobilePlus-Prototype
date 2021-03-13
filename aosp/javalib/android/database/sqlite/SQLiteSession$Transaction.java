package android.database.sqlite;

final class Transaction {
  public boolean mChildFailed;
  
  public SQLiteTransactionListener mListener;
  
  public boolean mMarkedSuccessful;
  
  public int mMode;
  
  public Transaction mParent;
  
  private Transaction() {}
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteSession$Transaction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */