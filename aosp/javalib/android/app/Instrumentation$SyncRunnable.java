package android.app;

final class SyncRunnable implements Runnable {
  private boolean mComplete;
  
  private final Runnable mTarget;
  
  public SyncRunnable(Runnable paramRunnable) {
    this.mTarget = paramRunnable;
  }
  
  public void run() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mTarget : Ljava/lang/Runnable;
    //   4: invokeinterface run : ()V
    //   9: aload_0
    //   10: monitorenter
    //   11: aload_0
    //   12: iconst_1
    //   13: putfield mComplete : Z
    //   16: aload_0
    //   17: invokevirtual notifyAll : ()V
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   11	22	23	finally
    //   24	26	23	finally
  }
  
  public void waitForComplete() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mComplete : Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifne -> 22
    //   11: aload_0
    //   12: invokevirtual wait : ()V
    //   15: goto -> 2
    //   18: astore_2
    //   19: goto -> 15
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_2
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_2
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	25	finally
    //   11	15	18	java/lang/InterruptedException
    //   11	15	25	finally
    //   22	24	25	finally
    //   26	28	25	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Instrumentation$SyncRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */