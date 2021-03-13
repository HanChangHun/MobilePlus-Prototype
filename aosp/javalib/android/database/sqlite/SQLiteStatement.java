package android.database.sqlite;

import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;

public final class SQLiteStatement extends SQLiteProgram {
  SQLiteStatement(SQLiteDatabase paramSQLiteDatabase, String paramString, Object[] paramArrayOfObject) {
    super(paramSQLiteDatabase, paramString, paramArrayOfObject, (CancellationSignal)null);
  }
  
  public void execute() {
    Exception exception;
    acquireReference();
    try {
      getSession().execute(getSql(), getBindArgs(), getConnectionFlags(), null);
      releaseReference();
      return;
    } catch (SQLiteDatabaseCorruptException sQLiteDatabaseCorruptException) {
      onCorruption();
      throw sQLiteDatabaseCorruptException;
    } finally {}
    releaseReference();
    throw exception;
  }
  
  public long executeInsert() {
    Exception exception;
    acquireReference();
    try {
      long l = getSession().executeForLastInsertedRowId(getSql(), getBindArgs(), getConnectionFlags(), null);
      releaseReference();
      return l;
    } catch (SQLiteDatabaseCorruptException sQLiteDatabaseCorruptException) {
      onCorruption();
      throw sQLiteDatabaseCorruptException;
    } finally {}
    releaseReference();
    throw exception;
  }
  
  public int executeUpdateDelete() {
    Exception exception;
    acquireReference();
    try {
      int i = getSession().executeForChangedRowCount(getSql(), getBindArgs(), getConnectionFlags(), null);
      releaseReference();
      return i;
    } catch (SQLiteDatabaseCorruptException sQLiteDatabaseCorruptException) {
      onCorruption();
      throw sQLiteDatabaseCorruptException;
    } finally {}
    releaseReference();
    throw exception;
  }
  
  public ParcelFileDescriptor simpleQueryForBlobFileDescriptor() {
    Exception exception;
    acquireReference();
    try {
      ParcelFileDescriptor parcelFileDescriptor = getSession().executeForBlobFileDescriptor(getSql(), getBindArgs(), getConnectionFlags(), null);
      releaseReference();
      return parcelFileDescriptor;
    } catch (SQLiteDatabaseCorruptException sQLiteDatabaseCorruptException) {
      onCorruption();
      throw sQLiteDatabaseCorruptException;
    } finally {}
    releaseReference();
    throw exception;
  }
  
  public long simpleQueryForLong() {
    Exception exception;
    acquireReference();
    try {
      long l = getSession().executeForLong(getSql(), getBindArgs(), getConnectionFlags(), null);
      releaseReference();
      return l;
    } catch (SQLiteDatabaseCorruptException sQLiteDatabaseCorruptException) {
      onCorruption();
      throw sQLiteDatabaseCorruptException;
    } finally {}
    releaseReference();
    throw exception;
  }
  
  public String simpleQueryForString() {
    Exception exception;
    acquireReference();
    try {
      String str = getSession().executeForString(getSql(), getBindArgs(), getConnectionFlags(), null);
      releaseReference();
      return str;
    } catch (SQLiteDatabaseCorruptException sQLiteDatabaseCorruptException) {
      onCorruption();
      throw sQLiteDatabaseCorruptException;
    } finally {}
    releaseReference();
    throw exception;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SQLiteProgram: ");
    stringBuilder.append(getSql());
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteStatement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */