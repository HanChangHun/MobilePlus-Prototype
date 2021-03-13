package android.graphics;

import java.io.OutputStream;

public class YuvImage {
  private static final int WORKING_COMPRESS_STORAGE = 4096;
  
  private byte[] mData;
  
  private int mFormat;
  
  private int mHeight;
  
  private int[] mStrides;
  
  private int mWidth;
  
  public YuvImage(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfint) {
    if (paramInt1 == 17 || paramInt1 == 20) {
      if (paramInt2 > 0 && paramInt3 > 0) {
        if (paramArrayOfbyte != null) {
          if (paramArrayOfint == null) {
            this.mStrides = calculateStrides(paramInt2, paramInt1);
          } else {
            this.mStrides = paramArrayOfint;
          } 
          this.mData = paramArrayOfbyte;
          this.mFormat = paramInt1;
          this.mWidth = paramInt2;
          this.mHeight = paramInt3;
          return;
        } 
        throw new IllegalArgumentException("yuv cannot be null");
      } 
      throw new IllegalArgumentException("width and height must large than 0");
    } 
    throw new IllegalArgumentException("only support ImageFormat.NV21 and ImageFormat.YUY2 for now");
  }
  
  private void adjustRectangle(Rect paramRect) {
    int i = paramRect.width();
    int j = paramRect.height();
    int k = i;
    if (this.mFormat == 17) {
      k = i & 0xFFFFFFFE;
      paramRect.left &= 0xFFFFFFFE;
      paramRect.top &= 0xFFFFFFFE;
      paramRect.right = paramRect.left + k;
      paramRect.bottom = paramRect.top + (j & 0xFFFFFFFE);
    } 
    if (this.mFormat == 20) {
      paramRect.left &= 0xFFFFFFFE;
      paramRect.right = paramRect.left + (k & 0xFFFFFFFE);
    } 
  }
  
  private int[] calculateStrides(int paramInt1, int paramInt2) {
    return (paramInt2 == 17) ? new int[] { paramInt1, paramInt1 } : ((paramInt2 == 20) ? new int[] { paramInt1 * 2 } : null);
  }
  
  private static native boolean nativeCompressToJpeg(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt4, OutputStream paramOutputStream, byte[] paramArrayOfbyte2);
  
  int[] calculateOffsets(int paramInt1, int paramInt2) {
    int i = this.mFormat;
    if (i == 17) {
      int[] arrayOfInt = this.mStrides;
      return new int[] { arrayOfInt[0] * paramInt2 + paramInt1, this.mHeight * arrayOfInt[0] + paramInt2 / 2 * arrayOfInt[1] + paramInt1 / 2 * 2 };
    } 
    return (i == 20) ? new int[] { this.mStrides[0] * paramInt2 + paramInt1 / 2 * 4 } : null;
  }
  
  public boolean compressToJpeg(Rect paramRect, int paramInt, OutputStream paramOutputStream) {
    if ((new Rect(0, 0, this.mWidth, this.mHeight)).contains(paramRect)) {
      if (paramInt >= 0 && paramInt <= 100) {
        if (paramOutputStream != null) {
          adjustRectangle(paramRect);
          int[] arrayOfInt = calculateOffsets(paramRect.left, paramRect.top);
          return nativeCompressToJpeg(this.mData, this.mFormat, paramRect.width(), paramRect.height(), arrayOfInt, this.mStrides, paramInt, paramOutputStream, new byte[4096]);
        } 
        throw new IllegalArgumentException("stream cannot be null");
      } 
      throw new IllegalArgumentException("quality must be 0..100");
    } 
    throw new IllegalArgumentException("rectangle is not inside the image");
  }
  
  public int getHeight() {
    return this.mHeight;
  }
  
  public int[] getStrides() {
    return this.mStrides;
  }
  
  public int getWidth() {
    return this.mWidth;
  }
  
  public byte[] getYuvData() {
    return this.mData;
  }
  
  public int getYuvFormat() {
    return this.mFormat;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/YuvImage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */