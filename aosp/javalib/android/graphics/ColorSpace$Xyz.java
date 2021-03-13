package android.graphics;

final class Xyz extends ColorSpace {
  private Xyz(String paramString, int paramInt) {
    super(paramString, ColorSpace.Model.XYZ, paramInt);
  }
  
  private static float clamp(float paramFloat) {
    float f = -2.0F;
    if (paramFloat < -2.0F) {
      paramFloat = f;
    } else if (paramFloat > 2.0F) {
      paramFloat = 2.0F;
    } 
    return paramFloat;
  }
  
  public float[] fromXyz(float[] paramArrayOffloat) {
    paramArrayOffloat[0] = clamp(paramArrayOffloat[0]);
    paramArrayOffloat[1] = clamp(paramArrayOffloat[1]);
    paramArrayOffloat[2] = clamp(paramArrayOffloat[2]);
    return paramArrayOffloat;
  }
  
  public float getMaxValue(int paramInt) {
    return 2.0F;
  }
  
  public float getMinValue(int paramInt) {
    return -2.0F;
  }
  
  public boolean isWideGamut() {
    return true;
  }
  
  public float[] toXyz(float[] paramArrayOffloat) {
    paramArrayOffloat[0] = clamp(paramArrayOffloat[0]);
    paramArrayOffloat[1] = clamp(paramArrayOffloat[1]);
    paramArrayOffloat[2] = clamp(paramArrayOffloat[2]);
    return paramArrayOffloat;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$Xyz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */