package android.database.sqlite;

import android.database.DatabaseErrorHandler;
import com.android.internal.util.Preconditions;
import java.util.Objects;

public final class OpenParams {
  private final SQLiteDatabase.CursorFactory mCursorFactory;
  
  private final DatabaseErrorHandler mErrorHandler;
  
  private final long mIdleConnectionTimeout;
  
  private final String mJournalMode;
  
  private final int mLookasideSlotCount;
  
  private final int mLookasideSlotSize;
  
  private final int mOpenFlags;
  
  private final String mSyncMode;
  
  private OpenParams(int paramInt1, SQLiteDatabase.CursorFactory paramCursorFactory, DatabaseErrorHandler paramDatabaseErrorHandler, int paramInt2, int paramInt3, long paramLong, String paramString1, String paramString2) {
    this.mOpenFlags = paramInt1;
    this.mCursorFactory = paramCursorFactory;
    this.mErrorHandler = paramDatabaseErrorHandler;
    this.mLookasideSlotSize = paramInt2;
    this.mLookasideSlotCount = paramInt3;
    this.mIdleConnectionTimeout = paramLong;
    this.mJournalMode = paramString1;
    this.mSyncMode = paramString2;
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


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteDatabase$OpenParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */