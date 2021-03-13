package android.database.sqlite;

import android.database.CursorWindow;
import android.os.CancellationSignal;
import android.util.Log;

public final class SQLiteQuery extends SQLiteProgram {
  private static final String TAG = "SQLiteQuery";
  
  private final CancellationSignal mCancellationSignal;
  
  SQLiteQuery(SQLiteDatabase paramSQLiteDatabase, String paramString, CancellationSignal paramCancellationSignal) {
    super(paramSQLiteDatabase, paramString, (Object[])null, paramCancellationSignal);
    this.mCancellationSignal = paramCancellationSignal;
  }
  
  int fillWindow(CursorWindow paramCursorWindow, int paramInt1, int paramInt2, boolean paramBoolean) {
    acquireReference();
    try {
      Exception exception;
      paramCursorWindow.acquireReference();
      try {
        paramInt1 = getSession().executeForCursorWindow(getSql(), getBindArgs(), paramCursorWindow, paramInt1, paramInt2, paramBoolean, getConnectionFlags(), this.mCancellationSignal);
        paramCursorWindow.releaseReference();
        return paramInt1;
      } catch (SQLiteDatabaseCorruptException sQLiteDatabaseCorruptException) {
        onCorruption();
        throw sQLiteDatabaseCorruptException;
      } catch (SQLiteException sQLiteException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("exception: ");
        stringBuilder.append(sQLiteException.getMessage());
        stringBuilder.append("; query: ");
        stringBuilder.append(getSql());
        Log.e("SQLiteQuery", stringBuilder.toString());
        throw sQLiteException;
      } finally {}
      paramCursorWindow.releaseReference();
      throw exception;
    } finally {
      releaseReference();
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SQLiteQuery: ");
    stringBuilder.append(getSql());
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteQuery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */