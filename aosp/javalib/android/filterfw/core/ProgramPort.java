package android.filterfw.core;

import java.lang.reflect.Field;

public class ProgramPort extends FieldPort {
  protected String mVarName;
  
  public ProgramPort(Filter paramFilter, String paramString1, String paramString2, Field paramField, boolean paramBoolean) {
    super(paramFilter, paramString1, paramField, paramBoolean);
    this.mVarName = paramString2;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Program ");
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
    //   8: ifeq -> 148
    //   11: aload_0
    //   12: getfield mField : Ljava/lang/reflect/Field;
    //   15: aload_0
    //   16: getfield mFilter : Landroid/filterfw/core/Filter;
    //   19: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   22: astore_1
    //   23: aload_1
    //   24: ifnull -> 47
    //   27: aload_1
    //   28: checkcast android/filterfw/core/Program
    //   31: aload_0
    //   32: getfield mVarName : Ljava/lang/String;
    //   35: aload_0
    //   36: getfield mValue : Ljava/lang/Object;
    //   39: invokevirtual setHostValue : (Ljava/lang/String;Ljava/lang/Object;)V
    //   42: aload_0
    //   43: iconst_0
    //   44: putfield mValueWaiting : Z
    //   47: goto -> 148
    //   50: astore_1
    //   51: new java/lang/RuntimeException
    //   54: astore_3
    //   55: new java/lang/StringBuilder
    //   58: astore_1
    //   59: aload_1
    //   60: invokespecial <init> : ()V
    //   63: aload_1
    //   64: ldc 'Non Program field ''
    //   66: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: pop
    //   70: aload_1
    //   71: aload_0
    //   72: getfield mField : Ljava/lang/reflect/Field;
    //   75: invokevirtual getName : ()Ljava/lang/String;
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: aload_1
    //   83: ldc '' annotated with ProgramParameter!'
    //   85: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: aload_3
    //   90: aload_1
    //   91: invokevirtual toString : ()Ljava/lang/String;
    //   94: invokespecial <init> : (Ljava/lang/String;)V
    //   97: aload_3
    //   98: athrow
    //   99: astore_1
    //   100: new java/lang/RuntimeException
    //   103: astore_3
    //   104: new java/lang/StringBuilder
    //   107: astore_1
    //   108: aload_1
    //   109: invokespecial <init> : ()V
    //   112: aload_1
    //   113: ldc 'Access to program field ''
    //   115: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: aload_1
    //   120: aload_0
    //   121: getfield mField : Ljava/lang/reflect/Field;
    //   124: invokevirtual getName : ()Ljava/lang/String;
    //   127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: pop
    //   131: aload_1
    //   132: ldc '' was denied!'
    //   134: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: pop
    //   138: aload_3
    //   139: aload_1
    //   140: invokevirtual toString : ()Ljava/lang/String;
    //   143: invokespecial <init> : (Ljava/lang/String;)V
    //   146: aload_3
    //   147: athrow
    //   148: aload_0
    //   149: monitorexit
    //   150: return
    //   151: astore_1
    //   152: aload_0
    //   153: monitorexit
    //   154: aload_1
    //   155: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	151	finally
    //   11	23	99	java/lang/IllegalAccessException
    //   11	23	50	java/lang/ClassCastException
    //   11	23	151	finally
    //   27	47	99	java/lang/IllegalAccessException
    //   27	47	50	java/lang/ClassCastException
    //   27	47	151	finally
    //   51	99	151	finally
    //   100	148	151	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/ProgramPort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */