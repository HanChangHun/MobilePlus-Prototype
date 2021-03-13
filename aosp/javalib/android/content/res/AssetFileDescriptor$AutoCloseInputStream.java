package android.content.res;

import android.os.ParcelFileDescriptor;
import java.io.IOException;

public class AutoCloseInputStream extends ParcelFileDescriptor.AutoCloseInputStream {
  private long mRemaining;
  
  public AutoCloseInputStream(AssetFileDescriptor paramAssetFileDescriptor) throws IOException {
    super(paramAssetFileDescriptor.getParcelFileDescriptor());
    super.skip(paramAssetFileDescriptor.getStartOffset());
    this.mRemaining = (int)paramAssetFileDescriptor.getLength();
  }
  
  public int available() throws IOException {
    int i;
    long l = this.mRemaining;
    if (l >= 0L) {
      if (l < 2147483647L) {
        i = (int)l;
      } else {
        i = Integer.MAX_VALUE;
      } 
    } else {
      i = super.available();
    } 
    return i;
  }
  
  public void mark(int paramInt) {
    if (this.mRemaining >= 0L)
      return; 
    super.mark(paramInt);
  }
  
  public boolean markSupported() {
    return (this.mRemaining >= 0L) ? false : super.markSupported();
  }
  
  public int read() throws IOException {
    byte[] arrayOfByte = new byte[1];
    int i = read(arrayOfByte, 0, 1);
    int j = -1;
    if (i != -1)
      j = arrayOfByte[0] & 0xFF; 
    return j;
  }
  
  public int read(byte[] paramArrayOfbyte) throws IOException {
    return read(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException {
    long l = this.mRemaining;
    if (l >= 0L) {
      if (l == 0L)
        return -1; 
      int i = paramInt2;
      if (paramInt2 > l)
        i = (int)l; 
      paramInt1 = super.read(paramArrayOfbyte, paramInt1, i);
      if (paramInt1 >= 0)
        this.mRemaining -= paramInt1; 
      return paramInt1;
    } 
    return super.read(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void reset() throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mRemaining : J
    //   6: lstore_1
    //   7: lload_1
    //   8: lconst_0
    //   9: lcmp
    //   10: iflt -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: aload_0
    //   17: invokespecial reset : ()V
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: astore_3
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_3
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	23	finally
    //   16	20	23	finally
  }
  
  public long skip(long paramLong) throws IOException {
    long l = this.mRemaining;
    if (l >= 0L) {
      if (l == 0L)
        return -1L; 
      long l1 = paramLong;
      if (paramLong > l)
        l1 = this.mRemaining; 
      paramLong = super.skip(l1);
      if (paramLong >= 0L)
        this.mRemaining -= paramLong; 
      return paramLong;
    } 
    return super.skip(paramLong);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/AssetFileDescriptor$AutoCloseInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */