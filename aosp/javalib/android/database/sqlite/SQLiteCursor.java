package android.database.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CursorWindow;
import android.database.DatabaseUtils;
import android.os.StrictMode;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.HashMap;
import java.util.Map;

public class SQLiteCursor extends AbstractWindowedCursor {
  static final int NO_COUNT = -1;
  
  static final String TAG = "SQLiteCursor";
  
  private Map<String, Integer> mColumnNameMap;
  
  private final String[] mColumns;
  
  private int mCount = -1;
  
  private int mCursorWindowCapacity;
  
  private final SQLiteCursorDriver mDriver;
  
  private final String mEditTable;
  
  private boolean mFillWindowForwardOnly;
  
  private final SQLiteQuery mQuery;
  
  private final Throwable mStackTrace;
  
  public SQLiteCursor(SQLiteCursorDriver paramSQLiteCursorDriver, String paramString, SQLiteQuery paramSQLiteQuery) {
    if (paramSQLiteQuery != null) {
      if (StrictMode.vmSqliteObjectLeaksEnabled()) {
        this.mStackTrace = (new DatabaseObjectNotClosedException()).fillInStackTrace();
      } else {
        this.mStackTrace = null;
      } 
      this.mDriver = paramSQLiteCursorDriver;
      this.mEditTable = paramString;
      this.mColumnNameMap = null;
      this.mQuery = paramSQLiteQuery;
      this.mColumns = paramSQLiteQuery.getColumnNames();
      return;
    } 
    throw new IllegalArgumentException("query object cannot be null");
  }
  
  @Deprecated
  public SQLiteCursor(SQLiteDatabase paramSQLiteDatabase, SQLiteCursorDriver paramSQLiteCursorDriver, String paramString, SQLiteQuery paramSQLiteQuery) {
    this(paramSQLiteCursorDriver, paramString, paramSQLiteQuery);
  }
  
  private void fillWindow(int paramInt) {
    clearOrCreateWindow(getDatabase().getPath());
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("requiredPos cannot be negative, but was ");
      stringBuilder.append(paramInt);
      Preconditions.checkArgumentNonnegative(paramInt, stringBuilder.toString());
      if (this.mCount == -1) {
        this.mCount = this.mQuery.fillWindow(this.mWindow, paramInt, paramInt, true);
        this.mCursorWindowCapacity = this.mWindow.getNumRows();
        if (Log.isLoggable("SQLiteCursor", 3)) {
          stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("received count(*) from native_fill_window: ");
          stringBuilder.append(this.mCount);
          Log.d("SQLiteCursor", stringBuilder.toString());
        } 
      } else {
        int i;
        if (this.mFillWindowForwardOnly) {
          i = paramInt;
        } else {
          i = DatabaseUtils.cursorPickFillWindowStartPosition(paramInt, this.mCursorWindowCapacity);
        } 
        this.mQuery.fillWindow(this.mWindow, i, paramInt, false);
      } 
      return;
    } catch (RuntimeException runtimeException) {
      closeWindow();
      throw runtimeException;
    } 
  }
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial close : ()V
    //   4: aload_0
    //   5: monitorenter
    //   6: aload_0
    //   7: getfield mQuery : Landroid/database/sqlite/SQLiteQuery;
    //   10: invokevirtual close : ()V
    //   13: aload_0
    //   14: getfield mDriver : Landroid/database/sqlite/SQLiteCursorDriver;
    //   17: invokeinterface cursorClosed : ()V
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   6	24	25	finally
    //   26	28	25	finally
  }
  
  public void deactivate() {
    super.deactivate();
    this.mDriver.cursorDeactivated();
  }
  
  protected void finalize() {
    try {
      if (this.mWindow != null) {
        if (this.mStackTrace != null) {
          String str = this.mQuery.getSql();
          int i = str.length();
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Finalizing a Cursor that has not been deactivated or closed. database = ");
          stringBuilder.append(this.mQuery.getDatabase().getLabel());
          stringBuilder.append(", table = ");
          stringBuilder.append(this.mEditTable);
          stringBuilder.append(", query = ");
          char c = 'Ϩ';
          if (i > 1000)
            i = c; 
          stringBuilder.append(str.substring(0, i));
          StrictMode.onSqliteObjectLeaked(stringBuilder.toString(), this.mStackTrace);
        } 
        close();
      } 
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public int getColumnIndex(String paramString) {
    if (this.mColumnNameMap == null) {
      String[] arrayOfString = this.mColumns;
      int j = arrayOfString.length;
      HashMap<Object, Object> hashMap = new HashMap<>(j, 1.0F);
      for (byte b = 0; b < j; b++)
        hashMap.put(arrayOfString[b], Integer.valueOf(b)); 
      this.mColumnNameMap = (Map)hashMap;
    } 
    int i = paramString.lastIndexOf('.');
    String str = paramString;
    if (i != -1) {
      Exception exception = new Exception();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("requesting column name with table name -- ");
      stringBuilder.append(paramString);
      Log.e("SQLiteCursor", stringBuilder.toString(), exception);
      str = paramString.substring(i + 1);
    } 
    Integer integer = this.mColumnNameMap.get(str);
    return (integer != null) ? integer.intValue() : -1;
  }
  
  public String[] getColumnNames() {
    return this.mColumns;
  }
  
  public int getCount() {
    if (this.mCount == -1)
      fillWindow(0); 
    return this.mCount;
  }
  
  public SQLiteDatabase getDatabase() {
    return this.mQuery.getDatabase();
  }
  
  public boolean onMove(int paramInt1, int paramInt2) {
    if (this.mWindow == null || paramInt2 < this.mWindow.getStartPosition() || paramInt2 >= this.mWindow.getStartPosition() + this.mWindow.getNumRows())
      fillWindow(paramInt2); 
    return true;
  }
  
  public boolean requery() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual isClosed : ()Z
    //   4: ifeq -> 9
    //   7: iconst_0
    //   8: ireturn
    //   9: aload_0
    //   10: monitorenter
    //   11: aload_0
    //   12: getfield mQuery : Landroid/database/sqlite/SQLiteQuery;
    //   15: invokevirtual getDatabase : ()Landroid/database/sqlite/SQLiteDatabase;
    //   18: invokevirtual isOpen : ()Z
    //   21: ifne -> 28
    //   24: aload_0
    //   25: monitorexit
    //   26: iconst_0
    //   27: ireturn
    //   28: aload_0
    //   29: getfield mWindow : Landroid/database/CursorWindow;
    //   32: ifnull -> 42
    //   35: aload_0
    //   36: getfield mWindow : Landroid/database/CursorWindow;
    //   39: invokevirtual clear : ()V
    //   42: aload_0
    //   43: iconst_m1
    //   44: putfield mPos : I
    //   47: aload_0
    //   48: iconst_m1
    //   49: putfield mCount : I
    //   52: aload_0
    //   53: getfield mDriver : Landroid/database/sqlite/SQLiteCursorDriver;
    //   56: aload_0
    //   57: invokeinterface cursorRequeried : (Landroid/database/Cursor;)V
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_0
    //   65: invokespecial requery : ()Z
    //   68: istore_1
    //   69: iload_1
    //   70: ireturn
    //   71: astore_2
    //   72: new java/lang/StringBuilder
    //   75: dup
    //   76: invokespecial <init> : ()V
    //   79: astore_3
    //   80: aload_3
    //   81: ldc_w 'requery() failed '
    //   84: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload_3
    //   89: aload_2
    //   90: invokevirtual getMessage : ()Ljava/lang/String;
    //   93: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: ldc 'SQLiteCursor'
    //   99: aload_3
    //   100: invokevirtual toString : ()Ljava/lang/String;
    //   103: aload_2
    //   104: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   107: pop
    //   108: iconst_0
    //   109: ireturn
    //   110: astore_3
    //   111: aload_0
    //   112: monitorexit
    //   113: aload_3
    //   114: athrow
    // Exception table:
    //   from	to	target	type
    //   11	26	110	finally
    //   28	42	110	finally
    //   42	64	110	finally
    //   64	69	71	java/lang/IllegalStateException
    //   111	113	110	finally
  }
  
  public void setFillWindowForwardOnly(boolean paramBoolean) {
    this.mFillWindowForwardOnly = paramBoolean;
  }
  
  public void setSelectionArguments(String[] paramArrayOfString) {
    this.mDriver.setBindArguments(paramArrayOfString);
  }
  
  public void setWindow(CursorWindow paramCursorWindow) {
    super.setWindow(paramCursorWindow);
    this.mCount = -1;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */