package android.graphics;

import android.content.res.AssetManager;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class BitmapRegionDecoder {
  private long mNativeBitmapRegionDecoder;
  
  private final Object mNativeLock = new Object();
  
  private boolean mRecycled;
  
  private BitmapRegionDecoder(long paramLong) {
    this.mNativeBitmapRegionDecoder = paramLong;
    this.mRecycled = false;
  }
  
  private void checkRecycled(String paramString) {
    if (!this.mRecycled)
      return; 
    throw new IllegalStateException(paramString);
  }
  
  private static native void nativeClean(long paramLong);
  
  private static native Bitmap nativeDecodeRegion(long paramLong1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BitmapFactory.Options paramOptions, long paramLong2, long paramLong3);
  
  private static native int nativeGetHeight(long paramLong);
  
  private static native int nativeGetWidth(long paramLong);
  
  private static native BitmapRegionDecoder nativeNewInstance(long paramLong, boolean paramBoolean);
  
  private static native BitmapRegionDecoder nativeNewInstance(FileDescriptor paramFileDescriptor, boolean paramBoolean);
  
  private static native BitmapRegionDecoder nativeNewInstance(InputStream paramInputStream, byte[] paramArrayOfbyte, boolean paramBoolean);
  
  private static native BitmapRegionDecoder nativeNewInstance(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean);
  
  public static BitmapRegionDecoder newInstance(FileDescriptor paramFileDescriptor, boolean paramBoolean) throws IOException {
    return nativeNewInstance(paramFileDescriptor, paramBoolean);
  }
  
  public static BitmapRegionDecoder newInstance(InputStream paramInputStream, boolean paramBoolean) throws IOException {
    return (paramInputStream instanceof AssetManager.AssetInputStream) ? nativeNewInstance(((AssetManager.AssetInputStream)paramInputStream).getNativeAsset(), paramBoolean) : nativeNewInstance(paramInputStream, new byte[16384], paramBoolean);
  }
  
  public static BitmapRegionDecoder newInstance(String paramString, boolean paramBoolean) throws IOException {
    FileInputStream fileInputStream1 = null;
    FileInputStream fileInputStream2 = fileInputStream1;
    try {
      FileInputStream fileInputStream4 = new FileInputStream();
      fileInputStream2 = fileInputStream1;
      this(paramString);
      FileInputStream fileInputStream3 = fileInputStream4;
      fileInputStream2 = fileInputStream3;
      return newInstance(fileInputStream3, paramBoolean);
    } finally {
      if (fileInputStream2 != null)
        try {
          fileInputStream2.close();
        } catch (IOException iOException) {} 
    } 
  }
  
  public static BitmapRegionDecoder newInstance(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) throws IOException {
    if ((paramInt1 | paramInt2) >= 0 && paramArrayOfbyte.length >= paramInt1 + paramInt2)
      return nativeNewInstance(paramArrayOfbyte, paramInt1, paramInt2, paramBoolean); 
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public Bitmap decodeRegion(Rect paramRect, BitmapFactory.Options paramOptions) {
    BitmapFactory.Options.validate(paramOptions);
    synchronized (this.mNativeLock) {
      checkRecycled("decodeRegion called on recycled region decoder");
      if (paramRect.right > 0 && paramRect.bottom > 0 && paramRect.left < getWidth() && paramRect.top < getHeight())
        return nativeDecodeRegion(this.mNativeBitmapRegionDecoder, paramRect.left, paramRect.top, paramRect.right - paramRect.left, paramRect.bottom - paramRect.top, paramOptions, BitmapFactory.Options.nativeInBitmap(paramOptions), BitmapFactory.Options.nativeColorSpace(paramOptions)); 
      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
      this("rectangle is outside the image");
      throw illegalArgumentException;
    } 
  }
  
  protected void finalize() throws Throwable {
    try {
      recycle();
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public int getHeight() {
    synchronized (this.mNativeLock) {
      checkRecycled("getHeight called on recycled region decoder");
      return nativeGetHeight(this.mNativeBitmapRegionDecoder);
    } 
  }
  
  public int getWidth() {
    synchronized (this.mNativeLock) {
      checkRecycled("getWidth called on recycled region decoder");
      return nativeGetWidth(this.mNativeBitmapRegionDecoder);
    } 
  }
  
  public final boolean isRecycled() {
    return this.mRecycled;
  }
  
  public void recycle() {
    synchronized (this.mNativeLock) {
      if (!this.mRecycled) {
        nativeClean(this.mNativeBitmapRegionDecoder);
        this.mRecycled = true;
      } 
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/BitmapRegionDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */