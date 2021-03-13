package android.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public final class DefaultDatabaseErrorHandler implements DatabaseErrorHandler {
  private static final String TAG = "DefaultDatabaseErrorHandler";
  
  private void deleteDatabaseFile(String paramString) {
    if (paramString.equalsIgnoreCase(":memory:") || paramString.trim().length() == 0)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("deleting the database file: ");
    stringBuilder.append(paramString);
    Log.e("DefaultDatabaseErrorHandler", stringBuilder.toString());
    try {
      File file = new File();
      this(paramString);
      SQLiteDatabase.deleteDatabase(file, false);
    } catch (Exception exception) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("delete failed: ");
      stringBuilder.append(exception.getMessage());
      Log.w("DefaultDatabaseErrorHandler", stringBuilder.toString());
    } 
  }
  
  public void onCorruption(SQLiteDatabase paramSQLiteDatabase) {
    Iterator iterator;
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Corruption reported by sqlite on database: ");
    stringBuilder1.append(paramSQLiteDatabase.getPath());
    Log.e("DefaultDatabaseErrorHandler", stringBuilder1.toString());
    SQLiteDatabase.wipeDetected(paramSQLiteDatabase.getPath(), "corruption");
    if (!paramSQLiteDatabase.isOpen()) {
      deleteDatabaseFile(paramSQLiteDatabase.getPath());
      return;
    } 
    stringBuilder1 = null;
    StringBuilder stringBuilder2 = null;
    try {
      List list2 = paramSQLiteDatabase.getAttachedDbs();
      List list1 = list2;
    } catch (SQLiteException sQLiteException) {
    
    } finally {}
    stringBuilder2 = stringBuilder1;
    try {
      paramSQLiteDatabase.close();
    } catch (SQLiteException sQLiteException) {}
    if (stringBuilder1 != null) {
      iterator = stringBuilder1.iterator();
      while (iterator.hasNext())
        deleteDatabaseFile((String)((Pair)iterator.next()).second); 
    } else {
      deleteDatabaseFile(iterator.getPath());
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/DefaultDatabaseErrorHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */