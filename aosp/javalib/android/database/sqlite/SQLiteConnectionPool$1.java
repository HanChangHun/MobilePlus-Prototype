package android.database.sqlite;

import android.os.CancellationSignal;

class null implements CancellationSignal.OnCancelListener {
  public void onCancel() {
    synchronized (SQLiteConnectionPool.access$000(SQLiteConnectionPool.this)) {
      if (waiter.mNonce == nonce)
        SQLiteConnectionPool.access$100(SQLiteConnectionPool.this, waiter); 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteConnectionPool$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */