package android.graphics;

final class Lab extends ColorSpace {
  private static final float A = 0.008856452F;
  
  private static final float B = 7.787037F;
  
  private static final float C = 0.13793103F;
  
  private static final float D = 0.20689656F;
  
  private Lab(String paramString, int paramInt) {
    super(paramString, ColorSpace.Model.LAB, paramInt);
  }
  
  private static float clamp(float paramFloat1, float paramFloat2, float paramFloat3) {
    if (paramFloat1 < paramFloat2) {
      paramFloat1 = paramFloat2;
    } else if (paramFloat1 > paramFloat3) {
      paramFloat1 = paramFloat3;
    } 
    return paramFloat1;
  }
  
  public float[] fromXyz(float[] paramArrayOffloat) {
    float f1 = paramArrayOffloat[0] / ColorSpace.access$1000()[0];
    float f2 = paramArrayOffloat[1] / ColorSpace.access$1000()[1];
    float f3 = paramArrayOffloat[2] / ColorSpace.access$1000()[2];
    if (f1 > 0.008856452F) {
      f1 = (float)Math.pow(f1, 0.3333333333333333D);
    } else {
      f1 = f1 * 7.787037F + 0.13793103F;
    } 
    if (f2 > 0.008856452F) {
      f2 = (float)Math.pow(f2, 0.3333333333333333D);
    } else {
      f2 = f2 * 7.787037F + 0.13793103F;
    } 
    if (f3 > 0.008856452F) {
      f3 = (float)Math.pow(f3, 0.3333333333333333D);
    } else {
      f3 = 7.787037F * f3 + 0.13793103F;
    } 
    paramArrayOffloat[0] = clamp(116.0F * f2 - 16.0F, 0.0F, 100.0F);
    paramArrayOffloat[1] = clamp((f1 - f2) * 500.0F, -128.0F, 128.0F);
    paramArrayOffloat[2] = clamp((f2 - f3) * 200.0F, -128.0F, 128.0F);
    return paramArrayOffloat;
  }
  
  public float getMaxValue(int paramInt) {
    float f;
    if (paramInt == 0) {
      f = 100.0F;
    } else {
      f = 128.0F;
    } 
    return f;
  }
  
  public float getMinValue(int paramInt) {
    float f;
    if (paramInt == 0) {
      f = 0.0F;
    } else {
      f = -128.0F;
    } 
    return f;
  }
  
  public boolean isWideGamut() {
    return true;
  }
  
  public float[] toXyz(float[] paramArrayOffloat) {
    paramArrayOffloat[0] = clamp(paramArrayOffloat[0], 0.0F, 100.0F);
    paramArrayOffloat[1] = clamp(paramArrayOffloat[1], -128.0F, 128.0F);
    paramArrayOffloat[2] = clamp(paramArrayOffloat[2], -128.0F, 128.0F);
    float f1 = (paramArrayOffloat[0] + 16.0F) / 116.0F;
    float f2 = paramArrayOffloat[1] * 0.002F + f1;
    float f3 = f1 - paramArrayOffloat[2] * 0.005F;
    if (f2 > 0.20689656F) {
      f2 = f2 * f2 * f2;
    } else {
      f2 = (f2 - 0.13793103F) * 0.12841855F;
    } 
    if (f1 > 0.20689656F) {
      f1 = f1 * f1 * f1;
    } else {
      f1 = (f1 - 0.13793103F) * 0.12841855F;
    } 
    if (f3 > 0.20689656F) {
      f3 = f3 * f3 * f3;
    } else {
      f3 = (f3 - 0.13793103F) * 0.12841855F;
    } 
    paramArrayOffloat[0] = ColorSpace.access$1000()[0] * f2;
    paramArrayOffloat[1] = ColorSpace.access$1000()[1] * f1;
    paramArrayOffloat[2] = ColorSpace.access$1000()[2] * f3;
    return paramArrayOffloat;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/ColorSpace$Lab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */