package android.database.sqlite;

public class SQLiteDatabaseCorruptException extends SQLiteException {
  public SQLiteDatabaseCorruptException() {}
  
  public SQLiteDatabaseCorruptException(String paramString) {
    super(paramString);
  }
  
  public static boolean isCorruptException(Throwable paramThrowable) {
    while (paramThrowable != null) {
      if (paramThrowable instanceof SQLiteDatabaseCorruptException)
        return true; 
      paramThrowable = paramThrowable.getCause();
    } 
    return false;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteDatabaseCorruptException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */