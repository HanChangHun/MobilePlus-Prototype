package android.graphics;

public final class LargeBitmap {
  private long mNativeLargeBitmap;
  
  private boolean mRecycled;
  
  private LargeBitmap(long paramLong) {
    this.mNativeLargeBitmap = paramLong;
    this.mRecycled = false;
  }
  
  private void checkRecycled(String paramString) {
    if (!this.mRecycled)
      return; 
    throw new IllegalStateException(paramString);
  }
  
  private static native void nativeClean(long paramLong);
  
  private static native Bitmap nativeDecodeRegion(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BitmapFactory.Options paramOptions);
  
  private static native int nativeGetHeight(long paramLong);
  
  private static native int nativeGetWidth(long paramLong);
  
  public Bitmap decodeRegion(Rect paramRect, BitmapFactory.Options paramOptions) {
    checkRecycled("decodeRegion called on recycled large bitmap");
    if (paramRect.left >= 0 && paramRect.top >= 0 && paramRect.right <= getWidth() && paramRect.bottom <= getHeight())
      return nativeDecodeRegion(this.mNativeLargeBitmap, paramRect.left, paramRect.top, paramRect.right - paramRect.left, paramRect.bottom - paramRect.top, paramOptions); 
    throw new IllegalArgumentException("rectangle is not inside the image");
  }
  
  protected void finalize() {
    recycle();
  }
  
  public int getHeight() {
    checkRecycled("getHeight called on recycled large bitmap");
    return nativeGetHeight(this.mNativeLargeBitmap);
  }
  
  public int getWidth() {
    checkRecycled("getWidth called on recycled large bitmap");
    return nativeGetWidth(this.mNativeLargeBitmap);
  }
  
  public final boolean isRecycled() {
    return this.mRecycled;
  }
  
  public void recycle() {
    if (!this.mRecycled) {
      nativeClean(this.mNativeLargeBitmap);
      this.mRecycled = true;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/LargeBitmap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */