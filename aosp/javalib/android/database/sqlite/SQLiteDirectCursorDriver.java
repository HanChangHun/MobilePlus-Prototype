package android.database.sqlite;

import android.database.Cursor;
import android.os.CancellationSignal;

public final class SQLiteDirectCursorDriver implements SQLiteCursorDriver {
  private final CancellationSignal mCancellationSignal;
  
  private final SQLiteDatabase mDatabase;
  
  private final String mEditTable;
  
  private SQLiteQuery mQuery;
  
  private final String mSql;
  
  public SQLiteDirectCursorDriver(SQLiteDatabase paramSQLiteDatabase, String paramString1, String paramString2, CancellationSignal paramCancellationSignal) {
    this.mDatabase = paramSQLiteDatabase;
    this.mEditTable = paramString2;
    this.mSql = paramString1;
    this.mCancellationSignal = paramCancellationSignal;
  }
  
  public void cursorClosed() {}
  
  public void cursorDeactivated() {}
  
  public void cursorRequeried(Cursor paramCursor) {}
  
  public Cursor query(SQLiteDatabase.CursorFactory paramCursorFactory, String[] paramArrayOfString) {
    SQLiteQuery sQLiteQuery = new SQLiteQuery(this.mDatabase, this.mSql, this.mCancellationSignal);
    try {
      SQLiteCursor sQLiteCursor;
      Cursor cursor;
      sQLiteQuery.bindAllArgsAsStrings(paramArrayOfString);
      if (paramCursorFactory == null) {
        sQLiteCursor = new SQLiteCursor(this, this.mEditTable, sQLiteQuery);
      } else {
        cursor = sQLiteCursor.newCursor(this.mDatabase, this, this.mEditTable, sQLiteQuery);
      } 
      this.mQuery = sQLiteQuery;
      return cursor;
    } catch (RuntimeException runtimeException) {
      sQLiteQuery.close();
      throw runtimeException;
    } 
  }
  
  public void setBindArguments(String[] paramArrayOfString) {
    this.mQuery.bindAllArgsAsStrings(paramArrayOfString);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SQLiteDirectCursorDriver: ");
    stringBuilder.append(this.mSql);
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteDirectCursorDriver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */