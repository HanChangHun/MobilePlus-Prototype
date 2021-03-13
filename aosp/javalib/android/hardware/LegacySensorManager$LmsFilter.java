package android.hardware;

final class LmsFilter {
  private static final int COUNT = 12;
  
  private static final float PREDICTION_RATIO = 0.33333334F;
  
  private static final float PREDICTION_TIME = 0.08F;
  
  private static final int SENSORS_RATE_MS = 20;
  
  private int mIndex = 12;
  
  private long[] mT = new long[24];
  
  private float[] mV = new float[24];
  
  public float filter(long paramLong, float paramFloat) {
    float f1 = paramFloat;
    float f2 = this.mV[this.mIndex];
    if (f1 - f2 > 180.0F) {
      paramFloat = f1 - 360.0F;
    } else {
      paramFloat = f1;
      if (f2 - f1 > 180.0F)
        paramFloat = f1 + 360.0F; 
    } 
    int i = this.mIndex + 1;
    this.mIndex = i;
    if (i >= 24)
      this.mIndex = 12; 
    float[] arrayOfFloat = this.mV;
    i = this.mIndex;
    arrayOfFloat[i] = paramFloat;
    long[] arrayOfLong = this.mT;
    arrayOfLong[i] = paramLong;
    arrayOfFloat[i - 12] = paramFloat;
    arrayOfLong[i - 12] = paramLong;
    f2 = 0.0F;
    float f3 = 0.0F;
    f1 = 0.0F;
    float f4 = 0.0F;
    paramFloat = 0.0F;
    for (i = 0; i < 11; i++) {
      int j = this.mIndex - 1 - i;
      float f5 = this.mV[j];
      long[] arrayOfLong1 = this.mT;
      float f6 = (float)(arrayOfLong1[j] / 2L + arrayOfLong1[j + 1] / 2L - paramLong) * 1.0E-9F;
      float f7 = (float)(arrayOfLong1[j] - arrayOfLong1[j + 1]) * 1.0E-9F;
      f7 *= f7;
      paramFloat += f5 * f7;
      f4 += f6 * f7 * f6;
      f1 += f6 * f7;
      f3 += f6 * f7 * f5;
      f2 += f7;
    } 
    f4 = (paramFloat * f4 + f1 * f3) / (f2 * f4 + f1 * f1);
    f1 = (0.08F * (f2 * f4 - paramFloat) / f1 + f4) * 0.0027777778F;
    if (f1 >= 0.0F) {
      f2 = f1;
    } else {
      f2 = -f1;
    } 
    paramFloat = f1;
    if (f2 >= 0.5F)
      paramFloat = f1 - (float)Math.ceil((0.5F + f1)) + 1.0F; 
    f1 = paramFloat;
    if (paramFloat < 0.0F)
      f1 = paramFloat + 1.0F; 
    return f1 * 360.0F;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/LegacySensorManager$LmsFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */