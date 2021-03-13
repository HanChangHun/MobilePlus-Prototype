package android.content.pm;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import libcore.util.ArrayUtils;

public class LimitedLengthInputStream extends FilterInputStream {
  private final long mEnd;
  
  private long mOffset;
  
  public LimitedLengthInputStream(InputStream paramInputStream, long paramLong1, long paramLong2) throws IOException {
    super(paramInputStream);
    if (paramInputStream != null) {
      if (paramLong1 >= 0L) {
        if (paramLong2 >= 0L) {
          if (paramLong2 <= Long.MAX_VALUE - paramLong1) {
            this.mEnd = paramLong1 + paramLong2;
            skip(paramLong1);
            this.mOffset = paramLong1;
            return;
          } 
          throw new IOException("offset + length > Long.MAX_VALUE");
        } 
        throw new IOException("length < 0");
      } 
      throw new IOException("offset < 0");
    } 
    throw new IOException("in == null");
  }
  
  public int read() throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mOffset : J
    //   6: lstore_1
    //   7: aload_0
    //   8: getfield mEnd : J
    //   11: lstore_3
    //   12: lload_1
    //   13: lload_3
    //   14: lcmp
    //   15: iflt -> 22
    //   18: aload_0
    //   19: monitorexit
    //   20: iconst_m1
    //   21: ireturn
    //   22: aload_0
    //   23: aload_0
    //   24: getfield mOffset : J
    //   27: lconst_1
    //   28: ladd
    //   29: putfield mOffset : J
    //   32: aload_0
    //   33: invokespecial read : ()I
    //   36: istore #5
    //   38: aload_0
    //   39: monitorexit
    //   40: iload #5
    //   42: ireturn
    //   43: astore #6
    //   45: aload_0
    //   46: monitorexit
    //   47: aload #6
    //   49: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	43	finally
    //   22	38	43	finally
  }
  
  public int read(byte[] paramArrayOfbyte) throws IOException {
    return read(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    if (this.mOffset >= this.mEnd)
      return -1; 
    ArrayUtils.throwsIfOutOfBounds(paramArrayOfbyte.length, paramInt1, paramInt2);
    long l = this.mOffset;
    if (l <= Long.MAX_VALUE - paramInt2) {
      long l1 = paramInt2;
      long l2 = this.mEnd;
      if (l1 + l > l2)
        paramInt2 = (int)(l2 - l); 
      paramInt1 = super.read(paramArrayOfbyte, paramInt1, paramInt2);
      this.mOffset += paramInt1;
      return paramInt1;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("offset out of bounds: ");
    stringBuilder.append(this.mOffset);
    stringBuilder.append(" + ");
    stringBuilder.append(paramInt2);
    throw new IOException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LimitedLengthInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */