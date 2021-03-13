package android.hardware;

class LegendreTable {
  public final float[][] mP;
  
  public final float[][] mPDeriv;
  
  public LegendreTable(int paramInt, float paramFloat) {
    float f = (float)Math.cos(paramFloat);
    paramFloat = (float)Math.sin(paramFloat);
    float[][] arrayOfFloat1 = new float[paramInt + 1][];
    this.mP = arrayOfFloat1;
    float[][] arrayOfFloat2 = new float[paramInt + 1][];
    this.mPDeriv = arrayOfFloat2;
    (new float[1])[0] = 1.0F;
    arrayOfFloat1[0] = new float[1];
    (new float[1])[0] = 0.0F;
    arrayOfFloat2[0] = new float[1];
    for (byte b = 1; b <= paramInt; b++) {
      this.mP[b] = new float[b + 1];
      this.mPDeriv[b] = new float[b + 1];
      for (byte b1 = 0; b1 <= b; b1++) {
        if (b == b1) {
          arrayOfFloat1 = this.mP;
          arrayOfFloat1[b][b1] = arrayOfFloat1[b - 1][b1 - 1] * paramFloat;
          arrayOfFloat2 = this.mPDeriv;
          arrayOfFloat2[b][b1] = arrayOfFloat1[b - 1][b1 - 1] * f + arrayOfFloat2[b - 1][b1 - 1] * paramFloat;
        } else if (b == 1 || b1 == b - 1) {
          arrayOfFloat2 = this.mP;
          arrayOfFloat2[b][b1] = arrayOfFloat2[b - 1][b1] * f;
          arrayOfFloat1 = this.mPDeriv;
          arrayOfFloat1[b][b1] = -paramFloat * arrayOfFloat2[b - 1][b1] + arrayOfFloat1[b - 1][b1] * f;
        } else {
          float f1 = ((b - 1) * (b - 1) - b1 * b1) / ((b * 2 - 1) * (b * 2 - 3));
          arrayOfFloat2 = this.mP;
          arrayOfFloat2[b][b1] = arrayOfFloat2[b - 1][b1] * f - arrayOfFloat2[b - 2][b1] * f1;
          arrayOfFloat1 = this.mPDeriv;
          arrayOfFloat1[b][b1] = -paramFloat * arrayOfFloat2[b - 1][b1] + arrayOfFloat1[b - 1][b1] * f - arrayOfFloat1[b - 2][b1] * f1;
        } 
      } 
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/GeomagneticField$LegendreTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */