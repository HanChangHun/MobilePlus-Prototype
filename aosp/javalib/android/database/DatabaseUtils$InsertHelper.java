package android.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class InsertHelper {
  public static final int TABLE_INFO_PRAGMA_COLUMNNAME_INDEX = 1;
  
  public static final int TABLE_INFO_PRAGMA_DEFAULT_INDEX = 4;
  
  private HashMap<String, Integer> mColumns;
  
  private final SQLiteDatabase mDb;
  
  private String mInsertSQL = null;
  
  private SQLiteStatement mInsertStatement = null;
  
  private SQLiteStatement mPreparedStatement = null;
  
  private SQLiteStatement mReplaceStatement = null;
  
  private final String mTableName;
  
  public InsertHelper(SQLiteDatabase paramSQLiteDatabase, String paramString) {
    this.mDb = paramSQLiteDatabase;
    this.mTableName = paramString;
  }
  
  private void buildSQL() throws SQLException {
    StringBuilder stringBuilder1 = new StringBuilder(128);
    stringBuilder1.append("INSERT INTO ");
    stringBuilder1.append(this.mTableName);
    stringBuilder1.append(" (");
    StringBuilder stringBuilder2 = new StringBuilder(128);
    stringBuilder2.append("VALUES (");
    int i = 1;
    null = null;
    Cursor cursor = null;
    try {
      SQLiteDatabase sQLiteDatabase = this.mDb;
      cursor = null;
      StringBuilder stringBuilder = new StringBuilder();
      cursor = null;
      this();
      cursor = null;
      stringBuilder.append("PRAGMA table_info(");
      cursor = null;
      stringBuilder.append(this.mTableName);
      cursor = null;
      stringBuilder.append(")");
      cursor = null;
      Cursor cursor1 = sQLiteDatabase.rawQuery(stringBuilder.toString(), null);
      cursor = cursor1;
      HashMap<Object, Object> hashMap = new HashMap<>();
      cursor = cursor1;
      this(cursor1.getCount());
      cursor = cursor1;
      this.mColumns = (HashMap)hashMap;
      while (true) {
        cursor = cursor1;
        if (cursor1.moveToNext()) {
          cursor = cursor1;
          String str2 = cursor1.getString(1);
          cursor = cursor1;
          String str1 = cursor1.getString(4);
          cursor = cursor1;
          this.mColumns.put(str2, Integer.valueOf(i));
          cursor = cursor1;
          stringBuilder1.append("'");
          cursor = cursor1;
          stringBuilder1.append(str2);
          cursor = cursor1;
          stringBuilder1.append("'");
          if (str1 == null) {
            cursor = cursor1;
            stringBuilder2.append("?");
          } else {
            cursor = cursor1;
            stringBuilder2.append("COALESCE(?, ");
            cursor = cursor1;
            stringBuilder2.append(str1);
            cursor = cursor1;
            stringBuilder2.append(")");
          } 
          cursor = cursor1;
          int j = cursor1.getCount();
          str2 = ", ";
          if (i == j) {
            str1 = ") ";
          } else {
            str1 = ", ";
          } 
          cursor = cursor1;
          stringBuilder1.append(str1);
          str1 = str2;
          cursor = cursor1;
          if (i == cursor1.getCount())
            str1 = ");"; 
          cursor = cursor1;
          stringBuilder2.append(str1);
          i++;
          continue;
        } 
        if (cursor1 != null)
          cursor1.close(); 
        stringBuilder1.append(stringBuilder2);
        return;
      } 
    } finally {
      if (cursor != null)
        cursor.close(); 
    } 
  }
  
  private SQLiteStatement getStatement(boolean paramBoolean) throws SQLException {
    if (paramBoolean) {
      if (this.mReplaceStatement == null) {
        if (this.mInsertSQL == null)
          buildSQL(); 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT OR REPLACE");
        stringBuilder.append(this.mInsertSQL.substring(6));
        String str = stringBuilder.toString();
        this.mReplaceStatement = this.mDb.compileStatement(str);
      } 
      return this.mReplaceStatement;
    } 
    if (this.mInsertStatement == null) {
      if (this.mInsertSQL == null)
        buildSQL(); 
      this.mInsertStatement = this.mDb.compileStatement(this.mInsertSQL);
    } 
    return this.mInsertStatement;
  }
  
  private long insertInternal(ContentValues paramContentValues, boolean paramBoolean) {
    this.mDb.beginTransactionNonExclusive();
    try {
      SQLiteStatement sQLiteStatement = getStatement(paramBoolean);
      sQLiteStatement.clearBindings();
      for (Map.Entry entry : paramContentValues.valueSet())
        DatabaseUtils.bindObjectToProgram((SQLiteProgram)sQLiteStatement, getColumnIndex((String)entry.getKey()), entry.getValue()); 
      long l = sQLiteStatement.executeInsert();
      this.mDb.setTransactionSuccessful();
      this.mDb.endTransaction();
      return l;
    } catch (SQLException sQLException) {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Error inserting ");
      stringBuilder.append(paramContentValues);
      stringBuilder.append(" into table  ");
      stringBuilder.append(this.mTableName);
      Log.e("DatabaseUtils", stringBuilder.toString(), sQLException);
      this.mDb.endTransaction();
      return -1L;
    } finally {}
    this.mDb.endTransaction();
    throw paramContentValues;
  }
  
  public void bind(int paramInt, double paramDouble) {
    this.mPreparedStatement.bindDouble(paramInt, paramDouble);
  }
  
  public void bind(int paramInt, float paramFloat) {
    this.mPreparedStatement.bindDouble(paramInt, paramFloat);
  }
  
  public void bind(int paramInt1, int paramInt2) {
    this.mPreparedStatement.bindLong(paramInt1, paramInt2);
  }
  
  public void bind(int paramInt, long paramLong) {
    this.mPreparedStatement.bindLong(paramInt, paramLong);
  }
  
  public void bind(int paramInt, String paramString) {
    if (paramString == null) {
      this.mPreparedStatement.bindNull(paramInt);
    } else {
      this.mPreparedStatement.bindString(paramInt, paramString);
    } 
  }
  
  public void bind(int paramInt, boolean paramBoolean) {
    long l;
    SQLiteStatement sQLiteStatement = this.mPreparedStatement;
    if (paramBoolean) {
      l = 1L;
    } else {
      l = 0L;
    } 
    sQLiteStatement.bindLong(paramInt, l);
  }
  
  public void bind(int paramInt, byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null) {
      this.mPreparedStatement.bindNull(paramInt);
    } else {
      this.mPreparedStatement.bindBlob(paramInt, paramArrayOfbyte);
    } 
  }
  
  public void bindNull(int paramInt) {
    this.mPreparedStatement.bindNull(paramInt);
  }
  
  public void close() {
    SQLiteStatement sQLiteStatement = this.mInsertStatement;
    if (sQLiteStatement != null) {
      sQLiteStatement.close();
      this.mInsertStatement = null;
    } 
    sQLiteStatement = this.mReplaceStatement;
    if (sQLiteStatement != null) {
      sQLiteStatement.close();
      this.mReplaceStatement = null;
    } 
    this.mInsertSQL = null;
    this.mColumns = null;
  }
  
  public long execute() {
    SQLiteStatement sQLiteStatement = this.mPreparedStatement;
    if (sQLiteStatement != null) {
      try {
        long l = sQLiteStatement.executeInsert();
        this.mPreparedStatement = null;
        return l;
      } catch (SQLException sQLException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Error executing InsertHelper with table ");
        stringBuilder.append(this.mTableName);
        Log.e("DatabaseUtils", stringBuilder.toString(), sQLException);
        this.mPreparedStatement = null;
        return -1L;
      } finally {}
      this.mPreparedStatement = null;
      throw sQLiteStatement;
    } 
    throw new IllegalStateException("you must prepare this inserter before calling execute");
  }
  
  public int getColumnIndex(String paramString) {
    getStatement(false);
    Integer integer = this.mColumns.get(paramString);
    if (integer != null)
      return integer.intValue(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("column '");
    stringBuilder.append(paramString);
    stringBuilder.append("' is invalid");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public long insert(ContentValues paramContentValues) {
    return insertInternal(paramContentValues, false);
  }
  
  public void prepareForInsert() {
    SQLiteStatement sQLiteStatement = getStatement(false);
    this.mPreparedStatement = sQLiteStatement;
    sQLiteStatement.clearBindings();
  }
  
  public void prepareForReplace() {
    SQLiteStatement sQLiteStatement = getStatement(true);
    this.mPreparedStatement = sQLiteStatement;
    sQLiteStatement.clearBindings();
  }
  
  public long replace(ContentValues paramContentValues) {
    return insertInternal(paramContentValues, true);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/DatabaseUtils$InsertHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */