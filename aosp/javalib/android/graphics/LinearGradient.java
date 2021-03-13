package android.graphics;

public class LinearGradient extends Shader {
  private int mColor0;
  
  private int mColor1;
  
  private final long[] mColorLongs;
  
  private int[] mColors;
  
  private float[] mPositions;
  
  private Shader.TileMode mTileMode;
  
  private float mX0;
  
  private float mX1;
  
  private float mY0;
  
  private float mY1;
  
  public LinearGradient(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2, Shader.TileMode paramTileMode) {
    this(paramFloat1, paramFloat2, paramFloat3, paramFloat4, Color.pack(paramInt1), Color.pack(paramInt2), paramTileMode);
  }
  
  public LinearGradient(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong1, long paramLong2, Shader.TileMode paramTileMode) {
    this(paramFloat1, paramFloat2, paramFloat3, paramFloat4, new long[] { paramLong1, paramLong2 }, (float[])null, paramTileMode);
  }
  
  public LinearGradient(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int[] paramArrayOfint, float[] paramArrayOffloat, Shader.TileMode paramTileMode) {
    this(paramFloat1, paramFloat2, paramFloat3, paramFloat4, convertColors(paramArrayOfint), paramArrayOffloat, paramTileMode, ColorSpace.get(ColorSpace.Named.SRGB));
  }
  
  public LinearGradient(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long[] paramArrayOflong, float[] paramArrayOffloat, Shader.TileMode paramTileMode) {
    this(paramFloat1, paramFloat2, paramFloat3, paramFloat4, (long[])paramArrayOflong.clone(), paramArrayOffloat, paramTileMode, detectColorSpace(paramArrayOflong));
  }
  
  private LinearGradient(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long[] paramArrayOflong, float[] paramArrayOffloat, Shader.TileMode paramTileMode, ColorSpace paramColorSpace) {
    super(paramColorSpace);
    if (paramArrayOffloat == null || paramArrayOflong.length == paramArrayOffloat.length) {
      this.mX0 = paramFloat1;
      this.mY0 = paramFloat2;
      this.mX1 = paramFloat3;
      this.mY1 = paramFloat4;
      this.mColorLongs = paramArrayOflong;
      if (paramArrayOffloat != null) {
        float[] arrayOfFloat = (float[])paramArrayOffloat.clone();
      } else {
        paramArrayOflong = null;
      } 
      this.mPositions = (float[])paramArrayOflong;
      this.mTileMode = paramTileMode;
      return;
    } 
    throw new IllegalArgumentException("color and position arrays must be of equal length");
  }
  
  private native long nativeCreate(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long[] paramArrayOflong, float[] paramArrayOffloat, int paramInt, long paramLong2);
  
  long createNativeInstance(long paramLong) {
    return nativeCreate(paramLong, this.mX0, this.mY0, this.mX1, this.mY1, this.mColorLongs, this.mPositions, this.mTileMode.nativeInt, colorSpace().getNativeInstance());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/LinearGradient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */