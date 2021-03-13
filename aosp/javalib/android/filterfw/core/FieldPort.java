package android.filterfw.core;

import java.lang.reflect.Field;

public class FieldPort extends InputPort {
  protected Field mField;
  
  protected boolean mHasFrame;
  
  protected Object mValue;
  
  protected boolean mValueWaiting = false;
  
  public FieldPort(Filter paramFilter, String paramString, Field paramField, boolean paramBoolean) {
    super(paramFilter, paramString);
    this.mField = paramField;
    this.mHasFrame = paramBoolean;
  }
  
  public boolean acceptsFrame() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mValueWaiting : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: iconst_1
    //   11: ixor
    //   12: ireturn
    //   13: astore_2
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_2
    //   17: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	13	finally
  }
  
  public void clear() {}
  
  public Object getTarget() {
    try {
      return this.mField.get(this.mFilter);
    } catch (IllegalAccessException illegalAccessException) {
      return null;
    } 
  }
  
  public boolean hasFrame() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mHasFrame : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public Frame pullFrame() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new java/lang/RuntimeException
    //   5: astore_1
    //   6: new java/lang/StringBuilder
    //   9: astore_2
    //   10: aload_2
    //   11: invokespecial <init> : ()V
    //   14: aload_2
    //   15: ldc 'Cannot pull frame on '
    //   17: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: pop
    //   21: aload_2
    //   22: aload_0
    //   23: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   26: pop
    //   27: aload_2
    //   28: ldc '!'
    //   30: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: pop
    //   34: aload_1
    //   35: aload_2
    //   36: invokevirtual toString : ()Ljava/lang/String;
    //   39: invokespecial <init> : (Ljava/lang/String;)V
    //   42: aload_1
    //   43: athrow
    //   44: astore_2
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_2
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   2	44	44	finally
  }
  
  public void pushFrame(Frame paramFrame) {
    setFieldFrame(paramFrame, false);
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
    //   12: aload_1
    //   13: invokevirtual getObjectValue : ()Ljava/lang/Object;
    //   16: astore_1
    //   17: aload_1
    //   18: ifnonnull -> 28
    //   21: aload_0
    //   22: getfield mValue : Ljava/lang/Object;
    //   25: ifnonnull -> 39
    //   28: aload_1
    //   29: aload_0
    //   30: getfield mValue : Ljava/lang/Object;
    //   33: invokevirtual equals : (Ljava/lang/Object;)Z
    //   36: ifne -> 49
    //   39: aload_0
    //   40: aload_1
    //   41: putfield mValue : Ljava/lang/Object;
    //   44: aload_0
    //   45: iconst_1
    //   46: putfield mValueWaiting : Z
    //   49: aload_0
    //   50: iconst_1
    //   51: putfield mHasFrame : Z
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
    //   2	17	57	finally
    //   21	28	57	finally
    //   28	39	57	finally
    //   39	49	57	finally
    //   49	54	57	finally
  }
  
  public void setFrame(Frame paramFrame) {
    setFieldFrame(paramFrame, true);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("field ");
    stringBuilder.append(super.toString());
    return stringBuilder.toString();
  }
  
  public void transfer(FilterContext paramFilterContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mValueWaiting : Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifeq -> 99
    //   11: aload_0
    //   12: getfield mField : Ljava/lang/reflect/Field;
    //   15: aload_0
    //   16: getfield mFilter : Landroid/filterfw/core/Filter;
    //   19: aload_0
    //   20: getfield mValue : Ljava/lang/Object;
    //   23: invokevirtual set : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   26: aload_0
    //   27: iconst_0
    //   28: putfield mValueWaiting : Z
    //   31: aload_1
    //   32: ifnull -> 99
    //   35: aload_0
    //   36: getfield mFilter : Landroid/filterfw/core/Filter;
    //   39: aload_0
    //   40: getfield mName : Ljava/lang/String;
    //   43: aload_1
    //   44: invokevirtual notifyFieldPortValueUpdated : (Ljava/lang/String;Landroid/filterfw/core/FilterContext;)V
    //   47: goto -> 99
    //   50: astore_1
    //   51: new java/lang/RuntimeException
    //   54: astore_3
    //   55: new java/lang/StringBuilder
    //   58: astore_1
    //   59: aload_1
    //   60: invokespecial <init> : ()V
    //   63: aload_1
    //   64: ldc 'Access to field ''
    //   66: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: pop
    //   70: aload_1
    //   71: aload_0
    //   72: getfield mField : Ljava/lang/reflect/Field;
    //   75: invokevirtual getName : ()Ljava/lang/String;
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: aload_1
    //   83: ldc '' was denied!'
    //   85: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: aload_3
    //   90: aload_1
    //   91: invokevirtual toString : ()Ljava/lang/String;
    //   94: invokespecial <init> : (Ljava/lang/String;)V
    //   97: aload_3
    //   98: athrow
    //   99: aload_0
    //   100: monitorexit
    //   101: return
    //   102: astore_1
    //   103: aload_0
    //   104: monitorexit
    //   105: aload_1
    //   106: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	102	finally
    //   11	26	50	java/lang/IllegalAccessException
    //   11	26	102	finally
    //   26	31	102	finally
    //   35	47	102	finally
    //   51	99	102	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/FieldPort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */