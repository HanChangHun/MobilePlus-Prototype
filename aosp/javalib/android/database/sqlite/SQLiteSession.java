package android.database.sqlite;

import android.database.CursorWindow;
import android.database.DatabaseUtils;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;

public final class SQLiteSession {
  public static final int TRANSACTION_MODE_DEFERRED = 0;
  
  public static final int TRANSACTION_MODE_EXCLUSIVE = 2;
  
  public static final int TRANSACTION_MODE_IMMEDIATE = 1;
  
  private SQLiteConnection mConnection;
  
  private int mConnectionFlags;
  
  private final SQLiteConnectionPool mConnectionPool;
  
  private int mConnectionUseCount;
  
  private Transaction mTransactionPool;
  
  private Transaction mTransactionStack;
  
  public SQLiteSession(SQLiteConnectionPool paramSQLiteConnectionPool) {
    if (paramSQLiteConnectionPool != null) {
      this.mConnectionPool = paramSQLiteConnectionPool;
      return;
    } 
    throw new IllegalArgumentException("connectionPool must not be null");
  }
  
  private void acquireConnection(String paramString, int paramInt, CancellationSignal paramCancellationSignal) {
    if (this.mConnection == null) {
      this.mConnection = this.mConnectionPool.acquireConnection(paramString, paramInt, paramCancellationSignal);
      this.mConnectionFlags = paramInt;
    } 
    this.mConnectionUseCount++;
  }
  
  private void beginTransactionUnchecked(int paramInt1, SQLiteTransactionListener paramSQLiteTransactionListener, int paramInt2, CancellationSignal paramCancellationSignal) {
    if (paramCancellationSignal != null)
      paramCancellationSignal.throwIfCanceled(); 
    if (this.mTransactionStack == null)
      acquireConnection(null, paramInt2, paramCancellationSignal); 
    try {
      if (this.mTransactionStack == null)
        if (paramInt1 != 1) {
          if (paramInt1 != 2) {
            this.mConnection.execute("BEGIN;", null, paramCancellationSignal);
          } else {
            this.mConnection.execute("BEGIN EXCLUSIVE;", null, paramCancellationSignal);
          } 
        } else {
          this.mConnection.execute("BEGIN IMMEDIATE;", null, paramCancellationSignal);
        }  
      if (paramSQLiteTransactionListener != null)
        try {
          paramSQLiteTransactionListener.onBegin();
        } catch (RuntimeException runtimeException) {
          if (this.mTransactionStack == null)
            this.mConnection.execute("ROLLBACK;", null, paramCancellationSignal); 
          throw runtimeException;
        }  
      Transaction transaction = obtainTransaction(paramInt1, (SQLiteTransactionListener)runtimeException);
      transaction.mParent = this.mTransactionStack;
      this.mTransactionStack = transaction;
      return;
    } finally {
      if (this.mTransactionStack == null)
        releaseConnection(); 
    } 
  }
  
  private void endTransactionUnchecked(CancellationSignal paramCancellationSignal, boolean paramBoolean) {
    boolean bool1;
    if (paramCancellationSignal != null)
      paramCancellationSignal.throwIfCanceled(); 
    Transaction transaction1 = this.mTransactionStack;
    if ((transaction1.mMarkedSuccessful || paramBoolean) && !transaction1.mChildFailed) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    Transaction transaction2 = null;
    SQLiteTransactionListener sQLiteTransactionListener = transaction1.mListener;
    boolean bool2 = bool1;
    Transaction transaction3 = transaction2;
    if (sQLiteTransactionListener != null)
      if (bool1) {
        try {
          sQLiteTransactionListener.onCommit();
          bool2 = bool1;
          transaction3 = transaction2;
        } catch (RuntimeException runtimeException) {
          bool2 = false;
        } 
      } else {
        sQLiteTransactionListener.onRollback();
        bool2 = bool1;
        transaction3 = transaction2;
      }  
    this.mTransactionStack = transaction1.mParent;
    recycleTransaction(transaction1);
    transaction2 = this.mTransactionStack;
    if (transaction2 != null) {
      if (!bool2)
        transaction2.mChildFailed = true; 
    } else {
      if (bool2)
        try {
          this.mConnection.execute("COMMIT;", null, paramCancellationSignal);
          releaseConnection();
        } finally {
          releaseConnection();
        }  
      this.mConnection.execute("ROLLBACK;", null, paramCancellationSignal);
      releaseConnection();
    } 
    if (transaction3 == null)
      return; 
    throw transaction3;
  }
  
  private boolean executeSpecial(String paramString, Object[] paramArrayOfObject, int paramInt, CancellationSignal paramCancellationSignal) {
    if (paramCancellationSignal != null)
      paramCancellationSignal.throwIfCanceled(); 
    int i = DatabaseUtils.getSqlStatementType(paramString);
    if (i != 4) {
      if (i != 5) {
        if (i != 6)
          return false; 
        endTransaction(paramCancellationSignal);
        return true;
      } 
      setTransactionSuccessful();
      endTransaction(paramCancellationSignal);
      return true;
    } 
    beginTransaction(2, null, paramInt, paramCancellationSignal);
    return true;
  }
  
  private Transaction obtainTransaction(int paramInt, SQLiteTransactionListener paramSQLiteTransactionListener) {
    Transaction transaction = this.mTransactionPool;
    if (transaction != null) {
      this.mTransactionPool = transaction.mParent;
      transaction.mParent = null;
      transaction.mMarkedSuccessful = false;
      transaction.mChildFailed = false;
    } else {
      transaction = new Transaction();
    } 
    transaction.mMode = paramInt;
    transaction.mListener = paramSQLiteTransactionListener;
    return transaction;
  }
  
  private void recycleTransaction(Transaction paramTransaction) {
    paramTransaction.mParent = this.mTransactionPool;
    paramTransaction.mListener = null;
    this.mTransactionPool = paramTransaction;
  }
  
  private void releaseConnection() {
    int i = this.mConnectionUseCount - 1;
    this.mConnectionUseCount = i;
    if (i == 0)
      try {
        this.mConnectionPool.releaseConnection(this.mConnection);
      } finally {
        this.mConnection = null;
      }  
  }
  
  private void throwIfNestedTransaction() {
    if (!hasNestedTransaction())
      return; 
    throw new IllegalStateException("Cannot perform this operation because a nested transaction is in progress.");
  }
  
  private void throwIfNoTransaction() {
    if (this.mTransactionStack != null)
      return; 
    throw new IllegalStateException("Cannot perform this operation because there is no current transaction.");
  }
  
  private void throwIfTransactionMarkedSuccessful() {
    Transaction transaction = this.mTransactionStack;
    if (transaction == null || !transaction.mMarkedSuccessful)
      return; 
    throw new IllegalStateException("Cannot perform this operation because the transaction has already been marked successful.  The only thing you can do now is call endTransaction().");
  }
  
  private boolean yieldTransactionUnchecked(long paramLong, CancellationSignal paramCancellationSignal) {
    if (paramCancellationSignal != null)
      paramCancellationSignal.throwIfCanceled(); 
    if (!this.mConnectionPool.shouldYieldConnection(this.mConnection, this.mConnectionFlags))
      return false; 
    int i = this.mTransactionStack.mMode;
    SQLiteTransactionListener sQLiteTransactionListener = this.mTransactionStack.mListener;
    int j = this.mConnectionFlags;
    endTransactionUnchecked(paramCancellationSignal, true);
    if (paramLong > 0L)
      try {
        Thread.sleep(paramLong);
      } catch (InterruptedException interruptedException) {} 
    beginTransactionUnchecked(i, sQLiteTransactionListener, j, paramCancellationSignal);
    return true;
  }
  
  public void beginTransaction(int paramInt1, SQLiteTransactionListener paramSQLiteTransactionListener, int paramInt2, CancellationSignal paramCancellationSignal) {
    throwIfTransactionMarkedSuccessful();
    beginTransactionUnchecked(paramInt1, paramSQLiteTransactionListener, paramInt2, paramCancellationSignal);
  }
  
  public void endTransaction(CancellationSignal paramCancellationSignal) {
    throwIfNoTransaction();
    endTransactionUnchecked(paramCancellationSignal, false);
  }
  
  public void execute(String paramString, Object[] paramArrayOfObject, int paramInt, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      if (executeSpecial(paramString, paramArrayOfObject, paramInt, paramCancellationSignal))
        return; 
      acquireConnection(paramString, paramInt, paramCancellationSignal);
      try {
        this.mConnection.execute(paramString, paramArrayOfObject, paramCancellationSignal);
        return;
      } finally {
        releaseConnection();
      } 
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public ParcelFileDescriptor executeForBlobFileDescriptor(String paramString, Object[] paramArrayOfObject, int paramInt, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      if (executeSpecial(paramString, paramArrayOfObject, paramInt, paramCancellationSignal))
        return null; 
      acquireConnection(paramString, paramInt, paramCancellationSignal);
      try {
        return this.mConnection.executeForBlobFileDescriptor(paramString, paramArrayOfObject, paramCancellationSignal);
      } finally {
        releaseConnection();
      } 
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public int executeForChangedRowCount(String paramString, Object[] paramArrayOfObject, int paramInt, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      if (executeSpecial(paramString, paramArrayOfObject, paramInt, paramCancellationSignal))
        return 0; 
      acquireConnection(paramString, paramInt, paramCancellationSignal);
      try {
        paramInt = this.mConnection.executeForChangedRowCount(paramString, paramArrayOfObject, paramCancellationSignal);
        return paramInt;
      } finally {
        releaseConnection();
      } 
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public int executeForCursorWindow(String paramString, Object[] paramArrayOfObject, CursorWindow paramCursorWindow, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      if (paramCursorWindow != null) {
        if (executeSpecial(paramString, paramArrayOfObject, paramInt3, paramCancellationSignal)) {
          paramCursorWindow.clear();
          return 0;
        } 
        acquireConnection(paramString, paramInt3, paramCancellationSignal);
        try {
          paramInt1 = this.mConnection.executeForCursorWindow(paramString, paramArrayOfObject, paramCursorWindow, paramInt1, paramInt2, paramBoolean, paramCancellationSignal);
          return paramInt1;
        } finally {
          releaseConnection();
        } 
      } 
      throw new IllegalArgumentException("window must not be null.");
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public long executeForLastInsertedRowId(String paramString, Object[] paramArrayOfObject, int paramInt, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      if (executeSpecial(paramString, paramArrayOfObject, paramInt, paramCancellationSignal))
        return 0L; 
      acquireConnection(paramString, paramInt, paramCancellationSignal);
      try {
        return this.mConnection.executeForLastInsertedRowId(paramString, paramArrayOfObject, paramCancellationSignal);
      } finally {
        releaseConnection();
      } 
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public long executeForLong(String paramString, Object[] paramArrayOfObject, int paramInt, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      if (executeSpecial(paramString, paramArrayOfObject, paramInt, paramCancellationSignal))
        return 0L; 
      acquireConnection(paramString, paramInt, paramCancellationSignal);
      try {
        return this.mConnection.executeForLong(paramString, paramArrayOfObject, paramCancellationSignal);
      } finally {
        releaseConnection();
      } 
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public String executeForString(String paramString, Object[] paramArrayOfObject, int paramInt, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      if (executeSpecial(paramString, paramArrayOfObject, paramInt, paramCancellationSignal))
        return null; 
      acquireConnection(paramString, paramInt, paramCancellationSignal);
      try {
        paramString = this.mConnection.executeForString(paramString, paramArrayOfObject, paramCancellationSignal);
        return paramString;
      } finally {
        releaseConnection();
      } 
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public boolean hasConnection() {
    boolean bool;
    if (this.mConnection != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasNestedTransaction() {
    boolean bool;
    Transaction transaction = this.mTransactionStack;
    if (transaction != null && transaction.mParent != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasTransaction() {
    boolean bool;
    if (this.mTransactionStack != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void prepare(String paramString, int paramInt, CancellationSignal paramCancellationSignal, SQLiteStatementInfo paramSQLiteStatementInfo) {
    if (paramString != null) {
      if (paramCancellationSignal != null)
        paramCancellationSignal.throwIfCanceled(); 
      acquireConnection(paramString, paramInt, paramCancellationSignal);
      try {
        this.mConnection.prepare(paramString, paramSQLiteStatementInfo);
        return;
      } finally {
        releaseConnection();
      } 
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public void setTransactionSuccessful() {
    throwIfNoTransaction();
    throwIfTransactionMarkedSuccessful();
    this.mTransactionStack.mMarkedSuccessful = true;
  }
  
  public boolean yieldTransaction(long paramLong, boolean paramBoolean, CancellationSignal paramCancellationSignal) {
    if (paramBoolean) {
      throwIfNoTransaction();
      throwIfTransactionMarkedSuccessful();
      throwIfNestedTransaction();
    } else {
      Transaction transaction = this.mTransactionStack;
      if (transaction == null || transaction.mMarkedSuccessful || this.mTransactionStack.mParent != null)
        return false; 
    } 
    return this.mTransactionStack.mChildFailed ? false : yieldTransactionUnchecked(paramLong, paramCancellationSignal);
  }
  
  private static final class Transaction {
    public boolean mChildFailed;
    
    public SQLiteTransactionListener mListener;
    
    public boolean mMarkedSuccessful;
    
    public int mMode;
    
    public Transaction mParent;
    
    private Transaction() {}
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */