package android.filterfw.core;

import java.lang.reflect.Field;

public class FinalPort extends FieldPort {
  public FinalPort(Filter paramFilter, String paramString, Field paramField, boolean paramBoolean) {
    super(paramFilter, paramString, paramField, paramBoolean);
  }
  
  protected void setFieldFrame(Frame paramFrame, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual assertPortIsOpen : ()V
    //   6: aload_0
    //   7: aload_1
    //   8: iload_2
    //   9: invokevirtual checkFrameType : (Landroid/filterfw/core/Frame;Z)V
    //   12: aload_0
    //   13: getfield mFilter : Landroid/filterfw/core/Filter;
    //   16: invokevirtual getStatus : ()I
    //   19: ifne -> 36
    //   22: aload_0
    //   23: aload_1
    //   24: iload_2
    //   25: invokespecial setFieldFrame : (Landroid/filterfw/core/Frame;Z)V
    //   28: aload_0
    //   29: aconst_null
    //   30: invokespecial transfer : (Landroid/filterfw/core/FilterContext;)V
    //   33: aload_0
    //   34: monitorexit
    //   35: return
    //   36: new java/lang/RuntimeException
    //   39: astore_1
    //   40: new java/lang/StringBuilder
    //   43: astore_3
    //   44: aload_3
    //   45: invokespecial <init> : ()V
    //   48: aload_3
    //   49: ldc 'Attempting to modify '
    //   51: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: pop
    //   55: aload_3
    //   56: aload_0
    //   57: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: aload_3
    //   62: ldc '!'
    //   64: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: aload_1
    //   69: aload_3
    //   70: invokevirtual toString : ()Ljava/lang/String;
    //   73: invokespecial <init> : (Ljava/lang/String;)V
    //   76: aload_1
    //   77: athrow
    //   78: astore_1
    //   79: aload_0
    //   80: monitorexit
    //   81: aload_1
    //   82: athrow
    // Exception table:
    //   from	to	target	type
    //   2	33	78	finally
    //   36	78	78	finally
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("final ");
    stringBuilder.append(super.toString());
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/FinalPort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */