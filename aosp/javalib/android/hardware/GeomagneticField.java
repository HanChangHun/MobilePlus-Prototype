package android.hardware;

import java.util.GregorianCalendar;

public class GeomagneticField {
  private static final long BASE_TIME;
  
  private static final float[][] DELTA_G;
  
  private static final float[][] DELTA_H;
  
  private static final float EARTH_REFERENCE_RADIUS_KM = 6371.2F;
  
  private static final float EARTH_SEMI_MAJOR_AXIS_KM = 6378.137F;
  
  private static final float EARTH_SEMI_MINOR_AXIS_KM = 6356.7524F;
  
  private static final float[][] G_COEFF;
  
  private static final float[][] H_COEFF;
  
  private static final float[][] SCHMIDT_QUASI_NORM_FACTORS;
  
  private float mGcLatitudeRad;
  
  private float mGcLongitudeRad;
  
  private float mGcRadiusKm;
  
  private float mX;
  
  private float mY;
  
  private float mZ;
  
  static {
    float[] arrayOfFloat1 = { 0.0F };
    float[] arrayOfFloat2 = { -29438.5F, -1501.1F };
    float[] arrayOfFloat3 = { -2445.3F, 3012.5F, 1676.6F };
    float[] arrayOfFloat4 = { 1351.1F, -2352.3F, 1225.6F, 581.9F };
    float[] arrayOfFloat5 = { 907.2F, 813.7F, 120.3F, -335.0F, 70.3F };
    float[] arrayOfFloat6 = { 81.6F, -76.1F, -6.8F, 51.9F, 15.0F, 9.3F, -2.8F, 6.7F };
    float[] arrayOfFloat7 = { 24.0F, 8.6F, -16.9F, -3.2F, -20.6F, 13.3F, 11.7F, -16.0F, -2.0F };
    float[] arrayOfFloat8 = { 
        3.1F, -1.5F, -2.3F, 2.1F, -0.9F, 0.6F, -0.7F, 0.2F, 1.7F, -0.2F, 
        0.4F, 3.5F };
    G_COEFF = new float[][] { 
        arrayOfFloat1, arrayOfFloat2, arrayOfFloat3, arrayOfFloat4, arrayOfFloat5, { -232.6F, 360.1F, 192.4F, -141.0F, -157.4F, 4.3F }, { 69.5F, 67.4F, 72.8F, -129.8F, -29.0F, 13.2F, -70.9F }, arrayOfFloat6, arrayOfFloat7, { 5.4F, 8.8F, 3.1F, -3.1F, 0.6F, -13.3F, -0.1F, 8.7F, -9.1F, -10.5F }, 
        { 
          -1.9F, -6.5F, 0.2F, 0.6F, -0.6F, 1.7F, -0.7F, 2.1F, 2.3F, -1.8F, 
          -3.6F }, arrayOfFloat8, { 
          -2.0F, -0.3F, 0.4F, 1.3F, -0.9F, 0.9F, 0.1F, 0.5F, -0.4F, -0.4F, 
          0.2F, -0.9F, 0.0F } };
    arrayOfFloat1 = new float[] { 0.0F, 283.4F, -188.6F, 180.9F, -329.5F };
    arrayOfFloat2 = new float[] { 
        0.0F, -0.1F, 2.1F, -0.7F, -1.1F, 0.7F, -0.2F, -2.1F, -1.5F, -2.5F, 
        -2.0F, -2.3F };
    H_COEFF = new float[][] { 
        { 0.0F }, { 0.0F, 4796.2F }, { 0.0F, -2845.6F, -642.0F }, { 0.0F, -115.3F, 245.0F, -538.3F }, arrayOfFloat1, { 0.0F, 47.4F, 196.9F, -119.4F, 16.1F, 100.1F }, { 0.0F, -20.7F, 33.2F, 58.8F, -66.5F, 7.3F, 62.5F }, { 0.0F, -54.1F, -19.4F, 5.6F, 24.4F, 3.3F, -27.5F, -2.3F }, { 0.0F, 10.2F, -18.1F, 13.2F, -14.6F, 16.2F, 5.7F, -9.1F, 2.2F }, { 0.0F, -21.6F, 10.8F, 11.7F, -6.8F, -6.9F, 7.8F, 1.0F, -3.9F, 8.5F }, 
        { 
          0.0F, 3.3F, -0.3F, 4.6F, 4.4F, -7.9F, -0.6F, -4.1F, -2.8F, -1.1F, 
          -8.7F }, arrayOfFloat2, { 
          0.0F, -1.0F, 0.5F, 1.8F, -2.2F, 0.3F, 0.7F, -0.1F, 0.3F, 0.2F, 
          -0.9F, -0.2F, 0.7F } };
    DELTA_G = new float[][] { 
        { 0.0F }, { 10.7F, 17.9F }, { -8.6F, -3.3F, 2.4F }, { 3.1F, -6.2F, -0.4F, -10.4F }, { -0.4F, 0.8F, -9.2F, 4.0F, -4.2F }, { -0.2F, 0.1F, -1.4F, 0.0F, 1.3F, 3.8F }, { -0.5F, -0.2F, -0.6F, 2.4F, -1.1F, 0.3F, 1.5F }, { 0.2F, -0.2F, -0.4F, 1.3F, 0.2F, -0.4F, -0.9F, 0.3F }, { 0.0F, 0.1F, -0.5F, 0.5F, -0.2F, 0.4F, 0.2F, -0.4F, 0.3F }, { 0.0F, -0.1F, -0.1F, 0.4F, -0.5F, -0.2F, 0.1F, 0.0F, -0.2F, -0.1F }, 
        { 
          0.0F, 0.0F, -0.1F, 0.3F, -0.1F, -0.1F, -0.1F, 0.0F, -0.2F, -0.1F, 
          -0.2F }, { 
          0.0F, 0.0F, -0.1F, 0.1F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 
          -0.1F, -0.1F }, { 
          0.1F, 0.0F, 0.0F, 0.1F, -0.1F, 0.0F, 0.1F, 0.0F, 0.0F, 0.0F, 
          0.0F, 0.0F, 0.0F } };
    arrayOfFloat1 = new float[] { 0.0F, -0.3F, 0.3F, 0.3F, 0.6F, -0.1F, -0.2F, 0.3F, 0.0F };
    DELTA_H = new float[][] { 
        { 0.0F }, { 0.0F, -26.8F }, { 0.0F, -27.1F, -13.3F }, { 0.0F, 8.4F, -0.4F, 2.3F }, { 0.0F, -0.6F, 5.3F, 3.0F, -5.3F }, { 0.0F, 0.4F, 1.6F, -1.1F, 3.3F, 0.1F }, { 0.0F, 0.0F, -2.2F, -0.7F, 0.1F, 1.0F, 1.3F }, { 0.0F, 0.7F, 0.5F, -0.2F, -0.1F, -0.7F, 0.1F, 0.1F }, arrayOfFloat1, { 0.0F, -0.2F, -0.1F, -0.2F, 0.1F, 0.1F, 0.0F, -0.2F, 0.4F, 0.3F }, 
        { 
          0.0F, 0.1F, -0.1F, 0.0F, 0.0F, -0.2F, 0.1F, -0.1F, -0.2F, 0.1F, 
          -0.1F }, { 
          0.0F, 0.0F, 0.1F, 0.0F, 0.1F, 0.0F, 0.0F, 0.1F, 0.0F, -0.1F, 
          0.0F, -0.1F }, { 
          0.0F, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 
          0.0F, 0.0F, 0.0F } };
    BASE_TIME = (new GregorianCalendar(2015, 1, 1)).getTimeInMillis();
    SCHMIDT_QUASI_NORM_FACTORS = computeSchmidtQuasiNormFactors(G_COEFF.length);
  }
  
  public GeomagneticField(float paramFloat1, float paramFloat2, float paramFloat3, long paramLong) {
    int i = G_COEFF.length;
    float f1 = Math.min(89.99999F, Math.max(-89.99999F, paramFloat1));
    computeGeocentricCoordinates(f1, paramFloat2, paramFloat3);
    LegendreTable legendreTable = new LegendreTable(i - 1, (float)(1.5707963267948966D - this.mGcLatitudeRad));
    float[] arrayOfFloat1 = new float[i + 2];
    arrayOfFloat1[0] = 1.0F;
    arrayOfFloat1[1] = 6371.2F / this.mGcRadiusKm;
    byte b;
    for (b = 2; b < arrayOfFloat1.length; b++)
      arrayOfFloat1[b] = arrayOfFloat1[b - 1] * arrayOfFloat1[1]; 
    float[] arrayOfFloat2 = new float[i];
    float[] arrayOfFloat3 = new float[i];
    arrayOfFloat2[0] = 0.0F;
    arrayOfFloat3[0] = 1.0F;
    arrayOfFloat2[1] = (float)Math.sin(this.mGcLongitudeRad);
    arrayOfFloat3[1] = (float)Math.cos(this.mGcLongitudeRad);
    for (b = 2; b < i; b++) {
      int j = b >> 1;
      arrayOfFloat2[b] = arrayOfFloat2[b - j] * arrayOfFloat3[j] + arrayOfFloat3[b - j] * arrayOfFloat2[j];
      arrayOfFloat3[b] = arrayOfFloat3[b - j] * arrayOfFloat3[j] - arrayOfFloat2[b - j] * arrayOfFloat2[j];
    } 
    float f2 = 1.0F / (float)Math.cos(this.mGcLatitudeRad);
    float f3 = (float)(paramLong - BASE_TIME) / 3.1536001E10F;
    paramFloat3 = 0.0F;
    paramFloat2 = 0.0F;
    paramFloat1 = 0.0F;
    for (b = 1; b < i; b++) {
      for (byte b1 = 0; b1 <= b; b1++) {
        float f4 = G_COEFF[b][b1] + DELTA_G[b][b1] * f3;
        float f5 = H_COEFF[b][b1] + DELTA_H[b][b1] * f3;
        paramFloat3 += arrayOfFloat1[b + 2] * (arrayOfFloat3[b1] * f4 + arrayOfFloat2[b1] * f5) * legendreTable.mPDeriv[b][b1] * SCHMIDT_QUASI_NORM_FACTORS[b][b1];
        paramFloat2 += arrayOfFloat1[b + 2] * b1 * (arrayOfFloat2[b1] * f4 - arrayOfFloat3[b1] * f5) * legendreTable.mP[b][b1] * SCHMIDT_QUASI_NORM_FACTORS[b][b1] * f2;
        paramFloat1 -= (b + 1) * arrayOfFloat1[b + 2] * (arrayOfFloat3[b1] * f4 + arrayOfFloat2[b1] * f5) * legendreTable.mP[b][b1] * SCHMIDT_QUASI_NORM_FACTORS[b][b1];
      } 
    } 
    double d = Math.toRadians(f1) - this.mGcLatitudeRad;
    this.mX = (float)(paramFloat3 * Math.cos(d) + paramFloat1 * Math.sin(d));
    this.mY = paramFloat2;
    this.mZ = (float)(-paramFloat3 * Math.sin(d) + paramFloat1 * Math.cos(d));
  }
  
  private void computeGeocentricCoordinates(float paramFloat1, float paramFloat2, float paramFloat3) {
    paramFloat3 /= 1000.0F;
    double d = Math.toRadians(paramFloat1);
    paramFloat1 = (float)Math.cos(d);
    float f1 = (float)Math.sin(d);
    float f2 = f1 / paramFloat1;
    float f3 = (float)Math.sqrt((4.0680636E7F * paramFloat1 * paramFloat1 + 4.04083E7F * f1 * f1));
    this.mGcLatitudeRad = (float)Math.atan(((f3 * paramFloat3 + 4.04083E7F) * f2 / (f3 * paramFloat3 + 4.0680636E7F)));
    this.mGcLongitudeRad = (float)Math.toRadians(paramFloat2);
    this.mGcRadiusKm = (float)Math.sqrt((paramFloat3 * paramFloat3 + 2.0F * paramFloat3 * (float)Math.sqrt((4.0680636E7F * paramFloat1 * paramFloat1 + 4.04083E7F * f1 * f1)) + (4.0680636E7F * 4.0680636E7F * paramFloat1 * paramFloat1 + 4.04083E7F * 4.04083E7F * f1 * f1) / (4.0680636E7F * paramFloat1 * paramFloat1 + 4.04083E7F * f1 * f1)));
  }
  
  private static float[][] computeSchmidtQuasiNormFactors(int paramInt) {
    float[][] arrayOfFloat = new float[paramInt + 1][];
    (new float[1])[0] = 1.0F;
    arrayOfFloat[0] = new float[1];
    for (byte b = 1; b <= paramInt; b++) {
      arrayOfFloat[b] = new float[b + 1];
      arrayOfFloat[b][0] = arrayOfFloat[b - 1][0] * (b * 2 - 1) / b;
      for (byte b1 = 1; b1 <= b; b1++) {
        byte b2;
        float[] arrayOfFloat1 = arrayOfFloat[b];
        float f = arrayOfFloat[b][b1 - 1];
        if (b1 == 1) {
          b2 = 2;
        } else {
          b2 = 1;
        } 
        arrayOfFloat1[b1] = f * (float)Math.sqrt((((b - b1 + 1) * b2) / (b + b1)));
      } 
    } 
    return arrayOfFloat;
  }
  
  public float getDeclination() {
    return (float)Math.toDegrees(Math.atan2(this.mY, this.mX));
  }
  
  public float getFieldStrength() {
    float f1 = this.mX;
    float f2 = this.mY;
    float f3 = this.mZ;
    return (float)Math.sqrt((f1 * f1 + f2 * f2 + f3 * f3));
  }
  
  public float getHorizontalStrength() {
    return (float)Math.hypot(this.mX, this.mY);
  }
  
  public float getInclination() {
    return (float)Math.toDegrees(Math.atan2(this.mZ, getHorizontalStrength()));
  }
  
  public float getX() {
    return this.mX;
  }
  
  public float getY() {
    return this.mY;
  }
  
  public float getZ() {
    return this.mZ;
  }
  
  private static class LegendreTable {
    public final float[][] mP;
    
    public final float[][] mPDeriv;
    
    public LegendreTable(int param1Int, float param1Float) {
      float f = (float)Math.cos(param1Float);
      param1Float = (float)Math.sin(param1Float);
      float[][] arrayOfFloat1 = new float[param1Int + 1][];
      this.mP = arrayOfFloat1;
      float[][] arrayOfFloat2 = new float[param1Int + 1][];
      this.mPDeriv = arrayOfFloat2;
      (new float[1])[0] = 1.0F;
      arrayOfFloat1[0] = new float[1];
      (new float[1])[0] = 0.0F;
      arrayOfFloat2[0] = new float[1];
      for (byte b = 1; b <= param1Int; b++) {
        this.mP[b] = new float[b + 1];
        this.mPDeriv[b] = new float[b + 1];
        for (byte b1 = 0; b1 <= b; b1++) {
          if (b == b1) {
            arrayOfFloat1 = this.mP;
            arrayOfFloat1[b][b1] = arrayOfFloat1[b - 1][b1 - 1] * param1Float;
            arrayOfFloat2 = this.mPDeriv;
            arrayOfFloat2[b][b1] = arrayOfFloat1[b - 1][b1 - 1] * f + arrayOfFloat2[b - 1][b1 - 1] * param1Float;
          } else if (b == 1 || b1 == b - 1) {
            arrayOfFloat2 = this.mP;
            arrayOfFloat2[b][b1] = arrayOfFloat2[b - 1][b1] * f;
            arrayOfFloat1 = this.mPDeriv;
            arrayOfFloat1[b][b1] = -param1Float * arrayOfFloat2[b - 1][b1] + arrayOfFloat1[b - 1][b1] * f;
          } else {
            float f1 = ((b - 1) * (b - 1) - b1 * b1) / ((b * 2 - 1) * (b * 2 - 3));
            arrayOfFloat2 = this.mP;
            arrayOfFloat2[b][b1] = arrayOfFloat2[b - 1][b1] * f - arrayOfFloat2[b - 2][b1] * f1;
            arrayOfFloat1 = this.mPDeriv;
            arrayOfFloat1[b][b1] = -param1Float * arrayOfFloat2[b - 1][b1] + arrayOfFloat1[b - 1][b1] * f - arrayOfFloat1[b - 2][b1] * f1;
          } 
        } 
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/GeomagneticField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */