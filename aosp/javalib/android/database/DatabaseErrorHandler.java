package android.database;

import android.database.sqlite.SQLiteDatabase;

public interface DatabaseErrorHandler {
  void onCorruption(SQLiteDatabase paramSQLiteDatabase);
}


/* Location:              /home/chun/Desktop/temp/!/android/database/DatabaseErrorHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */