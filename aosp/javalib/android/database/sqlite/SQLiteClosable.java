package android.database.sqlite;

import java.io.Closeable;

public abstract class SQLiteClosable implements Closeable {
  private int mReferenceCount = 1;
  
  public void acquireReference() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mReferenceCount : I
    //   6: ifle -> 22
    //   9: aload_0
    //   10: aload_0
    //   11: getfield mReferenceCount : I
    //   14: iconst_1
    //   15: iadd
    //   16: putfield mReferenceCount : I
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: new java/lang/IllegalStateException
    //   25: astore_1
    //   26: new java/lang/StringBuilder
    //   29: astore_2
    //   30: aload_2
    //   31: invokespecial <init> : ()V
    //   34: aload_2
    //   35: ldc 'attempt to re-open an already-closed object: '
    //   37: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: pop
    //   41: aload_2
    //   42: aload_0
    //   43: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: aload_1
    //   48: aload_2
    //   49: invokevirtual toString : ()Ljava/lang/String;
    //   52: invokespecial <init> : (Ljava/lang/String;)V
    //   55: aload_1
    //   56: athrow
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	57	finally
    //   22	57	57	finally
    //   58	60	57	finally
  }
  
  public void close() {
    releaseReference();
  }
  
  protected abstract void onAllReferencesReleased();
  
  @Deprecated
  protected void onAllReferencesReleasedFromContainer() {
    onAllReferencesReleased();
  }
  
  public void releaseReference() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mReferenceCount : I
    //   6: istore_1
    //   7: iconst_1
    //   8: istore_2
    //   9: iinc #1, -1
    //   12: aload_0
    //   13: iload_1
    //   14: putfield mReferenceCount : I
    //   17: iload_1
    //   18: ifne -> 24
    //   21: goto -> 26
    //   24: iconst_0
    //   25: istore_2
    //   26: aload_0
    //   27: monitorexit
    //   28: iload_2
    //   29: ifeq -> 36
    //   32: aload_0
    //   33: invokevirtual onAllReferencesReleased : ()V
    //   36: return
    //   37: astore_3
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_3
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	37	finally
    //   12	17	37	finally
    //   26	28	37	finally
    //   38	40	37	finally
  }
  
  @Deprecated
  public void releaseReferenceFromContainer() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mReferenceCount : I
    //   6: istore_1
    //   7: iconst_1
    //   8: istore_2
    //   9: iinc #1, -1
    //   12: aload_0
    //   13: iload_1
    //   14: putfield mReferenceCount : I
    //   17: iload_1
    //   18: ifne -> 24
    //   21: goto -> 26
    //   24: iconst_0
    //   25: istore_2
    //   26: aload_0
    //   27: monitorexit
    //   28: iload_2
    //   29: ifeq -> 36
    //   32: aload_0
    //   33: invokevirtual onAllReferencesReleasedFromContainer : ()V
    //   36: return
    //   37: astore_3
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_3
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	37	finally
    //   12	17	37	finally
    //   26	28	37	finally
    //   38	40	37	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/sqlite/SQLiteClosable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */