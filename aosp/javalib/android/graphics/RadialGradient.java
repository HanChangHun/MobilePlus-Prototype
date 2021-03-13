package android.graphics;

public class RadialGradient extends Shader {
  private int mCenterColor;
  
  private final long[] mColorLongs;
  
  private int[] mColors;
  
  private int mEdgeColor;
  
  private float[] mPositions;
  
  private float mRadius;
  
  private Shader.TileMode mTileMode;
  
  private float mX;
  
  private float mY;
  
  public RadialGradient(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2, Shader.TileMode paramTileMode) {
    this(paramFloat1, paramFloat2, paramFloat3, Color.pack(paramInt1), Color.pack(paramInt2), paramTileMode);
  }
  
  public RadialGradient(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong1, long paramLong2, Shader.TileMode paramTileMode) {
    this(paramFloat1, paramFloat2, paramFloat3, new long[] { paramLong1, paramLong2 }, (float[])null, paramTileMode);
  }
  
  public RadialGradient(float paramFloat1, float paramFloat2, float paramFloat3, int[] paramArrayOfint, float[] paramArrayOffloat, Shader.TileMode paramTileMode) {
    this(paramFloat1, paramFloat2, paramFloat3, convertColors(paramArrayOfint), paramArrayOffloat, paramTileMode, ColorSpace.get(ColorSpace.Named.SRGB));
  }
  
  public RadialGradient(float paramFloat1, float paramFloat2, float paramFloat3, long[] paramArrayOflong, float[] paramArrayOffloat, Shader.TileMode paramTileMode) {
    this(paramFloat1, paramFloat2, paramFloat3, (long[])paramArrayOflong.clone(), paramArrayOffloat, paramTileMode, detectColorSpace(paramArrayOflong));
  }
  
  private RadialGradient(float paramFloat1, float paramFloat2, float paramFloat3, long[] paramArrayOflong, float[] paramArrayOffloat, Shader.TileMode paramTileMode, ColorSpace paramColorSpace) {
    super(paramColorSpace);
    if (paramFloat3 > 0.0F) {
      if (paramArrayOffloat == null || paramArrayOflong.length == paramArrayOffloat.length) {
        this.mX = paramFloat1;
        this.mY = paramFloat2;
        this.mRadius = paramFloat3;
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
    throw new IllegalArgumentException("radius must be > 0");
  }
  
  private static native long nativeCreate(long paramLong1, float paramFloat1, float paramFloat2, float paramFloat3, long[] paramArrayOflong, float[] paramArrayOffloat, int paramInt, long paramLong2);
  
  long createNativeInstance(long paramLong) {
    return nativeCreate(paramLong, this.mX, this.mY, this.mRadius, this.mColorLongs, this.mPositions, this.mTileMode.nativeInt, colorSpace().getNativeInstance());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/RadialGradient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */