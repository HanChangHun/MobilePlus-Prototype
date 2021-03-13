package android.database.sqlite;

import android.database.Cursor;

public interface CursorFactory {
  Cursor newCursor(SQLiteDatabase paramSQLiteDatabase, SQLiteCursorDriver paramSQLiteCursorDriver, String paramString, SQLiteQuery paramSQLiteQuery);
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteDatabase$CursorFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */