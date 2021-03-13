package android.database.sqlite;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public final class SqliteWrapper {
  private static final String SQLITE_EXCEPTION_DETAIL_MESSAGE = "unable to open database file";
  
  private static final String TAG = "SqliteWrapper";
  
  public static void checkSQLiteException(Context paramContext, SQLiteException paramSQLiteException) {
    if (isLowMemory(paramSQLiteException)) {
      Toast.makeText(paramContext, 17040494, 0).show();
      return;
    } 
    throw paramSQLiteException;
  }
  
  public static int delete(Context paramContext, ContentResolver paramContentResolver, Uri paramUri, String paramString, String[] paramArrayOfString) {
    try {
      return paramContentResolver.delete(paramUri, paramString, paramArrayOfString);
    } catch (SQLiteException sQLiteException) {
      Log.e("SqliteWrapper", "Catch a SQLiteException when delete: ", (Throwable)sQLiteException);
      checkSQLiteException(paramContext, sQLiteException);
      return -1;
    } 
  }
  
  public static Uri insert(Context paramContext, ContentResolver paramContentResolver, Uri paramUri, ContentValues paramContentValues) {
    try {
      return paramContentResolver.insert(paramUri, paramContentValues);
    } catch (SQLiteException sQLiteException) {
      Log.e("SqliteWrapper", "Catch a SQLiteException when insert: ", (Throwable)sQLiteException);
      checkSQLiteException(paramContext, sQLiteException);
      return null;
    } 
  }
  
  private static boolean isLowMemory(SQLiteException paramSQLiteException) {
    return paramSQLiteException.getMessage().equals("unable to open database file");
  }
  
  public static Cursor query(Context paramContext, ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2) {
    try {
      return paramContentResolver.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
    } catch (SQLiteException sQLiteException) {
      Log.e("SqliteWrapper", "Catch a SQLiteException when query: ", (Throwable)sQLiteException);
      checkSQLiteException(paramContext, sQLiteException);
      return null;
    } 
  }
  
  public static boolean requery(Context paramContext, Cursor paramCursor) {
    try {
      return paramCursor.requery();
    } catch (SQLiteException sQLiteException) {
      Log.e("SqliteWrapper", "Catch a SQLiteException when requery: ", (Throwable)sQLiteException);
      checkSQLiteException(paramContext, sQLiteException);
      return false;
    } 
  }
  
  public static int update(Context paramContext, ContentResolver paramContentResolver, Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString) {
    try {
      return paramContentResolver.update(paramUri, paramContentValues, paramString, paramArrayOfString);
    } catch (SQLiteException sQLiteException) {
      Log.e("SqliteWrapper", "Catch a SQLiteException when update: ", (Throwable)sQLiteException);
      checkSQLiteException(paramContext, sQLiteException);
      return -1;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SqliteWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */