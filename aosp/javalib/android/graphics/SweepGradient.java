package android.graphics;

public class SweepGradient extends Shader {
  private int mColor0;
  
  private int mColor1;
  
  private final long[] mColorLongs;
  
  private int[] mColors;
  
  private float mCx;
  
  private float mCy;
  
  private float[] mPositions;
  
  public SweepGradient(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2) {
    this(paramFloat1, paramFloat2, Color.pack(paramInt1), Color.pack(paramInt2));
  }
  
  public SweepGradient(float paramFloat1, float paramFloat2, long paramLong1, long paramLong2) {
    this(paramFloat1, paramFloat2, new long[] { paramLong1, paramLong2 }, (float[])null);
  }
  
  public SweepGradient(float paramFloat1, float paramFloat2, int[] paramArrayOfint, float[] paramArrayOffloat) {
    this(paramFloat1, paramFloat2, convertColors(paramArrayOfint), paramArrayOffloat, ColorSpace.get(ColorSpace.Named.SRGB));
  }
  
  public SweepGradient(float paramFloat1, float paramFloat2, long[] paramArrayOflong, float[] paramArrayOffloat) {
    this(paramFloat1, paramFloat2, (long[])paramArrayOflong.clone(), paramArrayOffloat, detectColorSpace(paramArrayOflong));
  }
  
  private SweepGradient(float paramFloat1, float paramFloat2, long[] paramArrayOflong, float[] paramArrayOffloat, ColorSpace paramColorSpace) {
    super(paramColorSpace);
    if (paramArrayOffloat == null || paramArrayOflong.length == paramArrayOffloat.length) {
      this.mCx = paramFloat1;
      this.mCy = paramFloat2;
      this.mColorLongs = paramArrayOflong;
      if (paramArrayOffloat != null) {
        float[] arrayOfFloat = (float[])paramArrayOffloat.clone();
      } else {
        paramArrayOflong = null;
      } 
      this.mPositions = (float[])paramArrayOflong;
      return;
    } 
    throw new IllegalArgumentException("color and position arrays must be of equal length");
  }
  
  private static native long nativeCreate(long paramLong1, float paramFloat1, float paramFloat2, long[] paramArrayOflong, float[] paramArrayOffloat, long paramLong2);
  
  long createNativeInstance(long paramLong) {
    return nativeCreate(paramLong, this.mCx, this.mCy, this.mColorLongs, this.mPositions, colorSpace().getNativeInstance());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/SweepGradient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */