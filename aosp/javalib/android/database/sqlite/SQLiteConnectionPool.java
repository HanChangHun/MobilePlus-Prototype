package android.database.sqlite;

import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.OperationCanceledException;
import android.os.SystemClock;
import android.util.ArraySet;
import android.util.Log;
import android.util.Printer;
import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

public final class SQLiteConnectionPool implements Closeable {
  public static final int CONNECTION_FLAG_INTERACTIVE = 4;
  
  public static final int CONNECTION_FLAG_PRIMARY_CONNECTION_AFFINITY = 2;
  
  public static final int CONNECTION_FLAG_READ_ONLY = 1;
  
  private static final long CONNECTION_POOL_BUSY_MILLIS = 30000L;
  
  private static final String TAG = "SQLiteConnectionPool";
  
  private final WeakHashMap<SQLiteConnection, AcquiredConnectionStatus> mAcquiredConnections = new WeakHashMap<>();
  
  private final ArrayList<SQLiteConnection> mAvailableNonPrimaryConnections = new ArrayList<>();
  
  private SQLiteConnection mAvailablePrimaryConnection;
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private final SQLiteDatabaseConfiguration mConfiguration;
  
  private final AtomicBoolean mConnectionLeaked = new AtomicBoolean();
  
  private ConnectionWaiter mConnectionWaiterPool;
  
  private ConnectionWaiter mConnectionWaiterQueue;
  
  private IdleConnectionHandler mIdleConnectionHandler;
  
  private boolean mIsOpen;
  
  private final Object mLock = new Object();
  
  private int mMaxConnectionPoolSize;
  
  private int mNextConnectionId;
  
  private final AtomicLong mTotalExecutionTimeCounter = new AtomicLong(0L);
  
  private SQLiteConnectionPool(SQLiteDatabaseConfiguration paramSQLiteDatabaseConfiguration) {
    this.mConfiguration = new SQLiteDatabaseConfiguration(paramSQLiteDatabaseConfiguration);
    setMaxConnectionPoolSizeLocked();
    if (this.mConfiguration.idleConnectionTimeoutMs != Long.MAX_VALUE)
      setupIdleConnectionHandler(Looper.getMainLooper(), this.mConfiguration.idleConnectionTimeoutMs); 
  }
  
  private void cancelConnectionWaiterLocked(ConnectionWaiter paramConnectionWaiter) {
    if (paramConnectionWaiter.mAssignedConnection != null || paramConnectionWaiter.mException != null)
      return; 
    ConnectionWaiter connectionWaiter1 = null;
    for (ConnectionWaiter connectionWaiter2 = this.mConnectionWaiterQueue; connectionWaiter2 != paramConnectionWaiter; connectionWaiter2 = connectionWaiter2.mNext)
      connectionWaiter1 = connectionWaiter2; 
    if (connectionWaiter1 != null) {
      connectionWaiter1.mNext = paramConnectionWaiter.mNext;
    } else {
      this.mConnectionWaiterQueue = paramConnectionWaiter.mNext;
    } 
    paramConnectionWaiter.mException = (RuntimeException)new OperationCanceledException();
    LockSupport.unpark(paramConnectionWaiter.mThread);
    wakeConnectionWaitersLocked();
  }
  
  private boolean closeAvailableConnectionLocked(int paramInt) {
    for (int i = this.mAvailableNonPrimaryConnections.size() - 1; i >= 0; i--) {
      SQLiteConnection sQLiteConnection1 = this.mAvailableNonPrimaryConnections.get(i);
      if (sQLiteConnection1.getConnectionId() == paramInt) {
        closeConnectionAndLogExceptionsLocked(sQLiteConnection1);
        this.mAvailableNonPrimaryConnections.remove(i);
        return true;
      } 
    } 
    SQLiteConnection sQLiteConnection = this.mAvailablePrimaryConnection;
    if (sQLiteConnection != null && sQLiteConnection.getConnectionId() == paramInt) {
      closeConnectionAndLogExceptionsLocked(this.mAvailablePrimaryConnection);
      this.mAvailablePrimaryConnection = null;
      return true;
    } 
    return false;
  }
  
  private void closeAvailableConnectionsAndLogExceptionsLocked() {
    closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked();
    SQLiteConnection sQLiteConnection = this.mAvailablePrimaryConnection;
    if (sQLiteConnection != null) {
      closeConnectionAndLogExceptionsLocked(sQLiteConnection);
      this.mAvailablePrimaryConnection = null;
    } 
  }
  
  private void closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked() {
    int i = this.mAvailableNonPrimaryConnections.size();
    for (byte b = 0; b < i; b++)
      closeConnectionAndLogExceptionsLocked(this.mAvailableNonPrimaryConnections.get(b)); 
    this.mAvailableNonPrimaryConnections.clear();
  }
  
  private void closeConnectionAndLogExceptionsLocked(SQLiteConnection paramSQLiteConnection) {
    try {
      paramSQLiteConnection.close();
      if (this.mIdleConnectionHandler != null)
        this.mIdleConnectionHandler.connectionClosed(paramSQLiteConnection); 
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to close connection, its fate is now in the hands of the merciful GC: ");
      stringBuilder.append(paramSQLiteConnection);
      Log.e("SQLiteConnectionPool", stringBuilder.toString(), runtimeException);
    } 
  }
  
  private void closeExcessConnectionsAndLogExceptionsLocked() {
    int i = this.mAvailableNonPrimaryConnections.size();
    while (true) {
      int j = i - 1;
      if (i > this.mMaxConnectionPoolSize - 1) {
        closeConnectionAndLogExceptionsLocked(this.mAvailableNonPrimaryConnections.remove(j));
        i = j;
        continue;
      } 
      break;
    } 
  }
  
  private void discardAcquiredConnectionsLocked() {
    markAcquiredConnectionsLocked(AcquiredConnectionStatus.DISCARD);
  }
  
  private void dispose(boolean paramBoolean) {
    CloseGuard closeGuard = this.mCloseGuard;
    if (closeGuard != null) {
      if (paramBoolean)
        closeGuard.warnIfOpen(); 
      this.mCloseGuard.close();
    } 
    if (!paramBoolean)
      synchronized (this.mLock) {
        throwIfClosedLocked();
        this.mIsOpen = false;
        closeAvailableConnectionsAndLogExceptionsLocked();
        int i = this.mAcquiredConnections.size();
        if (i != 0) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("The connection pool for ");
          stringBuilder.append(this.mConfiguration.label);
          stringBuilder.append(" has been closed but there are still ");
          stringBuilder.append(i);
          stringBuilder.append(" connections in use.  They will be closed as they are released back to the pool.");
          Log.i("SQLiteConnectionPool", stringBuilder.toString());
        } 
        wakeConnectionWaitersLocked();
      }  
  }
  
  private void finishAcquireConnectionLocked(SQLiteConnection paramSQLiteConnection, int paramInt) {
    boolean bool;
    if ((paramInt & 0x1) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    try {
      paramSQLiteConnection.setOnlyAllowReadOnlyOperations(bool);
      this.mAcquiredConnections.put(paramSQLiteConnection, AcquiredConnectionStatus.NORMAL);
      return;
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to prepare acquired connection for session, closing it: ");
      stringBuilder.append(paramSQLiteConnection);
      stringBuilder.append(", connectionFlags=");
      stringBuilder.append(paramInt);
      Log.e("SQLiteConnectionPool", stringBuilder.toString());
      closeConnectionAndLogExceptionsLocked(paramSQLiteConnection);
      throw runtimeException;
    } 
  }
  
  private static int getPriority(int paramInt) {
    if ((paramInt & 0x4) != 0) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    return paramInt;
  }
  
  private boolean isSessionBlockingImportantConnectionWaitersLocked(boolean paramBoolean, int paramInt) {
    ConnectionWaiter connectionWaiter = this.mConnectionWaiterQueue;
    if (connectionWaiter != null) {
      paramInt = getPriority(paramInt);
      while (paramInt <= connectionWaiter.mPriority) {
        if (paramBoolean || !connectionWaiter.mWantPrimaryConnection)
          return true; 
        ConnectionWaiter connectionWaiter1 = connectionWaiter.mNext;
        connectionWaiter = connectionWaiter1;
        if (connectionWaiter1 == null)
          break; 
      } 
    } 
    return false;
  }
  
  private void logConnectionPoolBusyLocked(long paramLong, int paramInt) {
    Thread thread = Thread.currentThread();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("The connection pool for database '");
    stringBuilder.append(this.mConfiguration.label);
    stringBuilder.append("' has been unable to grant a connection to thread ");
    stringBuilder.append(thread.getId());
    stringBuilder.append(" (");
    stringBuilder.append(thread.getName());
    stringBuilder.append(") ");
    stringBuilder.append("with flags 0x");
    stringBuilder.append(Integer.toHexString(paramInt));
    stringBuilder.append(" for ");
    stringBuilder.append((float)paramLong * 0.001F);
    stringBuilder.append(" seconds.\n");
    ArrayList<String> arrayList = new ArrayList();
    int i = 0;
    int j = 0;
    int k = 0;
    paramInt = 0;
    if (!this.mAcquiredConnections.isEmpty()) {
      Iterator<SQLiteConnection> iterator = this.mAcquiredConnections.keySet().iterator();
      while (true) {
        i = j;
        k = paramInt;
        if (iterator.hasNext()) {
          String str = ((SQLiteConnection)iterator.next()).describeCurrentOperationUnsafe();
          if (str != null) {
            arrayList.add(str);
            j++;
            continue;
          } 
          paramInt++;
          continue;
        } 
        break;
      } 
    } 
    j = this.mAvailableNonPrimaryConnections.size();
    paramInt = j;
    if (this.mAvailablePrimaryConnection != null)
      paramInt = j + 1; 
    stringBuilder.append("Connections: ");
    stringBuilder.append(i);
    stringBuilder.append(" active, ");
    stringBuilder.append(k);
    stringBuilder.append(" idle, ");
    stringBuilder.append(paramInt);
    stringBuilder.append(" available.\n");
    if (!arrayList.isEmpty()) {
      stringBuilder.append("\nRequests in progress:\n");
      for (String str : arrayList) {
        stringBuilder.append("  ");
        stringBuilder.append(str);
        stringBuilder.append("\n");
      } 
    } 
    Log.w("SQLiteConnectionPool", stringBuilder.toString());
  }
  
  private void markAcquiredConnectionsLocked(AcquiredConnectionStatus paramAcquiredConnectionStatus) {
    if (!this.mAcquiredConnections.isEmpty()) {
      ArrayList<SQLiteConnection> arrayList = new ArrayList(this.mAcquiredConnections.size());
      for (Map.Entry<SQLiteConnection, AcquiredConnectionStatus> entry : this.mAcquiredConnections.entrySet()) {
        AcquiredConnectionStatus acquiredConnectionStatus = (AcquiredConnectionStatus)entry.getValue();
        if (paramAcquiredConnectionStatus != acquiredConnectionStatus && acquiredConnectionStatus != AcquiredConnectionStatus.DISCARD)
          arrayList.add((SQLiteConnection)entry.getKey()); 
      } 
      int i = arrayList.size();
      for (byte b = 0; b < i; b++)
        this.mAcquiredConnections.put(arrayList.get(b), paramAcquiredConnectionStatus); 
    } 
  }
  
  private ConnectionWaiter obtainConnectionWaiterLocked(Thread paramThread, long paramLong, int paramInt1, boolean paramBoolean, String paramString, int paramInt2) {
    ConnectionWaiter connectionWaiter = this.mConnectionWaiterPool;
    if (connectionWaiter != null) {
      this.mConnectionWaiterPool = connectionWaiter.mNext;
      connectionWaiter.mNext = null;
    } else {
      connectionWaiter = new ConnectionWaiter();
    } 
    connectionWaiter.mThread = paramThread;
    connectionWaiter.mStartTime = paramLong;
    connectionWaiter.mPriority = paramInt1;
    connectionWaiter.mWantPrimaryConnection = paramBoolean;
    connectionWaiter.mSql = paramString;
    connectionWaiter.mConnectionFlags = paramInt2;
    return connectionWaiter;
  }
  
  public static SQLiteConnectionPool open(SQLiteDatabaseConfiguration paramSQLiteDatabaseConfiguration) {
    if (paramSQLiteDatabaseConfiguration != null) {
      SQLiteConnectionPool sQLiteConnectionPool = new SQLiteConnectionPool(paramSQLiteDatabaseConfiguration);
      sQLiteConnectionPool.open();
      return sQLiteConnectionPool;
    } 
    throw new IllegalArgumentException("configuration must not be null.");
  }
  
  private void open() {
    this.mAvailablePrimaryConnection = openConnectionLocked(this.mConfiguration, true);
    synchronized (this.mLock) {
      if (this.mIdleConnectionHandler != null)
        this.mIdleConnectionHandler.connectionReleased(this.mAvailablePrimaryConnection); 
      this.mIsOpen = true;
      this.mCloseGuard.open("close");
      return;
    } 
  }
  
  private SQLiteConnection openConnectionLocked(SQLiteDatabaseConfiguration paramSQLiteDatabaseConfiguration, boolean paramBoolean) {
    int i = this.mNextConnectionId;
    this.mNextConnectionId = i + 1;
    return SQLiteConnection.open(this, paramSQLiteDatabaseConfiguration, i, paramBoolean);
  }
  
  private void reconfigureAllConnectionsLocked() {
    SQLiteConnection sQLiteConnection = this.mAvailablePrimaryConnection;
    if (sQLiteConnection != null)
      try {
        sQLiteConnection.reconfigure(this.mConfiguration);
      } catch (RuntimeException runtimeException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to reconfigure available primary connection, closing it: ");
        stringBuilder.append(this.mAvailablePrimaryConnection);
        Log.e("SQLiteConnectionPool", stringBuilder.toString(), runtimeException);
        closeConnectionAndLogExceptionsLocked(this.mAvailablePrimaryConnection);
        this.mAvailablePrimaryConnection = null;
      }  
    int i = this.mAvailableNonPrimaryConnections.size();
    for (byte b = 0; b < i; b++) {
      sQLiteConnection = this.mAvailableNonPrimaryConnections.get(b);
      try {
        sQLiteConnection.reconfigure(this.mConfiguration);
      } catch (RuntimeException runtimeException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to reconfigure available non-primary connection, closing it: ");
        stringBuilder.append(sQLiteConnection);
        Log.e("SQLiteConnectionPool", stringBuilder.toString(), runtimeException);
        closeConnectionAndLogExceptionsLocked(sQLiteConnection);
        this.mAvailableNonPrimaryConnections.remove(b);
        i--;
        b--;
      } 
    } 
    markAcquiredConnectionsLocked(AcquiredConnectionStatus.RECONFIGURE);
  }
  
  private boolean recycleConnectionLocked(SQLiteConnection paramSQLiteConnection, AcquiredConnectionStatus paramAcquiredConnectionStatus) {
    AcquiredConnectionStatus acquiredConnectionStatus = paramAcquiredConnectionStatus;
    if (paramAcquiredConnectionStatus == AcquiredConnectionStatus.RECONFIGURE)
      try {
        paramSQLiteConnection.reconfigure(this.mConfiguration);
        acquiredConnectionStatus = paramAcquiredConnectionStatus;
      } catch (RuntimeException runtimeException) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to reconfigure released connection, closing it: ");
        stringBuilder.append(paramSQLiteConnection);
        Log.e("SQLiteConnectionPool", stringBuilder.toString(), runtimeException);
        acquiredConnectionStatus = AcquiredConnectionStatus.DISCARD;
      }  
    if (acquiredConnectionStatus == AcquiredConnectionStatus.DISCARD) {
      closeConnectionAndLogExceptionsLocked(paramSQLiteConnection);
      return false;
    } 
    return true;
  }
  
  private void recycleConnectionWaiterLocked(ConnectionWaiter paramConnectionWaiter) {
    paramConnectionWaiter.mNext = this.mConnectionWaiterPool;
    paramConnectionWaiter.mThread = null;
    paramConnectionWaiter.mSql = null;
    paramConnectionWaiter.mAssignedConnection = null;
    paramConnectionWaiter.mException = null;
    paramConnectionWaiter.mNonce++;
    this.mConnectionWaiterPool = paramConnectionWaiter;
  }
  
  private void setMaxConnectionPoolSizeLocked() {
    if (!this.mConfiguration.isInMemoryDb() && (this.mConfiguration.openFlags & 0x20000000) != 0) {
      this.mMaxConnectionPoolSize = SQLiteGlobal.getWALConnectionPoolSize();
    } else {
      this.mMaxConnectionPoolSize = 1;
    } 
  }
  
  private void throwIfClosedLocked() {
    if (this.mIsOpen)
      return; 
    throw new IllegalStateException("Cannot perform this operation because the connection pool has been closed.");
  }
  
  private SQLiteConnection tryAcquireNonPrimaryConnectionLocked(String paramString, int paramInt) {
    int i = this.mAvailableNonPrimaryConnections.size();
    if (i > 1 && paramString != null)
      for (byte b = 0; b < i; b++) {
        SQLiteConnection sQLiteConnection1 = this.mAvailableNonPrimaryConnections.get(b);
        if (sQLiteConnection1.isPreparedStatementInCache(paramString)) {
          this.mAvailableNonPrimaryConnections.remove(b);
          finishAcquireConnectionLocked(sQLiteConnection1, paramInt);
          return sQLiteConnection1;
        } 
      }  
    if (i > 0) {
      SQLiteConnection sQLiteConnection1 = this.mAvailableNonPrimaryConnections.remove(i - 1);
      finishAcquireConnectionLocked(sQLiteConnection1, paramInt);
      return sQLiteConnection1;
    } 
    i = this.mAcquiredConnections.size();
    int j = i;
    if (this.mAvailablePrimaryConnection != null)
      j = i + 1; 
    if (j >= this.mMaxConnectionPoolSize)
      return null; 
    SQLiteConnection sQLiteConnection = openConnectionLocked(this.mConfiguration, false);
    finishAcquireConnectionLocked(sQLiteConnection, paramInt);
    return sQLiteConnection;
  }
  
  private SQLiteConnection tryAcquirePrimaryConnectionLocked(int paramInt) {
    SQLiteConnection sQLiteConnection2 = this.mAvailablePrimaryConnection;
    if (sQLiteConnection2 != null) {
      this.mAvailablePrimaryConnection = null;
      finishAcquireConnectionLocked(sQLiteConnection2, paramInt);
      return sQLiteConnection2;
    } 
    Iterator<SQLiteConnection> iterator = this.mAcquiredConnections.keySet().iterator();
    while (iterator.hasNext()) {
      if (((SQLiteConnection)iterator.next()).isPrimaryConnection())
        return null; 
    } 
    SQLiteConnection sQLiteConnection1 = openConnectionLocked(this.mConfiguration, true);
    finishAcquireConnectionLocked(sQLiteConnection1, paramInt);
    return sQLiteConnection1;
  }
  
  private SQLiteConnection waitForConnection(String paramString, int paramInt, CancellationSignal paramCancellationSignal) {
    boolean bool;
    if ((paramInt & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Object object = this.mLock;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
    try {
      ConnectionWaiter connectionWaiter2;
      throwIfClosedLocked();
      if (paramCancellationSignal != null)
        try {
          paramCancellationSignal.throwIfCanceled();
        } finally {} 
      SQLiteConnection sQLiteConnection1 = null;
      if (!bool)
        sQLiteConnection1 = tryAcquireNonPrimaryConnectionLocked(paramString, paramInt); 
      SQLiteConnection sQLiteConnection2 = sQLiteConnection1;
      if (sQLiteConnection1 == null)
        sQLiteConnection2 = tryAcquirePrimaryConnectionLocked(paramInt); 
      if (sQLiteConnection2 != null)
        return sQLiteConnection2; 
      final int nonce = getPriority(paramInt);
      long l = SystemClock.uptimeMillis();
      final ConnectionWaiter waiter = obtainConnectionWaiterLocked(Thread.currentThread(), l, i, bool, paramString, paramInt);
      sQLiteConnection1 = null;
      ConnectionWaiter connectionWaiter1;
      for (connectionWaiter1 = this.mConnectionWaiterQueue; connectionWaiter1 != null; connectionWaiter1 = connectionWaiter1.mNext) {
        if (i > connectionWaiter1.mPriority) {
          connectionWaiter3.mNext = connectionWaiter1;
          break;
        } 
        connectionWaiter2 = connectionWaiter1;
      } 
      if (connectionWaiter2 != null) {
        connectionWaiter2.mNext = connectionWaiter3;
      } else {
        this.mConnectionWaiterQueue = connectionWaiter3;
      } 
      i = connectionWaiter3.mNonce;
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
      if (paramCancellationSignal != null)
        paramCancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
              public void onCancel() {
                synchronized (SQLiteConnectionPool.this.mLock) {
                  if (waiter.mNonce == nonce)
                    SQLiteConnectionPool.this.cancelConnectionWaiterLocked(waiter); 
                  return;
                } 
              }
            }); 
      l = 30000L;
      boolean bool1 = bool;
      try {
        long l1 = connectionWaiter3.mStartTime + 30000L;
        while (true) {
          bool1 = bool;
          bool1 = this.mConnectionLeaked.compareAndSet(true, false);
          if (bool1)
            try {
              synchronized (this.mLock) {
                wakeConnectionWaitersLocked();
              } 
            } finally {} 
          try {
            LockSupport.parkNanos(this, l * 1000000L);
            Thread.interrupted();
            Object object1 = this.mLock;
            /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
            try {
              throwIfClosedLocked();
              SQLiteConnection sQLiteConnection = connectionWaiter3.mAssignedConnection;
              object = connectionWaiter3.mException;
              if (sQLiteConnection != null || object != null) {
                recycleConnectionWaiterLocked(connectionWaiter3);
                if (sQLiteConnection != null) {
                  /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
                  return sQLiteConnection;
                } 
                throw object;
              } 
              long l2 = SystemClock.uptimeMillis();
              if (l2 < l1) {
                l = l2 - l1;
              } else {
                try {
                  logConnectionPoolBusyLocked(l2 - connectionWaiter3.mStartTime, paramInt);
                  l = 30000L;
                  l1 = l2 + 30000L;
                  try {
                    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
                    continue;
                  } finally {}
                } finally {}
                /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
              } 
              try {
                /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
                continue;
              } finally {}
            } finally {}
            /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=null} */
          } finally {}
          if (paramCancellationSignal != null)
            paramCancellationSignal.setOnCancelListener(null); 
          throw connectionWaiter1;
        } 
      } finally {}
      if (paramCancellationSignal != null)
        paramCancellationSignal.setOnCancelListener(null); 
      throw connectionWaiter1;
    } finally {
      paramString = null;
    } 
  }
  
  private void wakeConnectionWaitersLocked() {
    ConnectionWaiter connectionWaiter1 = null;
    ConnectionWaiter connectionWaiter2 = this.mConnectionWaiterQueue;
    boolean bool1 = false;
    boolean bool2;
    for (bool2 = false; connectionWaiter2 != null; bool2 = bool4) {
      boolean bool4;
      boolean bool3 = false;
      if (!this.mIsOpen) {
        boolean bool = true;
        bool4 = bool2;
        bool2 = bool;
      } else {
        SQLiteConnection sQLiteConnection1 = null;
        bool4 = bool2;
        SQLiteConnection sQLiteConnection2 = sQLiteConnection1;
        boolean bool5 = bool1;
        boolean bool6 = bool2;
        try {
          if (!connectionWaiter2.mWantPrimaryConnection) {
            bool4 = bool2;
            sQLiteConnection2 = sQLiteConnection1;
            if (!bool2) {
              bool5 = bool1;
              bool6 = bool2;
              sQLiteConnection1 = tryAcquireNonPrimaryConnectionLocked(connectionWaiter2.mSql, connectionWaiter2.mConnectionFlags);
              bool4 = bool2;
              sQLiteConnection2 = sQLiteConnection1;
              if (sQLiteConnection1 == null) {
                bool4 = true;
                sQLiteConnection2 = sQLiteConnection1;
              } 
            } 
          } 
          bool2 = bool1;
          sQLiteConnection1 = sQLiteConnection2;
          if (sQLiteConnection2 == null) {
            bool2 = bool1;
            sQLiteConnection1 = sQLiteConnection2;
            if (!bool1) {
              bool5 = bool1;
              bool6 = bool4;
              sQLiteConnection2 = tryAcquirePrimaryConnectionLocked(connectionWaiter2.mConnectionFlags);
              bool2 = bool1;
              sQLiteConnection1 = sQLiteConnection2;
              if (sQLiteConnection2 == null) {
                bool2 = true;
                sQLiteConnection1 = sQLiteConnection2;
              } 
            } 
          } 
          if (sQLiteConnection1 != null) {
            bool5 = bool2;
            bool6 = bool4;
            connectionWaiter2.mAssignedConnection = sQLiteConnection1;
            bool5 = true;
          } else {
            bool5 = bool3;
            if (bool4) {
              bool5 = bool3;
              if (bool2)
                break; 
            } 
          } 
          bool1 = bool2;
          bool2 = bool5;
        } catch (RuntimeException runtimeException) {
          connectionWaiter2.mException = runtimeException;
          bool2 = true;
          bool4 = bool6;
          bool1 = bool5;
        } 
      } 
      ConnectionWaiter connectionWaiter = connectionWaiter2.mNext;
      if (bool2) {
        if (connectionWaiter1 != null) {
          connectionWaiter1.mNext = connectionWaiter;
        } else {
          this.mConnectionWaiterQueue = connectionWaiter;
        } 
        connectionWaiter2.mNext = null;
        LockSupport.unpark(connectionWaiter2.mThread);
      } else {
        connectionWaiter1 = connectionWaiter2;
      } 
      connectionWaiter2 = connectionWaiter;
    } 
  }
  
  public SQLiteConnection acquireConnection(String paramString, int paramInt, CancellationSignal paramCancellationSignal) {
    null = waitForConnection(paramString, paramInt, paramCancellationSignal);
    synchronized (this.mLock) {
      if (this.mIdleConnectionHandler != null)
        this.mIdleConnectionHandler.connectionAcquired(null); 
      return null;
    } 
  }
  
  public void close() {
    dispose(false);
  }
  
  void closeAvailableNonPrimaryConnectionsAndLogExceptions() {
    synchronized (this.mLock) {
      closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked();
      return;
    } 
  }
  
  public void collectDbStats(ArrayList<SQLiteDebug.DbStats> paramArrayList) {
    synchronized (this.mLock) {
      if (this.mAvailablePrimaryConnection != null)
        this.mAvailablePrimaryConnection.collectDbStats(paramArrayList); 
      Iterator<SQLiteConnection> iterator = this.mAvailableNonPrimaryConnections.iterator();
      while (iterator.hasNext())
        ((SQLiteConnection)iterator.next()).collectDbStats(paramArrayList); 
      iterator = this.mAcquiredConnections.keySet().iterator();
      while (iterator.hasNext())
        ((SQLiteConnection)iterator.next()).collectDbStatsUnsafe(paramArrayList); 
      return;
    } 
  }
  
  void disableIdleConnectionHandler() {
    synchronized (this.mLock) {
      this.mIdleConnectionHandler = null;
      return;
    } 
  }
  
  public void dump(Printer paramPrinter, boolean paramBoolean, ArraySet<String> paramArraySet) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w '    '
    //   4: invokestatic create : (Landroid/util/Printer;Ljava/lang/String;)Landroid/util/Printer;
    //   7: astore #4
    //   9: aload_0
    //   10: getfield mLock : Ljava/lang/Object;
    //   13: astore #5
    //   15: aload #5
    //   17: monitorenter
    //   18: aload_3
    //   19: ifnull -> 49
    //   22: new java/io/File
    //   25: astore #6
    //   27: aload #6
    //   29: aload_0
    //   30: getfield mConfiguration : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   33: getfield path : Ljava/lang/String;
    //   36: invokespecial <init> : (Ljava/lang/String;)V
    //   39: aload_3
    //   40: aload #6
    //   42: invokevirtual getParent : ()Ljava/lang/String;
    //   45: invokevirtual add : (Ljava/lang/Object;)Z
    //   48: pop
    //   49: aload_0
    //   50: getfield mConfiguration : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   53: invokevirtual isLegacyCompatibilityWalEnabled : ()Z
    //   56: istore #7
    //   58: new java/lang/StringBuilder
    //   61: astore_3
    //   62: aload_3
    //   63: invokespecial <init> : ()V
    //   66: aload_3
    //   67: ldc_w 'Connection pool for '
    //   70: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: pop
    //   74: aload_3
    //   75: aload_0
    //   76: getfield mConfiguration : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   79: getfield path : Ljava/lang/String;
    //   82: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: pop
    //   86: aload_3
    //   87: ldc_w ':'
    //   90: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: aload_1
    //   95: aload_3
    //   96: invokevirtual toString : ()Ljava/lang/String;
    //   99: invokeinterface println : (Ljava/lang/String;)V
    //   104: new java/lang/StringBuilder
    //   107: astore_3
    //   108: aload_3
    //   109: invokespecial <init> : ()V
    //   112: aload_3
    //   113: ldc_w '  Open: '
    //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: aload_3
    //   121: aload_0
    //   122: getfield mIsOpen : Z
    //   125: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   128: pop
    //   129: aload_1
    //   130: aload_3
    //   131: invokevirtual toString : ()Ljava/lang/String;
    //   134: invokeinterface println : (Ljava/lang/String;)V
    //   139: new java/lang/StringBuilder
    //   142: astore_3
    //   143: aload_3
    //   144: invokespecial <init> : ()V
    //   147: aload_3
    //   148: ldc_w '  Max connections: '
    //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: pop
    //   155: aload_3
    //   156: aload_0
    //   157: getfield mMaxConnectionPoolSize : I
    //   160: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   163: pop
    //   164: aload_1
    //   165: aload_3
    //   166: invokevirtual toString : ()Ljava/lang/String;
    //   169: invokeinterface println : (Ljava/lang/String;)V
    //   174: new java/lang/StringBuilder
    //   177: astore_3
    //   178: aload_3
    //   179: invokespecial <init> : ()V
    //   182: aload_3
    //   183: ldc_w '  Total execution time: '
    //   186: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: pop
    //   190: aload_3
    //   191: aload_0
    //   192: getfield mTotalExecutionTimeCounter : Ljava/util/concurrent/atomic/AtomicLong;
    //   195: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   198: pop
    //   199: aload_1
    //   200: aload_3
    //   201: invokevirtual toString : ()Ljava/lang/String;
    //   204: invokeinterface println : (Ljava/lang/String;)V
    //   209: new java/lang/StringBuilder
    //   212: astore_3
    //   213: aload_3
    //   214: invokespecial <init> : ()V
    //   217: aload_3
    //   218: ldc_w '  Configuration: openFlags='
    //   221: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: pop
    //   225: aload_3
    //   226: aload_0
    //   227: getfield mConfiguration : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   230: getfield openFlags : I
    //   233: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   236: pop
    //   237: aload_3
    //   238: ldc_w ', isLegacyCompatibilityWalEnabled='
    //   241: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: pop
    //   245: aload_3
    //   246: iload #7
    //   248: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   251: pop
    //   252: aload_3
    //   253: ldc_w ', journalMode='
    //   256: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: pop
    //   260: aload_3
    //   261: aload_0
    //   262: getfield mConfiguration : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   265: getfield journalMode : Ljava/lang/String;
    //   268: invokestatic emptyIfNull : (Ljava/lang/String;)Ljava/lang/String;
    //   271: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: pop
    //   275: aload_3
    //   276: ldc_w ', syncMode='
    //   279: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: pop
    //   283: aload_3
    //   284: aload_0
    //   285: getfield mConfiguration : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   288: getfield syncMode : Ljava/lang/String;
    //   291: invokestatic emptyIfNull : (Ljava/lang/String;)Ljava/lang/String;
    //   294: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: pop
    //   298: aload_1
    //   299: aload_3
    //   300: invokevirtual toString : ()Ljava/lang/String;
    //   303: invokeinterface println : (Ljava/lang/String;)V
    //   308: iload #7
    //   310: ifeq -> 347
    //   313: new java/lang/StringBuilder
    //   316: astore_3
    //   317: aload_3
    //   318: invokespecial <init> : ()V
    //   321: aload_3
    //   322: ldc_w '  Compatibility WAL enabled: wal_syncmode='
    //   325: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: pop
    //   329: aload_3
    //   330: invokestatic getWALSyncMode : ()Ljava/lang/String;
    //   333: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   336: pop
    //   337: aload_1
    //   338: aload_3
    //   339: invokevirtual toString : ()Ljava/lang/String;
    //   342: invokeinterface println : (Ljava/lang/String;)V
    //   347: aload_0
    //   348: getfield mConfiguration : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   351: invokevirtual isLookasideConfigSet : ()Z
    //   354: ifeq -> 415
    //   357: new java/lang/StringBuilder
    //   360: astore_3
    //   361: aload_3
    //   362: invokespecial <init> : ()V
    //   365: aload_3
    //   366: ldc_w '  Lookaside config: sz='
    //   369: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: pop
    //   373: aload_3
    //   374: aload_0
    //   375: getfield mConfiguration : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   378: getfield lookasideSlotSize : I
    //   381: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   384: pop
    //   385: aload_3
    //   386: ldc_w ' cnt='
    //   389: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: pop
    //   393: aload_3
    //   394: aload_0
    //   395: getfield mConfiguration : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   398: getfield lookasideSlotCount : I
    //   401: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   404: pop
    //   405: aload_1
    //   406: aload_3
    //   407: invokevirtual toString : ()Ljava/lang/String;
    //   410: invokeinterface println : (Ljava/lang/String;)V
    //   415: aload_0
    //   416: getfield mConfiguration : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   419: getfield idleConnectionTimeoutMs : J
    //   422: ldc2_w 9223372036854775807
    //   425: lcmp
    //   426: ifeq -> 467
    //   429: new java/lang/StringBuilder
    //   432: astore_3
    //   433: aload_3
    //   434: invokespecial <init> : ()V
    //   437: aload_3
    //   438: ldc_w '  Idle connection timeout: '
    //   441: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: pop
    //   445: aload_3
    //   446: aload_0
    //   447: getfield mConfiguration : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   450: getfield idleConnectionTimeoutMs : J
    //   453: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   456: pop
    //   457: aload_1
    //   458: aload_3
    //   459: invokevirtual toString : ()Ljava/lang/String;
    //   462: invokeinterface println : (Ljava/lang/String;)V
    //   467: aload_1
    //   468: ldc_w '  Available primary connection:'
    //   471: invokeinterface println : (Ljava/lang/String;)V
    //   476: aload_0
    //   477: getfield mAvailablePrimaryConnection : Landroid/database/sqlite/SQLiteConnection;
    //   480: ifnull -> 496
    //   483: aload_0
    //   484: getfield mAvailablePrimaryConnection : Landroid/database/sqlite/SQLiteConnection;
    //   487: aload #4
    //   489: iload_2
    //   490: invokevirtual dump : (Landroid/util/Printer;Z)V
    //   493: goto -> 506
    //   496: aload #4
    //   498: ldc_w '<none>'
    //   501: invokeinterface println : (Ljava/lang/String;)V
    //   506: aload_1
    //   507: ldc_w '  Available non-primary connections:'
    //   510: invokeinterface println : (Ljava/lang/String;)V
    //   515: aload_0
    //   516: getfield mAvailableNonPrimaryConnections : Ljava/util/ArrayList;
    //   519: invokevirtual isEmpty : ()Z
    //   522: ifne -> 571
    //   525: aload_0
    //   526: getfield mAvailableNonPrimaryConnections : Ljava/util/ArrayList;
    //   529: invokevirtual size : ()I
    //   532: istore #8
    //   534: iconst_0
    //   535: istore #9
    //   537: iload #9
    //   539: iload #8
    //   541: if_icmpge -> 568
    //   544: aload_0
    //   545: getfield mAvailableNonPrimaryConnections : Ljava/util/ArrayList;
    //   548: iload #9
    //   550: invokevirtual get : (I)Ljava/lang/Object;
    //   553: checkcast android/database/sqlite/SQLiteConnection
    //   556: aload #4
    //   558: iload_2
    //   559: invokevirtual dump : (Landroid/util/Printer;Z)V
    //   562: iinc #9, 1
    //   565: goto -> 537
    //   568: goto -> 581
    //   571: aload #4
    //   573: ldc_w '<none>'
    //   576: invokeinterface println : (Ljava/lang/String;)V
    //   581: aload_1
    //   582: ldc_w '  Acquired connections:'
    //   585: invokeinterface println : (Ljava/lang/String;)V
    //   590: aload_0
    //   591: getfield mAcquiredConnections : Ljava/util/WeakHashMap;
    //   594: invokevirtual isEmpty : ()Z
    //   597: ifne -> 697
    //   600: aload_0
    //   601: getfield mAcquiredConnections : Ljava/util/WeakHashMap;
    //   604: invokevirtual entrySet : ()Ljava/util/Set;
    //   607: invokeinterface iterator : ()Ljava/util/Iterator;
    //   612: astore #10
    //   614: aload #10
    //   616: invokeinterface hasNext : ()Z
    //   621: ifeq -> 694
    //   624: aload #10
    //   626: invokeinterface next : ()Ljava/lang/Object;
    //   631: checkcast java/util/Map$Entry
    //   634: astore #6
    //   636: aload #6
    //   638: invokeinterface getKey : ()Ljava/lang/Object;
    //   643: checkcast android/database/sqlite/SQLiteConnection
    //   646: aload #4
    //   648: iload_2
    //   649: invokevirtual dumpUnsafe : (Landroid/util/Printer;Z)V
    //   652: new java/lang/StringBuilder
    //   655: astore_3
    //   656: aload_3
    //   657: invokespecial <init> : ()V
    //   660: aload_3
    //   661: ldc_w '  Status: '
    //   664: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   667: pop
    //   668: aload_3
    //   669: aload #6
    //   671: invokeinterface getValue : ()Ljava/lang/Object;
    //   676: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   679: pop
    //   680: aload #4
    //   682: aload_3
    //   683: invokevirtual toString : ()Ljava/lang/String;
    //   686: invokeinterface println : (Ljava/lang/String;)V
    //   691: goto -> 614
    //   694: goto -> 707
    //   697: aload #4
    //   699: ldc_w '<none>'
    //   702: invokeinterface println : (Ljava/lang/String;)V
    //   707: aload_1
    //   708: ldc_w '  Connection waiters:'
    //   711: invokeinterface println : (Ljava/lang/String;)V
    //   716: aload_0
    //   717: getfield mConnectionWaiterQueue : Landroid/database/sqlite/SQLiteConnectionPool$ConnectionWaiter;
    //   720: ifnull -> 864
    //   723: iconst_0
    //   724: istore #9
    //   726: invokestatic uptimeMillis : ()J
    //   729: lstore #11
    //   731: aload_0
    //   732: getfield mConnectionWaiterQueue : Landroid/database/sqlite/SQLiteConnectionPool$ConnectionWaiter;
    //   735: astore_1
    //   736: aload_1
    //   737: ifnull -> 861
    //   740: new java/lang/StringBuilder
    //   743: astore_3
    //   744: aload_3
    //   745: invokespecial <init> : ()V
    //   748: aload_3
    //   749: iload #9
    //   751: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   754: pop
    //   755: aload_3
    //   756: ldc_w ': waited for '
    //   759: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   762: pop
    //   763: aload_3
    //   764: lload #11
    //   766: aload_1
    //   767: getfield mStartTime : J
    //   770: lsub
    //   771: l2f
    //   772: ldc_w 0.001
    //   775: fmul
    //   776: invokevirtual append : (F)Ljava/lang/StringBuilder;
    //   779: pop
    //   780: aload_3
    //   781: ldc_w ' ms - thread='
    //   784: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   787: pop
    //   788: aload_3
    //   789: aload_1
    //   790: getfield mThread : Ljava/lang/Thread;
    //   793: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   796: pop
    //   797: aload_3
    //   798: ldc_w ', priority='
    //   801: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   804: pop
    //   805: aload_3
    //   806: aload_1
    //   807: getfield mPriority : I
    //   810: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   813: pop
    //   814: aload_3
    //   815: ldc_w ', sql=''
    //   818: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   821: pop
    //   822: aload_3
    //   823: aload_1
    //   824: getfield mSql : Ljava/lang/String;
    //   827: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   830: pop
    //   831: aload_3
    //   832: ldc_w '''
    //   835: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   838: pop
    //   839: aload #4
    //   841: aload_3
    //   842: invokevirtual toString : ()Ljava/lang/String;
    //   845: invokeinterface println : (Ljava/lang/String;)V
    //   850: aload_1
    //   851: getfield mNext : Landroid/database/sqlite/SQLiteConnectionPool$ConnectionWaiter;
    //   854: astore_1
    //   855: iinc #9, 1
    //   858: goto -> 736
    //   861: goto -> 874
    //   864: aload #4
    //   866: ldc_w '<none>'
    //   869: invokeinterface println : (Ljava/lang/String;)V
    //   874: aload #5
    //   876: monitorexit
    //   877: return
    //   878: astore_1
    //   879: aload #5
    //   881: monitorexit
    //   882: aload_1
    //   883: athrow
    // Exception table:
    //   from	to	target	type
    //   22	49	878	finally
    //   49	308	878	finally
    //   313	347	878	finally
    //   347	415	878	finally
    //   415	467	878	finally
    //   467	493	878	finally
    //   496	506	878	finally
    //   506	534	878	finally
    //   544	562	878	finally
    //   571	581	878	finally
    //   581	614	878	finally
    //   614	691	878	finally
    //   697	707	878	finally
    //   707	723	878	finally
    //   726	736	878	finally
    //   740	855	878	finally
    //   864	874	878	finally
    //   874	877	878	finally
    //   879	882	878	finally
  }
  
  protected void finalize() throws Throwable {
    try {
      dispose(true);
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public String getPath() {
    return this.mConfiguration.path;
  }
  
  void onConnectionLeaked() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("A SQLiteConnection object for database '");
    stringBuilder.append(this.mConfiguration.label);
    stringBuilder.append("' was leaked!  Please fix your application to end transactions in progress properly and to close the database when it is no longer needed.");
    Log.w("SQLiteConnectionPool", stringBuilder.toString());
    this.mConnectionLeaked.set(true);
  }
  
  void onStatementExecuted(long paramLong) {
    this.mTotalExecutionTimeCounter.addAndGet(paramLong);
  }
  
  public void reconfigure(SQLiteDatabaseConfiguration paramSQLiteDatabaseConfiguration) {
    if (paramSQLiteDatabaseConfiguration != null)
      synchronized (this.mLock) {
        throwIfClosedLocked();
        int i = paramSQLiteDatabaseConfiguration.openFlags;
        int j = this.mConfiguration.openFlags;
        boolean bool = false;
        if (((i ^ j) & 0x20000000) != 0) {
          i = 1;
        } else {
          i = 0;
        } 
        if (i != 0)
          if (this.mAcquiredConnections.isEmpty()) {
            closeAvailableNonPrimaryConnectionsAndLogExceptionsLocked();
          } else {
            illegalStateException = new IllegalStateException();
            this("Write Ahead Logging (WAL) mode cannot be enabled or disabled while there are transactions in progress.  Finish all transactions and release all active database connections first.");
            throw illegalStateException;
          }  
        if (((SQLiteDatabaseConfiguration)illegalStateException).foreignKeyConstraintsEnabled != this.mConfiguration.foreignKeyConstraintsEnabled) {
          j = 1;
        } else {
          j = 0;
        } 
        if (j == 0 || this.mAcquiredConnections.isEmpty()) {
          j = bool;
          if ((this.mConfiguration.openFlags ^ ((SQLiteDatabaseConfiguration)illegalStateException).openFlags) == Integer.MIN_VALUE)
            j = 1; 
          if (j == 0 && this.mConfiguration.openFlags != ((SQLiteDatabaseConfiguration)illegalStateException).openFlags) {
            if (i != 0)
              closeAvailableConnectionsAndLogExceptionsLocked(); 
            SQLiteConnection sQLiteConnection = openConnectionLocked((SQLiteDatabaseConfiguration)illegalStateException, true);
            closeAvailableConnectionsAndLogExceptionsLocked();
            discardAcquiredConnectionsLocked();
            this.mAvailablePrimaryConnection = sQLiteConnection;
            this.mConfiguration.updateParametersFrom((SQLiteDatabaseConfiguration)illegalStateException);
            setMaxConnectionPoolSizeLocked();
          } else {
            this.mConfiguration.updateParametersFrom((SQLiteDatabaseConfiguration)illegalStateException);
            setMaxConnectionPoolSizeLocked();
            closeExcessConnectionsAndLogExceptionsLocked();
            reconfigureAllConnectionsLocked();
          } 
          wakeConnectionWaitersLocked();
          return;
        } 
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Foreign Key Constraints cannot be enabled or disabled while there are transactions in progress.  Finish all transactions and release all active database connections first.");
        throw illegalStateException;
      }  
    throw new IllegalArgumentException("configuration must not be null.");
  }
  
  public void releaseConnection(SQLiteConnection paramSQLiteConnection) {
    synchronized (this.mLock) {
      if (this.mIdleConnectionHandler != null)
        this.mIdleConnectionHandler.connectionReleased(paramSQLiteConnection); 
      AcquiredConnectionStatus acquiredConnectionStatus = this.mAcquiredConnections.remove(paramSQLiteConnection);
      if (acquiredConnectionStatus != null) {
        if (!this.mIsOpen) {
          closeConnectionAndLogExceptionsLocked(paramSQLiteConnection);
        } else if (paramSQLiteConnection.isPrimaryConnection()) {
          if (recycleConnectionLocked(paramSQLiteConnection, acquiredConnectionStatus))
            this.mAvailablePrimaryConnection = paramSQLiteConnection; 
          wakeConnectionWaitersLocked();
        } else if (this.mAvailableNonPrimaryConnections.size() >= this.mMaxConnectionPoolSize - 1) {
          closeConnectionAndLogExceptionsLocked(paramSQLiteConnection);
        } else {
          if (recycleConnectionLocked(paramSQLiteConnection, acquiredConnectionStatus))
            this.mAvailableNonPrimaryConnections.add(paramSQLiteConnection); 
          wakeConnectionWaitersLocked();
        } 
        return;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Cannot perform this operation because the specified connection was not acquired from this pool or has already been released.");
      throw illegalStateException;
    } 
  }
  
  public void setupIdleConnectionHandler(Looper paramLooper, long paramLong) {
    synchronized (this.mLock) {
      IdleConnectionHandler idleConnectionHandler = new IdleConnectionHandler();
      this(this, paramLooper, paramLong);
      this.mIdleConnectionHandler = idleConnectionHandler;
      return;
    } 
  }
  
  public boolean shouldYieldConnection(SQLiteConnection paramSQLiteConnection, int paramInt) {
    synchronized (this.mLock) {
      if (this.mAcquiredConnections.containsKey(paramSQLiteConnection)) {
        if (!this.mIsOpen)
          return false; 
        return isSessionBlockingImportantConnectionWaitersLocked(paramSQLiteConnection.isPrimaryConnection(), paramInt);
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("Cannot perform this operation because the specified connection was not acquired from this pool or has already been released.");
      throw illegalStateException;
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SQLiteConnectionPool: ");
    stringBuilder.append(this.mConfiguration.path);
    return stringBuilder.toString();
  }
  
  enum AcquiredConnectionStatus {
    DISCARD, NORMAL, RECONFIGURE;
    
    static {
      AcquiredConnectionStatus acquiredConnectionStatus = new AcquiredConnectionStatus("DISCARD", 2);
      DISCARD = acquiredConnectionStatus;
      $VALUES = new AcquiredConnectionStatus[] { NORMAL, RECONFIGURE, acquiredConnectionStatus };
    }
  }
  
  private static final class ConnectionWaiter {
    public SQLiteConnection mAssignedConnection;
    
    public int mConnectionFlags;
    
    public RuntimeException mException;
    
    public ConnectionWaiter mNext;
    
    public int mNonce;
    
    public int mPriority;
    
    public String mSql;
    
    public long mStartTime;
    
    public Thread mThread;
    
    public boolean mWantPrimaryConnection;
    
    private ConnectionWaiter() {}
  }
  
  private class IdleConnectionHandler extends Handler {
    private final long mTimeout;
    
    IdleConnectionHandler(Looper param1Looper, long param1Long) {
      super(param1Looper);
      this.mTimeout = param1Long;
    }
    
    void connectionAcquired(SQLiteConnection param1SQLiteConnection) {
      removeMessages(param1SQLiteConnection.getConnectionId());
    }
    
    void connectionClosed(SQLiteConnection param1SQLiteConnection) {
      removeMessages(param1SQLiteConnection.getConnectionId());
    }
    
    void connectionReleased(SQLiteConnection param1SQLiteConnection) {
      sendEmptyMessageDelayed(param1SQLiteConnection.getConnectionId(), this.mTimeout);
    }
    
    public void handleMessage(Message param1Message) {
      synchronized (SQLiteConnectionPool.this.mLock) {
        if (this != SQLiteConnectionPool.this.mIdleConnectionHandler)
          return; 
        if (SQLiteConnectionPool.this.closeAvailableConnectionLocked(param1Message.what) && Log.isLoggable("SQLiteConnectionPool", 3)) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("Closed idle connection ");
          stringBuilder.append(SQLiteConnectionPool.this.mConfiguration.label);
          stringBuilder.append(" ");
          stringBuilder.append(param1Message.what);
          stringBuilder.append(" after ");
          stringBuilder.append(this.mTimeout);
          Log.d("SQLiteConnectionPool", stringBuilder.toString());
        } 
        return;
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteConnectionPool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */