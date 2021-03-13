package android.graphics;

public class TemporaryBuffer {
  private static char[] sTemp = null;
  
  public static char[] obtain(int paramInt) {
    // Byte code:
    //   0: ldc android/graphics/TemporaryBuffer
    //   2: monitorenter
    //   3: getstatic android/graphics/TemporaryBuffer.sTemp : [C
    //   6: astore_1
    //   7: aconst_null
    //   8: putstatic android/graphics/TemporaryBuffer.sTemp : [C
    //   11: ldc android/graphics/TemporaryBuffer
    //   13: monitorexit
    //   14: aload_1
    //   15: ifnull -> 26
    //   18: aload_1
    //   19: astore_2
    //   20: aload_1
    //   21: arraylength
    //   22: iload_0
    //   23: if_icmpge -> 31
    //   26: iload_0
    //   27: invokestatic newUnpaddedCharArray : (I)[C
    //   30: astore_2
    //   31: aload_2
    //   32: areturn
    //   33: astore_2
    //   34: ldc android/graphics/TemporaryBuffer
    //   36: monitorexit
    //   37: aload_2
    //   38: athrow
    // Exception table:
    //   from	to	target	type
    //   3	14	33	finally
    //   34	37	33	finally
  }
  
  public static void recycle(char[] paramArrayOfchar) {
    // Byte code:
    //   0: aload_0
    //   1: arraylength
    //   2: sipush #1000
    //   5: if_icmple -> 9
    //   8: return
    //   9: ldc android/graphics/TemporaryBuffer
    //   11: monitorenter
    //   12: aload_0
    //   13: putstatic android/graphics/TemporaryBuffer.sTemp : [C
    //   16: ldc android/graphics/TemporaryBuffer
    //   18: monitorexit
    //   19: return
    //   20: astore_0
    //   21: ldc android/graphics/TemporaryBuffer
    //   23: monitorexit
    //   24: aload_0
    //   25: athrow
    // Exception table:
    //   from	to	target	type
    //   12	19	20	finally
    //   21	24	20	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/TemporaryBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */