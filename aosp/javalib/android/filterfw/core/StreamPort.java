package android.filterfw.core;

public class StreamPort extends InputPort {
  private Frame mFrame;
  
  private boolean mPersistent;
  
  public StreamPort(Filter paramFilter, String paramString) {
    super(paramFilter, paramString);
  }
  
  protected void assignFrame(Frame paramFrame, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual assertPortIsOpen : ()V
    //   6: aload_0
    //   7: aload_1
    //   8: iload_2
    //   9: invokevirtual checkFrameType : (Landroid/filterfw/core/Frame;Z)V
    //   12: iload_2
    //   13: ifeq -> 34
    //   16: aload_0
    //   17: getfield mFrame : Landroid/filterfw/core/Frame;
    //   20: ifnull -> 41
    //   23: aload_0
    //   24: getfield mFrame : Landroid/filterfw/core/Frame;
    //   27: invokevirtual release : ()Landroid/filterfw/core/Frame;
    //   30: pop
    //   31: goto -> 41
    //   34: aload_0
    //   35: getfield mFrame : Landroid/filterfw/core/Frame;
    //   38: ifnonnull -> 63
    //   41: aload_1
    //   42: invokevirtual retain : ()Landroid/filterfw/core/Frame;
    //   45: astore_1
    //   46: aload_0
    //   47: aload_1
    //   48: putfield mFrame : Landroid/filterfw/core/Frame;
    //   51: aload_1
    //   52: invokevirtual markReadOnly : ()V
    //   55: aload_0
    //   56: iload_2
    //   57: putfield mPersistent : Z
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    //   63: new java/lang/RuntimeException
    //   66: astore_3
    //   67: new java/lang/StringBuilder
    //   70: astore_1
    //   71: aload_1
    //   72: invokespecial <init> : ()V
    //   75: aload_1
    //   76: ldc 'Attempting to push more than one frame on port: '
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: aload_1
    //   83: aload_0
    //   84: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload_1
    //   89: ldc '!'
    //   91: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: pop
    //   95: aload_3
    //   96: aload_1
    //   97: invokevirtual toString : ()Ljava/lang/String;
    //   100: invokespecial <init> : (Ljava/lang/String;)V
    //   103: aload_3
    //   104: athrow
    //   105: astore_1
    //   106: aload_0
    //   107: monitorexit
    //   108: aload_1
    //   109: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	105	finally
    //   16	31	105	finally
    //   34	41	105	finally
    //   41	60	105	finally
    //   63	105	105	finally
  }
  
  public void clear() {
    Frame frame = this.mFrame;
    if (frame != null) {
      frame.release();
      this.mFrame = null;
    } 
  }
  
  public boolean hasFrame() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mFrame : Landroid/filterfw/core/Frame;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull -> 16
    //   11: iconst_1
    //   12: istore_2
    //   13: goto -> 18
    //   16: iconst_0
    //   17: istore_2
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_2
    //   21: ireturn
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
  }
  
  public Frame pullFrame() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mFrame : Landroid/filterfw/core/Frame;
    //   6: ifnull -> 41
    //   9: aload_0
    //   10: getfield mFrame : Landroid/filterfw/core/Frame;
    //   13: astore_1
    //   14: aload_0
    //   15: getfield mPersistent : Z
    //   18: ifeq -> 32
    //   21: aload_0
    //   22: getfield mFrame : Landroid/filterfw/core/Frame;
    //   25: invokevirtual retain : ()Landroid/filterfw/core/Frame;
    //   28: pop
    //   29: goto -> 37
    //   32: aload_0
    //   33: aconst_null
    //   34: putfield mFrame : Landroid/filterfw/core/Frame;
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: areturn
    //   41: new java/lang/RuntimeException
    //   44: astore_2
    //   45: new java/lang/StringBuilder
    //   48: astore_1
    //   49: aload_1
    //   50: invokespecial <init> : ()V
    //   53: aload_1
    //   54: ldc 'No frame available to pull on port: '
    //   56: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: pop
    //   60: aload_1
    //   61: aload_0
    //   62: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   65: pop
    //   66: aload_1
    //   67: ldc '!'
    //   69: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: aload_2
    //   74: aload_1
    //   75: invokevirtual toString : ()Ljava/lang/String;
    //   78: invokespecial <init> : (Ljava/lang/String;)V
    //   81: aload_2
    //   82: athrow
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Exception table:
    //   from	to	target	type
    //   2	29	83	finally
    //   32	37	83	finally
    //   41	83	83	finally
  }
  
  public void pushFrame(Frame paramFrame) {
    assignFrame(paramFrame, false);
  }
  
  public void setFrame(Frame paramFrame) {
    assignFrame(paramFrame, true);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("input ");
    stringBuilder.append(super.toString());
    return stringBuilder.toString();
  }
  
  public void transfer(FilterContext paramFilterContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mFrame : Landroid/filterfw/core/Frame;
    //   6: ifnull -> 18
    //   9: aload_0
    //   10: aload_0
    //   11: getfield mFrame : Landroid/filterfw/core/Frame;
    //   14: aload_1
    //   15: invokevirtual checkFrameManager : (Landroid/filterfw/core/Frame;Landroid/filterfw/core/FilterContext;)V
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	21	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/StreamPort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */