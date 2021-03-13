package android.filterfw.core;

import java.io.InputStream;

class DirectByteInputStream extends InputStream {
  private byte[] mBuffer;
  
  private int mPos = 0;
  
  private int mSize;
  
  public DirectByteInputStream(byte[] paramArrayOfbyte, int paramInt) {
    this.mBuffer = paramArrayOfbyte;
    this.mSize = paramInt;
  }
  
  public final int available() {
    return this.mSize - this.mPos;
  }
  
  public final int read() {
    int i = this.mPos;
    if (i < this.mSize) {
      byte[] arrayOfByte = this.mBuffer;
      this.mPos = i + 1;
      i = arrayOfByte[i] & 0xFF;
    } else {
      i = -1;
    } 
    return i;
  }
  
  public final int read(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int i = this.mPos;
    int j = this.mSize;
    if (i >= j)
      return -1; 
    int k = paramInt2;
    if (i + paramInt2 > j)
      k = j - i; 
    System.arraycopy(this.mBuffer, this.mPos, paramArrayOfbyte, paramInt1, k);
    this.mPos += k;
    return k;
  }
  
  public final long skip(long paramLong) {
    int i = this.mPos;
    long l1 = i;
    int j = this.mSize;
    long l2 = paramLong;
    if (l1 + paramLong > j)
      l2 = (j - i); 
    if (l2 < 0L)
      return 0L; 
    this.mPos = (int)(this.mPos + l2);
    return l2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/SerializedFrame$DirectByteInputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */