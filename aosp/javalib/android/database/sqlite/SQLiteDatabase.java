package android.database.sqlite;

import android.app.ActivityManager;
import android.app.ActivityThread;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.DefaultDatabaseErrorHandler;
import android.database.SQLException;
import android.os.CancellationSignal;
import android.os.Looper;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.EventLog;
import android.util.Log;
import android.util.Pair;
import android.util.Printer;
import com.android.internal.util.Preconditions;
import dalvik.system.CloseGuard;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public final class SQLiteDatabase extends SQLiteClosable {
  public static final int CONFLICT_ABORT = 2;
  
  public static final int CONFLICT_FAIL = 3;
  
  public static final int CONFLICT_IGNORE = 4;
  
  public static final int CONFLICT_NONE = 0;
  
  public static final int CONFLICT_REPLACE = 5;
  
  public static final int CONFLICT_ROLLBACK = 1;
  
  public static final String[] CONFLICT_VALUES;
  
  public static final int CREATE_IF_NECESSARY = 268435456;
  
  private static final boolean DEBUG_CLOSE_IDLE_CONNECTIONS = SystemProperties.getBoolean("persist.debug.sqlite.close_idle_connections", false);
  
  public static final int ENABLE_LEGACY_COMPATIBILITY_WAL = -2147483648;
  
  public static final int ENABLE_WRITE_AHEAD_LOGGING = 536870912;
  
  private static final int EVENT_DB_CORRUPT = 75004;
  
  public static final int MAX_SQL_CACHE_SIZE = 100;
  
  public static final int NO_LOCALIZED_COLLATORS = 16;
  
  public static final int OPEN_READONLY = 1;
  
  public static final int OPEN_READWRITE = 0;
  
  private static final int OPEN_READ_MASK = 1;
  
  public static final int SQLITE_MAX_LIKE_PATTERN_LENGTH = 50000;
  
  private static final String TAG = "SQLiteDatabase";
  
  private static WeakHashMap<SQLiteDatabase, Object> sActiveDatabases = new WeakHashMap<>();
  
  private final CloseGuard mCloseGuardLocked;
  
  private final SQLiteDatabaseConfiguration mConfigurationLocked;
  
  private SQLiteConnectionPool mConnectionPoolLocked;
  
  private final CursorFactory mCursorFactory;
  
  private final DatabaseErrorHandler mErrorHandler;
  
  private boolean mHasAttachedDbsLocked;
  
  private final Object mLock;
  
  private final ThreadLocal<SQLiteSession> mThreadSession;
  
  static {
    CONFLICT_VALUES = new String[] { "", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE " };
  }
  
  private SQLiteDatabase(String paramString1, int paramInt1, CursorFactory paramCursorFactory, DatabaseErrorHandler paramDatabaseErrorHandler, int paramInt2, int paramInt3, long paramLong, String paramString2, String paramString3) {
    DefaultDatabaseErrorHandler defaultDatabaseErrorHandler;
    this.mThreadSession = ThreadLocal.withInitial(new _$$Lambda$RBWjWVyGrOTsQrLCYzJ_G8Uk25Q(this));
    this.mLock = new Object();
    this.mCloseGuardLocked = CloseGuard.get();
    this.mCursorFactory = paramCursorFactory;
    if (paramDatabaseErrorHandler == null)
      defaultDatabaseErrorHandler = new DefaultDatabaseErrorHandler(); 
    this.mErrorHandler = (DatabaseErrorHandler)defaultDatabaseErrorHandler;
    SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = new SQLiteDatabaseConfiguration(paramString1, paramInt1);
    this.mConfigurationLocked = sQLiteDatabaseConfiguration;
    sQLiteDatabaseConfiguration.lookasideSlotSize = paramInt2;
    this.mConfigurationLocked.lookasideSlotCount = paramInt3;
    if (ActivityManager.isLowRamDeviceStatic()) {
      this.mConfigurationLocked.lookasideSlotCount = 0;
      this.mConfigurationLocked.lookasideSlotSize = 0;
    } 
    long l1 = Long.MAX_VALUE;
    long l2 = l1;
    if (!this.mConfigurationLocked.isInMemoryDb())
      if (paramLong >= 0L) {
        l2 = paramLong;
      } else {
        l2 = l1;
        if (DEBUG_CLOSE_IDLE_CONNECTIONS)
          l2 = SQLiteGlobal.getIdleConnectionTimeout(); 
      }  
    this.mConfigurationLocked.idleConnectionTimeoutMs = l2;
    this.mConfigurationLocked.journalMode = paramString2;
    this.mConfigurationLocked.syncMode = paramString3;
    if (SQLiteCompatibilityWalFlags.isLegacyCompatibilityWalEnabled()) {
      sQLiteDatabaseConfiguration = this.mConfigurationLocked;
      sQLiteDatabaseConfiguration.openFlags |= Integer.MIN_VALUE;
    } 
  }
  
  private void beginTransaction(SQLiteTransactionListener paramSQLiteTransactionListener, boolean paramBoolean) {
    acquireReference();
    try {
      boolean bool;
      SQLiteSession sQLiteSession = getThreadSession();
      if (paramBoolean) {
        bool = true;
      } else {
        bool = true;
      } 
      sQLiteSession.beginTransaction(bool, paramSQLiteTransactionListener, getThreadDefaultConnectionFlags(false), null);
      return;
    } finally {
      releaseReference();
    } 
  }
  
  private void collectDbStats(ArrayList<SQLiteDebug.DbStats> paramArrayList) {
    synchronized (this.mLock) {
      if (this.mConnectionPoolLocked != null)
        this.mConnectionPoolLocked.collectDbStats(paramArrayList); 
      return;
    } 
  }
  
  public static SQLiteDatabase create(CursorFactory paramCursorFactory) {
    return openDatabase(":memory:", paramCursorFactory, 268435456);
  }
  
  public static SQLiteDatabase createInMemory(OpenParams paramOpenParams) {
    return openDatabase(":memory:", paramOpenParams.toBuilder().addOpenFlags(268435456).build());
  }
  
  public static boolean deleteDatabase(File paramFile) {
    return deleteDatabase(paramFile, true);
  }
  
  public static boolean deleteDatabase(File paramFile, boolean paramBoolean) {
    if (paramFile != null) {
      boolean bool1 = paramFile.delete();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramFile.getPath());
      stringBuilder.append("-journal");
      paramBoolean = (new File(stringBuilder.toString())).delete();
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramFile.getPath());
      stringBuilder.append("-shm");
      boolean bool2 = (new File(stringBuilder.toString())).delete();
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramFile.getPath());
      stringBuilder.append("-wal");
      int i = false | bool1 | paramBoolean | bool2 | (new File(stringBuilder.toString())).delete();
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramFile.getPath());
      stringBuilder.append("-wipecheck");
      (new File(stringBuilder.toString())).delete();
      File file = paramFile.getParentFile();
      int j = i;
      if (file != null) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(paramFile.getName());
        stringBuilder.append("-mj");
        File[] arrayOfFile = file.listFiles(new FileFilter(stringBuilder.toString()) {
              public boolean accept(File param1File) {
                return param1File.getName().startsWith(prefix);
              }
            });
        j = i;
        if (arrayOfFile != null) {
          int k = arrayOfFile.length;
          byte b = 0;
          while (true) {
            j = i;
            if (b < k) {
              boolean bool = i | arrayOfFile[b].delete();
              b++;
              continue;
            } 
            break;
          } 
        } 
      } 
      return j;
    } 
    throw new IllegalArgumentException("file must not be null");
  }
  
  private void dispose(boolean paramBoolean) {
    synchronized (this.mLock) {
      if (this.mCloseGuardLocked != null) {
        if (paramBoolean)
          this.mCloseGuardLocked.warnIfOpen(); 
        this.mCloseGuardLocked.close();
      } 
      SQLiteConnectionPool sQLiteConnectionPool = this.mConnectionPoolLocked;
      this.mConnectionPoolLocked = null;
      if (!paramBoolean)
        synchronized (sActiveDatabases) {
          sActiveDatabases.remove(this);
          if (sQLiteConnectionPool != null)
            sQLiteConnectionPool.close(); 
        }  
      return;
    } 
  }
  
  private void dump(Printer paramPrinter, boolean paramBoolean1, boolean paramBoolean2, ArraySet<String> paramArraySet) {
    synchronized (this.mLock) {
      if (this.mConnectionPoolLocked != null) {
        paramPrinter.println("");
        this.mConnectionPoolLocked.dump(paramPrinter, paramBoolean1, paramArraySet);
      } 
      return;
    } 
  }
  
  static void dumpAll(Printer paramPrinter, boolean paramBoolean1, boolean paramBoolean2) {
    ArraySet arraySet = new ArraySet();
    Iterator<SQLiteDatabase> iterator = getActiveDatabases().iterator();
    while (iterator.hasNext())
      ((SQLiteDatabase)iterator.next()).dump(paramPrinter, paramBoolean1, paramBoolean2, arraySet); 
    if (arraySet.size() > 0) {
      String[] arrayOfString = (String[])arraySet.toArray((Object[])new String[arraySet.size()]);
      Arrays.sort((Object[])arrayOfString);
      int i = arrayOfString.length;
      for (byte b = 0; b < i; b++)
        dumpDatabaseDirectory(paramPrinter, new File(arrayOfString[b]), paramBoolean2); 
    } 
  }
  
  private static void dumpDatabaseDirectory(Printer paramPrinter, File paramFile, boolean paramBoolean) {
    paramPrinter.println("");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Database files in ");
    stringBuilder.append(paramFile.getAbsolutePath());
    stringBuilder.append(":");
    paramPrinter.println(stringBuilder.toString());
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null || arrayOfFile.length == 0) {
      paramPrinter.println("  [none]");
      return;
    } 
    Arrays.sort(arrayOfFile, (Comparator<? super File>)_$$Lambda$SQLiteDatabase$1FsSJH2q7x3eeDFXCAu9l4piDsE.INSTANCE);
    int i = arrayOfFile.length;
    for (byte b = 0; b < i; b++) {
      File file = arrayOfFile[b];
      if (paramBoolean) {
        String str = file.getName();
        if (!str.endsWith(".db") && !str.endsWith(".db-wal") && !str.endsWith(".db-journal") && !str.endsWith("-wipecheck"))
          continue; 
      } 
      paramPrinter.println(String.format("  %-40s %7db %s", new Object[] { file.getName(), Long.valueOf(file.length()), getFileTimestamps(file.getAbsolutePath()) }));
      continue;
    } 
  }
  
  public static String findEditTable(String paramString) {
    if (!TextUtils.isEmpty(paramString)) {
      int i = paramString.indexOf(' ');
      int j = paramString.indexOf(',');
      return (i > 0 && (i < j || j < 0)) ? paramString.substring(0, i) : ((j > 0 && (j < i || i < 0)) ? paramString.substring(0, j) : paramString);
    } 
    throw new IllegalStateException("Invalid tables");
  }
  
  private static ArrayList<SQLiteDatabase> getActiveDatabases() {
    null = new ArrayList();
    synchronized (sActiveDatabases) {
      null.addAll(sActiveDatabases.keySet());
      return null;
    } 
  }
  
  static ArrayList<SQLiteDebug.DbStats> getDbStats() {
    ArrayList<SQLiteDebug.DbStats> arrayList = new ArrayList();
    Iterator<SQLiteDatabase> iterator = getActiveDatabases().iterator();
    while (iterator.hasNext())
      ((SQLiteDatabase)iterator.next()).collectDbStats(arrayList); 
    return arrayList;
  }
  
  public static String getFileTimestamps(String paramString) {
    try {
      paramString = Files.readAttributes(FileSystems.getDefault().getPath(paramString, new String[0]), BasicFileAttributes.class, new java.nio.file.LinkOption[0]);
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("ctime=");
      stringBuilder.append(paramString.creationTime());
      stringBuilder.append(" mtime=");
      stringBuilder.append(paramString.lastModifiedTime());
      stringBuilder.append(" atime=");
      stringBuilder.append(paramString.lastAccessTime());
      return stringBuilder.toString();
    } catch (IOException iOException) {
      return "[unable to obtain timestamp]";
    } 
  }
  
  private static boolean isMainThread() {
    boolean bool;
    Looper looper = Looper.myLooper();
    if (looper != null && looper == Looper.getMainLooper()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private boolean isReadOnlyLocked() {
    int i = this.mConfigurationLocked.openFlags;
    boolean bool = true;
    if ((i & 0x1) != 1)
      bool = false; 
    return bool;
  }
  
  private void open() {
    try {
      openInner();
    } catch (RuntimeException runtimeException) {
      if (SQLiteDatabaseCorruptException.isCorruptException(runtimeException)) {
        Log.e("SQLiteDatabase", "Database corruption detected in open()", runtimeException);
        onCorruption();
        openInner();
        return;
      } 
    } catch (SQLiteException sQLiteException) {}
  }
  
  public static SQLiteDatabase openDatabase(File paramFile, OpenParams paramOpenParams) {
    return openDatabase(paramFile.getPath(), paramOpenParams);
  }
  
  public static SQLiteDatabase openDatabase(String paramString, CursorFactory paramCursorFactory, int paramInt) {
    return openDatabase(paramString, paramCursorFactory, paramInt, (DatabaseErrorHandler)null);
  }
  
  public static SQLiteDatabase openDatabase(String paramString, CursorFactory paramCursorFactory, int paramInt, DatabaseErrorHandler paramDatabaseErrorHandler) {
    SQLiteDatabase sQLiteDatabase = new SQLiteDatabase(paramString, paramInt, paramCursorFactory, paramDatabaseErrorHandler, -1, -1, -1L, null, null);
    sQLiteDatabase.open();
    return sQLiteDatabase;
  }
  
  private static SQLiteDatabase openDatabase(String paramString, OpenParams paramOpenParams) {
    boolean bool;
    if (paramOpenParams != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "OpenParams cannot be null");
    SQLiteDatabase sQLiteDatabase = new SQLiteDatabase(paramString, paramOpenParams.mOpenFlags, paramOpenParams.mCursorFactory, paramOpenParams.mErrorHandler, paramOpenParams.mLookasideSlotSize, paramOpenParams.mLookasideSlotCount, paramOpenParams.mIdleConnectionTimeout, paramOpenParams.mJournalMode, paramOpenParams.mSyncMode);
    sQLiteDatabase.open();
    return sQLiteDatabase;
  }
  
  private void openInner() {
    synchronized (this.mLock) {
      this.mConnectionPoolLocked = SQLiteConnectionPool.open(this.mConfigurationLocked);
      this.mCloseGuardLocked.open("close");
      synchronized (sActiveDatabases) {
        sActiveDatabases.put(this, null);
        return;
      } 
    } 
  }
  
  public static SQLiteDatabase openOrCreateDatabase(File paramFile, CursorFactory paramCursorFactory) {
    return openOrCreateDatabase(paramFile.getPath(), paramCursorFactory);
  }
  
  public static SQLiteDatabase openOrCreateDatabase(String paramString, CursorFactory paramCursorFactory) {
    return openDatabase(paramString, paramCursorFactory, 268435456, (DatabaseErrorHandler)null);
  }
  
  public static SQLiteDatabase openOrCreateDatabase(String paramString, CursorFactory paramCursorFactory, DatabaseErrorHandler paramDatabaseErrorHandler) {
    return openDatabase(paramString, paramCursorFactory, 268435456, paramDatabaseErrorHandler);
  }
  
  public static int releaseMemory() {
    return SQLiteGlobal.releaseMemory();
  }
  
  private void throwIfNotOpenLocked() {
    if (this.mConnectionPoolLocked != null)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("The database '");
    stringBuilder.append(this.mConfigurationLocked.label);
    stringBuilder.append("' is not open.");
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public static void wipeDetected(String paramString1, String paramString2) {
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("DB wipe detected: package=");
    stringBuilder2.append(ActivityThread.currentPackageName());
    stringBuilder2.append(" reason=");
    stringBuilder2.append(paramString2);
    stringBuilder2.append(" file=");
    stringBuilder2.append(paramString1);
    stringBuilder2.append(" ");
    stringBuilder2.append(getFileTimestamps(paramString1));
    stringBuilder2.append(" checkfile ");
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(paramString1);
    stringBuilder1.append("-wipecheck");
    stringBuilder2.append(getFileTimestamps(stringBuilder1.toString()));
    wtfAsSystemServer("SQLiteDatabase", stringBuilder2.toString(), new Throwable("STACKTRACE"));
  }
  
  static void wtfAsSystemServer(String paramString1, String paramString2, Throwable paramThrowable) {
    Log.e(paramString1, paramString2, paramThrowable);
    ContentResolver.onDbCorruption(paramString1, paramString2, paramThrowable);
  }
  
  private boolean yieldIfContendedHelper(boolean paramBoolean, long paramLong) {
    acquireReference();
    try {
      paramBoolean = getThreadSession().yieldTransaction(paramLong, paramBoolean, null);
      return paramBoolean;
    } finally {
      releaseReference();
    } 
  }
  
  public void beginTransaction() {
    beginTransaction((SQLiteTransactionListener)null, true);
  }
  
  public void beginTransactionNonExclusive() {
    beginTransaction((SQLiteTransactionListener)null, false);
  }
  
  public void beginTransactionWithListener(SQLiteTransactionListener paramSQLiteTransactionListener) {
    beginTransaction(paramSQLiteTransactionListener, true);
  }
  
  public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener paramSQLiteTransactionListener) {
    beginTransaction(paramSQLiteTransactionListener, false);
  }
  
  public SQLiteStatement compileStatement(String paramString) throws SQLException {
    acquireReference();
    try {
      return new SQLiteStatement(this, paramString, null);
    } finally {
      releaseReference();
    } 
  }
  
  SQLiteSession createSession() {
    synchronized (this.mLock) {
      throwIfNotOpenLocked();
      SQLiteConnectionPool sQLiteConnectionPool = this.mConnectionPoolLocked;
      return new SQLiteSession(sQLiteConnectionPool);
    } 
  }
  
  public int delete(String paramString1, String paramString2, String[] paramArrayOfString) {
    acquireReference();
    try {
      SQLiteStatement sQLiteStatement = new SQLiteStatement();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("DELETE FROM ");
      stringBuilder.append(paramString1);
      if (!TextUtils.isEmpty(paramString2)) {
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append(" WHERE ");
        stringBuilder1.append(paramString2);
        String str = stringBuilder1.toString();
      } else {
        paramString1 = "";
      } 
      stringBuilder.append(paramString1);
      this(this, stringBuilder.toString(), (Object[])paramArrayOfString);
    } finally {
      releaseReference();
    } 
  }
  
  public void disableWriteAheadLogging() {
    synchronized (this.mLock) {
      boolean bool2;
      throwIfNotOpenLocked();
      int i = this.mConfigurationLocked.openFlags;
      boolean bool1 = true;
      if ((0x20000000 & i) != 0) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      if ((Integer.MIN_VALUE & i) == 0)
        bool1 = false; 
      if (!bool2 && !bool1)
        return; 
      SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = this.mConfigurationLocked;
      sQLiteDatabaseConfiguration.openFlags &= 0xDFFFFFFF;
      sQLiteDatabaseConfiguration = this.mConfigurationLocked;
      sQLiteDatabaseConfiguration.openFlags &= Integer.MAX_VALUE;
      try {
        this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
        return;
      } catch (RuntimeException runtimeException) {
        this.mConfigurationLocked.openFlags = i;
        throw runtimeException;
      } 
    } 
  }
  
  public boolean enableWriteAheadLogging() {
    synchronized (this.mLock) {
      throwIfNotOpenLocked();
      if ((this.mConfigurationLocked.openFlags & 0x20000000) != 0)
        return true; 
      if (isReadOnlyLocked())
        return false; 
      if (this.mConfigurationLocked.isInMemoryDb()) {
        Log.i("SQLiteDatabase", "can't enable WAL for memory databases.");
        return false;
      } 
      if (this.mHasAttachedDbsLocked) {
        if (Log.isLoggable("SQLiteDatabase", 3)) {
          StringBuilder stringBuilder = new StringBuilder();
          this();
          stringBuilder.append("this database: ");
          stringBuilder.append(this.mConfigurationLocked.label);
          stringBuilder.append(" has attached databases. can't  enable WAL.");
          Log.d("SQLiteDatabase", stringBuilder.toString());
        } 
        return false;
      } 
      SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = this.mConfigurationLocked;
      sQLiteDatabaseConfiguration.openFlags = 0x20000000 | sQLiteDatabaseConfiguration.openFlags;
      try {
        this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
        return true;
      } catch (RuntimeException runtimeException) {
        sQLiteDatabaseConfiguration = this.mConfigurationLocked;
        sQLiteDatabaseConfiguration.openFlags &= 0xDFFFFFFF;
        throw runtimeException;
      } 
    } 
  }
  
  public void endTransaction() {
    acquireReference();
    try {
      getThreadSession().endTransaction(null);
      return;
    } finally {
      releaseReference();
    } 
  }
  
  public void execPerConnectionSQL(String paramString, Object[] paramArrayOfObject) throws SQLException {
    Objects.requireNonNull(paramString);
    Object[] arrayOfObject = DatabaseUtils.deepCopyOf(paramArrayOfObject);
    synchronized (this.mLock) {
      throwIfNotOpenLocked();
      int i = this.mConfigurationLocked.perConnectionSql.size();
      this.mConfigurationLocked.perConnectionSql.add(Pair.create(paramString, arrayOfObject));
      try {
        this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
        return;
      } catch (RuntimeException runtimeException) {
        this.mConfigurationLocked.perConnectionSql.remove(i);
        throw runtimeException;
      } 
    } 
  }
  
  public void execSQL(String paramString) throws SQLException {
    executeSql(paramString, (Object[])null);
  }
  
  public void execSQL(String paramString, Object[] paramArrayOfObject) throws SQLException {
    if (paramArrayOfObject != null) {
      executeSql(paramString, paramArrayOfObject);
      return;
    } 
    throw new IllegalArgumentException("Empty bindArgs");
  }
  
  public int executeSql(String paramString, Object[] paramArrayOfObject) throws SQLException {
    acquireReference();
    try {
      int i = DatabaseUtils.getSqlStatementType(paramString);
      if (i == 3) {
        boolean bool = false;
        synchronized (this.mLock) {
          if (!this.mHasAttachedDbsLocked) {
            this.mHasAttachedDbsLocked = true;
            bool = true;
            this.mConnectionPoolLocked.disableIdleConnectionHandler();
          } 
          if (bool)
            disableWriteAheadLogging(); 
        } 
      } 
    } finally {
      releaseReference();
    } 
  }
  
  protected void finalize() throws Throwable {
    try {
      dispose(true);
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public List<Pair<String, String>> getAttachedDbs() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: aload_0
    //   9: getfield mLock : Ljava/lang/Object;
    //   12: astore_2
    //   13: aload_2
    //   14: monitorenter
    //   15: aload_0
    //   16: getfield mConnectionPoolLocked : Landroid/database/sqlite/SQLiteConnectionPool;
    //   19: ifnonnull -> 26
    //   22: aload_2
    //   23: monitorexit
    //   24: aconst_null
    //   25: areturn
    //   26: aload_0
    //   27: getfield mHasAttachedDbsLocked : Z
    //   30: ifne -> 61
    //   33: new android/util/Pair
    //   36: astore_3
    //   37: aload_3
    //   38: ldc_w 'main'
    //   41: aload_0
    //   42: getfield mConfigurationLocked : Landroid/database/sqlite/SQLiteDatabaseConfiguration;
    //   45: getfield path : Ljava/lang/String;
    //   48: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   51: aload_1
    //   52: aload_3
    //   53: invokevirtual add : (Ljava/lang/Object;)Z
    //   56: pop
    //   57: aload_2
    //   58: monitorexit
    //   59: aload_1
    //   60: areturn
    //   61: aload_0
    //   62: invokevirtual acquireReference : ()V
    //   65: aload_2
    //   66: monitorexit
    //   67: aconst_null
    //   68: astore_2
    //   69: aload_0
    //   70: ldc_w 'pragma database_list;'
    //   73: aconst_null
    //   74: invokevirtual rawQuery : (Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   77: astore_3
    //   78: aload_3
    //   79: astore_2
    //   80: aload_3
    //   81: invokeinterface moveToNext : ()Z
    //   86: ifeq -> 129
    //   89: aload_3
    //   90: astore_2
    //   91: new android/util/Pair
    //   94: astore #4
    //   96: aload_3
    //   97: astore_2
    //   98: aload #4
    //   100: aload_3
    //   101: iconst_1
    //   102: invokeinterface getString : (I)Ljava/lang/String;
    //   107: aload_3
    //   108: iconst_2
    //   109: invokeinterface getString : (I)Ljava/lang/String;
    //   114: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   117: aload_3
    //   118: astore_2
    //   119: aload_1
    //   120: aload #4
    //   122: invokevirtual add : (Ljava/lang/Object;)Z
    //   125: pop
    //   126: goto -> 78
    //   129: aload_3
    //   130: ifnull -> 139
    //   133: aload_3
    //   134: invokeinterface close : ()V
    //   139: aload_0
    //   140: invokevirtual releaseReference : ()V
    //   143: aload_1
    //   144: areturn
    //   145: astore_3
    //   146: aload_2
    //   147: ifnull -> 156
    //   150: aload_2
    //   151: invokeinterface close : ()V
    //   156: aload_3
    //   157: athrow
    //   158: astore_2
    //   159: aload_0
    //   160: invokevirtual releaseReference : ()V
    //   163: aload_2
    //   164: athrow
    //   165: astore_3
    //   166: aload_2
    //   167: monitorexit
    //   168: aload_3
    //   169: athrow
    // Exception table:
    //   from	to	target	type
    //   15	24	165	finally
    //   26	59	165	finally
    //   61	67	165	finally
    //   69	78	145	finally
    //   80	89	145	finally
    //   91	96	145	finally
    //   98	117	145	finally
    //   119	126	145	finally
    //   133	139	158	finally
    //   150	156	158	finally
    //   156	158	158	finally
    //   166	168	165	finally
  }
  
  String getLabel() {
    synchronized (this.mLock) {
      return this.mConfigurationLocked.label;
    } 
  }
  
  public long getMaximumSize() {
    long l = DatabaseUtils.longForQuery(this, "PRAGMA max_page_count;", null);
    return getPageSize() * l;
  }
  
  public long getPageSize() {
    return DatabaseUtils.longForQuery(this, "PRAGMA page_size;", null);
  }
  
  public final String getPath() {
    synchronized (this.mLock) {
      return this.mConfigurationLocked.path;
    } 
  }
  
  @Deprecated
  public Map<String, String> getSyncedTables() {
    return new HashMap<>(0);
  }
  
  int getThreadDefaultConnectionFlags(boolean paramBoolean) {
    byte b;
    if (paramBoolean) {
      b = 1;
    } else {
      b = 2;
    } 
    int i = b;
    if (isMainThread())
      i = b | 0x4; 
    return i;
  }
  
  SQLiteSession getThreadSession() {
    return this.mThreadSession.get();
  }
  
  public int getVersion() {
    return Long.valueOf(DatabaseUtils.longForQuery(this, "PRAGMA user_version;", null)).intValue();
  }
  
  public boolean inTransaction() {
    acquireReference();
    try {
      return getThreadSession().hasTransaction();
    } finally {
      releaseReference();
    } 
  }
  
  public long insert(String paramString1, String paramString2, ContentValues paramContentValues) {
    try {
      return insertWithOnConflict(paramString1, paramString2, paramContentValues, 0);
    } catch (SQLException sQLException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Error inserting ");
      stringBuilder.append(paramContentValues);
      Log.e("SQLiteDatabase", stringBuilder.toString(), (Throwable)sQLException);
      return -1L;
    } 
  }
  
  public long insertOrThrow(String paramString1, String paramString2, ContentValues paramContentValues) throws SQLException {
    return insertWithOnConflict(paramString1, paramString2, paramContentValues, 0);
  }
  
  public long insertWithOnConflict(String paramString1, String paramString2, ContentValues paramContentValues, int paramInt) {
    acquireReference();
    try {
      Object[] arrayOfObject;
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("INSERT");
      stringBuilder.append(CONFLICT_VALUES[paramInt]);
      stringBuilder.append(" INTO ");
      stringBuilder.append(paramString1);
      stringBuilder.append('(');
      paramString1 = null;
      if (paramContentValues != null && !paramContentValues.isEmpty()) {
        paramInt = paramContentValues.size();
      } else {
        paramInt = 0;
      } 
      if (paramInt > 0) {
        arrayOfObject = new Object[paramInt];
        byte b = 0;
        for (String str : paramContentValues.keySet()) {
          if (b) {
            paramString1 = ",";
          } else {
            paramString1 = "";
          } 
          stringBuilder.append(paramString1);
          stringBuilder.append(str);
          arrayOfObject[b] = paramContentValues.get(str);
          b++;
        } 
        stringBuilder.append(')');
        stringBuilder.append(" VALUES (");
        for (b = 0; b < paramInt; b++) {
          if (b > 0) {
            paramString1 = ",?";
          } else {
            paramString1 = "?";
          } 
          stringBuilder.append(paramString1);
        } 
        null = arrayOfObject;
      } else {
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append((String)arrayOfObject);
        stringBuilder1.append(") VALUES (NULL");
        stringBuilder.append(stringBuilder1.toString());
      } 
      stringBuilder.append(')');
      SQLiteStatement sQLiteStatement = new SQLiteStatement();
      this(this, stringBuilder.toString(), null);
    } finally {
      releaseReference();
    } 
  }
  
  public boolean isDatabaseIntegrityOk() {
    ArrayList<Pair> arrayList;
    acquireReference();
    try {
      List<Pair<String, String>> list = getAttachedDbs();
      if (list == null) {
        IllegalStateException illegalStateException = new IllegalStateException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("databaselist for: ");
        stringBuilder.append(getPath());
        stringBuilder.append(" couldn't be retrieved. probably because the database is closed");
        this(stringBuilder.toString());
        throw illegalStateException;
      } 
    } catch (SQLiteException sQLiteException) {
      arrayList = new ArrayList();
      this();
      Pair pair = new Pair();
      this("main", getPath());
      arrayList.add(pair);
    } finally {
      Exception exception;
    } 
    byte b = 0;
    while (b < arrayList.size()) {
      Pair pair = arrayList.get(b);
      SQLiteStatement sQLiteStatement2 = null;
      SQLiteStatement sQLiteStatement1 = sQLiteStatement2;
      try {
        StringBuilder stringBuilder = new StringBuilder();
        sQLiteStatement1 = sQLiteStatement2;
        this();
        sQLiteStatement1 = sQLiteStatement2;
        stringBuilder.append("PRAGMA ");
        sQLiteStatement1 = sQLiteStatement2;
        stringBuilder.append((String)pair.first);
        sQLiteStatement1 = sQLiteStatement2;
        stringBuilder.append(".integrity_check(1);");
        sQLiteStatement1 = sQLiteStatement2;
        sQLiteStatement2 = compileStatement(stringBuilder.toString());
        sQLiteStatement1 = sQLiteStatement2;
        String str = sQLiteStatement2.simpleQueryForString();
        sQLiteStatement1 = sQLiteStatement2;
        if (!str.equalsIgnoreCase("ok")) {
          sQLiteStatement1 = sQLiteStatement2;
          StringBuilder stringBuilder1 = new StringBuilder();
          sQLiteStatement1 = sQLiteStatement2;
          this();
          sQLiteStatement1 = sQLiteStatement2;
          stringBuilder1.append("PRAGMA integrity_check on ");
          sQLiteStatement1 = sQLiteStatement2;
          stringBuilder1.append((String)pair.second);
          sQLiteStatement1 = sQLiteStatement2;
          stringBuilder1.append(" returned: ");
          sQLiteStatement1 = sQLiteStatement2;
          stringBuilder1.append(str);
          sQLiteStatement1 = sQLiteStatement2;
          Log.e("SQLiteDatabase", stringBuilder1.toString());
          if (sQLiteStatement2 != null)
            sQLiteStatement2.close(); 
          return false;
        } 
        if (sQLiteStatement2 != null)
          sQLiteStatement2.close(); 
      } finally {
        if (sQLiteStatement1 != null)
          sQLiteStatement1.close(); 
      } 
    } 
    releaseReference();
    return true;
  }
  
  public boolean isDbLockedByCurrentThread() {
    acquireReference();
    try {
      return getThreadSession().hasConnection();
    } finally {
      releaseReference();
    } 
  }
  
  @Deprecated
  public boolean isDbLockedByOtherThreads() {
    return false;
  }
  
  public boolean isInMemoryDatabase() {
    synchronized (this.mLock) {
      return this.mConfigurationLocked.isInMemoryDb();
    } 
  }
  
  public boolean isOpen() {
    synchronized (this.mLock) {
      boolean bool;
      if (this.mConnectionPoolLocked != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  public boolean isReadOnly() {
    synchronized (this.mLock) {
      return isReadOnlyLocked();
    } 
  }
  
  public boolean isWriteAheadLoggingEnabled() {
    synchronized (this.mLock) {
      boolean bool;
      throwIfNotOpenLocked();
      if ((this.mConfigurationLocked.openFlags & 0x20000000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  @Deprecated
  public void markTableSyncable(String paramString1, String paramString2) {}
  
  @Deprecated
  public void markTableSyncable(String paramString1, String paramString2, String paramString3) {}
  
  public boolean needUpgrade(int paramInt) {
    boolean bool;
    if (paramInt > getVersion()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  protected void onAllReferencesReleased() {
    dispose(false);
  }
  
  void onCorruption() {
    EventLog.writeEvent(75004, getLabel());
    this.mErrorHandler.onCorruption(this);
  }
  
  public Cursor query(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5) {
    return query(false, paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5, (String)null);
  }
  
  public Cursor query(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6) {
    return query(false, paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5, paramString6);
  }
  
  public Cursor query(boolean paramBoolean, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6) {
    return queryWithFactory((CursorFactory)null, paramBoolean, paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5, paramString6, (CancellationSignal)null);
  }
  
  public Cursor query(boolean paramBoolean, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6, CancellationSignal paramCancellationSignal) {
    return queryWithFactory((CursorFactory)null, paramBoolean, paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5, paramString6, paramCancellationSignal);
  }
  
  public Cursor queryWithFactory(CursorFactory paramCursorFactory, boolean paramBoolean, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6) {
    return queryWithFactory(paramCursorFactory, paramBoolean, paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5, paramString6, (CancellationSignal)null);
  }
  
  public Cursor queryWithFactory(CursorFactory paramCursorFactory, boolean paramBoolean, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6, CancellationSignal paramCancellationSignal) {
    acquireReference();
    try {
      String str = SQLiteQueryBuilder.buildQueryString(paramBoolean, paramString1, paramArrayOfString1, paramString2, paramString3, paramString4, paramString5, paramString6);
      return rawQueryWithFactory(paramCursorFactory, str, paramArrayOfString2, findEditTable(paramString1), paramCancellationSignal);
    } finally {
      releaseReference();
    } 
  }
  
  public Cursor rawQuery(String paramString, String[] paramArrayOfString) {
    return rawQueryWithFactory((CursorFactory)null, paramString, paramArrayOfString, (String)null, (CancellationSignal)null);
  }
  
  public Cursor rawQuery(String paramString, String[] paramArrayOfString, CancellationSignal paramCancellationSignal) {
    return rawQueryWithFactory((CursorFactory)null, paramString, paramArrayOfString, (String)null, paramCancellationSignal);
  }
  
  public Cursor rawQueryWithFactory(CursorFactory paramCursorFactory, String paramString1, String[] paramArrayOfString, String paramString2) {
    return rawQueryWithFactory(paramCursorFactory, paramString1, paramArrayOfString, paramString2, (CancellationSignal)null);
  }
  
  public Cursor rawQueryWithFactory(CursorFactory paramCursorFactory, String paramString1, String[] paramArrayOfString, String paramString2, CancellationSignal paramCancellationSignal) {
    acquireReference();
    try {
      SQLiteDirectCursorDriver sQLiteDirectCursorDriver = new SQLiteDirectCursorDriver();
      this(this, paramString1, paramString2, paramCancellationSignal);
      if (paramCursorFactory == null)
        paramCursorFactory = this.mCursorFactory; 
      return sQLiteDirectCursorDriver.query(paramCursorFactory, paramArrayOfString);
    } finally {
      releaseReference();
    } 
  }
  
  public void reopenReadWrite() {
    synchronized (this.mLock) {
      throwIfNotOpenLocked();
      if (!isReadOnlyLocked())
        return; 
      int i = this.mConfigurationLocked.openFlags;
      this.mConfigurationLocked.openFlags = this.mConfigurationLocked.openFlags & 0xFFFFFFFE | 0x0;
      try {
        this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
        return;
      } catch (RuntimeException runtimeException) {
        this.mConfigurationLocked.openFlags = i;
        throw runtimeException;
      } 
    } 
  }
  
  public long replace(String paramString1, String paramString2, ContentValues paramContentValues) {
    try {
      return insertWithOnConflict(paramString1, paramString2, paramContentValues, 5);
    } catch (SQLException sQLException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Error inserting ");
      stringBuilder.append(paramContentValues);
      Log.e("SQLiteDatabase", stringBuilder.toString(), (Throwable)sQLException);
      return -1L;
    } 
  }
  
  public long replaceOrThrow(String paramString1, String paramString2, ContentValues paramContentValues) throws SQLException {
    return insertWithOnConflict(paramString1, paramString2, paramContentValues, 5);
  }
  
  public void setCustomAggregateFunction(String paramString, BinaryOperator<String> paramBinaryOperator) throws SQLiteException {
    Objects.requireNonNull(paramString);
    Objects.requireNonNull(paramBinaryOperator);
    synchronized (this.mLock) {
      throwIfNotOpenLocked();
      this.mConfigurationLocked.customAggregateFunctions.put(paramString, paramBinaryOperator);
      try {
        this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
        return;
      } catch (RuntimeException runtimeException) {
        this.mConfigurationLocked.customAggregateFunctions.remove(paramString);
        throw runtimeException;
      } 
    } 
  }
  
  public void setCustomScalarFunction(String paramString, UnaryOperator<String> paramUnaryOperator) throws SQLiteException {
    Objects.requireNonNull(paramString);
    Objects.requireNonNull(paramUnaryOperator);
    synchronized (this.mLock) {
      throwIfNotOpenLocked();
      this.mConfigurationLocked.customScalarFunctions.put(paramString, paramUnaryOperator);
      try {
        this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
        return;
      } catch (RuntimeException runtimeException) {
        this.mConfigurationLocked.customScalarFunctions.remove(paramString);
        throw runtimeException;
      } 
    } 
  }
  
  public void setForeignKeyConstraintsEnabled(boolean paramBoolean) {
    synchronized (this.mLock) {
      throwIfNotOpenLocked();
      if (this.mConfigurationLocked.foreignKeyConstraintsEnabled == paramBoolean)
        return; 
      this.mConfigurationLocked.foreignKeyConstraintsEnabled = paramBoolean;
      try {
        this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
        return;
      } catch (RuntimeException runtimeException) {
        SQLiteDatabaseConfiguration sQLiteDatabaseConfiguration = this.mConfigurationLocked;
        if (!paramBoolean) {
          paramBoolean = true;
        } else {
          paramBoolean = false;
        } 
        sQLiteDatabaseConfiguration.foreignKeyConstraintsEnabled = paramBoolean;
        throw runtimeException;
      } 
    } 
  }
  
  public void setLocale(Locale paramLocale) {
    if (paramLocale != null)
      synchronized (this.mLock) {
        throwIfNotOpenLocked();
        Locale locale = this.mConfigurationLocked.locale;
        this.mConfigurationLocked.locale = paramLocale;
        try {
          this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
          return;
        } catch (RuntimeException runtimeException) {
          this.mConfigurationLocked.locale = locale;
          throw runtimeException;
        } 
      }  
    throw new IllegalArgumentException("locale must not be null.");
  }
  
  @Deprecated
  public void setLockingEnabled(boolean paramBoolean) {}
  
  public void setMaxSqlCacheSize(int paramInt) {
    if (paramInt <= 100 && paramInt >= 0)
      synchronized (this.mLock) {
        throwIfNotOpenLocked();
        int i = this.mConfigurationLocked.maxSqlCacheSize;
        this.mConfigurationLocked.maxSqlCacheSize = paramInt;
        try {
          this.mConnectionPoolLocked.reconfigure(this.mConfigurationLocked);
          return;
        } catch (RuntimeException runtimeException) {
          this.mConfigurationLocked.maxSqlCacheSize = i;
          throw runtimeException;
        } 
      }  
    throw new IllegalStateException("expected value between 0 and 100");
  }
  
  public long setMaximumSize(long paramLong) {
    long l1 = getPageSize();
    long l2 = paramLong / l1;
    long l3 = l2;
    if (paramLong % l1 != 0L)
      l3 = l2 + 1L; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PRAGMA max_page_count = ");
    stringBuilder.append(l3);
    return DatabaseUtils.longForQuery(this, stringBuilder.toString(), null) * l1;
  }
  
  public void setPageSize(long paramLong) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PRAGMA page_size = ");
    stringBuilder.append(paramLong);
    execSQL(stringBuilder.toString());
  }
  
  public void setTransactionSuccessful() {
    acquireReference();
    try {
      getThreadSession().setTransactionSuccessful();
      return;
    } finally {
      releaseReference();
    } 
  }
  
  public void setVersion(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PRAGMA user_version = ");
    stringBuilder.append(paramInt);
    execSQL(stringBuilder.toString());
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SQLiteDatabase: ");
    stringBuilder.append(getPath());
    return stringBuilder.toString();
  }
  
  public int update(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString) {
    return updateWithOnConflict(paramString1, paramContentValues, paramString2, paramArrayOfString, 0);
  }
  
  public int updateWithOnConflict(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString, int paramInt) {
    if (paramContentValues != null && !paramContentValues.isEmpty()) {
      acquireReference();
      try {
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        this(120);
        stringBuilder.append("UPDATE ");
        stringBuilder.append(CONFLICT_VALUES[paramInt]);
        stringBuilder.append(paramString1);
        stringBuilder.append(" SET ");
        paramInt = paramContentValues.size();
        if (paramArrayOfString == null) {
          i = paramInt;
        } else {
          i = paramArrayOfString.length + paramInt;
        } 
        Object[] arrayOfObject = new Object[i];
        int j = 0;
        for (String str : paramContentValues.keySet()) {
          if (j) {
            paramString1 = ",";
          } else {
            paramString1 = "";
          } 
          stringBuilder.append(paramString1);
          stringBuilder.append(str);
          arrayOfObject[j] = paramContentValues.get(str);
          stringBuilder.append("=?");
          j++;
        } 
        if (paramArrayOfString != null)
          for (j = paramInt; j < i; j++)
            arrayOfObject[j] = paramArrayOfString[j - paramInt];  
        if (!TextUtils.isEmpty(paramString2)) {
          stringBuilder.append(" WHERE ");
          stringBuilder.append(paramString2);
        } 
        SQLiteStatement sQLiteStatement = new SQLiteStatement();
        this(this, stringBuilder.toString(), arrayOfObject);
      } finally {
        releaseReference();
      } 
    } 
    throw new IllegalArgumentException("Empty values");
  }
  
  public void validateSql(String paramString, CancellationSignal paramCancellationSignal) {
    getThreadSession().prepare(paramString, getThreadDefaultConnectionFlags(true), paramCancellationSignal, null);
  }
  
  @Deprecated
  public boolean yieldIfContended() {
    return yieldIfContendedHelper(false, -1L);
  }
  
  public boolean yieldIfContendedSafely() {
    return yieldIfContendedHelper(true, -1L);
  }
  
  public boolean yieldIfContendedSafely(long paramLong) {
    return yieldIfContendedHelper(true, paramLong);
  }
  
  public static interface CursorFactory {
    Cursor newCursor(SQLiteDatabase param1SQLiteDatabase, SQLiteCursorDriver param1SQLiteCursorDriver, String param1String, SQLiteQuery param1SQLiteQuery);
  }
  
  public static interface CustomFunction {
    void callback(String[] param1ArrayOfString);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface DatabaseOpenFlags {}
  
  public static final class OpenParams {
    private final SQLiteDatabase.CursorFactory mCursorFactory;
    
    private final DatabaseErrorHandler mErrorHandler;
    
    private final long mIdleConnectionTimeout;
    
    private final String mJournalMode;
    
    private final int mLookasideSlotCount;
    
    private final int mLookasideSlotSize;
    
    private final int mOpenFlags;
    
    private final String mSyncMode;
    
    private OpenParams(int param1Int1, SQLiteDatabase.CursorFactory param1CursorFactory, DatabaseErrorHandler param1DatabaseErrorHandler, int param1Int2, int param1Int3, long param1Long, String param1String1, String param1String2) {
      this.mOpenFlags = param1Int1;
      this.mCursorFactory = param1CursorFactory;
      this.mErrorHandler = param1DatabaseErrorHandler;
      this.mLookasideSlotSize = param1Int2;
      this.mLookasideSlotCount = param1Int3;
      this.mIdleConnectionTimeout = param1Long;
      this.mJournalMode = param1String1;
      this.mSyncMode = param1String2;
    }
    
    public SQLiteDatabase.CursorFactory getCursorFactory() {
      return this.mCursorFactory;
    }
    
    public DatabaseErrorHandler getErrorHandler() {
      return this.mErrorHandler;
    }
    
    public long getIdleConnectionTimeout() {
      return this.mIdleConnectionTimeout;
    }
    
    public String getJournalMode() {
      return this.mJournalMode;
    }
    
    public int getLookasideSlotCount() {
      return this.mLookasideSlotCount;
    }
    
    public int getLookasideSlotSize() {
      return this.mLookasideSlotSize;
    }
    
    public int getOpenFlags() {
      return this.mOpenFlags;
    }
    
    public String getSynchronousMode() {
      return this.mSyncMode;
    }
    
    public Builder toBuilder() {
      return new Builder(this);
    }
    
    public static final class Builder {
      private SQLiteDatabase.CursorFactory mCursorFactory;
      
      private DatabaseErrorHandler mErrorHandler;
      
      private long mIdleConnectionTimeout = -1L;
      
      private String mJournalMode;
      
      private int mLookasideSlotCount = -1;
      
      private int mLookasideSlotSize = -1;
      
      private int mOpenFlags;
      
      private String mSyncMode;
      
      public Builder() {}
      
      public Builder(SQLiteDatabase.OpenParams param2OpenParams) {
        this.mLookasideSlotSize = param2OpenParams.mLookasideSlotSize;
        this.mLookasideSlotCount = param2OpenParams.mLookasideSlotCount;
        this.mOpenFlags = param2OpenParams.mOpenFlags;
        this.mCursorFactory = param2OpenParams.mCursorFactory;
        this.mErrorHandler = param2OpenParams.mErrorHandler;
        this.mJournalMode = param2OpenParams.mJournalMode;
        this.mSyncMode = param2OpenParams.mSyncMode;
      }
      
      public Builder addOpenFlags(int param2Int) {
        this.mOpenFlags |= param2Int;
        return this;
      }
      
      public SQLiteDatabase.OpenParams build() {
        return new SQLiteDatabase.OpenParams(this.mOpenFlags, this.mCursorFactory, this.mErrorHandler, this.mLookasideSlotSize, this.mLookasideSlotCount, this.mIdleConnectionTimeout, this.mJournalMode, this.mSyncMode);
      }
      
      public boolean isWriteAheadLoggingEnabled() {
        boolean bool;
        if ((this.mOpenFlags & 0x20000000) != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool;
      }
      
      public Builder removeOpenFlags(int param2Int) {
        this.mOpenFlags &= param2Int;
        return this;
      }
      
      public Builder setCursorFactory(SQLiteDatabase.CursorFactory param2CursorFactory) {
        this.mCursorFactory = param2CursorFactory;
        return this;
      }
      
      public Builder setErrorHandler(DatabaseErrorHandler param2DatabaseErrorHandler) {
        this.mErrorHandler = param2DatabaseErrorHandler;
        return this;
      }
      
      @Deprecated
      public Builder setIdleConnectionTimeout(long param2Long) {
        boolean bool;
        if (param2Long >= 0L) {
          bool = true;
        } else {
          bool = false;
        } 
        Preconditions.checkArgument(bool, "idle connection timeout cannot be negative");
        this.mIdleConnectionTimeout = param2Long;
        return this;
      }
      
      public Builder setJournalMode(String param2String) {
        Objects.requireNonNull(param2String);
        this.mJournalMode = param2String;
        return this;
      }
      
      public Builder setLookasideConfig(int param2Int1, int param2Int2) {
        // Byte code:
        //   0: iconst_1
        //   1: istore_3
        //   2: iload_1
        //   3: iflt -> 12
        //   6: iconst_1
        //   7: istore #4
        //   9: goto -> 15
        //   12: iconst_0
        //   13: istore #4
        //   15: iload #4
        //   17: ldc 'lookasideSlotCount cannot be negative'
        //   19: invokestatic checkArgument : (ZLjava/lang/Object;)V
        //   22: iload_2
        //   23: iflt -> 32
        //   26: iconst_1
        //   27: istore #4
        //   29: goto -> 35
        //   32: iconst_0
        //   33: istore #4
        //   35: iload #4
        //   37: ldc 'lookasideSlotSize cannot be negative'
        //   39: invokestatic checkArgument : (ZLjava/lang/Object;)V
        //   42: iload_1
        //   43: ifle -> 53
        //   46: iload_3
        //   47: istore #4
        //   49: iload_2
        //   50: ifgt -> 70
        //   53: iload_2
        //   54: ifne -> 67
        //   57: iload_1
        //   58: ifne -> 67
        //   61: iload_3
        //   62: istore #4
        //   64: goto -> 70
        //   67: iconst_0
        //   68: istore #4
        //   70: new java/lang/StringBuilder
        //   73: dup
        //   74: invokespecial <init> : ()V
        //   77: astore #5
        //   79: aload #5
        //   81: ldc 'Invalid configuration: '
        //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   86: pop
        //   87: aload #5
        //   89: iload_1
        //   90: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   93: pop
        //   94: aload #5
        //   96: ldc ', '
        //   98: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   101: pop
        //   102: aload #5
        //   104: iload_2
        //   105: invokevirtual append : (I)Ljava/lang/StringBuilder;
        //   108: pop
        //   109: iload #4
        //   111: aload #5
        //   113: invokevirtual toString : ()Ljava/lang/String;
        //   116: invokestatic checkArgument : (ZLjava/lang/Object;)V
        //   119: aload_0
        //   120: iload_1
        //   121: putfield mLookasideSlotSize : I
        //   124: aload_0
        //   125: iload_2
        //   126: putfield mLookasideSlotCount : I
        //   129: aload_0
        //   130: areturn
      }
      
      public Builder setOpenFlags(int param2Int) {
        this.mOpenFlags = param2Int;
        return this;
      }
      
      public Builder setSynchronousMode(String param2String) {
        Objects.requireNonNull(param2String);
        this.mSyncMode = param2String;
        return this;
      }
      
      public void setWriteAheadLoggingEnabled(boolean param2Boolean) {
        if (param2Boolean) {
          addOpenFlags(536870912);
        } else {
          removeOpenFlags(536870912);
        } 
      }
    }
  }
  
  public static final class Builder {
    private SQLiteDatabase.CursorFactory mCursorFactory;
    
    private DatabaseErrorHandler mErrorHandler;
    
    private long mIdleConnectionTimeout = -1L;
    
    private String mJournalMode;
    
    private int mLookasideSlotCount = -1;
    
    private int mLookasideSlotSize = -1;
    
    private int mOpenFlags;
    
    private String mSyncMode;
    
    public Builder() {}
    
    public Builder(SQLiteDatabase.OpenParams param1OpenParams) {
      this.mLookasideSlotSize = param1OpenParams.mLookasideSlotSize;
      this.mLookasideSlotCount = param1OpenParams.mLookasideSlotCount;
      this.mOpenFlags = param1OpenParams.mOpenFlags;
      this.mCursorFactory = param1OpenParams.mCursorFactory;
      this.mErrorHandler = param1OpenParams.mErrorHandler;
      this.mJournalMode = param1OpenParams.mJournalMode;
      this.mSyncMode = param1OpenParams.mSyncMode;
    }
    
    public Builder addOpenFlags(int param1Int) {
      this.mOpenFlags |= param1Int;
      return this;
    }
    
    public SQLiteDatabase.OpenParams build() {
      return new SQLiteDatabase.OpenParams(this.mOpenFlags, this.mCursorFactory, this.mErrorHandler, this.mLookasideSlotSize, this.mLookasideSlotCount, this.mIdleConnectionTimeout, this.mJournalMode, this.mSyncMode);
    }
    
    public boolean isWriteAheadLoggingEnabled() {
      boolean bool;
      if ((this.mOpenFlags & 0x20000000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public Builder removeOpenFlags(int param1Int) {
      this.mOpenFlags &= param1Int;
      return this;
    }
    
    public Builder setCursorFactory(SQLiteDatabase.CursorFactory param1CursorFactory) {
      this.mCursorFactory = param1CursorFactory;
      return this;
    }
    
    public Builder setErrorHandler(DatabaseErrorHandler param1DatabaseErrorHandler) {
      this.mErrorHandler = param1DatabaseErrorHandler;
      return this;
    }
    
    @Deprecated
    public Builder setIdleConnectionTimeout(long param1Long) {
      boolean bool;
      if (param1Long >= 0L) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkArgument(bool, "idle connection timeout cannot be negative");
      this.mIdleConnectionTimeout = param1Long;
      return this;
    }
    
    public Builder setJournalMode(String param1String) {
      Objects.requireNonNull(param1String);
      this.mJournalMode = param1String;
      return this;
    }
    
    public Builder setLookasideConfig(int param1Int1, int param1Int2) {
      // Byte code:
      //   0: iconst_1
      //   1: istore_3
      //   2: iload_1
      //   3: iflt -> 12
      //   6: iconst_1
      //   7: istore #4
      //   9: goto -> 15
      //   12: iconst_0
      //   13: istore #4
      //   15: iload #4
      //   17: ldc 'lookasideSlotCount cannot be negative'
      //   19: invokestatic checkArgument : (ZLjava/lang/Object;)V
      //   22: iload_2
      //   23: iflt -> 32
      //   26: iconst_1
      //   27: istore #4
      //   29: goto -> 35
      //   32: iconst_0
      //   33: istore #4
      //   35: iload #4
      //   37: ldc 'lookasideSlotSize cannot be negative'
      //   39: invokestatic checkArgument : (ZLjava/lang/Object;)V
      //   42: iload_1
      //   43: ifle -> 53
      //   46: iload_3
      //   47: istore #4
      //   49: iload_2
      //   50: ifgt -> 70
      //   53: iload_2
      //   54: ifne -> 67
      //   57: iload_1
      //   58: ifne -> 67
      //   61: iload_3
      //   62: istore #4
      //   64: goto -> 70
      //   67: iconst_0
      //   68: istore #4
      //   70: new java/lang/StringBuilder
      //   73: dup
      //   74: invokespecial <init> : ()V
      //   77: astore #5
      //   79: aload #5
      //   81: ldc 'Invalid configuration: '
      //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   86: pop
      //   87: aload #5
      //   89: iload_1
      //   90: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   93: pop
      //   94: aload #5
      //   96: ldc ', '
      //   98: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   101: pop
      //   102: aload #5
      //   104: iload_2
      //   105: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   108: pop
      //   109: iload #4
      //   111: aload #5
      //   113: invokevirtual toString : ()Ljava/lang/String;
      //   116: invokestatic checkArgument : (ZLjava/lang/Object;)V
      //   119: aload_0
      //   120: iload_1
      //   121: putfield mLookasideSlotSize : I
      //   124: aload_0
      //   125: iload_2
      //   126: putfield mLookasideSlotCount : I
      //   129: aload_0
      //   130: areturn
    }
    
    public Builder setOpenFlags(int param1Int) {
      this.mOpenFlags = param1Int;
      return this;
    }
    
    public Builder setSynchronousMode(String param1String) {
      Objects.requireNonNull(param1String);
      this.mSyncMode = param1String;
      return this;
    }
    
    public void setWriteAheadLoggingEnabled(boolean param1Boolean) {
      if (param1Boolean) {
        addOpenFlags(536870912);
      } else {
        removeOpenFlags(536870912);
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteDatabase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */