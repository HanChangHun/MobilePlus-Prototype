package android.database.sqlite;

import android.database.SQLException;

public class SQLiteException extends SQLException {
  public SQLiteException() {}
  
  public SQLiteException(String paramString) {
    super(paramString);
  }
  
  public SQLiteException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */