package android.database.sqlite;

import android.database.DatabaseUtils;
import android.os.CancellationSignal;
import java.util.Arrays;

public abstract class SQLiteProgram extends SQLiteClosable {
  private static final String[] EMPTY_STRING_ARRAY = new String[0];
  
  private final Object[] mBindArgs;
  
  private final String[] mColumnNames;
  
  private final SQLiteDatabase mDatabase;
  
  private final int mNumParameters;
  
  private final boolean mReadOnly;
  
  private final String mSql;
  
  SQLiteProgram(SQLiteDatabase paramSQLiteDatabase, String paramString, Object[] paramArrayOfObject, CancellationSignal paramCancellationSignal) {
    this.mDatabase = paramSQLiteDatabase;
    paramString = paramString.trim();
    this.mSql = paramString;
    int i = DatabaseUtils.getSqlStatementType(paramString);
    if (i != 4 && i != 5 && i != 6) {
      boolean bool = true;
      if (i != 1)
        bool = false; 
      SQLiteStatementInfo sQLiteStatementInfo = new SQLiteStatementInfo();
      paramSQLiteDatabase.getThreadSession().prepare(this.mSql, paramSQLiteDatabase.getThreadDefaultConnectionFlags(bool), paramCancellationSignal, sQLiteStatementInfo);
      this.mReadOnly = sQLiteStatementInfo.readOnly;
      this.mColumnNames = sQLiteStatementInfo.columnNames;
      this.mNumParameters = sQLiteStatementInfo.numParameters;
    } else {
      this.mReadOnly = false;
      this.mColumnNames = EMPTY_STRING_ARRAY;
      this.mNumParameters = 0;
    } 
    if (paramArrayOfObject == null || paramArrayOfObject.length <= this.mNumParameters) {
      i = this.mNumParameters;
      if (i != 0) {
        Object[] arrayOfObject = new Object[i];
        this.mBindArgs = arrayOfObject;
        if (paramArrayOfObject != null)
          System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramArrayOfObject.length); 
      } else {
        this.mBindArgs = null;
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Too many bind arguments.  ");
    stringBuilder.append(paramArrayOfObject.length);
    stringBuilder.append(" arguments were provided but the statement needs ");
    stringBuilder.append(this.mNumParameters);
    stringBuilder.append(" arguments.");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private void bind(int paramInt, Object paramObject) {
    if (paramInt >= 1 && paramInt <= this.mNumParameters) {
      this.mBindArgs[paramInt - 1] = paramObject;
      return;
    } 
    paramObject = new StringBuilder();
    paramObject.append("Cannot bind argument at index ");
    paramObject.append(paramInt);
    paramObject.append(" because the index is out of range.  The statement has ");
    paramObject.append(this.mNumParameters);
    paramObject.append(" parameters.");
    throw new IllegalArgumentException(paramObject.toString());
  }
  
  public void bindAllArgsAsStrings(String[] paramArrayOfString) {
    if (paramArrayOfString != null)
      for (int i = paramArrayOfString.length; i != 0; i--)
        bindString(i, paramArrayOfString[i - 1]);  
  }
  
  public void bindBlob(int paramInt, byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte != null) {
      bind(paramInt, paramArrayOfbyte);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("the bind value at index ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" is null");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void bindDouble(int paramInt, double paramDouble) {
    bind(paramInt, Double.valueOf(paramDouble));
  }
  
  public void bindLong(int paramInt, long paramLong) {
    bind(paramInt, Long.valueOf(paramLong));
  }
  
  public void bindNull(int paramInt) {
    bind(paramInt, null);
  }
  
  public void bindString(int paramInt, String paramString) {
    if (paramString != null) {
      bind(paramInt, paramString);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("the bind value at index ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" is null");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public void clearBindings() {
    Object[] arrayOfObject = this.mBindArgs;
    if (arrayOfObject != null)
      Arrays.fill(arrayOfObject, (Object)null); 
  }
  
  final Object[] getBindArgs() {
    return this.mBindArgs;
  }
  
  final String[] getColumnNames() {
    return this.mColumnNames;
  }
  
  protected final int getConnectionFlags() {
    return this.mDatabase.getThreadDefaultConnectionFlags(this.mReadOnly);
  }
  
  final SQLiteDatabase getDatabase() {
    return this.mDatabase;
  }
  
  protected final SQLiteSession getSession() {
    return this.mDatabase.getThreadSession();
  }
  
  final String getSql() {
    return this.mSql;
  }
  
  @Deprecated
  public final int getUniqueId() {
    return -1;
  }
  
  protected void onAllReferencesReleased() {
    clearBindings();
  }
  
  protected final void onCorruption() {
    this.mDatabase.onCorruption();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteProgram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */