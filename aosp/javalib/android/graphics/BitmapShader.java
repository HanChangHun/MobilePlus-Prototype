package android.graphics;

public class BitmapShader extends Shader {
  public Bitmap mBitmap;
  
  private int mTileX;
  
  private int mTileY;
  
  private BitmapShader(Bitmap paramBitmap, int paramInt1, int paramInt2) {
    if (paramBitmap != null) {
      if (paramBitmap == this.mBitmap && paramInt1 == this.mTileX && paramInt2 == this.mTileY)
        return; 
      this.mBitmap = paramBitmap;
      this.mTileX = paramInt1;
      this.mTileY = paramInt2;
      return;
    } 
    throw new IllegalArgumentException("Bitmap must be non-null");
  }
  
  public BitmapShader(Bitmap paramBitmap, Shader.TileMode paramTileMode1, Shader.TileMode paramTileMode2) {
    this(paramBitmap, paramTileMode1.nativeInt, paramTileMode2.nativeInt);
  }
  
  private static native long nativeCreate(long paramLong1, long paramLong2, int paramInt1, int paramInt2);
  
  long createNativeInstance(long paramLong) {
    return nativeCreate(paramLong, this.mBitmap.getNativeInstance(), this.mTileX, this.mTileY);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/BitmapShader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */