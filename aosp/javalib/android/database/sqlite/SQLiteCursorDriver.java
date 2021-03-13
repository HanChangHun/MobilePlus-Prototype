package android.database.sqlite;

import android.database.Cursor;

public interface SQLiteCursorDriver {
  void cursorClosed();
  
  void cursorDeactivated();
  
  void cursorRequeried(Cursor paramCursor);
  
  Cursor query(SQLiteDatabase.CursorFactory paramCursorFactory, String[] paramArrayOfString);
  
  void setBindArguments(String[] paramArrayOfString);
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteCursorDriver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */