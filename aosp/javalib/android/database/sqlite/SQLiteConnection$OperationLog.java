package android.database.sqlite;

import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import android.util.Printer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

final class OperationLog {
  private static final int COOKIE_GENERATION_SHIFT = 8;
  
  private static final int COOKIE_INDEX_MASK = 255;
  
  private static final int MAX_RECENT_OPERATIONS = 20;
  
  private int mGeneration;
  
  private int mIndex;
  
  private final SQLiteConnection.Operation[] mOperations = new SQLiteConnection.Operation[20];
  
  private final SQLiteConnectionPool mPool;
  
  private long mResultLong = Long.MIN_VALUE;
  
  private String mResultString;
  
  OperationLog(SQLiteConnectionPool paramSQLiteConnectionPool) {
    this.mPool = paramSQLiteConnectionPool;
  }
  
  private boolean endOperationDeferLogLocked(int paramInt) {
    SQLiteConnection.Operation operation = getOperationLocked(paramInt);
    boolean bool = false;
    if (operation != null) {
      if (Trace.isTagEnabled(1048576L))
        Trace.asyncTraceEnd(1048576L, SQLiteConnection.Operation.access$500(operation), operation.mCookie); 
      operation.mEndTime = SystemClock.uptimeMillis();
      operation.mFinished = true;
      long l = operation.mEndTime - operation.mStartTime;
      this.mPool.onStatementExecuted(l);
      boolean bool1 = bool;
      if (SQLiteDebug.NoPreloadHolder.DEBUG_LOG_SLOW_QUERIES) {
        bool1 = bool;
        if (SQLiteDebug.shouldLogSlowQuery(l))
          bool1 = true; 
      } 
      return bool1;
    } 
    return false;
  }
  
  private SQLiteConnection.Operation getOperationLocked(int paramInt) {
    SQLiteConnection.Operation operation = this.mOperations[paramInt & 0xFF];
    if (operation.mCookie != paramInt)
      operation = null; 
    return operation;
  }
  
  private void logOperationLocked(int paramInt, String paramString) {
    SQLiteConnection.Operation operation = getOperationLocked(paramInt);
    operation.mResultLong = this.mResultLong;
    operation.mResultString = this.mResultString;
    StringBuilder stringBuilder = new StringBuilder();
    operation.describe(stringBuilder, true);
    if (paramString != null) {
      stringBuilder.append(", ");
      stringBuilder.append(paramString);
    } 
    Log.d("SQLiteConnection", stringBuilder.toString());
  }
  
  private int newOperationCookieLocked(int paramInt) {
    int i = this.mGeneration;
    this.mGeneration = i + 1;
    return i << 8 | paramInt;
  }
  
  public int beginOperation(String paramString1, String paramString2, Object[] paramArrayOfObject) {
    this.mResultLong = Long.MIN_VALUE;
    this.mResultString = null;
    synchronized (this.mOperations) {
      SQLiteConnection.Operation operation2;
      int i = (this.mIndex + 1) % 20;
      SQLiteConnection.Operation operation1 = this.mOperations[i];
      if (operation1 == null) {
        operation2 = new SQLiteConnection.Operation();
        this();
        this.mOperations[i] = operation2;
      } else {
        operation1.mFinished = false;
        operation1.mException = null;
        operation2 = operation1;
        if (operation1.mBindArgs != null) {
          operation1.mBindArgs.clear();
          operation2 = operation1;
        } 
      } 
      operation2.mStartWallTime = System.currentTimeMillis();
      operation2.mStartTime = SystemClock.uptimeMillis();
      operation2.mKind = paramString1;
      operation2.mSql = paramString2;
      operation2.mPath = this.mPool.getPath();
      operation2.mResultLong = Long.MIN_VALUE;
      operation2.mResultString = null;
      if (paramArrayOfObject != null) {
        if (operation2.mBindArgs == null) {
          ArrayList<Object> arrayList = new ArrayList();
          this();
          operation2.mBindArgs = arrayList;
        } else {
          operation2.mBindArgs.clear();
        } 
        for (byte b = 0; b < paramArrayOfObject.length; b++) {
          Object object = paramArrayOfObject[b];
          if (object != null && object instanceof byte[]) {
            operation2.mBindArgs.add(SQLiteConnection.access$400());
          } else {
            operation2.mBindArgs.add(object);
          } 
        } 
      } 
      operation2.mCookie = newOperationCookieLocked(i);
      if (Trace.isTagEnabled(1048576L))
        Trace.asyncTraceBegin(1048576L, SQLiteConnection.Operation.access$500(operation2), operation2.mCookie); 
      this.mIndex = i;
      return operation2.mCookie;
    } 
  }
  
  public String describeCurrentOperation() {
    synchronized (this.mOperations) {
      SQLiteConnection.Operation operation = this.mOperations[this.mIndex];
      if (operation != null && !operation.mFinished) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        operation.describe(stringBuilder, false);
        return stringBuilder.toString();
      } 
      return null;
    } 
  }
  
  public void dump(Printer paramPrinter) {
    synchronized (this.mOperations) {
      paramPrinter.println("  Most recently executed operations:");
      int i = this.mIndex;
      SQLiteConnection.Operation operation = this.mOperations[i];
      if (operation != null) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        this("yyyy-MM-dd HH:mm:ss.SSS");
        int j = 0;
        while (true) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("    ");
          stringBuilder.append(j);
          stringBuilder.append(": [");
          Date date = new Date();
          this(operation.mStartWallTime);
          stringBuilder.append(simpleDateFormat.format(date));
          stringBuilder.append("] ");
          operation.describe(stringBuilder, false);
          paramPrinter.println(stringBuilder.toString());
          if (i > 0) {
            i--;
          } else {
            i = 19;
          } 
          int k = j + 1;
          operation = this.mOperations[i];
          if (operation != null) {
            j = k;
            if (k >= 20)
              break; 
            continue;
          } 
          break;
        } 
      } else {
        paramPrinter.println("    <none>");
      } 
      return;
    } 
  }
  
  public void endOperation(int paramInt) {
    synchronized (this.mOperations) {
      if (endOperationDeferLogLocked(paramInt))
        logOperationLocked(paramInt, null); 
      return;
    } 
  }
  
  public boolean endOperationDeferLog(int paramInt) {
    synchronized (this.mOperations) {
      return endOperationDeferLogLocked(paramInt);
    } 
  }
  
  public void failOperation(int paramInt, Exception paramException) {
    synchronized (this.mOperations) {
      SQLiteConnection.Operation operation = getOperationLocked(paramInt);
      if (operation != null)
        operation.mException = paramException; 
      return;
    } 
  }
  
  public void logOperation(int paramInt, String paramString) {
    synchronized (this.mOperations) {
      logOperationLocked(paramInt, paramString);
      return;
    } 
  }
  
  public void setResult(long paramLong) {
    this.mResultLong = paramLong;
  }
  
  public void setResult(String paramString) {
    this.mResultString = paramString;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteConnection$OperationLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */