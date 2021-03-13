package android.database.sqlite;

import android.database.CursorWindow;
import android.database.DatabaseUtils;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import android.util.LruCache;
import android.util.Pair;
import android.util.Printer;
import dalvik.system.BlockGuard;
import dalvik.system.CloseGuard;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public final class SQLiteConnection implements CancellationSignal.OnCancelListener {
  private static final boolean DEBUG = false;
  
  private static final byte[] EMPTY_BYTE_ARRAY;
  
  private static final String[] EMPTY_STRING_ARRAY = new String[0];
  
  private static final String TAG = "SQLiteConnection";
  
  private int mCancellationSignalAttachCount;
  
  private final CloseGuard mCloseGuard = CloseGuard.get();
  
  private final SQLiteDatabaseConfiguration mConfiguration;
  
  private final int mConnectionId;
  
  private long mConnectionPtr;
  
  private final boolean mIsPrimaryConnection;
  
  private final boolean mIsReadOnlyConnection;
  
  private boolean mOnlyAllowReadOnlyOperations;
  
  private final SQLiteConnectionPool mPool;
  
  private final PreparedStatementCache mPreparedStatementCache;
  
  private PreparedStatement mPreparedStatementPool;
  
  private final OperationLog mRecentOperations;
  
  static {
    EMPTY_BYTE_ARRAY = new byte[0];
  }
  
  private SQLiteConnection(SQLiteConnectionPool paramSQLiteConnectionPool, SQLiteDatabaseConfiguration paramSQLiteDatabaseConfiguration, int paramInt, boolean paramBoolean) {
    this.mPool = paramSQLiteConnectionPool;
    this.mRecentOperations = new OperationLog(paramSQLiteConnectionPool);
    this.mConfiguration = new SQLiteDatabaseConfiguration(paramSQLiteDatabaseConfiguration);
    this.mConnectionId = paramInt;
    this.mIsPrimaryConnection = paramBoolean;
    paramInt = paramSQLiteDatabaseConfiguration.openFlags;
    paramBoolean = true;
    if ((paramInt & 0x1) == 0)
      paramBoolean = false; 
    this.mIsReadOnlyConnection = paramBoolean;
    this.mPreparedStatementCache = new PreparedStatementCache(this.mConfiguration.maxSqlCacheSize);
    this.mCloseGuard.open("close");
  }
  
  private PreparedStatement acquirePreparedStatement(String paramString) {
    PreparedStatement preparedStatement1 = (PreparedStatement)this.mPreparedStatementCache.get(paramString);
    boolean bool = false;
    if (preparedStatement1 != null) {
      if (!preparedStatement1.mInUse)
        return preparedStatement1; 
      bool = true;
    } 
    long l = nativePrepareStatement(this.mConnectionPtr, paramString);
    PreparedStatement preparedStatement2 = preparedStatement1;
    try {
      int i = nativeGetParameterCount(this.mConnectionPtr, l);
      preparedStatement2 = preparedStatement1;
      int j = DatabaseUtils.getSqlStatementType(paramString);
      preparedStatement2 = preparedStatement1;
      preparedStatement1 = obtainPreparedStatement(paramString, l, i, j, nativeIsReadOnly(this.mConnectionPtr, l));
      if (!bool) {
        preparedStatement2 = preparedStatement1;
        if (isCacheable(j)) {
          preparedStatement2 = preparedStatement1;
          this.mPreparedStatementCache.put(paramString, preparedStatement1);
          preparedStatement2 = preparedStatement1;
          preparedStatement1.mInCache = true;
        } 
      } 
      preparedStatement1.mInUse = true;
      return preparedStatement1;
    } catch (RuntimeException runtimeException) {
      if (preparedStatement2 == null || !preparedStatement2.mInCache)
        nativeFinalizeStatement(this.mConnectionPtr, l); 
      throw runtimeException;
    } 
  }
  
  private void applyBlockGuardPolicy(PreparedStatement paramPreparedStatement) {
    if (!this.mConfiguration.isInMemoryDb())
      if (paramPreparedStatement.mReadOnly) {
        BlockGuard.getThreadPolicy().onReadFromDisk();
      } else {
        BlockGuard.getThreadPolicy().onWriteToDisk();
      }  
  }
  
  private void attachCancellationSignal(CancellationSignal paramCancellationSignal) {
    if (paramCancellationSignal != null) {
      paramCancellationSignal.throwIfCanceled();
      int i = this.mCancellationSignalAttachCount + 1;
      this.mCancellationSignalAttachCount = i;
      if (i == 1) {
        nativeResetCancel(this.mConnectionPtr, true);
        paramCancellationSignal.setOnCancelListener(this);
      } 
    } 
  }
  
  private void bindArguments(PreparedStatement paramPreparedStatement, Object[] paramArrayOfObject) {
    Object object;
    byte b;
    if (paramArrayOfObject != null) {
      b = paramArrayOfObject.length;
    } else {
      b = 0;
    } 
    if (b == paramPreparedStatement.mNumParameters) {
      if (!b)
        return; 
      long l = paramPreparedStatement.mStatementPtr;
      for (byte b1 = 0; b1 < b; b1++) {
        object = paramArrayOfObject[b1];
        int i = DatabaseUtils.getTypeOfObject(object);
        if (i != 0) {
          if (i != 1) {
            if (i != 2) {
              if (i != 4) {
                if (object instanceof Boolean) {
                  long l2;
                  long l1 = this.mConnectionPtr;
                  if (((Boolean)object).booleanValue()) {
                    l2 = 1L;
                  } else {
                    l2 = 0L;
                  } 
                  nativeBindLong(l1, l, b1 + 1, l2);
                } else {
                  nativeBindString(this.mConnectionPtr, l, b1 + 1, object.toString());
                } 
              } else {
                nativeBindBlob(this.mConnectionPtr, l, b1 + 1, (byte[])object);
              } 
            } else {
              nativeBindDouble(this.mConnectionPtr, l, b1 + 1, ((Number)object).doubleValue());
            } 
          } else {
            nativeBindLong(this.mConnectionPtr, l, b1 + 1, ((Number)object).longValue());
          } 
        } else {
          nativeBindNull(this.mConnectionPtr, l, b1 + 1);
        } 
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Expected ");
    stringBuilder.append(((PreparedStatement)object).mNumParameters);
    stringBuilder.append(" bind arguments but ");
    stringBuilder.append(b);
    stringBuilder.append(" were provided.");
    throw new SQLiteBindOrColumnIndexOutOfRangeException(stringBuilder.toString());
  }
  
  private static String canonicalizeSyncMode(String paramString) {
    byte b;
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 50:
        if (paramString.equals("2")) {
          b = 2;
          break;
        } 
      case 49:
        if (paramString.equals("1")) {
          b = 1;
          break;
        } 
      case 48:
        if (paramString.equals("0")) {
          b = 0;
          break;
        } 
    } 
    return (b != 0) ? ((b != 1) ? ((b != 2) ? paramString : "FULL") : "NORMAL") : "OFF";
  }
  
  private void checkDatabaseWiped() {
    if (!SQLiteGlobal.checkDbWipe())
      return; 
    try {
      boolean bool;
      File file = new File();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(this.mConfiguration.path);
      stringBuilder.append("-wipecheck");
      this(stringBuilder.toString());
      if (executeForLong("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='android_metadata'", null, null) > 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      boolean bool1 = file.exists();
      if (!this.mIsReadOnlyConnection && !bool1)
        file.createNewFile(); 
      if (!bool && bool1)
        SQLiteDatabase.wipeDetected(this.mConfiguration.path, "unknown"); 
    } catch (RuntimeException|java.io.IOException runtimeException) {
      SQLiteDatabase.wtfAsSystemServer("SQLiteConnection", "Unexpected exception while checking for wipe", runtimeException);
    } 
  }
  
  private void detachCancellationSignal(CancellationSignal paramCancellationSignal) {
    if (paramCancellationSignal != null) {
      int i = this.mCancellationSignalAttachCount - 1;
      this.mCancellationSignalAttachCount = i;
      if (i == 0) {
        paramCancellationSignal.setOnCancelListener(null);
        nativeResetCancel(this.mConnectionPtr, false);
      } 
    } 
  }
  
  private void dispose(boolean paramBoolean) {
    CloseGuard closeGuard = this.mCloseGuard;
    if (closeGuard != null) {
      if (paramBoolean)
        closeGuard.warnIfOpen(); 
      this.mCloseGuard.close();
    } 
    if (this.mConnectionPtr != 0L) {
      int i = this.mRecentOperations.beginOperation("close", null, null);
      try {
        this.mPreparedStatementCache.evictAll();
        nativeClose(this.mConnectionPtr);
        this.mConnectionPtr = 0L;
      } finally {
        this.mRecentOperations.endOperation(i);
      } 
    } 
  }
  
  private void executePerConnectionSqlFromConfiguration(int paramInt) {
    while (paramInt < this.mConfiguration.perConnectionSql.size()) {
      Pair pair = this.mConfiguration.perConnectionSql.get(paramInt);
      int i = DatabaseUtils.getSqlStatementType((String)pair.first);
      if (i != 1) {
        if (i == 7) {
          execute((String)pair.first, (Object[])pair.second, null);
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Unsupported configuration statement: ");
          stringBuilder.append(pair);
          throw new IllegalArgumentException(stringBuilder.toString());
        } 
      } else {
        executeForString((String)pair.first, (Object[])pair.second, null);
      } 
      paramInt++;
    } 
  }
  
  private void finalizePreparedStatement(PreparedStatement paramPreparedStatement) {
    nativeFinalizeStatement(this.mConnectionPtr, paramPreparedStatement.mStatementPtr);
    recyclePreparedStatement(paramPreparedStatement);
  }
  
  private SQLiteDebug.DbStats getMainDbStatsUnsafe(int paramInt, long paramLong1, long paramLong2) {
    String str1 = this.mConfiguration.path;
    String str2 = str1;
    if (!this.mIsPrimaryConnection) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append(" (");
      stringBuilder.append(this.mConnectionId);
      stringBuilder.append(")");
      str2 = stringBuilder.toString();
    } 
    return new SQLiteDebug.DbStats(str2, paramLong1, paramLong2, paramInt, this.mPreparedStatementCache.hitCount(), this.mPreparedStatementCache.missCount(), this.mPreparedStatementCache.size());
  }
  
  private static boolean isCacheable(int paramInt) {
    return (paramInt == 2 || paramInt == 1);
  }
  
  private void maybeTruncateWalFile() {
    long l1 = SQLiteGlobal.getWALTruncateSize();
    if (l1 == 0L)
      return; 
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(this.mConfiguration.path);
    stringBuilder1.append("-wal");
    File file = new File(stringBuilder1.toString());
    if (!file.isFile())
      return; 
    long l2 = file.length();
    if (l2 < l1)
      return; 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(file.getAbsolutePath());
    stringBuilder2.append(" ");
    stringBuilder2.append(l2);
    stringBuilder2.append(" bytes: Bigger than ");
    stringBuilder2.append(l1);
    stringBuilder2.append("; truncating");
    Log.i("SQLiteConnection", stringBuilder2.toString());
    try {
      executeForString("PRAGMA wal_checkpoint(TRUNCATE)", null, null);
    } catch (SQLiteException sQLiteException) {
      Log.w("SQLiteConnection", "Failed to truncate the -wal file", (Throwable)sQLiteException);
    } 
  }
  
  private static native void nativeBindBlob(long paramLong1, long paramLong2, int paramInt, byte[] paramArrayOfbyte);
  
  private static native void nativeBindDouble(long paramLong1, long paramLong2, int paramInt, double paramDouble);
  
  private static native void nativeBindLong(long paramLong1, long paramLong2, int paramInt, long paramLong3);
  
  private static native void nativeBindNull(long paramLong1, long paramLong2, int paramInt);
  
  private static native void nativeBindString(long paramLong1, long paramLong2, int paramInt, String paramString);
  
  private static native void nativeCancel(long paramLong);
  
  private static native void nativeClose(long paramLong);
  
  private static native void nativeExecute(long paramLong1, long paramLong2);
  
  private static native int nativeExecuteForBlobFileDescriptor(long paramLong1, long paramLong2);
  
  private static native int nativeExecuteForChangedRowCount(long paramLong1, long paramLong2);
  
  private static native long nativeExecuteForCursorWindow(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2, boolean paramBoolean);
  
  private static native long nativeExecuteForLastInsertedRowId(long paramLong1, long paramLong2);
  
  private static native long nativeExecuteForLong(long paramLong1, long paramLong2);
  
  private static native String nativeExecuteForString(long paramLong1, long paramLong2);
  
  private static native void nativeFinalizeStatement(long paramLong1, long paramLong2);
  
  private static native int nativeGetColumnCount(long paramLong1, long paramLong2);
  
  private static native String nativeGetColumnName(long paramLong1, long paramLong2, int paramInt);
  
  private static native int nativeGetDbLookaside(long paramLong);
  
  private static native int nativeGetParameterCount(long paramLong1, long paramLong2);
  
  private static native boolean nativeIsReadOnly(long paramLong1, long paramLong2);
  
  private static native long nativeOpen(String paramString1, int paramInt1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3);
  
  private static native long nativePrepareStatement(long paramLong, String paramString);
  
  private static native void nativeRegisterCustomAggregateFunction(long paramLong, String paramString, BinaryOperator<String> paramBinaryOperator);
  
  private static native void nativeRegisterCustomScalarFunction(long paramLong, String paramString, UnaryOperator<String> paramUnaryOperator);
  
  private static native void nativeRegisterLocalizedCollators(long paramLong, String paramString);
  
  private static native void nativeResetCancel(long paramLong, boolean paramBoolean);
  
  private static native void nativeResetStatementAndClearBindings(long paramLong1, long paramLong2);
  
  private PreparedStatement obtainPreparedStatement(String paramString, long paramLong, int paramInt1, int paramInt2, boolean paramBoolean) {
    PreparedStatement preparedStatement = this.mPreparedStatementPool;
    if (preparedStatement != null) {
      this.mPreparedStatementPool = preparedStatement.mPoolNext;
      preparedStatement.mPoolNext = null;
      preparedStatement.mInCache = false;
    } else {
      preparedStatement = new PreparedStatement();
    } 
    preparedStatement.mSql = paramString;
    preparedStatement.mStatementPtr = paramLong;
    preparedStatement.mNumParameters = paramInt1;
    preparedStatement.mType = paramInt2;
    preparedStatement.mReadOnly = paramBoolean;
    return preparedStatement;
  }
  
  static SQLiteConnection open(SQLiteConnectionPool paramSQLiteConnectionPool, SQLiteDatabaseConfiguration paramSQLiteDatabaseConfiguration, int paramInt, boolean paramBoolean) {
    SQLiteConnection sQLiteConnection = new SQLiteConnection(paramSQLiteConnectionPool, paramSQLiteDatabaseConfiguration, paramInt, paramBoolean);
    try {
      sQLiteConnection.open();
      return sQLiteConnection;
    } catch (SQLiteException sQLiteException) {
      sQLiteConnection.dispose(false);
      throw sQLiteException;
    } 
  }
  
  private void open() {
    String str = this.mConfiguration.path;
    int i = this.mRecentOperations.beginOperation("open", null, null);
    try {
      this.mConnectionPtr = nativeOpen(str, this.mConfiguration.openFlags, this.mConfiguration.label, SQLiteDebug.NoPreloadHolder.DEBUG_SQL_STATEMENTS, SQLiteDebug.NoPreloadHolder.DEBUG_SQL_TIME, this.mConfiguration.lookasideSlotSize, this.mConfiguration.lookasideSlotCount);
      this.mRecentOperations.endOperation(i);
      setPageSize();
      setForeignKeyModeFromConfiguration();
      setWalModeFromConfiguration();
      setJournalSizeLimit();
      setAutoCheckpointInterval();
      setLocaleFromConfiguration();
      setCustomFunctionsFromConfiguration();
      executePerConnectionSqlFromConfiguration(0);
      return;
    } catch (SQLiteCantOpenDatabaseException sQLiteCantOpenDatabaseException1) {
      String str1;
      String str2 = String.format("Cannot open database '%s'", new Object[] { str });
      try {
        StringBuilder stringBuilder;
      } finally {
        Exception exception = null;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append(str2);
        stringBuilder.append(": Unknown reason; cannot examine filesystem: ");
        stringBuilder.append(exception.getMessage());
      } 
      SQLiteCantOpenDatabaseException sQLiteCantOpenDatabaseException2 = new SQLiteCantOpenDatabaseException();
      this(str1, (Throwable)sQLiteCantOpenDatabaseException1);
      throw sQLiteCantOpenDatabaseException2;
    } finally {}
    this.mRecentOperations.endOperation(i);
    throw str;
  }
  
  private void recyclePreparedStatement(PreparedStatement paramPreparedStatement) {
    paramPreparedStatement.mSql = null;
    paramPreparedStatement.mPoolNext = this.mPreparedStatementPool;
    this.mPreparedStatementPool = paramPreparedStatement;
  }
  
  private void releasePreparedStatement(PreparedStatement paramPreparedStatement) {
    paramPreparedStatement.mInUse = false;
    if (paramPreparedStatement.mInCache) {
      try {
        nativeResetStatementAndClearBindings(this.mConnectionPtr, paramPreparedStatement.mStatementPtr);
      } catch (SQLiteException sQLiteException) {
        this.mPreparedStatementCache.remove(paramPreparedStatement.mSql);
      } 
    } else {
      finalizePreparedStatement(paramPreparedStatement);
    } 
  }
  
  private void setAutoCheckpointInterval() {
    if (!this.mConfiguration.isInMemoryDb() && !this.mIsReadOnlyConnection) {
      long l = SQLiteGlobal.getWALAutoCheckpoint();
      if (executeForLong("PRAGMA wal_autocheckpoint", null, null) != l) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PRAGMA wal_autocheckpoint=");
        stringBuilder.append(l);
        executeForLong(stringBuilder.toString(), null, null);
      } 
    } 
  }
  
  private void setCustomFunctionsFromConfiguration() {
    byte b;
    for (b = 0; b < this.mConfiguration.customScalarFunctions.size(); b++)
      nativeRegisterCustomScalarFunction(this.mConnectionPtr, (String)this.mConfiguration.customScalarFunctions.keyAt(b), (UnaryOperator<String>)this.mConfiguration.customScalarFunctions.valueAt(b)); 
    for (b = 0; b < this.mConfiguration.customAggregateFunctions.size(); b++)
      nativeRegisterCustomAggregateFunction(this.mConnectionPtr, (String)this.mConfiguration.customAggregateFunctions.keyAt(b), (BinaryOperator<String>)this.mConfiguration.customAggregateFunctions.valueAt(b)); 
  }
  
  private void setForeignKeyModeFromConfiguration() {
    if (!this.mIsReadOnlyConnection) {
      long l;
      if (this.mConfiguration.foreignKeyConstraintsEnabled) {
        l = 1L;
      } else {
        l = 0L;
      } 
      if (executeForLong("PRAGMA foreign_keys", null, null) != l) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PRAGMA foreign_keys=");
        stringBuilder.append(l);
        execute(stringBuilder.toString(), null, null);
      } 
    } 
  }
  
  private void setJournalMode(String paramString) {
    String str = executeForString("PRAGMA journal_mode", null, null);
    if (!str.equalsIgnoreCase(paramString)) {
      try {
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("PRAGMA journal_mode=");
        stringBuilder1.append(paramString);
        boolean bool = executeForString(stringBuilder1.toString(), null, null).equalsIgnoreCase(paramString);
        if (bool)
          return; 
      } catch (SQLiteDatabaseLockedException sQLiteDatabaseLockedException) {}
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not change the database journal mode of '");
      stringBuilder.append(this.mConfiguration.label);
      stringBuilder.append("' from '");
      stringBuilder.append(str);
      stringBuilder.append("' to '");
      stringBuilder.append(paramString);
      stringBuilder.append("' because the database is locked.  This usually means that there are other open connections to the database which prevents the database from enabling or disabling write-ahead logging mode.  Proceeding without changing the journal mode.");
      Log.w("SQLiteConnection", stringBuilder.toString());
    } 
  }
  
  private void setJournalSizeLimit() {
    if (!this.mConfiguration.isInMemoryDb() && !this.mIsReadOnlyConnection) {
      long l = SQLiteGlobal.getJournalSizeLimit();
      if (executeForLong("PRAGMA journal_size_limit", null, null) != l) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PRAGMA journal_size_limit=");
        stringBuilder.append(l);
        executeForLong(stringBuilder.toString(), null, null);
      } 
    } 
  }
  
  private void setLocaleFromConfiguration() {
    String str1 = "COMMIT";
    if ((this.mConfiguration.openFlags & 0x10) != 0)
      return; 
    String str2 = this.mConfiguration.locale.toString();
    nativeRegisterLocalizedCollators(this.mConnectionPtr, str2);
    if (!this.mConfiguration.isInMemoryDb())
      checkDatabaseWiped(); 
    if (this.mIsReadOnlyConnection)
      return; 
    try {
      execute("CREATE TABLE IF NOT EXISTS android_metadata (locale TEXT)", null, null);
      null = executeForString("SELECT locale FROM android_metadata UNION SELECT NULL ORDER BY locale DESC LIMIT 1", null, null);
      if (null != null && null.equals(str2))
        return; 
      execute("BEGIN", null, null);
      try {
        execute("DELETE FROM android_metadata", null, null);
        execute("INSERT INTO android_metadata (locale) VALUES(?)", new Object[] { str2 }, null);
        execute("REINDEX LOCALIZED", null, null);
        return;
      } finally {
        if (!false)
          str1 = "ROLLBACK"; 
        execute(str1, null, null);
      } 
    } catch (SQLiteException sQLiteException) {
      throw sQLiteException;
    } catch (RuntimeException runtimeException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Failed to change locale for db '");
      stringBuilder.append(this.mConfiguration.label);
      stringBuilder.append("' to '");
      stringBuilder.append(str2);
      stringBuilder.append("'.");
      throw new SQLiteException(stringBuilder.toString(), runtimeException);
    } 
  }
  
  private void setPageSize() {
    if (!this.mConfiguration.isInMemoryDb() && !this.mIsReadOnlyConnection) {
      long l = SQLiteGlobal.getDefaultPageSize();
      if (executeForLong("PRAGMA page_size", null, null) != l) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PRAGMA page_size=");
        stringBuilder.append(l);
        execute(stringBuilder.toString(), null, null);
      } 
    } 
  }
  
  private void setSyncMode(String paramString) {
    if (!canonicalizeSyncMode(executeForString("PRAGMA synchronous", null, null)).equalsIgnoreCase(canonicalizeSyncMode(paramString))) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("PRAGMA synchronous=");
      stringBuilder.append(paramString);
      execute(stringBuilder.toString(), null, null);
    } 
  }
  
  private void setWalModeFromConfiguration() {
    if (!this.mConfiguration.isInMemoryDb() && !this.mIsReadOnlyConnection) {
      boolean bool;
      String str;
      if ((this.mConfiguration.openFlags & 0x20000000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      boolean bool1 = this.mConfiguration.isLegacyCompatibilityWalEnabled();
      if (bool || bool1) {
        setJournalMode("WAL");
        if (this.mConfiguration.syncMode != null) {
          setSyncMode(this.mConfiguration.syncMode);
        } else if (bool1) {
          setSyncMode(SQLiteCompatibilityWalFlags.getWALSyncMode());
        } else {
          setSyncMode(SQLiteGlobal.getWALSyncMode());
        } 
        maybeTruncateWalFile();
        return;
      } 
      if (this.mConfiguration.journalMode == null) {
        str = SQLiteGlobal.getDefaultJournalMode();
      } else {
        str = this.mConfiguration.journalMode;
      } 
      setJournalMode(str);
      if (this.mConfiguration.syncMode == null) {
        str = SQLiteGlobal.getDefaultSyncMode();
      } else {
        str = this.mConfiguration.syncMode;
      } 
      setSyncMode(str);
    } 
  }
  
  private void throwIfStatementForbidden(PreparedStatement paramPreparedStatement) {
    if (!this.mOnlyAllowReadOnlyOperations || paramPreparedStatement.mReadOnly)
      return; 
    throw new SQLiteException("Cannot execute this statement because it might modify the database but the connection is read-only.");
  }
  
  private static String trimSqlForDisplay(String paramString) {
    return paramString.replaceAll("[\\s]*\\n+[\\s]*", " ");
  }
  
  void close() {
    dispose(false);
  }
  
  void collectDbStats(ArrayList<SQLiteDebug.DbStats> paramArrayList) {
    long l2;
    String str1;
    String str2;
    SQLiteDebug.DbStats dbStats;
    int i = nativeGetDbLookaside(this.mConnectionPtr);
    long l1 = 0L;
    try {
      l2 = executeForLong("PRAGMA page_count;", null, null);
      l1 = l2;
      long l = executeForLong("PRAGMA page_size;", null, null);
      l1 = l;
    } catch (SQLiteException sQLiteException) {
      l2 = l1;
      l1 = 0L;
    } 
    paramArrayList.add(getMainDbStatsUnsafe(i, l2, l1));
    CursorWindow cursorWindow2 = new CursorWindow("collectDbStats");
    CursorWindow cursorWindow1 = cursorWindow2;
    CursorWindow cursorWindow3 = cursorWindow1;
    CursorWindow cursorWindow4 = cursorWindow1;
    try {
      executeForCursorWindow("PRAGMA database_list;", null, cursorWindow2, 0, 0, false, null);
      i = 1;
      while (true) {
        cursorWindow3 = cursorWindow1;
        cursorWindow4 = cursorWindow1;
        int j = cursorWindow1.getNumRows();
        if (i < j) {
          try {
            str2 = cursorWindow1.getString(i, 1);
            String str3 = cursorWindow1.getString(i, 2);
            l2 = 0L;
            long l = 0L;
            l1 = l2;
            try {
              StringBuilder stringBuilder1 = new StringBuilder();
              l1 = l2;
              this();
              l1 = l2;
              stringBuilder1.append("PRAGMA ");
              l1 = l2;
              stringBuilder1.append(str2);
              l1 = l2;
              stringBuilder1.append(".page_count;");
              l1 = l2;
              l2 = executeForLong(stringBuilder1.toString(), null, null);
              l1 = l2;
              stringBuilder1 = new StringBuilder();
              l1 = l2;
              this();
              l1 = l2;
              stringBuilder1.append("PRAGMA ");
              l1 = l2;
              stringBuilder1.append(str2);
              l1 = l2;
              stringBuilder1.append(".page_size;");
              l1 = l2;
              long l3 = executeForLong(stringBuilder1.toString(), null, null);
              l1 = l3;
              l = l2;
              l2 = l1;
            } catch (SQLiteException sQLiteException) {
              l2 = l;
              l = l1;
            } 
            StringBuilder stringBuilder = new StringBuilder();
            this();
            stringBuilder.append("  (attached) ");
            stringBuilder.append(str2);
            String str4 = stringBuilder.toString();
            str2 = str4;
            if (!str3.isEmpty()) {
              StringBuilder stringBuilder1 = new StringBuilder();
              this();
              stringBuilder1.append(str4);
              stringBuilder1.append(": ");
              stringBuilder1.append(str3);
              str2 = stringBuilder1.toString();
            } 
            dbStats = new SQLiteDebug.DbStats();
            this(str2, l, l2, 0, 0, 0, 0);
            paramArrayList.add(dbStats);
            i++;
            continue;
          } catch (SQLiteException sQLiteException) {
            break;
          } finally {}
          cursorWindow1.close();
          throw paramArrayList;
        } 
        break;
      } 
    } catch (SQLiteException sQLiteException) {
    
    } finally {
      paramArrayList = null;
    } 
    str1.close();
  }
  
  void collectDbStatsUnsafe(ArrayList<SQLiteDebug.DbStats> paramArrayList) {
    paramArrayList.add(getMainDbStatsUnsafe(0, 0L, 0L));
  }
  
  String describeCurrentOperationUnsafe() {
    return this.mRecentOperations.describeCurrentOperation();
  }
  
  public void dump(Printer paramPrinter, boolean paramBoolean) {
    dumpUnsafe(paramPrinter, paramBoolean);
  }
  
  void dumpUnsafe(Printer paramPrinter, boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Connection #");
    stringBuilder.append(this.mConnectionId);
    stringBuilder.append(":");
    paramPrinter.println(stringBuilder.toString());
    if (paramBoolean) {
      stringBuilder = new StringBuilder();
      stringBuilder.append("  connectionPtr: 0x");
      stringBuilder.append(Long.toHexString(this.mConnectionPtr));
      paramPrinter.println(stringBuilder.toString());
    } 
    stringBuilder = new StringBuilder();
    stringBuilder.append("  isPrimaryConnection: ");
    stringBuilder.append(this.mIsPrimaryConnection);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append("  onlyAllowReadOnlyOperations: ");
    stringBuilder.append(this.mOnlyAllowReadOnlyOperations);
    paramPrinter.println(stringBuilder.toString());
    this.mRecentOperations.dump(paramPrinter);
    if (paramBoolean)
      this.mPreparedStatementCache.dump(paramPrinter); 
  }
  
  public void execute(String paramString, Object[] paramArrayOfObject, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      int i = this.mRecentOperations.beginOperation("execute", paramString, paramArrayOfObject);
      try {
        PreparedStatement preparedStatement = acquirePreparedStatement(paramString);
        try {
          throwIfStatementForbidden(preparedStatement);
          bindArguments(preparedStatement, paramArrayOfObject);
          applyBlockGuardPolicy(preparedStatement);
          attachCancellationSignal(paramCancellationSignal);
        } finally {
          releasePreparedStatement(preparedStatement);
        } 
      } catch (RuntimeException runtimeException) {
        this.mRecentOperations.failOperation(i, runtimeException);
        throw runtimeException;
      } finally {}
      this.mRecentOperations.endOperation(i);
      throw paramString;
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public ParcelFileDescriptor executeForBlobFileDescriptor(String paramString, Object[] paramArrayOfObject, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      int i = this.mRecentOperations.beginOperation("executeForBlobFileDescriptor", paramString, paramArrayOfObject);
      try {
        PreparedStatement preparedStatement = acquirePreparedStatement(paramString);
        try {
          throwIfStatementForbidden(preparedStatement);
          bindArguments(preparedStatement, paramArrayOfObject);
          applyBlockGuardPolicy(preparedStatement);
          attachCancellationSignal(paramCancellationSignal);
        } finally {
          releasePreparedStatement(preparedStatement);
        } 
      } catch (RuntimeException runtimeException) {
        this.mRecentOperations.failOperation(i, runtimeException);
        throw runtimeException;
      } finally {}
      this.mRecentOperations.endOperation(i);
      throw paramString;
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public int executeForChangedRowCount(String paramString, Object[] paramArrayOfObject, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      int i = 0;
      int j = 0;
      int k = 0;
      int m = this.mRecentOperations.beginOperation("executeForChangedRowCount", paramString, paramArrayOfObject);
      try {
        OperationLog operationLog;
        PreparedStatement preparedStatement = acquirePreparedStatement(paramString);
        int n = k;
        try {
          throwIfStatementForbidden(preparedStatement);
          n = k;
          bindArguments(preparedStatement, paramArrayOfObject);
        } finally {
          i = n;
          j = n;
          releasePreparedStatement((PreparedStatement)operationLog);
          i = n;
          j = n;
        } 
      } catch (RuntimeException runtimeException) {
        i = j;
        this.mRecentOperations.failOperation(m, runtimeException);
        i = j;
        throw runtimeException;
      } finally {}
      if (this.mRecentOperations.endOperationDeferLog(m)) {
        OperationLog operationLog = this.mRecentOperations;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("changedRows=");
        stringBuilder.append(i);
        operationLog.logOperation(m, stringBuilder.toString());
      } 
      throw paramString;
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public int executeForCursorWindow(String paramString, Object[] paramArrayOfObject, CursorWindow paramCursorWindow, int paramInt1, int paramInt2, boolean paramBoolean, CancellationSignal paramCancellationSignal) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 570
    //   4: aload_3
    //   5: ifnull -> 559
    //   8: aload_3
    //   9: invokevirtual acquireReference : ()V
    //   12: iconst_m1
    //   13: istore #8
    //   15: iconst_m1
    //   16: istore #9
    //   18: iconst_m1
    //   19: istore #10
    //   21: aload_0
    //   22: getfield mRecentOperations : Landroid/database/sqlite/SQLiteConnection$OperationLog;
    //   25: ldc_w 'executeForCursorWindow'
    //   28: aload_1
    //   29: aload_2
    //   30: invokevirtual beginOperation : (Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
    //   33: istore #11
    //   35: aload_0
    //   36: aload_1
    //   37: invokespecial acquirePreparedStatement : (Ljava/lang/String;)Landroid/database/sqlite/SQLiteConnection$PreparedStatement;
    //   40: astore #12
    //   42: aload_0
    //   43: aload #12
    //   45: invokespecial throwIfStatementForbidden : (Landroid/database/sqlite/SQLiteConnection$PreparedStatement;)V
    //   48: aload_0
    //   49: aload #12
    //   51: aload_2
    //   52: invokespecial bindArguments : (Landroid/database/sqlite/SQLiteConnection$PreparedStatement;[Ljava/lang/Object;)V
    //   55: aload_0
    //   56: aload #12
    //   58: invokespecial applyBlockGuardPolicy : (Landroid/database/sqlite/SQLiteConnection$PreparedStatement;)V
    //   61: aload_0
    //   62: aload #7
    //   64: invokespecial attachCancellationSignal : (Landroid/os/CancellationSignal;)V
    //   67: aload_0
    //   68: getfield mConnectionPtr : J
    //   71: lstore #13
    //   73: aload #12
    //   75: getfield mStatementPtr : J
    //   78: lstore #15
    //   80: aload_3
    //   81: getfield mWindowPtr : J
    //   84: lstore #17
    //   86: lload #13
    //   88: lload #15
    //   90: lload #17
    //   92: iload #4
    //   94: iload #5
    //   96: iload #6
    //   98: invokestatic nativeExecuteForCursorWindow : (JJJIIZ)J
    //   101: lstore #17
    //   103: lload #17
    //   105: bipush #32
    //   107: lshr
    //   108: l2i
    //   109: istore #8
    //   111: lload #17
    //   113: l2i
    //   114: istore #9
    //   116: aload_3
    //   117: invokevirtual getNumRows : ()I
    //   120: istore #5
    //   122: aload_3
    //   123: iload #8
    //   125: invokevirtual setStartPosition : (I)V
    //   128: aload_0
    //   129: aload #7
    //   131: invokespecial detachCancellationSignal : (Landroid/os/CancellationSignal;)V
    //   134: aload_0
    //   135: aload #12
    //   137: invokespecial releasePreparedStatement : (Landroid/database/sqlite/SQLiteConnection$PreparedStatement;)V
    //   140: aload_0
    //   141: getfield mRecentOperations : Landroid/database/sqlite/SQLiteConnection$OperationLog;
    //   144: iload #11
    //   146: invokevirtual endOperationDeferLog : (I)Z
    //   149: ifeq -> 252
    //   152: aload_0
    //   153: getfield mRecentOperations : Landroid/database/sqlite/SQLiteConnection$OperationLog;
    //   156: astore_2
    //   157: new java/lang/StringBuilder
    //   160: astore_1
    //   161: aload_1
    //   162: invokespecial <init> : ()V
    //   165: aload_1
    //   166: ldc_w 'window=''
    //   169: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: pop
    //   173: aload_1
    //   174: aload_3
    //   175: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   178: pop
    //   179: aload_1
    //   180: ldc_w '', startPos='
    //   183: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: pop
    //   187: aload_1
    //   188: iload #4
    //   190: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   193: pop
    //   194: aload_1
    //   195: ldc_w ', actualPos='
    //   198: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: pop
    //   202: aload_1
    //   203: iload #8
    //   205: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   208: pop
    //   209: aload_1
    //   210: ldc_w ', filledRows='
    //   213: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: pop
    //   217: aload_1
    //   218: iload #5
    //   220: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   223: pop
    //   224: aload_1
    //   225: ldc_w ', countedRows='
    //   228: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: pop
    //   232: aload_1
    //   233: iload #9
    //   235: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   238: pop
    //   239: aload_2
    //   240: iload #11
    //   242: aload_1
    //   243: invokevirtual toString : ()Ljava/lang/String;
    //   246: invokevirtual logOperation : (ILjava/lang/String;)V
    //   249: goto -> 252
    //   252: aload_3
    //   253: invokevirtual releaseReference : ()V
    //   256: iload #9
    //   258: ireturn
    //   259: astore_1
    //   260: goto -> 553
    //   263: astore_1
    //   264: goto -> 424
    //   267: astore_1
    //   268: goto -> 375
    //   271: astore_1
    //   272: goto -> 321
    //   275: astore_1
    //   276: goto -> 304
    //   279: astore_1
    //   280: iload #10
    //   282: istore #5
    //   284: goto -> 304
    //   287: astore_1
    //   288: iload #10
    //   290: istore #5
    //   292: goto -> 304
    //   295: astore_1
    //   296: goto -> 300
    //   299: astore_1
    //   300: iload #10
    //   302: istore #5
    //   304: aload_0
    //   305: aload #7
    //   307: invokespecial detachCancellationSignal : (Landroid/os/CancellationSignal;)V
    //   310: aload_1
    //   311: athrow
    //   312: astore_1
    //   313: goto -> 321
    //   316: astore_1
    //   317: iload #10
    //   319: istore #5
    //   321: iload #8
    //   323: istore #19
    //   325: iload #9
    //   327: istore #20
    //   329: iload #5
    //   331: istore #10
    //   333: aload_0
    //   334: aload #12
    //   336: invokespecial releasePreparedStatement : (Landroid/database/sqlite/SQLiteConnection$PreparedStatement;)V
    //   339: iload #8
    //   341: istore #19
    //   343: iload #9
    //   345: istore #20
    //   347: iload #5
    //   349: istore #10
    //   351: aload_1
    //   352: athrow
    //   353: astore_1
    //   354: goto -> 375
    //   357: astore_1
    //   358: iconst_m1
    //   359: istore #8
    //   361: iconst_m1
    //   362: istore #9
    //   364: iconst_m1
    //   365: istore #5
    //   367: goto -> 424
    //   370: astore_1
    //   371: iload #10
    //   373: istore #5
    //   375: iload #8
    //   377: istore #19
    //   379: iload #9
    //   381: istore #20
    //   383: iload #5
    //   385: istore #10
    //   387: aload_0
    //   388: getfield mRecentOperations : Landroid/database/sqlite/SQLiteConnection$OperationLog;
    //   391: iload #11
    //   393: aload_1
    //   394: invokevirtual failOperation : (ILjava/lang/Exception;)V
    //   397: iload #8
    //   399: istore #19
    //   401: iload #9
    //   403: istore #20
    //   405: iload #5
    //   407: istore #10
    //   409: aload_1
    //   410: athrow
    //   411: astore_1
    //   412: iload #19
    //   414: istore #8
    //   416: iload #20
    //   418: istore #9
    //   420: iload #10
    //   422: istore #5
    //   424: aload_0
    //   425: getfield mRecentOperations : Landroid/database/sqlite/SQLiteConnection$OperationLog;
    //   428: iload #11
    //   430: invokevirtual endOperationDeferLog : (I)Z
    //   433: ifeq -> 546
    //   436: aload_0
    //   437: getfield mRecentOperations : Landroid/database/sqlite/SQLiteConnection$OperationLog;
    //   440: astore_2
    //   441: new java/lang/StringBuilder
    //   444: astore #7
    //   446: aload #7
    //   448: invokespecial <init> : ()V
    //   451: aload #7
    //   453: ldc_w 'window=''
    //   456: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: pop
    //   460: aload #7
    //   462: aload_3
    //   463: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   466: pop
    //   467: aload #7
    //   469: ldc_w '', startPos='
    //   472: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   475: pop
    //   476: aload #7
    //   478: iload #4
    //   480: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   483: pop
    //   484: aload #7
    //   486: ldc_w ', actualPos='
    //   489: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   492: pop
    //   493: aload #7
    //   495: iload #8
    //   497: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   500: pop
    //   501: aload #7
    //   503: ldc_w ', filledRows='
    //   506: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   509: pop
    //   510: aload #7
    //   512: iload #5
    //   514: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   517: pop
    //   518: aload #7
    //   520: ldc_w ', countedRows='
    //   523: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   526: pop
    //   527: aload #7
    //   529: iload #9
    //   531: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   534: pop
    //   535: aload_2
    //   536: iload #11
    //   538: aload #7
    //   540: invokevirtual toString : ()Ljava/lang/String;
    //   543: invokevirtual logOperation : (ILjava/lang/String;)V
    //   546: aload_1
    //   547: athrow
    //   548: astore_1
    //   549: goto -> 553
    //   552: astore_1
    //   553: aload_3
    //   554: invokevirtual releaseReference : ()V
    //   557: aload_1
    //   558: athrow
    //   559: new java/lang/IllegalArgumentException
    //   562: dup
    //   563: ldc_w 'window must not be null.'
    //   566: invokespecial <init> : (Ljava/lang/String;)V
    //   569: athrow
    //   570: new java/lang/IllegalArgumentException
    //   573: dup
    //   574: ldc_w 'sql must not be null.'
    //   577: invokespecial <init> : (Ljava/lang/String;)V
    //   580: athrow
    // Exception table:
    //   from	to	target	type
    //   21	35	552	finally
    //   35	42	370	java/lang/RuntimeException
    //   35	42	357	finally
    //   42	67	316	finally
    //   67	80	299	finally
    //   80	86	295	finally
    //   86	103	287	finally
    //   116	122	279	finally
    //   122	128	275	finally
    //   128	134	271	finally
    //   134	140	267	java/lang/RuntimeException
    //   134	140	263	finally
    //   140	187	259	finally
    //   187	249	548	finally
    //   304	310	312	finally
    //   310	312	312	finally
    //   333	339	353	java/lang/RuntimeException
    //   333	339	411	finally
    //   351	353	353	java/lang/RuntimeException
    //   351	353	411	finally
    //   387	397	411	finally
    //   409	411	411	finally
    //   424	546	548	finally
    //   546	548	548	finally
  }
  
  public long executeForLastInsertedRowId(String paramString, Object[] paramArrayOfObject, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      int i = this.mRecentOperations.beginOperation("executeForLastInsertedRowId", paramString, paramArrayOfObject);
      try {
        PreparedStatement preparedStatement = acquirePreparedStatement(paramString);
        try {
          throwIfStatementForbidden(preparedStatement);
          bindArguments(preparedStatement, paramArrayOfObject);
          applyBlockGuardPolicy(preparedStatement);
          attachCancellationSignal(paramCancellationSignal);
        } finally {
          releasePreparedStatement(preparedStatement);
        } 
      } catch (RuntimeException runtimeException) {
        this.mRecentOperations.failOperation(i, runtimeException);
        throw runtimeException;
      } finally {}
      this.mRecentOperations.endOperation(i);
      throw paramString;
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public long executeForLong(String paramString, Object[] paramArrayOfObject, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      int i = this.mRecentOperations.beginOperation("executeForLong", paramString, paramArrayOfObject);
      try {
        PreparedStatement preparedStatement = acquirePreparedStatement(paramString);
        try {
          throwIfStatementForbidden(preparedStatement);
          bindArguments(preparedStatement, paramArrayOfObject);
          applyBlockGuardPolicy(preparedStatement);
          attachCancellationSignal(paramCancellationSignal);
        } finally {
          releasePreparedStatement(preparedStatement);
        } 
      } catch (RuntimeException runtimeException) {
        this.mRecentOperations.failOperation(i, runtimeException);
        throw runtimeException;
      } finally {}
      this.mRecentOperations.endOperation(i);
      throw paramString;
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  public String executeForString(String paramString, Object[] paramArrayOfObject, CancellationSignal paramCancellationSignal) {
    if (paramString != null) {
      int i = this.mRecentOperations.beginOperation("executeForString", paramString, paramArrayOfObject);
      try {
        PreparedStatement preparedStatement = acquirePreparedStatement(paramString);
        try {
          throwIfStatementForbidden(preparedStatement);
          bindArguments(preparedStatement, paramArrayOfObject);
          applyBlockGuardPolicy(preparedStatement);
          attachCancellationSignal(paramCancellationSignal);
        } finally {
          releasePreparedStatement(preparedStatement);
        } 
      } catch (RuntimeException runtimeException) {
        this.mRecentOperations.failOperation(i, runtimeException);
        throw runtimeException;
      } finally {}
      this.mRecentOperations.endOperation(i);
      throw paramString;
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  protected void finalize() throws Throwable {
    try {
      if (this.mPool != null && this.mConnectionPtr != 0L)
        this.mPool.onConnectionLeaked(); 
      dispose(true);
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public int getConnectionId() {
    return this.mConnectionId;
  }
  
  boolean isPreparedStatementInCache(String paramString) {
    boolean bool;
    if (this.mPreparedStatementCache.get(paramString) != null) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean isPrimaryConnection() {
    return this.mIsPrimaryConnection;
  }
  
  public void onCancel() {
    nativeCancel(this.mConnectionPtr);
  }
  
  public void prepare(String paramString, SQLiteStatementInfo paramSQLiteStatementInfo) {
    if (paramString != null) {
      int i = this.mRecentOperations.beginOperation("prepare", paramString, null);
      try {
        PreparedStatement preparedStatement = acquirePreparedStatement(paramString);
        if (paramSQLiteStatementInfo != null)
          try {
            paramSQLiteStatementInfo.numParameters = preparedStatement.mNumParameters;
            paramSQLiteStatementInfo.readOnly = preparedStatement.mReadOnly;
            int j = nativeGetColumnCount(this.mConnectionPtr, preparedStatement.mStatementPtr);
          } finally {
            releasePreparedStatement(preparedStatement);
          }  
        releasePreparedStatement(preparedStatement);
        this.mRecentOperations.endOperation(i);
        return;
      } catch (RuntimeException runtimeException) {
        this.mRecentOperations.failOperation(i, runtimeException);
        throw runtimeException;
      } finally {}
      this.mRecentOperations.endOperation(i);
      throw paramString;
    } 
    throw new IllegalArgumentException("sql must not be null.");
  }
  
  void reconfigure(SQLiteDatabaseConfiguration paramSQLiteDatabaseConfiguration) {
    boolean bool2;
    boolean bool3;
    boolean bool1 = false;
    this.mOnlyAllowReadOnlyOperations = false;
    if (paramSQLiteDatabaseConfiguration.foreignKeyConstraintsEnabled != this.mConfiguration.foreignKeyConstraintsEnabled) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (((paramSQLiteDatabaseConfiguration.openFlags ^ this.mConfiguration.openFlags) & 0xA0000000) != 0) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    boolean bool4 = paramSQLiteDatabaseConfiguration.locale.equals(this.mConfiguration.locale);
    boolean bool5 = paramSQLiteDatabaseConfiguration.customScalarFunctions.equals(this.mConfiguration.customScalarFunctions);
    boolean bool6 = paramSQLiteDatabaseConfiguration.customAggregateFunctions.equals(this.mConfiguration.customAggregateFunctions);
    int i = this.mConfiguration.perConnectionSql.size();
    if (paramSQLiteDatabaseConfiguration.perConnectionSql.size() > i)
      bool1 = true; 
    this.mConfiguration.updateParametersFrom(paramSQLiteDatabaseConfiguration);
    this.mPreparedStatementCache.resize(paramSQLiteDatabaseConfiguration.maxSqlCacheSize);
    if (bool2)
      setForeignKeyModeFromConfiguration(); 
    if (bool3)
      setWalModeFromConfiguration(); 
    if ((bool4 ^ true) != 0)
      setLocaleFromConfiguration(); 
    if ((bool5 ^ true) != 0 || (bool6 ^ true) != 0)
      setCustomFunctionsFromConfiguration(); 
    if (bool1)
      executePerConnectionSqlFromConfiguration(i); 
  }
  
  void setOnlyAllowReadOnlyOperations(boolean paramBoolean) {
    this.mOnlyAllowReadOnlyOperations = paramBoolean;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SQLiteConnection: ");
    stringBuilder.append(this.mConfiguration.path);
    stringBuilder.append(" (");
    stringBuilder.append(this.mConnectionId);
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  private static final class Operation {
    private static final int MAX_TRACE_METHOD_NAME_LEN = 256;
    
    public ArrayList<Object> mBindArgs;
    
    public int mCookie;
    
    public long mEndTime;
    
    public Exception mException;
    
    public boolean mFinished;
    
    public String mKind;
    
    public String mPath;
    
    public long mResultLong;
    
    public String mResultString;
    
    public String mSql;
    
    public long mStartTime;
    
    public long mStartWallTime;
    
    private Operation() {}
    
    private String getStatus() {
      String str;
      if (!this.mFinished)
        return "running"; 
      if (this.mException != null) {
        str = "failed";
      } else {
        str = "succeeded";
      } 
      return str;
    }
    
    private String getTraceMethodName() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.mKind);
      stringBuilder.append(" ");
      stringBuilder.append(this.mSql);
      String str = stringBuilder.toString();
      return (str.length() > 256) ? str.substring(0, 256) : str;
    }
    
    public void describe(StringBuilder param1StringBuilder, boolean param1Boolean) {
      // Byte code:
      //   0: aload_1
      //   1: aload_0
      //   2: getfield mKind : Ljava/lang/String;
      //   5: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   8: pop
      //   9: aload_0
      //   10: getfield mFinished : Z
      //   13: ifeq -> 47
      //   16: aload_1
      //   17: ldc ' took '
      //   19: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   22: pop
      //   23: aload_1
      //   24: aload_0
      //   25: getfield mEndTime : J
      //   28: aload_0
      //   29: getfield mStartTime : J
      //   32: lsub
      //   33: invokevirtual append : (J)Ljava/lang/StringBuilder;
      //   36: pop
      //   37: aload_1
      //   38: ldc 'ms'
      //   40: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   43: pop
      //   44: goto -> 74
      //   47: aload_1
      //   48: ldc ' started '
      //   50: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   53: pop
      //   54: aload_1
      //   55: invokestatic currentTimeMillis : ()J
      //   58: aload_0
      //   59: getfield mStartWallTime : J
      //   62: lsub
      //   63: invokevirtual append : (J)Ljava/lang/StringBuilder;
      //   66: pop
      //   67: aload_1
      //   68: ldc 'ms ago'
      //   70: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   73: pop
      //   74: aload_1
      //   75: ldc ' - '
      //   77: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   80: pop
      //   81: aload_1
      //   82: aload_0
      //   83: invokespecial getStatus : ()Ljava/lang/String;
      //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   89: pop
      //   90: aload_0
      //   91: getfield mSql : Ljava/lang/String;
      //   94: ifnull -> 123
      //   97: aload_1
      //   98: ldc ', sql="'
      //   100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   103: pop
      //   104: aload_1
      //   105: aload_0
      //   106: getfield mSql : Ljava/lang/String;
      //   109: invokestatic access$200 : (Ljava/lang/String;)Ljava/lang/String;
      //   112: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   115: pop
      //   116: aload_1
      //   117: ldc '"'
      //   119: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   122: pop
      //   123: iload_2
      //   124: ifeq -> 155
      //   127: getstatic android/database/sqlite/SQLiteDebug$NoPreloadHolder.DEBUG_LOG_DETAILED : Z
      //   130: ifeq -> 155
      //   133: aload_0
      //   134: getfield mBindArgs : Ljava/util/ArrayList;
      //   137: astore_3
      //   138: aload_3
      //   139: ifnull -> 155
      //   142: aload_3
      //   143: invokevirtual size : ()I
      //   146: ifeq -> 155
      //   149: iconst_1
      //   150: istore #4
      //   152: goto -> 158
      //   155: iconst_0
      //   156: istore #4
      //   158: iload #4
      //   160: ifeq -> 294
      //   163: aload_1
      //   164: ldc ', bindArgs=['
      //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   169: pop
      //   170: aload_0
      //   171: getfield mBindArgs : Ljava/util/ArrayList;
      //   174: invokevirtual size : ()I
      //   177: istore #5
      //   179: iconst_0
      //   180: istore #4
      //   182: iload #4
      //   184: iload #5
      //   186: if_icmpge -> 287
      //   189: aload_0
      //   190: getfield mBindArgs : Ljava/util/ArrayList;
      //   193: iload #4
      //   195: invokevirtual get : (I)Ljava/lang/Object;
      //   198: astore_3
      //   199: iload #4
      //   201: ifeq -> 211
      //   204: aload_1
      //   205: ldc ', '
      //   207: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   210: pop
      //   211: aload_3
      //   212: ifnonnull -> 225
      //   215: aload_1
      //   216: ldc 'null'
      //   218: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   221: pop
      //   222: goto -> 281
      //   225: aload_3
      //   226: instanceof [B
      //   229: ifeq -> 242
      //   232: aload_1
      //   233: ldc '<byte[]>'
      //   235: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   238: pop
      //   239: goto -> 281
      //   242: aload_3
      //   243: instanceof java/lang/String
      //   246: ifeq -> 275
      //   249: aload_1
      //   250: ldc '"'
      //   252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   255: pop
      //   256: aload_1
      //   257: aload_3
      //   258: checkcast java/lang/String
      //   261: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   264: pop
      //   265: aload_1
      //   266: ldc '"'
      //   268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   271: pop
      //   272: goto -> 281
      //   275: aload_1
      //   276: aload_3
      //   277: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   280: pop
      //   281: iinc #4, 1
      //   284: goto -> 182
      //   287: aload_1
      //   288: ldc ']'
      //   290: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   293: pop
      //   294: aload_1
      //   295: ldc ', path='
      //   297: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   300: pop
      //   301: aload_1
      //   302: aload_0
      //   303: getfield mPath : Ljava/lang/String;
      //   306: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   309: pop
      //   310: aload_0
      //   311: getfield mException : Ljava/lang/Exception;
      //   314: ifnull -> 343
      //   317: aload_1
      //   318: ldc ', exception="'
      //   320: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   323: pop
      //   324: aload_1
      //   325: aload_0
      //   326: getfield mException : Ljava/lang/Exception;
      //   329: invokevirtual getMessage : ()Ljava/lang/String;
      //   332: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   335: pop
      //   336: aload_1
      //   337: ldc '"'
      //   339: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   342: pop
      //   343: aload_0
      //   344: getfield mResultLong : J
      //   347: ldc2_w -9223372036854775808
      //   350: lcmp
      //   351: ifeq -> 370
      //   354: aload_1
      //   355: ldc ', result='
      //   357: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   360: pop
      //   361: aload_1
      //   362: aload_0
      //   363: getfield mResultLong : J
      //   366: invokevirtual append : (J)Ljava/lang/StringBuilder;
      //   369: pop
      //   370: aload_0
      //   371: getfield mResultString : Ljava/lang/String;
      //   374: ifnull -> 400
      //   377: aload_1
      //   378: ldc ', result="'
      //   380: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   383: pop
      //   384: aload_1
      //   385: aload_0
      //   386: getfield mResultString : Ljava/lang/String;
      //   389: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   392: pop
      //   393: aload_1
      //   394: ldc '"'
      //   396: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   399: pop
      //   400: return
    }
  }
  
  private static final class OperationLog {
    private static final int COOKIE_GENERATION_SHIFT = 8;
    
    private static final int COOKIE_INDEX_MASK = 255;
    
    private static final int MAX_RECENT_OPERATIONS = 20;
    
    private int mGeneration;
    
    private int mIndex;
    
    private final SQLiteConnection.Operation[] mOperations = new SQLiteConnection.Operation[20];
    
    private final SQLiteConnectionPool mPool;
    
    private long mResultLong = Long.MIN_VALUE;
    
    private String mResultString;
    
    OperationLog(SQLiteConnectionPool param1SQLiteConnectionPool) {
      this.mPool = param1SQLiteConnectionPool;
    }
    
    private boolean endOperationDeferLogLocked(int param1Int) {
      SQLiteConnection.Operation operation = getOperationLocked(param1Int);
      boolean bool = false;
      if (operation != null) {
        if (Trace.isTagEnabled(1048576L))
          Trace.asyncTraceEnd(1048576L, operation.getTraceMethodName(), operation.mCookie); 
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
    
    private SQLiteConnection.Operation getOperationLocked(int param1Int) {
      SQLiteConnection.Operation operation = this.mOperations[param1Int & 0xFF];
      if (operation.mCookie != param1Int)
        operation = null; 
      return operation;
    }
    
    private void logOperationLocked(int param1Int, String param1String) {
      SQLiteConnection.Operation operation = getOperationLocked(param1Int);
      operation.mResultLong = this.mResultLong;
      operation.mResultString = this.mResultString;
      StringBuilder stringBuilder = new StringBuilder();
      operation.describe(stringBuilder, true);
      if (param1String != null) {
        stringBuilder.append(", ");
        stringBuilder.append(param1String);
      } 
      Log.d("SQLiteConnection", stringBuilder.toString());
    }
    
    private int newOperationCookieLocked(int param1Int) {
      int i = this.mGeneration;
      this.mGeneration = i + 1;
      return i << 8 | param1Int;
    }
    
    public int beginOperation(String param1String1, String param1String2, Object[] param1ArrayOfObject) {
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
        operation2.mKind = param1String1;
        operation2.mSql = param1String2;
        operation2.mPath = this.mPool.getPath();
        operation2.mResultLong = Long.MIN_VALUE;
        operation2.mResultString = null;
        if (param1ArrayOfObject != null) {
          if (operation2.mBindArgs == null) {
            ArrayList<Object> arrayList = new ArrayList();
            this();
            operation2.mBindArgs = arrayList;
          } else {
            operation2.mBindArgs.clear();
          } 
          for (byte b = 0; b < param1ArrayOfObject.length; b++) {
            Object object = param1ArrayOfObject[b];
            if (object != null && object instanceof byte[]) {
              operation2.mBindArgs.add(SQLiteConnection.EMPTY_BYTE_ARRAY);
            } else {
              operation2.mBindArgs.add(object);
            } 
          } 
        } 
        operation2.mCookie = newOperationCookieLocked(i);
        if (Trace.isTagEnabled(1048576L))
          Trace.asyncTraceBegin(1048576L, operation2.getTraceMethodName(), operation2.mCookie); 
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
    
    public void dump(Printer param1Printer) {
      synchronized (this.mOperations) {
        param1Printer.println("  Most recently executed operations:");
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
            param1Printer.println(stringBuilder.toString());
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
          param1Printer.println("    <none>");
        } 
        return;
      } 
    }
    
    public void endOperation(int param1Int) {
      synchronized (this.mOperations) {
        if (endOperationDeferLogLocked(param1Int))
          logOperationLocked(param1Int, null); 
        return;
      } 
    }
    
    public boolean endOperationDeferLog(int param1Int) {
      synchronized (this.mOperations) {
        return endOperationDeferLogLocked(param1Int);
      } 
    }
    
    public void failOperation(int param1Int, Exception param1Exception) {
      synchronized (this.mOperations) {
        SQLiteConnection.Operation operation = getOperationLocked(param1Int);
        if (operation != null)
          operation.mException = param1Exception; 
        return;
      } 
    }
    
    public void logOperation(int param1Int, String param1String) {
      synchronized (this.mOperations) {
        logOperationLocked(param1Int, param1String);
        return;
      } 
    }
    
    public void setResult(long param1Long) {
      this.mResultLong = param1Long;
    }
    
    public void setResult(String param1String) {
      this.mResultString = param1String;
    }
  }
  
  private static final class PreparedStatement {
    public boolean mInCache;
    
    public boolean mInUse;
    
    public int mNumParameters;
    
    public PreparedStatement mPoolNext;
    
    public boolean mReadOnly;
    
    public String mSql;
    
    public long mStatementPtr;
    
    public int mType;
    
    private PreparedStatement() {}
  }
  
  private final class PreparedStatementCache extends LruCache<String, PreparedStatement> {
    public PreparedStatementCache(int param1Int) {
      super(param1Int);
    }
    
    public void dump(Printer param1Printer) {
      param1Printer.println("  Prepared statement cache:");
      Map map = snapshot();
      if (!map.isEmpty()) {
        byte b = 0;
        for (Map.Entry entry : map.entrySet()) {
          SQLiteConnection.PreparedStatement preparedStatement = (SQLiteConnection.PreparedStatement)entry.getValue();
          if (preparedStatement.mInCache) {
            String str = (String)entry.getKey();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("    ");
            stringBuilder.append(b);
            stringBuilder.append(": statementPtr=0x");
            stringBuilder.append(Long.toHexString(preparedStatement.mStatementPtr));
            stringBuilder.append(", numParameters=");
            stringBuilder.append(preparedStatement.mNumParameters);
            stringBuilder.append(", type=");
            stringBuilder.append(preparedStatement.mType);
            stringBuilder.append(", readOnly=");
            stringBuilder.append(preparedStatement.mReadOnly);
            stringBuilder.append(", sql=\"");
            stringBuilder.append(SQLiteConnection.trimSqlForDisplay(str));
            stringBuilder.append("\"");
            param1Printer.println(stringBuilder.toString());
          } 
          b++;
        } 
      } else {
        param1Printer.println("    <none>");
      } 
    }
    
    protected void entryRemoved(boolean param1Boolean, String param1String, SQLiteConnection.PreparedStatement param1PreparedStatement1, SQLiteConnection.PreparedStatement param1PreparedStatement2) {
      param1PreparedStatement1.mInCache = false;
      if (!param1PreparedStatement1.mInUse)
        SQLiteConnection.this.finalizePreparedStatement(param1PreparedStatement1); 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */