package android.filterfw.core;

import java.io.OutputStream;

class DirectByteOutputStream extends OutputStream {
  private byte[] mBuffer = null;
  
  private int mDataOffset = 0;
  
  private int mOffset = 0;
  
  public DirectByteOutputStream(int paramInt) {
    this.mBuffer = new byte[paramInt];
  }
  
  private final void ensureFit(int paramInt) {
    int i = this.mOffset;
    byte[] arrayOfByte = this.mBuffer;
    if (i + paramInt > arrayOfByte.length) {
      byte[] arrayOfByte1 = this.mBuffer;
      arrayOfByte = new byte[Math.max(i + paramInt, arrayOfByte.length * 2)];
      this.mBuffer = arrayOfByte;
      System.arraycopy(arrayOfByte1, 0, arrayOfByte, 0, this.mOffset);
    } 
  }
  
  public byte[] getByteArray() {
    return this.mBuffer;
  }
  
  public final SerializedFrame.DirectByteInputStream getInputStream() {
    return new SerializedFrame.DirectByteInputStream(SerializedFrame.this, this.mBuffer, this.mOffset);
  }
  
  public final int getSize() {
    return this.mOffset;
  }
  
  public final void markHeaderEnd() {
    this.mDataOffset = this.mOffset;
  }
  
  public final void reset() {
    this.mOffset = this.mDataOffset;
  }
  
  public final void write(int paramInt) {
    ensureFit(1);
    byte[] arrayOfByte = this.mBuffer;
    int i = this.mOffset;
    this.mOffset = i + 1;
    arrayOfByte[i] = (byte)(byte)paramInt;
  }
  
  public final void write(byte[] paramArrayOfbyte) {
    write(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public final void write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    ensureFit(paramInt2);
    System.arraycopy(paramArrayOfbyte, paramInt1, this.mBuffer, this.mOffset, paramInt2);
    this.mOffset += paramInt2;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/SerializedFrame$DirectByteOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */