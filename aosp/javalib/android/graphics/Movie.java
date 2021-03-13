package android.graphics;

import android.content.res.AssetManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Deprecated
public class Movie {
  private long mNativeMovie;
  
  private Movie(long paramLong) {
    if (paramLong != 0L) {
      this.mNativeMovie = paramLong;
      return;
    } 
    throw new RuntimeException("native movie creation failed");
  }
  
  public static native Movie decodeByteArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2);
  
  public static Movie decodeFile(String paramString) {
    try {
      FileInputStream fileInputStream = new FileInputStream(paramString);
      return decodeTempStream(fileInputStream);
    } catch (FileNotFoundException fileNotFoundException) {
      return null;
    } 
  }
  
  public static Movie decodeStream(InputStream paramInputStream) {
    return (paramInputStream == null) ? null : ((paramInputStream instanceof AssetManager.AssetInputStream) ? nativeDecodeAsset(((AssetManager.AssetInputStream)paramInputStream).getNativeAsset()) : nativeDecodeStream(paramInputStream));
  }
  
  private static Movie decodeTempStream(InputStream paramInputStream) {
    Movie movie = null;
    try {
      Movie movie1 = decodeStream(paramInputStream);
      movie = movie1;
      paramInputStream.close();
      movie = movie1;
    } catch (IOException iOException) {}
    return movie;
  }
  
  private native void nDraw(long paramLong1, float paramFloat1, float paramFloat2, long paramLong2);
  
  private static native Movie nativeDecodeAsset(long paramLong);
  
  private static native Movie nativeDecodeStream(InputStream paramInputStream);
  
  private static native void nativeDestructor(long paramLong);
  
  public void draw(Canvas paramCanvas, float paramFloat1, float paramFloat2) {
    nDraw(paramCanvas.getNativeCanvasWrapper(), paramFloat1, paramFloat2, 0L);
  }
  
  public void draw(Canvas paramCanvas, float paramFloat1, float paramFloat2, Paint paramPaint) {
    long l2;
    long l1 = paramCanvas.getNativeCanvasWrapper();
    if (paramPaint != null) {
      l2 = paramPaint.getNativeInstance();
    } else {
      l2 = 0L;
    } 
    nDraw(l1, paramFloat1, paramFloat2, l2);
  }
  
  public native int duration();
  
  protected void finalize() throws Throwable {
    try {
      nativeDestructor(this.mNativeMovie);
      this.mNativeMovie = 0L;
      return;
    } finally {
      super.finalize();
    } 
  }
  
  public native int height();
  
  public native boolean isOpaque();
  
  public native boolean setTime(int paramInt);
  
  public native int width();
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/Movie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */