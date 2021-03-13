package android.database.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.os.FileUtils;
import android.util.Log;
import java.io.File;
import java.util.Objects;

public abstract class SQLiteOpenHelper implements AutoCloseable {
  private static final String TAG = SQLiteOpenHelper.class.getSimpleName();
  
  private final Context mContext;
  
  private SQLiteDatabase mDatabase;
  
  private boolean mIsInitializing;
  
  private final int mMinimumSupportedVersion;
  
  private final String mName;
  
  private final int mNewVersion;
  
  private SQLiteDatabase.OpenParams.Builder mOpenParamsBuilder;
  
  private SQLiteOpenHelper(Context paramContext, String paramString, int paramInt1, int paramInt2, SQLiteDatabase.OpenParams.Builder paramBuilder) {
    Objects.requireNonNull(paramBuilder);
    if (paramInt1 >= 1) {
      this.mContext = paramContext;
      this.mName = paramString;
      this.mNewVersion = paramInt1;
      this.mMinimumSupportedVersion = Math.max(0, paramInt2);
      setOpenParamsBuilder(paramBuilder);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Version must be >= 1, was ");
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public SQLiteOpenHelper(Context paramContext, String paramString, int paramInt, SQLiteDatabase.OpenParams paramOpenParams) {
    this(paramContext, paramString, paramInt, 0, paramOpenParams.toBuilder());
  }
  
  public SQLiteOpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt) {
    this(paramContext, paramString, paramCursorFactory, paramInt, (DatabaseErrorHandler)null);
  }
  
  public SQLiteOpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt1, int paramInt2, DatabaseErrorHandler paramDatabaseErrorHandler) {
    this(paramContext, paramString, paramInt1, paramInt2, new SQLiteDatabase.OpenParams.Builder());
    this.mOpenParamsBuilder.setCursorFactory(paramCursorFactory);
    this.mOpenParamsBuilder.setErrorHandler(paramDatabaseErrorHandler);
  }
  
  public SQLiteOpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt, DatabaseErrorHandler paramDatabaseErrorHandler) {
    this(paramContext, paramString, paramCursorFactory, paramInt, 0, paramDatabaseErrorHandler);
  }
  
  private SQLiteDatabase getDatabaseLocked(boolean paramBoolean) {
    SQLiteDatabase sQLiteDatabase = this.mDatabase;
    if (sQLiteDatabase != null)
      if (!sQLiteDatabase.isOpen()) {
        this.mDatabase = null;
      } else if (!paramBoolean || !this.mDatabase.isReadOnly()) {
        return this.mDatabase;
      }  
    if (!this.mIsInitializing) {
      SQLiteDatabase sQLiteDatabase1 = this.mDatabase;
      SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase1;
      try {
        this.mIsInitializing = true;
        if (sQLiteDatabase1 != null) {
          sQLiteDatabase = sQLiteDatabase1;
          if (paramBoolean) {
            sQLiteDatabase = sQLiteDatabase1;
            sQLiteDatabase2 = sQLiteDatabase1;
            if (sQLiteDatabase1.isReadOnly()) {
              sQLiteDatabase2 = sQLiteDatabase1;
              sQLiteDatabase1.reopenReadWrite();
              sQLiteDatabase = sQLiteDatabase1;
            } 
          } 
        } else {
          sQLiteDatabase2 = sQLiteDatabase1;
          if (this.mName == null) {
            sQLiteDatabase2 = sQLiteDatabase1;
            sQLiteDatabase = SQLiteDatabase.createInMemory(this.mOpenParamsBuilder.build());
          } else {
            sQLiteDatabase2 = sQLiteDatabase1;
            File file = this.mContext.getDatabasePath(this.mName);
            sQLiteDatabase2 = sQLiteDatabase1;
            SQLiteDatabase.OpenParams openParams = this.mOpenParamsBuilder.build();
            sQLiteDatabase = sQLiteDatabase1;
            sQLiteDatabase2 = sQLiteDatabase1;
            try {
              sQLiteDatabase1 = SQLiteDatabase.openDatabase(file, openParams);
              sQLiteDatabase = sQLiteDatabase1;
              sQLiteDatabase2 = sQLiteDatabase1;
              setFilePermissionsForDb(file.getPath());
              sQLiteDatabase = sQLiteDatabase1;
            } catch (SQLException sQLException) {
              if (!paramBoolean) {
                sQLiteDatabase2 = sQLiteDatabase;
                String str = TAG;
                sQLiteDatabase2 = sQLiteDatabase;
                StringBuilder stringBuilder = new StringBuilder();
                sQLiteDatabase2 = sQLiteDatabase;
                this();
                sQLiteDatabase2 = sQLiteDatabase;
                stringBuilder.append("Couldn't open ");
                sQLiteDatabase2 = sQLiteDatabase;
                stringBuilder.append(this.mName);
                sQLiteDatabase2 = sQLiteDatabase;
                stringBuilder.append(" for writing (will try read-only):");
                sQLiteDatabase2 = sQLiteDatabase;
                Log.e(str, stringBuilder.toString(), (Throwable)sQLException);
                sQLiteDatabase2 = sQLiteDatabase;
                sQLiteDatabase = SQLiteDatabase.openDatabase(file, openParams.toBuilder().addOpenFlags(1).build());
              } else {
                sQLiteDatabase2 = sQLiteDatabase;
                throw sQLException;
              } 
            } 
          } 
        } 
        sQLiteDatabase2 = sQLiteDatabase;
        onConfigure(sQLiteDatabase);
        sQLiteDatabase2 = sQLiteDatabase;
        int i = sQLiteDatabase.getVersion();
        sQLiteDatabase2 = sQLiteDatabase;
        if (i != this.mNewVersion) {
          sQLiteDatabase2 = sQLiteDatabase;
          if (!sQLiteDatabase.isReadOnly()) {
            if (i > 0) {
              sQLiteDatabase2 = sQLiteDatabase;
              if (i < this.mMinimumSupportedVersion) {
                sQLiteDatabase2 = sQLiteDatabase;
                File file = new File();
                sQLiteDatabase2 = sQLiteDatabase;
                this(sQLiteDatabase.getPath());
                sQLiteDatabase2 = sQLiteDatabase;
                onBeforeDelete(sQLiteDatabase);
                sQLiteDatabase2 = sQLiteDatabase;
                sQLiteDatabase.close();
                sQLiteDatabase2 = sQLiteDatabase;
                if (SQLiteDatabase.deleteDatabase(file)) {
                  sQLiteDatabase2 = sQLiteDatabase;
                  this.mIsInitializing = false;
                  sQLiteDatabase2 = sQLiteDatabase;
                  return getDatabaseLocked(paramBoolean);
                } 
                sQLiteDatabase2 = sQLiteDatabase;
                IllegalStateException illegalStateException = new IllegalStateException();
                sQLiteDatabase2 = sQLiteDatabase;
                StringBuilder stringBuilder = new StringBuilder();
                sQLiteDatabase2 = sQLiteDatabase;
                this();
                sQLiteDatabase2 = sQLiteDatabase;
                stringBuilder.append("Unable to delete obsolete database ");
                sQLiteDatabase2 = sQLiteDatabase;
                stringBuilder.append(this.mName);
                sQLiteDatabase2 = sQLiteDatabase;
                stringBuilder.append(" with version ");
                sQLiteDatabase2 = sQLiteDatabase;
                stringBuilder.append(i);
                sQLiteDatabase2 = sQLiteDatabase;
                this(stringBuilder.toString());
                sQLiteDatabase2 = sQLiteDatabase;
                throw illegalStateException;
              } 
            } 
            sQLiteDatabase2 = sQLiteDatabase;
            sQLiteDatabase.beginTransaction();
            if (i == 0) {
              try {
                onCreate(sQLiteDatabase);
                sQLiteDatabase.setVersion(this.mNewVersion);
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase2 = sQLiteDatabase;
              } finally {
                sQLiteDatabase2 = sQLiteDatabase;
                sQLiteDatabase.endTransaction();
                sQLiteDatabase2 = sQLiteDatabase;
              } 
            } else {
              if (i > this.mNewVersion) {
                onDowngrade(sQLiteDatabase, i, this.mNewVersion);
              } else {
                onUpgrade(sQLiteDatabase, i, this.mNewVersion);
              } 
              sQLiteDatabase.setVersion(this.mNewVersion);
              sQLiteDatabase.setTransactionSuccessful();
              sQLiteDatabase2 = sQLiteDatabase;
            } 
          } else {
            sQLiteDatabase2 = sQLiteDatabase;
            SQLiteException sQLiteException = new SQLiteException();
            sQLiteDatabase2 = sQLiteDatabase;
            StringBuilder stringBuilder = new StringBuilder();
            sQLiteDatabase2 = sQLiteDatabase;
            this();
            sQLiteDatabase2 = sQLiteDatabase;
            stringBuilder.append("Can't upgrade read-only database from version ");
            sQLiteDatabase2 = sQLiteDatabase;
            stringBuilder.append(sQLiteDatabase.getVersion());
            sQLiteDatabase2 = sQLiteDatabase;
            stringBuilder.append(" to ");
            sQLiteDatabase2 = sQLiteDatabase;
            stringBuilder.append(this.mNewVersion);
            sQLiteDatabase2 = sQLiteDatabase;
            stringBuilder.append(": ");
            sQLiteDatabase2 = sQLiteDatabase;
            stringBuilder.append(this.mName);
            sQLiteDatabase2 = sQLiteDatabase;
            this(stringBuilder.toString());
            sQLiteDatabase2 = sQLiteDatabase;
            throw sQLiteException;
          } 
        } 
        sQLiteDatabase2 = sQLiteDatabase;
        onOpen(sQLiteDatabase);
        sQLiteDatabase2 = sQLiteDatabase;
        if (sQLiteDatabase.isReadOnly()) {
          sQLiteDatabase2 = sQLiteDatabase;
          String str = TAG;
          sQLiteDatabase2 = sQLiteDatabase;
          StringBuilder stringBuilder = new StringBuilder();
          sQLiteDatabase2 = sQLiteDatabase;
          this();
          sQLiteDatabase2 = sQLiteDatabase;
          stringBuilder.append("Opened ");
          sQLiteDatabase2 = sQLiteDatabase;
          stringBuilder.append(this.mName);
          sQLiteDatabase2 = sQLiteDatabase;
          stringBuilder.append(" in read-only mode");
          sQLiteDatabase2 = sQLiteDatabase;
          Log.w(str, stringBuilder.toString());
        } 
        sQLiteDatabase2 = sQLiteDatabase;
        this.mDatabase = sQLiteDatabase;
        return sQLiteDatabase;
      } finally {
        this.mIsInitializing = false;
        if (sQLiteDatabase2 != null && sQLiteDatabase2 != this.mDatabase)
          sQLiteDatabase2.close(); 
      } 
    } 
    throw new IllegalStateException("getDatabase called recursively");
  }
  
  private static void setFilePermissionsForDb(String paramString) {
    FileUtils.setPermissions(paramString, 432, -1, -1);
  }
  
  private void setOpenParamsBuilder(SQLiteDatabase.OpenParams.Builder paramBuilder) {
    this.mOpenParamsBuilder = paramBuilder;
    paramBuilder.addOpenFlags(268435456);
  }
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mIsInitializing : Z
    //   6: ifne -> 41
    //   9: aload_0
    //   10: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   13: ifnull -> 38
    //   16: aload_0
    //   17: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   20: invokevirtual isOpen : ()Z
    //   23: ifeq -> 38
    //   26: aload_0
    //   27: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   30: invokevirtual close : ()V
    //   33: aload_0
    //   34: aconst_null
    //   35: putfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   38: aload_0
    //   39: monitorexit
    //   40: return
    //   41: new java/lang/IllegalStateException
    //   44: astore_1
    //   45: aload_1
    //   46: ldc 'Closed during initialization'
    //   48: invokespecial <init> : (Ljava/lang/String;)V
    //   51: aload_1
    //   52: athrow
    //   53: astore_1
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    // Exception table:
    //   from	to	target	type
    //   2	38	53	finally
    //   41	53	53	finally
  }
  
  public String getDatabaseName() {
    return this.mName;
  }
  
  public SQLiteDatabase getReadableDatabase() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_0
    //   4: invokespecial getDatabaseLocked : (Z)Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_1
    //   8: aload_0
    //   9: monitorexit
    //   10: aload_1
    //   11: areturn
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	12	finally
    //   13	15	12	finally
  }
  
  public SQLiteDatabase getWritableDatabase() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: invokespecial getDatabaseLocked : (Z)Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_1
    //   8: aload_0
    //   9: monitorexit
    //   10: aload_1
    //   11: areturn
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	12	finally
    //   13	15	12	finally
  }
  
  public void onBeforeDelete(SQLiteDatabase paramSQLiteDatabase) {}
  
  public void onConfigure(SQLiteDatabase paramSQLiteDatabase) {}
  
  public abstract void onCreate(SQLiteDatabase paramSQLiteDatabase);
  
  public void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Can't downgrade database from version ");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" to ");
    stringBuilder.append(paramInt2);
    throw new SQLiteException(stringBuilder.toString());
  }
  
  public void onOpen(SQLiteDatabase paramSQLiteDatabase) {}
  
  public abstract void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2);
  
  @Deprecated
  public void setIdleConnectionTimeout(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   6: ifnull -> 35
    //   9: aload_0
    //   10: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   13: invokevirtual isOpen : ()Z
    //   16: ifne -> 22
    //   19: goto -> 35
    //   22: new java/lang/IllegalStateException
    //   25: astore_3
    //   26: aload_3
    //   27: ldc_w 'Connection timeout setting cannot be changed after opening the database'
    //   30: invokespecial <init> : (Ljava/lang/String;)V
    //   33: aload_3
    //   34: athrow
    //   35: aload_0
    //   36: getfield mOpenParamsBuilder : Landroid/database/sqlite/SQLiteDatabase$OpenParams$Builder;
    //   39: lload_1
    //   40: invokevirtual setIdleConnectionTimeout : (J)Landroid/database/sqlite/SQLiteDatabase$OpenParams$Builder;
    //   43: pop
    //   44: aload_0
    //   45: monitorexit
    //   46: return
    //   47: astore_3
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_3
    //   51: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	47	finally
    //   22	35	47	finally
    //   35	46	47	finally
    //   48	50	47	finally
  }
  
  public void setLookasideConfig(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   6: ifnull -> 35
    //   9: aload_0
    //   10: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   13: invokevirtual isOpen : ()Z
    //   16: ifne -> 22
    //   19: goto -> 35
    //   22: new java/lang/IllegalStateException
    //   25: astore_3
    //   26: aload_3
    //   27: ldc_w 'Lookaside memory config cannot be changed after opening the database'
    //   30: invokespecial <init> : (Ljava/lang/String;)V
    //   33: aload_3
    //   34: athrow
    //   35: aload_0
    //   36: getfield mOpenParamsBuilder : Landroid/database/sqlite/SQLiteDatabase$OpenParams$Builder;
    //   39: iload_1
    //   40: iload_2
    //   41: invokevirtual setLookasideConfig : (II)Landroid/database/sqlite/SQLiteDatabase$OpenParams$Builder;
    //   44: pop
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: astore_3
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_3
    //   52: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	48	finally
    //   22	35	48	finally
    //   35	47	48	finally
    //   49	51	48	finally
  }
  
  public void setOpenParams(SQLiteDatabase.OpenParams paramOpenParams) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic requireNonNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   11: ifnull -> 40
    //   14: aload_0
    //   15: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   18: invokevirtual isOpen : ()Z
    //   21: ifne -> 27
    //   24: goto -> 40
    //   27: new java/lang/IllegalStateException
    //   30: astore_1
    //   31: aload_1
    //   32: ldc_w 'OpenParams cannot be set after opening the database'
    //   35: invokespecial <init> : (Ljava/lang/String;)V
    //   38: aload_1
    //   39: athrow
    //   40: new android/database/sqlite/SQLiteDatabase$OpenParams$Builder
    //   43: astore_2
    //   44: aload_2
    //   45: aload_1
    //   46: invokespecial <init> : (Landroid/database/sqlite/SQLiteDatabase$OpenParams;)V
    //   49: aload_0
    //   50: aload_2
    //   51: invokespecial setOpenParamsBuilder : (Landroid/database/sqlite/SQLiteDatabase$OpenParams$Builder;)V
    //   54: aload_0
    //   55: monitorexit
    //   56: return
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Exception table:
    //   from	to	target	type
    //   7	24	57	finally
    //   27	40	57	finally
    //   40	56	57	finally
    //   58	60	57	finally
  }
  
  public void setWriteAheadLoggingEnabled(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mOpenParamsBuilder : Landroid/database/sqlite/SQLiteDatabase$OpenParams$Builder;
    //   6: invokevirtual isWriteAheadLoggingEnabled : ()Z
    //   9: iload_1
    //   10: if_icmpeq -> 70
    //   13: aload_0
    //   14: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   17: ifnull -> 62
    //   20: aload_0
    //   21: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   24: invokevirtual isOpen : ()Z
    //   27: ifeq -> 62
    //   30: aload_0
    //   31: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   34: invokevirtual isReadOnly : ()Z
    //   37: ifne -> 62
    //   40: iload_1
    //   41: ifeq -> 55
    //   44: aload_0
    //   45: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   48: invokevirtual enableWriteAheadLogging : ()Z
    //   51: pop
    //   52: goto -> 62
    //   55: aload_0
    //   56: getfield mDatabase : Landroid/database/sqlite/SQLiteDatabase;
    //   59: invokevirtual disableWriteAheadLogging : ()V
    //   62: aload_0
    //   63: getfield mOpenParamsBuilder : Landroid/database/sqlite/SQLiteDatabase$OpenParams$Builder;
    //   66: iload_1
    //   67: invokevirtual setWriteAheadLoggingEnabled : (Z)V
    //   70: aload_0
    //   71: getfield mOpenParamsBuilder : Landroid/database/sqlite/SQLiteDatabase$OpenParams$Builder;
    //   74: ldc_w -2147483648
    //   77: invokevirtual removeOpenFlags : (I)Landroid/database/sqlite/SQLiteDatabase$OpenParams$Builder;
    //   80: pop
    //   81: aload_0
    //   82: monitorexit
    //   83: return
    //   84: astore_2
    //   85: aload_0
    //   86: monitorexit
    //   87: aload_2
    //   88: athrow
    // Exception table:
    //   from	to	target	type
    //   2	40	84	finally
    //   44	52	84	finally
    //   55	62	84	finally
    //   62	70	84	finally
    //   70	83	84	finally
    //   85	87	84	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteOpenHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */