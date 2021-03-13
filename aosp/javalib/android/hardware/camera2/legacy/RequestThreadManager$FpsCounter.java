package android.hardware.camera2.legacy;

public class FpsCounter {
  private static final long NANO_PER_SECOND = 1000000000L;
  
  private static final String TAG = "FpsCounter";
  
  private int mFrameCount = 0;
  
  private double mLastFps = 0.0D;
  
  private long mLastPrintTime = 0L;
  
  private long mLastTime = 0L;
  
  private final String mStreamType;
  
  public FpsCounter(String paramString) {
    this.mStreamType = paramString;
  }
  
  public double checkFps() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mLastFps : D
    //   6: dstore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: dload_1
    //   10: dreturn
    //   11: astore_3
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_3
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public void countAndLog() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual countFrame : ()V
    //   6: aload_0
    //   7: invokevirtual staggeredLog : ()V
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	13	finally
  }
  
  public void countFrame() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield mFrameCount : I
    //   7: iconst_1
    //   8: iadd
    //   9: putfield mFrameCount : I
    //   12: invokestatic elapsedRealtimeNanos : ()J
    //   15: lstore_1
    //   16: aload_0
    //   17: getfield mLastTime : J
    //   20: lconst_0
    //   21: lcmp
    //   22: ifne -> 30
    //   25: aload_0
    //   26: lload_1
    //   27: putfield mLastTime : J
    //   30: lload_1
    //   31: aload_0
    //   32: getfield mLastTime : J
    //   35: ldc2_w 1000000000
    //   38: ladd
    //   39: lcmp
    //   40: ifle -> 76
    //   43: aload_0
    //   44: getfield mLastTime : J
    //   47: lstore_3
    //   48: aload_0
    //   49: aload_0
    //   50: getfield mFrameCount : I
    //   53: i2d
    //   54: ldc2_w 1.0E9
    //   57: lload_1
    //   58: lload_3
    //   59: lsub
    //   60: l2d
    //   61: ddiv
    //   62: dmul
    //   63: putfield mLastFps : D
    //   66: aload_0
    //   67: iconst_0
    //   68: putfield mFrameCount : I
    //   71: aload_0
    //   72: lload_1
    //   73: putfield mLastTime : J
    //   76: aload_0
    //   77: monitorexit
    //   78: return
    //   79: astore #5
    //   81: aload_0
    //   82: monitorexit
    //   83: aload #5
    //   85: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	79	finally
    //   30	76	79	finally
  }
  
  public void staggeredLog() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mLastTime : J
    //   6: aload_0
    //   7: getfield mLastPrintTime : J
    //   10: ldc2_w 5000000000
    //   13: ladd
    //   14: lcmp
    //   15: ifle -> 76
    //   18: aload_0
    //   19: aload_0
    //   20: getfield mLastTime : J
    //   23: putfield mLastPrintTime : J
    //   26: new java/lang/StringBuilder
    //   29: astore_1
    //   30: aload_1
    //   31: invokespecial <init> : ()V
    //   34: aload_1
    //   35: ldc 'FPS for '
    //   37: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: pop
    //   41: aload_1
    //   42: aload_0
    //   43: getfield mStreamType : Ljava/lang/String;
    //   46: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: aload_1
    //   51: ldc ' stream: '
    //   53: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: pop
    //   57: aload_1
    //   58: aload_0
    //   59: getfield mLastFps : D
    //   62: invokevirtual append : (D)Ljava/lang/StringBuilder;
    //   65: pop
    //   66: ldc 'FpsCounter'
    //   68: aload_1
    //   69: invokevirtual toString : ()Ljava/lang/String;
    //   72: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   75: pop
    //   76: aload_0
    //   77: monitorexit
    //   78: return
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: aload_1
    //   83: athrow
    // Exception table:
    //   from	to	target	type
    //   2	76	79	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestThreadManager$FpsCounter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */