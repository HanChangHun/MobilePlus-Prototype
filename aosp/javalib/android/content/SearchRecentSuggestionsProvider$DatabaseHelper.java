package android.content;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseHelper extends SQLiteOpenHelper {
  private int mNewVersion;
  
  public DatabaseHelper(Context paramContext, int paramInt) {
    super(paramContext, "suggestions.db", null, paramInt);
    this.mNewVersion = paramInt;
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CREATE TABLE suggestions (_id INTEGER PRIMARY KEY,display1 TEXT UNIQUE ON CONFLICT REPLACE");
    if ((this.mNewVersion & 0x2) != 0)
      stringBuilder.append(",display2 TEXT"); 
    stringBuilder.append(",query TEXT,date LONG);");
    paramSQLiteDatabase.execSQL(stringBuilder.toString());
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Upgrading database from version ");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" to ");
    stringBuilder.append(paramInt2);
    stringBuilder.append(", which will destroy all old data");
    Log.w("SuggestionsProvider", stringBuilder.toString());
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS suggestions");
    onCreate(paramSQLiteDatabase);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SearchRecentSuggestionsProvider$DatabaseHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */