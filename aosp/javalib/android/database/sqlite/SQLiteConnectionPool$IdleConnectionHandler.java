package android.database.sqlite;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

class IdleConnectionHandler extends Handler {
  private final long mTimeout;
  
  IdleConnectionHandler(Looper paramLooper, long paramLong) {
    super(paramLooper);
    this.mTimeout = paramLong;
  }
  
  void connectionAcquired(SQLiteConnection paramSQLiteConnection) {
    removeMessages(paramSQLiteConnection.getConnectionId());
  }
  
  void connectionClosed(SQLiteConnection paramSQLiteConnection) {
    removeMessages(paramSQLiteConnection.getConnectionId());
  }
  
  void connectionReleased(SQLiteConnection paramSQLiteConnection) {
    sendEmptyMessageDelayed(paramSQLiteConnection.getConnectionId(), this.mTimeout);
  }
  
  public void handleMessage(Message paramMessage) {
    synchronized (SQLiteConnectionPool.access$000(SQLiteConnectionPool.this)) {
      if (this != SQLiteConnectionPool.access$300(SQLiteConnectionPool.this))
        return; 
      if (SQLiteConnectionPool.access$400(SQLiteConnectionPool.this, paramMessage.what) && Log.isLoggable("SQLiteConnectionPool", 3)) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Closed idle connection ");
        stringBuilder.append((SQLiteConnectionPool.access$500(SQLiteConnectionPool.this)).label);
        stringBuilder.append(" ");
        stringBuilder.append(paramMessage.what);
        stringBuilder.append(" after ");
        stringBuilder.append(this.mTimeout);
        Log.d("SQLiteConnectionPool", stringBuilder.toString());
      } 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteConnectionPool$IdleConnectionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */