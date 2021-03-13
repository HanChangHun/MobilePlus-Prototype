package android.app;

import android.os.MessageQueue;

final class Idler implements MessageQueue.IdleHandler {
  private final Runnable mCallback;
  
  private boolean mIdle;
  
  public Idler(Runnable paramRunnable) {
    this.mCallback = paramRunnable;
    this.mIdle = false;
  }
  
  public final boolean queueIdle() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mCallback : Ljava/lang/Runnable;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnull -> 15
    //   9: aload_1
    //   10: invokeinterface run : ()V
    //   15: aload_0
    //   16: monitorenter
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield mIdle : Z
    //   22: aload_0
    //   23: invokevirtual notifyAll : ()V
    //   26: aload_0
    //   27: monitorexit
    //   28: iconst_0
    //   29: ireturn
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   17	28	30	finally
    //   31	33	30	finally
  }
  
  public void waitForIdle() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mIdle : Z
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


/* Location:              /home/chun/Desktop/temp/!/android/app/Instrumentation$Idler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */